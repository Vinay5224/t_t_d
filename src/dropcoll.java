

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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
 * Description: 
 * 				This class drops the Collection in the MongoDB, by get the
 * values from Frontend (DarshanTimeEstimation.jsp) and checks whether the Collections
 * are exist or not, then it drops the collection in the MongoDB.
 * 
 * @author G.MuniKumari
 *
 */
@WebServlet("/dropcoll")
public class dropcoll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dropcoll() {
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
		
		//Stores the daybefore date from the frontend (DarshanTimeEstimation.jsp
		String currentdate = request.getParameter("DayBeforeYesterdayDate");
		//Stores the daybefore day from the frontend (DarshanTimeEstimation.jsp
		String currentday =  request.getParameter("DayBeforeYesterday").trim();
		
		
		

		/// sunday verification
		if (currentday.equals("Sunday")) {
			MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));

			// Now connect to your databases
			DB db = mongoClient.getDB("TTD");
		

			DBCollection sundaycollection = db.getCollection("Sunday");
			//drop the collection
			sundaycollection.drop();

		}

		////////// monday verification
		else if (currentday.equals("Monday")) {

			MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));

			// Now connect to your databases
			DB db = mongoClient.getDB("TTD");
			

			DBCollection mondaycollection = db.getCollection("Monday");
			//drop the monday collection
			mondaycollection.drop();

		}

		////// tuesday verification
		else if (currentday.equals("Tuesday")) {

			MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));

			// Now connect to your databases
			DB db = mongoClient.getDB("TTD");
		

			DBCollection tuesdaycollection = db.getCollection("Tuesday");
			//drop the tuesday collection
			tuesdaycollection.drop();

			DBCollection coll11 = db.getCollection("CountColl");
		

		}
		//////////////////// wednesday verification//////////

		else if (currentday.equals("Wednesday")) {

			MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));

			// Now connect to your databases
			DB db = mongoClient.getDB("TTD");
			

			DBCollection wednesdaycollection = db.getCollection("Wednesday");
			//drop the wednesday collection
			wednesdaycollection.drop();

		}

		///////////////// thursday verification ///////////

		else if (currentday.equals("Thursday")) {

			MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
			// Now connect to your databases
			DB db = mongoClient.getDB("TTD");
			

			DBCollection thursdaycollection = db.getCollection("Thursday");
			//drop the thursday collection
			thursdaycollection.drop();

		}

		/////////////////// friday verification ///////////////

		else if (currentday.equals("Friday")) {

			MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
			// Now connect to your databases
			DB db = mongoClient.getDB("TTD");
			

			DBCollection fridaycollection = db.getCollection("Friday");
			//drop the friday collection
			fridaycollection.drop();

		}
		// }

		////////////////// saturday verification //////////

		else if (currentday.equals("Saturday")) {

			MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));

			// Now connect to your databases
			DB db = mongoClient.getDB("TTD");
			

			DBCollection saturdaycollection = db.getCollection("Saturday");
			//drop the saturday collection
			saturdaycollection.drop();
		}
		//}
		
	}

}
