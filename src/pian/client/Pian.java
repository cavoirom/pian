package pian.client;

import java.util.Collection;

import pian.model.Song;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Pian implements EntryPoint {
	private VerticalPanel pageContainer = new VerticalPanel();
	private DockPanel searchPanel = new DockPanel();
	private DockPanel resultPanel = new DockPanel();
	private HorizontalPanel footerPanel = new HorizontalPanel();
	
	private MultiWordSuggestOracle keywordOracle = new MultiWordSuggestOracle();
	private SuggestBox searchBox = new SuggestBox(keywordOracle);
	
	@Override
	public void onModuleLoad() {
		/* Begin - SEACH BOX */
		// logo in the left of search box.
		Image logoImage = new Image();
		logoImage.setUrl("images/logo.jpg");
		logoImage.setSize("115px", "110px");
		
		// test suggest keyword oracle
		//keywordOracle.add("Áo Lụa Hà Đông");
		//keywordOracle.add("Vài Lần Đón Đưa");
		
		// input keyword here
		searchBox.setWidth("300px");
		searchBox.setLimit(10);
		searchBox.addKeyDownHandler(new KeyDownHandler(){
			@Override
			public void onKeyDown(KeyDownEvent event) {
				event.stopPropagation();
				requestSuggestKeyword();
			}
		});
		
		// start search on click
		Button submitButton = new Button("Tìm nhạc");
		submitButton.getElement().getStyle().setPaddingLeft(10, Unit.PX);
		
		submitButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				searchPanel.removeStyleName("new");
				resultPanel.setVisible(true);
			}
			
		});
		
		// add searchBox and submitButton to its container panel.
		HorizontalPanel commandPanel = new HorizontalPanel();
		commandPanel.setBorderWidth(0);
		commandPanel.setWidth("400px");
		commandPanel.add(searchBox);
		commandPanel.add(submitButton);
			
		// option for search all field (Song's title, Artist, Album)
		CheckBox allOptionCheckBox = new CheckBox("Tất cả");
		// search Song title only
		CheckBox titleCheckBox = new CheckBox("Bài hát");
		// search Artist only
		CheckBox artistCheckBox = new CheckBox("Ca sĩ");
		// search Album only
		CheckBox albumCheckBox = new CheckBox("Album");
		// add options into its container panel, also commandPanel.
		DockPanel optionPanel = new DockPanel();
		optionPanel.add(commandPanel, DockPanel.NORTH);
		optionPanel.add(allOptionCheckBox, DockPanel.WEST);
		optionPanel.add(titleCheckBox, DockPanel.WEST);
		optionPanel.add(artistCheckBox, DockPanel.WEST);
		optionPanel.add(albumCheckBox, DockPanel.WEST);
		
		// Associate the logoImage, controlPanel, optionPanel with the searchPanel.
		searchPanel.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		searchPanel.add(logoImage, DockPanel.WEST);
		searchPanel.add(optionPanel, DockPanel.EAST);
		
		// margin to the middle of page
		searchPanel.addStyleName("new");
		/* End - SEACH BOX */
		
		/* Begin - SONG PANEL, This panel display the list of songs match the search keyword */
		// this panel display all result matched.
		VerticalPanel allResultPanel = new VerticalPanel();
		// this panel display song matched.
		VerticalPanel songResultPanel = new VerticalPanel();
		// this panel display artist matched.
		VerticalPanel artistResultPanel = new VerticalPanel();
		// this panel display album matched.
		VerticalPanel albumResultPanel = new VerticalPanel();
		
		// add above panels into container tab
		TabPanel songListPanel = new TabPanel();
		songListPanel.setWidth("700px");
		songListPanel.add(allResultPanel, "Tất cả");
		songListPanel.add(songResultPanel, "Tựa đề");
		songListPanel.add(artistResultPanel, "Ca sĩ");
		songListPanel.add(albumResultPanel, "Album");
		songListPanel.selectTab(0);
		/* End - SONG PANEL */
		
		/* Begin - PLAYER PANEL */
		VerticalPanel playerPanel = new VerticalPanel();
		playerPanel.setPixelSize(280, 30);
		playerPanel.add(new HTML("<div id=\"mainPlayer\">Loading the player ...</div>"));
		playerPanel.addAttachHandler(new Handler() {
			@Override
			public void onAttachOrDetach(AttachEvent event) {
				if (event.isAttached())
					Pian.renderPlayer();
				
			}
		});
		/* End - PLAYER PANEL */
		
		/* Begin - PLAYLIST PANEL */
		Label playlistLabel = new Label("Playlist của bạn");
		
		VerticalPanel playlistPanel = new VerticalPanel();
		playlistPanel.setWidth("280px");
		playlistPanel.add(playlistLabel);
		/* End - PLAYLIST PANEL */
		
		/* Begin - RESULT PANEL */
		// Associate the songPanel, playerPanel, playlistPanel with the resultPanel.
		resultPanel.setWidth("980px");
		resultPanel.setHeight("100%");
		resultPanel.setSpacing(5);
		resultPanel.add(songListPanel, DockPanel.WEST);
		resultPanel.add(playerPanel, DockPanel.NORTH);
		resultPanel.add(playlistPanel, DockPanel.NORTH);
		
		// hide resultPanel by default
		resultPanel.setVisible(false);
		/* End - RESULT PANEL */
		
		/* Begin - FOOTER PANEL */
		footerPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		footerPanel.add(new HTML("&copy;2011 by Hữu Phong, Đỗ Tâm, Xuân Vinh"));
		/* End - FOOTER PANEL */
		
		// Associate the searchPanel, resultPanel, footerPanel with the pageContainer.
		pageContainer.setBorderWidth(0);
		pageContainer.setWidth("100%");
		pageContainer.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		pageContainer.setSpacing(10);
		pageContainer.add(searchPanel);
		pageContainer.add(resultPanel);
		pageContainer.add(footerPanel);
		
		// Associate the pageContainer with the RootPanel to display it.
		RootPanel.get("pageContainer").add(pageContainer);
		
		// move cursor focus to the input box.
		searchBox.setFocus(true);
	}

	/* This code isn't work!
	private DockPanel getSongCell(Song song) {
		DockPanel cellPanel = new DockPanel();

		return cellPanel;
	}
	*/
	
	private static native void renderPlayer() /*-{
		$wnd.jwplayer("mainPlayer").setup({
			flashplayer: "/player/player.swf",
			//file: "http://stream3.mp3.zdn.vn/ES3stAYFQh/452b03c7ccc92e8bc7b50838207651e2/4eccceb0/2011/11/22/5/2/52e7b9a0ee8627037ca3a2a8235012fe.mp3",
			controlbar: "bottom",
			width: 270,
			height: 24,
		});
	}-*/;
	
	private static native void playSong(String url) /*-{
		$wnd.jwplayer("mainPlayer").load(url);
		$wnd.jwplayer("mainPlayer").play();
		//alert(url);
	}-*/;
	
	private void requestSuggestKeyword() {
		// Initialize the service proxy class
		SuggestKeywordServiceAsync suggestKeywordServ = GWT.create(SuggestKeywordService.class);
		
		AsyncCallback<Collection<String>> callback = new AsyncCallback<Collection<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Collection<String> result) {
				// TODO Auto-generated method stub
				updateSuggestKeywordOracle(result);
			}
		};
		
		// Make the call to the stock price service.
		suggestKeywordServ.getSuggestKeyword(searchBox.getValue(), callback);
	}
	
	private void updateSuggestKeywordOracle(Collection<String> suggestKeyword) {
		keywordOracle.clear();
		if(suggestKeyword != null) {
			keywordOracle.addAll(suggestKeyword);
		}
	}
}