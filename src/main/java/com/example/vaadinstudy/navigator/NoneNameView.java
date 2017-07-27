package com.example.vaadinstudy.navigator;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 没有Name
 * 
 * @author AmyChen
 *
 */
public class NoneNameView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	public NoneNameView(final Navigator navigator) {
		this.setSizeFull();
		Button btnNext ;
		this.addComponent(new TextField("fsfsd"));
		this.addComponent(btnNext = new Button("进入Main>>",new Button.ClickListener() {
			
			private static final long serialVersionUID = 9007389896469172609L;

			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo("main");
			}
		}));
		// 居中
		this.setComponentAlignment(btnNext, Alignment.MIDDLE_CENTER);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
	}

}
