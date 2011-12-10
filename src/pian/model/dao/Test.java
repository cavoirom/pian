package pian.model.dao;

import java.io.IOException;


public class Test {
	public static void main(String[] args) throws IOException {
//		Artist artist = new Artist("Lam Trường");
//		Album album =  new Album("album1");
//		album.setArtist(artist);
//		Song song = new Song("Title 1", artist, "Cộng hòa xã hội chủ nghĩa", album);
//		System.out.println(new SongDAOImpl().storeSong(song));
		System.out.println(new SongDAOImpl().loadSong(1).getTitle());
	}
}
