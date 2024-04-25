package edu.mu.test;

import edu.mu.customer.Customer;
import edu.mu.customer.CustomerDBSingleton;

public class Main {
	
	

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {


		
		CustomerDBSingleton customerDB = CustomerDBSingleton.getInstance();


        Customer retrievedCustomer = customerDB.getCustomer(5);
        System.out.println(retrievedCustomer.toString());


        customerDB.saveDatabase();

	}
}



