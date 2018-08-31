


import java.io.BufferedReader;



import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
public class VQCIWednesday {
	
	
static	ArrayList<String> Slot = new ArrayList<String>();
	static String str,str1,currentdate,PeopleEnteringTime, Date,VQC1ExactTimeFromDB,DBLastDocumentTime,DBLastDocumentDay,currentday,u,Remainingslot,DBToTiming, WednesdayToTiming;
	static int Count = 0,DocumentCountOfWednesdayCollection=0,DocumentCountOfThursdaycollection=0,w,z,SumOfWedThuDocumentCount,WednesdayDocumentid,WednesdayCollCount;
	static long WednesdayCollectionCount;

	public static ArrayList<Double> main(String[] args) throws NumberFormatException, IOException, ParseException {
				// TODO Auto-generated method stub
				
		int NumberOfPeopleEnteredIntoCompartment=VQCIServlet.PeopleEnteredIntoCompartment;
		String EnteringTimeOfCompartment=args[1];
		double RemainingPeopleIntheCompartment1=Double.parseDouble(args[2]),RemainingcapacityOftheSlot1=Double.parseDouble(args[3]);
		
		 
		ArrayList<String> slotTimings1Wed= new ArrayList<>();
		ArrayList<String> LastDocumentTimeFromDB= new ArrayList<>();
		ArrayList<Integer>  slotCapacity1Wed = new ArrayList<>();
		ArrayList<String> slotTimings1Thur= new ArrayList<>(); 
		
		
		String Entering_time[]={EnteringTimeOfCompartment};
				
		currentdate=VQCIServlet.UICurrentDate;
		currentday=VQCIServlet.UICurrentDay;
		PeopleEnteringTime=VQCIServlet.UIEnteringTime;
		
	
		System.out.println("Dd"+Date+"date::"+currentdate+currentday);
		MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
		
         // Now connect to your databases
         DB db = mongoClient.getDB( "TTD" );
         System.out.println("Connect to database successfully");
      
         DBCollection HomepageCollection = db.getCollection("Homepage");
         System.out.println("Collection Homepage selected successfully");
         
         if(PeopleEnteringTime.equalsIgnoreCase("0:00"))
         {
        
         DBCursor cursor3 = HomepageCollection.find().sort(new BasicDBObject("_id", -1)).limit(1);
        
 		
 	    while(cursor3.hasNext())
 	    {
 	    	DBObject result=cursor3.next();
 			  Map resultmap=result.toMap();
 			  
 			  VQC1ExactTimeFromDB=  (String)resultmap.get("VQC1_Exact_time");
 			  System.out.println("last::"+VQC1ExactTimeFromDB);
 			  
 			
			  if(VQC1ExactTimeFromDB==null)
			  {
				 DBCursor cursor4 = HomepageCollection.find().sort(new BasicDBObject("_id", -1)).limit(2);
	             
	            while(cursor4.hasNext())
	     	    {
	     	    	DBObject result1=cursor4.next();
	     			  Map resultmap1=result1.toMap();
	     			  
	     			  VQC1ExactTimeFromDB=  (String)resultmap1.get("VQC1_Exact_time");
	     			  System.out.println("last::"+VQC1ExactTimeFromDB);
			  }
			  }
 			  
 	   
 	    if(!(VQC1ExactTimeFromDB==null))
 	    {
 	    	 String[] Cdate=VQC1ExactTimeFromDB.split("-");
 	    	 DBLastDocumentTime=Cdate[0];
 	    	 DBLastDocumentDay=Cdate[1];
 	 	    
 	 	  u =  DBLastDocumentTime;
	 	    
	 	    LastDocumentTimeFromDB.add(u);
 	 	   
 	    }
 	    
 	    }  
         
         }
      
		  w=DocumentCountOfWednesdayCollection;
		  w=0;
		  DocumentCountOfWednesdayCollection=w;
		  
		  z=DocumentCountOfThursdaycollection;
		  z=0;
		  DocumentCountOfThursdaycollection=z;
		  
		  DBCollection WednesdayCollection = db.getCollection("Wednesday"); 
			  System.out.println("Collection Wednesday selected successfully");
			
			WednesdayCollectionCount =WednesdayCollection.count();
			
			WednesdayCollCount = (int) WednesdayCollectionCount;
			
			 if(currentday.equals(DBLastDocumentDay))
			  {
				  SimpleDateFormat curFormater = new SimpleDateFormat("H:m"); 
		            Date dateObj = curFormater.parse(DBLastDocumentTime); 
		            SimpleDateFormat postFormater = new SimpleDateFormat("HH:mm"); 
		            String newDateStr = postFormater.format(dateObj); 
		           
				 
				  BasicDBObject que = new BasicDBObject();
				  que.put("To", newDateStr);
				  DBCursor cursor = WednesdayCollection.find(que);
				  
				  while(cursor.hasNext())
					 {
					  DBObject result=cursor.next();
					  Map resultmap=result.toMap();
					  WednesdayToTiming=  (String)resultmap.get("To");
					  WednesdayDocumentid = (Integer) resultmap.get("ID");
					  
					  WednesdayDocumentid++;
					  for (int LastDocumentID=WednesdayDocumentid; LastDocumentID<WednesdayCollCount; LastDocumentID++ ){
						  BasicDBObject ques2 = new BasicDBObject("ID", WednesdayDocumentid);
						  ques2.put("ID", WednesdayDocumentid);
						  DBCursor cursorv = WednesdayCollection.find(ques2);
						  
						  while(cursorv.hasNext())
							 {
							  DBObject resultv=cursorv.next();
							  Map resultmapv=resultv.toMap();
										  
						  
						  DBToTiming = (String) resultmapv.get("To");
						 WednesdayDocumentid++;
						  
						  String DBVQC1MaxCapacity=(String)resultmapv.get("VQC1_Max_Capacity");
						  
							
						  if(!DBVQC1MaxCapacity.equals("")){
							  int dbVQC1MaxCapacity=  Integer.parseInt((String)resultmapv.get("VQC1_Max_Capacity"));
							  slotCapacity1Wed.add(dbVQC1MaxCapacity);
							
							  slotTimings1Wed.add(DBToTiming);
							  DocumentCountOfWednesdayCollection++;
							  
					 	  }
					  
							 }
					 }}
				  
			  }
			  else if(DBLastDocumentDay.equals("Tuesday"))
			 {
				 
				 DBCursor cursor = WednesdayCollection.find();
				  while(cursor.hasNext())
					 {
					  DBObject result=cursor.next();
					  Map resultmap=result.toMap();
					  
					  String ToTiming=  (String)resultmap.get("To");
					  String DBVQC1MaxCapacity=(String)resultmap.get("VQC1_Max_Capacity");
					  if(!DBVQC1MaxCapacity.equals("")){
						  int dbVQC1MaxCapacity=  Integer.parseInt((String)resultmap.get("VQC1_Max_Capacity"));
						  slotCapacity1Wed.add(dbVQC1MaxCapacity);
						  slotTimings1Wed.add(ToTiming);
						 
						  
						  DocumentCountOfWednesdayCollection++;
						  
					  }
					 }
				 
				 
			 }
		
        DBCollection ThursdayCollection = db.getCollection("Thursday");
        
         DBCursor cursor1 = ThursdayCollection.find();
         
		         while(cursor1.hasNext())
				 {
				  DBObject result=cursor1.next();
				  Map resultmap=result.toMap();
				  
				  String ToTiming=  (String)resultmap.get("To");
				  String DBVQC1MaxCapacity=(String)resultmap.get("VQC1_Max_Capacity");
				  if(!DBVQC1MaxCapacity.equals("")){
					  int dbVQC1MaxCapacity=  Integer.parseInt((String)resultmap.get("VQC1_Max_Capacity"));
					slotCapacity1Wed.add(dbVQC1MaxCapacity); 
					  
					  slotTimings1Thur.add(ToTiming);
					  
					  DocumentCountOfThursdaycollection++;
					 
				  }
				   }
		        
		     	ArrayList<Double> ResWed1= new ArrayList<>();	  
			 ResWed1=estimatedtime(Entering_time, slotTimings1Wed, slotTimings1Thur, slotCapacity1Wed,RemainingPeopleIntheCompartment1,RemainingcapacityOftheSlot1,NumberOfPeopleEnteredIntoCompartment);
			
			return ResWed1;
		}

