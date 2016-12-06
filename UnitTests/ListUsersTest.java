import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;


import util.MD5Util;
import model.BHUser;

public class ListUsersTest {

	@Test
	public void test() {
		
		String sql = "Select bhuserid,username,useremail,userpassword,joindate,motto " + 
				"from Bhuser";
		BHUser user = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if (rs.next()){
				user = new BHUser();
				user.setBhuserid(rs.getLong(1));
				user.setUsername(rs.getString(2));
				user.setUseremail(rs.getString(3));
				user.setUserpassword(rs.getString(4));
				//user.setJoindate(java.util.Date(rs.getString(5)));
				user.setMotto(rs.getString(6));
				System.out.println("User ID \t User Name \t User Email \t \t Password \t Motto");
				System.out.println(user.getBhuserid() + "\t \t" + user.getUsername() + "\t \t" + user.getUseremail() + "\t" + user.getUserpassword() + "\t" + user.getMotto());
			}




		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		// fail("Not yet implemented");
	}

}
