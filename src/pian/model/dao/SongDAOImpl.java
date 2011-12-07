package pian.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pian.model.Album;
import pian.model.Artist;
import pian.model.Song;

public class SongDAOImpl implements SongDAO{

	@Override
	public boolean storeDAO(Song s) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "INSERT INTO Song (Title, Link, AlbumID, ArtistID) VALUES(?,?,?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, s.getTitle());
			statement.setString(2, s.getLink());
			statement.setInt(3, s.getAlbum().getId());
			statement.setInt(4, s.getArtist().getId());
			statement.executeUpdate();
			statement.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Song loadSong(int id) {
		Connection connection = ConnectionFactory.getConnection();
		Song song = null;
		try {
			String sql = "SELECT * FROM Song WHERE ID = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()){
				song = readSongAll(result);
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return song;
	}

	@Override
	public boolean deleteSong(int id) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "DELETE FROM Song WHERE ID = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			int ret = statement.executeUpdate();
			if (ret >= 1){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteSong(Song s) {
		return deleteSong(s.getId());
	}


	public List<Song> getSongsByAlbum(Album album, int numberResult, int page) {
		return getSongsByAlbum(album.getId(), numberResult, page);
	}


	public List<Song> getSongsByArtist(Artist artist, int numberResult, int page) {
		return getSongsByArtist(artist.getId(), numberResult, page);
	}

	@Override
	public List<Song> getSongsByAlbum(int albumID, int numberResult, int page) {
		Connection connection = ConnectionFactory.getConnection();
		List<Song> songs = new ArrayList<Song>();
		try {
			String sql = "SELECT * FROM Song WHERE AlbumID = ?";
			PreparedStatement statement = connection.prepareStatement(sqlWithLimit(sql, numberResult, page));
			statement.setInt(1, albumID);
			ResultSet set = statement.executeQuery();
			while (set.next()){
				songs.add(readSongAll(set));
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return songs;
	}

	@Override
	public List<Song> getSongsByArtist(int artistID, int numberResult, int page) {
		Connection connection = ConnectionFactory.getConnection();
		List<Song> songs = new ArrayList<Song>();
		try {
			String sql = "SELECT * FROM Song WHERE ArtistID = ?";
			PreparedStatement statement = connection.prepareStatement(sqlWithLimit(sql, numberResult, page));
			statement.setInt(1, artistID);
			ResultSet set = statement.executeQuery();
			while (set.next()){
				songs.add(readSongAll(set));
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return songs;
	}
	
	private Song readSongAll(ResultSet set) throws SQLException{
		Song song = readSong(set);
		Album album = new Album();
		album.setId(set.getInt("AlbumID"));
		Artist artist = new Artist();
		artist.setId(set.getInt("ArtistID"));
		song.setArtist(artist);
		song.setAlbum(album);
		return song;
	}
	
	private Song readSongHasAlbum(ResultSet set) throws SQLException{
		Song song = readSong(set);
		Album album = new Album();
		album.setId(set.getInt("AlbumID"));
		song.setAlbum(album);
		return song;
	}
	
	private Song readSongHasArtist(ResultSet set) throws SQLException{
		Song song = readSong(set);
		Artist artist = new Artist();
		artist.setId(set.getInt("ArtistID"));
		song.setArtist(artist);
		return song;
	}
	
	private Song readSong(ResultSet set) throws SQLException{
		int id = set.getInt("ID");
		String title = set.getString("Title");
		String link = set.getString("Link");
		Song song = new Song(title, link);
		song.setId(id);
		return song;
	}

	private String sqlWithLimit(String sql, int numberResult, int page){
		// numberResult -1 to not limit
		if(numberResult != -1){
			sql += " LIMIT " + (page - 1) * numberResult + ", " + numberResult;
		}else{
			sql +=";";
		}
		return sql;
	}
	
	public List<Song> findSongsByTitle(String name, int numberResult, int page){
		Connection connection = ConnectionFactory.getConnection();
		List<Song> songs = new ArrayList<Song>();
		try {
			String sql = "SELECT * FROM Song WHERE Title LIKE '%?%'";
			PreparedStatement statement = connection.prepareStatement(sqlWithLimit(sql, numberResult, page));
			statement.setString(1, name);
			ResultSet set = statement.executeQuery();
			while (set.next()){
				songs.add(readSongAll(set));
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return songs;
	}
	
	public List<Song> findSongsByArtistName(String name, int numberResult, int page){
		List<Artist> artists = new ArtistDAOImpl().findArtistsByName(name);
		List<Song> songs = new ArrayList<Song>();
		int sumSongs = page * numberResult;
		for (Artist artist : artists){
			if (numberResult == -1){
				songs.addAll(getSongsByArtist(artist, -1, 1));
			}else{
				if (songs.size() < sumSongs){
					songs.addAll(getSongsByArtist(artist, -1, 1));
				}else{
					songs = songs.subList((sumSongs - numberResult), numberResult);
					break;
				}
			}
		}
		return songs;
	}
	
	public List<Song> findSongsByAlbumName(String name, int numberResult, int page){
		List<Album> albums = new AlbumDAOImpl().findAlbumsByName(name);
		List<Song> songs = new ArrayList<Song>();
		int sumSongs = page * numberResult;
		for (Album album : albums){
			if (numberResult == -1){
				songs.addAll(getSongsByAlbum(album, -1, 1));
			}else{
				if (songs.size() < sumSongs){
					songs.addAll(getSongsByAlbum(album, -1, 1));
				}else{
					songs = songs.subList((sumSongs - numberResult), numberResult);
					break;
				}
			}
		}
		return songs;
	}
}
