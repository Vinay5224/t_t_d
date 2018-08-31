
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

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class VQCISunday {

	static ArrayList<String> Slot = new ArrayList<String>();

	static String str, str1, currentdate, PeopleEnteringTime, Date, VQC1ExactTimeFromDB, DBLastDocumentTime,
			DBLastDocumentDay, currentday, u, Remainingslot, DBToTiming, SundayToTiming;
	static int Count = 0, DocumentCountOfSundayCollection = 0, DocumentCountOfMondaycollection = 0, w, z,
			SumOfSunMonDocumentCount, SundayDocumentid, e, grt, SundayCollCount;
	static long SundayCollectionCount;

	public static ArrayList<Double> main(String[] args) throws NumberFormatException, IOException, ParseException {
		// TODO Auto-generated method stub

		int NumberOfPeopleEnteredIntoCompartment = VQCIServlet.PeopleEnteredIntoCompartment;
		String EnteringTimeOfCompartment = args[1];
		double RemainingPeopleIntheCompartment1 = Double.parseDouble(args[2]),
				RemainingcapacityOftheSlot1 = Double.parseDouble(args[3]);

		ArrayList<String> slotTimings1Sun = new ArrayList<>();
		ArrayList<Integer> slotCapacity1Sun = new ArrayList<>();

		ArrayList<String> slotTimings1Mon = new ArrayList<>();

		String Entering_time[] = { EnteringTimeOfCompartment };

		currentdate = VQCIServlet.UICurrentDate;
		currentday = VQCIServlet.UICurrentDay;
		PeopleEnteringTime = VQCIServlet.UIEnteringTime;

		System.out.println("Dd" + Date + "date::" + currentdate + currentday);
		MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));

		// Now connect to your databases
		DB db = mongoClient.getDB("TTD");
		System.out.println("Connect to database successfully");

		DBCollection HomepageCollection = db.getCollection("Homepage");
		System.out.println("Collection Homepage selected successfully");

		if (PeopleEnteringTime.equalsIgnoreCase("0:00")) {
			DBCursor cursor3 = HomepageCollection.find().sort(new BasicDBObject("_id", -1)).limit(1);
			while (cursor3.hasNext()) {
				DBObject result = cursor3.next();
				Map resultmap = result.toMap();

				VQC1ExactTimeFromDB = (String) resultmap.get("VQC1_Exact_time");
				System.out.println("last::" + VQC1ExactTimeFromDB);
				if (VQC1ExactTimeFromDB == null) {
					DBCursor cursor4 = HomepageCollection.find().sort(new BasicDBObject("_id", -1)).limit(2);
					System.out.println("lkjlj:::" + cursor3);
					while (cursor4.hasNext()) {
						DBObject result1 = cursor4.next();
						Map resultmap1 = result1.toMap();

						VQC1ExactTimeFromDB = (String) resultmap1.get("VQC1_Exact_time");
						System.out.println("last::" + VQC1ExactTimeFromDB);
					}
				}

				if (!(VQC1ExactTimeFromDB == null)) {
					String[] Cdate = VQC1ExactTimeFromDB.split("-");
					DBLastDocumentTime = Cdate[0];
					DBLastDocumentDay = Cdate[1];

					u = DBLastDocumentTime;
					System.out.println("erw::" + u);

				}
			}

		}

		w = DocumentCountOfSundayCollection;
		w = 0;
		DocumentCountOfSundayCollection = w;

		z = DocumentCountOfMondaycollection;
		z = 0;
		DocumentCountOfMondaycollection = z;

		DBCollection SundayCollection = db.getCollection("Sunday");
		System.out.println("Collection Sunday selected successfully");

		SundayCollectionCount = SundayCollection.count();

		SundayCollCount = (int) SundayCollectionCount;

		if (currentday.equals(DBLastDocumentDay)) {
			System.out.println("Day::" + currentday + "--------" + "Cday::" + DBLastDocumentDay);

			SimpleDateFormat curFormater = new SimpleDateFormat("H:m");
			Date dateObj = curFormater.parse(DBLastDocumentTime);
			SimpleDateFormat postFormater = new SimpleDateFormat("HH:mm");
			String newDateStr = postFormater.format(dateObj);

			BasicDBObject que = new BasicDBObject();
			que.put("To", newDateStr);
			DBCursor cursor = SundayCollection.find(que);

			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				Map resultmap = result.toMap();
				SundayToTiming = (String) resultmap.get("To");
				SundayDocumentid = (Integer) resultmap.get("ID");

				SundayDocumentid++;
				for (int LastDocumentID = SundayDocumentid; LastDocumentID < SundayCollCount; LastDocumentID++) {
					BasicDBObject ques2 = new BasicDBObject("ID", SundayDocumentid);
					ques2.put("ID", SundayDocumentid);
					DBCursor cursorv = SundayCollection.find(ques2);

					while (cursorv.hasNext()) {
						DBObject resultv = cursorv.next();
						Map resultmapv = resultv.toMap();

						DBToTiming = (String) resultmapv.get("To");
						SundayDocumentid++;

						String DBVQC1MaxCapacity = (String) resultmapv.get("VQC1_Max_Capacity");

						if (!DBVQC1MaxCapacity.equals("")) {
							int dbVQC1MaxCapacity = Integer.parseInt((String) resultmapv.get("VQC1_Max_Capacity"));
							slotCapacity1Sun.add(dbVQC1MaxCapacity);

							slotTimings1Sun.add(DBToTiming);
							DocumentCountOfSundayCollection++;
						}

					}
				}
			}

		}

		else if (DBLastDocumentDay.equals("Saturday")) {

			DBCursor cursor = SundayCollection.find();
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				Map resultmap = result.toMap();

				String ToTiming = (String) resultmap.get("To");
				String DBVQC1MaxCapacity = (String) resultmap.get("VQC1_Max_Capacity");
				if (!DBVQC1MaxCapacity.equals("")) {
					int dbVQC1MaxCapacity = Integer.parseInt((String) resultmap.get("VQC1_Max_Capacity"));
					slotCapacity1Sun.add(dbVQC1MaxCapacity);
					slotTimings1Sun.add(ToTiming);

					DocumentCountOfSundayCollection++;

				}
			}

		}

		DBCollection MondayCollection = db.getCollection("Monday");
		System.out.println("Collection Monday selected successfully");

		DBCursor cursor1 = MondayCollection.find();

		while (cursor1.hasNext()) {
			DBObject result = cursor1.next();
			Map resultmap = result.toMap();

			String ToTiming = (String) resultmap.get("To");
			String DBVQC1MaxCapacity = (String) resultmap.get("VQC1_Max_Capacity");
			if (!DBVQC1MaxCapacity.equals("")) {
				int dbVQC1MaxCapacity = Integer.parseInt((String) resultmap.get("VQC1_Max_Capacity"));
				slotCapacity1Sun.add(dbVQC1MaxCapacity);

				slotTimings1Mon.add(ToTiming);

				DocumentCountOfMondaycollection++;

			}
		}

		ArrayList<Double> ResSunI = new ArrayList<>();
		ResSunI = estimatedtime(Entering_time, slotTimings1Sun, slotTimings1Mon, slotCapacity1Sun,
				RemainingPeopleIntheCompartment1, RemainingcapacityOftheSlot1, NumberOfPeopleEnteredIntoCompartment);

		return ResSunI;
	}

	public static ArrayList<Double> estimatedtime(String[] Entering_time, ArrayList<String> slotTimingsSun,
			ArrayList<String> slotTimingsMon, ArrayList<Integer> slotcapacity,

			double RemainingPeopleInCompartment1, double RemainingCapacityOfSlot1, int NumberOfPeopleEntered)
			throws IOException, ParseException {

		double PeopleConsideredForDarshanVQC1 = 0;
		long DarshanTimeInHoursandMinutes = 0;
		long DarshanTimeInHours = 0;
		long DarshanTimeInMinutes = 0;
		double PeopleLeftInCompartment;

		int lent = 0;

		ArrayList<Double> VQC1SunResult = new ArrayList<>();
		ArrayList<Date> SundaySlotTime = new ArrayList<>();
		ArrayList<Date> MondaySlotTime = new ArrayList<>();
		ArrayList<String> slotTimings = new ArrayList<>();
		slotTimings.addAll(slotTimingsSun);
		slotTimings.addAll(slotTimingsMon);

		ArrayList<Date> enteredTime = new ArrayList<>();

		for (String h : slotTimingsSun) {

			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date1 = format.parse(h);
			SundaySlotTime.add(date1);

		}

		for (String h : slotTimingsMon) {

			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);
			Calendar c = Calendar.getInstance();
			c.setTime(date2);
			c.add(Calendar.DATE, 1); // number of days to add
			Date currentDatePlusOne = c.getTime();
			MondaySlotTime.add(currentDatePlusOne);
		}

		SundaySlotTime.addAll(MondaySlotTime);

		for (String h : Entering_time) {
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);
			enteredTime.add(date2);
		}

		SumOfSunMonDocumentCount = 0;
		SumOfSunMonDocumentCount = DocumentCountOfSundayCollection + DocumentCountOfMondaycollection;

		int SundaySlotValue = VQCIServlet.VQC1SunSlotValueCount;
		for (int EnteringPeopleTime = 0; EnteringPeopleTime < Entering_time.length; EnteringPeopleTime++) {

			for (int SundaySlotTimings = VQCIServlet.VQC1SunSlotTimeCount; SundaySlotTimings < slotTimings
					.size(); SundaySlotTimings++) {

				if (EnteringPeopleTime > 0) {
					VQCIServlet.VQC1SunSlotTimeCount = SundaySlotTimings;
					VQCIServlet.VQC1SunSlotValueCount = SundaySlotValue;
					break;

				}

				double EnteredPeopleCount = NumberOfPeopleEntered;

				if (EnteringPeopleTime > 20) {
					System.out.println("Day completed");
					break;
				}

				if ((RemainingCapacityOfSlot1 > 0)
						&& ((enteredTime.get(0).compareTo(SundaySlotTime.get(SundaySlotTimings))) >= 0)) {

					SundaySlotTimings++;
					SundaySlotValue++;
					RemainingCapacityOfSlot1 = 0;
				}

				if ((SundaySlotTime.get(SundaySlotTimings).compareTo(enteredTime.get(0))) >= 0) {

					if (SundaySlotValue > SumOfSunMonDocumentCount - 1) {
						System.out.println("Last slot");
						if (EnteredPeopleCount > slotcapacity.get(SundaySlotValue)) {
							if (RemainingCapacityOfSlot1 <= 0) {
								RemainingCapacityOfSlot1 = 0;

							}
							if (RemainingCapacityOfSlot1 > 0) {
								slotcapacity.set(SundaySlotValue, (int) RemainingCapacityOfSlot1);
							}

							RemainingPeopleInCompartment1 = RemainingPeopleInCompartment1 + EnteredPeopleCount
									- (slotcapacity.get(SundaySlotValue));

							DarshanTimeInHoursandMinutes = (SundaySlotTime.get(SundaySlotTimings).getTime()
									- enteredTime.get(0).getTime()) + 3600000;
							DarshanTimeInHours = SundaySlotTime.get(SundaySlotTimings).getHours();
							DarshanTimeInMinutes = SundaySlotTime.get(SundaySlotTimings).getMinutes();

							RemainingCapacityOfSlot1 = ((RemainingPeopleInCompartment1
									+ slotcapacity.get(SundaySlotValue)) - EnteredPeopleCount);
							if (RemainingCapacityOfSlot1 > 0) {

							}

							if ((RemainingPeopleInCompartment1 > 0) && (RemainingCapacityOfSlot1 <= 0)) {
								PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
										+ (EnteredPeopleCount - RemainingPeopleInCompartment1);

							}

							else {

								PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
										+ (EnteredPeopleCount - RemainingCapacityOfSlot1);

							}

						}
						if (EnteredPeopleCount <= slotcapacity.get(SundaySlotValue)) {

							if (RemainingCapacityOfSlot1 <= 0) {
								RemainingCapacityOfSlot1 = 0;

							}

							if (RemainingCapacityOfSlot1 > 0) {
								slotcapacity.set(SundaySlotValue, (int) RemainingCapacityOfSlot1);
								;
							}

							DarshanTimeInHoursandMinutes = (SundaySlotTime.get(SundaySlotTimings).getTime()
									- enteredTime.get(0).getTime()) + 3600000;
							DarshanTimeInHours = SundaySlotTime.get(SundaySlotTimings).getHours();
							DarshanTimeInMinutes = SundaySlotTime.get(SundaySlotTimings).getMinutes();

							if (RemainingPeopleInCompartment1 > 0) {
								RemainingCapacityOfSlot1 = ((slotcapacity.get(SundaySlotValue))
										- RemainingPeopleInCompartment1);
							} else {
								RemainingCapacityOfSlot1 = ((slotcapacity.get(SundaySlotValue)) - EnteredPeopleCount);
							}
							RemainingPeopleInCompartment1 = EnteredPeopleCount - slotcapacity.get(SundaySlotValue);

							if (RemainingCapacityOfSlot1 > 0) {

							}

							if ((RemainingPeopleInCompartment1 > 0) && (RemainingCapacityOfSlot1 <= 0)) {
								PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
										+ (EnteredPeopleCount - RemainingPeopleInCompartment1);

							}

							else {
								PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
										+ (slotcapacity.get(SundaySlotValue) - RemainingCapacityOfSlot1);

							}
							slotcapacity.set(SundaySlotValue, (int) RemainingCapacityOfSlot1);

							if (RemainingCapacityOfSlot1 <= 0) {

								RemainingCapacityOfSlot1 = 0;
							}
						}
						while (RemainingPeopleInCompartment1 >= slotcapacity.get(SundaySlotValue)) {

							if (RemainingCapacityOfSlot1 <= 0) {
								RemainingCapacityOfSlot1 = 0;

							}
							PeopleLeftInCompartment = RemainingPeopleInCompartment1;
							RemainingPeopleInCompartment1 = RemainingPeopleInCompartment1
									- slotcapacity.get(SundaySlotValue);

							if (SundaySlotValue > SumOfSunMonDocumentCount - 1) {
								System.out.println("slots are over");
								break;
							}

							DarshanTimeInHoursandMinutes = (SundaySlotTime.get(SundaySlotTimings).getTime()
									- enteredTime.get(0).getTime()) + 3600000;
							DarshanTimeInHours = SundaySlotTime.get(SundaySlotTimings).getHours();
							DarshanTimeInMinutes = SundaySlotTime.get(SundaySlotTimings).getMinutes();

							RemainingCapacityOfSlot1 = ((RemainingPeopleInCompartment1
									+ slotcapacity.get(SundaySlotValue)) - EnteredPeopleCount);

							if (RemainingCapacityOfSlot1 > 0) {

							}

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

						if ((RemainingPeopleInCompartment1 > 0)
								&& (RemainingPeopleInCompartment1 < slotcapacity.get(SundaySlotValue))) {

							DarshanTimeInHoursandMinutes = (SundaySlotTime.get(SundaySlotTimings).getTime()
									- enteredTime.get(0).getTime()) + 3600000;
							DarshanTimeInHours = SundaySlotTime.get(SundaySlotTimings).getHours();
							DarshanTimeInMinutes = SundaySlotTime.get(SundaySlotTimings).getMinutes();

							RemainingCapacityOfSlot1 = ((slotcapacity.get(SundaySlotValue))
									- RemainingPeopleInCompartment1);
							RemainingPeopleInCompartment1 = RemainingPeopleInCompartment1
									- slotcapacity.get(SundaySlotValue);

							if (RemainingCapacityOfSlot1 > 0) {

							}

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
										+ (slotcapacity.get(SundaySlotValue)) - (RemainingCapacityOfSlot1);

							}

						}
						if (RemainingPeopleInCompartment1 < 0) {
							RemainingPeopleInCompartment1 = 0;

						}
						System.out.println("slots are over");
						break;
					}

					if (EnteredPeopleCount > slotcapacity.get(SundaySlotValue)) {

						if (RemainingCapacityOfSlot1 <= 0) {
							RemainingCapacityOfSlot1 = 0;

						}
						if (RemainingCapacityOfSlot1 > 0) {
							slotcapacity.set(SundaySlotValue, (int) RemainingCapacityOfSlot1);
							;
						}

						RemainingPeopleInCompartment1 = RemainingPeopleInCompartment1 + EnteredPeopleCount
								- (slotcapacity.get(SundaySlotValue));

						DarshanTimeInHoursandMinutes = (SundaySlotTime.get(SundaySlotTimings).getTime()
								- enteredTime.get(0).getTime()) + 3600000;
						DarshanTimeInHours = SundaySlotTime.get(SundaySlotTimings).getHours();
						DarshanTimeInMinutes = SundaySlotTime.get(SundaySlotTimings).getMinutes();

						RemainingCapacityOfSlot1 = ((RemainingPeopleInCompartment1 + slotcapacity.get(SundaySlotValue))
								- EnteredPeopleCount);
						if (RemainingCapacityOfSlot1 > 0) {

						}

						if ((RemainingPeopleInCompartment1 > 0) && (RemainingCapacityOfSlot1 <= 0)) {
							PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
									+ (EnteredPeopleCount - RemainingPeopleInCompartment1);

						}

						else {

							PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
									+ (EnteredPeopleCount - RemainingCapacityOfSlot1);

						}

						if (RemainingCapacityOfSlot1 <= 0) {

							RemainingCapacityOfSlot1 = 0;
							SundaySlotTimings++;
							SundaySlotValue++;

						}

					}
					if (EnteredPeopleCount <= slotcapacity.get(SundaySlotValue)) {
						if (RemainingCapacityOfSlot1 <= 0) {
							RemainingCapacityOfSlot1 = 0;

						}

						if (RemainingCapacityOfSlot1 > 0) {
							slotcapacity.set(SundaySlotValue, (int) RemainingCapacityOfSlot1);
							;
						}

						DarshanTimeInHoursandMinutes = (SundaySlotTime.get(SundaySlotTimings).getTime()
								- enteredTime.get(0).getTime()) + 3600000;
						DarshanTimeInHours = SundaySlotTime.get(SundaySlotTimings).getHours();
						DarshanTimeInMinutes = SundaySlotTime.get(SundaySlotTimings).getMinutes();

						if (RemainingPeopleInCompartment1 > 0) {
							RemainingCapacityOfSlot1 = ((slotcapacity.get(SundaySlotValue))
									- RemainingPeopleInCompartment1);
						} else {
							RemainingCapacityOfSlot1 = ((slotcapacity.get(SundaySlotValue)) - EnteredPeopleCount);
						}
						RemainingPeopleInCompartment1 = EnteredPeopleCount - slotcapacity.get(SundaySlotValue);

						if (RemainingCapacityOfSlot1 > 0) {

						}

						if ((RemainingPeopleInCompartment1 > 0) && (RemainingCapacityOfSlot1 <= 0)) {
							PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
									+ (EnteredPeopleCount - RemainingPeopleInCompartment1);

						}

						else {
							PeopleConsideredForDarshanVQC1 = PeopleConsideredForDarshanVQC1
									+ (slotcapacity.get(SundaySlotValue) - RemainingCapacityOfSlot1);

						}

						slotcapacity.set(SundaySlotValue, (int) RemainingCapacityOfSlot1);
						;

						if (RemainingCapacityOfSlot1 <= 0) {

							RemainingCapacityOfSlot1 = 0;
							SundaySlotTimings++;
							SundaySlotValue++;

						}
					}
					while (RemainingPeopleInCompartment1 >= slotcapacity.get(SundaySlotValue)) {
						if (RemainingCapacityOfSlot1 <= 0) {
							RemainingCapacityOfSlot1 = 0;

						}
						PeopleLeftInCompartment = RemainingPeopleInCompartment1;
						RemainingPeopleInCompartment1 = RemainingPeopleInCompartment1
								- slotcapacity.get(SundaySlotValue);

						DarshanTimeInHoursandMinutes = (SundaySlotTime.get(SundaySlotTimings).getTime()
								- enteredTime.get(0).getTime()) + 3600000;
						DarshanTimeInHours = SundaySlotTime.get(SundaySlotTimings).getHours();
						DarshanTimeInMinutes = SundaySlotTime.get(SundaySlotTimings).getMinutes();

						RemainingCapacityOfSlot1 = ((RemainingPeopleInCompartment1 + slotcapacity.get(SundaySlotValue))
								- EnteredPeopleCount);

						if (RemainingCapacityOfSlot1 > 0) {

						}

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

						SundaySlotTimings++;
						SundaySlotValue++;

						if (SundaySlotValue > SumOfSunMonDocumentCount - 1) {
							System.out.println("slots are over");
							break;
						}

					}

					if ((RemainingPeopleInCompartment1 > 0)
							&& (RemainingPeopleInCompartment1 < slotcapacity.get(SundaySlotValue))) {

						DarshanTimeInHoursandMinutes = (SundaySlotTime.get(SundaySlotTimings).getTime()
								- enteredTime.get(0).getTime()) + 3600000;
						DarshanTimeInHours = SundaySlotTime.get(SundaySlotTimings).getHours();
						DarshanTimeInMinutes = SundaySlotTime.get(SundaySlotTimings).getMinutes();

						RemainingCapacityOfSlot1 = ((slotcapacity.get(SundaySlotValue))
								- RemainingPeopleInCompartment1);
						RemainingPeopleInCompartment1 = RemainingPeopleInCompartment1
								- slotcapacity.get(SundaySlotValue);

						if (RemainingCapacityOfSlot1 > 0) {

						}

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
									+ (slotcapacity.get(SundaySlotValue)) - (RemainingCapacityOfSlot1);

						}
					}
					if (RemainingPeopleInCompartment1 < 0)

					{
						RemainingPeopleInCompartment1 = 0;
					}

					EnteringPeopleTime++;
				} else {
					RemainingCapacityOfSlot1 = 0;
					SundaySlotTimings++;
					SundaySlotValue++;

					break;
				}
				SundaySlotTimings--;

			}
		}
		VQC1SunResult.add(RemainingPeopleInCompartment1);
		VQC1SunResult.add(RemainingCapacityOfSlot1);
		VQC1SunResult.add((double) TimeUnit.MILLISECONDS.toMinutes(DarshanTimeInHoursandMinutes));
		VQC1SunResult.add((double) TimeUnit.MILLISECONDS.toHours(DarshanTimeInHoursandMinutes));
		VQC1SunResult.add((double) DarshanTimeInHours);
		VQC1SunResult.add((double) DarshanTimeInMinutes);
		VQC1SunResult.add(PeopleConsideredForDarshanVQC1);
		return VQC1SunResult;
	}
}
