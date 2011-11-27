package pian.model.dao;

import java.util.List;

import pian.model.Song;

public interface SongDAO {
	public boolean storeDAO(Song s);
	
	public Song loadSong(int id);
	
	public boolean deleteSong(int id);
	
	public boolean deleteSong(Song s);
	
	public List<Song> getSongsByAlbum(int albumID, int numberResult, int page);
	
	public List<Song> getSongsByArtist(int artistID, int numberResult, int page);
}
