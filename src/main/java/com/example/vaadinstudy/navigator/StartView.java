package com.example.vaadinstudy.navigator;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * 开始View
 * 
 * @author AmyChen
 *
 */
public class StartView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	
	private Navigator navigator ;
	private Panel panelPic ;
	
	public StartView(final Navigator navigator) {
		this.navigator = navigator ;
		this.setSizeFull();
		this.setMargin(true);
		this.createMainLayout();
	}
	
	private void createMainLayout() {
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSizeFull();
		hLayout.setMargin(true);
		
		VerticalLayout menuButtons = new VerticalLayout();
		menuButtons.setMargin(true);
		menuButtons.addComponent(new Button("liujinxi",listener));
		menuButtons.addComponent(new Button("chenhuan",listener));
		menuButtons.addComponent(new Button("vaadin",listener));
		menuButtons.addComponent(new Button("kobi",listener));
		menuButtons.addComponent(new Button("answer",listener));
		menuButtons.addComponent(new Button("tmac",listener));
		menuButtons.addComponent(new Button("Logout", new Button.ClickListener() {
			
			private static final long serialVersionUID = -8123299505605129629L;

			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo("accordionUI");//回到首页
			}
		}));
		
		menuButtons.addComponent(new Button("返回上一个View<<", new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Notification.show("main");
				navigator.navigateTo("main");
			}
		}));
		hLayout.addComponent(menuButtons);
		hLayout.addComponent(panelPic = new Panel("图片显示"));
		
		this.addComponent(hLayout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		String parameter = event.getParameters();
		if (parameter == null || parameter.isEmpty()) {
			return ;
		}
		VerticalLayout panelContent = new VerticalLayout() ;
		panelContent.setSizeFull();
		panelContent.setMargin(true);
		panelPic.setContent(panelContent);
		
		panelContent.addComponent(new Label("当前的图片名称是：" + parameter));
		// 加载图片控件 
		Embedded embedded = new Embedded(null, new ThemeResource("img/" + parameter + ".jpg"));
		panelContent.addComponent(embedded);
		panelContent.setExpandRatio(embedded, 1.0f);
		panelContent.setComponentAlignment(embedded, Alignment.BOTTOM_CENTER);
	}

	private Button.ClickListener listener = new Button.ClickListener() {
		
		private static final long serialVersionUID = 1L;
		
		@Override
		public void buttonClick(ClickEvent event) {
			navigator.navigateTo("start/" + event.getButton().getCaption());
		}
	};
}
