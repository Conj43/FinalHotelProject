public class SeasonalPricingStrategy implements PricingStrategy {
    public double calculatePrice(RoomType roomType, LocalDate date) {
        if (isPeakSeason(date)) {
            return roomType.getBasePrice() * 1.2;  // 20% increase during peak season
        }
        return roomType.getBasePrice();
    }

    private boolean isPeakSeason(LocalDate date) {
        // Example: Summer months and year-end holidays
        return date.getMonth() == Month.JUNE || date.getMonth() == Month.JULY
                || date.getMonth() == Month.DECEMBER;
    }
}

public class AdvanceBookingPricingStrategy implements PricingStrategy {
    public double calculatePrice(RoomType roomType, LocalDate date) {
        long daysInAdvance = ChronoUnit.DAYS.between(LocalDate.now(), date);
        if (daysInAdvance > 60) {
            return roomType.getBasePrice() * 0.9;  // 10% discount for early bookings
        }
        return roomType.getBasePrice();
    }
}