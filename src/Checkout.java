import java.time.LocalDate;
import java.util.Map;

public class Checkout {
    private Map<String, Tool> toolInventory;

    public Checkout(Map<String, Tool> toolInventory) {
        this.toolInventory = toolInventory;
    }

    public RentalAgreement processCheckout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) throws IllegalArgumentException {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
        }

        Tool tool = toolInventory.get(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Invalid tool code.");
        }

        RentalAgreement agreement = new RentalAgreement(tool, rentalDays, checkoutDate, discountPercent);
        agreement.printAgreement();
        return agreement;
    }
}
