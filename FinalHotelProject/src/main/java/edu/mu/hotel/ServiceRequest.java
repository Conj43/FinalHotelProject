package edu.mu.hotel;



/*
 * class to keep track of service requests that customers can make when booking rooms
 */
public class ServiceRequest {
	/*
	 * attributes for service request
	 */
    private String requestType;
    private int quantity;
    private String notes;  
    
    /*
     * default constructor
     */
    public ServiceRequest() {
    	
    }
    
    
    /*
     * to string method for service requests
     * @return string with all info for service request
     */
    @Override
	public String toString() {
		return "ServiceRequest [requestType=" + requestType + ", quantity=" + quantity + ", notes=" + notes + "]";
	}

    
    /*
     * paramaterized constrctor
     */
	public ServiceRequest(String requestType, int quantity, String notes) {
        this.requestType = requestType;
        this.quantity = quantity;
        this.notes = notes;
    }

	
	/*
	 * getters and setters
	 */
    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    
   
}
