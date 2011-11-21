package pian.client;

import java.util.Iterator;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Pian implements EntryPoint {
	private VerticalPanel pageContainer = new VerticalPanel();
	
	private SuggestBox searchBox = new SuggestBox();
	private Button searchButton = new Button("Tìm nhạc");
	private CheckBox allOptionCheckBox = new CheckBox("Tất cả");
	private CheckBox titleCheckBox = new CheckBox("Bài hát");
	private CheckBox artistCheckBox = new CheckBox("Ca sĩ");
	private CheckBox albumCheckBox = new CheckBox("Album/Playlist");
	private Image backgroundImage = new Image();
	private Image logoImage = new Image();
	private AbsolutePanel searchPanel = new AbsolutePanel();
	private HorizontalPanel controlPanel = new HorizontalPanel();
	private HorizontalPanel optionPanel = new HorizontalPanel();
	
	private DockPanel resultPanel = new DockPanel();
	private TabLayoutPanel songPanel = new TabLayoutPanel(30, Unit.PX);
	private VerticalPanel allResultPanel = new VerticalPanel();
	private VerticalPanel songResultPanel = new VerticalPanel();
	private VerticalPanel artistResultPanel = new VerticalPanel();
	private VerticalPanel albumResultPanel = new VerticalPanel();
	private VerticalPanel playerPanel = new VerticalPanel();
	private VerticalPanel playlistPanel = new VerticalPanel();
	private Label playlistLabel = new Label("Playlist của bạn");
	
	
	@Override
	public void onModuleLoad() {
		/* render full search box. */
		logoImage.setUrl("images/logo.jpg");
		
		searchBox.setWidth("300px");
		
		// add searchBox and searchButton to its panel.
		controlPanel.setBorderWidth(0);
		controlPanel.add(searchBox);
		controlPanel.add(searchButton);
		
		// add options to its panel.
		optionPanel.setBorderWidth(0);
		optionPanel.add(allOptionCheckBox);
		optionPanel.add(titleCheckBox);
		optionPanel.add(artistCheckBox);
		optionPanel.add(albumCheckBox);
		
		// Associate the logoImage, controlPanel, optionPanel with the searchPanel.
		searchPanel.add(logoImage);
		searchPanel.add(controlPanel);
		searchPanel.add(optionPanel);
		/* render full search box. */
		
		/* Test result panel */
		playerPanel.add(new HTML("This is Player Panel"));
		//
		allResultPanel.add(renderCellPanel("id001", "Làm Ơn", "Đang cập nhật", "ab001", "Trần Trung Đức"));
		allResultPanel.add(renderCellPanel("id001", "Vài Lần Đón Đưa", "Đang cập nhật", "ab001", "Lê Hiếu"));
		/* Test result panel */
		
		/* render result box. */
		songPanel.setWidth("700px");
		songPanel.setHeight("100%");
		songPanel.add(allResultPanel, "Tất cả");
		songPanel.add(songResultPanel, "Tựa đề");
		songPanel.add(artistResultPanel, "Ca sĩ");
		songPanel.add(albumResultPanel, "Album/Playlist");
		songPanel.selectTab(0);
		
		playerPanel.setPixelSize(280, 80);
		
		playlistPanel.setWidth("280px");
		playlistPanel.add(playlistLabel);
		
		// Associate the songPanel, playerPanel, playlistPanel with the searchPanel.
		resultPanel.setWidth("980px");
		resultPanel.setHeight("100%");
		resultPanel.setSpacing(5);
		resultPanel.add(songPanel, DockPanel.WEST);
		resultPanel.add(playerPanel, DockPanel.NORTH);
		resultPanel.add(playlistPanel, DockPanel.NORTH);
		/* render result box. */
		
		// Associate the searchPanel, resultPanel with the pageContainer.
		pageContainer.setBorderWidth(0);
		pageContainer.setWidth("100%");
		pageContainer.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		pageContainer.setSpacing(10);
		pageContainer.add(searchPanel);
		pageContainer.add(resultPanel);
		
		// Associate the pageContainer with the RootPanel to display it.
		RootPanel.get().add(pageContainer);
		
		// move cursor focus to the input box.
		searchBox.setFocus(true);
	}

	private DockPanel renderCellPanel(String id, String songTitle, String artist, String albumId, String album) {
		Label titleAndArtistLabel = new Label(songTitle + " - " + artist);
		Label albumNameLabel = new Label(album);
		
		VerticalPanel songInfoPanel = new VerticalPanel();
		songInfoPanel.setWidth("540px");
		songInfoPanel.add(titleAndArtistLabel);
		songInfoPanel.add(albumNameLabel);
		
		Label addToPlaylistLabel = new Label("Thêm vào Playlist");
		Label downloadLabel = new Label("Tải về");
		
		VerticalPanel songOperationPanel = new VerticalPanel();
		songOperationPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		songOperationPanel.setWidth("150px");
		songOperationPanel.add(addToPlaylistLabel);
		songOperationPanel.add(downloadLabel);
		
		DockPanel cellPanel = new DockPanel();
		cellPanel.setWidth("100%");
		cellPanel.add(songInfoPanel, DockPanel.WEST);
		cellPanel.add(songOperationPanel, DockPanel.EAST);
		
		return cellPanel;
	}
	
}