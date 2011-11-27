package pian.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pian.model.Album;
import pian.model.Artist;

public class AlbumDAOImpl implements AlbumDAO{

	@Override
	public boolean storeAlbum(Album a) {
		Connection connection = Connect.getConnection();
		try {
			String sql = "INSERT INTO Song (ArtistID, Name) VALUES(?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, a.getArtist().getId());
			statement.setString(2, a.getName());
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
	public Album loadAlbum(int id) {
		Connection connection = Connect.getConnection();
		Album album = null;
		try {
			String sql = "SELECT * FROM Album WHERE ID = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet set = statement.executeQuery();
			if (set.next()){
				album = readAlbum(set, 0);
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return album;
	}

	@Override
	public boolean deleteAlbum(int id) {
		Connection connection = Connect.getConnection();
		try {
			String sql = "DELETE FROM Album WHERE ID = ?;";
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
	public boolean deleteAlbum(Album a) {
		return deleteAlbum(a.getId());
	}
	
	private Album readAlbum(ResultSet set, int detail) throws SQLException{
		// 0 : All field in Artist (Default)
		// 1 : Not songs
		int id = set.getInt("ID");
		String name = set.getString("Name");
		Album album = new Album(name);
		album.setId(id);
		if (detail == 0){
			album.setSongs(new SongDAOImpl().getSongsByAlbum(id, 20, 1));
		}
		return album;
	}
}
