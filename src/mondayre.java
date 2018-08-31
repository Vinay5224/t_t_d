

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * 
 * Description:
 * 			 This removes the particular document in the MondayCollection, by getting the ID from the 
 * MondayBusinessRule page (DarshanTimeEstimation.jsp).
 * 
 * @author G.MuniKumari
 *
 */
@WebServlet("/mondayre")
public class mondayre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mondayre() {
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
		doGet(request, response);
		
		//Now connect to Database
		MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
		
        // Now connect to your databases
        DB db = mongoClient.getDB( "TTD" );
        //connecting to the collection
        DBCollection mondaycollection = db.getCollection("Monday");

      //parsing the string to the Integer
		int result = Integer.parseInt(request.getParameter("parentdivmonlength").trim());
		BasicDBObject ID = new BasicDBObject("ID",result);

		//removing that particular document in the MondayCollection

		mondaycollection.remove(ID);


}
	

	
}


