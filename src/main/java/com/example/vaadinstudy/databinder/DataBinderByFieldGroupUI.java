package com.example.vaadinstudy.databinder;

import java.util.Date;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Runo;

/**
 * 数据绑定by FiledGroup/BeanFiledGroup
 * 
 * @author AmyChen
 *
 */
@SuppressWarnings("serial")
@Theme(Runo.THEME_NAME)
@Title("数据绑定by FiledGroup")
public class DataBinderByFieldGroupUI extends UI {

	private static final long serialVersionUID = 1734220846896769519L;

	@WebServlet(value = {"/field/*","/VAADIN/*"}, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = DataBinderByFieldGroupUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	@Override
	protected void init(VaadinRequest request) {
		this.createMainLayout();
	}

	private void createMainLayout() {
		mainPanel = new HorizontalSplitPanel();
		mainPanel.setSizeFull();
		mainPanel.setSplitPosition(50, Unit.PERCENTAGE);
		mainPanel.setFirstComponent(initTable());
		mainPanel.setSecondComponent(initFormLayout());
		this.setContent(mainPanel);
	}
	
	public Table initTable() {
		tbUsers = new Table(null, getPersonContainer());
		tbUsers.setSizeFull();
		tbUsers.setImmediate(true); //及时反应
		tbUsers.setMultiSelect(false);
		tbUsers.setSelectable(true);
		tbUsers.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
				Object itemId = event.getItemId();
				if (itemId != null) {
					fieldGroup.setItemDataSource(tbUsers.getItem(itemId));
				} 
				formLayout.setVisible(itemId != null);
			}
		});
		tbUsers.setVisibleColumns(visibleColumns); // 要在headers前面，否则表头排序会乱
		tbUsers.setColumnHeaders(headers);
		return tbUsers;
	}
	
	public FormLayout initFormLayout() {
		formLayout = new FormLayout();
		fieldGroup = new BeanFieldGroup<PersonEntry>(PersonEntry.class);
		fieldGroup.setBuffered(false);
		for (int i=0; i<visibleColumns.length; i++) {
			TextField field = new TextField(headers[i]);
			field.setNullRepresentation("");
			formLayout.addComponent(field);
			field.setWidth("100%");
			fieldGroup.bind(field, visibleColumns[i]);
		}
		formLayout.addComponentAsFirst(new Button("增加", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Item item = ((BeanItemContainer<PersonEntry>)tbUsers.getContainerDataSource()).addItemAt(0,new PersonEntry(i++,""));
				tbUsers.addItem(visibleColumns, item);
				//fieldGroup.setItemDataSource(item);
				tbUsers.select(item);
			}
		}));
		formLayout.addComponent(new Button("删除", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				tbUsers.getContainerDataSource().removeItem(tbUsers.getValue());
			}
		}), 1);
		formLayout.addComponent(new Button("提交", new Button.ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
//				try {
					//fieldGroup.commit();
					tbUsers.addItem(fieldGroup.getItemDataSource().getBean());
					System.out.println(tbUsers.getContainerDataSource().size());
					for (Object obj : tbUsers.getContainerDataSource().getItemIds()) {
						System.out.println(obj);
					}
					tbUsers.refreshRowCache();
//				} catch (CommitException e) {
//					e.printStackTrace();
//				}
			}
		}), 2);
		formLayout.setVisible(false);
		return formLayout;
	}
	
	private static final String[] headers = {"编号", "用户名","密码","年龄","生日","薪水"};
	private static final Object[] visibleColumns = {"id","name","password","age","birthDay","salary"};
	private Table tbUsers ;
	private BeanItemContainer<PersonEntry> container;
	private HorizontalSplitPanel mainPanel;
	private FormLayout formLayout;
//	private FieldGroup fieldGroup;
	private BeanFieldGroup<PersonEntry> fieldGroup;
	private int i;
	
	
	public BeanItemContainer<PersonEntry> getPersonContainer() {
		container = new BeanItemContainer<PersonEntry>(PersonEntry.class);
		for (i=0; i<=100; i++) {
			PersonEntry bean = new PersonEntry(i,"刘金喜" + i);
			bean.setBirthDay(new Date());
			container.addBean(bean);
		}
		return container;
	}
}
