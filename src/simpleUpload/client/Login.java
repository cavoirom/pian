package simpleUpload.client;

import pian.client.Pian;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;

import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.FlexTable;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class Login extends DialogBox {
	
	
	public Login(final Upload upload) {
		
		
		FlexTable flexTable = new FlexTable();
		setWidget(flexTable);
		flexTable.setSize("376px", "237px");
		
		Label lblDda = new Label("\u0110\u0103ng Nh\u00E2\u0323p");
		lblDda.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblDda.setStyleName("h1");
		flexTable.setWidget(0, 0, lblDda);
		
		Label lblUserName = new Label("Ta\u0300i khoa\u0309n");
		flexTable.setWidget(1, 0, lblUserName);
		lblUserName.setSize("115px", "16px");
		
		final TextBox textBox = new TextBox();
		flexTable.setWidget(1, 1, textBox);
		textBox.setWidth("188px");
		
		Label lblPassword = new Label("M\u00E2\u0323t kh\u00E2\u0309u");
		flexTable.setWidget(2, 0, lblPassword);
		lblPassword.setSize("125px", "26px");
		
		final PasswordTextBox passwordTextBox = new PasswordTextBox();
		flexTable.setWidget(2, 1, passwordTextBox);
		passwordTextBox.setWidth("188px");
		
		Button dangnhapButton = new Button("Đăng nhập");
		dangnhapButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(textBox.getText().trim().equalsIgnoreCase("admin") && 
						passwordTextBox.getText().trim().equalsIgnoreCase("admin")){
					RootPanel.get("pageLogin").clear();
				upload.upload();	
				
					
				}
				
			}
		});
//		btnBack.setText("\u0110\u0103ng Nh\u00E2\u0323p");
		flexTable.setWidget(3, 0, dangnhapButton);
		dangnhapButton.setSize("116px", "44px");

	}

}
