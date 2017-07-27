package com.example.vaadinstudy.navigator;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Runo;

/**
 * test Navigator
 * 
 * @author AmyChen
 *
 */
@SuppressWarnings("serial")
@Theme(Runo.THEME_NAME)
@Title("测试Navigator组件")
//@PreserveOnRefresh
public class NavigatorUI extends UI {
	
	private static final long serialVersionUID = 1L;
	
	@WebServlet(value = {"/nav/*","/VAADIN/*"}, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = NavigatorUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	private Navigator navigator ;
	
	@Override
	protected void init(VaadinRequest request) {
		navigator = new Navigator(this, this);
		navigator.addView("", new NoneNameView(navigator));
		navigator.addView("main", new MainView(navigator));
		navigator.addView("start", new StartView(navigator));
		navigator.addView("accordionUI", AccordionView.class);
	}

}
