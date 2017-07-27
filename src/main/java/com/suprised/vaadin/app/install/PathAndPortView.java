package com.suprised.vaadin.app.install;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * tomcat端口和虚拟路径设置视图
 */
public class PathAndPortView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	
	private TextField tfTomcatPort, tfTomcatPath;

	public PathAndPortView(final Navigator navigator) {
		this.setSizeFull();
		this.setMargin(true);
		this.buildView(navigator);
	}
	
	private void buildView(final Navigator navigator) {
		Panel panel = new Panel("端口和虚拟路径设置");
		panel.setSizeUndefined();
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setSizeFull();
		vLayout.setMargin(true);
		
		vLayout.addComponent(new Label("<b>请设置端口和虚拟路径</b>", ContentMode.HTML));
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(tfTomcatPort = new TextField("tomcat端口："));
		tfTomcatPort.setRequired(true);
		tfTomcatPort.setRequiredError("端口号不能为空");
		//tfTomcatPort.addValidator(new IntegerRangeValidator("请输入正确的端口号", 0, 100000));
		
		formLayout.addComponent(tfTomcatPath = new TextField("系统虚拟路径："));
		tfTomcatPath.setRequired(true);
		tfTomcatPath.setRequiredError("虚拟路径不能空");
		
		vLayout.addComponent(formLayout);
		vLayout.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);
		
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setMargin(true);
		hLayout.addComponent(new Button("检查端口号是否可用", new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Notification.show("检查端口号是否可用");
			}
		}));
		hLayout.addComponent(new Button("上一步", new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo("dbInfo");
			}
		}));
		hLayout.addComponent(new Button("下一步", new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// check port
				try {
					tfTomcatPath.validate();
					tfTomcatPort.validate();
				} catch(Exception e) {
					Notification.show(e.getMessage(), Type.ERROR_MESSAGE);
					return;
				}
				String path = tfTomcatPath.getValue();
				String port = tfTomcatPort.getValue();
				Notification.show(String.format("路径：%s\r\n端口：%s", path, port));
				navigator.navigateTo("finished");
			}
		}));
		hLayout.addComponent(new Button("取消"));
		vLayout.addComponent(hLayout);
		
		vLayout.addComponent(new Label("<font color='red'><b>注意：</b><br/>系统路径不是以'/'开头，则自动修改路径以'/'开头，例如：suite -> /suite</font>", ContentMode.HTML));
		
		panel.setContent(vLayout);
		this.addComponent(panel);
		this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
	}

}
