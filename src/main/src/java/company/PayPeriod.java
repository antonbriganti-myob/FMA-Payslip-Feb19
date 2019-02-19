package company;

import java.time.MonthDay;

public class PayPeriod {
    private MonthDay start;
    private MonthDay end;

    public PayPeriod(MonthDay start, MonthDay end) {
        this.start = start;
        this.end = end;
    }

    String returnPayPeriodAsString(){
        return start.getDayOfMonth() + " " + start.getMonth() + " - " + end.getDayOfMonth() + " " + end.getMonth();
    }
}
