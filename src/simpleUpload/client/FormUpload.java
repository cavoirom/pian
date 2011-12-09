package simpleUpload.client;



import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;



public class FormUpload extends Composite {


	public FormUpload() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		verticalPanel.setSize("450px", "338px");
		
		
		
		
		Label thongtinbaihat = new Label("Th\u00F4ng tin ba\u0300i ha\u0301t");
		verticalPanel.add(thongtinbaihat);
		
		Grid grid = new Grid(6, 2);
		verticalPanel.add(grid);
		grid.setSize("451px", "214px");
		
		Label tenbaihat = new Label("T\u00EAn ba\u0300i ha\u0301t");
		grid.setWidget(0, 0, tenbaihat);
		
		TextBox tenBaiHat = new TextBox();
		grid.setWidget(0, 1, tenBaiHat);
		
		Label lblCaSy = new Label("Ca sy\u0303");
		grid.setWidget(1, 0, lblCaSy);
		
		TextBox caSy = new TextBox();
		grid.setWidget(1, 1, caSy);
		
		Label lblNhacSy = new Label("Nha\u0323c sy\u0303");
		grid.setWidget(2, 0, lblNhacSy);
		
		TextBox nhacSy = new TextBox();
		grid.setWidget(2, 1, nhacSy);
		
		Label lblThLoai = new Label("Th\u00EA\u0309 loa\u0323i");
		grid.setWidget(3, 0, lblThLoai);
		
		ListBox theLoai = new ListBox();
		theLoai.addItem("Nhạc Việt Nam");
		theLoai.addItem("Nhạc trẻ ");
		theLoai.addItem("Nhạc chữ tình");
		theLoai.addItem("Nhạc rock");
		theLoai.addItem("Nhạc cách mạng");
		theLoai.addItem("Nhạc quê hương");
		theLoai.addItem("Nhạc thiếu nhi");
		theLoai.addItem("Nhạc không lời");
		theLoai.addItem("Nhạc trịnh");
		
		grid.setWidget(3, 1, theLoai);
		theLoai.setWidth("171px");
		
		Label lblTnBaiHat = new Label("Album");
		grid.setWidget(4, 0, lblTnBaiHat);
		
		TextBox album = new TextBox();
		grid.setWidget(4, 1, album);
		
		Button saveButton = new Button("Save");
		grid.setWidget(5,0,saveButton);

		Button exitButton = new Button("Exit");
		exitButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				RootPanel.get("single1").clear();
				RootPanel.get("pageLogin").clear();
				new Upload().onModuleLoad();
			}
		});
		grid.setWidget(5,1,exitButton);
		
	}

}
