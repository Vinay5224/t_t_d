
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.model.InsertManyOptions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * Description: 
 * 					This appends the Real time estimated darshan time for all days in the week into the MongoDB, by get the
 * values from Homepage collection and then admin enters the data into the Onehour.jsp 
 *  
 * @author G.MuniKumari
 *
 */
@WebServlet("/Slots")
public class Slots extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Slots() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		//Stores the Date from the OneHour.jsp
		String Date = request.getParameter("date");
		//Stores the Day from the OneHour.jsp
		String Day = request.getParameter("day");
		//stores the count value from the OneHour.jsp
		String[] countarry = request.getParameterValues("count");
		//Stores the 24hours which gets from the OneHour.jsp
		String[] TimeArray = request.getParameterValues("Time");
		//Stores the VQC-1 pilgrims values from the OneHour.jsp
		String[] vqc1pilgrims = request.getParameterValues("rvqc1");
		//Stores the VQC-2 pilgrims values from the OneHour.jsp
		String[] vqc2pilgrims = request.getParameterValues("rvqc2");
		//Stores the VQC-1 estmated darshan time from the OneHour.jsp
		String[] vqc1darshantime = request.getParameterValues("cvqc1");
		//Stores the VQC-2 estmated darshan time from the OneHour.jsp
		String[] vqc2darshantime = request.getParameterValues("cvqc2");
		//Stores the VQC-1 real time from the OneHour.jsp
		String[] vqc1realtime = request.getParameterValues("ctvqc1");
		//Stores the VQC-2 real time from the OneHour.jsp
		String[] vqc2realtime = request.getParameterValues("ctvqc2");
		
		int vqc2realtimelength = vqc2realtime.length;
		
