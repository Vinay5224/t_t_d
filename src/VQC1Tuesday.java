
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//import javax.websocket.RemoteEndpoint.Basic;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * Description: This Calculates the Estimated Darshan time(in minutes and hours)
 * for the people entered into compartment at particular time, also gives
 * information about number of people left in the compartment,remaining capacity
 * of the slot,Number of people considered for Darshan and returns the output to
 * the VQC1Servlet.
 * 
 * @param currentdate
 *            the current date from the VQC1Servlet.java
 * @param PeopleEnteringTime
 *            the people entered time into the compartment from the
 *            VQC1Servlet.java
 * @param Date
 *            the current date
 * @param VQC1ExactTimeFromDB
 *            the VQC1 exact time of the last document value from the MongoDB
 *            (Homepage Collection)
 * @param DBLastDocumentTime
 *            the Time from VQC1ExactTimeFromDB
 * @param DBLastDocumentDay
 *            the Day from VQC1ExactTimeFromDB
 * @param currentday
 *            the current day from the VQC1Servlet.java
 * @param DBToTiming
 *            the To Time from MongoDB (TuesdayCollection)
 * @param TuesdayToTiming
 *            the To Time from MongoDB (TuesdayCollection)
 * @param DocumentCountOfMondayCollection
 *            the Number of documents in Tuesday collection(MongoDB)
 * @param DocumentCountOfWednesdaycollection
 *            the Number of documents in Wednesday collection(MongoDB)
 * @param temp1
 *            the temporary variable initializing with zero
 * @param temp2
 *            the temporary variable initializing with zero
 * @param SumOfTueWedDocumentCount
 *            the total number of documents in Tuesday and Wednesday
 *            collection(MongoDB)
 * @param TuesdayDocumentid
 *            the Document ID from Tuesday Collection(MongoDB)
 * @param TuesdayCollCount
 *            the count of Tuesday Collection(MongoDB)
 * @param TuesdayCollectionCount
 *            the count of Tuesday Collection(MongoDB)
 * 
 * 
 * @author G.MuniKumari
 */

public class VQC1Tuesday {

	static String currentdate, PeopleEnteringTime, Date, VQC1ExactTimeFromDB, DBLastDocumentTime, DBLastDocumentDay,
			currentday, DBToTiming, TuesdayToTiming;
	static int DocumentCountOfTuesdayCollection = 0, DocumentCountOfWednesdaycollection = 0, temp1, temp2,
			SumOfTueWedDocumentCount, TuesdayDocumentid, TuesdayCollCount;
	static long TuesdayCollectionCount;

