package pian.client;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("searchSong")
public interface SearchService extends RemoteService {
	public Collection<String[]> getResult(String kw);
}
