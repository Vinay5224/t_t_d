


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
public class VQCIIFriday {
	
	
	static	ArrayList<String> Slot = new ArrayList<String>();
	
	
	static String str,str1,currentdate,PeopleEnteringTime, Date,VQC2ExactTimeFromDB,DBLastDocumentTime,DBLastDocumentDay,currentday,u,Remainingslot,DBToTiming, FridayToTiming;
	static int Count = 0,DocumentCountOfFridayCollection=0,DocumentCountOfSaturdaycollection=0,w,z,SumOfFriSatDocumentCount,FridayDocumentid,e, grt, FridayCollCount;
	static long FridayCollectionCount;


	public static ArrayList<Double> main(String[] args) throws NumberFormatException, IOException, ParseException {
				// TODO Auto-generated method stub
		 
		int NumberOfPeopleEnteredIntoCompartment2=VQCIIServlet.PeopleEnteredIntoCompartment2;
		 
	 
		String EnteringTimeOfCompartment2=args[1];
		double RemainingPeopleIntheCompartment2=Double.parseDouble(args[2]),RemainingcapacityOftheSlot2=Double.parseDouble(args[3]);
		 
		 
		ArrayList<String> slotTimings1Fri= new ArrayList<>();
		ArrayList<Integer>  slotCapacity1Fri = new ArrayList<>();
		 
		ArrayList<String> slotTimings1Sat= new ArrayList<>(); 
	 
	
				 
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
         
		  w=DocumentCountOfFridayCollection;
		  w=0;
		  DocumentCountOfFridayCollection=w;
		
		  
		  z=DocumentCountOfSaturdaycollection;
		  z=0;
		  DocumentCountOfSaturdaycollection=z;
		
		   DBCollection FridayCollection = db.getCollection("Friday"); 
			
			  
			
			
			FridayCollectionCount =FridayCollection.count();
			
			FridayCollCount = (int) FridayCollectionCount;
			
			 if(currentday.equals(DBLastDocumentDay))
			  {
				 
				
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
						
						  
						  String DBVQC2MaxCapacity=(String)resultmapv.get("VQC2_Max_Capacity");
						  
							
						  if(!DBVQC2MaxCapacity.equals("")){
							  int dbVQC2MaxCapacity =  Integer.parseInt((String)resultmapv.get("VQC2_Max_Capacity"));
							  slotCapacity1Fri.add(dbVQC2MaxCapacity);
							
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
					  String DBVQC2MaxCapacity=(String)resultmap.get("VQC2_Max_Capacity");
					  if(!DBVQC2MaxCapacity.equals("")){
						  int dbVQC2MaxCapacity =  Integer.parseInt((String)resultmap.get("VQC2_Max_Capacity"));
						  slotCapacity1Fri.add(dbVQC2MaxCapacity);
						  slotTimings1Fri.add(ToTiming);
						 
						  
						  DocumentCountOfFridayCollection++;
						
					  }
					 }
				
				 
			 }
		 
       
         
        DBCollection SaturdayCollection = db.getCollection("Saturday");
       
          
         DBCursor cursor1 = SaturdayCollection.find();
		         
		         while(cursor1.hasNext())
				 {
				  DBObject result=cursor1.next();
				  Map resultmap=result.toMap();
				  
				  String ToTiming=  (String)resultmap.get("To");
				  String DBVQC2MaxCapacity =(String)resultmap.get("VQC2_Max_Capacity");
				  if(!DBVQC2MaxCapacity.equals("")){
					  int dbVQC2MaxCapacity =  Integer.parseInt((String)resultmap.get("VQC2_Max_Capacity"));
					slotCapacity1Fri.add(dbVQC2MaxCapacity); 
					  
					  slotTimings1Sat.add(ToTiming);
					  
					  DocumentCountOfSaturdaycollection++;
					  
					 
				  }
				   }

		        
		     	ArrayList<Double> ResFriII= new ArrayList<>();	  
			 ResFriII=estimatedtime(Entering_time, slotTimings1Fri, slotTimings1Sat, slotCapacity1Fri,RemainingPeopleIntheCompartment2,RemainingcapacityOftheSlot2,NumberOfPeopleEnteredIntoCompartment2);
				
			return ResFriII;
		}

