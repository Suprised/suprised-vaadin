package com.suprised.vaadin.app.install;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 产品展示以及安装软件展示
 */
public class ProductAndSoftView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	
	public ProductAndSoftView(Navigator navigator) {
		this.setSizeFull();
		buildView(navigator);
	}
	
	private void buildView(final Navigator navigator) {
		Panel panel = new Panel("产品展示与软件安装");
		/*panel.setWidth(300, Unit.PIXELS);
		panel.setHeight(300, Unit.PIXELS);*/
		panel.setSizeUndefined();
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setMargin(true);
		vLayout.setSizeFull();
		vLayout.addComponent(new Label("<b>根据您的许可证，本次将为您按照以下产品:</b>", ContentMode.HTML));
		vLayout.addComponent(new Label("网络教学平台"));
		vLayout.addComponent(new Label("课程管理平台"));
		vLayout.addComponent(new Label("资源管理平台"));
		
		vLayout.addComponent(new Label("<b>同时将为您按照以下服务（勾选需要的服务即可）</b>", ContentMode.HTML));
		for (int i=0; i<5; i++) {
			vLayout.addComponent(new CheckBox("这里演示了点击跳过广告按钮后的执行的动作，如果注册会员可以做成直接跳过的效果。"));
		}
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setMargin(true);
		hLayout.addComponent(new Button("下一步", new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo("dir");
			}
		}));
		hLayout.addComponent(new Button("取消"));
		vLayout.addComponent(hLayout);
		vLayout.setComponentAlignment(hLayout, Alignment.MIDDLE_CENTER);
		panel.setContent(vLayout);
		this.addComponent(panel);
		this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
