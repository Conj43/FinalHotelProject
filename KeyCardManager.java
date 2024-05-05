public class KeyCardManager {
    private Map<Integer, Boolean> keyCardStatus = new HashMap<>(); // Maps reservation ID to key card status

    public void activateKeyCard(int reservationId) {
        keyCardStatus.put(reservationId, true);
        System.out.println("Key card activated for reservation ID: " + reservationId);
    }

    public void deactivateKeyCard(int reservationId) {
        keyCardStatus.put(reservationId, false);
        System.out.println("Key card deactivated for reservation ID: " + reservationId);
    }

    public boolean isKeyCardActive(int reservationId) {
        return keyCardStatus.getOrDefault(reservationId, false);
    }
}