	public static ArrayList<Double> estimatedtime( String[] Entering_time,
			ArrayList<String> slotTimingsFri,
			ArrayList<String> slotTimingsSat,
			ArrayList<Integer> slotcapacity,
			double RemainingPeopleInCompartment2,
			double RemainingCapacityOfSlot2,
		 	int NumberOfPeopleEntered2 ) throws IOException, ParseException {
		 
		double PeopleConsideredForDarshanVQC2=0;
		long  DarshanTimeInHoursandMinutes=0;
		long DarshanTimeInHours =0;
		long DarshanTimeInMinutes=0;
		double PeopleLeftInCompartment ;
	 
	int FridaySlotValue = VQCIIServlet.VQC2FriSlotValueCount;
			
	 
		
		ArrayList<Double> VQC2FriResult= new ArrayList<>();
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
	  
	 
	  
	   	for(int EnteringPeopleTime = 0;EnteringPeopleTime<Entering_time.length;EnteringPeopleTime++) {
	 		
			for(int FridaySlotTimings =VQCIIServlet.VQC2FriSlotTimeCount;FridaySlotTimings<slotTimings.size();FridaySlotTimings++){
				 
				if (EnteringPeopleTime>0){
					VQCIIServlet.VQC2FriSlotTimeCount=FridaySlotTimings;
					VQCIIServlet.VQC2FriSlotValueCount=FridaySlotValue;
					break;
			 	}
				 double EnteredPeopleCount2 = NumberOfPeopleEntered2;
				 if(EnteringPeopleTime>20){
					
					break;
				}
				
				
				if((RemainingCapacityOfSlot2>0)&&((enteredTime.get(0).compareTo(FridaySlotTime.get(FridaySlotTimings))) >=0 ))
				{
				 
					FridaySlotTimings++;
					FridaySlotValue++;
					RemainingCapacityOfSlot2=0;
				}
				 if( (FridaySlotTime.get(FridaySlotTimings).compareTo(enteredTime.get(0))) >=0)
				{
					
				 
			        if(FridaySlotValue>SumOfFriSatDocumentCount-1)
					{
			        	
			        	
			        	if(EnteredPeopleCount2> slotcapacity.get(FridaySlotValue))
				        {
						 
				        	
				        	if(RemainingCapacityOfSlot2<=0)
							{
								RemainingCapacityOfSlot2=0;
								
							}
				        	if(RemainingCapacityOfSlot2>0)
							{
								slotcapacity.set(FridaySlotValue,(int)RemainingCapacityOfSlot2);
							}
						 
				        	
				        	
							
				        	RemainingPeopleInCompartment2=RemainingPeopleInCompartment2+EnteredPeopleCount2-(slotcapacity.get(FridaySlotValue));
							
					
							 
							DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
							DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();
						
							
							RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(FridaySlotValue))-EnteredPeopleCount2);
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
					 if(EnteredPeopleCount2<=slotcapacity.get(FridaySlotValue)){
						 
						 
						 if(RemainingCapacityOfSlot2<=0)
							{
								RemainingCapacityOfSlot2=0;
								
							}
						 
					
						if(RemainingCapacityOfSlot2>0)
						{
							
							slotcapacity.set(FridaySlotValue,(int)RemainingCapacityOfSlot2);;
						}
						
					
			        	 
						DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
						DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();
						
						if(RemainingPeopleInCompartment2>0)
						{
						
							RemainingCapacityOfSlot2=((slotcapacity.get(FridaySlotValue))-RemainingPeopleInCompartment2);
						}
						else
						{
							
							RemainingCapacityOfSlot2=((slotcapacity.get(FridaySlotValue))-EnteredPeopleCount2);
							 
						}
						RemainingPeopleInCompartment2=EnteredPeopleCount2-slotcapacity.get(FridaySlotValue);
						
						
						if(RemainingCapacityOfSlot2>0)
						{
							
						}
						
						if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
						{
							
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount2-RemainingPeopleInCompartment2);
						
							
						}
					 
						else
						{
						
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(FridaySlotValue)-RemainingCapacityOfSlot2);
						
						}
						slotcapacity.set(FridaySlotValue,(int)RemainingCapacityOfSlot2);
						
						if(RemainingCapacityOfSlot2<=0)
						{
					 
							RemainingCapacityOfSlot2=0;
						  
						}
		        }
				while(RemainingPeopleInCompartment2>=slotcapacity.get(FridaySlotValue))
				{
			
					if(RemainingCapacityOfSlot2<=0)
					{
						RemainingCapacityOfSlot2=0;
						 
					}
				
					PeopleLeftInCompartment=RemainingPeopleInCompartment2;
					RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(FridaySlotValue);
					
					if(FridaySlotValue>SumOfFriSatDocumentCount-1)
					{
			        
						break;
					}
			      
					 
					DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
					DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();
			
					
					RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(FridaySlotValue))-EnteredPeopleCount2);
					
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
				
				if((RemainingPeopleInCompartment2>0)&&(RemainingPeopleInCompartment2<slotcapacity.get(FridaySlotValue)))
				{
					 
				
					 
					DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
					DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();

					RemainingCapacityOfSlot2=((slotcapacity.get(FridaySlotValue))-RemainingPeopleInCompartment2);
					RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(FridaySlotValue);
				
					
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
					
						PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(FridaySlotValue))-(RemainingCapacityOfSlot2);
					
					}
					
					
				}
				if(RemainingPeopleInCompartment2<0){
					RemainingPeopleInCompartment2=0;
				  }	
			        
						break;
					}
			       
						 if(EnteredPeopleCount2>slotcapacity.get(FridaySlotValue))
					        {
							  
					        	
					        	if(RemainingCapacityOfSlot2<=0)
								{
									RemainingCapacityOfSlot2=0;
									
								}
					        	if(RemainingCapacityOfSlot2>0)
								{
									slotcapacity.set(FridaySlotValue,(int)RemainingCapacityOfSlot2);;
								}
							 
					        	
					        	
					        	
								
					        	RemainingPeopleInCompartment2=RemainingPeopleInCompartment2+EnteredPeopleCount2-(slotcapacity.get(FridaySlotValue));
								
						
								 
								DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
								DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
								DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();
								
								
								RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(FridaySlotValue))-EnteredPeopleCount2);
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
									FridaySlotTimings++;
									FridaySlotValue++;
									 
								}
						    }
						 if(EnteredPeopleCount2<=slotcapacity.get(FridaySlotValue)){
							  if(RemainingCapacityOfSlot2<=0)
								{
									RemainingCapacityOfSlot2=0;
								
								}
							 
						
							if(RemainingCapacityOfSlot2>0)
							{
								slotcapacity.set(FridaySlotValue,(int)RemainingCapacityOfSlot2);;
							}
							
						
							
						 	DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
							DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();

							if(RemainingPeopleInCompartment2>0)
							{
						
								RemainingCapacityOfSlot2=((slotcapacity.get(FridaySlotValue))-RemainingPeopleInCompartment2);
							}
							else
							{
							
								RemainingCapacityOfSlot2=((slotcapacity.get(FridaySlotValue))-EnteredPeopleCount2);
							}
							RemainingPeopleInCompartment2=EnteredPeopleCount2-slotcapacity.get(FridaySlotValue);
						
							
							if(RemainingCapacityOfSlot2>0)
							{
								
								
							}
							if((RemainingPeopleInCompartment2>0)&&(RemainingCapacityOfSlot2<=0))
							{
								
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(EnteredPeopleCount2-RemainingPeopleInCompartment2);
							
								
							}
							 
							else
							{
							
								PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(FridaySlotValue)-RemainingCapacityOfSlot2);
							
							}
							
							slotcapacity.set(FridaySlotValue,(int)RemainingCapacityOfSlot2);
							
							if(RemainingCapacityOfSlot2<=0)
							{
							 
								RemainingCapacityOfSlot2=0;
								FridaySlotTimings++;
								FridaySlotValue++;
							 
							}
			        }
					while(RemainingPeopleInCompartment2>=slotcapacity.get(FridaySlotValue))
					{
					
						if(RemainingCapacityOfSlot2<=0)
						{
							RemainingCapacityOfSlot2=0;
							 
						}
						
						PeopleLeftInCompartment=RemainingPeopleInCompartment2;
						RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(FridaySlotValue);
						
						
				       
						
						
 
						DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
						DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();
				
						
						RemainingCapacityOfSlot2=((RemainingPeopleInCompartment2+slotcapacity.get(FridaySlotValue))-EnteredPeopleCount2);
						
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
						FridaySlotTimings++;
						FridaySlotValue++; 
						
						if(FridaySlotValue>SumOfFriSatDocumentCount-1)
						{
				        	
							break;
						}
					}
					
					if((RemainingPeopleInCompartment2>0)&&(RemainingPeopleInCompartment2<slotcapacity.get(FridaySlotValue)))
					{
						 
						 
					
						DarshanTimeInHoursandMinutes=(FridaySlotTime.get(FridaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=FridaySlotTime.get(FridaySlotTimings).getHours();
						DarshanTimeInMinutes=FridaySlotTime.get(FridaySlotTimings).getMinutes();
						
						RemainingCapacityOfSlot2=((slotcapacity.get(FridaySlotValue))-RemainingPeopleInCompartment2);
						RemainingPeopleInCompartment2=RemainingPeopleInCompartment2-slotcapacity.get(FridaySlotValue);
						
						
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
							
							PeopleConsideredForDarshanVQC2=PeopleConsideredForDarshanVQC2+(slotcapacity.get(FridaySlotValue))-(RemainingCapacityOfSlot2);
				
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
					FridaySlotTimings++;
					FridaySlotValue++;
					 break;
				 	}
				FridaySlotTimings--;
			 }
		 } 
	    VQC2FriResult.add(RemainingPeopleInCompartment2);
	 	VQC2FriResult.add(RemainingCapacityOfSlot2);
	 	VQC2FriResult.add((double)TimeUnit.MILLISECONDS.toMinutes(DarshanTimeInHoursandMinutes));
	 	VQC2FriResult.add((double)TimeUnit.MILLISECONDS.toHours(DarshanTimeInHoursandMinutes));
	 	VQC2FriResult.add((double)DarshanTimeInHours);
	 	VQC2FriResult.add((double)DarshanTimeInMinutes);
	 	VQC2FriResult.add(PeopleConsideredForDarshanVQC2);
	 	return VQC2FriResult; 
	 	
	 	
	}
	
	
}






