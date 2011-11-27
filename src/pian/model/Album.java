package pian.model;

import java.util.List;

public class Album {
	private int id;
	private String name;
	private Artist artist;
	private List<Song> songs;
	
	public Album(){
		
	}
	
	public Album(String name, Artist artist) {
		this.name = name;
		this.artist = artist;
	}

	public Album(String name, Artist artist, List<Song> songs){
		this.name = name;
		this.artist = artist;
		this.songs = songs;
	}

	public Album(String name) {
		this.name = name;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
}
