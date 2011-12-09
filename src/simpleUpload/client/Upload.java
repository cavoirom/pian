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

import java.awt.Color;

import javax.jws.soap.SOAPBinding.Style;

import gwtupload.client.IUploader;
import gwtupload.client.SingleUploader;
import gwtupload.client.SingleUploaderModal;
import gwtupload.client.IUploader.UploadedInfo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Upload implements EntryPoint {
		

	  public void onModuleLoad() {
	   Login login = new Login(this);
	   login.getElement().getStyle().setPosition(com.google.gwt.dom.client.Style.Position.ABSOLUTE);
	   login.setPopupPosition(Window.getClientWidth()/4,Window.getClientHeight()/4);
	   RootPanel.get("pageLogin").add(login);
	   
	    
		 
	  }

	  // Load the image in the document and in the case of success attach it to the viewer
	  private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
	    public void onFinish(IUploader uploader) {
	      if (uploader.getStatus() == gwtupload.client.IUploadStatus.Status.SUCCESS) {
  		        
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
	  public void upload(){
		  Label label = new Label("Begin upload music");
		  
		  SingleUploader single1 = new SingleUploaderModal();
		    single1.addOnFinishUploadHandler(onFinishUploaderHandler);
		   
		    // This enables php apc progress mechanism
		    single1.add(new Hidden("APC_UPLOAD_PROGRESS", single1.getInputName()), 0);
		    single1.avoidEmptyFiles(false);
		    FormUpload uploadForm =new FormUpload();
		   
		    uploadPanel.setBorderWidth(0);
		    uploadPanel.setWidth("100%");
		    uploadPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		    uploadPanel.setSpacing(10);
		    
		    uploadPanel.add(label);
		    uploadPanel.add(single1);
		    uploadPanel.add(uploadForm);
//		    RootPanel.get("single1").clear();
		    RootPanel.get("single1").add(uploadPanel);
		    

		    // Add a finish handler which will load the image once the upload finishes
		    single1.addOnFinishUploadHandler(onFinishUploaderHandler);
	  }
	  private VerticalPanel uploadPanel = new VerticalPanel();
	  
}
