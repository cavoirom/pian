package simpleUpload.server;

import java.io.*;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;

public class MyServlet extends UploadAction {
	private static final long serialVersionUID = 1L;

	Hashtable<String, String> receivedContentTypes = new Hashtable<String, String>();

	Hashtable<String, File> receivedFiles = new Hashtable<String, File>();

	public String executeAction(HttpServletRequest request,
			List<FileItem> sessionFiles) throws UploadActionException {
		String response = "";
		for (FileItem item : sessionFiles) {
			if (false == item.isFormField()) {
				try {
					InputStream in = item.getInputStream();
					BufferedInputStream inin = new BufferedInputStream(in);
					BufferedOutputStream out = new BufferedOutputStream(
							new FileOutputStream("anh.mp3"));
					int readed = -1;
					byte[] buff = new byte[10240];
					while ((readed = inin.read(buff)) != -1) {
						out.write(buff, 0, readed);
					}
					out.flush();
					inin.close();
					in.close();
					out.close();

				} catch (Exception e) {
					throw new UploadActionException(e);
				}
			}
		}
		removeSessionFileItems(request);
		return response;
	}

	@Override
	public void getUploadedFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// String fieldName = request.getParameter("PARAM_SHOW");
		// File f = receivedFiles.get(fieldName);
		// if (f != null) {
		// response.setContentType(receivedContentTypes.get(fieldName));
		// FileInputStream is = new FileInputStream(f);
		// copyFromInputStreamToOutputStream(is, response.getOutputStream());
		// } else {
		// renderXmlResponse(request, response, "ERROR_ITEM_NOT_FOUND");
		// }
	}

	@Override
	public void removeItem(HttpServletRequest request, String fieldName)
			throws UploadActionException {
		// File file = receivedFiles.get(fieldName);
		// receivedFiles.remove(fieldName);
		// receivedContentTypes.remove(fieldName);
		// if (file != null) {
		// file.delete();
		// }
	}

}
