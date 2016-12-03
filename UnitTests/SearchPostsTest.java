import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import customTools.*;
import model.*;


public class SearchPostsTest {

	@Test
	public void test() {
		try {
			
			System.out.println("DbBullhorn posts = search posts");
			ArrayList<BHPost> posts = (ArrayList) DbBullhorn.searchPosts("minion");
			for (Integer j=0; j <posts.size(); j++) {
				BHPost printPost = new BHPost();
				printPost = posts(j);
				System.out.println( printPost.getPosttext());
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

}
