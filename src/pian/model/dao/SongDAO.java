package pian.model.dao;

import pian.model.Song;

public interface SongDAO {
	public boolean storeDAO(Song s);
	
	public Song loadSong(int id);
	
	public boolean deleteSong(int id);
	
	public boolean deleteSong(Song s);
}
