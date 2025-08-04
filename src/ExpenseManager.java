import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.LocalDate;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.*;


public class ExpenseManager implements Expenser{
	public User userAtHand;
	
	public ExpenseManager (User user) {
		userAtHand = user;
	}
	
	@Override
	public boolean loadExpenseFile(String filePath) {
		//Building this code to read a file with three parts on each line:
		// item, price, date YYYY-MM-DD (With commas required)
		
		try{
		File file = new File(filePath);
		Scanner scnr = new Scanner(file);

		
		while(scnr.hasNextLine()) {	
			String Line = scnr.nextLine();
			String [] parts = Line.split(",");
			
			if(parts.length == 3) {
				String type = parts[0];
	            double amount = Double.parseDouble(parts[1]);
	            LocalDate date = LocalDate.parse(parts[2]);

	            Expense e = new Expense(type, amount, date);
	            userAtHand.addExpense(e);
			}
		}
		 scnr.close();
		 Component frame = null;
			JOptionPane.showMessageDialog(frame, "Loaded " + userAtHand.getSpendingSize() + " expenses!");
		 System.out.println("Loaded " + userAtHand.getSpendingSize() + " expenses!");
		  //System.out.println("Loaded " + expenses.size() + " expenses!");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found: " + e.getMessage());
			return false;
		}
		
		
		return true;
	}

	@Override
	public void addExpense(Expense Ex) {
		//this method works: someone enters in a Expense object, () and it gets stored into the arrayList expenses
					
		//storing the wage object in the array
		userAtHand.addExpense(Ex);
		System.out.println(userAtHand.getSpending());
		 Component frame = null;
			JOptionPane.showMessageDialog(frame, userAtHand.getSpending());
	}

	@Override
	public void addMonthlyIncome(Wage W) {
		//this method works: someone enters in a wage object, (job title, month, salary) and it gets stored into the arrayList wages
		//wages holds objects of Wage 
		
		
		
		//storing the wage object in the array
		userAtHand.addIncome(W);
		Component frame = null;
		JOptionPane.showMessageDialog(frame, userAtHand.getIncome());
		System.out.println(userAtHand.getIncome());
		
		
	}

	@Override
	public void PrintFullreport() {
		System.out.println("<<Full Report>>");
    	float totalExpense = 0;
    	float totalIncome = 0; 
    	for (Expense s : userAtHand.getSpending()) {
    		totalExpense += s.amount * s.yearlyfrequency; // calculate total expense based on frequency
    	}
    	for (Wage w : userAtHand.getIncome()) {
    		totalIncome += w.amount;
    	}
    	
    	PrintExpensereport();
    	System.out.println("Total yearly expenses: $" + totalExpense);
    	
    	PrintIncomereport();
    	for (String m : new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}) {
    		float monthlyIncome = 0;
			for (Wage w : userAtHand.getIncome()) {
				if (w.Month.equals(m)) {
					monthlyIncome += w.amount;
				}
			}
			
			if (monthlyIncome != 0) { //only print if there is income(s) for the month
				System.out.println("Total income for " + m + ": $" + monthlyIncome);
			}
    	}
    	
    	System.out.println("Total yearly income: $" + totalIncome);
    	
    	double totalSavings = (totalIncome - totalExpense);
    	if (totalSavings >= 0) {
    		System.out.println("Total savings: $" + totalSavings);
    	} else {
    		System.out.println("Total new debt: $" + totalSavings);
    	}
	}

	@Override
	public void PrintExpensereport() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrintIncomereport() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrintIncomereportbyTpe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrintExpensebyType() {
		
		 Component frame = null;
		 String search = JOptionPane.showInputDialog("Enter the type of Expense you want to see:");
		
		
		String find[];
		//create arraylist to store all matches and then print them out
		ArrayList<Expense> typeTracker = new ArrayList<Expense>();
		
		for(Expense word : userAtHand.getSpending()) {
			//System.out.println("Checking: [" + word.getType() + "] vs [" + search + "]");
			if(word.getType().trim().equalsIgnoreCase(search)) {
				typeTracker.add(word);					
			}
		}
		System.out.print(typeTracker);
		JOptionPane.showMessageDialog(frame, typeTracker);
	}

	@Override
	public void exportReport(String reportTitle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Currency convertForeignCurrency(Currency C, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean loadIncomeFile(String filePath) {
		//source= job title
		//amount = income amount
		//Month
		//ArrayList <Wage> income = new ArrayList<>();

		//using arraylist wage
		//stored in an private arraylist in class user
		try{
			File file = new File(filePath);
			Scanner scnr = new Scanner(file);

			
			while(scnr.hasNextLine()) {	
				String Line = scnr.nextLine();
				String [] parts = Line.split(",");
				
				if(parts.length == 3) {
					String source = parts[0];
		            double amount = Double.parseDouble(parts[1]);
		            String Month = parts[2];

		            Wage e = new Wage(source, amount, Month);
		            userAtHand.addIncome(e);
				}
			}
			 scnr.close();
			  System.out.println("Loaded " + userAtHand.getIncomeSize() + " Income!");
			  Component frame = null;
				JOptionPane.showMessageDialog(frame, "Loaded " + userAtHand.getIncomeSize() + " rows of Income!");
			}
			catch(FileNotFoundException e){
				System.out.println("File not found: " + e.getMessage());
				return false;
			} 
		return true;
	}
	@Override
	public int whenCanIBuy(String itemname, double price) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateMonthlySavings() {
		float monthlyExpense = 0;
    	float monthlyIncome = 0; 
    	for (Expense s : userAtHand.getSpending()) {
    		if (s.yearlyfrequency >= 12) { // only consider monthly or biweekly expenses
    			monthlyExpense += s.amount * (s.yearlyfrequency / 12); // calculate total expense based on frequency
    		}
    	}
    	String curMonth = userAtHand.getIncome().get(userAtHand.getIncome().size() - 1).Month;
    	for (Wage w : userAtHand.getIncome()) {
    		if (w.Month.equals(curMonth)) { //if the income is added during the latest possible month
    			monthlyIncome += w.amount;
    		}
    	}
    	
    	userAtHand.setMonthlySavings(monthlyIncome - monthlyExpense);
	}
}
