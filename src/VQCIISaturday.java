


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
public class VQCIISaturday {
	
	
	static	ArrayList<String> Slot = new ArrayList<String>();
	
	
	static String str,str1,currentdate,PeopleEnteringTime, Date,VQC2ExactTimeFromDB,DBLastDocumentTime,DBLastDocumentDay,currentday,u,Remainingslot,DBToTiming, SaturdayToTiming;
	static int Count = 0,DocumentCountOfSaturdayCollection=0,DocumentCountOfSundaycollection=0,w,z,SumOfSatSunDocumentCount,SaturdayDocumentid,e, grt, SaturdayCollCount;
	static long SaturdayCollectionCount;


	public static ArrayList<Double> main(String[] args) throws NumberFormatException, IOException, ParseException {
				// TODO Auto-generated method stub
		 
		int NumberOfPeopleEnteredIntoCompartment2=VQCIIServlet.PeopleEnteredIntoCompartment2;
		String EnteringTimeOfCompartment2=args[1];
		double RemainingPeopleIntheCompartment2=Double.parseDouble(args[2]),RemainingcapacityOftheSlot2=Double.parseDouble(args[3]);
		 
		 
		ArrayList<String> slotTimings1Sat= new ArrayList<>();
		ArrayList<Integer>  slotCapacity1Sat = new ArrayList<>();
		 
		ArrayList<String> slotTimings1Sun= new ArrayList<>(); 
		 
	 
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
             
     		  
     		  w=DocumentCountOfSaturdayCollection;
     		  w=0;
     		  DocumentCountOfSaturdayCollection=w;
     		
     		  
     		  z=DocumentCountOfSundaycollection;
     		  z=0;
     		  DocumentCountOfSundaycollection=z;
     	
     		  
     		  DBCollection SaturdayCollection = db.getCollection("Saturday"); 
     			
     			  
     		
     			
     			SaturdayCollectionCount =SaturdayCollection.count();
     			
     			SaturdayCollCount = (int) SaturdayCollectionCount;
     			
     			 if(currentday.equals(DBLastDocumentDay))
     			  {
     				
     				  
     				 SimpleDateFormat curFormater = new SimpleDateFormat("H:m"); 
			            Date dateObj = curFormater.parse(DBLastDocumentTime); 
			            SimpleDateFormat postFormater = new SimpleDateFormat("HH:mm"); 
			            String newDateStr = postFormater.format(dateObj); 
			         
     				 
     				  BasicDBObject que = new BasicDBObject();
     				  que.put("To", newDateStr);
     				  DBCursor cursor = SaturdayCollection.find(que);
     				  
     				  while(cursor.hasNext())
     					 {
     					  DBObject result=cursor.next();
     					  Map resultmap=result.toMap();
     					  SaturdayToTiming=  (String)resultmap.get("To");
     					  SaturdayDocumentid = (Integer) resultmap.get("ID");
     					  
     					  SaturdayDocumentid++;
     					  for (int LastDocumentID=SaturdayDocumentid; LastDocumentID<SaturdayCollCount; LastDocumentID++ ){
     						  BasicDBObject ques2 = new BasicDBObject("ID", SaturdayDocumentid);
     						  ques2.put("ID", SaturdayDocumentid);
     						  DBCursor cursorv = SaturdayCollection.find(ques2);
     						  
     						  while(cursorv.hasNext())
     							 {
     							  DBObject resultv=cursorv.next();
     							  Map resultmapv=resultv.toMap();
     										  
     						  
     						  DBToTiming = (String) resultmapv.get("To");
     						 SaturdayDocumentid++;
     					
     						  
     						  String DBVQC2MaxCapacity =(String)resultmapv.get("VQC2_Max_Capacity");
     						  
     							
     						  if(!DBVQC2MaxCapacity.equals("")){
     							  int dbVQC2MaxCapacity =  Integer.parseInt((String)resultmapv.get("VQC2_Max_Capacity"));
     							  slotCapacity1Sat.add(dbVQC2MaxCapacity);
     							
     							  slotTimings1Sat.add(DBToTiming);
     						;
     							  DocumentCountOfSaturdayCollection++;
     						
     						   }
     					  
     							 }
     					 }}
     			
     			  }
     			  else if(DBLastDocumentDay.equals("Friday"))
     			 {
     				 
     				 DBCursor cursor = SaturdayCollection.find();
     				  while(cursor.hasNext())
     					 {
     					  DBObject result=cursor.next();
     					  Map resultmap=result.toMap();
     					  
     					  String ToTiming =  (String)resultmap.get("To");
     					  String DBVQC2MaxCapacity =(String)resultmap.get("VQC2_Max_Capacity");
     					  if(!DBVQC2MaxCapacity.equals("")){
     						  int dbVQC2MaxCapacity =  Integer.parseInt((String)resultmap.get("VQC2_Max_Capacity"));
     						  slotCapacity1Sat.add(dbVQC2MaxCapacity);
     						  slotTimings1Sat.add(ToTiming);
     						  
     						  
     						  DocumentCountOfSaturdayCollection++;
     						
     					  }
     					 }
     			
     				 
     			 }
     	 
         
        DBCollection SundayCollection = db.getCollection("Sunday");
   
          
         DBCursor cursor1 = SundayCollection.find();
		         
		         while(cursor1.hasNext())
				 {
				  DBObject result=cursor1.next();
				  Map resultmap=result.toMap();
				  
				  String ToTiming=  (String)resultmap.get("To");
				  String DBVQC2MaxCapacity =(String)resultmap.get("VQC2_Max_Capacity");
				  if(!DBVQC2MaxCapacity.equals("")){
					  int dbVQC2MaxCapacity =  Integer.parseInt((String)resultmap.get("VQC2_Max_Capacity"));
					slotCapacity1Sat.add(dbVQC2MaxCapacity); 
					  
					  slotTimings1Sun.add(ToTiming);
					  DocumentCountOfSundaycollection++;
					
					  
				  }
				    }

		    
		     	ArrayList<Double> ResSatII= new ArrayList<>();	  
			 ResSatII=estimatedtime(Entering_time, slotTimings1Sat, slotTimings1Sun, slotCapacity1Sat,RemainingPeopleIntheCompartment2,RemainingcapacityOftheSlot2,NumberOfPeopleEnteredIntoCompartment2);
				
			return ResSatII;
		}

