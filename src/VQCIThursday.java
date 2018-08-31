


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
public class VQCIThursday {
	
	
	static	ArrayList<String> Slot = new ArrayList<String>();
	
	
	static String str,str1,currentdate,PeopleEnteringTime, Date,VQC1ExactTimeFromDB,DBLastdocumentTime,DBLastDocumentDay,currentday,u,Remainingslot,DBToTiming, ThursdayToTiming;
	static int Count = 0,DocumentCountOfThursdayCollection=0,DocumentCountOfFridayCollection=0,temp1,temp2,sumOfThuFriDocumentCount,ThursdayDocumentid,e, grt, ThursdayCollCount;
	static long ThursdayCollectionCount;
	static double exem;


	public static ArrayList<Double> main(String[] args) throws NumberFormatException, IOException, ParseException {
				// TODO Auto-generated method stub
				
		int NumberOfPeopleEnteredIntoCompartment=VQCIServlet.PeopleEnteredIntoCompartment;
		String EnteringTimeOfCompartment=args[1];
		double RemainingPeopleIntheCompartment1=Double.parseDouble(args[2]),RemainingCapacityOftheSlot1=Double.parseDouble(args[3]);
		
		 
		ArrayList<String> slotTimings1Thu= new ArrayList<>();
		ArrayList<Integer>  slotCapacity1Thu = new ArrayList<>();
		ArrayList<String> slotTimings1Fri= new ArrayList<>(); 
		
		
		String Entering_time[]={EnteringTimeOfCompartment};
				
	
		currentdate=VQCIServlet.UICurrentDate;
		currentday=VQCIServlet.UICurrentDay;
		PeopleEnteringTime=VQCIServlet.UIEnteringTime;
		//String d=" ";
	
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
         System.out.println("lkjlj:::"+cursor3);
 		
 	    while(cursor3.hasNext())
 	    {
 	    	DBObject result=cursor3.next();
 			  Map resultmap=result.toMap();
 			  
 			  VQC1ExactTimeFromDB=  (String)resultmap.get("VQC1_Exact_time");
 			  System.out.println("last::"+VQC1ExactTimeFromDB);
 			  
 			 if(VQC1ExactTimeFromDB==null)
			  {
				 DBCursor cursor4 = HomepageCollection.find().sort(new BasicDBObject("_id", -1)).limit(2);
	             System.out.println("lkjlj:::"+cursor3); 
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
 	    	 DBLastdocumentTime=Cdate[0];
 	    	 DBLastDocumentDay=Cdate[1];
 	 	    
 	    }
 	    
 	    }  
         }  
      
		  temp1=DocumentCountOfThursdayCollection;
		  temp1=0;
		  DocumentCountOfThursdayCollection=temp1;
		  
		  temp2=DocumentCountOfFridayCollection;
		  temp2=0;
		  DocumentCountOfFridayCollection=temp2;
		 
		  
  DBCollection ThursdayCollection = db.getCollection("Thursday"); 
			  System.out.println("Collection Thursday selected successfully");
			  
			ThursdayCollectionCount =ThursdayCollection.count();
			
			ThursdayCollCount = (int) ThursdayCollectionCount;
			 if(currentday.equals(DBLastDocumentDay))
			  {
				  System.out.println("Day::"+currentday+"--------"+"Cday::"+DBLastDocumentDay);
				  
				  SimpleDateFormat curFormater = new SimpleDateFormat("H:m"); 
		            Date dateObj = curFormater.parse(DBLastdocumentTime); 
		            SimpleDateFormat postFormater = new SimpleDateFormat("HH:mm"); 
		            String newDateStr = postFormater.format(dateObj); 
		           
				  BasicDBObject que = new BasicDBObject();
				  que.put("To", newDateStr);
				  DBCursor cursor = ThursdayCollection.find(que);
				  
				  while(cursor.hasNext())
					 {
					  DBObject result=cursor.next();
					  Map resultmap=result.toMap();
					  ThursdayToTiming=  (String)resultmap.get("To");
					  ThursdayDocumentid = (Integer) resultmap.get("ID");
					  
					  ThursdayDocumentid++;
					  for (int LastDocumentId=ThursdayDocumentid; LastDocumentId<ThursdayCollCount; LastDocumentId++ ){
						  BasicDBObject ques2 = new BasicDBObject("ID", ThursdayDocumentid);
						  ques2.put("ID", ThursdayDocumentid);
						  DBCursor cursorv = ThursdayCollection.find(ques2);
						  
						  while(cursorv.hasNext())
							 {
							  DBObject resultv=cursorv.next();
							  Map resultmapv=resultv.toMap();
										  
						  
						  DBToTiming = (String) resultmapv.get("To");
						 ThursdayDocumentid++;
						  
						  String DBVQC1MaxCapacity=(String)resultmapv.get("VQC1_Max_Capacity");
						  
							
						  if(!DBVQC1MaxCapacity.equals("")){
							  int dbVQC1MaxCapacity=  Integer.parseInt((String)resultmapv.get("VQC1_Max_Capacity"));
							  slotCapacity1Thu.add(dbVQC1MaxCapacity);
							 
							  slotTimings1Thu.add(DBToTiming);
							  DocumentCountOfThursdayCollection++;
							
						  }
					  
							 }
					 }}
				   }
				  
				  
		  
