
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * 
 * Description: This class will returns the last document's 'ToTime' in
 * mondaycollection from MongoDB to the Frontend (DarshanTimeEstimation.jsp) to display the
 * 'From Time' in Monday Business Rule Page in Front end for every add rule
 * 
 * @author C.Swapna
 */
@WebServlet("/Createmon")
public class Createmon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long collectioncount;
	String ToTime;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Createmon() {
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
		// response.getWriter().append("Served at:").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @param totime
	 *            'To Time' in the MondayCollection (MongoDB)
	 * @param collectioncount
	 *            the MondayCollection count of documents
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		// Creating the Mongoclient and connecting to the Database
		MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));

		// Now connect to Database
		DB db = mongoClient.getDB("TTD");
		 

		// Now choose the particular collection
		DBCollection mondaycollection = db.getCollection("Monday");

		// Holds the MondayCollection overall document count
		collectioncount = mondaycollection.count();

		/*
		 * checks whether the collectioncount is not equals to zero or not, if
		 * it's true then it holds the last document in mondaycollection from
		 * mongodb and sends the 'ToTime' as a response to the Monday
		 * BusinessRule Page (DarshanTimeEstimation.jsp)
		 */

		if (collectioncount != 0) {

			// Holds the last document
			DBCursor cursormon = mondaycollection.find().sort(new BasicDBObject("_id", -1)).limit(1);

			/*
			 * Cursor Method: Returns true of the cursor has documents and can
			 * be iterated. Holds the documents in the objects then assigning to
			 * the map.
			 */
			if (cursormon.hasNext()) {

				DBObject results = cursormon.next();
				Map resultmaps = results.toMap();
				ToTime = (String) resultmaps.get("To");

				// Returns ToTime variable to the Monday BusinessRule Page(DarshanTimeEstimation.jsp)
				response.getWriter().write(ToTime);
			}
		}

		// If the line 84 is false then it simply displays the print statement

		else {
			System.out.println("hello");
		}
	}
}
