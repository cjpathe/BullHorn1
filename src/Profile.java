

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUser;
import model.BHUser;
/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Executing Profile DoGet Code");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		/*
		 * simplify this so that it always requires two parameters, userid and action
		 * action is view or edit. If edit then the userID of the session(user) must be same as userID for profile
		 * since you can only edit your own.
		 * all urls coming to this page must contain both parameters or get error.
		 */
		 
		 HttpSession session = request.getSession();
		 String nextURL = "/error.jsp";
		 long userid = 0;
		 String action = "";
		 BHUser profileUser = null;
		 BHUser loggedInUser = null;
		 
		 //get user out of session. If they don't exist then send them back to the login page.
		 //kill the session while you're at it.
		 if (session.getAttribute("user")==null){
		 //http://stackoverflow.com/questions/13638446/checking-servlet-session-attribute-value-in-jsp-file
		 nextURL = "/Login.jsp";
		 session.invalidate();
		 response.sendRedirect(request.getContextPath() + nextURL);
		 return;//return prevents an error
		 }
		 
		 
		 try{
		 userid = Long.parseLong(request.getParameter("userid"));
		 action = request.getParameter("action");
		 
		 
		 //update profile for user in request variable if action = updateprofile
		 if (request.getParameter("action").equals("updateprofile")){
			 System.out.println("Update Profile Request Received");
		 long uid = Long.parseLong(request.getParameter("userid"));
		 String userEmail = request.getParameter("useremail");
		 String userMotto = request.getParameter("usermotto");
		 //populate the existing user then change some fields
		 BHUser updateUser = DBUser.getUser(uid);
		 updateUser.setMotto(userMotto);
		 updateUser.setUseremail(userEmail);
		 //alternatively could modify DbUser.update to take an object of type BhUser
		 DBUser.update(updateUser);
		 }
		 		 
		 //get the user from the parameter
		 profileUser = DBUser.getUser(userid);
		 System.out.println("profileUser = " + profileUser);
		 //get the current user out of the session
		 loggedInUser = (BHUser) session.getAttribute("user"); 
		 System.out.println("LoggedInUser = " + loggedInUser);
		 
		 if (profileUser.getBhuserid()==loggedInUser.getBhuserid()){
		 //display profile as form
		 //the session variable editProfile is used by the JSP to
		 //display the profile in edit mode
		 session.setAttribute("editProfile", true);
		 }else{
		 //display profile read-only
		 //the session variable editProfile is used by the JSP to
		 //display the profile in read-only mode
		 session.setAttribute("editProfile", false);
		 }
		 
		 //populate the data in the attributes
		 System.out.println("Populate the data in the attributes");
		 int imgSize = 120;
		 SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
		 System.out.println("simple date format");
		 // String joindate = sdf.format(profileUser.getJoindate());
		 // System.out.println("joindate");
		 request.setAttribute("userid", profileUser.getBhuserid());
		 System.out.println("userid " + profileUser.getBhuserid());
		 // request.setAttribute("userimage","http://media-cache-ak0.pinimg.com/236x/13/c1/ce/13c1ceb56fb8431275b5cdb0f26adbd7.jpg", imgSize));
		 // System.out.println("userimage");
		 request.setAttribute("username", profileUser.getUsername());
		 System.out.println("username " + profileUser.getUsername());
		 request.setAttribute("useremail", profileUser.getUseremail());
		 System.out.println("useremail " + profileUser.getUseremail());
		 request.setAttribute("usermotto", profileUser.getMotto());
		 System.out.println("usermotto " + profileUser.getMotto());
		 // request.setAttribute("userjoindate", joindate);
		 // System.out.println("userjoindate");
		 nextURL = "/Profile.jsp";
		 // getServletContext().getRequestDispatcher(nextURL).forward(request,response);
		 System.out.println("getServletContext " + nextURL);
		 
		 
		 }catch(Exception e){
		 System.out.println(e);
		 }finally{
		 //redirect to next page as indicated by the value of the nextURL variable
		 getServletContext().getRequestDispatcher(nextURL).forward(request,response);
		 System.out.println("getServletContext " + nextURL);
		 return;
		 }		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Executing Profile DoPost Code");
		doGet(request, response);

	}

}
