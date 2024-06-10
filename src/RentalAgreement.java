import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;
import java.util.Set;

public class RentalAgreement {
    private Tool tool;
    private int rentalDays;
    private LocalDate checkoutDate;
    private int discountPercent;
    private LocalDate dueDate;
    private int chargeDays;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;

    public RentalAgreement(Tool tool, int rentalDays, LocalDate checkoutDate, int discountPercent) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.discountPercent = discountPercent;

        this.dueDate = checkoutDate.plusDays(rentalDays);
        this.chargeDays = calculateChargeDays();
        this.preDiscountCharge = this.chargeDays * tool.getDailyCharge();
        this.discountAmount = this.preDiscountCharge * discountPercent / 100.0;
        this.finalCharge = this.preDiscountCharge - this.discountAmount;
    }

    private int calculateChargeDays() {
        int chargeDays = 0;
        Set<LocalDate> holidays = getHolidays();

        for (int i = 1; i <= rentalDays; i++) {
            LocalDate date = checkoutDate.plusDays(i);
            boolean isWeekend = date.getDayOfWeek().getValue() >= 6;
            boolean isHoliday = holidays.contains(date);

            if ((tool.isWeekdayCharge() && !isWeekend && !isHoliday) ||
                (tool.isWeekendCharge() && isWeekend) ||
                (tool.isHolidayCharge() && isHoliday)) {
                chargeDays++;
            }
        }
        return chargeDays;
    }

    private Set<LocalDate> getHolidays() {
        Set<LocalDate> holidays = new HashSet<>();

        // Independence Day
        LocalDate independenceDay = LocalDate.of(checkoutDate.getYear(), 7, 4);
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            holidays.add(independenceDay.minusDays(1)); // Observed on Friday
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            holidays.add(independenceDay.plusDays(1)); // Observed on Monday
        } else {
            holidays.add(independenceDay);
        }

        // Labor Day (first Monday in September)
        LocalDate laborDay = LocalDate.of(checkoutDate.getYear(), 9, 1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        holidays.add(laborDay);

        return holidays;
    }

    public void printAgreement() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        System.out.println("Tool code: " + tool.getToolCode());
        System.out.println("Tool type: " + tool.getToolType());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + checkoutDate.format(formatter));
        System.out.println("Due date: " + dueDate.format(formatter));
        System.out.println("Daily rental charge: $" + String.format("%.2f", tool.getDailyCharge()));
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: $" + String.format("%.2f", preDiscountCharge));
        System.out.println("Discount percent: " + discountPercent + "%");
        System.out.println("Discount amount: $" + String.format("%.2f", discountAmount));
        System.out.println("Final charge: $" + String.format("%.2f", finalCharge));
    }
}
