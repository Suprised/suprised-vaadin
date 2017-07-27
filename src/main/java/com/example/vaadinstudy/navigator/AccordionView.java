package com.example.vaadinstudy.navigator;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;

/**
 * 折叠View
 * 
 * @author AmyChen
 *
 */
public class AccordionView extends VerticalLayout implements View {

	private static final long serialVersionUID = 2849825800852308613L;
	
	public AccordionView() {
		this.setSizeFull();
		this.createMainLayout();
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	public void createMainLayout() {
		HorizontalSplitPanel mainLayout = new HorizontalSplitPanel() ;
		mainLayout.setSizeFull() ;
		mainLayout.setSplitPosition(200.0f, Unit.PIXELS);
	
		Accordion accordion = new Accordion() ;
		accordion.addTab(new Button("用户管理",navClickListener), "用户管理");
		accordion.addTab(new Button("权限管理",navClickListener), "权限管理");
		accordion.addTab(new Button("理财管理",navClickListener), "理财管理");
		accordion.addTab(new Button("考勤管理",navClickListener), "考勤管理");
		
		Panel panel = new Panel("功能菜单");
		panel.setSizeFull();
		panel.setContent(accordion);
		
		mainLayout.setFirstComponent(panel);
		mainLayout.setSecondComponent(createSecondComponent());
		this.addComponent(mainLayout);
	}
	
	private VerticalLayout createSecondComponent() {
		VerticalLayout secondLayout = new VerticalLayout() ;
		secondLayout.setSizeFull() ;
		tabSheet.setImmediate(true);
		tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
			
			private static final long serialVersionUID = -4630401554191984979L;

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
	
	private final TabSheet tabSheet = new TabSheet();
	
	private Button.ClickListener navClickListener = new Button.ClickListener() {
		private static final long serialVersionUID = 5667171670578320074L;

		@Override
		public void buttonClick(ClickEvent event) {
			int index = tabSheet.getComponentCount();
			for (int i=0; i<index; i++) {
				if (tabSheet.getTab(i).getCaption().equals(event.getButton().getCaption())) {
					return;
				}
			}
			Tab curr_tab = tabSheet.addTab(
					new Label(event.getButton().getCaption()), event.getButton().getCaption());
			curr_tab.setClosable(true);
			tabSheet.setSelectedTab(curr_tab);
		}
	};
}