	public static ArrayList<Double> estimatedtime( String[] Entering_time,
			ArrayList<String> slotTimingsSat,
			ArrayList<String> slotTimingsSun,
			ArrayList<Integer> slotcapacity,
			double RemainingPeopleInCompartment2,
			double RemainingCapacityOfSlot2,
		 	int NumberOfPeopleEntered2 ) throws IOException, ParseException {
		 
		double PeopleConsideredForDarshanVQC2=0;
		long  DarshanTimeInHoursandMinutes=0;
		long DarshanTimeInHours =0;
		long DarshanTimeInMinutes=0;
		double PeopleLeftInCompartment;
		 
	int SaturdaySlotValue = VQCIIServlet.VQC2SatSlotValueCount;
		 	
		int lent= 0;
		
		ArrayList<Double> VQC2SatResult= new ArrayList<>();
		 ArrayList<Date> SaturdaySlotTime = new ArrayList<>();
		ArrayList<Date> SundaySlotTime= new ArrayList<>();
		ArrayList<String>slotTimings= new ArrayList<>();
		slotTimings.addAll(slotTimingsSat);
		slotTimings.addAll(slotTimingsSun);
		
		
		
		ArrayList<Date> enteredTime = new ArrayList<>();
		 
		for ( String h:slotTimingsSat){
			  
				SimpleDateFormat format = new SimpleDateFormat("HH:mm");
				Date date1 = format.parse(h);
				SaturdaySlotTime.add(date1);
		 
		}
		 
		for ( String h:slotTimingsSun){
			  
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);
			Calendar c = Calendar.getInstance();
			c.setTime(date2);
			c.add(Calendar.DATE, 1); // number of days to add
		 	Date currentDatePlusOne = c.getTime();
		 	SundaySlotTime.add(currentDatePlusOne);
		 	}
		
