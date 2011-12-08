package pian.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import pian.model.Album;
import pian.model.Artist;
import pian.model.Song;

public class GenerateData {
	
	public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
    
        // Get the size of the file
        long length = file.length();
    
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
    
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
    
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
    
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
	
	public static void main(String[] args) throws IOException {
		List<Artist> artists = new ArrayList<Artist>();
		List<Song> songs = new ArrayList<Song>();
		List<Album> albums = new ArrayList<Album>();
		String[] titles = new String[]{"Anh Khac Hay Em Khac", "Anh Sao Buon", "Anh Xin Loi", "Chi Anh Hieu Em", "Chi Can Em Hanh Phuc", "Co Khi Nao Roi Xa", "Con Mua Ngang Qua", "Co That Khong Anh", "Da Biet Se Co Ngay Hom Qua", "Dau Hieu Tinh Yeu", "Gio Lanh", "Hinh Xam Trong Trai Tim Anh", "Lac Buoc Trong Dem", "Lo Duyen Roi", "Mot Doi Canh", "Neu Nhu Anh Den", "Nga Tu Duong", "Noi Dau Xot Xa", "Phai Lam The Nao", "Sac Moi Em Hong", "Vo Tam", "Hay Vo Tinh", "Xin Anh Dung Yeu Mai Yeu"};
		String[] artistName = new String[]{"Khac Viet",
				"V.Music ft. Thuy Tien",
				"Minh Vuong M4U",
				"Khac Viet",
				"Ho Quang Hieu",
				"Bich Phuong",
				"M-TP",
				"Vy Oanh",
				"Trinh Thang Binh",
				"Pham Truong",
				"Cao Thai Son",
				"Ung Hoang Phuc",
				"Noo Phuoc Thinh",
				"Dan Truong ft. Cam Ly",
				"Tran Tuan Luong",
				"Van Mai Huong",
				"Ho Quang Hieu",
				"Minh Vuong M4U",
				"Wanbi Tuan Anh",
				"Minh Hang",
				"Pham Truong",
				"Lil' Knight ft. Emily ft. JustaTee",
				"Duong Trieu Vu ft. Bao Thy"};
		
		String[] links = new String[]{"music/Anh Khac Hay Em Khac - Khac Viet.mp3",
				"music/Anh Sao Buon - V.Music ft. Thuy Tien.mp3",
				"music/Anh Xin Loi - Minh Vuong M4U.mp3",
				"music/Chi Anh Hieu Em - Khac Viet.mp3",
				"music/Chi Can Em Hanh Phuc - Ho Quang Hieu.mp3",
				"music/Co Khi Nao Roi Xa - Bich Phuong.mp3",
				"music/Con Mua Ngang Qua - M-TP.mp3",
				"music/Co That Khong Anh - Vy Oanh.mp3",
				"music/Da Biet Se Co Ngay Hom Qua - Trinh Thang Binh.mp3",
				"music/Dau Hieu Tinh Yeu - Pham Truong.mp3",
				"music/Gio Lanh - Cao Thai Son.mp3",
				"music/Hinh Xam Trong Trai Tim Anh - Ung Hoang Phuc.mp3",
				"music/Lac Buoc Trong Dem - Noo Phuoc Thinh.mp3",
				"music/Lo Duyen Roi - Dan Truong ft. Cam Ly.mp3",
				"music/Mot Doi Canh - Tran Tuan Luong.mp3",
				"music/Neu Nhu Anh Den - Van Mai Huong.mp3",
				"music/Nga Tu Duong - Ho Quang Hieu.mp3",
				"music/Noi Dau Xot Xa - Minh Vuong M4U.mp3",
				"music/Phai Lam The Nao - Wanbi Tuan Anh.mp3",
				"music/Sac Moi Em Hong - Minh Hang.mp3",
				"music/Vo Tam Hay Vo Tinh - Pham Truong.mp3",
				"music/Xin Anh Dung - Lil' Knight ft. Emily ft. JustaTee.mp3",
				"music/Yeu Mai Yeu - Duong Trieu Vu ft. Bao Thy.mp3"};
		
		for (int i=0;i < links.length; i++){
			Artist artist = new Artist(artistName[i]);
			Album album = new Album(artistName[i], artist);
			Song song = new Song(titles[i], artist, links[i], album);
			songs.add(song);
		}
		//Save to database;
		SongDAO songDao = new SongDAOImpl();
		for (int i=0;i < links.length; i++){
			int id = songDao.storeSong(songs.get(i));
			songDao.upload(id, GenerateData.getBytesFromFile(new File(links[i])));
			System.out.println("---------Done---------" + links[i]);
		}
		songDao.closeConnection();
		System.out.println(songs.size());
		System.out.println("--------------------Done-----------------------");
	}

}
