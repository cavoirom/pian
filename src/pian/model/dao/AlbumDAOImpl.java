package pian.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pian.model.Album;

public class AlbumDAOImpl implements AlbumDAO{
	
	private Connection connection;
	
	public AlbumDAOImpl(){
		this.connection = ConnectionFactory.getConnection();
	}
	
	public AlbumDAOImpl(Connection connection){
		this.connection = connection;
	}

	@Override
	public boolean storeAlbum(Album a) {
		if (existAlbum(a) || a == null)
			return false;
		
//		Connection connection = ConnectionFactory.getConnection();
		try {
			new ArtistDAOImpl(connection).storeArtist(a.getArtist());
			int artistID = new ArtistDAOImpl().getArtistByName(a.getArtist().getName()).getId();
			String sql = "INSERT INTO Album (ArtistID, Name) VALUES(?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, artistID);
			statement.setString(2, a.getName());
			statement.executeUpdate();
			statement.close();
			//connection.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return false;
	}

	@Override
	public Album loadAlbum(int id) {
		return addSongs(loadAlbum(id));
	}
	
	public Album loadAlbumNoSongs(int id) {
//		Connection connection = ConnectionFactory.getConnection();
		Album album = null;
		try {
			String sql = "SELECT * FROM Album WHERE ID = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet set = statement.executeQuery();
			if (set.next()){
				album = readAlbum(set);
			}
			statement.close();
			//connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return album;
	}

	@Override
	public boolean deleteAlbum(int id) {
//		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "DELETE FROM Album WHERE ID = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			int ret = statement.executeUpdate();
			statement.close();
			//connection.close();
			if (ret >=1){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteAlbum(Album a) {
		return deleteAlbum(a.getId());
	}
	
	private Album readAlbum(ResultSet set) throws SQLException{
		int id = set.getInt("ID");
		String name = set.getString("Name");
		Album album = new Album(name);
		album.setId(id);
		return album;
	}
	
	private Album addSongs(Album album){
		if (album == null)
			return null;
		album.setSongs(new SongDAOImpl(connection).getSongsByAlbum(album.getId(), 20, 1));
		return album;
	}
	
	public List<Album> findAlbumsByName(String name){
//		Connection connection = ConnectionFactory.getConnection();
		List<Album> albums = new ArrayList<Album>();
		try {
			String sql = "SELECT * FROM Album WHERE Name LIKE ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + name + "%");
			ResultSet set = statement.executeQuery();
			if (set.next()){
				albums.add(readAlbum(set));
			}
			statement.close();
			//connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return albums;
	}
	
	public Album getAlbumByName(String name){
//		Connection connection = ConnectionFactory.getConnection();
		Album album = null;
		try {
			String sql = "SELECT * FROM Album WHERE Name = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet set = statement.executeQuery();
			if (set.next()){
				album = readAlbum(set);
			}
			statement.close();
			//connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return album;
	}
	
	private boolean existAlbum(Album album){
		if (album == null) return false;
		return (getAlbumByName(album.getName()) != null);
	}
	
	public void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
		}
	}
}
