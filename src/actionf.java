
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
 * Description: This appends the Business Rules into the MongoDB, by get the
 * values from Frontend (DarshanTimeEstimation.jsp) and checks whether the present Business
 * Rule matches with any document in FridayCollection, it will not append the
 * business rule into the collection otherwise it appends into the
 * FridayCollection.
 * 
 * @author G.MuniKumari
 *
 */
@WebServlet("/actionf")
public class actionf extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long collectioncount;
	static String from, to, fridayfrom, fridayto, responsetoUI, fromtime, totime;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public actionf() {
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 * @param from
	 *            the 'To Time' in the FridayCollection (MongoDB)
	 * @param to
	 *            the 'From Time' in the FridayCollection (MongoDB)
	 * @param fridayfrom
	 *            the new 'From Time' from the Friday Business Rule
	 *            page(DarshanTimeEstimation.jsp)
	 * @param fridayto
	 *            the new 'To Time' from the Friday Business Rule
	 *            page(DarshanTimeEstimation.jsp)
	 * @param responsetoUI
	 *            this sends the response to the Frontend (DarshanTimeEstimation.jsp)
	 * @param fromtime
	 *            'From Time' in the FridayCollection (MongoDB)
	 * @param totime
	 *            'To Time' in the FridayCollection (MongoDB)
	 * @param collectioncount
	 *            the FridayCollection count of documents
	 * 
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
		DBCollection fridaycollection = db.getCollection("Friday");
		// Holds the FridayCollection overall document count
		collectioncount = fridaycollection.count();
		// Stores the FridayFromTime from Friday Business Rule Tab in Frontend
		// (DarshanTimeEstimation.jsp)
		fridayfrom = request.getParameter("FridayFromTime").trim();
		// Stores the FridayToTime from Friday Business Rule Tab in Frontend
		// (DarshanTimeEstimation.jsp)
		fridayto = request.getParameter("FridayToTime").trim();


		/*
		 * checks whether the Collection Count is not equals to zero or not, if
		 * it's true then it queries 'fridayfrom' and 'fridayto' timings in the
		 * FridayCollection in MongoDB and then retrieves the 'From' and 'To'
		 * time in this fromtime and totime parameters and then checks whether
		 * the fromtime and totime are equals to the fridayfrom and fridayto, if
		 * it's true then it's parse the string to integer the
		 * "parentdivfrilength" from frontend(DarshanTimeEstimation.jsp) and appends all the
		 * rules to the FridayCollection in MongoDB.
		 */
		if (collectioncount != 0) {

			// for update of values
			BasicDBObject querytimings = new BasicDBObject();
			querytimings.put("From", fridayfrom);
			querytimings.put("To", fridayto);
			/*
			 * Cursor Method: Returns true of the cursor has documents and can
			 * be iterated. Holds the documents in the objects then assigning to
			 * the map.
			 */
			DBCursor cursors = fridaycollection.find(querytimings);
			if (cursors.hasNext()) {

				DBObject results = cursors.next();
				Map resultmaps = results.toMap();

				fromtime = (String) resultmaps.get("From");
				totime = (String) resultmaps.get("To");
				

				if (fromtime.equals(fridayfrom) && totime.equals(fridayto)) {

					int result = Integer.parseInt(request.getParameter("parentdivfrilength").trim());

					BasicDBObject updatevalues = new BasicDBObject("ID", result);

					

					BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
							request.getParameter("FridayBusinessRuleName")).

									append("" + "From", request.getParameter("FridayFromTime"))
									.append("" + "To", request.getParameter("FridayToTime"))
									.append("" + "VQC1_Max_Capacity", request.getParameter("FridayVQC1"))
									.append("" + "VQC2_Max_Capacity", request.getParameter("FridayVQC2"))
									.append("" + "Preference_Queue", request.getParameter("FridayPreferenceQueue"));

					fridaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

				}

				/*
				 * If the line 123 is false then it queries the 'fridayfrom' in
				 * the FridayCollection and checks if the cursorsfri has
				 * document it retrieves the 'To' Time into the parameter 'from'
				 * and then appends all the rules to the FridayCollection in
				 * MongoDB.
				 */
			} else {

				BasicDBObject querytime = new BasicDBObject();
				querytime.put("To", fridayfrom);
				// refer to the line 118 "Cursor Method"
				DBCursor cursorsfri = fridaycollection.find(querytime);
				if (cursorsfri.hasNext()) {

					DBObject resultsfri = cursorsfri.next();
					Map resultmapsfri = resultsfri.toMap();

					from = (String) resultmapsfri.get("To");

					if (from.equals(fridayfrom)) {

						int result = Integer.parseInt(request.getParameter("parentdivfrilength").trim());

						BasicDBObject updatevalues = new BasicDBObject("ID", result);

						

						BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
								request.getParameter("FridayBusinessRuleName")).

										append("" + "From", request.getParameter("FridayFromTime"))
										.append("" + "To", request.getParameter("FridayToTime"))
										.append("" + "VQC1_Max_Capacity", request.getParameter("FridayVQC1"))
										.append("" + "VQC2_Max_Capacity", request.getParameter("FridayVQC2"))
										.append("" + "Preference_Queue", request.getParameter("FridayPreferenceQueue"));

						fridaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

					}

					/*
					 * If the line 166 is false then sends the response to the
					 * Frontend (DarshanTimeEstimation.jsp)
					 */
				} else {

					response.getWriter().write(responsetoUI);

				}

			}

		}

		/*
		 * If the line 111 is false then it appends all the rules to the
		 * FridayCollection in MongoDB with ID count zero i.e., first document
		 * is inserted into the collection.
		 */
		else {

			int result = Integer.parseInt(request.getParameter("parentdivfrilength").trim());

			BasicDBObject updatevalues = new BasicDBObject("ID", result);

			

			BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
					request.getParameter("FridayBusinessRuleName")).

							append("" + "From", request.getParameter("FridayFromTime"))
							.append("" + "To", request.getParameter("FridayToTime"))
							.append("" + "VQC1_Max_Capacity", request.getParameter("FridayVQC1"))
							.append("" + "VQC2_Max_Capacity", request.getParameter("FridayVQC2"))
							.append("" + "Preference_Queue", request.getParameter("FridayPreferenceQueue"));

			fridaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

		}
	}

}
