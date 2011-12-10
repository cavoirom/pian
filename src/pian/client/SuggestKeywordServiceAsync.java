package pian.client;

import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SuggestKeywordServiceAsync {
	
	public void getSuggestKeyword(AsyncCallback<Collection<String>> callback);
}
