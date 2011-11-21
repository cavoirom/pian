package pian.model.dao;

public interface ArtistDAO {
	public void storeArtist(ArtistDAO a);
	
	public ArtistDAO loadArtist(int id);
	
	public boolean deleteArtist(int id);
	
	public boolean deleteArtist(ArtistDAO a);
}
