


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
public class VQCIFriday {
	
	
	static	ArrayList<String> Slot = new ArrayList<String>();
	
	
	static String str,str1,currentdate,PeopleEnteringTime, Date,VQC1ExactTimeFromDB,DBLastDocumentTime,DBLastDocumentDay,currentday,u,Remainingslot,DBToTiming, FridayToTiming;
	static int Count = 0,DocumentCountOfFridayCollection=0,DocumentCountOfSaturdaycollection=0,w,z,SumOfFriSatDocumentCount,FridayDocumentid,e, grt, FridayCollCount;
	static long FridayCollectionCount;

	public static ArrayList<Double> main(String[] args) throws NumberFormatException, IOException, ParseException {
				// TODO Auto-generated method stub
		 
		int NumberOfPeopleEnteredIntoCompartment=VQCIServlet.PeopleEnteredIntoCompartment;
		System.out.println(NumberOfPeopleEnteredIntoCompartment);
		
		String EnteringTimeOfCompartment=args[1];
		double RemainingPeopleIntheCompartment1=Double.parseDouble(args[2]),RemainingcapacityOftheSlot1=Double.parseDouble(args[3]);
		
		 
		ArrayList<String> slotTimings1Fri= new ArrayList<>();
		ArrayList<Integer>  slotCapacity1Fri = new ArrayList<>();
		ArrayList<String> slotTimings1Sat= new ArrayList<>(); 
		
	
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
 	 	   
 	 	   
 	    }
 	    
 	    }  
         }   
       
		  w=DocumentCountOfFridayCollection;
		  w=0;
		  DocumentCountOfFridayCollection=w;
		  
		  z=DocumentCountOfSaturdaycollection;
		  z=0;
		  DocumentCountOfSaturdaycollection=z;
		  
		  
		   DBCollection FridayCollection = db.getCollection("Friday"); 
			  System.out.println("Collection Friday selected successfully");
			  
			
			FridayCollectionCount =FridayCollection.count();
			
			FridayCollCount = (int) FridayCollectionCount;
			
			
			 if(currentday.equals(DBLastDocumentDay))
			  {
				  System.out.println("Day::"+currentday+"--------"+"Cday::"+DBLastDocumentDay);
				  
				  SimpleDateFormat curFormater = new SimpleDateFormat("H:m"); 
		            Date dateObj = curFormater.parse(DBLastDocumentTime); 
		            SimpleDateFormat postFormater = new SimpleDateFormat("HH:mm"); 
		            String newDateStr = postFormater.format(dateObj); 
		             
				  
				 
				  BasicDBObject que = new BasicDBObject();
				  que.put("To", newDateStr);
				  DBCursor cursor = FridayCollection.find(que);
				  
				  while(cursor.hasNext())
					 {
					  DBObject result=cursor.next();
					  Map resultmap=result.toMap();
					  FridayToTiming=  (String)resultmap.get("To");
					  FridayDocumentid = (Integer) resultmap.get("ID");
					  
					  FridayDocumentid++;
					  for (int LastDocumentID=FridayDocumentid; LastDocumentID<FridayCollCount; LastDocumentID++ ){
						  BasicDBObject ques2 = new BasicDBObject("ID", FridayDocumentid);
						  ques2.put("ID", FridayDocumentid);
						  DBCursor cursorv = FridayCollection.find(ques2);
						  
						  while(cursorv.hasNext())
							 {
							  DBObject resultv=cursorv.next();
							  Map resultmapv=resultv.toMap();
										  
						  
						  DBToTiming = (String) resultmapv.get("To");
						 FridayDocumentid++;
						  
						  String DBVQC1MaxCapacity=(String)resultmapv.get("VQC1_Max_Capacity");
						  
							
						  if(!DBVQC1MaxCapacity.equals("")){
							  int dbVQC1MaxCapacity=  Integer.parseInt((String)resultmapv.get("VQC1_Max_Capacity"));
							  slotCapacity1Fri.add(dbVQC1MaxCapacity);
							
							  slotTimings1Fri.add(DBToTiming);
							  DocumentCountOfFridayCollection++;
							  
						   }
					  
							 }
					 }}
				  
			  }
				  
				  
		  
			 else if(DBLastDocumentDay.equals("Thursday"))
			 {
				 
				 DBCursor cursor = FridayCollection.find();
				  while(cursor.hasNext())
					 {
					  DBObject result=cursor.next();
					  Map resultmap=result.toMap();
					  
					  String ToTiming=  (String)resultmap.get("To");
					  String DBVQC1MaxCapacity=(String)resultmap.get("VQC1_Max_Capacity");
					  if(!DBVQC1MaxCapacity.equals("")){
						  int dbVQC1MaxCapacity=  Integer.parseInt((String)resultmap.get("VQC1_Max_Capacity"));
						  slotCapacity1Fri.add(dbVQC1MaxCapacity);
						  slotTimings1Fri.add(ToTiming);
						  DocumentCountOfFridayCollection++;
						  
					  }
					 }
				  
			 }
			  
		
         
      
        DBCollection SaturdayCollection = db.getCollection("Saturday");
         System.out.println("Collection Saturday selected successfully");
          
         DBCursor cursor1 = SaturdayCollection.find();
		
		
		         while(cursor1.hasNext())
				 {
				  DBObject result=cursor1.next();
				  Map resultmap=result.toMap();
				  
				  String ToTiming=  (String)resultmap.get("To");
				  String DBVQC1MaxCapacity=(String)resultmap.get("VQC1_Max_Capacity");
				  if(!DBVQC1MaxCapacity.equals("")){
					  int dbVQC1MaxCapacity=  Integer.parseInt((String)resultmap.get("VQC1_Max_Capacity"));
					slotCapacity1Fri.add(dbVQC1MaxCapacity); 
					  
					  slotTimings1Sat.add(ToTiming);
					  
					  DocumentCountOfSaturdaycollection++;
					  
				  }
				  
				 
		            
		         }
		         
		     	ArrayList<Double> ResFriI= new ArrayList<>();	  
			 ResFriI=estimatedtime(Entering_time, slotTimings1Fri, slotTimings1Sat, slotCapacity1Fri,RemainingPeopleIntheCompartment1,RemainingcapacityOftheSlot1,NumberOfPeopleEnteredIntoCompartment);
			return ResFriI;
		}

	public static ArrayList<Double> estimatedtime( String[] Entering_time,
			ArrayList<String> slotTimingsFri,
			ArrayList<String> slotTimingsSat,
			ArrayList<Integer> slotcapacity,
			
			double RemainingPeopleInCompartment1,
			double RemainingCapacityOfSlot1,
		 	int NumberOfPeopleEntered) throws IOException, ParseException {
		
		 
	
		double PeopleConsideredForDarshanVQC1=0;
		long  DarshanTimeInHoursandMinutes=0;
		long DarshanTimeInHours =0;
		long DarshanTimeInMinutes=0;
		double PeopleLeftInCompartment;
		

			
	  ArrayList<Double> VQC1FriResult= new ArrayList<>();
		ArrayList<Date> FridaySlotTime = new ArrayList<>();
		ArrayList<Date> SaturdaySlotTime= new ArrayList<>();
		ArrayList<String>slotTimings= new ArrayList<>();
		slotTimings.addAll(slotTimingsFri);
		slotTimings.addAll(slotTimingsSat);
		
		
		ArrayList<Date> enteredTime = new ArrayList<>();
		 
		for ( String h:slotTimingsFri){
			  
				SimpleDateFormat format = new SimpleDateFormat("HH:mm");
				Date date1 = format.parse(h);
				FridaySlotTime.add(date1);
		 
		}
		 
		for ( String h:slotTimingsSat){
			  
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);
			Calendar c = Calendar.getInstance();
			c.setTime(date2);
			c.add(Calendar.DATE, 1); // number of days to add
		 	Date currentDatePlusOne = c.getTime();
		 	SaturdaySlotTime.add(currentDatePlusOne);
		 	}
		
		FridaySlotTime.addAll(SaturdaySlotTime);
		
	 
		
		for ( String h:Entering_time){
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);	
			enteredTime.add(date2);
			
		}
		 
		
		if ( (FridaySlotTime.get(0).compareTo(enteredTime.get(0))) >0 ){
			
			
		}
		if ( (enteredTime.get(0).compareTo(FridaySlotTime.get(0))) > 0 ){
	
			
		}
		  
		SumOfFriSatDocumentCount=0;
		SumOfFriSatDocumentCount=DocumentCountOfFridayCollection+DocumentCountOfSaturdaycollection;
	
	  
		int FridaySlotValue = VQCIServlet.VQC1FriSlotValueCount;
	 	for(int EnteringPeopleTime = 0;EnteringPeopleTime<Entering_time.length;EnteringPeopleTime++) {
	 		
			for(int FridaySlotTimings=VQCIServlet.VQC1FriSlotTimeCount;FridaySlotTimings<slotTimings.size();FridaySlotTimings++){
				 
				if (EnteringPeopleTime>0){
					VQCIServlet.VQC1FriSlotTimeCount=FridaySlotTimings;
					VQCIServlet.VQC1FriSlotValueCount=FridaySlotValue;
					break;
				 
				}
				
				double EnteredPeopleCount = NumberOfPeopleEntered;
				 
		         
				if(EnteringPeopleTime>20){
					System.out.println("Day completed");
					break;
				}
				
				if((RemainingCapacityOfSlot1>0)&&((enteredTime.get(0).compareTo(FridaySlotTime.get(FridaySlotTimings))) >=0 ))
				{
					
					FridaySlotTimings++;
					FridaySlotValue++;
					RemainingCapacityOfSlot1=0;
				}
				
	
	if( (FridaySlotTime.get(FridaySlotTimings).compareTo(enteredTime.get(0))) >=0)
				{
					
			        if(FridaySlotValue>SumOfFriSatDocumentCount-1)
					{
			        	
			        
			        	if(EnteredPeopleCount> slotcapacity.get(FridaySlotValue))
				        {
						 
				        	
				        	if(RemainingCapacityOfSlot1<=0)
							{
								RemainingCapacityOfSlot1=0;
								
							}
				        	if(RemainingCapacityOfSlot1>0)
							{
								slotcapacity.set(FridaySlotValue,(int)RemainingCapacityOfSlot1);
							}
						 
				        	
				        	RemainingPeopleInCompartment1=RemainingPeopleInCompartment1+EnteredPeopleCount-(slotcapacity.get(FridaySlotValue));
							
							DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
							DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();
							
							RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slotcapacity.get(FridaySlotValue))-EnteredPeopleCount);
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
					 if(EnteredPeopleCount<=slotcapacity.get(FridaySlotValue)){
						 
					 
						 if(RemainingCapacityOfSlot1<=0)
							{
								RemainingCapacityOfSlot1=0;
								
							}
						 
						if(RemainingCapacityOfSlot1>0)
						{
							
							slotcapacity.set(FridaySlotValue,(int)RemainingCapacityOfSlot1);;
						}
						
						
						DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
						DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();
						
						if(RemainingPeopleInCompartment1>0)
						{
							RemainingCapacityOfSlot1=((slotcapacity.get(FridaySlotValue))-RemainingPeopleInCompartment1);
						}
						else
						{
							RemainingCapacityOfSlot1=((slotcapacity.get(FridaySlotValue))-EnteredPeopleCount);
							
						}
						RemainingPeopleInCompartment1=EnteredPeopleCount-slotcapacity.get(FridaySlotValue);
						
						if(RemainingCapacityOfSlot1>0)
						{
							
						}
						
						
						if((RemainingPeopleInCompartment1>0)&&(RemainingCapacityOfSlot1<=0))
						{
							PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(EnteredPeopleCount-RemainingPeopleInCompartment1);
							
						}
					
						else
						{
							PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(slotcapacity.get(FridaySlotValue)-RemainingCapacityOfSlot1);
							
						}
						
						slotcapacity.set(FridaySlotValue,(int)RemainingCapacityOfSlot1);
						
						if(RemainingCapacityOfSlot1<=0)
						{
						
							RemainingCapacityOfSlot1=0;
							
						
						}
		        }
				while(RemainingPeopleInCompartment1>=slotcapacity.get(FridaySlotValue))
				{
					
					if(RemainingCapacityOfSlot1<=0)
					{
						RemainingCapacityOfSlot1=0;
						
					}
					PeopleLeftInCompartment=RemainingPeopleInCompartment1;
					RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slotcapacity.get(FridaySlotValue);
					
					if(FridaySlotValue>SumOfFriSatDocumentCount-1)
					{
			        	System.out.println("slots are over");
						break;
					}
			       
					
					DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
					DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();
					
					RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slotcapacity.get(FridaySlotValue))-EnteredPeopleCount);
					
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
				
				if((RemainingPeopleInCompartment1>0)&&(RemainingPeopleInCompartment1<slotcapacity.get(FridaySlotValue)))
				{
					
					DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
					DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();
					
					RemainingCapacityOfSlot1=((slotcapacity.get(FridaySlotValue))-RemainingPeopleInCompartment1);
					RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slotcapacity.get(FridaySlotValue);
					
					if(RemainingCapacityOfSlot1>0)
					{
						
					}
					
				}
				if(RemainingPeopleInCompartment1<0){
					RemainingPeopleInCompartment1=0;
				 
					
				 }	
			        	
						break;
					}
			       
						 if(EnteredPeopleCount>slotcapacity.get(FridaySlotValue))
					        {
						 
					        	
					        	if(RemainingCapacityOfSlot1<=0)
								{
									RemainingCapacityOfSlot1=0;
									
								}
					        	if(RemainingCapacityOfSlot1>0)
								{
									slotcapacity.set(FridaySlotValue,(int)RemainingCapacityOfSlot1);;
								}
							 
					        	
					        	RemainingPeopleInCompartment1=RemainingPeopleInCompartment1+EnteredPeopleCount-(slotcapacity.get(FridaySlotValue));
								
								DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
								DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
								DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();
								
								RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slotcapacity.get(FridaySlotValue))-EnteredPeopleCount);
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
									FridaySlotTimings++;
									FridaySlotValue++;
									
								}
						
							
								
					        }
						 if(EnteredPeopleCount<=slotcapacity.get(FridaySlotValue)){
							 
							 
							 if(RemainingCapacityOfSlot1<=0)
								{
									RemainingCapacityOfSlot1=0;
									
								}
							 
							if(RemainingCapacityOfSlot1>0)
							{
								slotcapacity.set(FridaySlotValue,(int)RemainingCapacityOfSlot1);;
							}
							
							
							DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
							DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();
							
							if(RemainingPeopleInCompartment1>0)
							{
								RemainingCapacityOfSlot1=((slotcapacity.get(FridaySlotValue))-RemainingPeopleInCompartment1);
							}
							else
							{
								RemainingCapacityOfSlot1=((slotcapacity.get(FridaySlotValue))-EnteredPeopleCount);
							}
							RemainingPeopleInCompartment1=EnteredPeopleCount-slotcapacity.get(FridaySlotValue);
							
							if(RemainingCapacityOfSlot1>0)
							{
								
								
							}
							
							
							if((RemainingPeopleInCompartment1>0)&&(RemainingCapacityOfSlot1<=0))
							{
								PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(EnteredPeopleCount-RemainingPeopleInCompartment1);
								
								
							}
							
							else
							{
								PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(slotcapacity.get(FridaySlotValue)-RemainingCapacityOfSlot1);
								
							}
							
							slotcapacity.set(FridaySlotValue,(int)RemainingCapacityOfSlot1);
							
							if(RemainingCapacityOfSlot1<=0)
							{
								
								RemainingCapacityOfSlot1=0;
								FridaySlotTimings++;
								FridaySlotValue++;
								
							}
			        }
					while(RemainingPeopleInCompartment1>=slotcapacity.get(FridaySlotValue))
					{
						if(RemainingCapacityOfSlot1<=0)
						{
							RemainingCapacityOfSlot1=0;
							
						}
						PeopleLeftInCompartment=RemainingPeopleInCompartment1;
						RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slotcapacity.get(FridaySlotValue);
						
						DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
						DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();
						
						RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slotcapacity.get(FridaySlotValue))-EnteredPeopleCount);
						
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
						
						
						FridaySlotTimings++;
						FridaySlotValue++; 
						
						if(FridaySlotValue>SumOfFriSatDocumentCount-1)
						{
				        	
							break;
						}
					}
					
					if((RemainingPeopleInCompartment1>0)&&(RemainingPeopleInCompartment1<slotcapacity.get(FridaySlotValue)))
					{
						DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
						DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();
						
						RemainingCapacityOfSlot1=((slotcapacity.get(FridaySlotValue))-RemainingPeopleInCompartment1);
						RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slotcapacity.get(FridaySlotValue);
						
						if(RemainingCapacityOfSlot1>0)
						{
							
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
					FridaySlotTimings++;
					FridaySlotValue++;
					
					break;
					
					
				}
				FridaySlotTimings--;
				
			}
			
			
			 
		} 
	
	 	
	 	 VQC1FriResult.add(RemainingPeopleInCompartment1);
	 	VQC1FriResult.add(RemainingCapacityOfSlot1);
	 	VQC1FriResult.add((double)TimeUnit.MILLISECONDS.toMinutes(DarshanTimeInHoursandMinutes));
	 	VQC1FriResult.add((double)TimeUnit.MILLISECONDS.toHours(DarshanTimeInHoursandMinutes));
	 	VQC1FriResult.add((double)DarshanTimeInHours);
	 	VQC1FriResult.add((double)DarshanTimeInMinutes);
	 	VQC1FriResult.add(PeopleConsideredForDarshanVQC1);
	 	
	 	return VQC1FriResult; 
	 	
	 	
	}
	
	
}






