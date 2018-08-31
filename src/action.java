
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.RequestDispatcher;	import javax.servlet.ServletException;
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
 * 			This appends the Business Rules into the MongoDB, by get the
 * values from Frontend (DarshanTimeEstimation.jsp) and checks whether the present Business
 * Rule matches with any document in SundayCollection, it will not append the
 * business rule into the collection otherwise it appends into the
 * SundayCollection.
 * 
 * @author G.MuniKumari
 *
 */
@WebServlet("/action")
public class action extends HttpServlet {

	private static final long serialVersionUID = 1L;

	long collectioncount;
	static String from, to, sundayfrom, sundayto, responsetoUI, fromtime, totime;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public action() {
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
	 *            the 'To Time' in the SundayCollection (MongoDB)
	 * @param to
	 *            the 'From Time' in the SundayCollection (MongoDB)
	 * @param sundayfrom
	 *            the new 'From Time' from the Sunday Business Rule
	 *            page(DarshanTimeEstimation.jsp)
	 * @param sundayto
	 *            the new 'To Time' from the Sunday Business Rule page
	 *            (DarshanTimeEstimation.jsp)
	 * @param responsetoUI
	 *            this sends the response to the Frontend (DarshanTimeEstimation.jsp)
	 * @param fromtime
	 *            'From Time' in the SundayCollection (MongoDB)
	 * @param totime
	 *            'To Time' in the SundayCollection (MongoDB)
	 * @param collectioncount
	 *            the SundayCollection count of documents
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
		DBCollection sundaycollection = db.getCollection("Sunday");

		// Holds the SundayCollection overall document count
		collectioncount = sundaycollection.count();

		// Stores the SundayFromTime from Sunday Business Rule Tab in Frontend
		// (DarshanTimeEstimation.jsp)
		sundayfrom = request.getParameter("SundayFromTime").trim();
		// Stores the SundayToTime from Sunday Business Rule Tab in Frontend
		// (DarshanTimeEstimation.jsp)
		sundayto = request.getParameter("SundayToTime").trim();
		

		/*
		 * checks whether the Collection Count is not equals to zero or not, if
		 * it's true then it queries 'sundayfrom' and 'sundayto' timings in the
		 * SundayCollection in MongoDB and then retrieves the 'From' and 'To'
		 * time in this fromtime and totime parameters and then checks whether
		 * the fromtime and totime are equals to the sundayfrom and sundayto, if
		 * it's true then it's parse the string to integer the
		 * "parentdivsunlength" from frontend(DarshanTimeEstimation.jsp) and appends all the
		 * rules to the SundayCollection in MongoDB.
		 */
				if (collectioncount!= 0) {

			// for update of values
			BasicDBObject querytimings = new BasicDBObject();
			querytimings.put("From", sundayfrom);
			querytimings.put("To", sundayto);
			DBCursor cursors = sundaycollection.find(querytimings);
			/*
			 * Cursor Method: Returns true of the cursor has documents and can
			 * be iterated. Holds the documents in the objects then assigning to
			 * the map.
			 */
			if (cursors.hasNext()) {

				DBObject results = cursors.next();
				Map resultmaps = results.toMap();

				fromtime = (String) resultmaps.get("From");
				totime = (String) resultmaps.get("To");

				if (fromtime.equals(sundayfrom) && totime.equals(sundayto)) {

					int result = Integer.parseInt(request.getParameter("parentdivsunlength").trim());

					BasicDBObject updatevalues = new BasicDBObject("ID", result);

					

					BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
							request.getParameter("SundayBusinessRuleName")).

									append("" + "From", request.getParameter("SundayFromTime"))
									.append("" + "To", request.getParameter("SundayToTime"))
									.append("" + "VQC1_Max_Capacity", request.getParameter("SundayVQC1"))
									.append("" + "VQC2_Max_Capacity", request.getParameter("SundayVQC2"))
									.append("" + "Preference_Queue", request.getParameter("SundayPreferenceQueue"));

					sundaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

				}

			}
			/*
			 * If the line 130 is false then it queries the 'sundayfrom' in the
			 * SundayCollection and checks if the cursorsun has document it
			 * retrieves the 'To' Time into the parameter 'from' and then
			 * appends all the rules to the SundayCollection in MongoDB.
			 */
			else {

				BasicDBObject querytime = new BasicDBObject();
				querytime.put("To", sundayfrom);
				
				//refer to the line 125 "Cursor Method"
				DBCursor cursorssun = sundaycollection.find(querytime);
				if (cursorssun.hasNext()) {

					DBObject resultssun = cursorssun.next();
					Map resultmapsun = resultssun.toMap();

					from = (String) resultmapsun.get("To");

					if (from.equals(sundayfrom)) {

						int result = Integer.parseInt(request.getParameter("parentdivsunlength").trim());

						BasicDBObject updatevalue = new BasicDBObject("ID", result);

						

						BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
								request.getParameter("SundayBusinessRuleName")).

										append("" + "From", request.getParameter("SundayFromTime"))
										.append("" + "To", request.getParameter("SundayToTime"))
										.append("" + "VQC1_Max_Capacity", request.getParameter("SundayVQC1"))
										.append("" + "VQC2_Max_Capacity", request.getParameter("SundayVQC2"))
										.append("" + "Preference_Queue", request.getParameter("SundayPreferenceQueue"));

						sundaycollection.update(updatevalue, new BasicDBObject("$set", inserting), true, false);

					}

				}
				/*
				 * If the line 173 is false then sends the response to the
				 * Frontend (DarshanTimeEstimation.jsp)
				 */
				else {

					response.getWriter().write(responsetoUI);

				}

			}

		}

		/*
		 * If the line 118 is false then it appends all the rules to the
		 * SundayCollection in MongoDB with ID count zero i.e., first document
		 * is inserted into the collection.
		 */
		else {

					int result = Integer.parseInt(request.getParameter("parentdivsunlength").trim());

			BasicDBObject updatevalue = new BasicDBObject("ID", result);

			

			BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
					request.getParameter("SundayBusinessRuleName")).

							append("" + "From", request.getParameter("SundayFromTime"))
							.append("" + "To", request.getParameter("SundayToTime"))
							.append("" + "VQC1_Max_Capacity", request.getParameter("SundayVQC1"))
							.append("" + "VQC2_Max_Capacity", request.getParameter("SundayVQC2"))
							.append("" + "Preference_Queue", request.getParameter("SundayPreferenceQueue"));

			sundaycollection.update(updatevalue, new BasicDBObject("$set", inserting), true, false);

		}

	}
}
