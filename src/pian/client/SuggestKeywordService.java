package pian.client;

import java.util.Collection;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("suggestKeyword")
public interface SuggestKeywordService extends RemoteService {
	public Collection<String> getSuggestKeyword(String kw);
}
