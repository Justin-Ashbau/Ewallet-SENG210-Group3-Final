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
	Scanner scnr = new Scanner(System.in);
	static String Print;

	
	//expenses holds objects of Expense
	static ArrayList <Expense> expenses = new ArrayList<>();
	
	//Establishes that wages stores Wage objects
	ArrayList <Wage> wages = new ArrayList<>();
	
	ArrayList <Wage> income = new ArrayList<>();
	
	
	
	
	//expenses holds objects of Expense
	ArrayList <Expense> expenses = new ArrayList<>();
	
	//Establishes that wages stores Wage objects
	ArrayList <Wage> wages = new ArrayList<>();
	
	
	
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
		            expenses.add(e);
				}
			}
			 scnr.close();
			 Component frame = null;
				JOptionPane.showMessageDialog(frame, "Loaded " + expenses.size() + " expenses!");
			 System.out.println("Loaded " + expenses.size() + " expenses!");
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
			expenses.add(Ex);
			System.out.println(expenses);
			 Component frame = null;
				JOptionPane.showMessageDialog(frame, expenses);
		}

		@Override
		public void addMonthlyIncome(Wage W) {
			//this method works: someone enters in a wage object, (job title, month, salary) and it gets stored into the arrayList wages
			//wages holds objects of Wage 
			
			
			
			//storing the wage object in the array
			wages.add(W);
			Component frame = null;
			JOptionPane.showMessageDialog(frame, wages);
			System.out.println(wages);
			
			
		}

		@Override
		public void PrintFullreport() {
			// TODO Auto-generated method stub
			
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
			
			for(Expense word : expenses) {
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
			            income.add(e);
					}
				}
				 scnr.close();
				  System.out.println("Loaded " + income.size() + " Income!");
				  Component frame = null;
					JOptionPane.showMessageDialog(frame, "Loaded " + income.size() + " rows of Income!");
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
			// TODO Auto-generated method stub
			
		}
}
