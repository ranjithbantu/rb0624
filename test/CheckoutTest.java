import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutTest {
    private Map<String, Tool> setupToolInventory() {
        Map<String, Tool> toolInventory = new HashMap<>();
        toolInventory.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true));
        toolInventory.put("LADW", new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false));
        toolInventory.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false));
        toolInventory.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false));
        return toolInventory;
    }

    @Test
    public void testValidCheckout() {
        Map<String, Tool> toolInventory = setupToolInventory();
        Checkout checkout = new Checkout(toolInventory);

        // Test case 1
        System.out.println("----------Test Case 1 Start----------");
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> checkout.processCheckout("JAKR", 5, 101, LocalDate.of(2015, 9, 3)));
        System.out.println("Test Case 1 Exception Message: " + exception1.getMessage());
        System.out.println("----------Test Case 1 End----------");

        // Test case 2
        System.out.println("----------Test Case 2 Start----------");
        assertDoesNotThrow(() -> checkout.processCheckout("LADW", 3, 10, LocalDate.of(2020, 7, 2)));
        System.out.println("----------Test Case 2 End----------");

        // Test case 3
        System.out.println("----------Test Case 3 Start----------");
        assertDoesNotThrow(() -> checkout.processCheckout("CHNS", 5, 25, LocalDate.of(2015, 7, 2)));
        System.out.println("----------Test Case 3 End----------");

        // Test case 4
        System.out.println("----------Test Case 4 Start----------");
        assertDoesNotThrow(() -> checkout.processCheckout("JAKD", 6, 0, LocalDate.of(2015, 9, 3)));
        System.out.println("----------Test Case 4 End----------");

        // Test case 5
        System.out.println("----------Test Case 5 Start----------");
        assertDoesNotThrow(() -> checkout.processCheckout("JAKR", 9, 0, LocalDate.of(2015, 7, 2)));
        System.out.println("----------Test Case 5 End----------");

        // Test case 6
        System.out.println("----------Test Case 6 Start----------");
        assertDoesNotThrow(() -> checkout.processCheckout("JAKR", 4, 50, LocalDate.of(2020, 7, 2)));
        System.out.println("----------Test Case 6 End----------");

        // Test case 7
        System.out.println("----------Test Case 7 Start----------");
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> checkout.processCheckout("JAKR", 0, 90, LocalDate.of(2015, 9, 3)));
        System.out.println("Test Case 7 Exception Message: " + exception2.getMessage());
        System.out.println("----------Test Case 7 End----------");
    }
}
