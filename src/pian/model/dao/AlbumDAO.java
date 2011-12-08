package pian.model.dao;

import pian.model.Album;

public interface AlbumDAO {
	public boolean storeAlbum(Album a);
	
	public Album loadAlbum(int id);
	
	public boolean deleteAlbum(int id);
	
	public boolean deleteAlbum(Album a);
	
	public void closeConnection();
}
