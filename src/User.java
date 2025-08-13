import java.util.ArrayList;

public class User {
	private ArrayList <Currency>currencyRates;
	private ArrayList <Wage>Income = new ArrayList<Wage>();  // user income sources that user can record or view or search by type or month 
	private ArrayList <Expense>Spending = new ArrayList<Expense>(); //user's expenses 
	
	String username;
	String pwd;
	//current total income - total 
	double balance;
	// possible monthly savings, calculated using monthly income (most recent) assuming the data we have is for one year, and monthly and biweekly expenses, here you can assume yearly expenses that are recorded have already been paid. 
	double monthlysavings;	
	//should add constructor(s)
	
	public ArrayList<Wage> getIncome() {
		return Income;
	}
	public void addIncome(Wage income) {
		Income.add(income);
	}
	public ArrayList<Expense> getSpending() {
		return Spending;
	}
	public int getIncomeSize() {
		return Income.size();
	}
	public void addExpense(Expense expense) {
		Spending.add(expense);
	}
	public int getSpendingSize() {
		return Spending.size();
	}
	public double getMonthlySavings(ExpenseManager m) {
		m.updateMonthlySavings();
		return this.monthlysavings;
	}
	public void setMonthlySavings(double savings) {
		this.monthlysavings = savings;
	}
	
	User(String username,String password){
		this.username = username;
		this.pwd = password;
	}
	
}
