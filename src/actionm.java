
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
 * Rule matches with any document in MondayCollection, it will not append the
 * business rule into the collection otherwise it appends into the
 * MondayCollection.
 * 
 * @author G.MuniKumari
 *
 */
@WebServlet("/actionm")
public class actionm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	long collectioncount;
	static String from, to, mondayfrom, mondayto, responsetoUI, fromtime, totime;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public actionm() {
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
	 *            the 'To Time' in the MondayCollection (MongoDB)
	 * @param to
	 *            the 'From Time' in the MondayCollection (MongoDB)
	 * @param mondayfrom
	 *            the new 'From Time' from the Monday Business Rule
	 *            page(DarshanTimeEstimation.jsp)
	 * @param mondayto
	 *            the new 'To Time' from the Monday Business Rule
	 *            page(DarshanTimeEstimation.jsp)
	 * @param responsetoUI
	 *            this sends the response to the Frontend (DarshanTimeEstimation.jsp)
	 * @param fromtime
	 *            'From Time' in the MondayCollection (MongoDB)
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

		responsetoUI = "yes";
		// Creating the Mongoclient and connecting to the Database
		MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
		// Now connect to Database
		DB db = mongoClient.getDB("TTD");
		
		// Now choose the particular collection
		DBCollection mondaycollection = db.getCollection("Monday");

		// Stores the MondayFromTime from Monday Business Rule Tab in Frontend
		// (DarshanTimeEstimation.jsp)
		mondayfrom = request.getParameter("MondayFromTime").trim();
		// Stores the MondayToTime from Monday Business Rule Tab in Frontend
		// (DarshanTimeEstimation.jsp)
		mondayto = request.getParameter("MondayToTime").trim();
		

		// Holds the MondayCollection overall document count
		collectioncount = mondaycollection.count();

		/*
		 * checks whether the Collection Count is not equals to zero or not, if
		 * it's true then it queries 'mondayfrom' and 'mondayto' timings in the
		 * MondayCollection in MongoDB and then retrieves the 'From' and 'To'
		 * time in this fromtime and totime parameters and then checks whether
		 * the fromtime and totime are equals to the mondayfrom and mondayto, if
		 * it's true then it's parse the string to integer the
		 * "parentdivmonlength" from frontend(DarshanTimeEstimation.jsp) and appends all the
		 * rules to the MondayCollection in MongoDB.
		 */
		if (collectioncount != 0) {

			// for update of values
			BasicDBObject querytimings = new BasicDBObject();
			querytimings.put("From", mondayfrom);
			querytimings.put("To", mondayto);
			/*
			 * Cursor Method: Returns true of the cursor has documents and can
			 * be iterated. Holds the documents in the objects then assigning to
			 * the map.
			 */
			DBCursor cursors = mondaycollection.find(querytimings);
			if (cursors.hasNext()) {

				DBObject results = cursors.next();
				Map resultmaps = results.toMap();

				fromtime = (String) resultmaps.get("From");
				totime = (String) resultmaps.get("To");
			

				if (fromtime.equals(mondayfrom) && totime.equals(mondayto)) {

					int result = Integer.parseInt(request.getParameter("parentdivmonlength").trim());

					BasicDBObject updatevalues = new BasicDBObject("ID", result);

					

					BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
							request.getParameter("MondayBusinessRuleName")).

									append("" + "From", request.getParameter("MondayFromTime"))
									.append("" + "To", request.getParameter("MondayToTime"))
									.append("" + "VQC1_Max_Capacity", request.getParameter("MondayVQC1"))
									.append("" + "VQC2_Max_Capacity", request.getParameter("MondayVQC2"))
									.append("" + "Preference_Queue", request.getParameter("MondayPreferenceQueue"));

					mondaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

				}

				/*
				 * If the line 125 is false then it queries the 'mondayfrom' in
				 * the MondayCollection and checks if the cursorsmon has
				 * document it retrieves the 'To' Time into the parameter 'from'
				 * and then appends all the rules to the MondayCollection in
				 * MongoDB.
				 */
			} else {

				BasicDBObject querytime = new BasicDBObject();
				querytime.put("To", mondayfrom);
				// refer to the line 120 "Cursor Method"
				DBCursor cursorsmon = mondaycollection.find(querytime);
				if (cursorsmon.hasNext()) {

					DBObject resultsmon = cursorsmon.next();
					Map resultmapsmon = resultsmon.toMap();

					from = (String) resultmapsmon.get("To");

					
					if (from.equals(mondayfrom)) {

						int result = Integer.parseInt(request.getParameter("parentdivmonlength").trim());

						BasicDBObject updatevalues = new BasicDBObject("ID", result);

						

						BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
								request.getParameter("MondayBusinessRuleName")).

										append("" + "From", request.getParameter("MondayFromTime"))
										.append("" + "To", request.getParameter("MondayToTime"))
										.append("" + "VQC1_Max_Capacity", request.getParameter("MondayVQC1"))
										.append("" + "VQC2_Max_Capacity", request.getParameter("MondayVQC2"))
										.append("" + "Preference_Queue", request.getParameter("MondayPreferenceQueue"));

						mondaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

					}

					/*
					 * If the line 168 is false then sends the response to the
					 * Frontend (DarshanTimeEstimation.jsp)
					 */
				} else {

					response.getWriter().write(responsetoUI);

				}

			}

		}

		/*
		 * If the line 113 is false then it appends all the rules to the
		 * MondayCollection in MongoDB with ID count zero i.e., first document
		 * is inserted into the collection.
		 */
		else {

			int result = Integer.parseInt(request.getParameter("parentdivmonlength").trim());

			BasicDBObject updatevalues = new BasicDBObject("ID", result);

			

			BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
					request.getParameter("MondayBusinessRuleName")).

							append("" + "From", request.getParameter("MondayFromTime"))
							.append("" + "To", request.getParameter("MondayToTime"))
							.append("" + "VQC1_Max_Capacity", request.getParameter("MondayVQC1"))
							.append("" + "VQC2_Max_Capacity", request.getParameter("MondayVQC2"))
							.append("" + "Preference_Queue", request.getParameter("MondayPreferenceQueue"));

			mondaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

		}

	}

}
