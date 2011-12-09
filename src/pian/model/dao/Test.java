package pian.model.dao;

import java.io.IOException;

import pian.model.Album;
import pian.model.Artist;
import pian.model.Song;


public class Test {
	public static void main(String[] args) throws IOException {
		Artist artist = new Artist("Lam Trường");
		Album album =  new Album("album1");
		album.setArtist(artist);
		Song song = new Song("Title 1", artist, "Cộng hòa xã hội chủ nghĩa", album);
		System.out.println(new SongDAOImpl().storeSong(song));
		System.out.println(new SongDAOImpl().loadSong(1).getLink());
//		System.out.println(new SongDAOImpl().loadSong(12).getAlbum().getName());
//		System.out.println(new SongDAOImpl().findSongsByTitle("Anh khac hay em khac", -1, 1).size());
//		System.out.println(new SongDAOImpl().findSongsByArtistName("Khac", 5, 1).size());
//		System.out.println(new SongDAOImpl().findSongsByTitle("Noi Dau", 1, 1).get(0).getLink());
//		SongDAOImpl download = new SongDAOImpl();
//		InputStream in = new ByteArrayInputStream(download.play(1));
//		BufferedInputStream inin = new BufferedInputStream(in);
//		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("asdf.mp3"));
//		int readed = -1;
//		byte[] buff = new byte[10240];
//		while ((readed = inin.read(buff)) != -1){
//			out.write(buff,0,readed);
//		}
//		out.flush();
//		inin.close();
//		in.close();
//		out.close();
	}
}
