
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;
import javax.swing.*;


public class driver {
	
	
	//button1: //addMonthlyIncome()
	//button2: //testing loadExpenseFile()                C:\Users\skkae\Documents\Expenses.txt
	//button3: //testing loadIncomeFile(String filePath)   C:\Users\skkae\Documents\Income.txt
	//button4: //addExpense()
	//button5: //printExpensebyType()
	//button6: //I made extra buttons for lisa
	//button7:
	
	
	
	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		ExpenseManager test = new ExpenseManager();
		
		 JFrame frame = new JFrame("Seven Pink Buttons");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(600, 300);
	        frame.setLayout(new GridLayout(2, 4, 10, 10)); // 2 rows, 4 columns

	        // BUTTON 1
	        JButton button1 = new JButton("Add Monthly Income");
	        button1.setBackground(new Color(255, 182, 193)); // Pink
	        button1.setOpaque(true);
	        button1.setBorderPainted(false);
	        button1.addActionListener(e -> {
	        	String job = JOptionPane.showInputDialog("What is your job title:"); 
	        	String Month = JOptionPane.showInputDialog("Enter the month:"); 
	        	String monthlyIncome1 = JOptionPane.showInputDialog("Enter your monthly Wage:");
	        	double monthlyIncome = Double.parseDouble(monthlyIncome1.trim());
	        	
	    		
	    		//creating a Wage object
	    		Wage wage = new Wage(job, monthlyIncome, Month);
	    		test.addMonthlyIncome(wage); 
	        });
	        frame.add(button1);

	        // BUTTON 2
	        JButton button2 = new JButton("Add ExpensesFile");
	        button2.setBackground(new Color(255, 182, 193));
	        button2.setOpaque(true);
	        button2.setBorderPainted(false);
	        button2.addActionListener(e -> {
	        	//testing loadExpenseFile()
	    		//System.out.println("Enter file name: ");
	        	String FileName = JOptionPane.showInputDialog("Enter file name:");   		
	    		test.loadExpenseFile(FileName); 
	    		
	        });
	        frame.add(button2);

	        // BUTTON 3
	        JButton button3 = new JButton("Add IncomeFile");
	        button3.setBackground(new Color(255, 182, 193));
	        button3.setOpaque(true);
	        button3.setBorderPainted(false);
	       // button3.addActionListener(e -> JOptionPanel.showMessageDialog)
	        button3.addActionListener(e -> {
	        	//testing loadIncomeFile(String filePath)
	    		//use this filename: C:\Users\skkae\Documents\Income.txt
	        	
	    		String FileName = JOptionPane.showInputDialog("Enter file name: ");
	    		test.loadIncomeFile(FileName);
	    		
	        });
	        frame.add(button3);

	        // BUTTON 4
	        JButton button4 = new JButton("Add Expense");
	        button4.setBackground(new Color(255, 182, 193));
	        button4.setOpaque(true);
	        button4.setBorderPainted(false);
	        button4.addActionListener(e -> {
	    		//test run addExpense()
	        	String type = JOptionPane.showInputDialog("What is the Expense type:"); 
	        	String amount1 = JOptionPane.showInputDialog("Enter the amount:"); 
	        	double amount = Double.parseDouble(amount1.trim());
	        	String date1 = JOptionPane.showInputDialog("Enter a date (YYYY-MM-DD):");
	        	LocalDate date = LocalDate.parse(date1);
	        	
	    	    
	    		//creating a Wage object
	    		Expense expense = new Expense(type, amount, date);
	    		test.addExpense(expense);
	    		
	    	
	        
	        });
	        frame.add(button4);

	        // BUTTON 5
	        JButton button5 = new JButton("Print Expenses");
	        button5.setBackground(new Color(255, 182, 193));
	        button5.setOpaque(true);
	        button5.setBorderPainted(false);
	        button5.addActionListener(e -> {
	        test.PrintExpensebyType();
	        });
	        frame.add(button5);

	        // BUTTON 6
	        JButton button6 = new JButton("Button 6");
	        button6.setBackground(new Color(255, 182, 193));
	        button6.setOpaque(true);
	        button6.setBorderPainted(false);
	        frame.add(button6);

	        // BUTTON 7
	        JButton button7 = new JButton("Button 7");
	        button7.setBackground(new Color(255, 182, 193));
	        button7.setOpaque(true);
	        button7.setBorderPainted(false);
	        frame.add(button7);

	        // Add empty label to fill last cell (2x4 = 8 total spots)
	        frame.add(new JLabel());

	        frame.setVisible(true);
		
		
		
		
	
		
		
		
		
	
		
		
	
		
		
		
		
		
		
		
		
		
	
		
		

		
		
	
	
	
	}
}
