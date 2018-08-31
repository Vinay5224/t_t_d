


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
public class VQCIITuesday {
	
	
	static	ArrayList<String> Slot = new ArrayList<String>();
	
	
	static String str,str1,currentdate,PeopleEnteringTime, Date,VQC2ExactTimeFromDB,DBLastDocumentTime,DBLastDocumentDay,currentday,u,DBToTiming, TuesdayToTiming ,Remainingslot;
	static int Count = 0,DocumentCountOfTuesdayCollection=0,DocumentCountOfWednesdaycollection=0,w,z,SumOfTueWedDocumentCount,TuesdayDocumentid,e, grt, TuesdayCollCount;
	static long TuesdayCollectionCount;


	public static ArrayList<Double> main(String[] args) throws NumberFormatException, IOException, ParseException {
				// TODO Auto-generated method stub
	 
		int NumberOfPeopleEnteredIntoCompartment2=VQCIIServlet.PeopleEnteredIntoCompartment2;
		 
		String EnteringTimeOfCompartment2=args[1];
		double RemainingPeopleIntheCompartment2=Double.parseDouble(args[2]),RemainingcapacityOftheSlot2=Double.parseDouble(args[3]);
	 
		 
		ArrayList<String> slotTimings1Tue= new ArrayList<>();
		ArrayList<Integer>  slotCapacity1Tue = new ArrayList<>();
		 
		ArrayList<String> slotTimings1Wed= new ArrayList<>(); 
		 
	
				 
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
     	 	
     	 	    u =  DBLastDocumentTime;
     	 	
     	 	    
     	 }
     	    }  
           }
         
		  w=DocumentCountOfTuesdayCollection;
		  w=0;
		  DocumentCountOfTuesdayCollection=w;
		 
		  
		  z=DocumentCountOfWednesdaycollection;
		  z=0;
		  DocumentCountOfWednesdaycollection=z;
		
		  
		   DBCollection TuesdayCollection = db.getCollection("Tuesday"); 
			
			  
		
			
			TuesdayCollectionCount =TuesdayCollection.count();
			
			TuesdayCollCount = (int) TuesdayCollectionCount;
			 if(currentday.equals(DBLastDocumentDay))
			  {
				 
				  SimpleDateFormat curFormater = new SimpleDateFormat("H:m"); 
		            Date dateObj = curFormater.parse(DBLastDocumentTime); 
		            SimpleDateFormat postFormater = new SimpleDateFormat("HH:mm"); 
		            String newDateStr = postFormater.format(dateObj); 
		           
				 
				  BasicDBObject que = new BasicDBObject();
				  que.put("To", newDateStr);
				  DBCursor cursor = TuesdayCollection.find(que);
				  
				  while(cursor.hasNext())
					 {
					  DBObject result=cursor.next();
					  Map resultmap=result.toMap();
					  TuesdayToTiming=  (String)resultmap.get("To");
					  TuesdayDocumentid = (Integer) resultmap.get("ID");
					  
					  TuesdayDocumentid++;
					  for (int LastDocumentID=TuesdayDocumentid; LastDocumentID<TuesdayCollCount; LastDocumentID++ ){
						  BasicDBObject ques2 = new BasicDBObject("ID", TuesdayDocumentid);
						  ques2.put("ID", TuesdayDocumentid);
						  DBCursor cursorv = TuesdayCollection.find(ques2);
						  
						  while(cursorv.hasNext())
							 {
							  DBObject resultv=cursorv.next();
							  Map resultmapv=resultv.toMap();
						   DBToTiming = (String) resultmapv.get("To");
						 TuesdayDocumentid++;
						
						  
						  String DBVQC2MaxCapacity=(String)resultmapv.get("VQC2_Max_Capacity");
						  
							
						  if(!DBVQC2MaxCapacity.equals("")){
							  int dbVQC2MaxCapacity =  Integer.parseInt((String)resultmapv.get("VQC2_Max_Capacity"));
							  slotCapacity1Tue.add(dbVQC2MaxCapacity);
							
							  slotTimings1Tue.add(DBToTiming);
							
							  
							  DocumentCountOfTuesdayCollection++;
							
					   }
					    }
					 }}
		
				  }
				  
			 else if(DBLastDocumentDay.equals("Monday"))
			 {
				 
				 DBCursor cursor = TuesdayCollection.find();
				  while(cursor.hasNext())
					 {
					  DBObject result=cursor.next();
					  Map resultmap=result.toMap();
					  
					  String ToTiming=  (String)resultmap.get("To");
					  String DBVQC2MaxCapacity=(String)resultmap.get("VQC2_Max_Capacity");
					  if(!DBVQC2MaxCapacity.equals("")){
						  int dbVQC2MaxCapacity =  Integer.parseInt((String)resultmap.get("VQC2_Max_Capacity"));
						  slotCapacity1Tue.add(dbVQC2MaxCapacity);
						  slotTimings1Tue.add(ToTiming);
						  DocumentCountOfTuesdayCollection++;
						
					   }
					 }
				   
			 }
	   DBCollection WednesdayCollection = db.getCollection("Wednesday");
       
          DBCursor cursor1 = WednesdayCollection.find();
		         while(cursor1.hasNext())
				 {
				  DBObject result=cursor1.next();
				  Map resultmap=result.toMap();
				  
				  String ToTiming=  (String)resultmap.get("To");
				  String DBVQC2MaxCapacity=(String)resultmap.get("VQC2_Max_Capacity");
				  if(!DBVQC2MaxCapacity.equals("")){
					  int dbVQC2MaxCapacity=  Integer.parseInt((String)resultmap.get("VQC2_Max_Capacity"));
					slotCapacity1Tue.add(dbVQC2MaxCapacity); 
					  
					  slotTimings1Wed.add(ToTiming);
					  
					  DocumentCountOfWednesdaycollection++;
					 
					   
				  }
				   }
		         
		         
		     	ArrayList<Double> Res2= new ArrayList<>();	  
			 Res2=estimatedtime(Entering_time, slotTimings1Tue, slotTimings1Wed, slotCapacity1Tue,RemainingPeopleIntheCompartment2,RemainingcapacityOftheSlot2,NumberOfPeopleEnteredIntoCompartment2);
			
			return Res2;
		}

	public static ArrayList<Double> estimatedtime( String[] Entering_time,
			ArrayList<String> slotTimingsTue,
			ArrayList<String> slotTimingsWed,
			ArrayList<Integer> slotcapacity,
			double RemainingPeopleInCompartment2,
			double RemainingCapacityOfSlot2,
			
		 	int NumberOfPeopleEntered2 ) throws IOException, ParseException {
 
		double PeopleConsideredForDarshanVQC2=0;
		long  DarshanTimeInHoursandMinutes=0;
		long DarshanTimeInHours =0;
		long DarshanTimeInMinutes=0;
		double PeopleLeftInCompartment;
		
		 
	int TuesdaySlotValue = VQCIIServlet.VQC2TueSlotValueCount;
			
		int lent= 0;
		
		ArrayList<Double> VQC2TuesResult= new ArrayList<>();
		 ArrayList<Date> TuesdaySlotTime = new ArrayList<>();
		ArrayList<Date> WednesdaySlotTime= new ArrayList<>();
		ArrayList<String>slotTimings= new ArrayList<>();
		
		slotTimings.addAll(slotTimingsTue);
		slotTimings.addAll(slotTimingsWed);
		
	 	ArrayList<Date> enteredTime = new ArrayList<>();
		 
		for ( String h:slotTimingsTue){
			  
				SimpleDateFormat format = new SimpleDateFormat("HH:mm");
				Date date1 = format.parse(h);
				TuesdaySlotTime.add(date1);
	 	}
	 	for ( String h:slotTimingsWed){
		 	SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);
			Calendar c = Calendar.getInstance();
			c.setTime(date2);
			c.add(Calendar.DATE, 1); // number of days to add
		 	Date currentDatePlusOne = c.getTime();
		 	WednesdaySlotTime.add(currentDatePlusOne);
		 	}
		
		TuesdaySlotTime.addAll(WednesdaySlotTime);
		 
		for ( String h:Entering_time){
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);	
			enteredTime.add(date2);
		}
	  
		SumOfTueWedDocumentCount=0;
		
	  SumOfTueWedDocumentCount=DocumentCountOfTuesdayCollection+DocumentCountOfWednesdaycollection;
	
		
	 
	 	for(int EnteringPeopleTime = 0;EnteringPeopleTime<Entering_time.length;EnteringPeopleTime++) {

	 			for(int TuesdaySlotTimings =VQCIIServlet.VQC2TueSlotTimeCount;TuesdaySlotTimings<slotTimings.size();TuesdaySlotTimings++){
					 
					if (EnteringPeopleTime>0){
						VQCIIServlet.VQC2TueSlotTimeCount=TuesdaySlotTimings;
						VQCIIServlet.VQC2TueSlotValueCount=TuesdaySlotValue;
						break;
					 
					}
					 
					double EnteredPeopleCount = NumberOfPeopleEntered2;
					 
			         
					if(EnteringPeopleTime>20){
						
						break;
					}
					
					
					if((RemainingCapacityOfSlot2>0)&&((enteredTime.get(0).compareTo(TuesdaySlotTime.get(TuesdaySlotTimings))) >=0 ))
					{
						 
						TuesdaySlotTimings++;
						TuesdaySlotValue++;
						RemainingCapacityOfSlot2=0;
					}
					  
					if( (TuesdaySlotTime.get(TuesdaySlotTimings).compareTo(enteredTime.get(0))) >=0)
					{
						
					 
				        if(TuesdaySlotValue>SumOfTueWedDocumentCount-1)
						{
				        	
				        	
				        	if(EnteredPeopleCount> slotcapacity.get(TuesdaySlotValue))
					        {
							 
					        	
					        	if(RemainingCapacityOfSlot2<=0)
								{
									RemainingCapacityOfSlot2=0;
							
								}
					        	if(RemainingCapacityOfSlot2>0)
								{
									slotcapacity.set(TuesdaySlotValue,(int)RemainingCapacityOfSlot2);
								}
							 
				
								
					        	RemainingPeopleInCompartment2=RemainingPeopleInCompartment2+EnteredPeopleCount-(slotcapacity.get(TuesdaySlotValue));
						
						 
								DarshanTimeInHoursandMinutes=(TuesdaySlotTime.get(TuesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
								DarshanTimeInHours=TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
								DarshanTimeInMinutes=TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
							
								
								
								RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(TuesdaySlotValue))-EnteredPeopleCount);
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
						 if(EnteredPeopleCount<=slotcapacity.get(TuesdaySlotValue)){
							  
							 if(RemainingCapacityOfSlot2<=0)
								{
									RemainingCapacityOfSlot2=0;
								
								}
							 
							
							if(RemainingCapacityOfSlot2>0)
							{
								slotcapacity.set(TuesdaySlotValue,(int)RemainingCapacityOfSlot2);;
							}
							
					
				        	 
							DarshanTimeInHoursandMinutes=(TuesdaySlotTime.get(TuesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							DarshanTimeInHours=TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
							DarshanTimeInMinutes=TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
					
							if(RemainingPeopleInCompartment2>0)
							{
					
								RemainingCapacityOfSlot2=((slotcapacity.get(TuesdaySlotValue))-RemainingPeopleInCompartment2);
							}
							else
							{
							
								RemainingCapacityOfSlot2=((slotcapacity.get(TuesdaySlotValue))-EnteredPeopleCount);
							}
							RemainingPeopleInCompartment2=EnteredPeopleCount-slotcapacity.get(TuesdaySlotValue);
					
							
							if(RemainingCapacityOfSlot2>0)
							{
								
							}
							slotcapacity.set(TuesdaySlotValue,(int)RemainingCapacityOfSlot2);
							
							if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
							{
							
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount-RemainingPeopleInCompartment2);
						
								
							}
						 
							else
							{
								
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(TuesdaySlotValue)-RemainingCapacityOfSlot2);
						
							}
							
							
							
							if(RemainingCapacityOfSlot2<=0)
							{
								 
								RemainingCapacityOfSlot2=0;
						 		 
							}
			        }
					while(RemainingPeopleInCompartment2>=slotcapacity.get(TuesdaySlotValue))
					{
						
						if(RemainingCapacityOfSlot2<=0)
						{
							RemainingCapacityOfSlot2=0;
						 
						}
			
						PeopleLeftInCompartment=RemainingPeopleInCompartment2;
						RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(TuesdaySlotValue);
						
						if(TuesdaySlotValue>SumOfTueWedDocumentCount-1)
						{
				       
							break;
						}
				       
						
					
						 
						
						DarshanTimeInHoursandMinutes=(TuesdaySlotTime.get(TuesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
						DarshanTimeInMinutes=TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
				
						RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(TuesdaySlotValue))-EnteredPeopleCount);
						
						if(RemainingCapacityOfSlot2>0)
						{
						
						}
						
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
					
					if((RemainingPeopleInCompartment2>0)&&(RemainingPeopleInCompartment2<slotcapacity.get(TuesdaySlotValue)))
					{
						
						 
						DarshanTimeInHoursandMinutes=(TuesdaySlotTime.get(TuesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
						DarshanTimeInMinutes=TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
					
						RemainingCapacityOfSlot2=((slotcapacity.get(TuesdaySlotValue))-RemainingPeopleInCompartment2);
						RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(TuesdaySlotValue);
					
						
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
							
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(TuesdaySlotValue))-(RemainingCapacityOfSlot2);
					
						}
					 }
					if(RemainingPeopleInCompartment2<0){
						RemainingPeopleInCompartment2=0;
					  }	
				        	
							break;
						}
				       
							 if(EnteredPeopleCount>slotcapacity.get(TuesdaySlotValue))
						        {
							 
						        	
						        	if(RemainingCapacityOfSlot2<=0)
									{
										RemainingCapacityOfSlot2=0;
									
									}
						        	if(RemainingCapacityOfSlot2>0)
									{
										slotcapacity.set(TuesdaySlotValue,(int)RemainingCapacityOfSlot2);;
									}
								 
						     
									
						        	RemainingPeopleInCompartment2=RemainingPeopleInCompartment2+EnteredPeopleCount-(slotcapacity.get(TuesdaySlotValue));
								
									DarshanTimeInHoursandMinutes=(TuesdaySlotTime.get(TuesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
									DarshanTimeInHours=TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
									DarshanTimeInMinutes=TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
						
									PeopleConsideredForDarshanVQC2=EnteredPeopleCount-RemainingPeopleInCompartment2;
						
							
									RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(TuesdaySlotValue))-EnteredPeopleCount);
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
										TuesdaySlotTimings++;
										TuesdaySlotValue++;
										 
									}
							    }
							 if(EnteredPeopleCount<=slotcapacity.get(TuesdaySlotValue)){
								  if(RemainingCapacityOfSlot2<=0)
									{
										RemainingCapacityOfSlot2=0;
									
									}
								 
								
								if(RemainingCapacityOfSlot2>0)
								{
									slotcapacity.set(TuesdaySlotValue,(int)RemainingCapacityOfSlot2);;
								}
								
						
								
							 	DarshanTimeInHoursandMinutes=(TuesdaySlotTime.get(TuesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
								DarshanTimeInHours=TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
								DarshanTimeInMinutes=TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
						
								if(RemainingPeopleInCompartment2>0)
								{
							
									RemainingCapacityOfSlot2=((slotcapacity.get(TuesdaySlotValue))-RemainingPeopleInCompartment2);
								}
								else
								{
								
									RemainingCapacityOfSlot2=((slotcapacity.get(TuesdaySlotValue))-EnteredPeopleCount);
								}
								RemainingPeopleInCompartment2=EnteredPeopleCount-slotcapacity.get(TuesdaySlotValue);
							
								PeopleConsideredForDarshanVQC2=EnteredPeopleCount-RemainingPeopleInCompartment2;
							
								if(RemainingCapacityOfSlot2>0)
								{
								
								}
								
								if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
								{
								
									PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount-RemainingPeopleInCompartment2);
							
								}
							 
								else
								{
									
									PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(TuesdaySlotValue)-RemainingCapacityOfSlot2);
							
								}
							 	slotcapacity.set(TuesdaySlotValue,(int)RemainingCapacityOfSlot2);;
							 	if(RemainingCapacityOfSlot2<=0)
								{
							 
									RemainingCapacityOfSlot2=0;
									TuesdaySlotTimings++;
									TuesdaySlotValue++;
									 
								}
				        }
						while(RemainingPeopleInCompartment2>=slotcapacity.get(TuesdaySlotValue))
						{
					
							if(RemainingCapacityOfSlot2<=0)
							{
								RemainingCapacityOfSlot2=0;
								 
							}
						
							PeopleLeftInCompartment=RemainingPeopleInCompartment2;
							RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(TuesdaySlotValue);
				
	 
							DarshanTimeInHoursandMinutes=(TuesdaySlotTime.get(TuesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							DarshanTimeInHours=TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
							DarshanTimeInMinutes=TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
					
							RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(TuesdaySlotValue))-EnteredPeopleCount);
							
							if(RemainingCapacityOfSlot2>0)
							{
								
							}
							
							
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
						 	TuesdaySlotTimings++;
							TuesdaySlotValue++; 
							
							if(TuesdaySlotValue>SumOfTueWedDocumentCount-1)
							{
					        	
								break;
							}
						}
						
						if((RemainingPeopleInCompartment2>0)&&(RemainingPeopleInCompartment2<slotcapacity.get(TuesdaySlotValue)))
						{
							
							DarshanTimeInHoursandMinutes=(TuesdaySlotTime.get(TuesdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							DarshanTimeInHours=TuesdaySlotTime.get(TuesdaySlotTimings).getHours();
							DarshanTimeInMinutes=TuesdaySlotTime.get(TuesdaySlotTimings).getMinutes();
				
							RemainingCapacityOfSlot2=((slotcapacity.get(TuesdaySlotValue))-RemainingPeopleInCompartment2);
							RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(TuesdaySlotValue);
		
							
							if(RemainingCapacityOfSlot2>0)
							{
								
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
						TuesdaySlotTimings++;
						TuesdaySlotValue++;
						
						break;
					 }
					TuesdaySlotTimings--;
					
				}
	 	 	} 
        VQC2TuesResult.add(RemainingPeopleInCompartment2);
	 	VQC2TuesResult.add(RemainingCapacityOfSlot2);
	 	VQC2TuesResult.add((double)TimeUnit.MILLISECONDS.toMinutes(DarshanTimeInHoursandMinutes));
	 	VQC2TuesResult.add((double)TimeUnit.MILLISECONDS.toHours(DarshanTimeInHoursandMinutes));
	 	VQC2TuesResult.add((double)DarshanTimeInHours);
	 	VQC2TuesResult.add((double)DarshanTimeInMinutes);
	 	VQC2TuesResult.add(PeopleConsideredForDarshanVQC2);
	 	 
	 	return VQC2TuesResult; 
 	}
 	 
}






