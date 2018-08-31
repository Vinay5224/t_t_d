


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
public class VQCIIWednesday {
	
	
	static	ArrayList<String> Slot = new ArrayList<String>();
	
	
	static String str,str1,currentdate,PeopleEnteringTime, Date,VQC2ExactTimeFromDB,DBLastDocumentTime,DBLastDocumentDay,currentday,u,Remainingslot,DBToTiming, WednesdayToTiming;
	static int Count = 0,DocumentCountOfWednesdayCollection=0,DocumentCountOfThursdaycollection=0,w,z,SumOfWedThuDocumentCount,WednesdayDocumentid,e, grt, WednesdayCollCount;
	static long WednesdayCollectionCount;


	public static ArrayList<Double> main(String[] args) throws NumberFormatException, IOException, ParseException {
				// TODO Auto-generated method stub
		 
		int NumberOfPeopleEnteredIntoCompartment2=VQCIIServlet.PeopleEnteredIntoCompartment2;
		String EnteringTimeOfCompartment2=args[1];
		double RemainingPeopleIntheCompartment2=Double.parseDouble(args[2]),RemainingcapacityOftheSlot2=Double.parseDouble(args[3]);
 
		 
		ArrayList<String> slotTimings1Wed= new ArrayList<>();
		ArrayList<Integer>  slotCapacity1Wed = new ArrayList<>();
		 ArrayList<String> slotTimings1Thur= new ArrayList<>(); 
		 
				 
		String Entering_time[]={EnteringTimeOfCompartment2};
				 
		currentdate=VQCIIServlet.UICurrentDate;
		currentday=VQCIIServlet.UICurrentDay;
		PeopleEnteringTime=VQCIIServlet.UIEnteringTime;
		 
	
		System.out.println("Dd"+Date+"date::"+currentdate+currentday);
		MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
		
         // Now connect to your databases
         DB db = mongoClient.getDB( "TTD" );
      
         
         
         DBCollection HomepageCollection = db.getCollection("Homepage");
       
         
         if(PeopleEnteringTime.equalsIgnoreCase("0:00"))
         {
          
         DBCursor cursor3 = HomepageCollection.find().sort(new BasicDBObject("_id", -1)).limit(1);
          
 		
 	    while(cursor3.hasNext())
 	    {
 	    	DBObject result=cursor3.next();
 			  Map resultmap=result.toMap();
 			  
 			  VQC2ExactTimeFromDB=  (String)resultmap.get("VQC2_Exact_time");
 			
 			  
 			 if(VQC2ExactTimeFromDB==null)
			  {
				 DBCursor cursor4 = HomepageCollection.find().sort(new BasicDBObject("_id", -1)).limit(2);
	              
	            while(cursor4.hasNext())
	     	    {
	     	    	DBObject result1=cursor4.next();
	     			  Map resultmap1=result1.toMap();
	     			  
	     			  VQC2ExactTimeFromDB=  (String)resultmap1.get("VQC2_Exact_time");
	     			 
			  }
			  }
 			  
 		   if(!(VQC2ExactTimeFromDB==null))
 	    {
 	    	 String[] Cdate=VQC2ExactTimeFromDB.split("-");
 	    	 DBLastDocumentTime=Cdate[0];
 	    	 DBLastDocumentDay=Cdate[1];
 	 
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
					
						  
						  String DBVQC2MaxCapacity=(String)resultmapv.get("VQC2_Max_Capacity");
						  
						   if(!DBVQC2MaxCapacity.equals("")){
							  int dbVQC2MaxCapacity=  Integer.parseInt((String)resultmapv.get("VQC2_Max_Capacity"));
							  slotCapacity1Wed.add(dbVQC2MaxCapacity);
							
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
					  String DBVQC2MaxCapacity=(String)resultmap.get("VQC2_Max_Capacity");
					  if(!DBVQC2MaxCapacity.equals("")){
						  int dbVQC2MaxCapacity =  Integer.parseInt((String)resultmap.get("VQC2_Max_Capacity"));
						  slotCapacity1Wed.add(dbVQC2MaxCapacity);
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
				  String DBVQC2MaxCapacity=(String)resultmap.get("VQC2_Max_Capacity");
				  if(!DBVQC2MaxCapacity.equals("")){
					  int dbVQC2MaxCapacity=  Integer.parseInt((String)resultmap.get("VQC2_Max_Capacity"));
					slotCapacity1Wed.add(dbVQC2MaxCapacity); 
					  
					  slotTimings1Thur.add(ToTiming);
					  
					  DocumentCountOfThursdaycollection++;
					
					  
				  }
				   }
		        

		     	ArrayList<Double> ResWedII= new ArrayList<>();	  
			 ResWedII=estimatedtime(Entering_time, slotTimings1Wed, slotTimings1Thur, slotCapacity1Wed,RemainingPeopleIntheCompartment2,RemainingcapacityOftheSlot2,NumberOfPeopleEnteredIntoCompartment2);
				
			return ResWedII;
		}

	public static ArrayList<Double> estimatedtime( String[] Entering_time,
			ArrayList<String> slotTimingsWed,
			ArrayList<String> slotTimingsThur,
			ArrayList<Integer> slotcapacity,
			double RemainingPeopleInCompartment2,
			double RemainingCapacityOfSlot2,
		 	int NumberOfPeopleEntered2 ) throws IOException, ParseException {
		 
		double PeopleConsideredForDarshanVQC2 = 0;
		long  DarshanTimeInHoursandMinutes=0;
		long DarshanTimeInHours =0;
		long DarshanTimeInMinutes=0;
		double PeopleLeftInCompartment;
		
		 
	int WednesdaySlotValue = VQCIIServlet.VQC2WedSlotValueCount;
	 
		 
		int lent= 0;
		
		ArrayList<Double> VQC2WedResult= new ArrayList<>();
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
			c.add(Calendar.DATE, 1); // number of days to add
		 	Date currentDatePlusOne = c.getTime();
		 	ThursdaySlotTime.add(currentDatePlusOne);
		 	}
		
		WednesdaySlotTime.addAll(ThursdaySlotTime);
		
	  	for ( String h:Entering_time){
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);	
			enteredTime.add(date2);
		}
		
		
		 SumOfWedThuDocumentCount =0   ;
		
	  SumOfWedThuDocumentCount=DocumentCountOfWednesdayCollection+DocumentCountOfThursdaycollection;
	 
	 
	 	for(int EnteringPeopleTime = 0;EnteringPeopleTime<Entering_time.length;EnteringPeopleTime++) {

			for(int WednesdaySlotTimings=VQCIIServlet.VQC2WedSlotTimeCount;WednesdaySlotTimings<slotTimings.size();WednesdaySlotTimings++){
				 
				if (EnteringPeopleTime>0){
					VQCIIServlet.VQC2WedSlotTimeCount  =WednesdaySlotTimings;
					VQCIIServlet.VQC2WedSlotValueCount=WednesdaySlotValue;
					break;
				 
				}
				 double EnteredPeopleCount = NumberOfPeopleEntered2;
				 	if(EnteringPeopleTime>20){
					
					break;
				}
				
			
				if((RemainingCapacityOfSlot2>0)&&((enteredTime.get(0).compareTo(WednesdaySlotTime.get(WednesdaySlotTimings))) >=0 ))
				{
					 
					WednesdaySlotTimings++;
					WednesdaySlotValue++;
					RemainingCapacityOfSlot2=0;
				}
			 	if( (WednesdaySlotTime.get(WednesdaySlotTimings).compareTo(enteredTime.get(0))) >=0)
				{
			
					 
			        if(WednesdaySlotValue>SumOfWedThuDocumentCount-1)
					{
			        	
			    
			        	if(EnteredPeopleCount> slotcapacity.get(WednesdaySlotValue))
				        {
						   if(RemainingCapacityOfSlot2<=0)
							{
								RemainingCapacityOfSlot2=0;
							
							}
				        	if(RemainingCapacityOfSlot2>0)
							{
								slotcapacity.set(WednesdaySlotValue,(int)RemainingCapacityOfSlot2);
							}
						 
				        	
				        	RemainingPeopleInCompartment2=RemainingPeopleInCompartment2+EnteredPeopleCount-(slotcapacity.get(WednesdaySlotValue));
						
					
							 
							DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
							DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
						
							DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						
							RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(WednesdaySlotValue))-EnteredPeopleCount);
							if(RemainingCapacityOfSlot2>0)
							{
							
							}
							
							if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
							{
								
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount-RemainingPeopleInCompartment2);
						
								
							}
							 
							else
							{
							
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount-RemainingCapacityOfSlot2);
							
							}
						   }
					 if(EnteredPeopleCount<=slotcapacity.get(WednesdaySlotValue)){
						  if(RemainingCapacityOfSlot2<=0)
							{
								RemainingCapacityOfSlot2=0;
							
							}
						 
					
						if(RemainingCapacityOfSlot2>0)
						{
							slotcapacity.set(WednesdaySlotValue,(int)RemainingCapacityOfSlot2);;
						}
						
					
						DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
						DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
					
						DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						
						if(RemainingPeopleInCompartment2>0)
						{
						
							RemainingCapacityOfSlot2=((slotcapacity.get(WednesdaySlotValue))-RemainingPeopleInCompartment2);
						}
						else
						{
							RemainingCapacityOfSlot2=((slotcapacity.get(WednesdaySlotValue))-EnteredPeopleCount);
						}
						RemainingPeopleInCompartment2=EnteredPeopleCount-slotcapacity.get(WednesdaySlotValue);
				
						
						if(RemainingCapacityOfSlot2>0)
						{
							
						}
						
						if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
						{
						
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount-RemainingPeopleInCompartment2);
			
							
						}
						 
						else
						{
						
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(WednesdaySlotValue)-RemainingCapacityOfSlot2);
						
						}
						
						slotcapacity.set(WednesdaySlotValue,(int)RemainingCapacityOfSlot2);;
						
						if(RemainingCapacityOfSlot2<=0)
						{
						 
							RemainingCapacityOfSlot2=0;
						 	 
						}
		        }
				while(RemainingPeopleInCompartment2>=slotcapacity.get(WednesdaySlotValue))
				{
					
					if(RemainingCapacityOfSlot2<=0)
					{
						RemainingCapacityOfSlot2=0;
						 
					}
				
					PeopleLeftInCompartment=RemainingPeopleInCompartment2;
					RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(WednesdaySlotValue);
					
					if(WednesdaySlotValue>SumOfWedThuDocumentCount-1)
					{
			        	
						break;
					}
			        	
					 
					DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
					DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
			
					
					
					DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
			
					RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(WednesdaySlotValue))-EnteredPeopleCount);
					
