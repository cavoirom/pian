package pian.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pian.model.dao.ConnectionFactory;
import pian.model.dao.SongDAOImpl;

public class LoadSongServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoadSongServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		int songId = 0;
		if (request.getAttribute("id") != null) {
			songId = Integer.parseInt(request.getParameter("id"));
			
			try {
				Connection connection = ConnectionFactory.getConnection();
				
				BufferedInputStream bis = new BufferedInputStream(
						new ByteArrayInputStream(new SongDAOImpl(connection).play(songId)));
				BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
				
				int read;
				byte[] array = new byte[1024];
				while ((read = bis.read(array)) != -1) {
					bos.write(array, 0, read);
				}
				
				bos.close();
				bis.close();
				connection.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		// Nothing to do
	}
}
