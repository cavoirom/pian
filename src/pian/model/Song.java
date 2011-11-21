package pian.model;

public class Song {
	private int id;
	private String title;
	private Artist artist;
	private String link;
	private Album album;
	
	public Song(){
		
	}
	
	public Song(String title, String link) {
		this.id = 0;
		this.title = title;
		this.artist = null;
		this.link = link;
		this.album = null;
	}
	
	public Song(String title, Artist artist, String genre, String link,
			Album album) {
		this.id = 0;
		this.title = title;
		this.artist = artist;
		this.link = link;
		this.album = album;
	}
}
