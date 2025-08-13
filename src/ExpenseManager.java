import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.io.*;
import java.time.LocalDate;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.*;


public class ExpenseManager implements Expenser{
	public User userAtHand;
	
	final static String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	public ExpenseManager (User user) {
		userAtHand = user;
	}
	
	@Override
	public boolean loadExpenseFile(String filePath) {
		//Building this code to read a file with three parts on each line:
		// item, price, yearly frequency (With commas required)
		
		try{
		File file = new File(filePath);
		Scanner scnr = new Scanner(file);

		
		while(scnr.hasNextLine()) {	
			String Line = scnr.nextLine();
			String [] parts = Line.split(",");
			
			if(parts.length == 3) {
				String type = parts[0];
	            double amount = Double.parseDouble(parts[1]);
	            int yearlyfrequency = Integer.parseInt(parts[2]);

	            Expense e = new Expense(type, amount, yearlyfrequency);
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
	public void PrintExpensereport() {
		Component frame = null;
		
		ArrayList<Expense> expenses = userAtHand.getSpending();
		ArrayList<String> report = new ArrayList<String>();
		
		for (int i = 0; i < expenses.size(); i++ ) {
			report.add("$" + expenses.get(i).amount + " from " + expenses.get(i).source + " with a frequency of " + expenses.get(i).yearlyfrequency + " times a year.");
		}
		
		System.out.println(report);
		JOptionPane.showMessageDialog(frame, report);
	}

	@Override
	public void PrintIncomereport() {
		Component frame = null;
		
		ArrayList<Wage> income = userAtHand.getIncome();
		ArrayList<String> report = new ArrayList<String>();
		
		for (int i = 0; i < income.size(); i++ ) {
			report.add("$" + income.get(i).amount + " from " + income.get(i).source + " in the month of " + income.get(i).Month);
		}
		
		System.out.println(report);
		JOptionPane.showMessageDialog(frame, report);
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
		
		String report = "";
		
		report += "<<Full Report>> \n";
    	float totalExpense = 0;
    	float totalIncome = 0; 
    	for (Expense s : userAtHand.getSpending()) {
    		totalExpense += s.amount * s.yearlyfrequency; // calculate total expense based on frequency
    	}
    	for (Wage w : userAtHand.getIncome()) {
    		totalIncome += w.amount;
    	}
    	
    	PrintExpensereport();
    	report += ("Total yearly expenses: $" + totalExpense + "\n");
    	
    	PrintIncomereport();
    	for (String m : MONTHS) {
    		float monthlyIncome = 0;
			for (Wage w : userAtHand.getIncome()) {
				if (w.Month.equals(m)) {
					monthlyIncome += w.amount;
				}
			}

			if (monthlyIncome != 0) { //only print if there is income(s) for the month
				report += ("Total income for " + m + ": $" + monthlyIncome + "\n");
			}
    	}
    	
    	report += ("Total yearly income: $" + totalIncome + "\n");
    	
    	double totalSavings = (totalIncome - totalExpense);
    	if (totalSavings >= 0) {
    		report += ("Total savings: $" + totalSavings + "\n");
    	} else {
    		report += ("Total new debt: $" + totalSavings + "\n");
    	}
    	
    	System.out.println(report);
    	JOptionPane.showMessageDialog(null, report);
	}

	@Override
	public void PrintIncomereportbyTpe() {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter income type: ");
        String type = scanner.nextLine();

        double total = 0;
		int count = 0;
        System.out.println("Type: " + type);

        for (int i = 0; i < userAtHand.getIncome().size(); i++) {
            if (userAtHand.getIncome().get(i).source.equalsIgnoreCase(type)) {
                System.out.println("Amount: $" + userAtHand.getIncome().get(i).amount + " in " + userAtHand.getIncome().get(i).Month);
                total += userAtHand.getIncome().get(i).amount;
		count++;
            }
        }

        System.out.println("Total income for " + type + ": $" + total + " over " + count + " months");
	}

	@Override
	public void PrintExpensebyType() {
		
		Component frame = null;
		String search = JOptionPane.showInputDialog("Enter the type of Expense you want to see:");
		if (search == null) return;
		
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
			
			Component frame = null;
			
			try {
	            File report = new File(reportTitle + ".json");
	            if (report.createNewFile()) {
	                System.out.println(report.getName() + " created.");
	            }
	            else {
	                System.out.println("File already exists");
	                JOptionPane.showMessageDialog(frame, "File already exists.");
	            }
	        
	            FileWriter reportWriter = new FileWriter(reportTitle + ".json");
	      
	            switch (reportTitle.toLowerCase()) {

	                case "expense":

	                    ArrayList<Expense> expenses = userAtHand.getSpending();

	                    for (int i = 0; i < expenses.size(); i++) {
	                        reportWriter.write("$" + expenses.get(i).amount + " from " + expenses.get(i).source + " with a frequency of " + expenses.get(i).yearlyfrequency + " times a year.");
	                    }

	                    break;
	                
	                case "income":

	                    ArrayList<Wage> income = userAtHand.getIncome();

	                    for (int i = 0; i < income.size(); i++) {
	                        reportWriter.write("$" + income.get(i).amount + " from " + income.get(i).source + " in the month of " + income.get(i).Month);
	                    }

	                    break;

	            }

	            reportWriter.close();

	            System.out.println("Report successfully exported.");
	            JOptionPane.showMessageDialog(frame, "Report successfully exported." );

	        }

	        catch (IOException e) {
	            System.out.println("Unexpected error occcured exporting file");
	            e.getStackTrace();
	            JOptionPane.showMessageDialog(frame, "Unexpected error occcured exporting file");
	        }
			
		}

	@Override
	public Currency convertForeignCurrency(Currency C, double amount) {
			Currency result = new Currency(C.name, C.rate);
			
			String output = "Your balance in " + C.name + " from USD: " + amount / C.rate;

	        System.out.println(output);
	        JOptionPane.showMessageDialog(null, output);

	        return result;
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
			
			Component frame = null;
			int waitTime = (int)Math.round(price/userAtHand.monthlysavings);
			
			JOptionPane.showMessageDialog(frame, "You will be able to Purchase " + itemname + " in " + waitTime + " month(s).");
			
			return waitTime;
		}

	@Override
	public void updateMonthlySavings() {
		float monthlyExpense = 0;
    	float monthlyIncome = 0; 
    	
    	if (userAtHand.getSpendingSize() <= 0 || userAtHand.getIncomeSize() <= 0) {
    		System.out.println("there is no spending or income to properly update");
    		return;
    	}
    	
    	for (Expense s : userAtHand.getSpending()) {
    		if (s.yearlyfrequency >= 12) { // only consider monthly or biweekly expenses
    			monthlyExpense += s.amount * (s.yearlyfrequency / 12); // calculate total expense based on frequency
    		}
    	}

    	int greatestIndex = 0;
    	for (int i = 0; i < MONTHS.length; i++) {
    		for (Wage w : userAtHand.getIncome()) {
    			if (MONTHS[i].compareTo(w.Month) == 0 && i > greatestIndex) {
    				System.out.println(w.Month);
    				greatestIndex = i;
    			}
    		}
    	}
    	
    	String curMonth = MONTHS[greatestIndex];
    	
    	for (Wage w : userAtHand.getIncome()) {
    		if (w.Month.equals(curMonth)) { //if the income is added during the latest possible month
    			monthlyIncome += w.amount;
    		}
    	}
    	
    	userAtHand.setMonthlySavings(monthlyIncome - monthlyExpense);
	}
}
