


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
public class VQCIMonday {
	
	
	
	
	static String str,str1,currentdate,PeopleEnteringTime, Date,VQC1ExactTimeFromDB,DBLastDocumentTime,DBLastDocumentDay,currentday,u,DBToTiming, MondayToTiming ,Remainingslot;
	static int Count = 0,DocumentCountOfMondayCollection=0,DocumentCountOfTuesdayCollection=0,w,z,SumOfMonTueDocumentCount,MondayDocumentid,e, grt, MondayCollCount;
	static long MondayCollectionCount;
	

	public static ArrayList<Double> main(String[] args) throws NumberFormatException, IOException, ParseException {
				
		int NumberOfPeopleEnteredIntoCompartment=VQCIServlet.PeopleEnteredIntoCompartment;
		String EnteringTimeOfCompartment=args[1];
		double RemainingPeopleIntheCompartment1=Double.parseDouble(args[2]),RemainingCapacityOftheSlot1=Double.parseDouble(args[3]);
		
		ArrayList<String> slotTimings1Mon= new ArrayList<>();
		ArrayList<Integer>  slotCapacity1Mon = new ArrayList<>();
		ArrayList<String> slotTimings1Tue= new ArrayList<>(); 
		
		String Entering_time[]={EnteringTimeOfCompartment};
				
		currentdate=VQCIServlet.UICurrentDate;
		currentday=VQCIServlet.UICurrentDay;
		PeopleEnteringTime=VQCIServlet.UIEnteringTime;
		
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
    	     			
    			  }
    			  }
     			 Remainingslot=(String)resultmap.get("Remainingslot");
    			
     			  
     	   
     	   if(!(VQC1ExactTimeFromDB==null))
     	    {
     	    	 String[] Cdate=VQC1ExactTimeFromDB.split("-");
     	    	 DBLastDocumentTime=Cdate[0];
     	    	 DBLastDocumentDay=Cdate[1];
     	 	 
     	 	    u =  DBLastDocumentTime;
     	 	    
     	 	   
     	 }
     	    }  
             
        	 
         }
      
      
       
		  
		  w=DocumentCountOfMondayCollection;
		  w=0;
		  DocumentCountOfMondayCollection=w;
		 
		  
		  z=DocumentCountOfTuesdayCollection;
		  z=0;
		  DocumentCountOfTuesdayCollection=z;
	
		 
		
			  DBCollection MondayCollection = db.getCollection("Monday"); 
		
			  

			
			MondayCollectionCount =MondayCollection.count();
			
			MondayCollCount = (int) MondayCollectionCount;
			 if(currentday.equals(DBLastDocumentDay))
			  {
				  System.out.println("Day::"+currentday+"--------"+"Cday::"+DBLastDocumentDay);
				 
				  SimpleDateFormat curFormater = new SimpleDateFormat("H:m");
					Date dateObj = curFormater.parse(DBLastDocumentTime);
					SimpleDateFormat postFormater = new SimpleDateFormat("HH:mm");
					String newDateStr = postFormater.format(dateObj);

				  
				  BasicDBObject que = new BasicDBObject();
				  que.put("To", DBLastDocumentTime);
				  DBCursor cursor = MondayCollection.find(que);
				  
				  while(cursor.hasNext())
					 {
					  DBObject result=cursor.next();
					  Map resultmap=result.toMap();
					  MondayToTiming=  (String)resultmap.get("To");
					  MondayDocumentid = (Integer) resultmap.get("ID");
					
					  MondayDocumentid++;
					  for (int LastDocumentID=MondayDocumentid; LastDocumentID<MondayCollCount; LastDocumentID++ ){
						  BasicDBObject ques2 = new BasicDBObject("ID", MondayDocumentid);
						  ques2.put("ID", MondayDocumentid);
						  DBCursor cursorv = MondayCollection.find(ques2);
						  
						  while(cursorv.hasNext())
							 {
							  DBObject resultv=cursorv.next();
							  Map resultmapv=resultv.toMap();
										  
						  
						  DBToTiming = (String) resultmapv.get("To");
						 MondayDocumentid++;
						
						  
						  String DBVQC1MaxCapacity=(String)resultmapv.get("VQC1_Max_Capacity");
						  
							
						  if(!DBVQC1MaxCapacity.equals("")){
							  int dbVQC1MaxCapacity=  Integer.parseInt((String)resultmapv.get("VQC1_Max_Capacity"));
							  slotCapacity1Mon.add(dbVQC1MaxCapacity);
							
							  slotTimings1Mon.add(DBToTiming);
							
							  
							  DocumentCountOfMondayCollection++;
					
							  
					
							
						  }
					  
							 }
					 }}
				
				  }
				  
			 else if(DBLastDocumentDay.equals("Sunday"))
			 {
				 
				 DBCursor cursor = MondayCollection.find();
				  while(cursor.hasNext())
					 {
					  DBObject result=cursor.next();
					  Map resultmap=result.toMap();
					  
					  String ToTiming=  (String)resultmap.get("To");
					  String DBVQC1MaxCapacity=(String)resultmap.get("VQC1_Max_Capacity");
					  if(!DBVQC1MaxCapacity.equals("")){
						  int dbVQC1MaxCapacity=  Integer.parseInt((String)resultmap.get("VQC1_Max_Capacity"));
						  slotCapacity1Mon.add(dbVQC1MaxCapacity);
						  slotTimings1Mon.add(ToTiming);
						  DocumentCountOfMondayCollection++;
						
					  }
					 }
				
			 }
			  

		  
	
        DBCollection TuesdayCollection = db.getCollection("Tuesday");
   
          
         DBCursor cursor1 = TuesdayCollection.find();
         
		         while(cursor1.hasNext())
				 {
				  DBObject result=cursor1.next();
				  Map resultmap=result.toMap();
				  
				  String ToTiming=  (String)resultmap.get("To");
				  String DBVQC1MaxCapacity=(String)resultmap.get("VQC1_Max_Capacity");
				  if(!DBVQC1MaxCapacity.equals("")){
					  int dbVQC1MaxCapacity=  Integer.parseInt((String)resultmap.get("VQC1_Max_Capacity"));
					slotCapacity1Mon.add(dbVQC1MaxCapacity); 
					  
					  slotTimings1Tue.add(ToTiming);
				
					  DocumentCountOfTuesdayCollection++;
		
					  
				  }
				
				 
		         }
		   

		     	ArrayList<Double> ResMon1= new ArrayList<>();	  
			 ResMon1=estimatedtime(Entering_time, slotTimings1Mon, slotTimings1Tue, slotCapacity1Mon,RemainingPeopleIntheCompartment1,RemainingCapacityOftheSlot1,NumberOfPeopleEnteredIntoCompartment);

			return ResMon1;
		}

	public static ArrayList<Double> estimatedtime( String[] Entering_time,
			ArrayList<String> slotTimingsMon,ArrayList<String> slotTimingsTue,
			ArrayList<Integer> slot_value,
			double RemainingPeopleInCompartment1,
			double RemainingCapacityOfSlot1,
		 	int NumberOfPeopleEntered
			
			) throws IOException, ParseException {

		double PeopleConsideredForDarshanVQC1 = 0;
		long  DarshanTimeInHoursandMinutes=0;
		long DarshanTimeInHours =0;
		long DarshanTimeInMinutes=0;
		double PeopleLeftInCompartment;
		
	int MondaySlotValue = VQCIServlet.VQC1MonSlotValueCount;

		int lent= 0;
		
		ArrayList<Double> VQC1MonResult= new ArrayList<>();
		
		 
		ArrayList<Date> MondaySlotTime = new ArrayList<>();
		ArrayList<Date> TuesdaySlotTime= new ArrayList<>();
		ArrayList<String>slotTimings= new ArrayList<>();
		slotTimings.addAll(slotTimingsMon);
		slotTimings.addAll(slotTimingsTue);
		
		
		
		ArrayList<Date> enteredTime = new ArrayList<>();
		 
		for ( String h:slotTimingsMon){
			  
				SimpleDateFormat format = new SimpleDateFormat("HH:mm");
				Date date1 = format.parse(h);
				MondaySlotTime.add(date1);
		 
		}
		 
		for ( String h:slotTimingsTue){
			  
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);
			Calendar c = Calendar.getInstance();
			c.setTime(date2);
			c.add(Calendar.DATE, 1); // number of days to add
		 	Date currentDatePlusOne = c.getTime();
		 	TuesdaySlotTime.add(currentDatePlusOne);
		 	}
		
		MondaySlotTime.addAll(TuesdaySlotTime);
		
	 
		
		for ( String h:Entering_time){
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);	
			enteredTime.add(date2);
		}
		SumOfMonTueDocumentCount=0;
	
	  SumOfMonTueDocumentCount=DocumentCountOfMondayCollection+DocumentCountOfTuesdayCollection;
	
	 
	 	for(int EnteringPeopleTime = 0;EnteringPeopleTime<Entering_time.length;EnteringPeopleTime++) {
	 		
	 		
			for(int MondaySlotTimings=VQCIServlet.VQC1MonSlotTimeCount;MondaySlotTimings<slotTimings.size();MondaySlotTimings++){
				 
				if (EnteringPeopleTime>0){
					VQCIServlet.VQC1MonSlotTimeCount=MondaySlotTimings;
					VQCIServlet.VQC1MonSlotValueCount=MondaySlotValue;
					break;
				 
				}
			
				double EnteredPeopleCount = NumberOfPeopleEntered;
				 
		         
				if(EnteringPeopleTime>23){
					
					break;
				}
			
				if((RemainingCapacityOfSlot1>0)&&((enteredTime.get(0).compareTo(MondaySlotTime.get(MondaySlotTimings))) >=0 ))
				{
				
					MondaySlotTimings++;
					MondaySlotValue++;
					RemainingCapacityOfSlot1=0;
				}
				
	
				if( (MondaySlotTime.get(MondaySlotTimings).compareTo(enteredTime.get(0))) >=0)
				{
		
			        if(MondaySlotValue>SumOfMonTueDocumentCount-1)
					{
			       
			        	if(EnteredPeopleCount> slot_value.get(MondaySlotValue))
				        {
			
				        	
				        	if(RemainingCapacityOfSlot1<=0)
							{
								RemainingCapacityOfSlot1=0;
								
							}
				        	if(RemainingCapacityOfSlot1>0)
							{
								slot_value.set(MondaySlotValue,(int)RemainingCapacityOfSlot1);
							}
					
							
				        	RemainingPeopleInCompartment1=RemainingPeopleInCompartment1+EnteredPeopleCount-(slot_value.get(MondaySlotValue));
						
							
							DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
							DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
						
							DarshanTimeInHoursandMinutes=(MondaySlotTime.get(MondaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
	
							
							RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slot_value.get(MondaySlotValue))-EnteredPeopleCount);
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
					 if(EnteredPeopleCount<=slot_value.get(MondaySlotValue)){
					
						 if(RemainingCapacityOfSlot1<=0)
							{
								RemainingCapacityOfSlot1=0;
							
							}
				
						if(RemainingCapacityOfSlot1>0)
						{
							slot_value.set(MondaySlotValue,(int)RemainingCapacityOfSlot1);;
						}
						
					
						
						
						DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
						DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
						
						DarshanTimeInHoursandMinutes=(MondaySlotTime.get(MondaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					
						if(RemainingPeopleInCompartment1>0)
						{
			
							RemainingCapacityOfSlot1=((slot_value.get(MondaySlotValue))-RemainingPeopleInCompartment1);
						}
						else
						{
							RemainingCapacityOfSlot1=((slot_value.get(MondaySlotValue))-EnteredPeopleCount);
						}
						RemainingPeopleInCompartment1=EnteredPeopleCount-slot_value.get(MondaySlotValue);
					
						
						if(RemainingCapacityOfSlot1>0)
						{
						
						}
						
						if((RemainingPeopleInCompartment1>0)&&(RemainingCapacityOfSlot1<=0))
						{
			
							PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(EnteredPeopleCount-RemainingPeopleInCompartment1);
			
							
						}
					
						else
						{
							
							PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(slot_value.get(MondaySlotValue)-RemainingCapacityOfSlot1);
						
						}
						
						slot_value.set(MondaySlotValue,(int)RemainingCapacityOfSlot1);;
						
						if(RemainingCapacityOfSlot1<=0)
						{
						
							RemainingCapacityOfSlot1=0;
					
						}
		        }
				while(RemainingPeopleInCompartment1>=slot_value.get(MondaySlotValue))
				{
				
					if(RemainingCapacityOfSlot1<=0)
					{
						RemainingCapacityOfSlot1=0;
						
					}
				
					PeopleLeftInCompartment=RemainingPeopleInCompartment1;
					RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slot_value.get(MondaySlotValue);
					
					if(MondaySlotValue>SumOfMonTueDocumentCount-1)
					{
			        
						break;
					}
			       
					
		
					DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
					DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
					
					
					
					DarshanTimeInHoursandMinutes=(MondaySlotTime.get(MondaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
				
					RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slot_value.get(MondaySlotValue))-EnteredPeopleCount);
					
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
				
				if((RemainingPeopleInCompartment1>0)&&(RemainingPeopleInCompartment1<slot_value.get(MondaySlotValue)))
				{
				
					DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
					DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
					
					
					DarshanTimeInHoursandMinutes=(MondaySlotTime.get(MondaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					RemainingCapacityOfSlot1=((slot_value.get(MondaySlotValue))-RemainingPeopleInCompartment1);
					RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slot_value.get(MondaySlotValue);
					
					
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
						
						PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(slot_value.get(MondaySlotValue))-(RemainingCapacityOfSlot1);
				
					}
					
					
				}
				if(RemainingPeopleInCompartment1<0){
					RemainingPeopleInCompartment1=0;
				
					
				 }	
			        	
						break;
					}
			        
			       if(EnteredPeopleCount==0)
			        {
			        	DarshanTimeInHoursandMinutes=0;
			        	
			        
			        }
			       
						 if(EnteredPeopleCount>slot_value.get(MondaySlotValue))
					        {
							
					        	
					        	if(RemainingCapacityOfSlot1<=0)
								{
									RemainingCapacityOfSlot1=0;
									
								}
					        	if(RemainingCapacityOfSlot1>0)
								{
									slot_value.set(MondaySlotValue,(int)RemainingCapacityOfSlot1);;
								}
							
					        	RemainingPeopleInCompartment1=RemainingPeopleInCompartment1+EnteredPeopleCount-(slot_value.get(MondaySlotValue));
							
								DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
								DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
							
								
								
								DarshanTimeInHoursandMinutes=(MondaySlotTime.get(MondaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							
								RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slot_value.get(MondaySlotValue))-EnteredPeopleCount);
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
									MondaySlotTimings++;
									MondaySlotValue++;
								
								}
							
						
								
					        }
						 
						 
						 if(EnteredPeopleCount<=slot_value.get(MondaySlotValue)){
							 
						
							 if(RemainingCapacityOfSlot1<=0)
								{
									RemainingCapacityOfSlot1=0;
								
								}
					
							if(RemainingCapacityOfSlot1>0)
							{
								slot_value.set(MondaySlotValue,(int)RemainingCapacityOfSlot1);;
							}
							
				
							
							DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
							DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
						
							DarshanTimeInHoursandMinutes=(MondaySlotTime.get(MondaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						
							DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
							DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
				
							if(RemainingPeopleInCompartment1>0)
							{
				
								RemainingCapacityOfSlot1=((slot_value.get(MondaySlotValue))-RemainingPeopleInCompartment1);
							}
							else
							{
							
								RemainingCapacityOfSlot1=((slot_value.get(MondaySlotValue))-EnteredPeopleCount);
							}
							RemainingPeopleInCompartment1=EnteredPeopleCount-slot_value.get(MondaySlotValue);
			
							
							if(RemainingCapacityOfSlot1>0)
							{
						
							}
							
							if((RemainingPeopleInCompartment1>0)&&(RemainingCapacityOfSlot1<=0))
							{
							
								PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(EnteredPeopleCount-RemainingPeopleInCompartment1);
							
							}
							
							else
							{
								PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(slot_value.get(MondaySlotValue)-RemainingCapacityOfSlot1);
						
							}
							
							slot_value.set(MondaySlotValue,(int)RemainingCapacityOfSlot1);
							
							if(RemainingCapacityOfSlot1<=0)
							{
								
								RemainingCapacityOfSlot1=0;
								MondaySlotTimings++;
								MondaySlotValue++;
								
							}
			        }
						 
					while(RemainingPeopleInCompartment1>=slot_value.get(MondaySlotValue))
					{
						
						if(RemainingCapacityOfSlot1<=0)
						{
							RemainingCapacityOfSlot1=0;
							
						}
						PeopleLeftInCompartment=RemainingPeopleInCompartment1;
						RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slot_value.get(MondaySlotValue);
						
						
						
						
						DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
						DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
					
						
					
						RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slot_value.get(MondaySlotValue))-EnteredPeopleCount);
						
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
						
						MondaySlotTimings++;
						MondaySlotValue++; 
						
						if(MondaySlotValue>SumOfMonTueDocumentCount-1)
						{
				        	
				        	break;
						}
				       
					}
					
					if((RemainingPeopleInCompartment1>0)&&(RemainingPeopleInCompartment1<slot_value.get(MondaySlotValue)))
					{
						
						DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
						DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
						DarshanTimeInHoursandMinutes=(MondaySlotTime.get(MondaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						
						RemainingCapacityOfSlot1=((slot_value.get(MondaySlotValue))-RemainingPeopleInCompartment1);
						RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slot_value.get(MondaySlotValue);
						
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
							PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(slot_value.get(MondaySlotValue))-(RemainingCapacityOfSlot1);
							
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
					MondaySlotTimings++;
					MondaySlotValue++;
					
					break;
					
					
					
					
				}
				MondaySlotTimings--;
				
			
			
	 		}
			 
		} 
	 	
	 	
	 	 VQC1MonResult.add(RemainingPeopleInCompartment1);
	 	VQC1MonResult.add(RemainingCapacityOfSlot1);
	 	VQC1MonResult.add((double)TimeUnit.MILLISECONDS.toMinutes(DarshanTimeInHoursandMinutes));
	 	VQC1MonResult.add((double)TimeUnit.MILLISECONDS.toHours(DarshanTimeInHoursandMinutes));
	 	VQC1MonResult.add((double)DarshanTimeInHours);
	 	VQC1MonResult.add((double)DarshanTimeInMinutes);
	 	VQC1MonResult.add(PeopleConsideredForDarshanVQC1);
	 	
	 	
	 	 
	 	return VQC1MonResult; 
	 	
	 	
	}
	
	
}






