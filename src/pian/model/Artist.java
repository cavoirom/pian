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
}
