package pian.model.dao;

import pian.model.Artist;

public interface ArtistDAO {
	public boolean storeArtist(Artist a);
	
	public Artist loadArtist(int id);
	
	public boolean deleteArtist(int id);
	
	public boolean deleteArtist(Artist a);
	
	public void closeConnection();
}
