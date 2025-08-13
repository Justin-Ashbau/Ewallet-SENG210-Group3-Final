import java.time.LocalDate;

//adding a comment for testing - 
public class Expense {
	String source;
	double amount;
	int yearlyfrequency; //1 for 1 time or once a year, 12 for monthly or or 24 for biweekly
	
	public Expense(String type, double amount, int freq) {
        this.source = type;
        this.amount = amount;
        this.yearlyfrequency = freq;
    }
	@Override
    public String toString() {
        return   source + " , $" +  amount + " , " + yearlyfrequency;
    }
	public String getType() {
	    return source;
	}
	public double getAmount() {
		return amount;
	}
	public int getYearlyFrequency() {
		return yearlyfrequency;
	}

}
