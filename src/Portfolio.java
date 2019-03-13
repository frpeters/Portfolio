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
        double beginingValue = 0.0;
        double endValue = 0.0;
        for (Stock st : stocks) {
            beginingValue += st.price(begin);
            endValue += st.price(end);
        }
        double profit = endValue - beginingValue;

        double overallReturn = profit/beginingValue;
        long daysBetween = DAYS.between(begin, end);
        int daysInYear = Year.of(begin.getYear()).length();

        double annualReturn = Math.pow(1 + overallReturn, 1/(daysBetween/daysInYear))-1;
        return annualReturn;
    }
}