		SaturdaySlotTime.addAll(SundaySlotTime);
	 for ( String h:Entering_time){
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);	
			enteredTime.add(date2);
		}
		
		SumOfSatSunDocumentCount=0;
	
	  SumOfSatSunDocumentCount=DocumentCountOfSaturdayCollection+DocumentCountOfSundaycollection;

	 
	 	for(int EnteringPeopleTime = 0;EnteringPeopleTime<Entering_time.length;EnteringPeopleTime++) {
	 		
			for(int SaturdaySlotTimings =VQCIIServlet.VQC2SatSlotTimeCount;SaturdaySlotTimings<slotTimings.size();SaturdaySlotTimings++){
				 
				if (EnteringPeopleTime>0){
					VQCIIServlet.VQC2SatSlotTimeCount=SaturdaySlotTimings;
					VQCIIServlet.VQC2SatSlotValueCount=SaturdaySlotValue;
					break;
				 
				}
				 
				double EnteredPeopleCount2 = NumberOfPeopleEntered2;
				 
		         
				if(EnteringPeopleTime>20){
				
					break;
				}
				
				
				if((RemainingCapacityOfSlot2>0)&&((enteredTime.get(0).compareTo(SaturdaySlotTime.get(SaturdaySlotTimings))) >=0 ))
				{
					 
					SaturdaySlotTimings++;
					SaturdaySlotValue++;
					RemainingCapacityOfSlot2=0;
				}
			 
				if( (SaturdaySlotTime.get(SaturdaySlotTimings).compareTo(enteredTime.get(0))) >=0)
				{
				
					 
			        if(SaturdaySlotValue>SumOfSatSunDocumentCount-1)
					{
			        	
			        	
			        	if(EnteredPeopleCount2> slotcapacity.get(SaturdaySlotValue))
				        {
						  
				        	
				        	if(RemainingCapacityOfSlot2<=0)
							{
								RemainingCapacityOfSlot2=0;
							
							}
				        	if(RemainingCapacityOfSlot2>0)
							{
								slotcapacity.set(SaturdaySlotValue,(int)RemainingCapacityOfSlot2);
							}
						 
				    
							
				        	RemainingPeopleInCompartment2=RemainingPeopleInCompartment2+EnteredPeopleCount2-(slotcapacity.get(SaturdaySlotValue));
						
					 
							DarshanTimeInHoursandMinutes=(SaturdaySlotTime.get(SaturdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							DarshanTimeInHours=SaturdaySlotTime.get(SaturdaySlotTimings).getHours();
							DarshanTimeInMinutes=SaturdaySlotTime.get(SaturdaySlotTimings).getMinutes();
					
							
							RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(SaturdaySlotValue))-EnteredPeopleCount2);
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
					 if(EnteredPeopleCount2<=slotcapacity.get(SaturdaySlotValue)){
						 
						 
						 if(RemainingCapacityOfSlot2<=0)
							{
								RemainingCapacityOfSlot2=0;
								
							}
						 
					
						if(RemainingCapacityOfSlot2>0)
						{
							slotcapacity.set(SaturdaySlotValue,(int)RemainingCapacityOfSlot2);;
						}
						
		
			        	 
						DarshanTimeInHoursandMinutes=(SaturdaySlotTime.get(SaturdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=SaturdaySlotTime.get(SaturdaySlotTimings).getHours();
						DarshanTimeInMinutes=SaturdaySlotTime.get(SaturdaySlotTimings).getMinutes();
					
						if(RemainingPeopleInCompartment2>0)
						{
					
							RemainingCapacityOfSlot2=((slotcapacity.get(SaturdaySlotValue))-RemainingPeopleInCompartment2);
						}
						else
						{
						
							RemainingCapacityOfSlot2=((slotcapacity.get(SaturdaySlotValue))-EnteredPeopleCount2);
						}
						RemainingPeopleInCompartment2=EnteredPeopleCount2-slotcapacity.get(SaturdaySlotValue);
					
						
						if(RemainingCapacityOfSlot2>0)
						{
						
						}
						
						if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
						{
							
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount2-RemainingPeopleInCompartment2);
						
							
						}
						 
						else
						{
						
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(SaturdaySlotValue)-RemainingCapacityOfSlot2);
					
						}
						
						
						slotcapacity.set(SaturdaySlotValue,(int)RemainingCapacityOfSlot2);
						
						if(RemainingCapacityOfSlot2<=0)
						{
							 
							RemainingCapacityOfSlot2=0;
						 
						}
		        }
				while(RemainingPeopleInCompartment2>=slotcapacity.get(SaturdaySlotValue))
				{
					
					if(RemainingCapacityOfSlot2<=0)
					{
						RemainingCapacityOfSlot2=0;
						 
					}
				
					PeopleLeftInCompartment=RemainingPeopleInCompartment2;
					RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(SaturdaySlotValue);
					
					if(SaturdaySlotValue>SumOfSatSunDocumentCount-1)
					{
			        	
						break;
					}
			     
					 DarshanTimeInHoursandMinutes=(SaturdaySlotTime.get(SaturdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					DarshanTimeInHours=SaturdaySlotTime.get(SaturdaySlotTimings).getHours();
					DarshanTimeInMinutes=SaturdaySlotTime.get(SaturdaySlotTimings).getMinutes();
				
					RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(SaturdaySlotValue))-EnteredPeopleCount2);
					
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
				
				if((RemainingPeopleInCompartment2>0)&&(RemainingPeopleInCompartment2<slotcapacity.get(SaturdaySlotValue)))
				{
					 
				
				 
					DarshanTimeInHoursandMinutes=(SaturdaySlotTime.get(SaturdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					DarshanTimeInHours=SaturdaySlotTime.get(SaturdaySlotTimings).getHours();
					DarshanTimeInMinutes=SaturdaySlotTime.get(SaturdaySlotTimings).getMinutes();
				
					RemainingCapacityOfSlot2=((slotcapacity.get(SaturdaySlotValue))-RemainingPeopleInCompartment2);
					RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(SaturdaySlotValue);
					
					
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
						
						PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(SaturdaySlotValue))-(RemainingCapacityOfSlot2);
			
					}
					
				}
				if(RemainingPeopleInCompartment2<0){
					RemainingPeopleInCompartment2=0;
					 
					
				 }	
			 
						break;
					}
			       
						 if(EnteredPeopleCount2>slotcapacity.get(SaturdaySlotValue))
					        {
							 
					        	
					        	if(RemainingCapacityOfSlot2<=0)
								{
									RemainingCapacityOfSlot2=0;
								
								}
					        	if(RemainingCapacityOfSlot2>0)
								{
									slotcapacity.set(SaturdaySlotValue,(int)RemainingCapacityOfSlot2);;
								}
							 
					  
								
					        	RemainingPeopleInCompartment2=RemainingPeopleInCompartment2+EnteredPeopleCount2-(slotcapacity.get(SaturdaySlotValue));
							
						
								 
								DarshanTimeInHoursandMinutes=(SaturdaySlotTime.get(SaturdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
								DarshanTimeInHours=SaturdaySlotTime.get(SaturdaySlotTimings).getHours();
								DarshanTimeInMinutes=SaturdaySlotTime.get(SaturdaySlotTimings).getMinutes();
							
								RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(SaturdaySlotValue))-EnteredPeopleCount2);
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
									SaturdaySlotTimings++;
									SaturdaySlotValue++;
									 
								}
								 
								
					        }
						 if(EnteredPeopleCount2<=slotcapacity.get(SaturdaySlotValue)){
							 
							 
							 if(RemainingCapacityOfSlot2<=0)
								{
									RemainingCapacityOfSlot2=0;
								
								}
							 
						
							if(RemainingCapacityOfSlot2>0)
							{
								slotcapacity.set(SaturdaySlotValue,(int)RemainingCapacityOfSlot2);;
							}
					
						 
							DarshanTimeInHoursandMinutes=(SaturdaySlotTime.get(SaturdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							DarshanTimeInHours=SaturdaySlotTime.get(SaturdaySlotTimings).getHours();
							DarshanTimeInMinutes=SaturdaySlotTime.get(SaturdaySlotTimings).getMinutes();
						
							if(RemainingPeopleInCompartment2>0)
							{
							
								RemainingCapacityOfSlot2=((slotcapacity.get(SaturdaySlotValue))-RemainingPeopleInCompartment2);
							}
							else
							{
					
								RemainingCapacityOfSlot2=((slotcapacity.get(SaturdaySlotValue))-EnteredPeopleCount2);
							}
							RemainingPeopleInCompartment2=EnteredPeopleCount2-slotcapacity.get(SaturdaySlotValue);
					
							
							 
							if(RemainingCapacityOfSlot2>0)
							{
							
							}
							
							if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
							{
							
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount2-RemainingPeopleInCompartment2);
					
								
							}
						 
							else
							{
							
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(SaturdaySlotValue)-RemainingCapacityOfSlot2);
								
							}
							
							 slotcapacity.set(SaturdaySlotValue,(int)RemainingCapacityOfSlot2);
							
							
							if(RemainingCapacityOfSlot2<=0)
							{
								 
								RemainingCapacityOfSlot2=0;
								SaturdaySlotTimings++;
								SaturdaySlotValue++;
								 
							}
			        }
					while(RemainingPeopleInCompartment2>=slotcapacity.get(SaturdaySlotValue))
					{
					
						if(RemainingCapacityOfSlot2<=0)
						{
							RemainingCapacityOfSlot2=0;
						 
						}
					
						PeopleLeftInCompartment=RemainingPeopleInCompartment2;
						RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(SaturdaySlotValue);
						
						
						
				
 
						DarshanTimeInHoursandMinutes=(SaturdaySlotTime.get(SaturdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=SaturdaySlotTime.get(SaturdaySlotTimings).getHours();
						DarshanTimeInMinutes=SaturdaySlotTime.get(SaturdaySlotTimings).getMinutes();
				
						
						RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(SaturdaySlotValue))-EnteredPeopleCount2);
						
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
						
						
						SaturdaySlotTimings++;
						SaturdaySlotValue++;
						if(SaturdaySlotValue>SumOfSatSunDocumentCount-1)
						{
				        	
							break; 
						}
				       
					}
					
					if((RemainingPeopleInCompartment2>0)&&(RemainingPeopleInCompartment2<slotcapacity.get(SaturdaySlotValue)))
					{
						 
						
						DarshanTimeInHoursandMinutes=(SaturdaySlotTime.get(SaturdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=SaturdaySlotTime.get(SaturdaySlotTimings).getHours();
						DarshanTimeInMinutes=SaturdaySlotTime.get(SaturdaySlotTimings).getMinutes();
				
						RemainingCapacityOfSlot2=((slotcapacity.get(SaturdaySlotValue))-RemainingPeopleInCompartment2);
						RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(SaturdaySlotValue);
					
						
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
							
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(SaturdaySlotValue))-(RemainingCapacityOfSlot2);
					
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
					SaturdaySlotTimings++;
					SaturdaySlotValue++;
					
					break;
					
				}
				SaturdaySlotTimings--;
				
			}
			
		} 
	 	 VQC2SatResult.add(RemainingPeopleInCompartment2);
	 	VQC2SatResult.add(RemainingCapacityOfSlot2);
	 	VQC2SatResult.add((double)TimeUnit.MILLISECONDS.toMinutes(DarshanTimeInHoursandMinutes));
	 	VQC2SatResult.add((double)TimeUnit.MILLISECONDS.toHours(DarshanTimeInHoursandMinutes));
	 	VQC2SatResult.add((double)DarshanTimeInHours);
	 	VQC2SatResult.add((double)DarshanTimeInMinutes);
	 	VQC2SatResult.add(PeopleConsideredForDarshanVQC2);
	 	 
	 	return VQC2SatResult; 
	 }
 }






