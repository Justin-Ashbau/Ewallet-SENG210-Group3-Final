
public class Wage {
	String source;
	double amount;
	String Month;
	
	
	public  Wage(String source, double amount, String Month){
		this.source = source;
		this.amount = amount;
		this.Month = Month;
		
	}
	//Overrides the class so when it prints the array, it prints actual strings
	@Override
    public String toString() {
        return "Source: " + source + ", Amount: " + amount + ", Month: " + Month;
    }
}
