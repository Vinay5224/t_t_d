


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
public class VQCIISunday {
	
	
	static	ArrayList<String> Slot = new ArrayList<String>();
	
	
	static String str,str1,currentdate,PeopleEnteringTime, Date,VQC2ExactTimeFromDB,DBLastDocumentTime,DBLastDocumentDay,currentday,u,Remainingslot,DBToTiming, SundayToTiming;
	static int Count = 0,DocumentCountOfSundayCollection=0,DocumentCountOfMondaycollection=0,w,z,SumOfSunMonDocumentCount,SundayDocumentid,e, grt, SundayCollCount;
	static long SundayCollectionCount;

	public static ArrayList<Double> main(String[] args) throws NumberFormatException, IOException, ParseException {
				// TODO Auto-generated method stub
		 
		int NumberOfPeopleEnteredIntoCompartment2=VQCIIServlet.PeopleEnteredIntoCompartment2;
		String EnteringTimeOfCompartment2=args[1];
		double RemainingPeopleIntheCompartment2=Double.parseDouble(args[2]),RemainingcapacityOftheSlot2=Double.parseDouble(args[3]);
	 
		 
		ArrayList<String> slotTimings1Sun= new ArrayList<>();
		ArrayList<Integer>  slotCapacity1Sun = new ArrayList<>();
	 
		ArrayList<String> slotTimings1Mon= new ArrayList<>(); 
		 
	
				 
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
        w=DocumentCountOfSundayCollection;
		  w=0;
		  DocumentCountOfSundayCollection=w;
		
		  
		  z=DocumentCountOfMondaycollection;
		  z=0;
		  DocumentCountOfMondaycollection=z;
	
		 
			  DBCollection SundayCollection = db.getCollection("Sunday"); 
			 
			
			
			SundayCollectionCount =SundayCollection.count();
			
			SundayCollCount = (int) SundayCollectionCount;
			
			 if(currentday.equals(DBLastDocumentDay))
			  {
				
				  
				  SimpleDateFormat curFormater = new SimpleDateFormat("H:m"); 
		            Date dateObj = curFormater.parse(DBLastDocumentTime); 
		            SimpleDateFormat postFormater = new SimpleDateFormat("HH:mm"); 
		            String newDateStr = postFormater.format(dateObj); 
		          
				 
				  BasicDBObject que = new BasicDBObject();
				  que.put("To", newDateStr);
				  DBCursor cursor = SundayCollection.find(que);
				  
				  while(cursor.hasNext())
					 {
					  DBObject result=cursor.next();
					  Map resultmap=result.toMap();
					  SundayToTiming=  (String)resultmap.get("To");
					  SundayDocumentid = (Integer) resultmap.get("ID");
					  
					  SundayDocumentid++;
					  for (int LastDocumentID=SundayDocumentid; LastDocumentID<SundayCollCount; LastDocumentID++ ){
						  BasicDBObject ques2 = new BasicDBObject("ID", SundayDocumentid);
						  ques2.put("ID", SundayDocumentid);
						  DBCursor cursorv = SundayCollection.find(ques2);
						  
						  while(cursorv.hasNext())
							 {
							  DBObject resultv=cursorv.next();
							  Map resultmapv=resultv.toMap();
										  
						  
						  DBToTiming = (String) resultmapv.get("To");
						 SundayDocumentid++;
					
						  
						  String DBVQC2MaxCapacity =(String)resultmapv.get("VQC2_Max_Capacity");
						  
							
						  if(!DBVQC2MaxCapacity.equals("")){
							  int dbVQC2MaxCapacity=  Integer.parseInt((String)resultmapv.get("VQC2_Max_Capacity"));
							  slotCapacity1Sun.add(dbVQC2MaxCapacity);
							
							  slotTimings1Sun.add(DBToTiming);
							
							  DocumentCountOfSundayCollection++;
							 
						   }
					  
							 }
					 }}
				  
			  }
			  else if(DBLastDocumentDay.equals("Saturday"))
			 {
				 
				 DBCursor cursor = SundayCollection.find();
				  while(cursor.hasNext())
					 {
					  DBObject result=cursor.next();
					  Map resultmap=result.toMap();
					  
					  String ToTiming=  (String)resultmap.get("To");
					  String DBVQC2MaxCapacity=(String)resultmap.get("VQC2_Max_Capacity");
					  if(!DBVQC2MaxCapacity.equals("")){
						  int dbVQC2MaxCapacity=  Integer.parseInt((String)resultmap.get("VQC2_Max_Capacity"));
						  slotCapacity1Sun.add(dbVQC2MaxCapacity);
						  slotTimings1Sun.add(ToTiming);
						  
						  
						  DocumentCountOfSundayCollection++;
					
					  }
					 }
				
				 
			 }
	   DBCollection MondayCollection = db.getCollection("Monday");
        
          
         DBCursor cursor1 = MondayCollection.find();
		   
		         while(cursor1.hasNext())
				 {
				  DBObject result=cursor1.next();
				  Map resultmap=result.toMap();
				  
				  String ToTiming=  (String)resultmap.get("To");
				  String DBVQC2MaxCapacity=(String)resultmap.get("VQC2_Max_Capacity");
				  if(!DBVQC2MaxCapacity.equals("")){
					  int dbVQC2MaxCapacity=  Integer.parseInt((String)resultmap.get("VQC2_Max_Capacity"));
					slotCapacity1Sun.add(dbVQC2MaxCapacity); 
					  
					  slotTimings1Mon.add(ToTiming);
					  DocumentCountOfMondaycollection++;
				
				  }
				 }
		

		         
		     	ArrayList<Double> ResSunII= new ArrayList<>();	  
			 ResSunII=estimatedtime(Entering_time, slotTimings1Sun, slotTimings1Mon, slotCapacity1Sun,RemainingPeopleIntheCompartment2,RemainingcapacityOftheSlot2,NumberOfPeopleEnteredIntoCompartment2);
			
			return ResSunII;
		}

