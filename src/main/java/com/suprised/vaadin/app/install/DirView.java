package com.suprised.vaadin.app.install;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 安装目录视图
 */
public class DirView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	public DirView(final Navigator navigator) {
		setSizeFull();
		buildView(navigator);
	}
	
	private void buildView(final Navigator navigator) {
		Panel panel = new Panel("安装目录选择");
		panel.setSizeUndefined();
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setSizeFull();
		vLayout.setMargin(true);
		vLayout.addComponent(new Label("<b>指定产品安装位置</b>", ContentMode.HTML));
		vLayout.addComponent(new Label("请指定一个目录用于安装得实产品以及环境软件"));
		Upload uploadProduct = new Upload();
		uploadProduct.setCaption("请选择安装目录：");
		vLayout.addComponent(uploadProduct);
		vLayout.addComponent(new Label("请指定一个目录用于存放得实产品资源"));
		Upload uploadRes = new Upload();
		uploadProduct.setCaption("请选择资源存放目录：");
		vLayout.addComponent(uploadRes);
		
		vLayout.addComponent(new Label("注意：安装目录只能包含字母和数字"));
		
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setMargin(true);
		hLayout.addComponent(new Button("上一步", new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo("");
			}
		}));
		hLayout.addComponent(new Button("下一步", new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo("dbInfo");
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