/*
 * If the Day is equals to the "Monday" then it connects to the 'MondayHistory' Collection and checks the MondayHistory collection
 * counts.  
 * 
 */
		if (Day.equalsIgnoreCase("Monday")) {

			MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
			DB db = mongoClient.getDB("TTD");
			

			DBCollection collection = db.getCollection("MondayHistory");
			

			int cou = (int) db.getCollection("MondayHistory").count();
			
			
			//if the Collection count is greater than 0, then creating the Basic list object and then pushing the data in the 
			//embedded document form.
			if (cou > 0) {

				for (int ID = 0; ID < 24; ID++) {

					List<BasicDBObject> realtimelist = new ArrayList<BasicDBObject>();
					int i = ID;

					DBObject find = new BasicDBObject("ID", ID);

					DBObject push = new BasicDBObject("$push", new BasicDBObject(ID + ":00",
							new BasicDBObject("Date", Date).append("VQC1_No_of_Pilgrims", vqc1pilgrims[i])
									.append("VQC2_No_of_Pilgrims", vqc2pilgrims[i])
									.append("VQC1_Est_DarshanTime", vqc1darshantime[i])
									.append("VQC2_Est_DarshanTime", vqc2darshantime[i])
									.append("VQC1_RealTime", vqc1realtime[i]).append("VQC2_RealTime", vqc2realtime[i])

					)

					);

					db.getCollection("MondayHistory").update(find, push);

				}
				
				response.sendRedirect("times.jsp");
			}
			//If collection count is 0 then it appends the new document into the collection
			
			else {

				
				////////////////////////
				for (int ID = 0; ID < 24; ID++) {

					for (int i = 0; i < vqc2realtimelength; i++) {

						BasicDBObject embeddedobject = new BasicDBObject();
						/// no documents .......
						embeddedobject.append("ID", ID);


						List<BasicDBObject> realtimelist = new ArrayList<BasicDBObject>();
						BasicDBObject embeddeddoc = new BasicDBObject("Date", Date)
								.append("VQC1_No_of_Pilgrims", vqc1pilgrims[i])
								.append("VQC2_No_of_Pilgrims", vqc2pilgrims[i])
								.append("VQC1_Est_DarshanTime", vqc1darshantime[i])
								.append("VQC2_Est_DarshanTime", vqc2darshantime[i])
								.append("VQC1_RealTime", vqc1realtime[i]).append("VQC2_RealTime", vqc2realtime[i]);
						realtimelist.add(embeddeddoc);

						embeddedobject.append(TimeArray[i], realtimelist);
						collection.insert(embeddedobject);

					}

				}
				response.sendRedirect("times.jsp");
			}

		}
		
		//same as the line 102
		else if (Day.equalsIgnoreCase("Tuesday")) {

			MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));

			DB db = mongoClient.getDB("TTD");
	

			DBCollection coll = db.getCollection("TuesdayHistory");
			

			int cou = (int) db.getCollection("TuesdayHistory").count();
			
			//same as the line 118
			if (cou > 0) {

				for (int ID = 0; ID < 24; ID++) {

					List<BasicDBObject> realtimelist = new ArrayList<BasicDBObject>();
					int i = ID;

					DBObject find = new BasicDBObject("ID", ID);

					DBObject push = new BasicDBObject("$push", new BasicDBObject(ID + ":00",
							new BasicDBObject("Date", Date).append("VQC1_No_of_Pilgrims", vqc1pilgrims[i])
									.append("VQC2_No_of_Pilgrims", vqc2pilgrims[i])
									.append("VQC1_Est_DarshanTime", vqc1darshantime[i])
									.append("VQC2_Est_DarshanTime", vqc2darshantime[i])
									.append("VQC1_RealTime", vqc1realtime[i]).append("VQC2_RealTime", vqc2realtime[i])

					)

					);

					db.getCollection("TuesdayHistory").update(find, push);

				}
				
				response.sendRedirect("times.jsp");
			} 
			//same as the line 147
			else {

				
				////////////////////////
				for (int ID = 0; ID < 24; ID++) {

					for (int i = 0; i < vqc2realtimelength; i++) {

						BasicDBObject embeddedobject = new BasicDBObject();
						/// no documents .......
						embeddedobject.append("ID", ID);

						

						List<BasicDBObject> realtimelist = new ArrayList<BasicDBObject>();
						BasicDBObject embeddeddoc = new BasicDBObject("Date", Date)
								.append("VQC1_No_of_Pilgrims", vqc1pilgrims[i])
								.append("VQC2_No_of_Pilgrims", vqc2pilgrims[i])
								.append("VQC1_Est_DarshanTime", vqc1darshantime[i])
								.append("VQC2_Est_DarshanTime", vqc2darshantime[i])
								.append("VQC1_RealTime", vqc1realtime[i]).append("VQC2_RealTime", vqc2realtime[i]);
						realtimelist.add(embeddeddoc);

						embeddedobject.append(TimeArray[i], realtimelist);
						coll.insert(embeddedobject);

					}

				}
				response.sendRedirect("times.jsp");
			}

		} 
		//same as the line 102
		else if (Day.equalsIgnoreCase("Wednesday")) {

			MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
			DB db = mongoClient.getDB("TTD");
			

			DBCollection coll = db.getCollection("WednesdayHistory");
			

			int cou = (int) db.getCollection("WednesdayHistory").count();
			
			//same as the line 118
			if (cou > 0) {
				/*
				 * DBObject find = new BasicDBObject("ID", 3); DBObject push =
				 * new BasicDBObject("$push", new BasicDBObject( "3:00" , new
				 * BasicDBObject().append("s", "t")));
				 * 
				 * db.getCollection("WednesdayHistory").update(find, push);
				 */
				for (int ID = 0; ID < 24; ID++) {

					List<BasicDBObject> realtimelist = new ArrayList<BasicDBObject>();
					int i = ID;
					/*
					 * BasicDBObject where = new BasicDBObject("Date", Date)
					 * .append("VQC1_No_of_Pilgrims", rvqc1Array1[i])
					 * .append("VQC2_No_of_Pilgrims",rvqc2Array2[i])
					 * .append("VQC1_Est_DarshanTime",cvqc1Array3[i])
					 * .append("VQC2_Est_DarshanTime",cvqc2Array4[i])
					 * .append("VQC1_RealTime",ctvqc1Array5[i])
					 * .append("VQC2_RealTime",ctvqc2Array6[i]);
					 * seva.add(where);
					 */
					DBObject find = new BasicDBObject("ID", ID);

					DBObject push = new BasicDBObject("$push", new BasicDBObject(ID + ":00",
							new BasicDBObject("Date", Date).append("VQC1_No_of_Pilgrims", vqc1pilgrims[i])
									.append("VQC2_No_of_Pilgrims", vqc2pilgrims[i])
									.append("VQC1_Est_DarshanTime", vqc1darshantime[i])
									.append("VQC2_Est_DarshanTime", vqc2darshantime[i])
									.append("VQC1_RealTime", vqc1realtime[i]).append("VQC2_RealTime", vqc2realtime[i])

					)

					);

					db.getCollection("WednesdayHistory").update(find, push);

				}
				
				response.sendRedirect("times.jsp");
			} 
			//same as the line 147
			else {

				
				////////////////////////
				for (int ID = 0; ID < 24; ID++) {

					for (int i = 0; i < vqc2realtimelength; i++) {

						BasicDBObject embeddedobject = new BasicDBObject();
						/// no documents .......
						embeddedobject.append("ID", ID);

						

						List<BasicDBObject> realtimelist = new ArrayList<BasicDBObject>();
						BasicDBObject embeddeddoc = new BasicDBObject("Date", Date)
								.append("VQC1_No_of_Pilgrims", vqc1pilgrims[i])
								.append("VQC2_No_of_Pilgrims", vqc2pilgrims[i])
								.append("VQC1_Est_DarshanTime", vqc1darshantime[i])
								.append("VQC2_Est_DarshanTime", vqc2darshantime[i])
								.append("VQC1_RealTime", vqc1realtime[i]).append("VQC2_RealTime", vqc2realtime[i]);
						realtimelist.add(embeddeddoc);

						embeddedobject.append(TimeArray[i], realtimelist);
						coll.insert(embeddedobject);
						//////////////////////////
					}

				}
				response.sendRedirect("times.jsp");
			}

		}
		
		//same as the line 102
	
		else if (Day.equalsIgnoreCase("Thursday")) {

			MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
			DB db = mongoClient.getDB("TTD");
			

			DBCollection coll = db.getCollection("ThursdayHistory");
			

			int cou = (int) db.getCollection("ThursdayHistory").count();
	
			//same as the line 118
	
			if (cou > 0) {

				for (int ID = 0; ID < 24; ID++) {

					List<BasicDBObject> realtimelist = new ArrayList<BasicDBObject>();
					int i = ID;

					DBObject find = new BasicDBObject("ID", ID);

					DBObject push = new BasicDBObject("$push", new BasicDBObject(ID + ":00",
							new BasicDBObject("Date", Date).append("VQC1_No_of_Pilgrims", vqc1pilgrims[i])
									.append("VQC2_No_of_Pilgrims", vqc2pilgrims[i])
									.append("VQC1_Est_DarshanTime", vqc1darshantime[i])
									.append("VQC2_Est_DarshanTime", vqc2darshantime[i])
									.append("VQC1_RealTime", vqc1realtime[i]).append("VQC2_RealTime", vqc2realtime[i])

					)

					);

					db.getCollection("ThursdayHistory").update(find, push);

				}
				
				response.sendRedirect("times.jsp");
			} 
	
			//same as the line 147
			else {

				
				////////////////////////
				for (int ID = 0; ID < 24; ID++) {

					for (int i = 0; i < vqc2realtimelength; i++) {

						BasicDBObject embeddedobject = new BasicDBObject();

						/// no documents .......
						embeddedobject.append("ID", ID);

					

						List<BasicDBObject> realtimelist = new ArrayList<BasicDBObject>();
						BasicDBObject embeddeddoc = new BasicDBObject("Date", Date)
								.append("VQC1_No_of_Pilgrims", vqc1pilgrims[i])
								.append("VQC2_No_of_Pilgrims", vqc2pilgrims[i])
								.append("VQC1_Est_DarshanTime", vqc1darshantime[i])
								.append("VQC2_Est_DarshanTime", vqc2darshantime[i])
								.append("VQC1_RealTime", vqc1realtime[i]).append("VQC2_RealTime", vqc2realtime[i]);
						realtimelist.add(embeddeddoc);

						embeddedobject.append(TimeArray[i], realtimelist);
						coll.insert(embeddedobject);

					}

				}
				response.sendRedirect("times.jsp");
			}

		}
		
		//same as the line 102

		else if (Day.equalsIgnoreCase("Friday")) {

			MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
			DB db = mongoClient.getDB("TTD");
		

			DBCollection coll = db.getCollection("FridayHistory");
			

			int cou = (int) db.getCollection("FridayHistory").count();
			
		
			//same as the line 118
		
			if (cou > 0) {

				for (int ID = 0; ID < 24; ID++) {

					List<BasicDBObject> realtimelist = new ArrayList<BasicDBObject>();
					int i = ID;

					DBObject find = new BasicDBObject("ID", ID);

					DBObject push = new BasicDBObject("$push", new BasicDBObject(ID + ":00",
							new BasicDBObject("Date", Date).append("VQC1_No_of_Pilgrims", vqc1pilgrims[i])
									.append("VQC2_No_of_Pilgrims", vqc2pilgrims[i])
									.append("VQC1_Est_DarshanTime", vqc1darshantime[i])
									.append("VQC2_Est_DarshanTime", vqc2darshantime[i])
									.append("VQC1_RealTime", vqc1realtime[i]).append("VQC2_RealTime", vqc2realtime[i])

					)

					);

					db.getCollection("FridayHistory").update(find, push);

				}
			
				response.sendRedirect("times.jsp");
			}

			//same as the line 147
			else {

			
				////////////////////////
				for (int ID = 0; ID < 24; ID++) {

					for (int i = 0; i < vqc2realtimelength; i++) {

						BasicDBObject embeddedobject = new BasicDBObject();
						/// no documents .......
						embeddedobject.append("ID", ID);

					

						List<BasicDBObject> realtimelist = new ArrayList<BasicDBObject>();
						BasicDBObject embeddeddoc = new BasicDBObject("Date", Date)
								.append("VQC1_No_of_Pilgrims", vqc1pilgrims[i])
								.append("VQC2_No_of_Pilgrims", vqc2pilgrims[i])
								.append("VQC1_Est_DarshanTime", vqc1darshantime[i])
								.append("VQC2_Est_DarshanTime", vqc2darshantime[i])
								.append("VQC1_RealTime", vqc1realtime[i]).append("VQC2_RealTime", vqc2realtime[i]);
						realtimelist.add(embeddeddoc);

						embeddedobject.append(TimeArray[i], realtimelist);
						coll.insert(embeddedobject);

					}

				}
				response.sendRedirect("times.jsp");
			}
		}
		
		//same as the line 102

		else if (Day.equalsIgnoreCase("Saturday")) {

			MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));

			DB db = mongoClient.getDB("TTD");
		

			DBCollection coll = db.getCollection("SaturdayHistory");
			

			int cou = (int) db.getCollection("SaturdayHistory").count();
		
			//same as the line 118
		
			if (cou > 0) {

				for (int ID = 0; ID < 24; ID++) {

					List<BasicDBObject> realtimelist = new ArrayList<BasicDBObject>();
					int i = ID;

					DBObject find = new BasicDBObject("ID", ID);

					DBObject push = new BasicDBObject("$push", new BasicDBObject(ID + ":00",
							new BasicDBObject("Date", Date).append("VQC1_No_of_Pilgrims", vqc1pilgrims[i])
									.append("VQC2_No_of_Pilgrims", vqc2pilgrims[i])
									.append("VQC1_Est_DarshanTime", vqc1darshantime[i])
									.append("VQC2_Est_DarshanTime", vqc2darshantime[i])
									.append("VQC1_RealTime", vqc1realtime[i]).append("VQC2_RealTime", vqc2realtime[i])

					)

					);

					db.getCollection("SaturdayHistory").update(find, push);

				}
				response.sendRedirect("times.jsp");
			
			}
		
			//same as the line 147
			else {

				////////////////////////
				for (int ID = 0; ID < 24; ID++) {

					for (int i = 0; i < vqc2realtimelength; i++) {

						BasicDBObject embeddedobject = new BasicDBObject();
						/// no documents .......
						embeddedobject.append("ID", ID);

						

						List<BasicDBObject> realtimelist = new ArrayList<BasicDBObject>();
						BasicDBObject embeddeddoc = new BasicDBObject("Date", Date)
								.append("VQC1_No_of_Pilgrims", vqc1pilgrims[i])
								.append("VQC2_No_of_Pilgrims", vqc2pilgrims[i])
								.append("VQC1_Est_DarshanTime", vqc1darshantime[i])
								.append("VQC2_Est_DarshanTime", vqc2darshantime[i])
								.append("VQC1_RealTime", vqc1realtime[i]).append("VQC2_RealTime", vqc2realtime[i]);
						realtimelist.add(embeddeddoc);

						embeddedobject.append(TimeArray[i], realtimelist);
						coll.insert(embeddedobject);

					}

				}
				response.sendRedirect("times.jsp");
			}

		}
		//same as the line 102
		else if (Day.equalsIgnoreCase("Sunday")) {

			MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
			DB db = mongoClient.getDB("TTD");
			

			DBCollection coll = db.getCollection("SundayHistory");
			

			int cou = (int) db.getCollection("SundayHistory").count();
		
			//same as the line 118
			
			if (cou > 0) {

				for (int ID = 0; ID < 24; ID++) {

					List<BasicDBObject> realtimelist = new ArrayList<BasicDBObject>();
					int i = ID;

					DBObject find = new BasicDBObject("ID", ID);

					DBObject push = new BasicDBObject("$push", new BasicDBObject(ID + ":00",
							new BasicDBObject("Date", Date).append("VQC1_No_of_Pilgrims", vqc1pilgrims[i])
									.append("VQC2_No_of_Pilgrims", vqc2pilgrims[i])
									.append("VQC1_Est_DarshanTime", vqc1darshantime[i])
									.append("VQC2_Est_DarshanTime", vqc2darshantime[i])
									.append("VQC1_RealTime", vqc1realtime[i]).append("VQC2_RealTime", vqc2realtime[i])

					)

					);

					db.getCollection("SundayHistory").update(find, push);

				}
				
				response.sendRedirect("times.jsp");
			} 
		
			//same as the line 147
			else {

				
				////////////////////////
				for (int ID = 0; ID < 24; ID++) {

					for (int i = 0; i < vqc2realtimelength; i++) {

						BasicDBObject embeddedobject = new BasicDBObject();
						/// no documents .......
						embeddedobject.append("ID", ID);

				

						List<BasicDBObject> realtimelist = new ArrayList<BasicDBObject>();
						BasicDBObject embeddeddoc = new BasicDBObject("Date", Date)
								.append("VQC1_No_of_Pilgrims", vqc1pilgrims[i])
								.append("VQC2_No_of_Pilgrims", vqc2pilgrims[i])
								.append("VQC1_Est_DarshanTime", vqc1darshantime[i])
								.append("VQC2_Est_DarshanTime", vqc2darshantime[i])
								.append("VQC1_RealTime", vqc1realtime[i]).append("VQC2_RealTime", vqc2realtime[i]);
						realtimelist.add(embeddeddoc);

						embeddedobject.append(TimeArray[i], realtimelist);
						coll.insert(embeddedobject);

					}

				}
			}

			response.sendRedirect("times.jsp");
		}

	}

}
