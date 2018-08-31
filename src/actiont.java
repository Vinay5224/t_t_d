
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
 * Rule matches with any document in TuesdayCollection, it will not append the
 * business rule into the collection otherwise it appends into the
 * TuesdayCollection.
 * 
 * @author G.MuniKumari
 *
 */
@WebServlet("/actiont")
public class actiont extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long collectioncount;
	static String from, to, tuesdayfrom, tuesdayto, responsetoUI, fromtime, totime;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public actiont() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 * @param from
	 *            the 'To Time' in the TuesdayCollection (MongoDB)
	 * @param to
	 *            the 'From Time' in the TuesdayCollection (MongoDB)
	 * @param tuesdayfrom
	 *            the new 'From Time' from the Tuesday Business Rule
	 *            page(DarshanTimeEstimation.jsp)
	 * @param tuesdayto
	 *            the new 'To Time' from the Tuesday Business Rule
	 *            page(DarshanTimeEstimation.jsp)
	 * @param responsetoUI
	 *            this sends the response to the Frontend (DarshanTimeEstimation.jsp)
	 * @param fromtime
	 *            'From Time' in the TuesdayCollection (MongoDB)
	 * @param totime
	 *            'To Time' in the TuesdayCollection (MongoDB)
	 * @param collectioncount
	 *            the TuesdayCollection count of documents
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
		DBCollection tuesdaycollection = db.getCollection("Tuesday");
		// Holds the TuesdayCollection overall document count
		collectioncount = tuesdaycollection.count();
		// Stores the TuesdayFromTime from Tuesday Business Rule Tab in Frontend
		// (DarshanTimeEstimation.jsp)
		tuesdayfrom = request.getParameter("TuesdayFromTime").trim();
		// Stores the TuesdayToTime from Tuesday Business Rule Tab in Frontend
		// (DarshanTimeEstimation.jsp)
		tuesdayto = request.getParameter("TuesdayToTime").trim();


		/*
		 * checks whether the Collection Count is not equals to zero or not, if
		 * it's true then it queries 'tuesdayfrom' and 'tuesdayto' timings in
		 * the TuesdayCollection in MongoDB and then retrieves the 'From' and
		 * 'To' time in this fromtime and totime parameters and then checks
		 * whether the fromtime and totime are equals to the tuesdayfrom and
		 * tuesdayto, if it's true then it's parse the string to integer the
		 * "parentdivtuelength" from frontend(DarshanTimeEstimation.jsp) and appends all the
		 * rules to the TuesdayCollection in MongoDB.
		 */
		if (collectioncount != 0) {

			// for update of values
			BasicDBObject querytimings = new BasicDBObject();
			querytimings.put("From", tuesdayfrom);
			querytimings.put("To", tuesdayto);
			/*
			 * Cursor Method: Returns true of the cursor has documents and can
			 * be iterated. Holds the documents in the objects then assigning to
			 * the map.
			 */
			DBCursor cursors = tuesdaycollection.find(querytimings);
			if (cursors.hasNext()) {

				DBObject results = cursors.next();
				Map resultmaps = results.toMap();

				fromtime = (String) resultmaps.get("From");
				totime = (String) resultmaps.get("To");
			

				if (fromtime.equals(tuesdayfrom) && totime.equals(tuesdayto)) {

					int result = Integer.parseInt(request.getParameter("parentdivtuelength").trim());

					BasicDBObject updatevalues = new BasicDBObject("ID", result);

					

					BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
							request.getParameter("TuesdayBusinessRuleName")).

									append("" + "From", request.getParameter("TuesdayFromTime"))
									.append("" + "To", request.getParameter("TuesdayToTime"))
									.append("" + "VQC1_Max_Capacity", request.getParameter("TuesdayVQC1"))
									.append("" + "VQC2_Max_Capacity", request.getParameter("TuesdayVQC2"))
									.append("" + "Preference_Queue", request.getParameter("TuesdayPreferenceQueue"));

					tuesdaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

				}

				/*
				 * If the line 122 is false then it queries the 'tuesdayfrom' in
				 * the TuesdayCollection and checks if the cursorstue has
				 * document it retrieves the 'To' Time into the parameter 'from'
				 * and then appends all the rules to the TuesdayCollection in
				 * MongoDB.
				 */
			} else {

				BasicDBObject querytime = new BasicDBObject();
				querytime.put("To", tuesdayfrom);
				// refer to the line 117 "Cursor Method"
				DBCursor cursorstue = tuesdaycollection.find(querytime);
				if (cursorstue.hasNext()) {

					DBObject resultstue = cursorstue.next();
					Map resultmapstue = resultstue.toMap();

					from = (String) resultmapstue.get("To");

					if (from.equals(tuesdayfrom)) {

						int result = Integer.parseInt(request.getParameter("parentdivtuelength").trim());

						BasicDBObject updatevalues = new BasicDBObject("ID", result);

						

						BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
								request.getParameter("TuesdayBusinessRuleName")).

										append("" + "From", request.getParameter("TuesdayFromTime"))
										.append("" + "To", request.getParameter("TuesdayToTime"))
										.append("" + "VQC1_Max_Capacity", request.getParameter("TuesdayVQC1"))
										.append("" + "VQC2_Max_Capacity", request.getParameter("TuesdayVQC2"))
										.append("" + "Preference_Queue",
												request.getParameter("TuesdayPreferenceQueue"));

						tuesdaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

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
		 * TuesdayCollection in MongoDB with ID count zero i.e., first document
		 * is inserted into the collection.
		 */
		else {

			int result = Integer.parseInt(request.getParameter("parentdivtuelength").trim());

			BasicDBObject updatevalues = new BasicDBObject("ID", result);

			

			BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
					request.getParameter("TuesdayBusinessRuleName")).

							append("" + "From", request.getParameter("TuesdayFromTime"))
							.append("" + "To", request.getParameter("TuesdayToTime"))
							.append("" + "VQC1_Max_Capacity", request.getParameter("TuesdayVQC1"))
							.append("" + "VQC2_Max_Capacity", request.getParameter("TuesdayVQC2"))
							.append("" + "Preference_Queue", request.getParameter("TuesdayPreferenceQueue"));

			tuesdaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

		}

	}

}
