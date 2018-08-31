
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
 * Description: This appends the Business Rules into the MongoDB, by get the
 * values from Frontend (DarshanTimeEstimation.jsp) and checks whether the present Business
 * Rule matches with any document in SaturdayCollection, it will not append the
 * business rule into the collection otherwise it appends into the
 * SaturdayCollection.
 * 
 * @author G.MuniKumari
 *
 */
@WebServlet("/actions")
public class actions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long collectioncount;
	static String from, to, saturdayfrom, saturdayto, responsetoUI, fromtime, totime;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public actions() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 * @param from
	 *            the 'To Time' in the SaturdayCollection (MongoDB)
	 * @param to
	 *            the 'From Time' in the SaturdayCollection (MongoDB)
	 * @param saturdayfrom
	 *            the new 'From Time' from the Saturday Business Rule
	 *            page(DarshanTimeEstimation.jsp)
	 * @param saturdayto
	 *            the new 'To Time' from the Saturday Business Rule
	 *            page(DarshanTimeEstimation.jsp)
	 * @param responsetoUI
	 *            this sends the response to the Frontend (DarshanTimeEstimation.jsp)
	 * @param fromtime
	 *            'From Time' in the SaturdayCollection (MongoDB)
	 * @param totime
	 *            'To Time' in the SaturdayCollection (MongoDB)
	 * @param collectioncount
	 *            the SaturdayCollection count of documents
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		responsetoUI = "yes";
		// Creating the Mongoclient and connecting to the Database
		MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
		// Now connect to Database
		DB db = mongoClient.getDB("TTD");
	
		// Now choose the particular collection
		DBCollection saturdaycollection = db.getCollection("Saturday");
		// Holds the SaturdayCollection overall document count
		collectioncount = saturdaycollection.count();
		// Stores the SaturdayFromTime from Saturday Business Rule Tab in
		// Frontend (DarshanTimeEstimation.jsp)
		saturdayfrom = request.getParameter("SaturdayFromTime").trim();
		// Stores the SaturdayToTime from Saturday Business Rule Tab in Frontend
		// (DarshanTimeEstimation.jsp)
		saturdayto = request.getParameter("SaturdayToTime").trim();
		

		/*
		 * checks whether the Collection Count is not equals to zero or not, if
		 * it's true then it queries 'saturdayfrom' and 'saturdayto' timings in
		 * the SaturdayCollection in MongoDB and then retrieves the 'From' and
		 * 'To' time in this fromtime and totime parameters and then checks
		 * whether the fromtime and totime are equals to the saturdayfrom and
		 * saturdayto, if it's true then it's parse the string to integer the
		 * "parentdivsatlength" from frontend(DarshanTimeEstimation.jsp) and appends all the
		 * rules to the SaturdayCollection in MongoDB.
		 */
		if (collectioncount != 0) {

			// for update of values
			BasicDBObject querytimings = new BasicDBObject();
			querytimings.put("From", saturdayfrom);
			querytimings.put("To", saturdayto);
			/*
			 * Cursor Method: Returns true of the cursor has documents and can
			 * be iterated. Holds the documents in the objects then assigning to
			 * the map.
			 */
			DBCursor cursors = saturdaycollection.find(querytimings);
			if (cursors.hasNext()) {

				DBObject results = cursors.next();
				Map resultmaps = results.toMap();

				fromtime = (String) resultmaps.get("From");
				totime = (String) resultmaps.get("To");
				

				if (fromtime.equals(saturdayfrom) && totime.equals(saturdayto)) {

					int result = Integer.parseInt(request.getParameter("parentdivsatlength").trim());

					BasicDBObject updatevalues = new BasicDBObject("ID", result);

					

					BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
							request.getParameter("SaturdayBusinessRuleName")).

									append("" + "From", request.getParameter("SaturdayFromTime"))
									.append("" + "To", request.getParameter("SaturdayToTime"))
									.append("" + "VQC1_Max_Capacity", request.getParameter("SaturdayVQC1"))
									.append("" + "VQC2_Max_Capacity", request.getParameter("SaturdayVQC2"))
									.append("" + "Preference_Queue", request.getParameter("SaturdayPreferenceQueue"));

					saturdaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

				}

				/*
				 * If the line 121 is false then it queries the 'saturdayfrom'
				 * in the SaturdayCollection and checks if the cursorssat has
				 * document it retrieves the 'To' Time into the parameter 'from'
				 * and then appends all the rules to the SaturdayCollection in
				 * MongoDB.
				 */
			} else {

				BasicDBObject querytime = new BasicDBObject();
				querytime.put("To", saturdayfrom);
				// refer to the line 117 "Cursor Method"
				DBCursor cursorssat = saturdaycollection.find(querytime);
				if (cursorssat.hasNext()) {

					DBObject resultssat = cursorssat.next();
					Map resultmapssat = resultssat.toMap();

					from = (String) resultmapssat.get("To");

					if (from.equals(saturdayfrom)) {

						int result = Integer.parseInt(request.getParameter("parentdivsatlength").trim());

						BasicDBObject updatevalues = new BasicDBObject("ID", result);

						

						BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
								request.getParameter("SaturdayBusinessRuleName")).

										append("" + "From", request.getParameter("SaturdayFromTime"))
										.append("" + "To", request.getParameter("SaturdayToTime"))
										.append("" + "VQC1_Max_Capacity", request.getParameter("SaturdayVQC1"))
										.append("" + "VQC2_Max_Capacity", request.getParameter("SaturdayVQC2"))
										.append("" + "Preference_Queue",
												request.getParameter("SaturdayPreferenceQueue"));

						saturdaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

					}

					/*
					 * If the line 165 is false then sends the response to the
					 * Frontend (DarshanTimeEstimation.jsp)
					 */
				} else {

					response.getWriter().write(responsetoUI);

				}

			}

		}

		/*
		 * If the line 110 is false then it appends all the rules to the
		 * SaturdayCollection in MongoDB with ID count zero i.e., first document
		 * is inserted into the collection.
		 */
		else {

			int result = Integer.parseInt(request.getParameter("parentdivsatlength").trim());

			BasicDBObject udpatevalues = new BasicDBObject("ID", result);

		

			BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
					request.getParameter("SaturdayBusinessRuleName")).

							append("" + "From", request.getParameter("SaturdayFromTime"))
							.append("" + "To", request.getParameter("SaturdayToTime"))
							.append("" + "VQC1_Max_Capacity", request.getParameter("SaturdayVQC1"))
							.append("" + "VQC2_Max_Capacity", request.getParameter("SaturdayVQC2"))
							.append("" + "Preference_Queue", request.getParameter("SaturdayPreferenceQueue"));

			saturdaycollection.update(udpatevalues, new BasicDBObject("$set", inserting), true, false);

		}
	}

}
