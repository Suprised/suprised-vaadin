package com.suprised.vaadin.app;

import javax.servlet.annotation.WebServlet;

import com.suprised.vaadin.app.install.DBInfoView;
import com.suprised.vaadin.app.install.DirView;
import com.suprised.vaadin.app.install.FinishedView;
import com.suprised.vaadin.app.install.PathAndPortView;
import com.suprised.vaadin.app.install.ProductAndSoftView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Runo;

@Theme(Runo.THEME_NAME)
@Title("一键安装程序")
public class MainUI extends UI {

	private static final long serialVersionUID = 1L;

	@WebServlet(value = {"/install/*","/VAADIN/*"}, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MainUI.class)
	public static class Servlet extends VaadinServlet {

		private static final long serialVersionUID = 1L;
	}
	
	private Navigator navigator ;
	
	@Override
	protected void init(VaadinRequest request) {
		navigator = new Navigator(this, this);
		navigator.addView("", new ProductAndSoftView(navigator));
		navigator.addView("dir", new DirView(navigator));
		navigator.addView("dbInfo", new DBInfoView(navigator));
		navigator.addView("pathAndPort", new PathAndPortView(navigator));
		navigator.addView("finished", new FinishedView(navigator));
	}
}
