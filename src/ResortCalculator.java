public class ResortCalculator {
    // Các hằng số
    private static final long BASE_PRICE_PER_NIGHT = 1_000_000; // giá cơ bản
    private static final long EXTRA_GUEST_FEE = 200_000; // 200,000 VND mỗi khách/đêm
    private static final double DISCOUNT_5_NIGHTS = 0.15; // giảm giá 15%
    private static final double DISCOUNT_10_NIGHTS = 0.25; // giảm giá 25%
    private static final double TAX_RATE = 0.10;   // VAT 10%
    private static final double SERVICE_FEE = 0.05; // phí dịch vụ 5%

    /**
     * Tính tổng tiền phải trả cho resort
     * @param nights số đêm ở (>= 1)
     * @param guests số khách (>= 1)
     * @return tổng tiền phải trả (VND)
     */
    public static long calculateTotalCost(int nights, int guests) {
        if (nights <= 0 || guests <= 0) {
            throw new IllegalArgumentException("Số đêm và số khách phải lớn hơn hoặc bằng 1");
        }

        if (nights > 15) {
            throw new IllegalArgumentException("Phòng chỉ được thuê tối đa 15 đêm và chứa tối đa 7 khách");
        }

        // Giá cơ bản
        long total = BASE_PRICE_PER_NIGHT * nights;

        // Phụ thu khách
        if (guests > 2) {
            int extraGuests = guests - 2;
            long surcharge = EXTRA_GUEST_FEE * extraGuests * nights;
            total += surcharge;
        }

        // Giảm giá
        if (nights >= 10) {
            total += total * DISCOUNT_10_NIGHTS;
        } else if (nights >= 5) {
            total -= total * DISCOUNT_5_NIGHTS;
        }

        // Thuế VAT
        total += total * TAX_RATE;

        // Phí dịch vụ
        total += total * SERVICE_FEE;

        return total;
    }
}
