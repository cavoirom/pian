package pian.model;

import java.util.List;

public class Artist {
	private int id;
	private String name;
	private List<Song> songs;
	
	public Artist(String name) {
		this.name = name;
	}

	public Artist(String name, List<Song> songs) {
		this.name = name;
		this.songs = songs;
	}

	public Artist() {
		
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
