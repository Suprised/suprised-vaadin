package com.suprised.vaadin.app.install;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.IntegerRangeValidator;
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
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * 数据库信息视图
 */
public class DBInfoView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	
	private TextField tfDbName, tfDbPort, tfDbIp, tfDbUserName, tfDbPassword;
	private TextField tfDbSysUserName, tfDbSysPassword;
	private FieldGroup fieldGroup;
	
	public DBInfoView(final Navigator navigator) {
		this.setSizeFull();
		PropertysetItem item = new PropertysetItem();
		item.addItemProperty("dbName", new ObjectProperty<String>(""));
		item.addItemProperty("dbPort", new ObjectProperty<Integer>(0));
		item.addItemProperty("dbIp", new ObjectProperty<String>(""));
		item.addItemProperty("dbUserName", new ObjectProperty<String>(""));
		item.addItemProperty("dbPassword", new ObjectProperty<String>(""));
		item.addItemProperty("dbSysUserName", new ObjectProperty<String>(""));
		item.addItemProperty("dbSysPassword", new ObjectProperty<String>(""));
		fieldGroup = new FieldGroup(item);
		buildView(navigator);
	}

	private void buildView(final Navigator navigator) {
		Panel panel = new Panel("数据库信息");
		panel.setSizeUndefined();
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.addComponent(new Label("<b>请选择数据库类型</b>", ContentMode.HTML));
		final VerticalLayout dbInfoLayout = new VerticalLayout();
		dbInfoLayout.setMargin(true);
		dbInfoLayout.setSizeUndefined();
		OptionGroup dbSelect = new OptionGroup();
		dbSelect.setImmediate(true);
		dbSelect.addValueChangeListener(new Property.ValueChangeListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				String value = event.getProperty().getValue().toString();
				Notification.show("选择的数据库：" + value);
				tfDbSysPassword.setVisible("oracle".equals(value));
				tfDbSysUserName.setVisible("oracle".equals(value));
				/*dbInfoLayout.removeAllComponents();
				dbInfoLayout.addComponent(buildSqlView(value));*/
			}
		});
		dbSelect.addItem("sqlserver");
		dbSelect.addItem("oracle");
		vLayout.addComponent(dbSelect);
		vLayout.addComponent(new Label("<b>请填写数据库信息</b>", ContentMode.HTML));
		
		dbInfoLayout.addComponent(buildSqlView("sqlserver"));
		dbSelect.select("sqlserver"); // 默认
		vLayout.addComponent(dbInfoLayout);
		vLayout.addComponent(new Button("测试链接", commitListener));
		
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setMargin(true);
		hLayout.addComponent(new Button("上一步", new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo("dir");
			}
		}));
		hLayout.addComponent(new Button("下一步", new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				validate();
				navigator.navigateTo("pathAndPort");
			}
		}));
		hLayout.addComponent(new Button("取消"));
		vLayout.addComponent(hLayout);
		vLayout.setComponentAlignment(hLayout, Alignment.MIDDLE_CENTER);
		
		panel.setContent(vLayout);
		this.addComponent(panel);
		this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
	}
	
	private FormLayout buildSqlView(String type) {
		FormLayout formLayout = new FormLayout();
		formLayout.setSizeFull();
		formLayout.setMargin(true);
		formLayout.addComponent(tfDbName = new TextField("数据库名称："));
		tfDbName.setRequired(true);
		tfDbName.setRequiredError("数据库名称不能为空");
		tfDbName.setImmediate(true);
		tfDbName.setValidationVisible(true);
		fieldGroup.bind(tfDbName, "dbName");
		formLayout.addComponent(tfDbIp = new TextField("IP："));
		tfDbIp.setRequired(true);
		tfDbIp.setRequiredError("ip不能为空");
		fieldGroup.bind(tfDbIp, "dbIp");
		tfDbIp.addValidator(new Validator() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void validate(Object value) throws InvalidValueException {
				Notification.show("验证IP是否正确");
			}
		});
		formLayout.addComponent(tfDbPort = new TextField("端口："));
		tfDbPort.setRequired(true);
		tfDbPort.setNullRepresentation("");
		tfDbPort.setRequiredError("端口不能为空");
		fieldGroup.bind(tfDbPort, "dbPort");
		tfDbPort.addValidator(new IntegerRangeValidator("请输入正确的端口号", 0, 100000));
		formLayout.addComponent(tfDbUserName = new TextField("用户名："));
		tfDbUserName.setRequired(true);
		tfDbUserName.setRequiredError("用户名不能为空");
		fieldGroup.bind(tfDbUserName, "dbUserName");
		
		formLayout.addComponent(tfDbPassword = new TextField("密码："));
		tfDbPassword.setRequired(true);
		tfDbPassword.setRequiredError("密码不能为空");
		fieldGroup.bind(tfDbPassword, "dbPassword");
		
		formLayout.addComponent(tfDbSysUserName = new TextField("管理员帐号："));
		tfDbSysUserName.setRequired(true);
		tfDbSysUserName.setRequiredError("管理员帐号不能为空");
		tfDbSysUserName.setVisible("oracle".equals(type));
		fieldGroup.bind(tfDbSysUserName, "dbSysUserName");
		
		
		formLayout.addComponent(tfDbSysPassword = new TextField("管理员密码："));
		tfDbSysPassword.setRequired(true);
		tfDbSysPassword.setRequiredError("管理员密码不能为空");
		tfDbSysPassword.setVisible("oracle".equals(type));
		fieldGroup.bind(tfDbSysPassword, "dbSysPassword");
		
		
		return formLayout;
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	private Button.ClickListener commitListener = new Button.ClickListener() {
		
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			validate();
		}
	};
	
	private void validate() {
		Notification.show("测试数据库链接");
		try {
			fieldGroup.commit();
		} catch (CommitException e) {
			Notification.show(e.getMessage(), Type.ERROR_MESSAGE);
			return;
		}
		Item item = fieldGroup.getItemDataSource();
		StringBuilder sb = new StringBuilder();
		sb.append("数据库名称：" + item.getItemProperty("dbName").getValue().toString() + "\r\n");
		sb.append("数据库端口：" + item.getItemProperty("dbPort").getValue().toString() + "\r\n");
		sb.append("数据库IP：" + item.getItemProperty("dbIp").getValue().toString() + "\r\n");
		sb.append("数据库用户名：" + item.getItemProperty("dbUserName").getValue().toString() + "\r\n");
		sb.append("数据库密码：" + item.getItemProperty("dbPassword").getValue().toString() + "\r\n");
		sb.append("数据库系统用户名(oracle)：" + item.getItemProperty("dbSysUserName").getValue().toString() + "\r\n");
		sb.append("数据库系统密码(oracle)：" + item.getItemProperty("dbSysPassword").getValue().toString() + "\r\n");
		Notification.show(sb.toString(), Type.WARNING_MESSAGE);
	}
	
}
