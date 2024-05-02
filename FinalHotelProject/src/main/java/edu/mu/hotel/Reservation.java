package edu.mu.hotel;

import java.time.LocalDate;

import edu.mu.hotel.rooms.RoomType;


public class Reservation {
    private  int reservationId;
    private  int customerId;
    private  String checkInDate;
    private  String checkOutDate;
    private RoomType room;
    private boolean isActive; 

    // Static list to hold all reservations
    

   public Reservation() {
	
	   
   }





	public Reservation(int customerId, String checkInDate, String checkOutDate, RoomType room) { 
        this.customerId = customerId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.setRoom(room);
        this.isActive = true;
        
        
    }


    
    

    public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}





	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}





	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}





	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}





	//Getters and Setters
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





	@Override
	public String toString() {
		return "\n\nReservation \n[reservationId = " + reservationId + ", \ncustomerId = " + customerId + ", \ncheckInDate = "
				+ checkInDate + ", \ncheckOutDate = " + checkOutDate + ", \nroom = " + room.toString() + ", \nisActive = " + isActive + "]";
	}
    
	
    
  

   
}

