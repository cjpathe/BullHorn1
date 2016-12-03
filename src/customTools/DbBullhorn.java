package customTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BHPost;

public class DbBullhorn {

public static int insert(java.util.Date postdate,String posttext,long userid) {
String sql = "insert into bhpost (postdate,posttext,bhuserid) " +
"values(?,?,?)";
int recordsAffected = 0;
Connection con = null;
PreparedStatement pstmt = null;

try{
Class.forName("oracle.jdbc.driver.OracleDriver");
con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
pstmt = con.prepareStatement(sql);
pstmt.setDate(1,new java.sql.Date(postdate.getTime()));
pstmt.setString(2, posttext);
pstmt.setLong(3, userid);
recordsAffected = pstmt.executeUpdate();
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

return recordsAffected;

}

public static void update() {

}

public static List<BHPost> AllPosts () throws SQLException, ClassNotFoundException{
List<BHPost> posts = new ArrayList<BHPost>();
String sql = "select postid,postdate,posttext,bhuserid from bhpost order by postdate desc";
ResultSet rs = null;
Connection con = null;
PreparedStatement pstmt = null;

Class.forName("oracle.jdbc.driver.OracleDriver");
con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
pstmt = con.prepareStatement(sql);
rs = pstmt.executeQuery();
// Fetch each row from the result set
while (rs.next()) {
long postid = rs.getInt("postid");
java.util.Date postdate = rs.getDate("postdate");
String posttext = rs.getString("posttext");
long userid = rs.getLong("bhuserid");

BHPost p = new BHPost();
p.setPostid(postid);
p.setPostdate(convertJavaDateToSqlDate(postdate));
p.setPosttext(posttext);
p.setBhuserid(userid);
//add the post to the arraylist
posts.add(p);
}
return posts;
}

public static List<BHPost> postsofUser(long userid)
{
List<BHPost> userposts = new ArrayList<BHPost>();

return userposts;
}
public static List<BHPost> postsofUser(String useremail)
{
List<BHPost> userposts = new ArrayList<BHPost>();

return userposts;
}

public static List<BHPost> searchPosts (String search) throws SQLException, ClassNotFoundException
{
	List<BHPost> searchposts = new ArrayList<BHPost>();

	String qString = "%"+search+"%";
	String sql = "select * from BHPost where lower(posttext) like lower(?) order by postdate desc";
	System.out.println("DbBullhorn search string = " + sql + qString);
	ResultSet rs = null;
	Connection con = null;
	PreparedStatement pstmt = null;

	Class.forName("oracle.jdbc.driver.OracleDriver");
	con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
	pstmt = con.prepareStatement(sql);
	pstmt.setString(1, qString);
	rs = pstmt.executeQuery();
	System.out.println("BDbBullhorn search request successful");
	// Fetch each row from the result set
	while (rs.next()) {
			long postid = rs.getInt("postid");
			java.util.Date postdate = rs.getDate("postdate");
			String posttext = rs.getString("posttext");
			long userid = rs.getLong("bhuserid");

			BHPost p = new BHPost();
			p.setPostid(postid);
			p.setPostdate(convertJavaDateToSqlDate(postdate));
			p.setPosttext(posttext);
			p.setBhuserid(userid);
			//add the post to the arraylist
			searchposts.add(p);
	}
	return searchposts;
}

public static java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
return new java.sql.Date(date.getTime());
}

}