					if(RemainingCapacityOfSlot2>0)
					{
					
					}
					
					 if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
					{
					
						PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(PeopleLeftInCompartment-RemainingPeopleInCompartment2);
					
				 }
				 	else
					{
						if(RemainingCapacityOfSlot2<0)
						{
							RemainingCapacityOfSlot2=0;
						
						}
						
						PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(PeopleLeftInCompartment-RemainingCapacityOfSlot2);
					
					}
				 }
				 if((RemainingPeopleInCompartment2>0)&&(RemainingPeopleInCompartment2<slotcapacity.get(WednesdaySlotValue)))
				{
					 
				
					 
					DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
					DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
					
					
					DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
				
					RemainingCapacityOfSlot2=((slotcapacity.get(WednesdaySlotValue))-RemainingPeopleInCompartment2);
					RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(WednesdaySlotValue);
				
					if(RemainingCapacityOfSlot2>0)
					{
						
					}
					
					if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
					{
						
						if(RemainingPeopleInCompartment2<0) 
							
						{
							RemainingPeopleInCompartment2=0;
				   }	
						PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+RemainingPeopleInCompartment2;
				
						
					}
				 
					else
					{
					
						PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(WednesdaySlotValue))-(RemainingCapacityOfSlot2);
				
					}
				 }
				if(RemainingPeopleInCompartment2<0){
					RemainingPeopleInCompartment2=0;
				  }	
			        	
						break;
					}
			       
						 if(EnteredPeopleCount>slotcapacity.get(WednesdaySlotValue))
					        {
						  	if(RemainingCapacityOfSlot2<=0)
								{
									RemainingCapacityOfSlot2=0;
								
								}
					        	if(RemainingCapacityOfSlot2>0)
								{
									slotcapacity.set(WednesdaySlotValue,(int)RemainingCapacityOfSlot2);;
								}
							 
					  
								
					        	RemainingPeopleInCompartment2=RemainingPeopleInCompartment2+EnteredPeopleCount-(slotcapacity.get(WednesdaySlotValue));
							
						
							 
								DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
								DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
						
								
								
								DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							
								RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(WednesdaySlotValue))-EnteredPeopleCount);
								if(RemainingCapacityOfSlot2>0)
								{
									
								}
								
								if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
								{
									
									PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount-RemainingPeopleInCompartment2);
								
									
								}
					 
								else
								{
									
									
									PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount-RemainingCapacityOfSlot2);
					
								}
								
								if(RemainingCapacityOfSlot2<=0)
								{
									 
									RemainingCapacityOfSlot2=0;
									WednesdaySlotTimings++;
									WednesdaySlotValue++;
									 
								}
							  }
						 if(EnteredPeopleCount<=slotcapacity.get(WednesdaySlotValue)){
							 
							 
							 if(RemainingCapacityOfSlot2<=0)
								{
									RemainingCapacityOfSlot2=0;
								
								}
							 
					
							if(RemainingCapacityOfSlot2>0)
							{
								slotcapacity.set(WednesdaySlotValue,(int)RemainingCapacityOfSlot2);;
							}
							
					
							
							DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
							DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
							
							
							DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							 
							DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
							DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
						
							if(RemainingPeopleInCompartment2>0)
							{
					
								RemainingCapacityOfSlot2=((slotcapacity.get(WednesdaySlotValue))-RemainingPeopleInCompartment2);
							}
							else
							{
							
								RemainingCapacityOfSlot2=((slotcapacity.get(WednesdaySlotValue))-EnteredPeopleCount);
							}
							RemainingPeopleInCompartment2=EnteredPeopleCount-slotcapacity.get(WednesdaySlotValue);
							
							
							if(RemainingCapacityOfSlot2>0)
							{
							
							}
							
							if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
							{
							
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount-RemainingPeopleInCompartment2);
							
						 	}
						 	else
							{
							
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(WednesdaySlotValue)-RemainingCapacityOfSlot2);
							
							}
							
							slotcapacity.set(WednesdaySlotValue,(int)RemainingCapacityOfSlot2);
							
							if(RemainingCapacityOfSlot2<=0)
							{
								RemainingCapacityOfSlot2=0;
								WednesdaySlotTimings++;
								WednesdaySlotValue++;
						 	}
			        }
						 
					while(RemainingPeopleInCompartment2>=slotcapacity.get(WednesdaySlotValue))
					{
				
						if(RemainingCapacityOfSlot2<=0)
						{
							RemainingCapacityOfSlot2=0;
							 
						}
			
						PeopleLeftInCompartment=RemainingPeopleInCompartment2;
						RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(WednesdaySlotValue);
						
						
						
					
 					
						DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
						DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();

						
						DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					
						RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(WednesdaySlotValue))-EnteredPeopleCount);
						
						if(RemainingCapacityOfSlot2>0)
						{
						
						}
						
						if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
						{
							
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(PeopleLeftInCompartment-RemainingPeopleInCompartment2);
							
					 	}
					  	else
						{
							if(RemainingCapacityOfSlot2<0)
							{
								RemainingCapacityOfSlot2=0;
							
							}
							
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(PeopleLeftInCompartment-RemainingCapacityOfSlot2);
						
						}
						 	WednesdaySlotTimings++;
						WednesdaySlotValue++; 
						
						if(WednesdaySlotValue>SumOfWedThuDocumentCount-1)
						{
				        	
				        	break;
						}
				   	}
					 if((RemainingPeopleInCompartment2>0)&&(RemainingPeopleInCompartment2<slotcapacity.get(WednesdaySlotValue)))
					{
					  
						
						DarshanTimeInHours=WednesdaySlotTime.get(WednesdaySlotTimings).getHours();
						DarshanTimeInMinutes=WednesdaySlotTime.get(WednesdaySlotTimings).getMinutes();
					
						
						
						DarshanTimeInHoursandMinutes=(WednesdaySlotTime.get(WednesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					
						RemainingCapacityOfSlot2=((slotcapacity.get(WednesdaySlotValue))-RemainingPeopleInCompartment2);
						RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(WednesdaySlotValue);
						
						
						if(RemainingCapacityOfSlot2>0)
						{
							
						}
						
						
						if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
						{
							
							if(RemainingPeopleInCompartment2<0) 
								
							{
								RemainingPeopleInCompartment2=0;
							 
							 }	
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+RemainingPeopleInCompartment2;
				
							
						}
			 
						else
						{
							
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(WednesdaySlotValue))-(RemainingCapacityOfSlot2);
					
						}
				 	}
					if(RemainingPeopleInCompartment2<0) 
						
					{
						RemainingPeopleInCompartment2=0;
				  }	
					
					EnteringPeopleTime++;
				 }
				
				else{
					RemainingCapacityOfSlot2=0;
					WednesdaySlotTimings++;
					WednesdaySlotValue++;
					
					break;
				 }
				WednesdaySlotTimings--;
			 }
	  } 
	 	 	 VQC2WedResult.add(RemainingPeopleInCompartment2);
	 	VQC2WedResult.add(RemainingCapacityOfSlot2);
	 	VQC2WedResult.add((double)TimeUnit.MILLISECONDS.toMinutes(DarshanTimeInHoursandMinutes));
	 	VQC2WedResult.add((double)TimeUnit.MILLISECONDS.toHours(DarshanTimeInHoursandMinutes));
	 	VQC2WedResult.add((double)DarshanTimeInHours);
	 	VQC2WedResult.add((double)DarshanTimeInMinutes);
	 	VQC2WedResult.add(PeopleConsideredForDarshanVQC2);
	  	return VQC2WedResult; 
	 	
	 	
	}
	
	
}






