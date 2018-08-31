
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import javafx.scene.control.Alert;

/**
 * 
 * Description: 
 * 			This class validates, whether username and password in 'Timeslots.jsp' are matched with the 
 * signing collection in MongoDB if it matches then it redirects to the OneHour.jsp or 
 * otherwise it passes a string called 'invalid username and password'.
 * 
 * @author G.MuniKumari
 *
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//Now to connect to Database
		MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
		// Now connect to your databases
		DB db = mongoClient.getDB("TTD");
	

		DBCollection signincollection = db.getCollection("signing");
		

		BasicDBObject loginobject = new BasicDBObject();

		String Email = request.getParameter("User");
		String Password = request.getParameter("Password");

		loginobject.put("Email", Email);
		loginobject.put("Password", Password);
		  //Checking the Email and Password is matches in signing collection
		DBCursor cursor = signincollection.find(loginobject);
		  //if the cursor hasNext then it send the Email to the OneHour.jsp through the Attribute and redirects to the OneHour.jsp
		if (cursor.hasNext()) {
			HttpSession session = request.getSession();
			session.setAttribute("Email", Email);
			response.sendRedirect("Timeslot.jsp");

		}
		 //if line 77 is false, then it passes the string values saying that "Invalid Username or Password" to the Timeslots.jsp
		else {

			String usernotfound = "";
			usernotfound += "<h1>Invalid Username or Password</h1>";
			response.getWriter().write(usernotfound);

		}
	}
}
