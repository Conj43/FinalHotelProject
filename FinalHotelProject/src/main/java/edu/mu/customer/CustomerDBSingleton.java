package edu.mu.customer;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class CustomerDBSingleton {
	
	//customer database using singleton design patter
	
	private Map<Integer, Customer> customers; 
	private Gson gson; //use gson to store data to json file
	private final String filePath = "data/CustomerDatabase.json";
	private static CustomerDBSingleton instance = null; //part of singleton design patters
	private int lastCustomerId;


	
	public static CustomerDBSingleton getInstance() { //only way to access customer db, and ensures only one at a time
		if(instance == null) { //if instance isn't being used, create a new one 
			instance = new CustomerDBSingleton();
		}
		return instance; //return the instance
	}
	
	private CustomerDBSingleton() { //private constructor so it cannot be accessed from outside this class
        this.customers = new HashMap<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        loadDatabase(); //load database into hash map
    }
	
	public void loadDatabase() {
		try(FileReader reader = new FileReader(filePath)){ //read file using file path
			Customer[] array = gson.fromJson(reader, Customer[].class); //reads json into Customer array
			if(array != null) {
			for(Customer customer: array) {
				customers.put(customer.getCustomerID(), customer); //puts each customer into has map
				updateLastCustomerId(customer.getCustomerID());
			}
			}
		}
		catch(IOException e) {
			System.err.println("Error loading customer database: " + e.getMessage()); //on error, we print out the error
		}
	}
	
	//method to add customer to database
	 public void addCustomer(Customer customer) {
		 int newCustomerId = generateCustomerId();
			customer.setCustomerID(newCustomerId); // Assign the new customer ID
	        customers.put(customer.getCustomerID(), customer); //adds it to hasmap
	        saveDatabase(); //save changes
	    }

	    public Customer getCustomer(int customerId) {
	        return customers.get(customerId); //return Customer with given ID
	    }
	    
	    
	   public void saveDatabase() { //saves database back to json file
	    	try(FileWriter writer = new FileWriter(filePath)){
	    		gson.toJson(customers.values().toArray(new Customer[0]), writer); //writes to file
	    	}
	    	catch(IOException e) {
	    		System.err.println("Error saving customer database: " + e.getMessage()); //print if there are any errors
	    	}
	    }
	   
	   
	   
	   private void updateLastCustomerId(int customerId) {
			if (customerId > lastCustomerId) {
				lastCustomerId = customerId;
			}
		}

		// Generate a new customer ID
		private int generateCustomerId() {
			return ++lastCustomerId;
		}
	
	
	
}
