package edu.mu.hotel;

import java.util.HashMap;
import java.util.Map;

public class KeyCardManager {
    private static Map<Integer, Boolean> keyCardStatus = new HashMap<>(); // Maps reservation ID to key card status

    public static void activateKeyCard(int reservationId) {
        keyCardStatus.put(reservationId, true);
        System.out.println("\nKey card activated for reservation ID: " + reservationId);
    }

    public static void deactivateKeyCard(int reservationId) {
        keyCardStatus.put(reservationId, false);
        System.out.println("\nKey card deactivated for reservation ID: " + reservationId);
    }

    public boolean isKeyCardActive(int reservationId) {
    	Reservation reservation = ReservationManager.getInstance().getReservationById(reservationId);
        return reservation.isKeyCardActive();
    }
}
