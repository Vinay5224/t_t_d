
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
 * Rule matches with any document in SundayCollection, it will not append the
 * business rule into the collection otherwise it appends into the
 * SundayCollection.
 * 
 * @author G.MuniKumari
 *
 */
@WebServlet("/actionw")
public class actionw extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long collectioncount;
	static String from, to, wednesdayfrom, wednesdayto, responsetoUI, fromtime, totime;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public actionw() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 * @param from
	 *            the 'To Time' in the WednesdayCollection (MongoDB)
	 * @param to
	 *            the 'From Time' in the WednesdayCollection (MongoDB)
	 * @param wednesdayfrom
	 *            the new 'From Time' from the Wednesday Business Rule
	 *            page(DarshanTimeEstimation.jsp)
	 * @param wednesdayto
	 *            the new 'To Time' from the Wednesday Business Rule
	 *            page(DarshanTimeEstimation.jsp)
	 * @param responsetoUI
	 *            this sends the response to the Frontend (DarshanTimeEstimation.jsp)
	 * @param fromtime
	 *            'From Time' in the WednesdayCollection (MongoDB)
	 * @param totime
	 *            'To Time' in the WednesdayCollection (MongoDB)
	 * @param collectioncount
	 *            the WednesdayCollection count of documents
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
		DBCollection wednesdaycollection = db.getCollection("Wednesday");
		// Holds the WednesdayCollection overall document count
		collectioncount = wednesdaycollection.count();

		// Stores the WenesdayFromTime from Wednesday Business Rule Tab in
		// Frontend (DarshanTimeEstimation.jsp)
		wednesdayfrom = request.getParameter("WenesdayFromTime").trim();
		// Stores the WenesdayToTime from Wednesday Business Rule Tab in
		// Frontend (DarshanTimeEstimation.jsp)
		wednesdayto = request.getParameter("WenesdayToTime").trim();
		

		/*
		 * checks whether the Collection Count is not equals to zero or not, if
		 * it's true then it queries 'wednesdayfrom' and 'wednesdayto' timings
		 * in the WednesdayCollection in MongoDB and then retrieves the 'From'
		 * and 'To' time in this fromtime and totime parameters and then checks
		 * whether the fromtime and totime are equals to the wednesdayfrom and
		 * wednesdayto, if it's true then it's parse the string to integer the
		 * "parentdivwedlength" from frontend(DarshanTimeEstimation.jsp) and appends all the
		 * rules to the WednesdayCollection in MongoDB.
		 */
		if (collectioncount != 0) {

			// for update of values
			BasicDBObject querytimings = new BasicDBObject();
			querytimings.put("From", wednesdayfrom);
			querytimings.put("To", wednesdayto);
			/*
			 * Cursor Method: Returns true of the cursor has documents and can
			 * be iterated. Holds the documents in the objects then assigning to
			 * the map.
			 */
			DBCursor cursors = wednesdaycollection.find(querytimings);
			if (cursors.hasNext()) {

				DBObject results = cursors.next();
				Map resultmaps = results.toMap();

				fromtime = (String) resultmaps.get("From");
				totime = (String) resultmaps.get("To");
				

				if (fromtime.equals(wednesdayfrom) && totime.equals(wednesdayto)) {

					int result = Integer.parseInt(request.getParameter("parentdivwedlength").trim());

					BasicDBObject updatevalues = new BasicDBObject("ID", result);

					

					BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
							request.getParameter("WednesdayBusinessRuleName")).

									append("" + "From", request.getParameter("WenesdayFromTime"))
									.append("" + "To", request.getParameter("WenesdayToTime"))
									.append("" + "VQC1_Max_Capacity", request.getParameter("WednesdayVQC1"))
									.append("" + "VQC2_Max_Capacity", request.getParameter("WednesdayVQC2"))
									.append("" + "Preference_Queue", request.getParameter("WednesdayPreferenceQueue"));

					wednesdaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

				}

				/*
				 * If the line 129 is false then it queries the 'wednesdayfrom'
				 * in the WednesdayCollection and checks if the cursorswed has
				 * document it retrieves the 'To' Time into the parameter 'from'
				 * and then appends all the rules to the WednesdayCollection in
				 * MongoDB.
				 */
			} else {

				BasicDBObject querytime = new BasicDBObject();
				querytime.put("To", wednesdayfrom);
				// refer to the line 125 "Cursor Method"
				DBCursor cursorswed = wednesdaycollection.find(querytime);
				if (cursorswed.hasNext()) {

					DBObject resultswed = cursorswed.next();
					Map resultmapswed = resultswed.toMap();

					from = (String) resultmapswed.get("To");

					if (from.equals(wednesdayfrom)) {

						int result = Integer.parseInt(request.getParameter("parentdivwedlength").trim());

						BasicDBObject updatevalues = new BasicDBObject("ID", result);

						

						BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
								request.getParameter("WednesdayBusinessRuleName")).

										append("" + "From", request.getParameter("WenesdayFromTime"))
										.append("" + "To", request.getParameter("WenesdayToTime"))
										.append("" + "VQC1_Max_Capacity", request.getParameter("WednesdayVQC1"))
										.append("" + "VQC2_Max_Capacity", request.getParameter("WednesdayVQC2"))
										.append("" + "Preference_Queue",
												request.getParameter("WednesdayPreferenceQueue"));

						wednesdaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

					}

					/*
					 * If the line 172 is false then sends the response to the
					 * Frontend (DarshanTimeEstimation.jsp)
					 */
				} else {

					response.getWriter().write(responsetoUI);

				}

			}

		}

		/*
		 * If the line 117 is false then it appends all the rules to the
		 * WednesdayCollection in MongoDB with ID count zero i.e., first
		 * document is inserted into the collection.
		 */
		else {

			int result = Integer.parseInt(request.getParameter("parentdivwedlength").trim());

			BasicDBObject updatevalues = new BasicDBObject("ID", result);

			

			BasicDBObject inserting = new BasicDBObject("" + "Business_Rule_Name",
					request.getParameter("WednesdayBusinessRuleName")).

							append("" + "From", request.getParameter("WenesdayFromTime"))
							.append("" + "To", request.getParameter("WenesdayToTime"))
							.append("" + "VQC1_Max_Capacity", request.getParameter("WednesdayVQC1"))
							.append("" + "VQC2_Max_Capacity", request.getParameter("WednesdayVQC2"))
							.append("" + "Preference_Queue", request.getParameter("WednesdayPreferenceQueue"));

			wednesdaycollection.update(updatevalues, new BasicDBObject("$set", inserting), true, false);

		}

	}

}
