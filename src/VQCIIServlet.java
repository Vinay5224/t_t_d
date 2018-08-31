



import java.io.IOException;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * Servlet implementation class VQCIIServlet
 */
@WebServlet("/VQCIIServlet")
public class VQCIIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	static int  VQC2TueSlotTimeCount ,VQC2TueSlotValueCount;
	static int VQC2MonSlotTimeCount ,VQC2MonSlotValueCount;
	static int VQC2WedSlotTimeCount ,VQC2WedSlotValueCount;
	static int VQC2ThuSlotTimeCount ,VQC2ThuSlotValueCount;
	static int VQC2FriSlotTimeCount ,VQC2FriSlotValueCount;
	static int VQC2SatSlotTimeCount ,VQC2SatSlotValueCount;
	static int VQC2SunSlotTimeCount ,VQC2SunSlotValueCount;
	

	static String UIEnteringTime,UICurrentDay,UICurrentDate;
	static int PeopleEnteredIntoCompartment2,numberOfPeopleEnteredVQC2;
	
	static Integer darshanTimeInMins,remainingSlotCapacityVQC2;
	static String darshanTimeInHrMin,VQC2exacttime,temp="00:00",tog="0";
	
	static String VQCIIValue=null;
	
	
	ArrayList<Double> VQCIIReturnedValues = new ArrayList<>();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VQCIIServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
