package pian.model.dao;

import pian.model.Album;
import pian.model.Artist;
import pian.model.Song;

public class Test {
	public static void main(String[] args) {
		Artist artist = new Artist("artist 1");
		Album album =  new Album("album1");
		Song song = new Song("Title 1", artist, "Cộng hòa xã hội chủ nghĩa", album);
//		System.out.println(new SongDAOImpl().storeDAO(song));
		System.out.println(new SongDAOImpl().loadSong(3).getLink());
		
	}
}
