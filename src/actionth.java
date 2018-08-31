
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
 * Rule matches with any document in ThursdayCollection, it will not append the
 * business rule into the collection otherwise it appends into the
 * ThursdayCollection.
 * 
 * @author G.MuniKumari
 *
 */
@WebServlet("/actionth")
public class actionth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long collectioncount;
	static String from, to, thursdayfrom, thursdayto, responsetoUI, fromtime, totime;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public actionth() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 * @param from
	 *            the 'To Time' in the ThursdayCollection (MongoDB)
	 * @param to
	 *            the 'From Time' in the ThursdayCollection (MongoDB)
	 * @param thursdayfrom
	 *            the new 'From Time' from the Thursday Business Rule
	 *            page(DarshanTimeEstimation.jsp)
	 * @param thursdayto
	 *            the new 'To Time' from the Thursday Business Rule
	 *            page(DarshanTimeEstimation.jsp)
	 * @param responsetoUI
	 *            this sends the response to the Frontend (DarshanTimeEstimation.jsp)
	 * @param fromtime
	 *            'From Time' in the ThursdayCollection (MongoDB)
	 * @param totime
	 *            'To Time' in the ThursdayCollection (MongoDB)
	 * @param collectioncount
	 *            the ThursdayCollection count of documents
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
		DBCollection thursdaycollection = db.getCollection("Thursday");
		// Holds the ThursdayCollection overall document count
		collectioncount = thursdaycollection.count();
		// Stores the ThursdayFromTime from Thursday Business Rule Tab in
		// Frontend (DarshanTimeEstimation.jsp)
		thursdayfrom = request.getParameter("ThursdayFromTime").trim();
		// Stores the ThursdayToTime from Thursday Business Rule Tab in Frontend
		// (DarshanTimeEstimation.jsp)
		thursdayto = request.getParameter("ThursdayToTime").trim();
		

		/*
		 * checks whether the Collection Count is not equals to zero or not, if
		 * it's true then it queries 'thursdayfrom' and 'thursdayto' timings in
		 * the ThursdayCollection in MongoDB and then retrieves the 'From' and
		 * 'To' time in this fromtime and totime parameters and then checks
		 * whether the fromtime and totime are equals to the thursdayfrom and
		 * thursdayto, if it's true then it's parse the string to integer the
		 * "parentdivthurlength" from frontend(DarshanTimeEstimation.jsp) and appends all the
		 * rules to the ThursdayCollection in MongoDB.
		 */
		if (collectioncount != 0) {

			// for update of values
			BasicDBObject querytimings = new BasicDBObject();
			querytimings.put("From", thursdayfrom);
			querytimings.put("To", thursdayto);
			/*
			 * Cursor Method: Returns true of the cursor has documents and can
			 * be iterated. Holds the documents in the objects then assigning to
			 * the map.
			 */
			DBCursor cursors = thursdaycollection.find(querytimings);
			if (cursors.hasNext()) {

				DBObject results = cursors.next();
				Map resultmaps = results.toMap();

				fromtime = (String) resultmaps.get("From");
				totime = (String) resultmaps.get("To");
				

				if (fromtime.equals(thursdayfrom) && totime.equals(thursdayto)) {

					int result = Integer.parseInt(request.getParameter("parentdivthurlength").trim());

					BasicDBObject updatevalues = new BasicDBObject("ID", result);

					

					BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
							request.getParameter("ThursdayBusinessRuleName")).

									append("" + "From", request.getParameter("ThursdayFromTime"))
									.append("" + "To", request.getParameter("ThursdayToTime"))
									.append("" + "VQC1_Max_Capacity", request.getParameter("ThursdayVQC1"))
									.append("" + "VQC2_Max_Capacity", request.getParameter("ThursdayVQC2"))
									.append("" + "Preference_Queue", request.getParameter("ThursdayPreferenceQueue"));

					thursdaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

				}

				/*
				 * If the line 122 is false then it queries the 'thursdayfrom'
				 * in the ThursdayCollection and checks if the cursorsthur has
				 * document it retrieves the 'To' Time into the parameter 'from'
				 * and then appends all the rules to the ThursdayCollection in
				 * MongoDB.
				 */
			} else {

				BasicDBObject querytime = new BasicDBObject();
				querytime.put("To", thursdayfrom);
				// refer to the line 118 "Cursor Method"
				DBCursor cursorsthur = thursdaycollection.find(querytime);
				if (cursorsthur.hasNext()) {

					DBObject resultsthur = cursorsthur.next();
					Map resultmapsthur = resultsthur.toMap();

					from = (String) resultmapsthur.get("To");

					if (from.equals(thursdayfrom)) {

						int result = Integer.parseInt(request.getParameter("parentdivthurlength").trim());

						BasicDBObject updatevalues = new BasicDBObject("ID", result);

						

						BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
								request.getParameter("ThursdayBusinessRuleName")).

										append("" + "From", request.getParameter("ThursdayFromTime"))
										.append("" + "To", request.getParameter("ThursdayToTime"))
										.append("" + "VQC1_Max_Capacity", request.getParameter("ThursdayVQC1"))
										.append("" + "VQC2_Max_Capacity", request.getParameter("ThursdayVQC2"))
										.append("" + "Preference_Queue",
												request.getParameter("ThursdayPreferenceQueue"));

						thursdaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

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
		 * ThursdayCollection in MongoDB with ID count zero i.e., first document
		 * is inserted into the collection.
		 */
		else {

			int result = Integer.parseInt(request.getParameter("parentdivthurlength").trim());

			BasicDBObject updatevalues = new BasicDBObject("ID", result);

			

			BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
					request.getParameter("ThursdayBusinessRuleName")).

							append("" + "From", request.getParameter("ThursdayFromTime"))
							.append("" + "To", request.getParameter("ThursdayToTime"))
							.append("" + "VQC1_Max_Capacity", request.getParameter("ThursdayVQC1"))
							.append("" + "VQC2_Max_Capacity", request.getParameter("ThursdayVQC2"))
							.append("" + "Preference_Queue", request.getParameter("ThursdayPreferenceQueue"));

			thursdaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

		}
	}

}
