import static org.junit.Assert.*;
import static org.junit.Assert.*;

import org.junit.Test;

import customTools.DBUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;


import util.MD5Util;
import model.BHUser;

import org.junit.Test;

public class AddUserTest {

	@Test
	public void test() {
		
		 String userName = "MarkCP";
		 String userEmail = "MarkCP@gmail.com";
		 String userPassword = "password";
		 String userMotto = "Go Hokies";
		 System.out.println(userName + userEmail + userPassword + userMotto);
		 //populate the existing user then change some fields
		 BHUser addUser = new BHUser();
		 System.out.println("BHUser addUser created");
		 addUser.setUsername(userName);
		 System.out.println(userName + " added");
		 addUser.setMotto(userMotto);
		 		 addUser.setUseremail(userEmail);
		 addUser.setUserpassword(userPassword);
		 System.out.println("All parameters added");
		 //alternatively could modify DbUser.update to take an object of type BhUser
		 Integer i = DBUser.insert(addUser);
		 System.out.println("Add User Request Complete");
		// fail("Not yet implemented");
	}

}
