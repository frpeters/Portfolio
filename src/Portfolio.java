import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import static java.time.temporal.ChronoUnit.DAYS;

public class Portfolio {

    ArrayList<Stock> stocks;

    public Portfolio() {
    }

    public double profit(LocalDate begin, LocalDate end) throws Exception {
        if (end.isBefore(begin)) {
            throw new Exception("Insert valid date range");
        }
        double beginningValue = 0.0;
        double endValue = 0.0;
        for (Stock st : stocks) {
            beginningValue += st.price(begin);
            endValue += st.price(end);
        }
        double profit = endValue - beginningValue;

        double overallReturn = profit/beginningValue;
        long daysBetween = calcWeekDays(begin, end);
        int daysInYear = 252; // working days

        double annualReturn = Math.pow(1 + overallReturn, 1/(daysBetween/daysInYear))-1;
        return annualReturn;
    }

    public static long calcWeekDays(final LocalDate start, final LocalDate end) {
        final DayOfWeek startW = start.getDayOfWeek();
        final DayOfWeek endW = end.getDayOfWeek();

        final long days = DAYS.between(start, end);
        final long daysWithoutWeekends = days - 2 * ((days + startW.getValue())/7);

        //adjust for starting and ending on a Sunday:
        return daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);
    }
}
