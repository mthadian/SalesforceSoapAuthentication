package testSoap;


import java.util.Scanner;

import org.apache.poi.ss.usermodel.Workbook;

import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Account;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
 




public class SF 
{
	//static final String USERNAME = "pmuthama@blueconsulting.co.ke";
	//static final String PASSWORD = "salesforce2020";
	//static EnterpriseConnection connection;
	
	
	static EnterpriseConnection connection;
	
	
	
	public static void main(String[] args)
	{
		
		Scanner scan=new Scanner(System.in);
		
		System.out.println("USERNAME: ");
		String USERNAME = scan.nextLine();
		
		System.out.println("PASSWORD: ");
		String PASSWORD=scan.nextLine();
		

		 
		
		   
		
		ConnectorConfig config = new ConnectorConfig();
		config.setUsername(USERNAME);
		config.setPassword(PASSWORD);
		
		try 
		{
			connection = Connector.newConnection(config);
			// display some current setting
			System.out.println("Service EndPoint:"+config.getServiceEndpoint());
			System.out.println("Username: "+config.getUsername());

		
			
			System.out.println("CHOOSE A METHOD");
			System.out.println("1: Get All Accounts");
			System.out.println("2: Get All Contacts");
			
			
			int choice=scan.nextInt();
			switch(choice)
			{
			
			case 1:
			
				queryAccounts();
				break;
			
			case 2:
			
				queryContacts();
				break;
			
			default:
			
				System.out.println("INVALID OPTION!!");
			}		
			
			
		}
		catch (ConnectionException e1)
			{
			System.out.println("ROSES ARE RED,VIOLETS ARE BLUE,INVALID LOGIN CREDENTIALS");
			e1.printStackTrace();
		
			
			}
		scan.close();
		
	}
	
	//read Excel
	public static void readExcel()
	{
		
	}
	//get All Accounts
	
	public static void queryAccounts() 
	{
		   QueryResult qResult = null;
		   try
		   {
		      String soqlQuery = "SELECT Name FROM Account";
		      qResult = connection.query(soqlQuery);
		      boolean done = false;
		      if (qResult.getSize() > 0) 
		      {
		         System.out.println("Total Records: "
		            + qResult.getSize() + " Account records.");
		         while (!done) 
		         {
		            SObject[] records = qResult.getRecords();
		            for (int i = 0; i < records.length; ++i) 
		            {
		               Account acc = (Account) records[i];
		   
		               String AccName = acc.getName();		               
		               if (AccName == null) 
		               {
		                  System.out.println(" " + (i + 1) + ": " + AccName + " : " );
		               } 
		               else 
		               {
		                  System.out.println(" " + (i + 1) + ": " + AccName + " " );
		               }
		            }
		            if (qResult.isDone()) 
		            {
		               done = true;
		            } 
		            else
		            {
		               qResult = connection.queryMore(qResult.getQueryLocator());
		            }
		         }
		      } 
		      else 
		      {
		         System.out.println("No records found.");
		      }
		      System.out.println("\nQuery succesfully executed.");
		   } 
		   catch (ConnectionException ce)
		   {
		      ce.printStackTrace();
		      System.out.println("Could not Query any Accounts");
		   }
		}

	
	
	//get all contacts
	public static void queryContacts() 
	{
		   QueryResult qResult = null;
		   try
		   {
		      String soqlQuery = "SELECT FirstName, LastName,Email FROM Contact";
		      qResult = connection.query(soqlQuery);
		      boolean done = false;
		      if (qResult.getSize() > 0) 
		      {
		         System.out.println("Logged-in user can see a total of "
		            + qResult.getSize() + " contact records.");
		         while (!done) 
		         {
		            SObject[] records = qResult.getRecords();
		            for (int i = 0; i < records.length; ++i) 
		            {
		               Contact con = (Contact) records[i];
		   
		               String fName = con.getFirstName();
		               String lName = con.getLastName();
		               String email=con.getEmail();
		               if (fName == null) 
		               {
		                  System.out.println(" " + (i + 1) + ": " + lName + " : " +email);
		               } 
		               else 
		               {
		                  System.out.println(" " + (i + 1) + ": " + fName + " " + lName + " : " +email);
		               }
		            }
		            if (qResult.isDone()) 
		            {
		               done = true;
		            } 
		            else
		            {
		               qResult = connection.queryMore(qResult.getQueryLocator());
		            }
		         }
		      } 
		      else 
		      {
		         System.out.println("No records found.");
		      }
		      System.out.println("\nQuery succesfully executed.");
		   } 
		   catch (ConnectionException ce)
		   {
		      ce.printStackTrace();
		   }
		}
	



}
