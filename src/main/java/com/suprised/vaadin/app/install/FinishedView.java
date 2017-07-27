package com.suprised.vaadin.app.install;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.vaadin.data.util.ObjectProperty;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * 最后进度条显示视图
 */
public class FinishedView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	private TextArea taProgress = new TextArea();
	private StringBuffer sbInfo = new StringBuffer();
	public FinishedView(final Navigator navigator) {
		this.setSizeFull();
		this.setMargin(true);
		this.buildView(navigator);
	}
	
	private void buildView(final Navigator navigator) {
		Panel panel = new Panel("安装进度显示");
		panel.setWidth("500px");
		panel.setHeight("500px");
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setSizeFull();
		vLayout.setMargin(true);
		vLayout.addComponent(new Label("<b>安装进度显示：</b>", ContentMode.HTML));
		
		taProgress.setSizeFull();
		taProgress.setRows(10);
		taProgress.setImmediate(true);
		taProgress.setBuffered(false);
		taProgress.setValue("aaa");
		vLayout.addComponent(taProgress);
		vLayout.setExpandRatio(taProgress, 1);
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setMargin(true);
		hLayout.addComponent(new Button("上一步", new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo("pathAndPort");
			}
		}));
		hLayout.addComponent(new Button("开始安装", new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				changeState();
				UI.getCurrent().setPollInterval(1000);// 一秒秒刷新一次
				event.getButton().setEnabled(false);
			}
		}));
		hLayout.addComponent(new Button("取消"));
		vLayout.addComponent(hLayout);
		
		panel.setContent(vLayout);
		
		this.addComponent(panel);
		this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
	}
	
	private void changeState() {
		// 必须是线程安全的
		final ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor();
		UI.getCurrent().access(new Runnable() {
			
			@Override
			public void run() {
				service.scheduleWithFixedDelay(new Runnable() {

					@Override
					public void run() {
						sbInfo.append("步骤111111\r\n");
						taProgress.setPropertyDataSource(new ObjectProperty<String>(sbInfo.toString()));
						// taProgress.setValue(sbInfo.toString());
					}
				}, 10l, 5000l, TimeUnit.MILLISECONDS);
			}
		});
		
	}

}
