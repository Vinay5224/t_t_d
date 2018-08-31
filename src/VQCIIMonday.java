


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
public class VQCIIMonday {
	
	
	static	ArrayList<String> Slot = new ArrayList<String>();
	
	
	static String str,str1,currentdate,PeopleEnteringTime, Date,VQC2ExactTimeFromDB,DBLastDocumentTime,DBLastDocumentDay,currentday,u,DBToTiming, MondayToTiming ,Remainingslot;
	static int Count = 0,DocumentCountOfMondayCollection=0,DocumentCountOfTuesdaycollection=0,w,z,SumOfMonTueDocumentCount,MondayDocumentid,e, grt, MondayCollCount;
	static long MondayCollectionCount;

	public static ArrayList<Double> main(String[] args) throws NumberFormatException, IOException, ParseException {
				// TODO Auto-generated method stub
			 
		 
		int NumberOfPeopleEnteredIntoCompartment2=VQCIIServlet.PeopleEnteredIntoCompartment2;
		String EnteringTimeOfCompartment2=args[1];
		double RemainingPeopleIntheCompartment2=Double.parseDouble(args[2]),RemainingcapacityOftheSlot2=Double.parseDouble(args[3]);
	 
		ArrayList<String> slotTimings1Mon= new ArrayList<>();
		ArrayList<Integer>  slotCapacity1Mon = new ArrayList<>();
	 
		ArrayList<String> slotTimings1Tue= new ArrayList<>(); 
		 
	
				 
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
        w=DocumentCountOfMondayCollection;
		  w=0;
		  DocumentCountOfMondayCollection=w;
		
		  
		  z=DocumentCountOfTuesdaycollection;
		  z=0;
		  DocumentCountOfTuesdaycollection=z;
		
		  
		  
			  DBCollection MondayCollection = db.getCollection("Monday"); 
			
			  
		
			
			MondayCollectionCount =MondayCollection.count();
			
			MondayCollCount = (int) MondayCollectionCount;
			 if(currentday.equals(DBLastDocumentDay))
			  {
				
				  
				  SimpleDateFormat curFormater = new SimpleDateFormat("H:m"); 
		            Date dateObj = curFormater.parse(DBLastDocumentTime); 
		            SimpleDateFormat postFormater = new SimpleDateFormat("HH:mm"); 
		            String newDateStr = postFormater.format(dateObj); 
				 
				  BasicDBObject que = new BasicDBObject();
				  que.put("To", newDateStr);
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
						
						  
						  String DBVQC2MaxCapacity=(String)resultmapv.get("VQC2_Max_Capacity");
						  
							
						  if(!DBVQC2MaxCapacity.equals("")){
							  int dbVQC2MaxCapacity=  Integer.parseInt((String)resultmapv.get("VQC2_Max_Capacity"));
							  slotCapacity1Mon.add(dbVQC2MaxCapacity);
							
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
					  String DBVQC2MaxCapacity=(String)resultmap.get("VQC2_Max_Capacity");
					  if(!DBVQC2MaxCapacity.equals("")){
						  int dbVQC2MaxCapacity =  Integer.parseInt((String)resultmap.get("VQC2_Max_Capacity"));
						  slotCapacity1Mon.add(dbVQC2MaxCapacity);
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
				  String DBVQC2MaxCapacity=(String)resultmap.get("VQC2_Max_Capacity");
				  if(!DBVQC2MaxCapacity.equals("")){
					  int dbVQC2MaxCapacity=  Integer.parseInt((String)resultmap.get("VQC2_Max_Capacity"));
					slotCapacity1Mon.add(dbVQC2MaxCapacity); 
					  
					  slotTimings1Tue.add(ToTiming);
					   
					  DocumentCountOfTuesdaycollection++;
					 
				   }
				   }
		      

		     	ArrayList<Double> ResMonII= new ArrayList<>();	  
			 ResMonII=estimatedtime(Entering_time, slotTimings1Mon, slotTimings1Tue, slotCapacity1Mon,RemainingPeopleIntheCompartment2,RemainingcapacityOftheSlot2,NumberOfPeopleEnteredIntoCompartment2);
			
			return ResMonII;
		}

	public static ArrayList<Double> estimatedtime( String[] Entering_time,
			ArrayList<String> slotTimingsMon,
			ArrayList<String> slotTimingsTue,
			ArrayList<Integer> slotcapacity,
			
			double RemainingPeopleInCompartment2,
			double RemainingCapacityOfSlot2,
			 int NumberOfPeopleEntered2 ) throws IOException, ParseException {
		 
		double PeopleConsideredForDarshanVQC2 = 0;
		
		long  DarshanTimeInHoursandMinutes=0;
		long DarshanTimeInHours =0;
		long DarshanTimeInMinutes=0;
		double PeopleLeftInCompartment;
		
	 
	int MondaySlotValue = VQCIIServlet.VQC2MonSlotValueCount;
	 
		 int lent= 0;
		
		ArrayList<Double> VQC2MonResult= new ArrayList<>();
		 ArrayList<Date> MondaySlotTime = new ArrayList<>();
		ArrayList<Date> TuesdaySlotTime= new ArrayList<>();
		ArrayList<String>slotTimings= new ArrayList<>();
		
		ArrayList<Date> enteredTime = new ArrayList<>();
		
		slotTimings.addAll(slotTimingsMon);
		slotTimings.addAll(slotTimingsTue);
		
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
		
		
		
	  SumOfMonTueDocumentCount=DocumentCountOfMondayCollection+DocumentCountOfTuesdaycollection;
	
	 
	 	for(int EnteringPeopleTime = 0;EnteringPeopleTime<Entering_time.length;EnteringPeopleTime++) {
	 		
	 	
			for(int MondaySlotTimings =VQCIIServlet.VQC2MonSlotTimeCount;MondaySlotTimings<slotTimings.size();MondaySlotTimings++){
				 
				if (EnteringPeopleTime>0){
					VQCIIServlet.VQC2MonSlotTimeCount=MondaySlotTimings;
					VQCIIServlet.VQC2MonSlotValueCount=MondaySlotValue;
					break;
				 
				}
				 
				double EnteredPeopleCount2 = NumberOfPeopleEntered2;
				
				 if(EnteringPeopleTime>20){
					
					break;
				}
				
				
				if((RemainingCapacityOfSlot2>0)&&((enteredTime.get(0).compareTo(MondaySlotTime.get(MondaySlotTimings))) >=0 ))
				{
					 
					MondaySlotTimings++;
					MondaySlotValue++;
					RemainingCapacityOfSlot2=0;
				}
				
	 	if( (MondaySlotTime.get(MondaySlotTimings).compareTo(enteredTime.get(0))) >=0)
				{
				
					 
			        if(MondaySlotValue>SumOfMonTueDocumentCount-1)
					{
			        	
			   
			        	if(EnteredPeopleCount2> slotcapacity.get(MondaySlotValue))
				        {
						 
				        	
				        	if(RemainingCapacityOfSlot2<=0)
							{
								RemainingCapacityOfSlot2=0;
							
							}
				        	if(RemainingCapacityOfSlot2>0)
							{
								slotcapacity.set(MondaySlotValue,(int)RemainingCapacityOfSlot2);
							}
						 
				        
				        	
				        	
							
				        	RemainingPeopleInCompartment2=RemainingPeopleInCompartment2+EnteredPeopleCount2-(slotcapacity.get(MondaySlotValue));
							
					
						 	DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
							DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
							
							DarshanTimeInHoursandMinutes=(MondaySlotTime.get(MondaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						
							
							RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(MondaySlotValue))-EnteredPeopleCount2);
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
					 if(EnteredPeopleCount2<=slotcapacity.get(MondaySlotValue)){
						 
						 
						 if(RemainingCapacityOfSlot2<=0)
							{
								RemainingCapacityOfSlot2=0;
								
							}
						 
						
						if(RemainingCapacityOfSlot2>0)
						{
							slotcapacity.set(MondaySlotValue,(int)RemainingCapacityOfSlot2);;
						}
						
					
			        	 
						
						
						DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
						DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
					
						DarshanTimeInHoursandMinutes=(MondaySlotTime.get(MondaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						
						if(RemainingPeopleInCompartment2>0)
						{
					
							RemainingCapacityOfSlot2=((slotcapacity.get(MondaySlotValue))-RemainingPeopleInCompartment2);
						}
						else
						{
					
							RemainingCapacityOfSlot2=((slotcapacity.get(MondaySlotValue))-EnteredPeopleCount2);
						}
						RemainingPeopleInCompartment2=EnteredPeopleCount2-slotcapacity.get(MondaySlotValue);
					
						
						if(RemainingCapacityOfSlot2>0)
						{
						
						}
						
						if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
						{
					
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount2-RemainingPeopleInCompartment2);
					
							
						}
					 
						else
						{
							
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(MondaySlotValue)-RemainingCapacityOfSlot2);
					
						}
						
						slotcapacity.set(MondaySlotValue,(int)RemainingCapacityOfSlot2);;
						
						if(RemainingCapacityOfSlot2<=0)
						{
							 
							RemainingCapacityOfSlot2=0;
							 
							 
						}
		        }
				while(RemainingPeopleInCompartment2>=slotcapacity.get(MondaySlotValue))
				{
				
					if(RemainingCapacityOfSlot2<=0)
					{
						RemainingCapacityOfSlot2=0;
						 
					}
					
					PeopleLeftInCompartment=RemainingPeopleInCompartment2;
					RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(MondaySlotValue);
					
					if(MondaySlotValue>SumOfMonTueDocumentCount-1)
					{
			        	
						break;
					}
			       
					
				
					 
					DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
					DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
					
					
					
					DarshanTimeInHoursandMinutes=(MondaySlotTime.get(MondaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
			
					
					RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(MondaySlotValue))-EnteredPeopleCount2);
					
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
				
				if((RemainingPeopleInCompartment2>0)&&(RemainingPeopleInCompartment2<slotcapacity.get(MondaySlotValue)))
				{
					 
		
				 
					
					DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
					DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
		
					
					DarshanTimeInHoursandMinutes=(MondaySlotTime.get(MondaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;

					RemainingCapacityOfSlot2=((slotcapacity.get(MondaySlotValue))-RemainingPeopleInCompartment2);
					RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(MondaySlotValue);
				
					
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
					
						PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(MondaySlotValue))-(RemainingCapacityOfSlot2);
					
					}
					
					
				}
				if(RemainingPeopleInCompartment2<0){
					RemainingPeopleInCompartment2=0;
					 
			  }	
			        	
						break;
					}
			       
						 if(EnteredPeopleCount2>slotcapacity.get(MondaySlotValue))
					        {
							 
					        	
					        	if(RemainingCapacityOfSlot2<=0)
								{
									RemainingCapacityOfSlot2=0;
							
								}
					        	if(RemainingCapacityOfSlot2>0)
								{
									slotcapacity.set(MondaySlotValue,(int)RemainingCapacityOfSlot2);;
								}
							 
					    
					        	
					        	
								
					        	RemainingPeopleInCompartment2=RemainingPeopleInCompartment2+EnteredPeopleCount2-(slotcapacity.get(MondaySlotValue));
							
						
								 
								DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
								DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
							
								
								
								DarshanTimeInHoursandMinutes=(MondaySlotTime.get(MondaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						
						
								
								RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(MondaySlotValue))-EnteredPeopleCount2);
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
									MondaySlotTimings++;
									MondaySlotValue++;
									 
								}
							 	
					        }
						 if(EnteredPeopleCount2<=slotcapacity.get(MondaySlotValue)){
							 
							 
							 if(RemainingCapacityOfSlot2<=0)
								{
									RemainingCapacityOfSlot2=0;
								
								}
							 
					
							if(RemainingCapacityOfSlot2>0)
							{
								slotcapacity.set(MondaySlotValue,(int)RemainingCapacityOfSlot2);;
							}
							
						
							
							DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
							DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
						
							
							DarshanTimeInHoursandMinutes=(MondaySlotTime.get(MondaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							 
							DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
							
							
							
							if(RemainingPeopleInCompartment2>0)
							{
							
								RemainingCapacityOfSlot2=((slotcapacity.get(MondaySlotValue))-RemainingPeopleInCompartment2);
							}
							else
							{
							
								RemainingCapacityOfSlot2=((slotcapacity.get(MondaySlotValue))-EnteredPeopleCount2);
							}
							RemainingPeopleInCompartment2=EnteredPeopleCount2-slotcapacity.get(MondaySlotValue);
						
							
							if(RemainingCapacityOfSlot2>0)
							{
								
							}
							
							if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
							{
							
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount2-RemainingPeopleInCompartment2);
							
								
							}
							 
							else
							{
							
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(MondaySlotValue)-RemainingCapacityOfSlot2);
						
							}
							
							slotcapacity.set(MondaySlotValue,(int)RemainingCapacityOfSlot2);
							
							if(RemainingCapacityOfSlot2<=0)
							{
								 
								RemainingCapacityOfSlot2=0;
								MondaySlotTimings++;
								MondaySlotValue++;
						 	}
			        }
						 
					while(RemainingPeopleInCompartment2>=slotcapacity.get(MondaySlotValue))
					{
						
						if(RemainingCapacityOfSlot2<=0)
						{
							RemainingCapacityOfSlot2=0;
							 
						}
						
						PeopleLeftInCompartment=RemainingPeopleInCompartment2;
						RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(MondaySlotValue);
						
						
						
					
 					
						DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
						DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
						
						
						DarshanTimeInHoursandMinutes=(MondaySlotTime.get(MondaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
				
						RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(MondaySlotValue))-EnteredPeopleCount2);
						
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
						
						
						}
						 MondaySlotTimings++;
						MondaySlotValue++; 
						
						if(MondaySlotValue>SumOfMonTueDocumentCount-1)
						{
				        	
				        	break;
						}
				       
					}
					
					if((RemainingPeopleInCompartment2>0)&&(RemainingPeopleInCompartment2<slotcapacity.get(MondaySlotValue)))
					{
						 
						
						
						DarshanTimeInHours=MondaySlotTime.get(MondaySlotTimings).getHours();
						DarshanTimeInMinutes=MondaySlotTime.get(MondaySlotTimings).getMinutes();
					
						
						
						DarshanTimeInHoursandMinutes=(MondaySlotTime.get(MondaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					
						RemainingCapacityOfSlot2=((slotcapacity.get(MondaySlotValue))-RemainingPeopleInCompartment2);
						RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(MondaySlotValue);
					
						
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
							
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(MondaySlotValue))-(RemainingCapacityOfSlot2);
					
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
					MondaySlotTimings++;
					MondaySlotValue++;
					
					break;
					
				}
				MondaySlotTimings--;
				
			}
			
			
			 
		} 
	    VQC2MonResult.add(RemainingPeopleInCompartment2);
	 	VQC2MonResult.add(RemainingCapacityOfSlot2);
	 	VQC2MonResult.add((double)TimeUnit.MILLISECONDS.toMinutes(DarshanTimeInHoursandMinutes));
	 	VQC2MonResult.add((double)TimeUnit.MILLISECONDS.toHours(DarshanTimeInHoursandMinutes));
	 	VQC2MonResult.add((double)DarshanTimeInHours);
	 	VQC2MonResult.add((double)DarshanTimeInMinutes);
	 	VQC2MonResult.add(PeopleConsideredForDarshanVQC2);
	  	return VQC2MonResult; 
	 	
	 	
	}
	
	
}






