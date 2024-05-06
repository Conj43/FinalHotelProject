package edu.mu.hotel;

import java.time.LocalDate;
import java.util.List;

import edu.mu.hotel.rooms.RoomType;

/*
 * class to make reservation objects
 */

public class Reservation {
	
	/*
	 * reservation attributes
	 */
    private  int reservationId;
    private  int customerId;
    private  String checkInDate;
    private  String checkOutDate;
    private RoomType room;
    private boolean isActive; 
    private String accessCode;
    private boolean isKeyCardActive;
    private List<ServiceRequest> serviceRequests;


    
   /*
    * default constructor
    */
   public Reservation() {
	
	   
   }

   /*
    * parameterized constructor
    */
	public Reservation(int customerId, String checkInDate, String checkOutDate, RoomType room, List<ServiceRequest> serviceRequests) { 
        this.customerId = customerId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.setRoom(room);
        this.isActive = true;
        this.serviceRequests = serviceRequests;
        this.isKeyCardActive = false;
        this.accessCode = "Guest has not checked in yet.";
    }


    
    /*
     * getters and setters
     */

    public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}





	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}





	public boolean isKeyCardActive() {
		return isKeyCardActive;
	}

	public void setKeyCardActive(boolean isKeyCardActive) {
		this.isKeyCardActive = isKeyCardActive;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public void setServiceRequests(List<ServiceRequest> serviceRequests) {
		this.serviceRequests = serviceRequests;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}





	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}



    public int getReservationId() {
        return reservationId;
    }

    public int getCustomerId() {
        return customerId;
    }

  

    public LocalDate getCheckInDate() {
        return LocalDate.parse(checkInDate);
    }

    public LocalDate getCheckOutDate() {
        return LocalDate.parse(checkOutDate);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }





	public RoomType getRoom() {
		return room;
	}

	
	public String getRoomType() {
		return room.getTypeName();
	}
	
	public String getCheckInDateString() {
		return checkInDate;
	}
	
	public String getCheckOutDateString() {
		return checkOutDate;
	}




	public void setRoom(RoomType room) {
		this.room = room;
	}
	
	public String getAccessCode() {
        return accessCode;
    }
	
	 public void addServiceRequest(ServiceRequest request) {
	        serviceRequests.add(request);
	    }

	    public List<ServiceRequest> getServiceRequests() {
	        return serviceRequests;
	    }
	    
	    
	    /*
	     * to string method
	     * @return string with all attributes of reservation
	     */

		@Override
		public String toString() {
			return "Reservation [reservationId=" + reservationId + ", customerId=" + customerId + ", checkInDate="
					+ checkInDate + ", checkOutDate=" + checkOutDate + ",\nroom=" + room + ", \nisActive=" + isActive
					+ ", accessCode=" + accessCode + ", isKeyCardActive=" + isKeyCardActive + ", serviceRequests="
					+ serviceRequests + "]";
		}





	
    
	
    
  

   
}

