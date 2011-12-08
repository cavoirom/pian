package pian.model.dao;

import java.io.InputStream;
import java.util.List;

import pian.model.Song;

public interface SongDAO {
	public int storeSong(Song s);
	
	public Song loadSong(int id);
	
	public boolean deleteSong(int id);
	
	public boolean deleteSong(Song s);
	
	public List<Song> getSongsByAlbum(int albumID, int numberResult, int page);
	
	public List<Song> getSongsByArtist(int artistID, int numberResult, int page);
	
	public List<Song> findSongsByAlbumName(String name, int numberResult, int page);
	
	public List<Song> findSongsByArtistName(String name, int numberResult, int page);
	
	public List<Song> findSongsByTitle(String name, int numberResult, int page);
	
	public byte[] play(int songID);
	
	public boolean upload(int songID, byte[] data);
	
	public void closeConnection();
}
