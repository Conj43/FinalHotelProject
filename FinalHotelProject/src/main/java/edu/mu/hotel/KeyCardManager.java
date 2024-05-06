package edu.mu.hotel;

import java.util.HashMap;


import java.util.Map;

/*
 * class to manage the key card
 */

public class KeyCardManager {
	
	/*
	 * map to keep track of key card status thru customer id
	 */
    private static Map<Integer, Boolean> keyCardStatus = new HashMap<>();

    
    
    /*
     * method to activate key card 
     * @param reservation id to activate card for
     * 
     */
    public static void activateKeyCard(int reservationId) {
        keyCardStatus.put(reservationId, true); //puts into map as true
        System.out.println("\nKey card activated for reservation ID: " + reservationId); //give a confirmation
    }

    
    /*
     * method to deactivate key card 
     * @param reservation id to deactivate card for
     * 
     */
    public static void deactivateKeyCard(int reservationId) {
        keyCardStatus.put(reservationId, false); //puts into map as false
        System.out.println("\nKey card deactivated for reservation ID: " + reservationId); //give confirmation
    }

    
    /*
     * method to check status of key card 
     * @param reservation id to check card status
     * @return true or false corresponding to status of key card
     */
    public static boolean isKeyCardActive(int reservationId) {
    	Reservation reservation = ReservationManager.getInstance().getReservationById(reservationId);
        return reservation.isKeyCardActive();
    }
}
