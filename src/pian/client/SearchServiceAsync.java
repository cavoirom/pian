package pian.client;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SearchServiceAsync {

	public void getResult(String kw, AsyncCallback<Collection<String[]>> callback);
}
