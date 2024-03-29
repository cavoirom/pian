package pian.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import pian.client.SuggestKeywordService;
import pian.model.dao.ConnectionFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SuggestKeywordServiceImpl extends RemoteServiceServlet implements
		SuggestKeywordService {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<String> getSuggestKeyword() {
		Collection<String> suggestKeyword = new ArrayList<String>();
		
		try {
			Connection connection = ConnectionFactory.getConnection();
			connection.setReadOnly(true);
			
			String query = "SELECT Title FROM Song;";
			PreparedStatement stm = connection.prepareStatement(query);
			
			ResultSet result = stm.executeQuery();
			
			while(result.next()) {
				suggestKeyword.add(result.getString("Title"));
			}
			
			stm.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<String> iter = suggestKeyword.iterator();
		
		while(iter.hasNext())
			System.out.println(iter.next());
		System.out.println(System.currentTimeMillis() + " Yeah! Suggest Service ran.");
		
		return suggestKeyword;
	}

}
