package pian.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pian.client.SearchService;
import pian.model.Song;
import pian.model.dao.SongDAOImpl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SearchServiceImpl extends RemoteServiceServlet implements
SearchService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<String[]> getResult(String kw) {
		Collection<String[]> result = new ArrayList<String[]>();
		List<Song> songResult = new SongDAOImpl().findSongsByTitle(kw, 20, 1);
		for(int i=0; i<songResult.size(); i++) {
			Song s = songResult.get(i);
			System.out.println(s.getTitle());
			System.out.println(s.getArtist().getName());
			System.out.println(s.getAlbum().getName());
			String[] r = new String[] {
					s.getTitle(),
					s.getArtist().getName(),
					s.getAlbum().getName(),
			};
			result.add(r);
		}
		System.out.println(result.size());
		return result;
	}

}
