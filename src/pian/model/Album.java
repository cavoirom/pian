package pian.model;

import java.util.List;

public class Album {
	private int id;
	private String name;
	private Artist artist;
	private List<Song> songs;
	
	public Album(String name, Artist artist, List<Song> songs){
		this.id = 0;
		this.name = name;
		this.artist = artist;
		this.songs = songs;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
}
