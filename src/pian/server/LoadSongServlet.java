package pian.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.sql.Connection;

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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		int songId = 0;
		if (request.getParameter("id") != null) {
			songId = Integer.parseInt(request.getParameter("id"));
			System.out.println(songId);
			try {
				Connection connection = ConnectionFactory.getConnection();
				connection.setReadOnly(true);

				BufferedInputStream bis = new BufferedInputStream(
						new SongDAOImpl(connection).download(songId));
				BufferedOutputStream bos = new BufferedOutputStream(
						response.getOutputStream());

				int readed = -1;
				byte[] buff = new byte[10240];
				while ((readed = bis.read(buff)) != -1) {
					bos.write(buff, 0, readed);
				}
				bos.flush();
				bos.close();
				bis.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		// Nothing to do
	}
}
