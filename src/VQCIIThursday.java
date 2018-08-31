
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

public class VQCIIThursday {

	static ArrayList<String> Slot = new ArrayList<String>();

	static String str, str1, currentdate, PeopleEnteringTime, Date, VQC2ExactTimeFromDB, DBLastDocumentTime,
			DBLastDocumentDay, currentday, u, Remainingslot, DBToTiming, ThursdayToTiming;
	static int Count = 0, DocumentCountOfThursdayCollection = 0, DocumentCountOfFridaycollection = 0, w, z,
			SumOfThuDocumentCount, ThursdayDocumentid, e, grt, ThursdayCollCount;
	static long ThursdayCollectionCount;

	public static ArrayList<Double> main(String[] args) throws NumberFormatException, IOException, ParseException {
		// TODO Auto-generated method stub

		int NumberOfPeopleEnteredIntoCompartment2 = VQCIIServlet.PeopleEnteredIntoCompartment2;
		String EnteringTimeOfCompartment2 = args[1];
		double RemainingPeopleIntheCompartment2 = Double.parseDouble(args[2]),
				RemainingcapacityOftheSlot2 = Double.parseDouble(args[3]);

		ArrayList<String> slotTimings1Thu = new ArrayList<>();
		ArrayList<Integer> slotCapacity1Thu = new ArrayList<>();

		ArrayList<String> slotTimings1Fri = new ArrayList<>();

		String Entering_time[] = { EnteringTimeOfCompartment2 };

		currentdate = VQCIIServlet.UICurrentDate;
		currentday = VQCIIServlet.UICurrentDay;
		PeopleEnteringTime = VQCIIServlet.UIEnteringTime;

		System.out.println("Dd" + Date + "date::" + currentdate + currentday);
		MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
		// Now connect to your databases
		DB db = mongoClient.getDB("TTD");

		DBCollection HomepageCollection = db.getCollection("Homepage");

		if (PeopleEnteringTime.equalsIgnoreCase("0:00")) {

			DBCursor cursor3 = HomepageCollection.find().sort(new BasicDBObject("_id", -1)).limit(1);

			while (cursor3.hasNext()) {
				DBObject result = cursor3.next();
				Map resultmap = result.toMap();

				VQC2ExactTimeFromDB = (String) resultmap.get("VQC2_Exact_time");

				if (VQC2ExactTimeFromDB == null) {
					DBCursor cursor4 = HomepageCollection.find().sort(new BasicDBObject("_id", -1)).limit(2);

					while (cursor4.hasNext()) {
						DBObject result1 = cursor4.next();
						Map resultmap1 = result1.toMap();

						VQC2ExactTimeFromDB = (String) resultmap1.get("VQC2_Exact_time");

					}
				}
				if (!(VQC2ExactTimeFromDB == null)) {
					String[] Cdate = VQC2ExactTimeFromDB.split("-");
					DBLastDocumentTime = Cdate[0];
					DBLastDocumentDay = Cdate[1];

				}
			}
		}

		w = DocumentCountOfThursdayCollection;
		w = 0;
		DocumentCountOfThursdayCollection = w;

		z = DocumentCountOfFridaycollection;
		z = 0;
		DocumentCountOfFridaycollection = z;

		DBCollection ThursdayCollection = db.getCollection("Thursday");

		ThursdayCollectionCount = ThursdayCollection.count();

		ThursdayCollCount = (int) ThursdayCollectionCount;
		if (currentday.equals(DBLastDocumentDay)) {

			SimpleDateFormat curFormater = new SimpleDateFormat("H:m");
			Date dateObj = curFormater.parse(DBLastDocumentTime);
			SimpleDateFormat postFormater = new SimpleDateFormat("HH:mm");
			String newDateStr = postFormater.format(dateObj);

			BasicDBObject que = new BasicDBObject();
			que.put("To", newDateStr);
			DBCursor cursor = ThursdayCollection.find(que);

			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				Map resultmap = result.toMap();
				ThursdayToTiming = (String) resultmap.get("To");
				ThursdayDocumentid = (Integer) resultmap.get("ID");

				ThursdayDocumentid++;

				for (int LastDocumentID = ThursdayDocumentid; LastDocumentID < ThursdayCollCount; LastDocumentID++) {
					BasicDBObject ques2 = new BasicDBObject("ID", ThursdayDocumentid);
					ques2.put("ID", ThursdayDocumentid);
					DBCursor cursorv = ThursdayCollection.find(ques2);

					while (cursorv.hasNext()) {
						DBObject resultv = cursorv.next();
						Map resultmapv = resultv.toMap();

						DBToTiming = (String) resultmapv.get("To");
						ThursdayDocumentid++;

						String DBVQC2MaxCapacity = (String) resultmapv.get("VQC2_Max_Capacity");

						if (!DBVQC2MaxCapacity.equals("")) {
							int dbVQC2MaxCapacity = Integer.parseInt((String) resultmapv.get("VQC2_Max_Capacity"));
							slotCapacity1Thu.add(dbVQC2MaxCapacity);

							slotTimings1Thu.add(DBToTiming);

							DocumentCountOfThursdayCollection++;

						}

					}
				}
			}

		}