	public static ArrayList<Double> estimatedtime( String[] Enteringtime,
			
			ArrayList<String> slotTimingsWed,
			ArrayList<String> slotTimingsThur,
			ArrayList<Integer> slotcapacity,
			 double RemainingPeopleInCompartment1,
			double RemainingCapacityOfSlot1,
			 	int NumberOfPeopleEntered) throws IOException, ParseException {
	 	double PeopleConsideredForDarshanVQC1 = 0;
		long  DarshanTimeInHoursandMinutes=0;
		long DarshanTimeInHours =0;
		long DarshanTimeInMinutes=0;
		double PeopleLeftInCompartment;
		
	    
 	
		int lent= 0;
		
		
	 	ArrayList<Double> VQC1WedResult= new ArrayList<>();
		ArrayList<Date> WednesdaySlotTime = new ArrayList<>();
		ArrayList<Date> ThursdaySlotTime= new ArrayList<>();
		ArrayList<String>slotTimings= new ArrayList<>();
		
		
		slotTimings.addAll(slotTimingsWed);
		slotTimings.addAll(slotTimingsThur);
		
		ArrayList<Date> enteredTime = new ArrayList<>();
		 
		for ( String h:slotTimingsWed){
			  
				SimpleDateFormat format = new SimpleDateFormat("HH:mm");
				Date date1 = format.parse(h);
				WednesdaySlotTime.add(date1);
	 	}
	 	for ( String h:slotTimingsThur){
			  
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);
			Calendar c = Calendar.getInstance();
			c.setTime(date2);
			c.add(Calendar.DATE, 1); 
		 	Date currentDatePlusOne = c.getTime();
		 	ThursdaySlotTime.add(currentDatePlusOne);
		 	}
		
