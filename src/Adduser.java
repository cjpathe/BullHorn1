

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUser;
import model.BHUser;

/**
 * Servlet implementation class Adduser
 */
@WebServlet("/Adduser")
public class Adduser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Adduser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		 HttpSession session = request.getSession();
		 String nextURL = "/error.jsp";
		 long userid = 0;
		 String action = "";
		 BHUser profileUser = null;
		 BHUser loggedInUser = null;
		 
		 //get user out of session. If they don't exist then send them back to the login page.
		 //kill the session while you're at it.
		 /* if (session.getAttribute("user")==null){
		 //http://stackoverflow.com/questions/13638446/checking-servlet-session-attribute-value-in-jsp-file
		 nextURL = "/Login.jsp";
		 session.invalidate();
		 System.out.println("Session user = null");
		 response.sendRedirect(request.getContextPath() + nextURL );
		 return;//return prevents an error
		 }
		 */
		 // Adding a user doesnt require being logged in
		 
		 try{
		 
		 action = request.getParameter("action");
		 	 
		 //add user user in request variable if action = adduser
		 if (request.getParameter("action").equals("adduser")){
			 System.out.println("Add User Request Received");
			 // long uid = Long.parseLong(request.getParameter("userid"));
			 String userName = request.getParameter("username");
			 String userEmail = request.getParameter("useremail");
			 String userPassword = request.getParameter("userpassword");
			 String userMotto = request.getParameter("usermotto");
			 System.out.println(userName + userEmail + userPassword + userMotto);
			 //populate the existing user then change some fields
			 BHUser addUser = new BHUser();
			 addUser.setUsername(userName);
			 addUser.setMotto(userMotto);
			 addUser.setUseremail(userEmail);
			 addUser.setUserpassword(userPassword);
			 //alternatively could modify DbUser.update to take an object of type BhUser
			 Integer i = DBUser.insert(addUser);
			 System.out.println("Add User Request Complete");
			 nextURL = "/adduser.jsp";
		 	 }
		 }
		 catch(Exception e){
			 System.out.println(e);
			 }finally{
			 //redirect to next page as indicated by the value of the nextURL variable
			 getServletContext().getRequestDispatcher(nextURL).forward(request,response);
			 System.out.println("getServletContext " + nextURL);
			 return;
			 }
	}

}