		else if (DBLastDocumentDay.equals("Wednesday")) {

			DBCursor cursor = ThursdayCollection.find();
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				Map resultmap = result.toMap();

				String ToTiming = (String) resultmap.get("To");
				String DBVQC2MaxCapacity = (String) resultmap.get("VQC2_Max_Capacity");
				if (!DBVQC2MaxCapacity.equals("")) {
					int dbVQC2MaxCapacity = Integer.parseInt((String) resultmap.get("VQC2_Max_Capacity"));
					slotCapacity1Thu.add(dbVQC2MaxCapacity);
					slotTimings1Thu.add(ToTiming);
					DocumentCountOfThursdayCollection++;

				}
			}

		}

		DBCollection FridayCollection = db.getCollection("Friday");

		DBCursor cursor1 = FridayCollection.find();

		while (cursor1.hasNext()) {
			DBObject result = cursor1.next();
			Map resultmap = result.toMap();

			String ToTiming = (String) resultmap.get("To");
			String DBVQC2MaxCapacity = (String) resultmap.get("VQC2_Max_Capacity");
			if (!DBVQC2MaxCapacity.equals("")) {
				int dbVQC2MaxCapacity = Integer.parseInt((String) resultmap.get("VQC2_Max_Capacity"));

				slotCapacity1Thu.add(dbVQC2MaxCapacity);

				slotTimings1Fri.add(ToTiming);
				DocumentCountOfFridaycollection++;

			}
		}

		ArrayList<Double> ResThuII = new ArrayList<>();
		ResThuII = estimatedtime(Entering_time, slotTimings1Thu, slotTimings1Fri, slotCapacity1Thu,
				RemainingPeopleIntheCompartment2, RemainingcapacityOftheSlot2, NumberOfPeopleEnteredIntoCompartment2);

		return ResThuII;
	}

	public static ArrayList<Double> estimatedtime(String[] Entering_time, ArrayList<String> slotTimingsThu,
			ArrayList<String> slotTimingsFri, ArrayList<Integer> slotcapacity,

			double RemainingPeopleInCompartment2, double RemainingCapacityOfSlot2, int NumberOfPeopleEntered2)
			throws IOException, ParseException {

		double PeopleConsideredForDarshanVQC2 = 0;
		long DarshanTimeInHoursandMinutes = 0;
		long DarshanTimeInHours = 0;
		long DarshanTimeInMinutes = 0;
		double PeopleLeftInCompartment;

		int ThursdaySlotValue = VQCIIServlet.VQC2MonSlotValueCount;

		int lent = 0;

		ArrayList<Double> VQC2ThuResult = new ArrayList<>();
		ArrayList<Date> ThursdaySlotTime = new ArrayList<>();
		ArrayList<Date> FridaySlotTime = new ArrayList<>();
		ArrayList<String> slotTimings = new ArrayList<>();

		slotTimings.addAll(slotTimingsThu);
		slotTimings.addAll(slotTimingsFri);

		ArrayList<Date> Date2 = new ArrayList<>();

		for (String h : slotTimingsThu) {

			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date1 = format.parse(h);
			ThursdaySlotTime.add(date1);

		}

		for (String h : slotTimingsFri) {

			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);
			Calendar c = Calendar.getInstance();
			c.setTime(date2);
			c.add(Calendar.DATE, 1); // number of days to add
			Date currentDatePlusOne = c.getTime();
			FridaySlotTime.add(currentDatePlusOne);
		}

		ThursdaySlotTime.addAll(FridaySlotTime);

		for (String h : Entering_time) {
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);
			Date2.add(date2);
		}

		SumOfThuDocumentCount = 0;

		SumOfThuDocumentCount = DocumentCountOfThursdayCollection + DocumentCountOfFridaycollection;

		for (int EnteringPeopleTime = 0; EnteringPeopleTime < Entering_time.length; EnteringPeopleTime++) {

			for (int ThursdaySlotTimings = VQCIIServlet.VQC2ThuSlotTimeCount; ThursdaySlotTimings < slotTimings
					.size(); ThursdaySlotTimings++) {

				if (EnteringPeopleTime > 0) {
					VQCIIServlet.VQC2ThuSlotTimeCount = ThursdaySlotTimings;
					VQCIIServlet.VQC2MonSlotValueCount = ThursdaySlotValue;
					break;

				}

				double EnteredPeopleCount2 = NumberOfPeopleEntered2;

				if (EnteringPeopleTime > 20) {

					break;
				}

				if ((RemainingCapacityOfSlot2 > 0)
						&& ((Date2.get(0).compareTo(ThursdaySlotTime.get(ThursdaySlotTimings))) >= 0)) {

					ThursdaySlotTimings++;
					ThursdaySlotValue++;
					RemainingCapacityOfSlot2 = 0;
				}
				if ((ThursdaySlotTime.get(ThursdaySlotTimings).compareTo(Date2.get(0))) >= 0) {

					if (ThursdaySlotValue > SumOfThuDocumentCount - 1) {

						if (EnteredPeopleCount2 > slotcapacity.get(ThursdaySlotValue)) {

							if (RemainingCapacityOfSlot2 <= 0) {
								RemainingCapacityOfSlot2 = 0;

							}
							if (RemainingCapacityOfSlot2 > 0) {
								slotcapacity.set(ThursdaySlotValue, (int) RemainingCapacityOfSlot2);
							}

							RemainingPeopleInCompartment2 = RemainingPeopleInCompartment2 + EnteredPeopleCount2
									- (slotcapacity.get(ThursdaySlotValue));

							DarshanTimeInHoursandMinutes = (ThursdaySlotTime.get(ThursdaySlotTimings).getTime()
									- Date2.get(0).getTime()) + 3600000;
							DarshanTimeInHours = ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
							DarshanTimeInMinutes = ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();

							RemainingCapacityOfSlot2 = ((RemainingPeopleInCompartment2
									+ slotcapacity.get(ThursdaySlotValue)) - EnteredPeopleCount2);
							if (RemainingCapacityOfSlot2 > 0) {

							}

							if ((RemainingPeopleInCompartment2 > 0) && (RemainingCapacityOfSlot2 <= 0)) {

								PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
										+ (EnteredPeopleCount2 - RemainingPeopleInCompartment2);

							}

							else {

								PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
										+ (EnteredPeopleCount2 - RemainingCapacityOfSlot2);

							}

						}
						if (EnteredPeopleCount2 <= slotcapacity.get(ThursdaySlotValue)) {

							if (RemainingCapacityOfSlot2 <= 0) {
								RemainingCapacityOfSlot2 = 0;

							}

							if (RemainingCapacityOfSlot2 > 0) {
								slotcapacity.set(ThursdaySlotValue, (int) RemainingCapacityOfSlot2);
								;
							}

							DarshanTimeInHoursandMinutes = (ThursdaySlotTime.get(ThursdaySlotTimings).getTime()
									- Date2.get(0).getTime()) + 3600000;
							DarshanTimeInHours = ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
							DarshanTimeInMinutes = ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();

							if (RemainingPeopleInCompartment2 > 0) {

								RemainingCapacityOfSlot2 = ((slotcapacity.get(ThursdaySlotValue))
										- RemainingPeopleInCompartment2);
							} else {

								RemainingCapacityOfSlot2 = ((slotcapacity.get(ThursdaySlotValue))
										- EnteredPeopleCount2);
							}
							RemainingPeopleInCompartment2 = EnteredPeopleCount2 - slotcapacity.get(ThursdaySlotValue);

							if (RemainingCapacityOfSlot2 > 0) {

							}

							if ((RemainingPeopleInCompartment2 > 0) && (RemainingCapacityOfSlot2 <= 0)) {

								PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
										+ (EnteredPeopleCount2 - RemainingPeopleInCompartment2);

							} else {

								PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
										+ (slotcapacity.get(ThursdaySlotValue) - RemainingCapacityOfSlot2);

							}

							slotcapacity.set(ThursdaySlotValue, (int) RemainingCapacityOfSlot2);

							if (RemainingCapacityOfSlot2 <= 0) {
								RemainingCapacityOfSlot2 = 0;
							}
						}
						while (RemainingPeopleInCompartment2 >= slotcapacity.get(ThursdaySlotValue)) {

							if (RemainingCapacityOfSlot2 <= 0) {
								RemainingCapacityOfSlot2 = 0;

							}

							PeopleLeftInCompartment = RemainingPeopleInCompartment2;
							RemainingPeopleInCompartment2 = RemainingPeopleInCompartment2
									- slotcapacity.get(ThursdaySlotValue);

							if (ThursdaySlotValue > SumOfThuDocumentCount - 1) {

								break;
							}

							DarshanTimeInHoursandMinutes = (ThursdaySlotTime.get(ThursdaySlotTimings).getTime()
									- Date2.get(0).getTime()) + 3600000;
							DarshanTimeInHours = ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
							DarshanTimeInMinutes = ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();

							RemainingCapacityOfSlot2 = ((RemainingPeopleInCompartment2
									+ slotcapacity.get(ThursdaySlotValue)) - EnteredPeopleCount2);

							if (RemainingCapacityOfSlot2 > 0) {

							}

							if ((RemainingPeopleInCompartment2 > 0) && (RemainingCapacityOfSlot2 <= 0)) {

								PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
										+ (PeopleLeftInCompartment - RemainingPeopleInCompartment2);

							} else {
								if (RemainingCapacityOfSlot2 < 0) {
									RemainingCapacityOfSlot2 = 0;

								}

								PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
										+ (PeopleLeftInCompartment - RemainingCapacityOfSlot2);

							}
						}
						if ((RemainingPeopleInCompartment2 > 0)
								&& (RemainingPeopleInCompartment2 < slotcapacity.get(ThursdaySlotValue))) {

							DarshanTimeInHoursandMinutes = (ThursdaySlotTime.get(ThursdaySlotTimings).getTime()
									- Date2.get(0).getTime()) + 3600000;
							DarshanTimeInHours = ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
							DarshanTimeInMinutes = ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();

							RemainingCapacityOfSlot2 = ((slotcapacity.get(ThursdaySlotValue))
									- RemainingPeopleInCompartment2);
							RemainingPeopleInCompartment2 = RemainingPeopleInCompartment2
									- slotcapacity.get(ThursdaySlotValue);

							if (RemainingCapacityOfSlot2 > 0) {

							}

							if ((RemainingPeopleInCompartment2 > 0) && (RemainingCapacityOfSlot2 <= 0)) {

								if (RemainingPeopleInCompartment2 < 0)

								{
									RemainingPeopleInCompartment2 = 0;
								}

								PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
										+ RemainingPeopleInCompartment2;

							}

							else {

								PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
										+ (slotcapacity.get(ThursdaySlotValue)) - (RemainingCapacityOfSlot2);

							}

						}
						if (RemainingPeopleInCompartment2 < 0) {
							RemainingPeopleInCompartment2 = 0;

						}

						break;
					}

					if (EnteredPeopleCount2 > slotcapacity.get(ThursdaySlotValue)) {

						if (RemainingCapacityOfSlot2 <= 0) {
							RemainingCapacityOfSlot2 = 0;

						}
						if (RemainingCapacityOfSlot2 > 0) {
							slotcapacity.set(ThursdaySlotValue, (int) RemainingCapacityOfSlot2);
							;
						}

						RemainingPeopleInCompartment2 = RemainingPeopleInCompartment2 + EnteredPeopleCount2
								- (slotcapacity.get(ThursdaySlotValue));

						DarshanTimeInHoursandMinutes = (ThursdaySlotTime.get(ThursdaySlotTimings).getTime()
								- Date2.get(0).getTime()) + 3600000;
						DarshanTimeInHours = ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
						DarshanTimeInMinutes = ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();

						RemainingCapacityOfSlot2 = ((RemainingPeopleInCompartment2
								+ slotcapacity.get(ThursdaySlotValue)) - EnteredPeopleCount2);
						if (RemainingCapacityOfSlot2 > 0) {

						}

						if ((RemainingPeopleInCompartment2 > 0) && (RemainingCapacityOfSlot2 <= 0)) {

							PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
									+ (EnteredPeopleCount2 - RemainingPeopleInCompartment2);

						} else {

							PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
									+ (EnteredPeopleCount2 - RemainingCapacityOfSlot2);

						}

						if (RemainingCapacityOfSlot2 <= 0) {

							RemainingCapacityOfSlot2 = 0;
							ThursdaySlotTimings++;
							ThursdaySlotValue++;
						}
					}
					if (EnteredPeopleCount2 <= slotcapacity.get(ThursdaySlotValue)) {

						if (RemainingCapacityOfSlot2 <= 0) {
							RemainingCapacityOfSlot2 = 0;

						}

						if (RemainingCapacityOfSlot2 > 0) {
							slotcapacity.set(ThursdaySlotValue, (int) RemainingCapacityOfSlot2);
							;
						}

						DarshanTimeInHoursandMinutes = (ThursdaySlotTime.get(ThursdaySlotTimings).getTime()
								- Date2.get(0).getTime()) + 3600000;
						DarshanTimeInHours = ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
						DarshanTimeInMinutes = ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();

						if (RemainingPeopleInCompartment2 > 0) {

							RemainingCapacityOfSlot2 = ((slotcapacity.get(ThursdaySlotValue))
									- RemainingPeopleInCompartment2);
						} else {

							RemainingCapacityOfSlot2 = ((slotcapacity.get(ThursdaySlotValue)) - EnteredPeopleCount2);
						}
						RemainingPeopleInCompartment2 = EnteredPeopleCount2 - slotcapacity.get(ThursdaySlotValue);

						if (RemainingCapacityOfSlot2 > 0) {

						}

						if ((RemainingPeopleInCompartment2 > 0) && (RemainingCapacityOfSlot2 <= 0)) {

							PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
									+ (EnteredPeopleCount2 - RemainingPeopleInCompartment2);

						}

						else {

							PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
									+ (slotcapacity.get(ThursdaySlotValue) - RemainingCapacityOfSlot2);

						}

						slotcapacity.set(ThursdaySlotValue, (int) RemainingCapacityOfSlot2);

						if (RemainingCapacityOfSlot2 <= 0) {

							RemainingCapacityOfSlot2 = 0;
							ThursdaySlotTimings++;
							ThursdaySlotValue++;

						}
					}
					while (RemainingPeopleInCompartment2 >= slotcapacity.get(ThursdaySlotValue)) {

						if (RemainingCapacityOfSlot2 <= 0) {
							RemainingCapacityOfSlot2 = 0;

						}

						PeopleLeftInCompartment = RemainingPeopleInCompartment2;
						RemainingPeopleInCompartment2 = RemainingPeopleInCompartment2
								- slotcapacity.get(ThursdaySlotValue);

						DarshanTimeInHoursandMinutes = (ThursdaySlotTime.get(ThursdaySlotTimings).getTime()
								- Date2.get(0).getTime()) + 3600000;
						DarshanTimeInHours = ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
						DarshanTimeInMinutes = ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();

						RemainingCapacityOfSlot2 = ((RemainingPeopleInCompartment2
								+ slotcapacity.get(ThursdaySlotValue)) - EnteredPeopleCount2);

						if (RemainingCapacityOfSlot2 > 0) {

						}

						if ((RemainingPeopleInCompartment2 > 0) && (RemainingCapacityOfSlot2 <= 0)) {

							PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
									+ (PeopleLeftInCompartment - RemainingPeopleInCompartment2);

						}

						else {
							if (RemainingCapacityOfSlot2 < 0) {
								RemainingCapacityOfSlot2 = 0;

							}

							PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
									+ (PeopleLeftInCompartment - RemainingCapacityOfSlot2);

						}

						ThursdaySlotTimings++;
						ThursdaySlotValue++;
						if (ThursdaySlotValue > SumOfThuDocumentCount - 1) {

							break;
						}

					}

					if ((RemainingPeopleInCompartment2 > 0)
							&& (RemainingPeopleInCompartment2 < slotcapacity.get(ThursdaySlotValue))) {

						DarshanTimeInHoursandMinutes = (ThursdaySlotTime.get(ThursdaySlotTimings).getTime()
								- Date2.get(0).getTime()) + 3600000;
						DarshanTimeInHours = ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
						DarshanTimeInMinutes = ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();

						RemainingCapacityOfSlot2 = ((slotcapacity.get(ThursdaySlotValue))
								- RemainingPeopleInCompartment2);
						RemainingPeopleInCompartment2 = RemainingPeopleInCompartment2
								- slotcapacity.get(ThursdaySlotValue);

						if (RemainingCapacityOfSlot2 > 0) {

						}

						if ((RemainingPeopleInCompartment2 > 0) && (RemainingCapacityOfSlot2 <= 0)) {

							if (RemainingPeopleInCompartment2 < 0)

							{
								RemainingPeopleInCompartment2 = 0;

							}
							PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
									+ RemainingPeopleInCompartment2;

						} else {

							PeopleConsideredForDarshanVQC2 = PeopleConsideredForDarshanVQC2
									+ (slotcapacity.get(ThursdaySlotValue)) - (RemainingCapacityOfSlot2);

						}
					}
					if (RemainingPeopleInCompartment2 < 0)

					{
						RemainingPeopleInCompartment2 = 0;
					}

					EnteringPeopleTime++;

				} else {
					RemainingCapacityOfSlot2 = 0;
					ThursdaySlotTimings++;
					ThursdaySlotValue++;

					break;

				}
				ThursdaySlotTimings--;
			}
		}
		VQC2ThuResult.add(RemainingPeopleInCompartment2);
		VQC2ThuResult.add(RemainingCapacityOfSlot2);
		VQC2ThuResult.add((double) TimeUnit.MILLISECONDS.toMinutes(DarshanTimeInHoursandMinutes));
		VQC2ThuResult.add((double) TimeUnit.MILLISECONDS.toHours(DarshanTimeInHoursandMinutes));
		VQC2ThuResult.add((double) DarshanTimeInHours);
		VQC2ThuResult.add((double) DarshanTimeInMinutes);
		VQC2ThuResult.add(PeopleConsideredForDarshanVQC2);
		return VQC2ThuResult;
	}
}
