package com.tools.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class MongoConnections {
	
	 private final int numberOfConnection = 10; 
	 private List<MongoClient> listOfConnection = new ArrayList(); 
	 
	 public void createConnection() {
		 for (int i = 0; i < numberOfConnection; i++) {
			 MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
			 listOfConnection.add(mongoClient) ; 
		}
	 }
	 
	 public MongoClient getConnection() {
		 if(listOfConnection.size() > 0 ) {
			 Iterator iter = listOfConnection.iterator();
			 MongoClient client =  (MongoClient) iter.next() ;
			 listOfConnection.remove(client);;
			 return client ; 
		 }else {
			 while(listOfConnection.size() == 0) {
				 System.out.println("Waiting for connection");
			 }
			 Iterator iter = listOfConnection.iterator();
			 MongoClient client =  (MongoClient) iter.next() ;
			 listOfConnection.remove(client);;
			 return client ; 
		 }
	}
	 
	 public void releaseConnection(MongoClient mongoClient) {
		 listOfConnection.add(mongoClient);
	 }
}
