package com.suprised.vaadin.app.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.suprised.vaadin.app.manager.UserManagerImpl;
import com.suprised.vaadin.app.utils.SpringContextHolder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class UserTest {
	
	@Autowired
	private UserManagerImpl userManager;

	@Test
	public void printString() throws Exception {
		DataSource datasource = SpringContextHolder.getBean("dataSource");
		Connection connection = datasource.getConnection();
		/*PreparedStatement ps = connection.prepareStatement("select * from TUser");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println("jdoidx : " + rs.getLong(0));
		}*/
		System.out.println(connection);
		connection.close();
		userManager.printString();
	}
}
