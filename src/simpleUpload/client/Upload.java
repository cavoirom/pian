/*
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package simpleUpload.client;

import gwtupload.client.IUploader;
import gwtupload.client.SingleUploader;
import gwtupload.client.SingleUploaderModal;
import gwtupload.client.IUploader.UploadedInfo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Upload implements EntryPoint {

	  public void onModuleLoad() {
	    // Attach the image viewer to the document
	   // RootPanel.get("thumbnails").add(panelImages);
	    
	    SingleUploader single1 = new SingleUploaderModal();
	    single1.addOnFinishUploadHandler(onFinishUploaderHandler);
	    
	    // This enables php apc progress mechanism
	    single1.add(new Hidden("APC_UPLOAD_PROGRESS", single1.getInputName()), 0);
	    single1.avoidEmptyFiles(false);
	    RootPanel.get("single1").add(single1);

	    // Add a finish handler which will load the image once the upload finishes
	    single1.addOnFinishUploadHandler(onFinishUploaderHandler);
	  }

	  // Load the image in the document and in the case of success attach it to the viewer
	  private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
	    public void onFinish(IUploader uploader) {
	      if (uploader.getStatus() == gwtupload.client.IUploadStatus.Status.SUCCESS) {

	    	  RootPanel.get("finish").add(new Label("upload finish"));
	        
	        // The server sends useful information to the client by default
	        UploadedInfo info = uploader.getServerInfo();
	        System.out.println("File name " + info.name);
	        System.out.println("File content-type " + info.ctype);
	        System.out.println("File size " + info.size);

	        // You can send any customized message and parse it 
	        System.out.println("Server message " + info.message);
	      }
	    }
	  };
}
