package com.example.vaadinstudy.handlerequest;

import java.io.IOException;
import java.util.Date;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.RequestHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

/**
 * Vaadin 处理Request请求
 * 
 * @author AmyChen
 *
 */
@Theme(Runo.THEME_NAME)
@Title("Vaadin 处理Request请求")
public class HandleRequestUI extends UI {

	private static final long serialVersionUID = 1L;

	@WebServlet(value = {"/handler/*","/VAADIN/*"}, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = HandleRequestUI.class)
	public static class Servlet extends VaadinServlet {
		private static final long serialVersionUID = 745365611637302740L;
	}
	
	@Override
	protected void init(VaadinRequest request) {
		this.addRequestHandler();
		this.buildMainLayout();
	}
	
	private void buildMainLayout() {
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		
		String servletPath = VaadinServlet.getCurrent().getServletContext().getContextPath() 
					+ VaadinServletService.getCurrentServletRequest().getServletPath();
		Link link = new Link("点击显示页面", new ExternalResource(servletPath + "/page?theme=" + Runo.themeName()));
		
		mainLayout.addComponent(link);
		mainLayout.setComponentAlignment(link, Alignment.MIDDLE_CENTER);
		this.setContent(mainLayout);
	}
	
	private void addRequestHandler() {
		VaadinSession.getCurrent().addRequestHandler(new RequestHandler() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean handleRequest(VaadinSession session, VaadinRequest request,
					VaadinResponse response) throws IOException {
				String servletPath = request.getPathInfo();
				if ("/page".equals(servletPath)) {
					response.setContentType("text/plain;charset=utf-8");
					response.getWriter().append("这是动态生成的内容。\n当前时间：" + new Date() + ",当前Theme：" + request.getParameter("theme"));
					return true;
				}
				return false;
			}
		}) ;
	}

}
