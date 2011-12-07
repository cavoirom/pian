package pian.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pian.model.Album;
import pian.model.Artist;

public class ArtistDAOImpl implements ArtistDAO{

	@Override
	public boolean storeArtist(Artist a) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "INSERT INTO Song (Name) VALUES(?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, a.getName());
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
	public Artist loadArtist(int id) {
		return addSongs(loadArtistNoSongs(id));
	}
	
	public Artist loadArtistNoSongs(int id) {
		Connection connection = ConnectionFactory.getConnection();
		Artist artist = null;
		try {
			String sql = "SELECT * FROM Artist WHERE ID = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet set = statement.executeQuery();
			if (set.next()){
				artist = readArtist(set);
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artist;
	}

	@Override
	public boolean deleteArtist(int id) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "DELETE FROM Artist WHERE ID = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			int ret = statement.executeUpdate();
			statement.close();
			connection.close();
			if (ret >=1){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteArtist(Artist a) {
		return deleteArtist(a.getId());
	}


	public Artist loadArtist(String name) {
		return addSongs(loadArtist(name));
	}
	
	public Artist loadArtistNoSongs(String name) {
		Connection connection = ConnectionFactory.getConnection();
		Artist artist = null;
		try {
			String sql = "SELECT * FROM Artist WHERE Name = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet set = statement.executeQuery();
			if (set.next()){
				readArtist(set);
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artist;
	}
	
	private Artist readArtist(ResultSet set) throws SQLException{
		int id = set.getInt("ID");
		String name = set.getString("Name");
		Artist artist = new Artist(name);
		artist.setId(id);
		return artist;
	}

	private Artist addSongs(Artist artist){
		if (artist == null)
			return null;
		artist.setSongs(new SongDAOImpl().getSongsByArtist(artist.getId(), 20, 1));
		return artist;
	}
	
	public List<Artist> findArtistsByName(String name){
		Connection connection = ConnectionFactory.getConnection();
		List<Artist> artists = new ArrayList<Artist>();
		try {
			String sql = "SELECT * FROM Artist WHERE Name LIKE %?%;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet set = statement.executeQuery();
			if (set.next()){
				artists.add(readArtist(set));
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artists;
	}

}