RequestDispatcher dispatcher = request.getRequestDispatcher("DarshanTimeEstimation.jsp");
		
		dispatcher.forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String VQCIIValue = request.getParameter("vqc2");
		
		
		 PeopleEnteredIntoCompartment2 = Integer.parseInt(VQCIIValue.trim());
		
		
		UIEnteringTime = request.getParameter("time");
		
		
		UICurrentDay = request.getParameter("day").trim();
		
		
		UICurrentDate = request.getParameter("date");
		
		
		
		if(UICurrentDay.equalsIgnoreCase("Tuesday"))
		{
		System.out.println("VQCIITUESDAY");
		VQCIITuesday  VQC2tue= new  VQCIITuesday();
	
		
		try {
			
			if(UIEnteringTime.equalsIgnoreCase("0:00"))
			{
			
				VQCIIReturnedValues=VQC2tue.main(new String[] { VQCIIValue,UIEnteringTime,"0","0"});
				
				
				darshanTimeInMins=Integer.valueOf(VQCIIReturnedValues.get(2).intValue());
				
				
				numberOfPeopleEnteredVQC2=Integer.parseInt(VQCIIValue);
				
				NumberFormat formatter = new DecimalFormat("00.#");
				if(VQCIIReturnedValues.get(5)== 0.0){
					
					
				List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
						Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue())+"",
						(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
				
					
				 String result1 = String.join("@", list1);
			      
			        response.getWriter().write(result1);
			        
				}else{
					
					List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
							Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+Integer.valueOf(VQCIIReturnedValues.get(5).intValue())+"",
							(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
					
					String result1 = String.join("@", list1);
				       
				        response.getWriter().write(result1);
				}
				
				
			
			
				
				darshanTimeInHrMin=Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue());		
				remainingSlotCapacityVQC2=Integer.valueOf(VQCIIReturnedValues.get(1).intValue());
			
	SimpleDateFormat format = new SimpleDateFormat("HH:mm");
				
				Date date1 = format.parse(darshanTimeInHrMin);
				Date date2 = format.parse(temp);
				
				
				long diff2=date1.getTime() - date2.getTime();
				long diff = date1.getHours() - date2.getHours();
				int diff1 = date1.getMinutes() - date2.getMinutes();
			
				temp=darshanTimeInHrMin;
				
				if((diff2>=0)&&(tog=="0"))
				{
					VQC2exacttime=darshanTimeInHrMin+"-Tuesday";
					
				}
				else{
					VQC2exacttime=darshanTimeInHrMin+"-Wednesday";
					
					tog="1";
				}
			
			
			}
			else
			{
				VQCIIReturnedValues=VQC2tue.main(new String[] { VQCIIValue,UIEnteringTime,String.valueOf(VQCIIReturnedValues.get(0)),String.valueOf(VQCIIReturnedValues.get(1))});
	 			
			
				
				NumberFormat formatter = new DecimalFormat("00.#");
				if(VQCIIReturnedValues.get(5)== 0.0){
					
					
				List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
						Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue())+"",
						(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
				
					
				 String result1 = String.join("@", list1);
			      
			        response.getWriter().write(result1);
			        
				}else{
					
					List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
							Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+Integer.valueOf(VQCIIReturnedValues.get(5).intValue())+"",
							(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
					
					String result1 = String.join("@", list1);
				       
				        response.getWriter().write(result1);
				}
				
				
				darshanTimeInMins=Integer.valueOf(VQCIIReturnedValues.get(2).intValue());
				numberOfPeopleEnteredVQC2=Integer.parseInt(VQCIIValue);
				
				
			
			
				darshanTimeInHrMin=Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue());		
				remainingSlotCapacityVQC2=Integer.valueOf(VQCIIReturnedValues.get(1).intValue());
	SimpleDateFormat format = new SimpleDateFormat("HH:mm");
				
				Date date1 = format.parse(darshanTimeInHrMin);
				Date date2 = format.parse(temp);
				long diff2=date1.getTime() - date2.getTime();
				long diff = date1.getHours() - date2.getHours(); 
				int diff1 = date1.getMinutes() - date2.getMinutes();
				
				temp=darshanTimeInHrMin;
			
				
				if((diff2>=0)&&(tog=="0"))
				{
					VQC2exacttime=darshanTimeInHrMin+"-Tuesday";
				
				}
				else{
					VQC2exacttime=darshanTimeInHrMin+"-Wednesday";
				
					tog="1";
				}
				
			
			
			}
			
			MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
			// Now connect to your databases
	         DB db = mongoClient.getDB( "TTD" );
	        
			
	         DBCollection coll = db.getCollection("Homepage");
	      
	         
	         BasicDBObject searchquery = new BasicDBObject("Date",  UICurrentDate /*request.getParameter("time").trim()*/).
	        		 append("Time",request.getParameter("time").trim() );
	         
	       

	         BasicDBObject doc = new BasicDBObject("VQC2_No_of_Pilgrims", numberOfPeopleEnteredVQC2).
	        		 append("VQC2_Exact_time",VQC2exacttime).
		         		append("Remainingslot2",remainingSlotCapacityVQC2).
	        		 append("VQC2_Est_DarshanTime",darshanTimeInMins);
	         coll.update(searchquery, new BasicDBObject("$set", doc ), true, false);
	        		 
	        		
			 
			
					} catch (NumberFormatException e) {
					e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
		}
		else if(UICurrentDay.equalsIgnoreCase("Wednesday"))
		{
			
			System.out.println("VQCIIWEDNESDAY");
			VQCIIWednesday  VQC2wed= new  VQCIIWednesday();
			
			
			try {
				
				if(UIEnteringTime.equalsIgnoreCase("0:00"))
				{
				
					VQCIIReturnedValues=VQC2wed.main(new String[] { VQCIIValue,UIEnteringTime,"0","0"});
					
					
					darshanTimeInMins=Integer.valueOf(VQCIIReturnedValues.get(2).intValue());
					
					
					numberOfPeopleEnteredVQC2=Integer.parseInt(VQCIIValue);
					
					NumberFormat formatter = new DecimalFormat("00.#");
					if(VQCIIReturnedValues.get(5)== 0.0){
						
						
					List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
							Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue())+"",
							(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
					
						
					 String result1 = String.join("@", list1);
				       
				        response.getWriter().write(result1);
				        
					}else{
						
						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
								Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+Integer.valueOf(VQCIIReturnedValues.get(5).intValue())+"",
								(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
						
						String result1 = String.join("@", list1);
					       
					        response.getWriter().write(result1);
					}
					
					
				
					
					darshanTimeInHrMin=Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue());		
					remainingSlotCapacityVQC2=Integer.valueOf(VQCIIReturnedValues.get(1).intValue());
				
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					
					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					
					
					long diff2=date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();
				
					temp=darshanTimeInHrMin;
					
					if((diff2>=0)&&(tog=="0"))
					{
						VQC2exacttime=darshanTimeInHrMin+"-Wednesday";
						
					}
					else{
						VQC2exacttime=darshanTimeInHrMin+"-Thursday";
						
						tog="1";
					}
				
				
				}
				else
				{
					VQCIIReturnedValues=VQC2wed.main(new String[] { VQCIIValue,UIEnteringTime,String.valueOf(VQCIIReturnedValues.get(0)),String.valueOf(VQCIIReturnedValues.get(1))});
		 			
				
					
					NumberFormat formatter = new DecimalFormat("00.#");
					if(VQCIIReturnedValues.get(5)== 0.0){
						
						
					List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
							Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue())+"",
							(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
					
						
					 String result1 = String.join("@", list1);
				       
				        response.getWriter().write(result1);
				        
					}else{
						
						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
								Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+Integer.valueOf(VQCIIReturnedValues.get(5).intValue())+"",
								(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
						
						String result1 = String.join("@", list1);
					     
					        response.getWriter().write(result1);
					}
					darshanTimeInMins=Integer.valueOf(VQCIIReturnedValues.get(2).intValue());
					numberOfPeopleEnteredVQC2=Integer.parseInt(VQCIIValue);
					
					
				
					darshanTimeInHrMin=Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue());		
					remainingSlotCapacityVQC2=Integer.valueOf(VQCIIReturnedValues.get(1).intValue());
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					
					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					long diff2=date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours(); 
					int diff1 = date1.getMinutes() - date2.getMinutes();
					
					temp=darshanTimeInHrMin;
					
					
					if((diff2>=0)&&(tog=="0"))
					{
						VQC2exacttime=darshanTimeInHrMin+"-Wednesday";
					
					}
					else{
						VQC2exacttime=darshanTimeInHrMin+"-Thursday";
						
						tog="1";
					}
					
				
				
				}
				
				MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
				MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
				// Now connect to your databases
		         DB db = mongoClient.getDB( "TTD" );
		       
				
		         DBCollection coll = db.getCollection("Homepage");
		        
		         
		         BasicDBObject searchquery = new BasicDBObject("Date",  UICurrentDate /*request.getParameter("time").trim()*/).
		        		 append("Time",request.getParameter("time").trim() );
		         
		        

		         BasicDBObject doc = new BasicDBObject("VQC2_No_of_Pilgrims", numberOfPeopleEnteredVQC2).
		        		 append("VQC2_Exact_time",VQC2exacttime).
			         		append("Remainingslot2",remainingSlotCapacityVQC2).
		        		 append("VQC2_Est_DarshanTime",darshanTimeInMins);
		         coll.update(searchquery, new BasicDBObject("$set", doc ), true, false);
		        		 
		        		
				 
				
						} catch (NumberFormatException e) {
							
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
			
	}
		
		
		else if(UICurrentDay.equalsIgnoreCase("Thursday"))
		{
			
			System.out.println("VQCIIThursday");
			VQCIIThursday  VQC2thu= new  VQCIIThursday();
		
			
			try {
				
				if(UIEnteringTime.equalsIgnoreCase("0:00"))
				{
					
					VQCIIReturnedValues=VQC2thu.main(new String[] { VQCIIValue,UIEnteringTime,"0","0"});
					
					
					darshanTimeInMins=Integer.valueOf(VQCIIReturnedValues.get(2).intValue());
					
					
					numberOfPeopleEnteredVQC2=Integer.parseInt(VQCIIValue);
					
					NumberFormat formatter = new DecimalFormat("00.#");
					
					
					if(VQCIIReturnedValues.get(5)== 0.0){
						
						
					List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
							Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue())+"",
							(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
					
						
					 String result1 = String.join("@", list1);
				       
				        response.getWriter().write(result1);
				        
					}else{
						
						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
								Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+Integer.valueOf(VQCIIReturnedValues.get(5).intValue())+"",
								(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
						
						String result1 = String.join("@", list1);
					        
					        response.getWriter().write(result1);
					}
					
					
				
					
					darshanTimeInHrMin=Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue());		
					remainingSlotCapacityVQC2=Integer.valueOf(VQCIIReturnedValues.get(1).intValue());
				
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					
					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					
					
					long diff2=date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();
					
					temp=darshanTimeInHrMin;
					
					if((diff2>=0)&&(tog=="0"))
					{
						VQC2exacttime=darshanTimeInHrMin+"-Thursday";
						
					}
					else{
						VQC2exacttime=darshanTimeInHrMin+"-Friday";
					
						tog="1";
					}
				
				
				}
				else
				{
					VQCIIReturnedValues=VQC2thu.main(new String[] { VQCIIValue,UIEnteringTime,String.valueOf(VQCIIReturnedValues.get(0)),String.valueOf(VQCIIReturnedValues.get(1))});
		 			
			
					
					NumberFormat formatter = new DecimalFormat("00.#");
					if(VQCIIReturnedValues.get(5)== 0.0){
						
						
					List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
							Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue())+"",
							(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
					
						
					 String result1 = String.join("@", list1);
				      
				        response.getWriter().write(result1);
				        
					}else{
						
						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
								Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+Integer.valueOf(VQCIIReturnedValues.get(5).intValue())+"",
								(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
						
						String result1 = String.join("@", list1);
					       
					        response.getWriter().write(result1);
					}
					
					darshanTimeInMins=Integer.valueOf(VQCIIReturnedValues.get(2).intValue());
					numberOfPeopleEnteredVQC2=Integer.parseInt(VQCIIValue);
					
					
				
					darshanTimeInHrMin=Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue());		
					remainingSlotCapacityVQC2=Integer.valueOf(VQCIIReturnedValues.get(1).intValue());
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					
					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					long diff2=date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours(); 
					int diff1 = date1.getMinutes() - date2.getMinutes();
				
					temp=darshanTimeInHrMin;
					
					
					if((diff2>=0)&&(tog=="0"))
					{
						VQC2exacttime=darshanTimeInHrMin+"-Thursday";
						
					}
					else{
						VQC2exacttime=darshanTimeInHrMin+"-Friday";
					
						tog="1";
					}
					
				
				
				}
				
				MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
				MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
				// Now connect to your databases
		         DB db = mongoClient.getDB( "TTD" );
		        
				
		         DBCollection coll = db.getCollection("Homepage");
		        
		         
		         BasicDBObject searchquery = new BasicDBObject("Date",  UICurrentDate /*request.getParameter("time").trim()*/).
		        		 append("Time",request.getParameter("time").trim() );
		         
		    

		         BasicDBObject doc = new BasicDBObject("VQC2_No_of_Pilgrims", numberOfPeopleEnteredVQC2).
		        		 append("VQC2_Exact_time",VQC2exacttime).
			         		append("Remainingslot2",remainingSlotCapacityVQC2).
		        		 append("VQC2_Est_DarshanTime",darshanTimeInMins);
		         coll.update(searchquery, new BasicDBObject("$set", doc ), true, false);
		        		 
		        		
				 
				
						} catch (NumberFormatException e) {
							
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
	}
		
		
		else if(UICurrentDay.equalsIgnoreCase("Friday"))
		{
			
			System.out.println("VQCIIFriday");
			VQCIIFriday  VQC2fri= new  VQCIIFriday();
			
			
			try {
				
				if(UIEnteringTime.equalsIgnoreCase("0:00"))
				{
					
					VQCIIReturnedValues=VQC2fri.main(new String[] { VQCIIValue,UIEnteringTime,"0","0"});
					
					darshanTimeInMins=Integer.valueOf(VQCIIReturnedValues.get(2).intValue());
					
					numberOfPeopleEnteredVQC2=Integer.parseInt(VQCIIValue);
					
					NumberFormat formatter = new DecimalFormat("00.#");
					if(VQCIIReturnedValues.get(5)== 0.0){
						
						
					List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
							Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue())+"",
							(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
					
						
					 String result1 = String.join("@", list1);
				        
				        response.getWriter().write(result1);
				        
					}else{
						
						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
								Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+Integer.valueOf(VQCIIReturnedValues.get(5).intValue())+"",
								(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
						
						String result1 = String.join("@", list1);
					      
					        response.getWriter().write(result1);
					}
					
					
					
					darshanTimeInHrMin=Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue());		
					remainingSlotCapacityVQC2=Integer.valueOf(VQCIIReturnedValues.get(1).intValue());
				
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					
					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					
					
					long diff2=date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();
				
					temp=darshanTimeInHrMin;
				
					if((diff2>=0)&&(tog=="0"))
					{
						VQC2exacttime=darshanTimeInHrMin+"-Friday";
						
					}
					else{
						VQC2exacttime=darshanTimeInHrMin+"-Saturday";
					
						tog="1";
					}
				
				
				}
				else
				{
					VQCIIReturnedValues=VQC2fri.main(new String[] { VQCIIValue,UIEnteringTime,String.valueOf(VQCIIReturnedValues.get(0)),String.valueOf(VQCIIReturnedValues.get(1))});
		 		
					NumberFormat formatter = new DecimalFormat("00.#");
					if(VQCIIReturnedValues.get(5)== 0.0){
						
						
					List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
							Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue())+"",
							(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
					
						
					 String result1 = String.join("@", list1);
				       
				        response.getWriter().write(result1);
				        
					}else{
						
						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
								Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+Integer.valueOf(VQCIIReturnedValues.get(5).intValue())+"",
								(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
						
						String result1 = String.join("@", list1);
					       
					        response.getWriter().write(result1);
					}
					
					darshanTimeInMins=Integer.valueOf(VQCIIReturnedValues.get(2).intValue());
					numberOfPeopleEnteredVQC2=Integer.parseInt(VQCIIValue);
					
					
				
					darshanTimeInHrMin=Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue());		
					remainingSlotCapacityVQC2=Integer.valueOf(VQCIIReturnedValues.get(1).intValue());
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					
					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					long diff2=date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours(); 
					int diff1 = date1.getMinutes() - date2.getMinutes();
				
					temp=darshanTimeInHrMin;
				
					
					if((diff2>=0)&&(tog=="0"))
					{
						VQC2exacttime=darshanTimeInHrMin+"-Friday";
						
					}
					else{
						VQC2exacttime=darshanTimeInHrMin+"-Saturday";
						
						tog="1";
					}
					
				
				
				}
				
				MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
				MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
				// Now connect to your databases
		         DB db = mongoClient.getDB( "TTD" );
		        
				
		         DBCollection coll = db.getCollection("Homepage");
		      
		         
		         BasicDBObject searchquery = new BasicDBObject("Date",  UICurrentDate /*request.getParameter("time").trim()*/).
		        		 append("Time",request.getParameter("time").trim() );
		         
		       

		         BasicDBObject doc = new BasicDBObject("VQC2_No_of_Pilgrims", numberOfPeopleEnteredVQC2).
		        		 append("VQC2_Exact_time",VQC2exacttime).
			         		append("Remainingslot2",remainingSlotCapacityVQC2).
		        		 append("VQC2_Est_DarshanTime",darshanTimeInMins);
		         coll.update(searchquery, new BasicDBObject("$set", doc ), true, false);
		        		 
		        		
				 
				
						} catch (NumberFormatException e) {
							
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
	}
		
		else if(UICurrentDay.equalsIgnoreCase("Saturday"))
		{
			
			System.out.println("VQCIISaturday");
			VQCIISaturday  VQC2sat= new  VQCIISaturday();
			
			
			try {
				if(UIEnteringTime.equalsIgnoreCase("0:00"))
				{
					
					VQCIIReturnedValues=VQC2sat.main(new String[] { VQCIIValue,UIEnteringTime,"0","0"});
					
					darshanTimeInMins=Integer.valueOf(VQCIIReturnedValues.get(2).intValue());
					
					numberOfPeopleEnteredVQC2=Integer.parseInt(VQCIIValue);
				
					NumberFormat formatter = new DecimalFormat("00.#");
					if(VQCIIReturnedValues.get(5)== 0.0){
						
						
					List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
							Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue())+"",
							(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
					
						
					 String result1 = String.join("@", list1);
				       
				        response.getWriter().write(result1);
				        
					}else{
						
						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
								Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+Integer.valueOf(VQCIIReturnedValues.get(5).intValue())+"",
								(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
						
						String result1 = String.join("@", list1);
					        
					        response.getWriter().write(result1);
					}
					
					
				
					
					darshanTimeInHrMin=Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue());		
					remainingSlotCapacityVQC2=Integer.valueOf(VQCIIReturnedValues.get(1).intValue());
				
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					
					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					
					
					long diff2=date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();
				
					temp=darshanTimeInHrMin;
					
					if((diff2>=0)&&(tog=="0"))
					{
						VQC2exacttime=darshanTimeInHrMin+"-Saturday";
						
					}
					else{
						VQC2exacttime=darshanTimeInHrMin+"-Sunday";
						
						tog="1";
					}
				
				
				}
				else
				{
					VQCIIReturnedValues=VQC2sat.main(new String[] { VQCIIValue,UIEnteringTime,String.valueOf(VQCIIReturnedValues.get(0)),String.valueOf(VQCIIReturnedValues.get(1))});
		 		
					NumberFormat formatter = new DecimalFormat("00.#");
					if(VQCIIReturnedValues.get(5)== 0.0){
						
						
					List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
							Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue())+"",
							(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
					
						
					 String result1 = String.join("@", list1);
				       
				        response.getWriter().write(result1);
				        
					}else{
						
						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
								Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+Integer.valueOf(VQCIIReturnedValues.get(5).intValue())+"",
								(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
						
						String result1 = String.join("@", list1);
					       
					        response.getWriter().write(result1);
					}
					
					darshanTimeInMins=Integer.valueOf(VQCIIReturnedValues.get(2).intValue());
					numberOfPeopleEnteredVQC2=Integer.parseInt(VQCIIValue);
				
				
				
					darshanTimeInHrMin=Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue());		
					remainingSlotCapacityVQC2=Integer.valueOf(VQCIIReturnedValues.get(1).intValue());
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					
					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					long diff2=date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours(); 
					int diff1 = date1.getMinutes() - date2.getMinutes();
					
					temp=darshanTimeInHrMin;
				
					
					if((diff2>=0)&&(tog=="0"))
					{
						VQC2exacttime=darshanTimeInHrMin+"-Saturday";
						
					}
					else{
						VQC2exacttime=darshanTimeInHrMin+"-Sunday";
						
						tog="1";
					}
					
				
				
				}
				
				MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
				MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
				// Now connect to your databases
		         DB db = mongoClient.getDB( "TTD" );
		        
				
		         DBCollection coll = db.getCollection("Homepage");
		       
		         
		         BasicDBObject searchquery = new BasicDBObject("Date",  UICurrentDate /*request.getParameter("time").trim()*/).
		        		 append("Time",request.getParameter("time").trim() );
		         
		        

		         BasicDBObject doc = new BasicDBObject("VQC2_No_of_Pilgrims", numberOfPeopleEnteredVQC2).
		        		 append("VQC2_Exact_time",VQC2exacttime).
			         		append("Remainingslot2",remainingSlotCapacityVQC2).
		        		 append("VQC2_Est_DarshanTime",darshanTimeInMins);
		         coll.update(searchquery, new BasicDBObject("$set", doc ), true, false);
		        		 
		        		
				 
				
						} catch (NumberFormatException e) {
							
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
	}
		
		
		else if(UICurrentDay.equalsIgnoreCase("Sunday"))
		{
			
			System.out.println("VQCIISunday");
			VQCIISunday  VQC2sun= new  VQCIISunday();
			
			
			try {
				if(UIEnteringTime.equalsIgnoreCase("0:00"))
				{
					
					VQCIIReturnedValues=VQC2sun.main(new String[] { VQCIIValue,UIEnteringTime,"0","0"});
					
					darshanTimeInMins=Integer.valueOf(VQCIIReturnedValues.get(2).intValue());
				
					numberOfPeopleEnteredVQC2=Integer.parseInt(VQCIIValue);
				
					NumberFormat formatter = new DecimalFormat("00.#");
					if(VQCIIReturnedValues.get(5)== 0.0){
						
						
					List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
							Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue())+"",
							(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
					
						
					 String result1 = String.join("@", list1);
				       
				        response.getWriter().write(result1);
				        
					}else{
						
						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
								Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+Integer.valueOf(VQCIIReturnedValues.get(5).intValue())+"",
								(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
						
						String result1 = String.join("@", list1);
					        
					        response.getWriter().write(result1);
					}
					
					
				
					
					darshanTimeInHrMin=Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue());		
					remainingSlotCapacityVQC2=Integer.valueOf(VQCIIReturnedValues.get(1).intValue());
				
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					
					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					
					
					long diff2=date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();
				
					temp=darshanTimeInHrMin;
				
					if((diff2>=0)&&(tog=="0"))
					{
						VQC2exacttime=darshanTimeInHrMin+"-Sunday";
					
					}
					else{
						VQC2exacttime=darshanTimeInHrMin+"-Monday";
					
						tog="1";
					}
				
				
				}
				else
				{
					VQCIIReturnedValues=VQC2sun.main(new String[] { VQCIIValue,UIEnteringTime,String.valueOf(VQCIIReturnedValues.get(0)),String.valueOf(VQCIIReturnedValues.get(1))});
		 			
					NumberFormat formatter = new DecimalFormat("00.#");
					if(VQCIIReturnedValues.get(5)== 0.0){
						
						
					List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
							Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue())+"",
							(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
					
						
					 String result1 = String.join("@", list1);
				      
				        response.getWriter().write(result1);
				        
					}else{
						
						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
								Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+Integer.valueOf(VQCIIReturnedValues.get(5).intValue())+"",
								(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
						
						String result1 = String.join("@", list1);
					       
					        response.getWriter().write(result1);
					}
					
					darshanTimeInMins=Integer.valueOf(VQCIIReturnedValues.get(2).intValue());
					numberOfPeopleEnteredVQC2=Integer.parseInt(VQCIIValue);
					
					
				
					darshanTimeInHrMin=Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue());		
					remainingSlotCapacityVQC2=Integer.valueOf(VQCIIReturnedValues.get(1).intValue());
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					
					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					long diff2=date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours(); 
					int diff1 = date1.getMinutes() - date2.getMinutes();
					
					temp=darshanTimeInHrMin;
					
					
					if((diff2>=0)&&(tog=="0"))
					{
						VQC2exacttime=darshanTimeInHrMin+"-Sunday";
					
					}
					else{
						VQC2exacttime=darshanTimeInHrMin+"-Monday";
					
						tog="1";
					}
					
				
				
				}
				
				MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
				MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
				// Now connect to your databases
		         DB db = mongoClient.getDB( "TTD" );
		        
				
		         DBCollection coll = db.getCollection("Homepage");
		       
		         
		         BasicDBObject searchquery = new BasicDBObject("Date",  UICurrentDate /*request.getParameter("time").trim()*/).
		        		 append("Time",request.getParameter("time").trim() );
		         
		        

		         BasicDBObject doc = new BasicDBObject("VQC2_No_of_Pilgrims", numberOfPeopleEnteredVQC2).
		        		 append("VQC2_Exact_time",VQC2exacttime).
			         		append("Remainingslot2",remainingSlotCapacityVQC2).
		        		 append("VQC2_Est_DarshanTime",darshanTimeInMins);
		         coll.update(searchquery, new BasicDBObject("$set", doc ), true, false);
		        		 
		        		
				 
				
						} catch (NumberFormatException e) {
							
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
	}
		
		
		
		else if(UICurrentDay.equalsIgnoreCase("Monday"))
		{
			
			System.out.println("VQCIIMonday");
			VQCIIMonday  VQC2mon= new  VQCIIMonday();
			
			
			try {
				if(UIEnteringTime.equalsIgnoreCase("0:00"))
				{
				
					VQCIIReturnedValues=VQC2mon.main(new String[] { VQCIIValue,UIEnteringTime,"0","0"});
				
					darshanTimeInMins=Integer.valueOf(VQCIIReturnedValues.get(2).intValue());
					
					numberOfPeopleEnteredVQC2=Integer.parseInt(VQCIIValue);
				
					NumberFormat formatter = new DecimalFormat("00.#");
					if(VQCIIReturnedValues.get(5)== 0.0){
						
						
					List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
							Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue())+"",
							(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
					
						
					 String result1 = String.join("@", list1);
				      
				        response.getWriter().write(result1);
				        
					}else{
						
						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
								Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+Integer.valueOf(VQCIIReturnedValues.get(5).intValue())+"",
								(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
						
						String result1 = String.join("@", list1);
					      
					        response.getWriter().write(result1);
					}
					
					
					
					darshanTimeInHrMin=Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue());		
					remainingSlotCapacityVQC2=Integer.valueOf(VQCIIReturnedValues.get(1).intValue());
				
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					
					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					
					
					long diff2=date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours();
					int diff1 = date1.getMinutes() - date2.getMinutes();
				
					temp=darshanTimeInHrMin;
					
					if((diff2>=0)&&(tog=="0"))
					{
						VQC2exacttime=darshanTimeInHrMin+"-Monday";
						
					}
					else{
						VQC2exacttime=darshanTimeInHrMin+"-Tuesday";
					
						tog="1";
					}
				
				
				}
				else
				{
					VQCIIReturnedValues=VQC2mon.main(new String[] { VQCIIValue,UIEnteringTime,String.valueOf(VQCIIReturnedValues.get(0)),String.valueOf(VQCIIReturnedValues.get(1))});
		 		
					NumberFormat formatter = new DecimalFormat("00.#");
					if(VQCIIReturnedValues.get(5)== 0.0){
						
						
					List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
							Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue())+"",
							(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
					
						
					 String result1 = String.join("@", list1);
				       
				        response.getWriter().write(result1);
				        
					}else{
						
						List<String> list1 = Arrays.asList(Integer.valueOf(VQCIIReturnedValues.get(0).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(6).intValue())+"",Integer.valueOf(VQCIIReturnedValues.get(1).intValue())+"",
								Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+Integer.valueOf(VQCIIReturnedValues.get(5).intValue())+"",
								(VQCIIReturnedValues.get(2).intValue())+"min"+"/" +  Integer.valueOf(VQCIIReturnedValues.get(3).intValue())+"hrs");
						
						String result1 = String.join("@", list1);
					       
					        response.getWriter().write(result1);
					}
					
					darshanTimeInMins=Integer.valueOf(VQCIIReturnedValues.get(2).intValue());
					numberOfPeopleEnteredVQC2=Integer.parseInt(VQCIIValue);
					
					
					darshanTimeInHrMin=Integer.valueOf(VQCIIReturnedValues.get(4).intValue())+":"+formatter.format(VQCIIReturnedValues.get(5).intValue());		
					remainingSlotCapacityVQC2=Integer.valueOf(VQCIIReturnedValues.get(1).intValue());
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					
					Date date1 = format.parse(darshanTimeInHrMin);
					Date date2 = format.parse(temp);
					long diff2=date1.getTime() - date2.getTime();
					long diff = date1.getHours() - date2.getHours(); 
					int diff1 = date1.getMinutes() - date2.getMinutes();
					
					temp=darshanTimeInHrMin;
				
					
					if((diff2>=0)&&(tog=="0"))
					{
						VQC2exacttime=darshanTimeInHrMin+"-Monday";
						
					}
					else{
						VQC2exacttime=darshanTimeInHrMin+"-Tuesday";
					
						tog="1";
					}
					
				
				
				}
				
				MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
				MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
				// Now connect to your databases
		         DB db = mongoClient.getDB( "TTD" );
		       
				
		         DBCollection coll = db.getCollection("Homepage");
		        
		         
		         BasicDBObject searchquery = new BasicDBObject("Date",  UICurrentDate /*request.getParameter("time").trim()*/).
		        		 append("Time",request.getParameter("time").trim() );
		         
		     

		         BasicDBObject doc = new BasicDBObject("VQC2_No_of_Pilgrims", numberOfPeopleEnteredVQC2).
		        		 append("VQC2_Exact_time",VQC2exacttime).
			         		append("Remainingslot2",remainingSlotCapacityVQC2).
		        		 append("VQC2_Est_DarshanTime",darshanTimeInMins);
		         coll.update(searchquery, new BasicDBObject("$set", doc ), true, false);
		        		 
		        		
				 
				
						} catch (NumberFormatException e) {
							
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
			
	}
		

}
	
	
	
}
