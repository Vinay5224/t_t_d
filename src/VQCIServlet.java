
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * Servlet implementation class VQCIServlet
 */
@WebServlet("/VQCIServlet")
public class VQCIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static int VQC1TueSlotTimeCount, VQC1TueSlotValueCount;
	static int VQC1MonSlotTimeCount, VQC1MonSlotValueCount;
	static int VQC1WedSlotTimeCount, VQC1WedSlotValueCount;
	static int VQC1ThuSlotTimeCount, VQC1ThuSlotValueCount;
	static int VQC1FriSlotTimeCount, VQC1FriSlotValueCount;
	static int VQC1SatSlotTimeCount, VQC1SatSlotValueCount;
	static int VQC1SunSlotTimeCount, VQC1SunSlotValueCount;

	static String UIEnteringTime, UICurrentDay, UICurrentDate;
	static int PeopleEnteredIntoCompartment, numberOfEnteredPeople;
	static Integer darshanTimeInMinutes, remainingSlotCapacity;
	static String darshanTimeInHrMin, VQC1exacttime, temp = "00:00", tog = "0";

	static String VQCIValue = null;

	ArrayList<Double> VQCIReturnedValues = new ArrayList<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public VQCIServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// protected void doGet(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
	// // TODO Auto-generated method stub
	// response.getWriter().append("Served at:
	// ").append(request.getContextPath());
	//
	// }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		RequestDispatcher dispatcher = request.getRequestDispatcher("DarshanTimeEstimation.jsp");

		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String VQCIValue = request.getParameter("vqc1");

		PeopleEnteredIntoCompartment = Integer.parseInt(VQCIValue.trim());

		UIEnteringTime = request.getParameter("time");

		UICurrentDay = request.getParameter("day").trim();

		UICurrentDate = request.getParameter("date");

		/////////////// VQCITuesday/////////////////

		if (UICurrentDay.equalsIgnoreCase("Tuesday")) {
			System.out.println("VQCITuesday class");
			VQC1Tuesday VQCItue = new VQC1Tuesday();

			try {

				if (UIEnteringTime.equalsIgnoreCase("0:00")) {

					VQCIReturnedValues = VQCItue.main(new String[] { VQCIValue, UIEnteringTime, "0", "0" });

					darshanTimeInMinutes = Integer.valueOf(VQCIReturnedValues.get(2).intValue());

					numberOfEnteredPeople = Integer.parseInt(VQCIValue);

					NumberFormat formatter = new DecimalFormat("00.#");

					if (VQCIReturnedValues.get(5) == 0.0) {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ formatter.format(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);

					} else {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ Integer.valueOf(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);
					}

					darshanTimeInHrMin = Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
							+ formatter.format(VQCIReturnedValues.get(5).intValue());
					remainingSlotCapacity = Integer.valueOf(VQCIReturnedValues.get(1).intValue());

					SimpleDateFormat format = new SimpleDateFormat("HH:mm");

					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);

					long diff2 = date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();

					temp = darshanTimeInHrMin;

					if ((diff2 >= 0) && (tog == "0")) {
						VQC1exacttime = darshanTimeInHrMin + "-Tuesday";

					} else {
						VQC1exacttime = darshanTimeInHrMin + "-Wednesday";

						tog = "1";
					}

				} else {
					VQCIReturnedValues = VQCItue.main(new String[] { VQCIValue, UIEnteringTime,
							String.valueOf(VQCIReturnedValues.get(0)), String.valueOf(VQCIReturnedValues.get(1)) });

					darshanTimeInMinutes = Integer.valueOf(VQCIReturnedValues.get(2).intValue());

					numberOfEnteredPeople = Integer.parseInt(VQCIValue);

					NumberFormat formatter = new DecimalFormat("00.#");

					if (VQCIReturnedValues.get(5) == 0.0) {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ formatter.format(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);

					} else {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ Integer.valueOf(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);
					}
					darshanTimeInHrMin = Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
							+ formatter.format(VQCIReturnedValues.get(5).intValue());
					remainingSlotCapacity = Integer.valueOf(VQCIReturnedValues.get(1).intValue());
					SimpleDateFormat format = new SimpleDateFormat("HH:mm");

					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					long diff2 = date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();

					temp = darshanTimeInHrMin;

					if ((diff2 >= 0) && (tog == "0")) {
						VQC1exacttime = darshanTimeInHrMin + "-Tuesday";

					} else {
						VQC1exacttime = darshanTimeInHrMin + "-Wednesday";

						tog = "1";
					}

				}
				///////////// DB connect/////////////////

				MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
				MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
				// Now connect to your databases
				DB db = mongoClient.getDB("TTD");

				DBCollection coll = db.getCollection("Homepage");

				BasicDBObject doc = new BasicDBObject("Time", UIEnteringTime).append("Date", UICurrentDate)
						.append("VQC1_No_of_Pilgrims", numberOfEnteredPeople).append("VQC1_Exact_time", VQC1exacttime)
						.append("Remainingslot1", remainingSlotCapacity)
						.append("VQC1_Est_DarshanTime", darshanTimeInMinutes);

				BasicDBObject searchquery = new BasicDBObject("Date",
						UICurrentDate /* request.getParameter("time").trim() */).append("Time",
								request.getParameter("time").trim());

				doc.append("VQC1_No_of_Pilgrims", numberOfEnteredPeople).append("VQC1_Exact_time", VQC1exacttime)
						.append("Remainingslot1", remainingSlotCapacity)
						.append("VQC1_Est_DarshanTime", darshanTimeInMinutes);

				coll.update(searchquery, new BasicDBObject("$set", doc), true, false);

			} catch (NumberFormatException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		} else if (UICurrentDay.equalsIgnoreCase("Wednesday")) {
			System.out.println("VQCIWednesday class");
			VQCIWednesday VQC1wed = new VQCIWednesday();
			System.out.println("Wednesday class");
			try {

				if (UIEnteringTime.equalsIgnoreCase("0:00")) {

					VQCIReturnedValues = VQC1wed.main(new String[] { VQCIValue, UIEnteringTime, "0", "0" });

					darshanTimeInMinutes = Integer.valueOf(VQCIReturnedValues.get(2).intValue());

					numberOfEnteredPeople = Integer.parseInt(VQCIValue);

					NumberFormat formatter = new DecimalFormat("00.#");

					if (VQCIReturnedValues.get(5) == 0.0) {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ formatter.format(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);

					} else {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ Integer.valueOf(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);
					}

					darshanTimeInHrMin = Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
							+ formatter.format(VQCIReturnedValues.get(5).intValue());
					remainingSlotCapacity = Integer.valueOf(VQCIReturnedValues.get(1).intValue());

					SimpleDateFormat format = new SimpleDateFormat("HH:mm");

					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);

					long diff2 = date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();

					temp = darshanTimeInHrMin;

					if ((diff2 >= 0) && (tog == "0")) {
						VQC1exacttime = darshanTimeInHrMin + "-Wednesday";

					} else {
						VQC1exacttime = darshanTimeInHrMin + "-Thursday";

						tog = "1";
					}

				} else {
					VQCIReturnedValues = VQC1wed.main(new String[] { VQCIValue, UIEnteringTime,
							String.valueOf(VQCIReturnedValues.get(0)), String.valueOf(VQCIReturnedValues.get(1)) });

					darshanTimeInMinutes = Integer.valueOf(VQCIReturnedValues.get(2).intValue());

					numberOfEnteredPeople = Integer.parseInt(VQCIValue);

					NumberFormat formatter = new DecimalFormat("00.#");

					if (VQCIReturnedValues.get(5) == 0.0) {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ formatter.format(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);

					} else {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ Integer.valueOf(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);
					}
					darshanTimeInHrMin = Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
							+ formatter.format(VQCIReturnedValues.get(5).intValue());
					remainingSlotCapacity = Integer.valueOf(VQCIReturnedValues.get(1).intValue());
					SimpleDateFormat format = new SimpleDateFormat("HH:mm");

					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					long diff2 = date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();

					temp = darshanTimeInHrMin;

					if ((diff2 >= 0) && (tog == "0")) {
						VQC1exacttime = darshanTimeInHrMin + "-Wednesday";

					} else {
						VQC1exacttime = darshanTimeInHrMin + "-Thursday";

						tog = "1";
					}

				}
				///////////// DB connect/////////////////
				MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
				MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
				// Now connect to your databases
				DB db = mongoClient.getDB("TTD");

				DBCollection coll = db.getCollection("Homepage");
				System.out.println("Collection Signing selected successfully");

				BasicDBObject doc = new BasicDBObject("Time", UIEnteringTime).append("Date", UICurrentDate)
						.append("VQC1_No_of_Pilgrims", numberOfEnteredPeople).append("VQC1_Exact_time", VQC1exacttime)
						.append("Remainingslot1", remainingSlotCapacity)
						.append("VQC1_Est_DarshanTime", darshanTimeInMinutes);

				BasicDBObject searchquery = new BasicDBObject("Date",
						UICurrentDate /* request.getParameter("time").trim() */).append("Time",
								request.getParameter("time").trim());

				doc.append("VQC1_No_of_Pilgrims", numberOfEnteredPeople).append("VQC1_Exact_time", VQC1exacttime)
						.append("Remainingslot1", remainingSlotCapacity)
						.append("VQC1_Est_DarshanTime", darshanTimeInMinutes);

				coll.update(searchquery, new BasicDBObject("$set", doc), true, false);

			} catch (NumberFormatException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		} else if (UICurrentDay.equalsIgnoreCase("Thursday")) {

			/*double exe = VQCIThursday.exem;
			if (exe == 27.07) {

				String slotover = "slotover";
				response.getWriter().write(slotover);

			} else {*/

				System.out.println("VQCIThursday class");
				VQCIThursday VQC1thu = new VQCIThursday();

				try {

					if (UIEnteringTime.equalsIgnoreCase("0:00")) {

						VQCIReturnedValues = VQC1thu.main(new String[] { VQCIValue, UIEnteringTime, "0", "0" });

						darshanTimeInMinutes = Integer.valueOf(VQCIReturnedValues.get(2).intValue());

						numberOfEnteredPeople = Integer.parseInt(VQCIValue);

						NumberFormat formatter = new DecimalFormat("00.#");

						if (VQCIReturnedValues.get(5) == 0.0) {

							List<String> list1 = Arrays.asList(
									Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
									Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
									Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
									Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
											+ formatter.format(VQCIReturnedValues.get(5).intValue()) + "",
									(VQCIReturnedValues.get(2).intValue() + "min" + "/")
											+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

							String result1 = String.join("@", list1);

							response.getWriter().write(result1);

						} else {

							List<String> list1 = Arrays.asList(
									Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
									Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
									Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
									Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
											+ Integer.valueOf(VQCIReturnedValues.get(5).intValue()) + "",
									(VQCIReturnedValues.get(2).intValue() + "min" + "/")
											+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

							String result1 = String.join("@", list1);

							response.getWriter().write(result1);
						}

						darshanTimeInHrMin = Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
								+ formatter.format(VQCIReturnedValues.get(5).intValue());
						remainingSlotCapacity = Integer.valueOf(VQCIReturnedValues.get(1).intValue());

						SimpleDateFormat format = new SimpleDateFormat("HH:mm");

						Date date1 = format.parse(darshanTimeInHrMin);
						Date date2 = format.parse(temp);

						long diff2 = date1.getTime() - date2.getTime();
						long diff = date1.getHours() - date2.getHours();
						int diff1 = date1.getMinutes() - date2.getMinutes();

						temp = darshanTimeInHrMin;

						if ((diff2 >= 0) && (tog == "0")) {
							VQC1exacttime = darshanTimeInHrMin + "-Thursday";

						} else {
							VQC1exacttime = darshanTimeInHrMin + "-Friday";

							tog = "1";
						}

					} else {
						VQCIReturnedValues = VQC1thu.main(new String[] { VQCIValue, UIEnteringTime,
								String.valueOf(VQCIReturnedValues.get(0)), String.valueOf(VQCIReturnedValues.get(1)) });

						darshanTimeInMinutes = Integer.valueOf(VQCIReturnedValues.get(2).intValue());

						numberOfEnteredPeople = Integer.parseInt(VQCIValue);

						NumberFormat formatter = new DecimalFormat("00.#");

						if (VQCIReturnedValues.get(5) == 0.0) {

							List<String> list1 = Arrays.asList(
									Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
									Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
									Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
									Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
											+ formatter.format(VQCIReturnedValues.get(5).intValue()) + "",
									(VQCIReturnedValues.get(2).intValue() + "min" + "/")
											+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

							String result1 = String.join("@", list1);

							response.getWriter().write(result1);

						} else {

							List<String> list1 = Arrays.asList(
									Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
									Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
									Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
									Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
											+ Integer.valueOf(VQCIReturnedValues.get(5).intValue()) + "",
									(VQCIReturnedValues.get(2).intValue() + "min" + "/")
											+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

							String result1 = String.join("@", list1);

							response.getWriter().write(result1);
						}
						darshanTimeInHrMin = Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
								+ formatter.format(VQCIReturnedValues.get(5).intValue());
						remainingSlotCapacity = Integer.valueOf(VQCIReturnedValues.get(1).intValue());
						SimpleDateFormat format = new SimpleDateFormat("HH:mm");

						Date date1 = format.parse(darshanTimeInHrMin);
						Date date2 = format.parse(temp);
						long diff2 = date1.getTime() - date2.getTime();
						long diff = date1.getHours() - date2.getHours();
						int diff1 = date1.getMinutes() - date2.getMinutes();

						temp = darshanTimeInHrMin;

						if ((diff2 >= 0) && (tog == "0")) {
							VQC1exacttime = darshanTimeInHrMin + "-Thursday";

						} else {
							VQC1exacttime = darshanTimeInHrMin + "-Friday";

							tog = "1";
						}

					}
					///////////// DB connect/////////////////
					MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
					MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
					// Now connect to your databases
					DB db = mongoClient.getDB("TTD");
					System.out.println("Connect to database successfully");

					DBCollection coll = db.getCollection("Homepage");
					System.out.println("Collection Signing selected successfully");

					BasicDBObject doc = new BasicDBObject("Time", UIEnteringTime).append("Date", UICurrentDate)
							.append("VQC1_No_of_Pilgrims", numberOfEnteredPeople)
							.append("VQC1_Exact_time", VQC1exacttime).append("Remainingslot1", remainingSlotCapacity)
							.append("VQC1_Est_DarshanTime", darshanTimeInMinutes);

					BasicDBObject searchquery = new BasicDBObject("Date", UICurrentDate).append("Time",
							request.getParameter("time").trim());

					doc.append("VQC1_No_of_Pilgrims", numberOfEnteredPeople).append("VQC1_Exact_time", VQC1exacttime)
							.append("Remainingslot1", remainingSlotCapacity)
							.append("VQC1_Est_DarshanTime", darshanTimeInMinutes);

					coll.update(searchquery, new BasicDBObject("$set", doc), true, false);

				} catch (NumberFormatException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			//}
		}

		else if (UICurrentDay.equalsIgnoreCase("Friday")) {
			System.out.println("VQCIFriday class");
			VQCIFriday VQCIfri = new VQCIFriday();

			try {
				if (UIEnteringTime.equalsIgnoreCase("0:00")) {

					VQCIReturnedValues = VQCIfri.main(new String[] { VQCIValue, UIEnteringTime, "0", "0" });

					darshanTimeInMinutes = Integer.valueOf(VQCIReturnedValues.get(2).intValue());

					numberOfEnteredPeople = Integer.parseInt(VQCIValue);

					NumberFormat formatter = new DecimalFormat("00.#");

					if (VQCIReturnedValues.get(5) == 0.0) {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ formatter.format(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);

					} else {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ Integer.valueOf(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);
					}

					darshanTimeInHrMin = Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
							+ formatter.format(VQCIReturnedValues.get(5).intValue());
					remainingSlotCapacity = Integer.valueOf(VQCIReturnedValues.get(1).intValue());

					SimpleDateFormat format = new SimpleDateFormat("HH:mm");

					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);

					long diff2 = date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();

					temp = darshanTimeInHrMin;

					if ((diff2 >= 0) && (tog == "0")) {
						VQC1exacttime = darshanTimeInHrMin + "-Friday";

					} else {
						VQC1exacttime = darshanTimeInHrMin + "-Saturday";

						tog = "1";
					}

				} else {
					VQCIReturnedValues = VQCIfri.main(new String[] { VQCIValue, UIEnteringTime,
							String.valueOf(VQCIReturnedValues.get(0)), String.valueOf(VQCIReturnedValues.get(1)) });

					darshanTimeInMinutes = Integer.valueOf(VQCIReturnedValues.get(2).intValue());

					numberOfEnteredPeople = Integer.parseInt(VQCIValue);

					NumberFormat formatter = new DecimalFormat("00.#");

					if (VQCIReturnedValues.get(5) == 0.0) {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ formatter.format(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);

					} else {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ Integer.valueOf(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);
					}
					darshanTimeInHrMin = Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
							+ formatter.format(VQCIReturnedValues.get(5).intValue());
					remainingSlotCapacity = Integer.valueOf(VQCIReturnedValues.get(1).intValue());
					SimpleDateFormat format = new SimpleDateFormat("HH:mm");

					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					long diff2 = date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();

					temp = darshanTimeInHrMin;

					if ((diff2 >= 0) && (tog == "0")) {
						VQC1exacttime = darshanTimeInHrMin + "-Friday";

					} else {
						VQC1exacttime = darshanTimeInHrMin + "-Saturday";

						tog = "1";
					}

				}
				///////////// DB connect/////////////////
				MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
				MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
				// Now connect to your databases
				DB db = mongoClient.getDB("TTD");
				System.out.println("Connect to database successfully");

				DBCollection coll = db.getCollection("Homepage");
				System.out.println("Collection Signing selected successfully");

				BasicDBObject doc = new BasicDBObject("Time", UIEnteringTime).append("Date", UICurrentDate)
						.append("VQC1_No_of_Pilgrims", numberOfEnteredPeople).append("VQC1_Exact_time", VQC1exacttime)
						.append("Remainingslot1", remainingSlotCapacity)
						.append("VQC1_Est_DarshanTime", darshanTimeInMinutes);

				BasicDBObject searchquery = new BasicDBObject("Date",
						UICurrentDate /* request.getParameter("time").trim() */).append("Time",
								request.getParameter("time").trim());

				doc.append("VQC1_No_of_Pilgrims", numberOfEnteredPeople).append("VQC1_Exact_time", VQC1exacttime)
						.append("Remainingslot1", remainingSlotCapacity)
						.append("VQC1_Est_DarshanTime", darshanTimeInMinutes);

				coll.update(searchquery, new BasicDBObject("$set", doc), true, false);

			} catch (NumberFormatException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		} else if (UICurrentDay.equalsIgnoreCase("Saturday")) {
			System.out.println("VQCISaturday class");
			VQCISaturday VQCIsat = new VQCISaturday();

			try {
				if (UIEnteringTime.equalsIgnoreCase("0:00")) {

					VQCIReturnedValues = VQCIsat.main(new String[] { VQCIValue, UIEnteringTime, "0", "0" });

					darshanTimeInMinutes = Integer.valueOf(VQCIReturnedValues.get(2).intValue());

					numberOfEnteredPeople = Integer.parseInt(VQCIValue);

					NumberFormat formatter = new DecimalFormat("00.#");

					if (VQCIReturnedValues.get(5) == 0.0) {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ formatter.format(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);

					} else {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ Integer.valueOf(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);
					}

					darshanTimeInHrMin = Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
							+ formatter.format(VQCIReturnedValues.get(5).intValue());
					remainingSlotCapacity = Integer.valueOf(VQCIReturnedValues.get(1).intValue());

					SimpleDateFormat format = new SimpleDateFormat("HH:mm");

					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);

					long diff2 = date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();

					temp = darshanTimeInHrMin;

					if ((diff2 >= 0) && (tog == "0")) {
						VQC1exacttime = darshanTimeInHrMin + "-Saturday";

					} else {
						VQC1exacttime = darshanTimeInHrMin + "-Sunday";

						tog = "1";
					}

				} else {
					VQCIReturnedValues = VQCIsat.main(new String[] { VQCIValue, UIEnteringTime,
							String.valueOf(VQCIReturnedValues.get(0)), String.valueOf(VQCIReturnedValues.get(1)) });

					darshanTimeInMinutes = Integer.valueOf(VQCIReturnedValues.get(2).intValue());

					numberOfEnteredPeople = Integer.parseInt(VQCIValue);

					NumberFormat formatter = new DecimalFormat("00.#");

					if (VQCIReturnedValues.get(5) == 0.0) {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ formatter.format(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);

					} else {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ Integer.valueOf(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);
					}
					darshanTimeInHrMin = Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
							+ formatter.format(VQCIReturnedValues.get(5).intValue());
					remainingSlotCapacity = Integer.valueOf(VQCIReturnedValues.get(1).intValue());
					SimpleDateFormat format = new SimpleDateFormat("HH:mm");

					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					long diff2 = date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();

					temp = darshanTimeInHrMin;

					if ((diff2 >= 0) && (tog == "0")) {
						VQC1exacttime = darshanTimeInHrMin + "-Saturday";

					} else {
						VQC1exacttime = darshanTimeInHrMin + "-Sunday";

						tog = "1";
					}

				}
				///////////// DB connect/////////////////
				MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
				MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
				// Now connect to your databases
				DB db = mongoClient.getDB("TTD");
				System.out.println("Connect to database successfully");

				DBCollection coll = db.getCollection("Homepage");
				System.out.println("Collection Signing selected successfully");

				BasicDBObject doc = new BasicDBObject("Time", UIEnteringTime).append("Date", UICurrentDate)
						.append("VQC1_No_of_Pilgrims", numberOfEnteredPeople).append("VQC1_Exact_time", VQC1exacttime)
						.append("Remainingslot1", remainingSlotCapacity)
						.append("VQC1_Est_DarshanTime", darshanTimeInMinutes);

				BasicDBObject searchquery = new BasicDBObject("Date",
						UICurrentDate /* request.getParameter("time").trim() */).append("Time",
								request.getParameter("time").trim());

				doc.append("VQC1_No_of_Pilgrims", numberOfEnteredPeople).append("VQC1_Exact_time", VQC1exacttime)
						.append("Remainingslot1", remainingSlotCapacity)
						.append("VQC1_Est_DarshanTime", darshanTimeInMinutes);

				coll.update(searchquery, new BasicDBObject("$set", doc), true, false);

			} catch (NumberFormatException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		} else if (UICurrentDay.equalsIgnoreCase("Sunday")) {
			System.out.println("VQCISunday class");
			VQCISunday VQCIsun = new VQCISunday();

			try {
				if (UIEnteringTime.equalsIgnoreCase("0:00")) {

					VQCIReturnedValues = VQCIsun.main(new String[] { VQCIValue, UIEnteringTime, "0", "0" });
					System.out.println("0:00 returend RC" + VQCIReturnedValues.get(2));
					darshanTimeInMinutes = Integer.valueOf(VQCIReturnedValues.get(2).intValue());

					numberOfEnteredPeople = Integer.parseInt(VQCIValue);

					NumberFormat formatter = new DecimalFormat("00.#");

					if (VQCIReturnedValues.get(5) == 0.0) {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ formatter.format(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);

					} else {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ Integer.valueOf(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);
					}

					darshanTimeInHrMin = Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
							+ formatter.format(VQCIReturnedValues.get(5).intValue());
					remainingSlotCapacity = Integer.valueOf(VQCIReturnedValues.get(1).intValue());

					SimpleDateFormat format = new SimpleDateFormat("HH:mm");

					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);

					long diff2 = date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();

					temp = darshanTimeInHrMin;

					if ((diff2 >= 0) && (tog == "0")) {
						VQC1exacttime = darshanTimeInHrMin + "-Sunday";

					} else {
						VQC1exacttime = darshanTimeInHrMin + "-Monday";

						tog = "1";
					}

				} else {
					VQCIReturnedValues = VQCIsun.main(new String[] { VQCIValue, UIEnteringTime,
							String.valueOf(VQCIReturnedValues.get(0)), String.valueOf(VQCIReturnedValues.get(1)) });

					darshanTimeInMinutes = Integer.valueOf(VQCIReturnedValues.get(2).intValue());

					numberOfEnteredPeople = Integer.parseInt(VQCIValue);

					NumberFormat formatter = new DecimalFormat("00.#");

					if (VQCIReturnedValues.get(5) == 0.0) {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ formatter.format(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);

					} else {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ Integer.valueOf(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);
					}
					darshanTimeInHrMin = Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
							+ formatter.format(VQCIReturnedValues.get(5).intValue());
					remainingSlotCapacity = Integer.valueOf(VQCIReturnedValues.get(1).intValue());
					SimpleDateFormat format = new SimpleDateFormat("HH:mm");

					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					long diff2 = date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();

					temp = darshanTimeInHrMin;

					if ((diff2 >= 0) && (tog == "0")) {
						VQC1exacttime = darshanTimeInHrMin + "-Sunday";

					} else {
						VQC1exacttime = darshanTimeInHrMin + "-Monday";

						tog = "1";
					}

				}
				///////////// DB connect/////////////////
				MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
				MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
				// Now connect to your databases
				DB db = mongoClient.getDB("TTD");
				System.out.println("Connect to database successfully");

				DBCollection coll = db.getCollection("Homepage");
				System.out.println("Collection Signing selected successfully");

				BasicDBObject doc = new BasicDBObject("Time", UIEnteringTime).append("Date", UICurrentDate)
						.append("VQC1_No_of_Pilgrims", numberOfEnteredPeople).append("VQC1_Exact_time", VQC1exacttime)
						.append("Remainingslot1", remainingSlotCapacity)
						.append("VQC1_Est_DarshanTime", darshanTimeInMinutes);

				BasicDBObject searchquery = new BasicDBObject("Date",
						UICurrentDate /* request.getParameter("time").trim() */).append("Time",
								request.getParameter("time").trim());

				doc.append("VQC1_No_of_Pilgrims", numberOfEnteredPeople).append("VQC1_Exact_time", VQC1exacttime)
						.append("Remainingslot1", remainingSlotCapacity)
						.append("VQC1_Est_DarshanTime", darshanTimeInMinutes);

				coll.update(searchquery, new BasicDBObject("$set", doc), true, false);

			} catch (NumberFormatException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		} else if (UICurrentDay.equalsIgnoreCase("Monday")) {
			System.out.println("VQCIMonday class");
			VQCIMonday VQCImon = new VQCIMonday();
			System.out.println("monday class");

			try {
				if (UIEnteringTime.equalsIgnoreCase("0:00")) {

					VQCIReturnedValues = VQCImon.main(new String[] { VQCIValue, UIEnteringTime, "0", "0" });

					darshanTimeInMinutes = Integer.valueOf(VQCIReturnedValues.get(2).intValue());

					numberOfEnteredPeople = Integer.parseInt(VQCIValue);

					NumberFormat formatter = new DecimalFormat("00.#");

					if (VQCIReturnedValues.get(5) == 0.0) {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ formatter.format(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);

					} else {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ Integer.valueOf(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);
					}

					darshanTimeInHrMin = Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
							+ formatter.format(VQCIReturnedValues.get(5).intValue());
					remainingSlotCapacity = Integer.valueOf(VQCIReturnedValues.get(1).intValue());

					SimpleDateFormat format = new SimpleDateFormat("HH:mm");

					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);

					long diff2 = date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();

					temp = darshanTimeInHrMin;

					if ((diff2 >= 0) && (tog == "0")) {
						VQC1exacttime = darshanTimeInHrMin + "-Monday";

					} else {
						VQC1exacttime = darshanTimeInHrMin + "-Tuesday";

						tog = "1";
					}

				} else {
					VQCIReturnedValues = VQCImon.main(new String[] { VQCIValue, UIEnteringTime,
							String.valueOf(VQCIReturnedValues.get(0)), String.valueOf(VQCIReturnedValues.get(1)) });
					darshanTimeInMinutes = Integer.valueOf(VQCIReturnedValues.get(2).intValue());
					numberOfEnteredPeople = Integer.parseInt(VQCIValue);

					NumberFormat formatter = new DecimalFormat("00.#");
					if (VQCIReturnedValues.get(5) == 0.0) {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ formatter.format(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);

					} else {

						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIReturnedValues.get(0).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(6).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(1).intValue()) + "",
								Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
										+ Integer.valueOf(VQCIReturnedValues.get(5).intValue()) + "",
								(VQCIReturnedValues.get(2).intValue() + "min" + "/")
										+ Integer.valueOf(VQCIReturnedValues.get(3).intValue()) + "hrs");

						String result1 = String.join("@", list1);

						response.getWriter().write(result1);
					}
					darshanTimeInHrMin = Integer.valueOf(VQCIReturnedValues.get(4).intValue()) + ":"
							+ formatter.format(VQCIReturnedValues.get(5).intValue());
					remainingSlotCapacity = Integer.valueOf(VQCIReturnedValues.get(1).intValue());
					SimpleDateFormat format = new SimpleDateFormat("HH:mm");

					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					long diff2 = date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();

					temp = darshanTimeInHrMin;

					if ((diff2 >= 0) && (tog == "0")) {
						VQC1exacttime = darshanTimeInHrMin + "-Monday";

					} else {
						VQC1exacttime = darshanTimeInHrMin + "-Tuesday";

						tog = "1";
					}

				}
				///////////// DB connect/////////////////
				MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
				MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
				// Now connect to your databases
				DB db = mongoClient.getDB("TTD");
				System.out.println("Connect to database successfully");

				DBCollection coll = db.getCollection("Homepage");
				System.out.println("Collection Signing selected successfully");

				BasicDBObject doc = new BasicDBObject("Time", UIEnteringTime).append("Date", UICurrentDate)
						.append("VQC1_No_of_Pilgrims", numberOfEnteredPeople).append("VQC1_Exact_time", VQC1exacttime)
						.append("Remainingslot1", remainingSlotCapacity)
						.append("VQC1_Est_DarshanTime", darshanTimeInMinutes);

				BasicDBObject searchquery = new BasicDBObject("Date", UICurrentDate).append("Time",
						request.getParameter("time").trim());

				doc.append("VQC1_No_of_Pilgrims", numberOfEnteredPeople).append("VQC1_Exact_time", VQC1exacttime)
						.append("Remainingslot1", remainingSlotCapacity)
						.append("VQC1_Est_DarshanTime", darshanTimeInMinutes);

				coll.update(searchquery, new BasicDBObject("$set", doc), true, false);

			} catch (NumberFormatException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}

	}

}