	public static ArrayList<Double> estimatedtime( String[] Entering_time,
			ArrayList<String> slotTimingsSun,
			ArrayList<String> slotTimingsMon,
			ArrayList<Integer> slotcapacity,
			
			double RemainingPeopleInCompartment2,
			double RemainingCapacityOfSlot2,
		 	int NumberOfPeopleEntered2 ) throws IOException, ParseException {
	 
		double PeopleConsideredForDarshanVQC2=0;
		long  DarshanTimeInHoursandMinutes=0;
		long DarshanTimeInHours =0;
		long DarshanTimeInMinutes=0;
		double PeopleLeftInCompartment ;
		 
	int SundaySlotValue = VQCIIServlet.VQC2SunSlotValueCount;
			
		int lent= 0;
		
		ArrayList<Double> VQC2SunResult= new ArrayList<>();
		 	ArrayList<Date> SundaySlotTime = new ArrayList<>();
		ArrayList<Date> MondaySlotTime= new ArrayList<>();
		ArrayList<String>slotTimings= new ArrayList<>();
		slotTimings.addAll(slotTimingsSun);
		slotTimings.addAll(slotTimingsMon);
		
		
		
		ArrayList<Date> enteredTime = new ArrayList<>();
		 
		for ( String h:slotTimingsSun){
			  
				SimpleDateFormat format = new SimpleDateFormat("HH:mm");
				Date date1 = format.parse(h);
				SundaySlotTime.add(date1);
		 
		}
		 
		for ( String h:slotTimingsMon){
			  
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);
			Calendar c = Calendar.getInstance();
			c.setTime(date2);
			c.add(Calendar.DATE, 1); // number of days to add
		 	Date currentDatePlusOne = c.getTime();
		 	MondaySlotTime.add(currentDatePlusOne);
		 	}
		
		SundaySlotTime.addAll(MondaySlotTime);
		
	 
		
