package pian.client;



import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;



public class Upload extends Composite {
	private FileUpload SelectFile;

	public Upload() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		verticalPanel.setSize("450px", "338px");
		
		SelectFile = new FileUpload();
		SelectFile.setName("Upload");
		SelectFile.setTitle("File Upload");
		
		verticalPanel.add(SelectFile);
		SelectFile.setHeight("26px");
		
		
		Label lblNewLabel_1 = new Label("Th\u00F4ng tin ba\u0300i ha\u0301t");
		verticalPanel.add(lblNewLabel_1);
		
		Grid grid = new Grid(5, 2);
		verticalPanel.add(grid);
		grid.setSize("451px", "214px");
		
		Label lblNewLabel = new Label("T\u00EAn ba\u0300i ha\u0301t");
		grid.setWidget(0, 0, lblNewLabel);
		
		TextBox textBox = new TextBox();
		grid.setWidget(0, 1, textBox);
		
		Label lblCaSy = new Label("Ca sy\u0303");
		grid.setWidget(1, 0, lblCaSy);
		
		TextBox textBox_1 = new TextBox();
		grid.setWidget(1, 1, textBox_1);
		
		Label lblNhacSy = new Label("Nha\u0323c sy\u0303");
		grid.setWidget(2, 0, lblNhacSy);
		
		TextBox textBox_2 = new TextBox();
		grid.setWidget(2, 1, textBox_2);
		
		Label lblThLoai = new Label("Th\u00EA\u0309 loa\u0323i");
		grid.setWidget(3, 0, lblThLoai);
		
		ListBox comboBox = new ListBox();
		comboBox.addItem("Nhạc Việt Nam");
		comboBox.addItem("Nhạc trẻ ");
		comboBox.addItem("Nhạc chữ tình");
		comboBox.addItem("Nhạc rock");
		comboBox.addItem("Nhạc cách mạng");
		comboBox.addItem("Nhạc quê hương");
		comboBox.addItem("Nhạc thiếu nhi");
		comboBox.addItem("Nhạc không lời");
		comboBox.addItem("Nhạc trịnh");
		
		grid.setWidget(3, 1, comboBox);
		comboBox.setWidth("171px");
		
		Label lblTnBaiHat = new Label("Album");
		grid.setWidget(4, 0, lblTnBaiHat);
		
		TextBox textBox_3 = new TextBox();
		grid.setWidget(4, 1, textBox_3);
		
		

		
	}

}
