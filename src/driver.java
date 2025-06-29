
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;
import javax.swing.*;


public class driver {
	
	// go back and add how many income and expensesto gui
	//implement the gui later in EWalletApp Class
	//button1: //addMonthlyIncome()
	//button2: //testing loadExpenseFile()                C:\Users\skkae\Documents\Expenses.txt
	//button3: //testing loadIncomeFile(String filePath)   C:\Users\skkae\Documents\Income.txt
	//button4:
	//button5:
	//button6:
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
	    		JOptionPane.showMessageDialog(frame, "Loaded Expenses Successfully!");
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
	    		JOptionPane.showMessageDialog(frame, "Loaded Income Successfully!");
	        });
	        frame.add(button3);

	        // BUTTON 4
	        JButton button4 = new JButton("Button 4");
	        button4.setBackground(new Color(255, 182, 193));
	        button4.setOpaque(true);
	        button4.setBorderPainted(false);
	        button4.addActionListener(e -> frame.setTitle("Button 4 was clicked"));
	        frame.add(button4);

	        // BUTTON 5
	        JButton button5 = new JButton("Button 5");
	        button5.setBackground(new Color(255, 182, 193));
	        button5.setOpaque(true);
	        button5.setBorderPainted(false);
	        button5.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Hi from Button 5"));
	        frame.add(button5);

	        // BUTTON 6
	        JButton button6 = new JButton("Button 6");
	        button6.setBackground(new Color(255, 182, 193));
	        button6.setOpaque(true);
	        button6.setBorderPainted(false);
	        button6.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Shutting down..."));
	        frame.add(button6);

	        // BUTTON 7
	        JButton button7 = new JButton("Button 7");
	        button7.setBackground(new Color(255, 182, 193));
	        button7.setOpaque(true);
	        button7.setBorderPainted(false);
	        button7.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Lucky 7!"));
	        frame.add(button7);

	        // Add empty label to fill last cell (2x4 = 8 total spots)
	        frame.add(new JLabel());

	        frame.setVisible(true);
		
		
		
		
	
		
		
		
		
	
		
		
		/*
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
		} */
		
		
		
		
		
		
		
		//test.PrintExpensebyType();
		
		
	
		
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
