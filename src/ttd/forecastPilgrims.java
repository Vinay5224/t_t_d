package ttd;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;



/**
 *  Description:
 *              This gets the Number of Pilgrim for all the previous days from the 'FORECAST' Database and along with the present day number of pilgrims values is 
 *              send via url 'http://52.38.31.24:8000/msg?msg=' to 'R' server and gets the estimated number of pilgrims for the next consecutive days.
 * 				 
 * @author  G.MuniKumari
 *
 */

public class forecastPilgrims {


	static String pilgrimGraphValue="";
	/**
	 * 
	 * @param dbIP
	 * 				this Holds the  Database 'IP'
	 * @param dbPort
	 * 				this Holds the  Database 'Port'	
	 * @param dbName
	 * 				this Holds the  Database Name	
	 * @param collectionName
	 * 				this Holds the  Collection Name in the Database
	 * @param selectedTime
	 * 				the particular time selected in VQC
	 * @param selectedVQC
	 * 				this contains either VQC-1 and VQC-2
	 * @param Presentvqc1
	 * 				this contains the Present day VQC-1 or VQC-2 value from the Homepage collection
	 * @param Date
	 * 				this contains present date from the DarshanTimeEstimation.jsp
	 * @return
	 *        output as 'pilgrimGraphValue'
	 * @throws Exception
	 */
	
	
	/*
	 * Description:
	 * 				This  method sends the number of pilgrims for previous consecutive days through https to the R sever and gets the three consecutive outputs
	 * from the 'R' server and returns to the pilgrimGraphValue to the slotprocess.jsp.
	 */
	
	public static String getForecastResult(String dbIP,int dbPort,String dbName,String collectionName,String selectedTime,String selectedVQC, String Presentvqc1, String Date) throws Exception {

 //Initiating the 'fetchFromDB' to the HashMap
		HashMap<Integer,String> vqcMap=DBInteract.fetchFromDB(dbIP, dbPort,dbName, collectionName,selectedTime,selectedVQC, Presentvqc1);//DBInteract.fetchFromDB("172.20.20.10", 27017, "FORECAST", "mondayhis", "3:00","VQC2");
		String msg="",Dbdate="";
		

		String LastDate="";
		//Splits the Number of Pilgrims and Date with comma separated into the string
		for(int i=0;i<vqcMap.size();i++)
		{
			String[] splits= (String[])vqcMap.get(i).split(":");

			msg+=""+splits[0]+",";
			Dbdate +=""+splits[1]+",";
			if(i == (vqcMap.size()-1))
			{
				///last date
				LastDate=splits[1];
				
			}
		}
		
//If the Presentvqc1 is not equal to then present date is added to the 'Dbdate'
		 if(Presentvqc1!=null)
			{
			 Dbdate = Dbdate.concat(Date).concat(",");
				
			}
		 //Dateformat 
		   final DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		   
		   //with last date getting the next three consecutive dates
		   for(int i=0;i<3;i++){
			   
			 Calendar c = Calendar.getInstance();
			 
			 //Checking if the Presentvqc1 is not equal to null, if it's null then it uses present date and gets the next three consecutive dates
			 if(Presentvqc1!=null)
				{
			
					 c.setTime(dateFormat1.parse(Date)); // Now use today date.
					    c.add(Calendar.DATE, ((i+1)*7));
				}
			 //If line 125 is false, then it uses last date from DB and gets the next three consecutive dates
			 else{
			 
			     c.setTime(dateFormat1.parse(LastDate)); // Now use today date.
			    c.add(Calendar.DATE, ((i+1)*7)); 
			    
				}
			 String fg= dateFormat1.format(c.getTime());
		
			   Dbdate +=""+fg+",";
			   
		   }Dbdate = Dbdate.substring(0, Dbdate.length() - 1);
		   
		  
		 //Checking if the Presentvqc1 is not equal to null, adds the present vqc value to the input of the 'R' server
		if(Presentvqc1!=null)
		{
		
			msg=msg+Presentvqc1;
			
			
		}
		//If line 146 is false, then it takes normal input to the 'R' server
		else{
		
			  msg = msg.substring(0, msg.length() - 1);
			  
		}
		
		  try {
			  
			  msg="\""+msg+"\"";
			  
			  //assigning the input(msg) to the url
			URL url = new URL("http://52.38.31.24:8000/msg?msg="+msg);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			//output from 'R' server to the br (BufferedReader)
			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			

			String output="";
			//assigning the br(bufferedreader) to the output (String)
			while ((output = br.readLine()) != null) {
				
			
				pilgrimGraphValue=output;
			 pilgrimGraphValue=output.substring(1, output.length()-1);
			 System.out.println("msg "+pilgrimGraphValue);
			}
		pilgrimGraphValue=	msg.replace("\"", "")+","+pilgrimGraphValue.replace("\"", "");
		
		 pilgrimGraphValue=pilgrimGraphValue+"@"+Dbdate+"@";
		 System.out.println(pilgrimGraphValue+"[]");
//closing the connection
			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }


		  
		  
       return pilgrimGraphValue;
        
	}


}