	/**
	 * @param args
	 * @return ArrayList
	 * @throws NumberFormatException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<Double> main(String[] args) throws NumberFormatException, IOException, ParseException {
		// TODO Auto-generated method stub

		int NumberOfPeopleEnteredIntoCompartment = VQCIServlet.PeopleEnteredIntoCompartment;
		System.out.println(NumberOfPeopleEnteredIntoCompartment);

		String EnteringTimeOfCompartment = args[1];
		double RemainingPeopleIntheCompartment1 = Double.parseDouble(args[2]),
				RemainingcapacityOftheSlot1 = Double.parseDouble(args[3]);

		ArrayList<String> slotTimings1Tue = new ArrayList<>();
		ArrayList<Integer> slotCapacity1Tue = new ArrayList<>();
		ArrayList<String> slotTimings1Wed = new ArrayList<>();

		String Entering_time[] = { EnteringTimeOfCompartment };

		currentdate = VQCIServlet.UICurrentDate;
		currentday = VQCIServlet.UICurrentDay;
		PeopleEnteringTime = VQCIServlet.UIEnteringTime;

		System.out.println("Dd" + Date + "date::" + currentdate + currentday);

		// creating the MonogoClinet and connecting to the Database
		MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
		// Now connect to your databases
		DB db = mongoClient.getDB("TTD");
		System.out.println("Connect to database successfully");

		// Now connect to your collection (Homepage)
		DBCollection HomepageCollection = db.getCollection("Homepage");
		System.out.println("Collection  Homepage selected successfully");

		// Check if the PeopleEnteringTime is equals to the "0:00", then
		// Retrieve the last document of VQC1_Exact_time from the Homepage
		// Collection.

		if (PeopleEnteringTime.equalsIgnoreCase("0:00")) {

			DBCursor cursor3 = HomepageCollection.find().sort(new BasicDBObject("_id", -1)).limit(1);
	

			
			/*
			 * Cursor Method: Returns true if the cursor has documents and can
			 * be iterated. Holds the documents in the Object then assiging to
			 * the map
			 */
			while (cursor3.hasNext()) {
				DBObject result = cursor3.next();
				Map resultmap = result.toMap();

				// Retrieving the VQC1 Exact Time from the Mongodb and casting
				// it into the String
				VQC1ExactTimeFromDB = (String) resultmap.get("VQC1_Exact_time");
				System.out.println("lastdocument::" + VQC1ExactTimeFromDB);

				/*
				 * Checking the VQC1ExactTimeFromDB is equals to null, then
				 * retrieving the VQC1_Exact_time from last second document from
				 * the Homepage collection.
				 */
				if (VQC1ExactTimeFromDB == null) {
					DBCursor cursor4 = HomepageCollection.find().sort(new BasicDBObject("_id", -1)).limit(2);
					
					// Cursor Method (refer to line number 134)
					while (cursor4.hasNext()) {
						DBObject result1 = cursor4.next();
						Map resultmap1 = result1.toMap();

						// Retrieving the VQC1 Exact Time from the Mongodb and
						// casting it into the String
						VQC1ExactTimeFromDB = (String) resultmap1.get("VQC1_Exact_time");
						
					}
				}

				/*
				 * Check VQC1ExactTimeFromDB is not equals to null, then split
				 * VQC1ExactTimeFromDB by using "-" Index[0] represents time of
				 * the last document i.e., DBLastDocumentTime Index[1]
				 * represents day of the last document i.e., DBLastDocumentTime
				 */
				if (!(VQC1ExactTimeFromDB == null)) {
					String[] Cdate = VQC1ExactTimeFromDB.split("-");
					DBLastDocumentTime = Cdate[0];
					DBLastDocumentDay = Cdate[1];
				}
			}

		}

		temp1 = DocumentCountOfTuesdayCollection;
		temp1 = 0;
		DocumentCountOfTuesdayCollection = temp1;
		

		temp2 = DocumentCountOfWednesdaycollection;
		temp2 = 0;
		DocumentCountOfWednesdaycollection = temp2;
		

		DBCollection TuesdayCollection = db.getCollection("Tuesday");
		System.out.println("Collection Tuesday selected successfully");

		

		TuesdayCollectionCount = TuesdayCollection.count();

		// parsing the long to integer
		TuesdayCollCount = (int) TuesdayCollectionCount;

		/*
		 * If the currentday is equals to the DBLastDocumentDay, then change
		 * DBLastDocumentTime to the Timeformat (HH:mm) then retrieve the
		 * TuesdayToTiming and TuesdayDocumentid from the TuesdayCollection and
		 * then incrementing the TuesdayDocumentid for the TuesdayToTiming.
		 */
		if (currentday.equals(DBLastDocumentDay)) {
			
			System.out.println("Day::" + currentday + "--------" + "Cday::" + DBLastDocumentDay);

			SimpleDateFormat curFormater = new SimpleDateFormat("H:m");
			Date dateObj = curFormater.parse(DBLastDocumentTime);
			SimpleDateFormat postFormater = new SimpleDateFormat("HH:mm");
			String newDateStr = postFormater.format(dateObj);

			BasicDBObject que = new BasicDBObject();
			que.put("To", newDateStr);
			DBCursor cursor = TuesdayCollection.find(que);
			
			// Cursor Method (refer to line number 134)
			while (cursor.hasNext()) {
				
				DBObject result = cursor.next();
				Map resultmap = result.toMap();
				TuesdayToTiming = (String) resultmap.get("To");
				TuesdayDocumentid = (Integer) resultmap.get("ID");

				TuesdayDocumentid++;

				for (int LastDocumentID = TuesdayDocumentid; LastDocumentID < TuesdayCollCount; LastDocumentID++) {
					BasicDBObject ques2 = new BasicDBObject("ID", TuesdayDocumentid);
					ques2.put("ID", TuesdayDocumentid);
					DBCursor cursorv = TuesdayCollection.find(ques2);
					// Cursor Method (refer to line number 134)
					while (cursorv.hasNext()) {
						DBObject resultv = cursorv.next();
						Map resultmapv = resultv.toMap();

						DBToTiming = (String) resultmapv.get("To");
						TuesdayDocumentid++;
						
						/*
						 * Get VQC1 Max capacity value from the Tuesday
						 * collection, parse it to a string and assign the
						 * values to DBVQC1MaxCapacity Get the VQC1_Max_Capacity
						 * values which are not equal to null and those values
						 * are added to the slotCapacity1Tue(arraylist) and also
						 * added DBToTiming to the slotTimings1Tue(arraylist)
						 * 
						 */
						String DBVQC1MaxCapacity = (String) resultmapv.get("VQC1_Max_Capacity");

						if (!DBVQC1MaxCapacity.equals("")) {
							int dbVQC1MaxCapacity = Integer.parseInt((String) resultmapv.get("VQC1_Max_Capacity"));
							slotCapacity1Tue.add(dbVQC1MaxCapacity);

							slotTimings1Tue.add(DBToTiming);
							DocumentCountOfTuesdayCollection++;
							}
					}
				}
			}
		}

		/*
		 * Check if DBLastDocumentDay is equals to Monday then get the "To"
		 * timing and "VQC1_Max_Capacity" from the Tuesday collection and also
		 * check if DBVQC1MaxCapacity is not equals to null then get the
		 * VQC1_Max_Capacity values which are not equal to null and those values
		 * are added to the slotCapacity1Tue(arraylist) and also added ToTiming
		 * to the slotTimings1Tue(arraylist).
		 */

		else if (DBLastDocumentDay.equals("Monday")) {

			// Cursor Method (refer to line number 134)
			DBCursor cursor = TuesdayCollection.find();
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				Map resultmap = result.toMap();

				String ToTiming = (String) resultmap.get("To");
				String DBVQC1MaxCapacity = (String) resultmap.get("VQC1_Max_Capacity");
				if (!DBVQC1MaxCapacity.equals("")) {
					int dbVQC1MaxCapacity = Integer.parseInt((String) resultmap.get("VQC1_Max_Capacity"));
					slotCapacity1Tue.add(dbVQC1MaxCapacity);
					slotTimings1Tue.add(ToTiming);
					DocumentCountOfTuesdayCollection++;
				}
			}
		}
		/*
		 * Get "To" timing and "VQC1_Max_Capacity" value from the Wednesday
		 * collection, parse it to a string and assign the values to
		 * DBVQC1MaxCapacity Get the VQC1_Max_Capacity values which are not
		 * equal to null and those values are added to the
		 * slotCapacity1Tue(arraylist) and also added ToTiming to the
		 * slotTimings1Tue(arraylist)
		 */
		DBCollection WednesdayCollection = db.getCollection("Wednesday");
		System.out.println("Collection  Wednesday selected successfully");

		DBCursor cursor1 = WednesdayCollection.find();
		// Cursor Method (refer to line number 134)
		while (cursor1.hasNext()) {
			DBObject result = cursor1.next();
			Map resultmap = result.toMap();

			String ToTiming = (String) resultmap.get("To");
			String DBVQC1MaxCapacity = (String) resultmap.get("VQC1_Max_Capacity");
			if (!DBVQC1MaxCapacity.equals("")) {
				int dbVQC1MaxCapacity = Integer.parseInt((String) resultmap.get("VQC1_Max_Capacity"));
				slotCapacity1Tue.add(dbVQC1MaxCapacity);
				slotTimings1Wed.add(ToTiming);

				DocumentCountOfWednesdaycollection++;
			}

		}
		

		ArrayList<Double> Res1 = new ArrayList<>();

		// Declaring to the estimatedtime Method
		Res1 = estimatedtime(Entering_time, slotTimings1Tue, slotTimings1Wed, slotCapacity1Tue,
				RemainingPeopleIntheCompartment1, RemainingcapacityOftheSlot1, NumberOfPeopleEnteredIntoCompartment);
		return Res1;
	}

	/**
	 * Description: This method describes that converting the slot timings into
	 * hours and minutes and also check if the entering time of people entered
	 * into compartment is less than the slot time(i.e., Darshan available
	 * time).If it satisfies the case then only Calculates the Estimated Darshan
	 * time(in minutes and hours) for the people entered into compartment at
	 * particular time, also gives information about number of people left in
	 * the compartment,remaining capacity of the slot,Number of people
	 * considered for Darshan and returns the output to the VQC1Servlet.
	 * 
	 * 
	 * @param Enteringtime
	 *            the Entering time of people into the compartment
	 * @param slotTimingsTue
	 *            the entire To Timings from Tuesday collection(MongoDB)
	 * @param slotTimingsWed
	 *            the entire To Timings from Wednesday collection(MongoDB)
	 * @param slotcapacity
	 *            the Capacity of the slot from Tuesday and Wednesday
	 *            collection(MongoDB) in ascending order
	 * @param RemainingPeopleInCompartment1
	 *            the Number of people left in the compartment
	 * @param RemainingCapacityOfSlot1
	 *            the remaining capacity of the slot
	 * @param NumberOfPeopleEntered
	 *            the number of people entered into the compartment
	 * @return VQC1TuesResult(ArrayList)
	 * @throws IOException
	 * @throws ParseException
	 * @author Munikumari
	 */
	public static ArrayList<Double> estimatedtime(String[] Enteringtime, ArrayList<String> slotTimingsTue,
			ArrayList<String> slotTimingsWed, ArrayList<Integer> slotcapacity, double RemainingPeopleInCompartment1,
			double RemainingCapacityOfSlot1, int NumberOfPeopleEntered) throws IOException, ParseException {

		double PeopleConsideredForDarshanVQC1 = 0;
		long DarshanTimeInHoursandMinutes = 0;
		long DarshanTimeInHours = 0;
		long DarshanTimeInMinutes = 0;
		double PeopleLeftInCompartment;

		ArrayList<Double> VQC1TuesResult = new ArrayList<>();

		ArrayList<Date> TuesdaySlotTime = new ArrayList<>();
		ArrayList<Date> WednesdaySlotTime = new ArrayList<>();

		ArrayList<String> slotTimings = new ArrayList<>();
		slotTimings.addAll(slotTimingsTue);
		slotTimings.addAll(slotTimingsWed);

		ArrayList<Date> enteredTime = new ArrayList<>();

		// slotTimingsTue of Tuesday converted into time format and add timings
		// to the TuesdaySlotTime
		for (String h : slotTimingsTue) {

			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date1 = format.parse(h);
			TuesdaySlotTime.add(date1);

		}
		// slotTimingsWed of Wednesday converted into time format and add
		// timings to the WednesdaySlotTime
		for (String h : slotTimingsWed) {

			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);
			Calendar c = Calendar.getInstance();
			c.setTime(date2);
			c.add(Calendar.DATE, 1); // number of days to add
			Date currentDatePlusOne = c.getTime();
			WednesdaySlotTime.add(currentDatePlusOne);
		}

		//add WednesdaySlotTime(timings) to TuesdaySlotTime
		TuesdaySlotTime.addAll(WednesdaySlotTime);

		// Enteringtime is converted into time format and add timings to the
		// enteredTime
		for (String h : Enteringtime) {
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);
			enteredTime.add(date2);

		}

		if ((TuesdaySlotTime.get(0).compareTo(enteredTime.get(0))) > 0) {

		}
		if ((enteredTime.get(0).compareTo(TuesdaySlotTime.get(0))) > 0) {
		}

		// SumOfTueWedDocumentCount is the sum of the documents count for
		// Tuesday and Wednesday collection
		SumOfTueWedDocumentCount = 0;
		SumOfTueWedDocumentCount = DocumentCountOfTuesdayCollection + DocumentCountOfWednesdaycollection;
		
		int TuesdaySlotValue = VQCIServlet.VQC1TueSlotValueCount;
		for (int EnteringPeopleTime = 0; EnteringPeopleTime < Enteringtime.length; EnteringPeopleTime++) {

			for (int TuesdaySlotTimings = VQCIServlet.VQC1TueSlotTimeCount; TuesdaySlotTimings < slotTimings
					.size(); TuesdaySlotTimings++) {

				if (EnteringPeopleTime > 0) {
					VQCIServlet.VQC1TueSlotTimeCount = TuesdaySlotTimings;
					VQCIServlet.VQC1TueSlotValueCount = TuesdaySlotValue;
					break;

				}
				// assign number of people entered into the compartment to
				// entered people count
				double EnteredPeopleCount = NumberOfPeopleEntered;

				if (EnteringPeopleTime > 20) {
					System.out.println("Day completed");
					break;
				}

				// check the condition if the RemainingCapacityOfSlot1 is
				// greater than zero and also check the entering time should be
				// greater than the slot time of people
				// then increment slot timings ,slot value and initialize
				// RemainingCapacityOfSlot is zero
				if ((RemainingCapacityOfSlot1 > 0)
						&& ((enteredTime.get(0).compareTo(TuesdaySlotTime.get(TuesdaySlotTimings))) >= 0)) {
					TuesdaySlotTimings++;
					TuesdaySlotValue++;
					RemainingCapacityOfSlot1 = 0;
				}

				// check if the entering time of people into the compartment is
				// less than the slot time(i.e., Darshan available time)
				if ((TuesdaySlotTime.get(TuesdaySlotTimings).compareTo(enteredTime.get(0))) >= 0) {
					
					// check if TuesdaySlotValue(index) greater than
					// SumOfTueWedDocumentCount(document count in collection)
					if (TuesdaySlotValue > SumOfTueWedDocumentCount - 1) {

						/*
						 * check if the number of people entered into
						 * compartment is greater than the slot
						 * capacity(i.e.,Maximum people count for Darshan from
						 * DB) if it satisfies the above condition then check
						 * RemainingCapacityOfSlot is less than zero then assign
						 * RemainingCapacityOfSlot to zero else
						 * RemainingCapacityOfSlot is greater than zero then
						 * assign RemainingCapacityOfSlot to slot capacity.
						 */

						if (EnteredPeopleCount > slotcapacity.get(TuesdaySlotValue)) {
							
							if (RemainingCapacityOfSlot1 <= 0) {
								RemainingCapacityOfSlot1 = 0;
								System.out.println("");
							}
							if (RemainingCapacityOfSlot1 > 0) {
								slotcapacity.set(TuesdaySlotValue, (int) RemainingCapacityOfSlot1);
							}

							RemainingPeopleInCompartment1 = RemainingPeopleInCompartment1 + EnteredPeopleCount
									- (slotcapacity.get(TuesdaySlotValue));
							
							//calculating the Darshan Time In HoursandMinutes
							DarshanTimeInHoursandMinutes = (TuesdaySlotTime.get(TuesdaySlotTimings).getTime()
									- enteredTime.get(0).getTime()) + 3600000;

							DarshanTimeInHours = TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
							DarshanTimeInMinutes = TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
							
							RemainingCapacityOfSlot1 = ((RemainingPeopleInCompartment1
									+ slotcapacity.get(TuesdaySlotValue)) - EnteredPeopleCount);
							if (RemainingCapacityOfSlot1 > 0) {
								
							}

							/*
							 * check if RemainingPeopleInCompartment is greater
							 * than zero and also RemainingCapacityOfSlot is
							 * less than or equal to zero then calculate
							 * PeopleConsideredForDarshanVQC1 in that expression
							 * or else follow the below expression
							 */

							if ((RemainingPeopleInCompartment1 > 0) && (RemainingCapacityOfSlot1 <= 0)) {
								
								PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
										+ (EnteredPeopleCount - RemainingPeopleInCompartment1);
						

							}

							else {

								PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
										+ (EnteredPeopleCount - RemainingCapacityOfSlot1);
								
							}

						}

						/*
						 * check if the number of people entered into
						 * compartment is less than the slot
						 * capacity(i.e.,Maximum people count for Darshan from
						 * DB) if it satisfies the above condition then check
						 * RemainingCapacityOfSlot is less than zero then
						 * initialize RemainingCapacityOfSlot to zero else
						 * RemainingCapacityOfSlot is greater than zero then
						 * assign RemainingCapacityOfSlot to slot capacity.
						 */
						if (EnteredPeopleCount <= slotcapacity.get(TuesdaySlotValue)) {

					
							if (RemainingCapacityOfSlot1 <= 0) {
								RemainingCapacityOfSlot1 = 0;
							
							}

							if (RemainingCapacityOfSlot1 > 0) {

								slotcapacity.set(TuesdaySlotValue, (int) RemainingCapacityOfSlot1);

							}

		

							//calculating the Darshan Time In HoursandMinutes
							DarshanTimeInHours = TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
							DarshanTimeInMinutes = TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
							

							DarshanTimeInHoursandMinutes = (TuesdaySlotTime.get(TuesdaySlotTimings).getTime()
									- enteredTime.get(0).getTime()) + 3600000;
						
							DarshanTimeInHours = TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
							DarshanTimeInMinutes = TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
							

							if (RemainingPeopleInCompartment1 > 0) {
							
								RemainingCapacityOfSlot1 = ((slotcapacity.get(TuesdaySlotValue))
										- RemainingPeopleInCompartment1);
							} else {
							
								RemainingCapacityOfSlot1 = ((slotcapacity.get(TuesdaySlotValue)) - EnteredPeopleCount);
								
							}
							RemainingPeopleInCompartment1 = EnteredPeopleCount - slotcapacity.get(TuesdaySlotValue);
							

							if (RemainingCapacityOfSlot1 > 0) {
							
							}

							/*
							 * check if RemainingPeopleInCompartment is greater
							 * than zero and also RemainingCapacityOfSlot is
							 * less than or equal to zero then calculate
							 * PeopleConsideredForDarshanVQC1 in that expression
							 * or else follow the below expression
							 */
							if ((RemainingPeopleInCompartment1 > 0) && (RemainingCapacityOfSlot1 <= 0)) {
								
								PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
										+ (EnteredPeopleCount - RemainingPeopleInCompartment1);
							

							}

							else {
							
								PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
										+ (slotcapacity.get(TuesdaySlotValue) - RemainingCapacityOfSlot1);
							
							}

							// assign RemainingCapacityOfSlot to slotcapacity
							slotcapacity.set(TuesdaySlotValue, (int) RemainingCapacityOfSlot1);

							if (RemainingCapacityOfSlot1 <= 0) {

								RemainingCapacityOfSlot1 = 0;

							}
						}

						/*
						 * Check if the people left in the compartment is
						 * greater than the slot capacity if it satisfies the
						 * condition then check RemainingCapacityOfSlot is less
						 * than zero then initialize RemainingCapacityOfSlot to
						 * zero.Then assign RemainingPeopleInCompartment to
						 * PeopleLeftInCompartment and also calculate
						 * RemainingPeopleInCompartment
						 */
						while (RemainingPeopleInCompartment1 >= slotcapacity.get(TuesdaySlotValue)) {
						
							if (RemainingCapacityOfSlot1 <= 0) {
								RemainingCapacityOfSlot1 = 0;

							}
							
							PeopleLeftInCompartment = RemainingPeopleInCompartment1;
							RemainingPeopleInCompartment1 = RemainingPeopleInCompartment1
									- slotcapacity.get(TuesdaySlotValue);

							// check if TuesdaySlotValue(index) greater than
							// SumOfTueWedDocumentCount(document count in
							// collection)
							if (TuesdaySlotValue > SumOfTueWedDocumentCount - 1) {
								
								break;
							}

						

							//calculating the Darshan Time In HoursandMinutes
							DarshanTimeInHoursandMinutes = (TuesdaySlotTime.get(TuesdaySlotTimings).getTime()
									- enteredTime.get(0).getTime()) + 3600000;

							DarshanTimeInHours = TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
							DarshanTimeInMinutes = TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
			

							RemainingCapacityOfSlot1 = ((RemainingPeopleInCompartment1
									+ slotcapacity.get(TuesdaySlotValue)) - EnteredPeopleCount);

							if (RemainingCapacityOfSlot1 > 0) {
						
							}

							/*
							 * check if RemainingPeopleInCompartment is greater
							 * than zero and also RemainingCapacityOfSlot is
							 * less than or equal to zero then calculate
							 * PeopleConsideredForDarshanVQC1 in that expression
							 * or else follow the below expression
							 */

							if ((RemainingPeopleInCompartment1 > 0) && (RemainingCapacityOfSlot1 <= 0)) {
								
								PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
										+ (PeopleLeftInCompartment - RemainingPeopleInCompartment1);
							

							}

							else {
								if (RemainingCapacityOfSlot1 < 0) {
									RemainingCapacityOfSlot1 = 0;
								
								}
								
								PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
										+ (PeopleLeftInCompartment - RemainingCapacityOfSlot1);
							
							}

						}

						/*
						 * check if RemainingPeopleInCompartment1 is greater
						 * than zero and also check RemainingPeopleInCompartment 
						 * is less than slotcapacity,if it satisfies both conditions 
						 * then calculate DarshanTimeInHoursandMinutes,RemainingCapacityOfSlot
						 * and RemainingPeopleInCompartment
						 */
						if ((RemainingPeopleInCompartment1 > 0)
								&& (RemainingPeopleInCompartment1 < slotcapacity.get(TuesdaySlotValue))) {
					

							

							//calculating the Darshan Time In HoursandMinutes
							DarshanTimeInHoursandMinutes = (TuesdaySlotTime.get(TuesdaySlotTimings).getTime()
									- enteredTime.get(0).getTime()) + 3600000;
							DarshanTimeInHours = TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
							DarshanTimeInMinutes = TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
							
							RemainingCapacityOfSlot1 = ((slotcapacity.get(TuesdaySlotValue))
									- RemainingPeopleInCompartment1);
							RemainingPeopleInCompartment1 = RemainingPeopleInCompartment1
									- slotcapacity.get(TuesdaySlotValue);
							

							if (RemainingCapacityOfSlot1 > 0) {
								
							}

							/*
							 * check if RemainingPeopleInCompartment is greater
							 * than zero and also RemainingCapacityOfSlot is
							 * less than or equal to zero then calculate
							 * PeopleConsideredForDarshanVQC1 in that expression
							 * or else follow the below expression
							 */
							if ((RemainingPeopleInCompartment1 > 0) && (RemainingCapacityOfSlot1 <= 0)) {
								
								if (RemainingPeopleInCompartment1 < 0)

								{
									RemainingPeopleInCompartment1 = 0;
									
								}
								PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
										+ RemainingPeopleInCompartment1;
								
							}

							else {
								
								PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
										+ (slotcapacity.get(TuesdaySlotValue)) - (RemainingCapacityOfSlot1);
								
							}

						}

						// check if the RemainingPeopleInCompartment is less
						// than zero then initialize zero to
						// RemainingPeopleInCompartment
						if (RemainingPeopleInCompartment1 < 0) {
							RemainingPeopleInCompartment1 = 0;
							

						}
						
						break;
					}

					/*
					 * check if the number of people entered into compartment is
					 * greater than the slot capacity(i.e.,Maximum people count
					 * for Darshan from DB) if it satisfies the above condition
					 * then check RemainingCapacityOfSlot is less than zero then
					 * assign RemainingCapacityOfSlot to zero else
					 * RemainingCapacityOfSlot is greater than zero then assign
					 * RemainingCapacityOfSlot to slot capacity.
					 */

					if (EnteredPeopleCount > slotcapacity.get(TuesdaySlotValue)) {
						

						if (RemainingCapacityOfSlot1 <= 0) {
							RemainingCapacityOfSlot1 = 0;
							
						}
						if (RemainingCapacityOfSlot1 > 0) {
							slotcapacity.set(TuesdaySlotValue, (int) RemainingCapacityOfSlot1);

						}

						

						// RemainingPeopleInCompartment1 is to find out the
						// people left in the compartment
						RemainingPeopleInCompartment1 = RemainingPeopleInCompartment1 + EnteredPeopleCount
								- (slotcapacity.get(TuesdaySlotValue));
						

						//calculating the Darshan Time In HoursandMinutes
						DarshanTimeInHoursandMinutes = (TuesdaySlotTime.get(TuesdaySlotTimings).getTime()
								- enteredTime.get(0).getTime()) + 3600000;
						DarshanTimeInHours = TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
						DarshanTimeInMinutes = TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
						

						
						// RemainingCapacityOfSlot is to find the remaining
						// capacity of the slot
						RemainingCapacityOfSlot1 = ((RemainingPeopleInCompartment1 + slotcapacity.get(TuesdaySlotValue))
								- EnteredPeopleCount);
						if (RemainingCapacityOfSlot1 > 0) {
							
						}

						/*
						 * check if RemainingPeopleInCompartment is greater than
						 * zero and also RemainingCapacityOfSlot is less than or
						 * equal to zero then calculate
						 * PeopleConsideredForDarshanVQC1 in that expression or
						 * else follow the below expression
						 */

						if ((RemainingPeopleInCompartment1 > 0) && (RemainingCapacityOfSlot1 <= 0)) {
						
							PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
									+ (EnteredPeopleCount - RemainingPeopleInCompartment1);
							
						}

						else {

							
							PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
									+ (EnteredPeopleCount - RemainingCapacityOfSlot1);
							
						}

						/*
						 * check if RemainingCapacityOfSlot1 is less than or
						 * equal to zero then initialize the
						 * RemainingCapacityOfSlot1 to zero and increment the
						 * TuesdaySlotTimings and TuesdaySlotValue.
						 */
						if (RemainingCapacityOfSlot1 <= 0) {

							RemainingCapacityOfSlot1 = 0;
							TuesdaySlotTimings++;
							TuesdaySlotValue++;
							
						}

					}

					/*
					 * check if the number of people entered into compartment is
					 * less than the slot capacity(i.e.,Maximum people count for
					 * Darshan from DB) if it satisfies the above condition then
					 * check RemainingCapacityOfSlot is less than zero then
					 * initialize RemainingCapacityOfSlot to zero else
					 * RemainingCapacityOfSlot is greater than zero then assign
					 * RemainingCapacityOfSlot to slot capacity.
					 */
					if (EnteredPeopleCount <= slotcapacity.get(TuesdaySlotValue)) {

						

						if (RemainingCapacityOfSlot1 <= 0) {
							RemainingCapacityOfSlot1 = 0;
							
						}

						
						if (RemainingCapacityOfSlot1 > 0) {
							slotcapacity.set(TuesdaySlotValue, (int) RemainingCapacityOfSlot1);
					
						}

						;

						//calculating the Darshan Time In HoursandMinutes
						DarshanTimeInHoursandMinutes = (TuesdaySlotTime.get(TuesdaySlotTimings).getTime()
								- enteredTime.get(0).getTime()) + 3600000;
						DarshanTimeInHours = TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
						DarshanTimeInMinutes = TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
				
						// check if the People left in the Compartment is
						// greater than zero calculate RemainingCapacityOfSlot
						// as below expression else follow the
						// RemainingCapacityOfSlot in else condition

						if (RemainingPeopleInCompartment1 > 0) {
							
							RemainingCapacityOfSlot1 = ((slotcapacity.get(TuesdaySlotValue))
									- RemainingPeopleInCompartment1);
						} else {
							
							RemainingCapacityOfSlot1 = ((slotcapacity.get(TuesdaySlotValue)) - EnteredPeopleCount);
						}
						RemainingPeopleInCompartment1 = EnteredPeopleCount - slotcapacity.get(TuesdaySlotValue);
						
						if (RemainingCapacityOfSlot1 > 0) {
							
						}

						/*
						 * check if RemainingPeopleInCompartment is greater than
						 * zero and also RemainingCapacityOfSlot is less than or
						 * equal to zero then calculate
						 * PeopleConsideredForDarshanVQC1 in that expression or
						 * else follow the below expression
						 */
						if ((RemainingPeopleInCompartment1 > 0) && (RemainingCapacityOfSlot1 <= 0)) {
							
							PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
									+ (EnteredPeopleCount - RemainingPeopleInCompartment1);
					

						}

						else {
							
							PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
									+ (slotcapacity.get(TuesdaySlotValue) - RemainingCapacityOfSlot1);
							
						}

						// assign RemainingCapacityOfSlot1 value to slotcapacity
						slotcapacity.set(TuesdaySlotValue, (int) RemainingCapacityOfSlot1);

						/*
						 * check if RemainingCapacityOfSlot1 is less than or
						 * equal to zero then initialize the
						 * RemainingCapacityOfSlot1 to zero and increment the
						 * TuesdaySlotTimings and TuesdaySlotValue.
						 */
						if (RemainingCapacityOfSlot1 <= 0) {

							RemainingCapacityOfSlot1 = 0;
							TuesdaySlotTimings++;
							TuesdaySlotValue++;
							
						}
					}

					/*
					 * Check if the people left in the compartment is greater
					 * than the slot capacity if it satisfies the condition then
					 * check RemainingCapacityOfSlot is less than zero then
					 * initialize RemainingCapacityOfSlot to zero.Then assign
					 * RemainingPeopleInCompartment to PeopleLeftInCompartment
					 * and also calculate RemainingPeopleInCompartment
					 */
					while (RemainingPeopleInCompartment1 >= slotcapacity.get(TuesdaySlotValue)) {
						
						if (RemainingCapacityOfSlot1 <= 0) {
							RemainingCapacityOfSlot1 = 0;

						}
						
						PeopleLeftInCompartment = RemainingPeopleInCompartment1;
						RemainingPeopleInCompartment1 = RemainingPeopleInCompartment1
								- slotcapacity.get(TuesdaySlotValue);

						

						//calculating the Darshan Time In HoursandMinutes
						DarshanTimeInHoursandMinutes = (TuesdaySlotTime.get(TuesdaySlotTimings).getTime()
								- enteredTime.get(0).getTime()) + 3600000;
						DarshanTimeInHours = TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
						DarshanTimeInMinutes = TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
						
						RemainingCapacityOfSlot1 = ((RemainingPeopleInCompartment1 + slotcapacity.get(TuesdaySlotValue))
								- EnteredPeopleCount);

						if (RemainingCapacityOfSlot1 > 0) {
							
						}

						/*
						 * check if RemainingPeopleInCompartment is greater than
						 * zero and also RemainingCapacityOfSlot is less than or
						 * equal to zero then calculate
						 * PeopleConsideredForDarshanVQC1 in that expression or
						 * else follow the below expression
						 */
						if ((RemainingPeopleInCompartment1 > 0) && (RemainingCapacityOfSlot1 <= 0)) {
							
							PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
									+ (PeopleLeftInCompartment - RemainingPeopleInCompartment1);
							
						}

						else {
							if (RemainingCapacityOfSlot1 < 0) {
								RemainingCapacityOfSlot1 = 0;
								
							}
							
							PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
									+ (PeopleLeftInCompartment - RemainingCapacityOfSlot1);
							
						}

						TuesdaySlotTimings++;
						TuesdaySlotValue++;

						// check if TuesdaySlotValue(index) is greater than
						// SumOfTueWedDocumentCount(Document count in a Tuesday
						// and Wednesday Collection)
						if (TuesdaySlotValue > SumOfTueWedDocumentCount - 1) {
							
							break;
						}
					}

					/*
					 * check if RemainingPeopleInCompartment1 is greater than
					 * zero then calculate
					 * DarshanTimeInHoursandMinutes,RemainingCapacityOfSlot and
					 * RemainingPeopleInCompartment
					 */
					if ((RemainingPeopleInCompartment1 > 0)
							&& (RemainingPeopleInCompartment1 < slotcapacity.get(TuesdaySlotValue))) {
						
						
						//calculating the Darshan Time In HoursandMinutes
						DarshanTimeInHoursandMinutes = (TuesdaySlotTime.get(TuesdaySlotTimings).getTime()
								- enteredTime.get(0).getTime()) + 3600000;
						DarshanTimeInHours = TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
						DarshanTimeInMinutes = TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
						

						RemainingCapacityOfSlot1 = ((slotcapacity.get(TuesdaySlotValue))
								- RemainingPeopleInCompartment1);
						RemainingPeopleInCompartment1 = RemainingPeopleInCompartment1
								- slotcapacity.get(TuesdaySlotValue);
						
						if (RemainingCapacityOfSlot1 > 0) {
							
						}

						/*
						 * check if RemainingPeopleInCompartment is greater than
						 * zero and also RemainingCapacityOfSlot is less than or
						 * equal to zero then calculate
						 * PeopleConsideredForDarshanVQC1 in that expression or
						 * else follow the below expression
						 */
						if ((RemainingPeopleInCompartment1 > 0) && (RemainingCapacityOfSlot1 <= 0)) {
						
							if (RemainingPeopleInCompartment1 < 0)

							{
								RemainingPeopleInCompartment1 = 0;
							
							}
							PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
									+ RemainingPeopleInCompartment1;
							

						}

						else {
							
							PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
									+ (slotcapacity.get(TuesdaySlotValue)) - (RemainingCapacityOfSlot1);
							
						}

					}

					// check if RemainingPeopleInCompartment is less than or
					// equal to zero then initialize the
					// RemainingCapacityOfSlot1 to zero

					if (RemainingPeopleInCompartment1 < 0)

					{
						RemainingPeopleInCompartment1 = 0;
						

					}

					EnteringPeopleTime++;
				}

				/*
				 * check if RemainingPeopleInCompartment is less than or equal
				 * to zero then initialize the RemainingCapacityOfSlot1 to zero
				 * and increment the TuesdaySlotTimings and TuesdaySlotValue.
				 */
				else {
					RemainingCapacityOfSlot1 = 0;
					TuesdaySlotTimings++;
					TuesdaySlotValue++;

					break;
				}
				TuesdaySlotTimings--;

			}

		}

		// output(returned values)
		VQC1TuesResult.add(RemainingPeopleInCompartment1);
		VQC1TuesResult.add(RemainingCapacityOfSlot1);
		VQC1TuesResult.add((double) TimeUnit.MILLISECONDS.toMinutes(DarshanTimeInHoursandMinutes));
		VQC1TuesResult.add((double) TimeUnit.MILLISECONDS.toHours(DarshanTimeInHoursandMinutes));
		VQC1TuesResult.add((double) DarshanTimeInHours);
		VQC1TuesResult.add((double) DarshanTimeInMinutes);
		VQC1TuesResult.add(PeopleConsideredForDarshanVQC1);
		return VQC1TuesResult;

	}

}
