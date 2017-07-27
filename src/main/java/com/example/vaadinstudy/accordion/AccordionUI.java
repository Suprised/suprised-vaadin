package com.example.vaadinstudy.accordion;

import javax.servlet.annotation.WebServlet;

import com.example.vaadinstudy.navigator.AccordionView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

/**
 * 
 * @author AmyChen
 *
 */
@SuppressWarnings("serial")
@Theme(Runo.THEME_NAME)
@Title("测试Accordion组件")
public class AccordionUI extends UI {

	private static final long serialVersionUID = 1L;
	
	@WebServlet(value = {"/*","/VAADIN/*"}, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = AccordionUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
//		this.createMainLayout();
		this.setContent(new AccordionView());
	}

	public void createMainLayout() {
		HorizontalSplitPanel mainLayout = new HorizontalSplitPanel() ;
		mainLayout.setSizeFull() ;
		mainLayout.setSplitPosition(200.0f, Unit.PIXELS);
	
		Accordion accordion = new Accordion() ;
		accordion.addTab(new Button("btn"), "用户管理");
		accordion.addTab(new Button("sales"), "权限管理");
		accordion.addTab(new Button("salary"), "理财管理");
		accordion.addTab(new Button("salary"), "考勤管理");
		
		Panel panel = new Panel("功能菜单");
		panel.setContent(accordion);
		
		mainLayout.setFirstComponent(panel);
		mainLayout.setSecondComponent(createSecondComponent());
		this.setContent(mainLayout);
	}
	
	private VerticalLayout createSecondComponent() {
		VerticalLayout secondLayout = new VerticalLayout() ;
		secondLayout.setSizeFull() ;
		TabSheet tabSheet = new TabSheet();
		tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
			
			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				Notification.show("当前Tab:" + event.getSource());
			}
		});
		tabSheet.setSizeFull();
		tabSheet.addTab(new Label("label1"),"第一个Tab").setClosable(true);
		tabSheet.addTab(new Label("label2"),"第二个Tab").setClosable(true);
		tabSheet.addTab(new Label("label3"),"第三个Tab").setClosable(true);
		tabSheet.addTab(new Label("label4"),"第四个Tab").setClosable(true);
		secondLayout.addComponent(tabSheet);
		return secondLayout ;
	}

}