			 else if(DBLastDocumentDay.equals("Wednesday"))
			 {
				 
				 DBCursor cursor = ThursdayCollection.find();
				  while(cursor.hasNext())
					 {
					  DBObject result=cursor.next();
					  Map resultmap=result.toMap();
					  
					  String ToTiming=  (String)resultmap.get("To");
					  String DBVQC1MaxCapacity=(String)resultmap.get("VQC1_Max_Capacity");
					  if(!DBVQC1MaxCapacity.equals("")){
						  int dbVQC1MaxCapacity=  Integer.parseInt((String)resultmap.get("VQC1_Max_Capacity"));
						  slotCapacity1Thu.add(dbVQC1MaxCapacity);
						  slotTimings1Thu.add(ToTiming);
						  
						  DocumentCountOfThursdayCollection++;
						  }
					 }
				  
			 }
			  
		 
  
      DBCollection FridayCollection = db.getCollection("Friday");
         System.out.println("Collection Friday selected successfully");
          
         DBCursor cursor1 = FridayCollection.find();
		
		      while(cursor1.hasNext())
				 {
				  DBObject result=cursor1.next();
				  Map resultmap=result.toMap();
				  
				  String ToTiming=  (String)resultmap.get("To");
				  String DBVQC1MaxCapacity=(String)resultmap.get("VQC1_Max_Capacity");
				  if(!DBVQC1MaxCapacity.equals("")){
					  int dbVQC1MaxCapacity=  Integer.parseInt((String)resultmap.get("VQC1_Max_Capacity"));
					slotCapacity1Thu.add(dbVQC1MaxCapacity); 
					  
					  slotTimings1Fri.add(ToTiming);
					  DocumentCountOfFridayCollection++;
				  }
				  
				    
		         }

		         
		     	ArrayList<Double> ResThu1= new ArrayList<>();	  
			 ResThu1=estimatedtime(Entering_time, slotTimings1Thu, slotTimings1Fri, slotCapacity1Thu,RemainingPeopleIntheCompartment1,RemainingCapacityOftheSlot1,NumberOfPeopleEnteredIntoCompartment);
			return ResThu1;
		}

	public static ArrayList<Double> estimatedtime( String[] Enteringtime,
			ArrayList<String> slotTimingsThu,ArrayList<String> slotTimingsFri,
			ArrayList<Integer> slotcapacity,
			double RemainingPeopleInCompartment1,
			double RemainingCapacityOfSlot1,
		 	int NumberOfPeopleEntered) throws IOException, ParseException {
		
		double PeopleConsideredForDarshanVQC1=0;
		long  DarshanTimeInHoursandMinutes=0;
		long DarshanTimeInHours =0;
		long DarshanTimeInMinutes=0;
		double PeopleLeftInCompartment;
			
		int lent= 0;
		
		ArrayList<Double> VQC1ThuResult= new ArrayList<>();
		 
		ArrayList<Date> ThursdaySlotTime = new ArrayList<>();
		ArrayList<Date> FridaySlotTime= new ArrayList<>();
		ArrayList<String>slotTimings= new ArrayList<>();
		slotTimings.addAll(slotTimingsThu);
		slotTimings.addAll(slotTimingsFri);
		
		
		
		ArrayList<Date> enteredTime = new ArrayList<>();
		 
		for ( String h:slotTimingsThu){
			  
				SimpleDateFormat format = new SimpleDateFormat("HH:mm");
				Date date1 = format.parse(h);
				ThursdaySlotTime.add(date1);
		 
		}
		 
		for ( String h:slotTimingsFri){
			  
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);
			Calendar c = Calendar.getInstance();
			c.setTime(date2);
			c.add(Calendar.DATE, 1); // number of days to add
		 	Date currentDatePlusOne = c.getTime();
		 	FridaySlotTime.add(currentDatePlusOne);
		 	}
		
		ThursdaySlotTime.addAll(FridaySlotTime);
		
	 
		
		for ( String h:Enteringtime){
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date2 = format.parse(h);	
			enteredTime.add(date2);
		}
		
		sumOfThuFriDocumentCount=0;
		sumOfThuFriDocumentCount=DocumentCountOfThursdayCollection+DocumentCountOfFridayCollection;
	  System.out.println("total::"+sumOfThuFriDocumentCount);
	 
	  int ThursdaySlotValue = VQCIServlet.VQC1ThuSlotValueCount;
	 	for(int EnteringPeopleTime = 0;EnteringPeopleTime<Enteringtime.length;EnteringPeopleTime++) {
	 		
			for(int ThursdaySlotTimings=VQCIServlet.VQC1ThuSlotTimeCount;ThursdaySlotTimings<slotTimings.size();ThursdaySlotTimings++){
				 
				if (EnteringPeopleTime>0){
					VQCIServlet.VQC1ThuSlotTimeCount=ThursdaySlotTimings;
					VQCIServlet.VQC1ThuSlotValueCount=ThursdaySlotValue;
					break;
				 
				}
				
				double EnteredPeopleCount = NumberOfPeopleEntered;
				 
		         
				if(EnteringPeopleTime>20){
					
					break;
				}
				
				if((RemainingCapacityOfSlot1>0)&&((enteredTime.get(0).compareTo(ThursdaySlotTime.get(ThursdaySlotTimings))) >=0 ))
				{
					
					ThursdaySlotTimings++;
					ThursdaySlotValue++;
					RemainingCapacityOfSlot1=0;
				}
				
				if( (ThursdaySlotTime.get(ThursdaySlotTimings).compareTo(enteredTime.get(0))) >=0)
				{
					if(ThursdaySlotValue>sumOfThuFriDocumentCount-1)
					{
			        	if(EnteredPeopleCount> slotcapacity.get(ThursdaySlotValue))
				        {
						 	if(RemainingCapacityOfSlot1<=0)
							{
								RemainingCapacityOfSlot1=0;
								
							}
				        	if(RemainingCapacityOfSlot1>0)
							{
								slotcapacity.set(ThursdaySlotValue,(int)RemainingCapacityOfSlot1);
							}
						 
				        	RemainingPeopleInCompartment1=RemainingPeopleInCompartment1+EnteredPeopleCount-(slotcapacity.get(ThursdaySlotValue));
							
				        	DarshanTimeInHoursandMinutes=(ThursdaySlotTime.get(ThursdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							DarshanTimeInHours=ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
							DarshanTimeInMinutes=ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();
							
							RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slotcapacity.get(ThursdaySlotValue))-EnteredPeopleCount);
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
					 if(EnteredPeopleCount<=slotcapacity.get(ThursdaySlotValue)){
						 
						 if(RemainingCapacityOfSlot1<=0)
							{
								RemainingCapacityOfSlot1=0;
							}
						 
						if(RemainingCapacityOfSlot1>0)
						{
							slotcapacity.set(ThursdaySlotValue,(int)RemainingCapacityOfSlot1);;
						}
						
						System.out.println("Capacity of people in this slot ="+slotcapacity.get(ThursdaySlotValue));
						System.out.println("REmaining people in the compartment="+RemainingPeopleInCompartment1);
			        	
						DarshanTimeInHoursandMinutes=(ThursdaySlotTime.get(ThursdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
						DarshanTimeInMinutes=ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();
						
						if(RemainingPeopleInCompartment1>0)
						{
							RemainingCapacityOfSlot1=((slotcapacity.get(ThursdaySlotValue))-RemainingPeopleInCompartment1);
						}
						else
						{
							RemainingCapacityOfSlot1=((slotcapacity.get(ThursdaySlotValue))-EnteredPeopleCount);
						}
						RemainingPeopleInCompartment1=EnteredPeopleCount-slotcapacity.get(ThursdaySlotValue);
						
						if(RemainingCapacityOfSlot1>0)
						{
							
						}
						
						if((RemainingPeopleInCompartment1>0)&&(RemainingCapacityOfSlot1<=0))
						{
							PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(EnteredPeopleCount-RemainingPeopleInCompartment1);
								
						}
						else
						{
							PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(slotcapacity.get(ThursdaySlotValue)-RemainingCapacityOfSlot1);
							
						}
						
						
						slotcapacity.set(ThursdaySlotValue,(int)RemainingCapacityOfSlot1);
						
						if(RemainingCapacityOfSlot1<=0)
						{
							
							RemainingCapacityOfSlot1=0;
							
						}
		        }
				while(RemainingPeopleInCompartment1>=slotcapacity.get(ThursdaySlotValue))
				{
					if(RemainingCapacityOfSlot1<=0)
					{
						RemainingCapacityOfSlot1=0;
						
					}
					PeopleLeftInCompartment=RemainingPeopleInCompartment1;
					RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slotcapacity.get(ThursdaySlotValue);
					
					if(ThursdaySlotValue>sumOfThuFriDocumentCount-1)
					{
					
			        	System.out.println("slots are over");
						break;
					}
			       
					DarshanTimeInHoursandMinutes=(ThursdaySlotTime.get(ThursdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					DarshanTimeInHours=ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
					DarshanTimeInMinutes=ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();
					
					RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slotcapacity.get(ThursdaySlotValue))-EnteredPeopleCount);
					
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
				
				if((RemainingPeopleInCompartment1>0)&&(RemainingPeopleInCompartment1<slotcapacity.get(ThursdaySlotValue)))
				{
					DarshanTimeInHoursandMinutes=(ThursdaySlotTime.get(ThursdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
					DarshanTimeInHours=ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
					DarshanTimeInMinutes=ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();
					
					RemainingCapacityOfSlot1=((slotcapacity.get(ThursdaySlotValue))-RemainingPeopleInCompartment1);
					RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slotcapacity.get(ThursdaySlotValue);
					
					if(RemainingCapacityOfSlot1>0)
					{
						
					}
					
				}
				if(RemainingPeopleInCompartment1<0){
					RemainingPeopleInCompartment1=0;
					
				 }	
			        	System.out.println("slots are over");
						break;
					}
			       
						 if(EnteredPeopleCount>slotcapacity.get(ThursdaySlotValue))
					        {
							 
					        	if(RemainingCapacityOfSlot1<=0)
								{
									RemainingCapacityOfSlot1=0;
									
								}
					        	if(RemainingCapacityOfSlot1>0)
								{
									slotcapacity.set(ThursdaySlotValue,(int)RemainingCapacityOfSlot1);;
								}
					        	RemainingPeopleInCompartment1=RemainingPeopleInCompartment1+EnteredPeopleCount-(slotcapacity.get(ThursdaySlotValue));
								
					        	DarshanTimeInHoursandMinutes=(ThursdaySlotTime.get(ThursdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
								DarshanTimeInHours=ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
								DarshanTimeInMinutes=ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();
								
								RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slotcapacity.get(ThursdaySlotValue))-EnteredPeopleCount);
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
									ThursdaySlotTimings++;
									ThursdaySlotValue++;
									
									
									
								}
						
								
					        }
						 if(EnteredPeopleCount<=slotcapacity.get(ThursdaySlotValue)){
							 
							 if(RemainingCapacityOfSlot1<=0)
								{
									RemainingCapacityOfSlot1=0;
									
								}
							 
							if(RemainingCapacityOfSlot1>0)
							{
								slotcapacity.set(ThursdaySlotValue,(int)RemainingCapacityOfSlot1);;
							}
							
							DarshanTimeInHoursandMinutes=(ThursdaySlotTime.get(ThursdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
							DarshanTimeInHours=ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
							DarshanTimeInMinutes=ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();
							
							if(RemainingPeopleInCompartment1>0)
							{
								RemainingCapacityOfSlot1=((slotcapacity.get(ThursdaySlotValue))-RemainingPeopleInCompartment1);
							}
							else
							{
								RemainingCapacityOfSlot1=((slotcapacity.get(ThursdaySlotValue))-EnteredPeopleCount);
							}
							RemainingPeopleInCompartment1=EnteredPeopleCount-slotcapacity.get(ThursdaySlotValue);
							
							if(RemainingCapacityOfSlot1>0)
							{
								
							}
							
							if((RemainingPeopleInCompartment1>0)&&(RemainingCapacityOfSlot1<=0))
							{
								PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(EnteredPeopleCount-RemainingPeopleInCompartment1);
								
							}
							
							else
							{
								PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(slotcapacity.get(ThursdaySlotValue)-RemainingCapacityOfSlot1);
								
							}
							
							slotcapacity.set(ThursdaySlotValue,(int)RemainingCapacityOfSlot1);
							
							if(RemainingCapacityOfSlot1<=0 )
							{
								
								RemainingCapacityOfSlot1=0;
								ThursdaySlotTimings++;
								ThursdaySlotValue++;
								
							
							}
			        }
					while(RemainingPeopleInCompartment1>=slotcapacity.get(ThursdaySlotValue))
					{
						
						if(RemainingCapacityOfSlot1<=0)
						{
							RemainingCapacityOfSlot1=0;
							
						}
						PeopleLeftInCompartment=RemainingPeopleInCompartment1;
						RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slotcapacity.get(ThursdaySlotValue);
						
						DarshanTimeInHoursandMinutes=(ThursdaySlotTime.get(ThursdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
						DarshanTimeInMinutes=ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();
						
						RemainingCapacityOfSlot1=((RemainingPeopleInCompartment1+slotcapacity.get(ThursdaySlotValue))-EnteredPeopleCount);
						
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
						
						ThursdaySlotTimings++;
						ThursdaySlotValue++; 
						
						if(ThursdaySlotValue>sumOfThuFriDocumentCount-1 )
						{
							break;	
				        
						}
				       
					}
					
					if((RemainingPeopleInCompartment1>0)&&(RemainingPeopleInCompartment1<slotcapacity.get(ThursdaySlotValue)))
					{
						
						DarshanTimeInHoursandMinutes=(ThursdaySlotTime.get(ThursdaySlotTimings).getTime()- enteredTime.get(0).getTime())+3600000;
						DarshanTimeInHours=ThursdaySlotTime.get(ThursdaySlotTimings).getHours();
						DarshanTimeInMinutes=ThursdaySlotTime.get(ThursdaySlotTimings).getMinutes();

						RemainingCapacityOfSlot1=((slotcapacity.get(ThursdaySlotValue))-RemainingPeopleInCompartment1);
						RemainingPeopleInCompartment1=RemainingPeopleInCompartment1-slotcapacity.get(ThursdaySlotValue);
						
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
							PeopleConsideredForDarshanVQC1=PeopleConsideredForDarshanVQC1+(slotcapacity.get(ThursdaySlotValue))-(RemainingCapacityOfSlot1);
							
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
					ThursdaySlotTimings++;
					ThursdaySlotValue++;
					
					break;
					
				}
				ThursdaySlotTimings--;
				
			}
			 
		} 
	 	
	 	 VQC1ThuResult.add(RemainingPeopleInCompartment1);
	 	VQC1ThuResult.add(RemainingCapacityOfSlot1);
	 	VQC1ThuResult.add((double)TimeUnit.MILLISECONDS.toMinutes(DarshanTimeInHoursandMinutes));
	 	VQC1ThuResult.add((double)TimeUnit.MILLISECONDS.toHours(DarshanTimeInHoursandMinutes));
	 	VQC1ThuResult.add((double)DarshanTimeInHours);
	 	VQC1ThuResult.add((double)DarshanTimeInMinutes);
	 	VQC1ThuResult.add(PeopleConsideredForDarshanVQC1);
	 	VQC1ThuResult.add(exem);
	 	 
	 	return VQC1ThuResult; 
	 	
	 	
	}
	
	
}






