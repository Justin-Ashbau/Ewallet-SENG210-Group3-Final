import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class driver {
	
	
	
	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		ExpenseManager test = new ExpenseManager();
		//testing loadExpenseFile()
		//System.out.println("Enter file name: ");
		//String FileName = scnr.nextLine();
		 
		//test.loadExpenseFile(FileName); 
		
	
		//testing loadIncomeFile(String filePath)
		//use this filename: C:\Users\skkae\Documents\Income.txt
		System.out.println("Enter file name: ");
		String FileName = scnr.nextLine();
		test.loadIncomeFile(FileName);
		
		
		
		//test run addMonthlyIncome()
		/*System.out.println("What is your job title:");
		String job = scnr.nextLine();
		System.out.println("Enter the month:");
		String Month = scnr.next();
		System.out.println("Enter your monthly wage:");
		double monthlyIncome = scnr.nextDouble();
		
		//creating a Wage object
		Wage wage = new Wage(job, monthlyIncome, Month);
		test.addMonthlyIncome(wage); */
		
		
		
		//test run addExpense()
		boolean runCount = true;
		while(runCount) {
		System.out.println("What is the Expense type:");
		String type = scnr.nextLine().trim();
		
		//scnr.nextLine(); 
		System.out.println("Enter the amount:");
		double amount = scnr.nextDouble();
		scnr.nextLine(); 
		
		System.out.println("Enter a date (YYYY-MM-DD):");
	    String input = scnr.nextLine().trim();
	    LocalDate date = LocalDate.parse(input);
	    
		//creating a Wage object
		Expense expense = new Expense(type, amount, date);
		test.addExpense(expense);
		
		System.out.println("Would you like to add another expense? (yes or no)");
		String answer = scnr.nextLine().trim();
			    
		if (answer.equalsIgnoreCase("no")) {
			runCount = false;
			break;
		}
		}
		
		
		
		
		
		
		
		test.PrintExpensebyType();
		
		
	
		
		//test run addMonthlyIncome()
		/*System.out.println("What is your job title:");
		String job = scnr.nextLine();
		System.out.println("Enter the month:");
		String Month = scnr.next();
		System.out.println("Enter your monthly wage:");
		double monthlyIncome = scnr.nextDouble();
		
		//creating a Wage object
		Wage wage = new Wage(job, monthlyIncome, Month);
		test.addMonthlyIncome(wage); */
		
		

		
		
	
	
	
	}
}