		for ( String h:Entering_time){
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);	
			enteredTime.add(date2);
		}
		
		SumOfSunMonDocumentCount=0;
		
	  SumOfSunMonDocumentCount=DocumentCountOfSundayCollection+DocumentCountOfMondaycollection;

	  
	 
	 	for(int EnteringPeopleTime = 0;EnteringPeopleTime<Entering_time.length;EnteringPeopleTime++) {
	 		
			for(int SundaySlotTimings =VQCIIServlet.VQC2SunSlotTimeCount;SundaySlotTimings<slotTimings.size();SundaySlotTimings++){
				 
				if (EnteringPeopleTime>0){
					VQCIIServlet.VQC2SunSlotTimeCount=SundaySlotTimings;
					VQCIIServlet.VQC2SunSlotValueCount=SundaySlotValue;
					break;
				 
				}
			 
				double EnteredPeopleCount2 = NumberOfPeopleEntered2;
				 
		         
				if(EnteringPeopleTime>20){
				
					break;
				}
				
		
				if((RemainingCapacityOfSlot2>0)&&((enteredTime.get(0).compareTo(SundaySlotTime.get(SundaySlotTimings))) >=0 ))
				{
					 
					SundaySlotTimings++;
					SundaySlotValue++;
					RemainingCapacityOfSlot2=0;
				}
				
	 
				if( (SundaySlotTime.get(SundaySlotTimings).compareTo(enteredTime.get(0))) >=0)
				{
				
					 
			        if(SundaySlotValue>SumOfSunMonDocumentCount-1)
					{
			        	
			        
			        	if(EnteredPeopleCount2> slotcapacity.get(SundaySlotValue))
				        {
						  
				        	
				        	if(RemainingCapacityOfSlot2<=0)
							{
								RemainingCapacityOfSlot2=0;
							
							}
				        	if(RemainingCapacityOfSlot2>0)
							{
								slotcapacity.set(SundaySlotValue,(int)RemainingCapacityOfSlot2);
							}
						 
				       
				        	
				        	
							
				        	RemainingPeopleInCompartment2=RemainingPeopleInCompartment2+EnteredPeopleCount2-(slotcapacity.get(SundaySlotValue));
						
					
							 
							DarshanTimeInHoursandMinutes=(SundaySlotTime.get(SundaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							DarshanTimeInHours=SundaySlotTime.get(SundaySlotTimings).getHours();
							DarshanTimeInMinutes=SundaySlotTime.get(SundaySlotTimings).getMinutes();
			
							
							RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(SundaySlotValue))-EnteredPeopleCount2);
							if(RemainingCapacityOfSlot2>0)
							{
							
							}
							
							if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
							{
								
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount2-RemainingPeopleInCompartment2);
								
					
								
							}
							 
							else
							{
								
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount2-RemainingCapacityOfSlot2);
						
							}
						 	
				        }
					 if(EnteredPeopleCount2<=slotcapacity.get(SundaySlotValue)){
						 
						 
						 if(RemainingCapacityOfSlot2<=0)
							{
								RemainingCapacityOfSlot2=0;
							
							}
						 
				
						if(RemainingCapacityOfSlot2>0)
						{
							slotcapacity.set(SundaySlotValue,(int)RemainingCapacityOfSlot2);;
						}
						
				
			        
						DarshanTimeInHoursandMinutes=(SundaySlotTime.get(SundaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=SundaySlotTime.get(SundaySlotTimings).getHours();
						DarshanTimeInMinutes=SundaySlotTime.get(SundaySlotTimings).getMinutes();
					
						if(RemainingPeopleInCompartment2>0)
						{
				
							RemainingCapacityOfSlot2=((slotcapacity.get(SundaySlotValue))-RemainingPeopleInCompartment2);
						}
						else
						{
						
							RemainingCapacityOfSlot2=((slotcapacity.get(SundaySlotValue))-EnteredPeopleCount2);
						}
						RemainingPeopleInCompartment2=EnteredPeopleCount2-slotcapacity.get(SundaySlotValue);
					
						
						if(RemainingCapacityOfSlot2>0)
						{
						
						}
						
						if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
						{
							
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount2-RemainingPeopleInCompartment2);
				
							
						}
						 
						else
						{
						
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(SundaySlotValue)-RemainingCapacityOfSlot2);
						
						}
						
						slotcapacity.set(SundaySlotValue,(int)RemainingCapacityOfSlot2);
						
						if(RemainingCapacityOfSlot2<=0)
						{
						 
							RemainingCapacityOfSlot2=0;
						 
						}
		        }
				while(RemainingPeopleInCompartment2>=slotcapacity.get(SundaySlotValue))
				{
			
					if(RemainingCapacityOfSlot2<=0)
					{
						RemainingCapacityOfSlot2=0;
						 
					}
				
					PeopleLeftInCompartment=RemainingPeopleInCompartment2;
					RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(SundaySlotValue);
					
					if(SundaySlotValue>SumOfSunMonDocumentCount-1)
					{
			       
						break;
					}
			     
					 
					 	DarshanTimeInHoursandMinutes=(SundaySlotTime.get(SundaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					DarshanTimeInHours=SundaySlotTime.get(SundaySlotTimings).getHours();
					DarshanTimeInMinutes=SundaySlotTime.get(SundaySlotTimings).getMinutes();
					
					RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(SundaySlotValue))-EnteredPeopleCount2);
					
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
				
				if((RemainingPeopleInCompartment2>0)&&(RemainingPeopleInCompartment2<slotcapacity.get(SundaySlotValue)))
				{
					 
					
				 
					DarshanTimeInHoursandMinutes=(SundaySlotTime.get(SundaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					DarshanTimeInHours=SundaySlotTime.get(SundaySlotTimings).getHours();
					DarshanTimeInMinutes=SundaySlotTime.get(SundaySlotTimings).getMinutes();
				
					RemainingCapacityOfSlot2=((slotcapacity.get(SundaySlotValue))-RemainingPeopleInCompartment2);
					RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(SundaySlotValue);
			
					
					if(RemainingCapacityOfSlot2>0)
					{
					
					}
					
				}
				if(RemainingPeopleInCompartment2<0){
					RemainingPeopleInCompartment2=0;
					 
					
				 }	
			        
						break;
					}
			       
						 if(EnteredPeopleCount2>slotcapacity.get(SundaySlotValue))
					        {
							 
					        	
					        	if(RemainingCapacityOfSlot2<=0)
								{
									RemainingCapacityOfSlot2=0;
									
								}
					        	if(RemainingCapacityOfSlot2>0)
								{
									slotcapacity.set(SundaySlotValue,(int)RemainingCapacityOfSlot2);;
								}
							 
					   
					        	
					        
								
					        	RemainingPeopleInCompartment2=RemainingPeopleInCompartment2+EnteredPeopleCount2-(slotcapacity.get(SundaySlotValue));
							
						
							 
								DarshanTimeInHoursandMinutes=(SundaySlotTime.get(SundaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
								DarshanTimeInHours=SundaySlotTime.get(SundaySlotTimings).getHours();
								DarshanTimeInMinutes=SundaySlotTime.get(SundaySlotTimings).getMinutes();
						
								RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(SundaySlotValue))-EnteredPeopleCount2);
								if(RemainingCapacityOfSlot2>0)
								{
									
								}
								
								if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
								{
								
									PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount2-RemainingPeopleInCompartment2);
							
									
								}
								 
								else
								{
								
									PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount2-RemainingCapacityOfSlot2);
							
								}
								
								if(RemainingCapacityOfSlot2<=0)
								{
									 
									RemainingCapacityOfSlot2=0;
									SundaySlotTimings++;
									SundaySlotValue++;
								 
								}
						 
					        }
						 if(EnteredPeopleCount2<=slotcapacity.get(SundaySlotValue)){
							  if(RemainingCapacityOfSlot2<=0)
								{
									RemainingCapacityOfSlot2=0;
								
								}
							 
							
							if(RemainingCapacityOfSlot2>0)
							{
								slotcapacity.set(SundaySlotValue,(int)RemainingCapacityOfSlot2);;
							}
							
						
						 
							DarshanTimeInHoursandMinutes=(SundaySlotTime.get(SundaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							DarshanTimeInHours=SundaySlotTime.get(SundaySlotTimings).getHours();
							DarshanTimeInMinutes=SundaySlotTime.get(SundaySlotTimings).getMinutes();
						
							if(RemainingPeopleInCompartment2>0)
							{
		
								RemainingCapacityOfSlot2=((slotcapacity.get(SundaySlotValue))-RemainingPeopleInCompartment2);
							}
							else
							{
							
								RemainingCapacityOfSlot2=((slotcapacity.get(SundaySlotValue))-EnteredPeopleCount2);
							}
							RemainingPeopleInCompartment2=EnteredPeopleCount2-slotcapacity.get(SundaySlotValue);
							
							
							if(RemainingCapacityOfSlot2>0)
							{
							
							}
							
							if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
							{
							
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount2-RemainingPeopleInCompartment2);
						
								
							}
							 
							else
							{
							
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(SundaySlotValue)-RemainingCapacityOfSlot2);
						
							}
							
							slotcapacity.set(SundaySlotValue,(int)RemainingCapacityOfSlot2);
							
							if(RemainingCapacityOfSlot2<=0)
							{
								 
								RemainingCapacityOfSlot2=0;
								SundaySlotTimings++;
								SundaySlotValue++;
								 
							}
			        }
					while(RemainingPeopleInCompartment2>=slotcapacity.get(SundaySlotValue))
					{
					
						if(RemainingCapacityOfSlot2<=0)
						{
							RemainingCapacityOfSlot2=0;
							 
						}
			
						PeopleLeftInCompartment=RemainingPeopleInCompartment2;
						RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(SundaySlotValue);
						
					
 
						DarshanTimeInHoursandMinutes=(SundaySlotTime.get(SundaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=SundaySlotTime.get(SundaySlotTimings).getHours();
						DarshanTimeInMinutes=SundaySlotTime.get(SundaySlotTimings).getMinutes();
					
						RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(SundaySlotValue))-EnteredPeopleCount2);
						
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
						
						SundaySlotTimings++;
						SundaySlotValue++; 
						
						if(SundaySlotValue>SumOfSunMonDocumentCount-1)
						{
				        	
							break;
						}
					}
				 	if((RemainingPeopleInCompartment2>0)&&(RemainingPeopleInCompartment2<slotcapacity.get(SundaySlotValue)))
					{
				 
						
						DarshanTimeInHoursandMinutes=(SundaySlotTime.get(SundaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=SundaySlotTime.get(SundaySlotTimings).getHours();
						DarshanTimeInMinutes=SundaySlotTime.get(SundaySlotTimings).getMinutes();
					
						RemainingCapacityOfSlot2=((slotcapacity.get(SundaySlotValue))-RemainingPeopleInCompartment2);
						RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(SundaySlotValue);
					
						
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
					SundaySlotTimings++;
					SundaySlotValue++;
					
					break;
				 }
				SundaySlotTimings--;
			 }
	 	} 
	 	  VQC2SunResult.add(RemainingPeopleInCompartment2);
	 	VQC2SunResult.add(RemainingCapacityOfSlot2);
	 	VQC2SunResult.add((double)TimeUnit.MILLISECONDS.toMinutes(DarshanTimeInHoursandMinutes));
	 	VQC2SunResult.add((double)TimeUnit.MILLISECONDS.toHours(DarshanTimeInHoursandMinutes));
	 	VQC2SunResult.add((double)DarshanTimeInHours);
	 	VQC2SunResult.add((double)DarshanTimeInMinutes);
	 	VQC2SunResult.add(PeopleConsideredForDarshanVQC2);
	  	return VQC2SunResult; 
	 	}
 }






