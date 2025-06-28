import java.time.LocalDate;

//adding a comment for testing - 
public class Expense {
	String source;
	double amount;
	int yearlyfrequency; //1 for 1 time or once a year, 12 for monthly or or 24 for biweekly
	
	String type;
	LocalDate date; 
	
	public Expense(String type, double amount, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }
	@Override
    public String toString() {
        return   type + " , " +  amount + " , " + date;
    }

}
