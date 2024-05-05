public class PricingManager {
    private static Map<String, PricingStrategy> strategies = new HashMap<>();

    static {
        strategies.put("seasonal", new SeasonalPricingStrategy());
        strategies.put("advanceBooking", new AdvanceBookingPricingStrategy());
    }

    public static double calculatePrice(RoomType roomType, LocalDate date) {
        double price = roomType.getBasePrice();
        for (PricingStrategy strategy : strategies.values()) {
            price = strategy.calculatePrice(roomType, date);
        }
        return price;
    }
}