		WednesdaySlotTime.addAll(ThursdaySlotTime);
		 for ( String h:Enteringtime){
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);	
			enteredTime.add(date2);
		}
		
		SumOfWedThuDocumentCount=0;
		SumOfWedThuDocumentCount=DocumentCountOfWednesdayCollection+DocumentCountOfThursdaycollection;
		System.out.println("total::"+SumOfWedThuDocumentCount);
		
		int WednesdaySlotValue = VQCIServlet.VQC1WedSlotValueCount;
		
	 	for(int EnteringPeopleTime = 0;EnteringPeopleTime<Enteringtime.length;EnteringPeopleTime++) {
	 		
	 		
			for(int WednesdaySlotTimings=VQCIServlet.VQC1WedSlotTimeCount;WednesdaySlotTimings<slotTimings.size();WednesdaySlotTimings++){
				 
				if (EnteringPeopleTime>0){
					VQCIServlet.VQC1WedSlotTimeCount=WednesdaySlotTimings;
					VQCIServlet.VQC1WedSlotValueCount=WednesdaySlotValue;
					break;
				 
				}
				
				double EnteredPeopleCount = NumberOfPeopleEntered;
			 	if(EnteringPeopleTime>20){
					
					break;
				}
				
				if((RemainingCapacityOfSlot1>0)&&((enteredTime.get(0).compareTo(WednesdaySlotTime.get(WednesdaySlotTimings))) >=0 ))
				{ 
					WednesdaySlotTimings++;
					WednesdaySlotValue++;
					RemainingCapacityOfSlot1=0;
				}
				
	if( (WednesdaySlotTime.get(WednesdaySlotTimings).compareTo(enteredTime.get(0))) >=0)
				{
					
			        if(WednesdaySlotValue>SumOfWedThuDocumentCount-1)
					{
			        	
			        	if(EnteredPeopleCount> slotcapacity.get(WednesdaySlotValue))
				        {
						 
				        	
				        	if(RemainingCapacityOfSlot1<=0)
							{
								RemainingCapacityOfSlot1=0;
								
							}
				        	if(RemainingCapacityOfSlot1>0)
							{
								slotcapacity.set(WednesdaySlotValue,(int)RemainingCapacityOfSlot1);
							}
						 
				        	
				        	RemainingPeopleInCompartment1=RemainingPeopleInCompartment1+EnteredPeopleCount-(slotcapacity.get(WednesdaySlotValue));
							
				        	DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
							DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
							DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							
							RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slotcapacity.get(WednesdaySlotValue))-EnteredPeopleCount);
							if(RemainingCapacityOfSlot1>0)
							{
								
							}
							
							if((RemainingPeopleInCompartment1>0)&&(RemainingCapacityOfSlot1<=0))
							{
								
								PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(EnteredPeopleCount-RemainingPeopleInCompartment1);
							
							}
							
							else
							{
								
								
								PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(EnteredPeopleCount-RemainingCapacityOfSlot1);
								
							}
					 		
				        }
					 if(EnteredPeopleCount<=slotcapacity.get(WednesdaySlotValue)){
						 
						 
						 if(RemainingCapacityOfSlot1<=0)
							{
								RemainingCapacityOfSlot1=0;
								
							}
						 
						
						if(RemainingCapacityOfSlot1>0)
						{
							slotcapacity.set(WednesdaySlotValue,(int)RemainingCapacityOfSlot1);;
						}
						
						DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
						DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
						DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						
						if(RemainingPeopleInCompartment1>0)
						{
							RemainingCapacityOfSlot1=((slotcapacity.get(WednesdaySlotValue))-RemainingPeopleInCompartment1);
						}
						else
						{
							RemainingCapacityOfSlot1=((slotcapacity.get(WednesdaySlotValue))-EnteredPeopleCount);
						}
						RemainingPeopleInCompartment1=EnteredPeopleCount-slotcapacity.get(WednesdaySlotValue);
						
						if(RemainingCapacityOfSlot1>0)
						{
							
						}
						
						if((RemainingPeopleInCompartment1>0)&&(RemainingCapacityOfSlot1<=0))
						{
							PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(EnteredPeopleCount-RemainingPeopleInCompartment1);
							
						}
						
						else
						{
							PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(slotcapacity.get(WednesdaySlotValue)-RemainingCapacityOfSlot1);
							
						}
						
						slotcapacity.set(WednesdaySlotValue,(int)RemainingCapacityOfSlot1);;
						
						if(RemainingCapacityOfSlot1<=0)
						{
						
							RemainingCapacityOfSlot1=0;
						 
						}
		        }
				while(RemainingPeopleInCompartment1>=slotcapacity.get(WednesdaySlotValue))
				{
					if(RemainingCapacityOfSlot1<=0)
					{
						RemainingCapacityOfSlot1=0;
					 
					}
					PeopleLeftInCompartment=RemainingPeopleInCompartment1;
					RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slotcapacity.get(WednesdaySlotValue);
					
					if(WednesdaySlotValue>SumOfWedThuDocumentCount-1)
					{
			        	System.out.println("slots are over");
						break;
					}
			       	
					DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
					DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
					DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					
					RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slotcapacity.get(WednesdaySlotValue))-EnteredPeopleCount);
					
					if(RemainingCapacityOfSlot1>0)
					{
						
					}
					
					
					if((RemainingPeopleInCompartment1>0)&&(RemainingCapacityOfSlot1<=0))
					{
						PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(PeopleLeftInCompartment-RemainingPeopleInCompartment1);
						
					}
					
					else
					{
						if(RemainingCapacityOfSlot1<0)
						{
							RemainingCapacityOfSlot1=0;
							
						}
						PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(PeopleLeftInCompartment-RemainingCapacityOfSlot1);
						
					}
					
				}
				
				if((RemainingPeopleInCompartment1>0)&&(RemainingPeopleInCompartment1<slotcapacity.get(WednesdaySlotValue)))
				{
					DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
					DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
					DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					
					RemainingCapacityOfSlot1=((slotcapacity.get(WednesdaySlotValue))-RemainingPeopleInCompartment1);
					RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slotcapacity.get(WednesdaySlotValue);
					
					if(RemainingCapacityOfSlot1>0)
					{
						
					}
					
					if((RemainingPeopleInCompartment1>0)&&(RemainingCapacityOfSlot1<=0))
					{
						
						if(RemainingPeopleInCompartment1<0) 
							
						{
							RemainingPeopleInCompartment1=0;
							 
							
						 }	
						PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+RemainingPeopleInCompartment1;
						
					}
					
					else
					{
						PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(slotcapacity.get(WednesdaySlotValue))-(RemainingCapacityOfSlot1);
					}
			 	}
				if(RemainingPeopleInCompartment1<0){
					RemainingPeopleInCompartment1=0;
			  }	
			        	break;
					}
			       
						 if(EnteredPeopleCount>slotcapacity.get(WednesdaySlotValue))
					        {
							 
					        	
					        	if(RemainingCapacityOfSlot1<=0)
								{
									RemainingCapacityOfSlot1=0;
									
								}
					        	if(RemainingCapacityOfSlot1>0)
								{
									slotcapacity.set(WednesdaySlotValue,(int)RemainingCapacityOfSlot1);;
								}
							 
					        	
					        	RemainingPeopleInCompartment1=RemainingPeopleInCompartment1+EnteredPeopleCount-(slotcapacity.get(WednesdaySlotValue));
								
					        	DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
								DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
								DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
								
								RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slotcapacity.get(WednesdaySlotValue))-EnteredPeopleCount);
								if(RemainingCapacityOfSlot1>0)
								{
									
								}
								
								if((RemainingPeopleInCompartment1>0)&&(RemainingCapacityOfSlot1<=0))
								{
									
									PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(EnteredPeopleCount-RemainingPeopleInCompartment1);
									
								}
							
								else
								{
									PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(EnteredPeopleCount-RemainingCapacityOfSlot1);
									
								}
								
								if(RemainingCapacityOfSlot1<=0)
								{
									
									RemainingCapacityOfSlot1=0;
									WednesdaySlotTimings++;
									WednesdaySlotValue++;
								 
								}
							 }
						 if(EnteredPeopleCount<=slotcapacity.get(WednesdaySlotValue)){
							  
							 if(RemainingCapacityOfSlot1<=0)
								{
									RemainingCapacityOfSlot1=0;
									
								}
							 
							if(RemainingCapacityOfSlot1>0)
							{
								slotcapacity.set(WednesdaySlotValue,(int)RemainingCapacityOfSlot1);;
							}
							
							
							DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
							DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
							DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
							DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
							
							if(RemainingPeopleInCompartment1>0)
							{
								RemainingCapacityOfSlot1=((slotcapacity.get(WednesdaySlotValue))-RemainingPeopleInCompartment1);
							}
							else
							{
								RemainingCapacityOfSlot1=((slotcapacity.get(WednesdaySlotValue))-EnteredPeopleCount);
							}
							RemainingPeopleInCompartment1=EnteredPeopleCount-slotcapacity.get(WednesdaySlotValue);
							
							if(RemainingCapacityOfSlot1>0)
							{
								
							}
							
							if((RemainingPeopleInCompartment1>0)&&(RemainingCapacityOfSlot1<=0))
							{
								
								PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(EnteredPeopleCount-RemainingPeopleInCompartment1);
								
							}
						else
							{
								PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(slotcapacity.get(WednesdaySlotValue)-RemainingCapacityOfSlot1);
								
							}
							
							slotcapacity.set(WednesdaySlotValue,(int)RemainingCapacityOfSlot1);
							
							if(RemainingCapacityOfSlot1<=0)
							{
							 
								RemainingCapacityOfSlot1=0;
								WednesdaySlotTimings++;
								WednesdaySlotValue++;
								
							}
			        }
						 
					while(RemainingPeopleInCompartment1>=slotcapacity.get(WednesdaySlotValue))
					{
						
						if(RemainingCapacityOfSlot1<=0)
						{
							RemainingCapacityOfSlot1=0;
							 
						}
						PeopleLeftInCompartment=RemainingPeopleInCompartment1;
						RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slotcapacity.get(WednesdaySlotValue);
						
						DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
						DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
						DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						
						RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slotcapacity.get(WednesdaySlotValue))-EnteredPeopleCount);
						
						if(RemainingCapacityOfSlot1>0)
						{
							
						}
						
						if((RemainingPeopleInCompartment1>0)&&(RemainingCapacityOfSlot1<=0))
						{
							PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(PeopleLeftInCompartment-RemainingPeopleInCompartment1);
							
						}
						
						else
						{
							if(RemainingCapacityOfSlot1<0)
							{
								RemainingCapacityOfSlot1=0;
								
							}
							PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(PeopleLeftInCompartment-RemainingCapacityOfSlot1);
							
						}
						WednesdaySlotTimings++;
						WednesdaySlotValue++; 
						
						if(WednesdaySlotValue>SumOfWedThuDocumentCount-1)
						{
				        	
				        	break;
						}
				       
					}
					
					if((RemainingPeopleInCompartment1>0)&&(RemainingPeopleInCompartment1<slotcapacity.get(WednesdaySlotValue)))
					{
						
						DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
						DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
						DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						
						RemainingCapacityOfSlot1=((slotcapacity.get(WednesdaySlotValue))-RemainingPeopleInCompartment1);
						RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slotcapacity.get(WednesdaySlotValue);
						
						if(RemainingCapacityOfSlot1>0)
						{
							
						}
						
						
						if((RemainingPeopleInCompartment1>0)&&(RemainingCapacityOfSlot1<=0))
						{
							if(RemainingPeopleInCompartment1<0) 
								
							{
								RemainingPeopleInCompartment1=0;
							 }	
							PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+RemainingPeopleInCompartment1;
							
						}
						
						else
						{
							PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(slotcapacity.get(WednesdaySlotValue))-(RemainingCapacityOfSlot1);
							
						}
				}
					if(RemainingPeopleInCompartment1<0) 
						
					{
						RemainingPeopleInCompartment1=0;
					  }	
					
					EnteringPeopleTime++;
			}
				
				else{
					RemainingCapacityOfSlot1=0;
					WednesdaySlotTimings++;
					WednesdaySlotValue++;
					
					break;
				}
				WednesdaySlotTimings--;
				
			}
		} 
	  VQC1WedResult.add(RemainingPeopleInCompartment1);
	 	VQC1WedResult.add(RemainingCapacityOfSlot1);
	 	VQC1WedResult.add((double)TimeUnit.MILLISECONDS.toMinutes(DarshanTimeInHoursandMinutes));
	 	VQC1WedResult.add((double)TimeUnit.MILLISECONDS.toHours(DarshanTimeInHoursandMinutes));
	 	VQC1WedResult.add((double)DarshanTimeInHours);
	 	VQC1WedResult.add((double)DarshanTimeInMinutes);
	 	VQC1WedResult.add(PeopleConsideredForDarshanVQC1);
	return VQC1WedResult; 
	}
	
	
}






