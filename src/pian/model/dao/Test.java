package pian.model.dao;

import pian.model.Album;
import pian.model.Artist;
import pian.model.Song;

public class Test {
	public static void main(String[] args) {
		Artist artist = new Artist("artist 1");
		Album album =  new Album("album1");
		album.setArtist(artist);
		Song song = new Song("Title 1", artist, "Cộng hòa xã hội chủ nghĩa", album);
		System.out.println(new SongDAOImpl().storeDAO(song));
		System.out.println(new SongDAOImpl().loadSong(1).getLink());
		System.out.println(new SongDAOImpl().loadSong(1).getAlbum().getName());
		System.out.println(new SongDAOImpl().findSongsByTitle("Title", 1, 1).get(0).getTitle());
		System.out.println(new SongDAOImpl().findSongsByArtistName("artist", 1, 1).get(0).getLink());
		
	}
}