class DBInteract
{
	
	 int count;
	 static MongoClient dbClient;
	 static MongoDatabase db;
	 static MongoCollection<Document> coll;
	 static String rec="";
	 static HashMap<Integer,String> selectVQC=new HashMap<Integer,String>();
	 /**
	 * @param host
	 * 				this Holds the  Database 'IP'
	 * @param port
	 * 				this Holds the  Database 'Port'	
	 * @param dbname
	 * 				this Holds the  Database Name
	 * @param collName
	 * 				this Holds the  Collection Name in the Database
	 * @param time
	 * 				the particular time selected in VQC
	 * @param vqc
	 * 				this contains either VQC-1 and VQC-2
	 * @param presentvqc1
	 * 				this contains the Present day VQC-1 or VQC-2 value from the Homepage collection
	 * @return
	 * 			selectVQC (HashMap)
	 * @throws Exception
	 */
	 
	 /*
	  * It stores all the values from the FORECAST database to the HashMap, HashMap contains the Number of pilgrims and dates of the History data.
	  */
	public  static HashMap<Integer,String> fetchFromDB(String host,int port, String dbname,String collName,String time,String vqc, String presentvqc1)throws Exception
	 {
		MongoCredential credential = MongoCredential.createCredential("forecastdb", "FORECAST", "forecast".toCharArray());
		dbClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
		// dbClient=new MongoClient(host,port);
		 db= dbClient.getDatabase(dbname);
		 coll=db.getCollection(collName);
		 rec="";
		 
		 selectVQC.clear();
		 
		 //Using MongoCursor to find the particular time
		 MongoCursor<Document> mcr=coll.find(Filters.exists(time)).iterator();
		
		 JSONParser parser=new JSONParser();
		 JSONObject docjson; 
		 if(mcr.hasNext())
		 {
			 Document d=mcr.next();
			
			 System.out.println(d.toJson());
			 docjson=(JSONObject)parser.parse(d.toJson());
			 JSONArray arr=(JSONArray) docjson.get(time);
			 
			 Iterator  itr=arr.iterator();
			 int mapcount=0;
			 while(itr.hasNext())
			 {
				 String jsonkv= ""+itr.next();
				 JSONObject kvpair=(JSONObject) parser.parse(jsonkv);
				 String DbDate = kvpair.get("Date")+"";
				 int pilgrimctVQC1=Integer.parseInt(""+kvpair.get("VQC1_No_of_Pilgrims"));
				 int pilgrimctVQC2=Integer.parseInt(""+kvpair.get("VQC2_No_of_Pilgrims"));
				 int edtVQC1=Integer.parseInt(""+kvpair.get("VQC1_Est_DarshanTime"));
				 int edtVQC2=Integer.parseInt(""+kvpair.get("VQC2_Est_DarshanTime"));
				 
				 if(vqc.equalsIgnoreCase("VQC1"))
				 {
					 selectVQC.put(mapcount, ""+pilgrimctVQC1+":"+DbDate);
				 }
				 else
				 {
					 selectVQC.put(mapcount, ""+pilgrimctVQC2+":"+DbDate);
				 }
				 mapcount++;
			 }
		 }
		 
		 return selectVQC;
	 }
	 public static void closeConnection ()
	 {
		 if(dbClient!=null)
		 {
			 dbClient.close();
		 }
	 }
}
