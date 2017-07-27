package com.example.vaadinstudy.navigator;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 主View
 * 
 * @author AmyChen
 */
public class MainView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1855395916068837781L;

	public MainView(final Navigator navigator) {
		this.setSizeFull();
		Button btnNext ;
		this.addComponent(new TextField("内容："));
		this.addComponent(btnNext = new Button("进入下一个View>>",new Button.ClickListener() {
			
			private static final long serialVersionUID = 9007389896469172609L;

			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo("start");
			}
		}));
		Button btnPre;
		this.addComponent(btnPre = new Button("返回上一个View<<", new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Notification.show("NoneName");
				navigator.navigateTo("");
			}
		}));
		// 居中
		this.setComponentAlignment(btnNext, Alignment.MIDDLE_CENTER);
		this.setComponentAlignment(btnPre, Alignment.MIDDLE_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println("ViewName : " + event.getViewName());
	}

}
