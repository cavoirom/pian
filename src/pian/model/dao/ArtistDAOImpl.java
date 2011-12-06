package pian.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		Connection connection = ConnectionFactory.getConnection();
		Artist artist = null;
		try {
			String sql = "SELECT * FROM Artist WHERE ID = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet set = statement.executeQuery();
			if (set.next()){
				artist = readArtist(set, 0);
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


	public Artist loadArtist(String name, int detail) {
		Connection connection = ConnectionFactory.getConnection();
		Artist artist = null;
		try {
			String sql = "SELECT * FROM Artist WHERE Name = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet set = statement.executeQuery();
			if (set.next()){
				readArtist(set, detail);
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return artist;
	}
	
	private Artist readArtist(ResultSet set, int detail) throws SQLException{
		// 0 : All field in Artist (Default)
		// 1 : Not songs
		int id = set.getInt("ID");
		String name = set.getString("Name");
		Artist artist = new Artist(name);
		artist.setId(id);
		if (detail == 0){
			artist.setSongs(new SongDAOImpl().getSongsByArtist(id, 20, 1));
		}
		return artist;
	}

	public Artist loadArtist(String name) {
		return loadArtist(name, 0);
	}

}
