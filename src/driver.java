
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import javax.swing.*;

import java.util.ArrayList;

public class driver {
	
	static ArrayList<User> Users = new ArrayList<User>();
	
	//button1: //addMonthlyIncome()
	//button2: //testing loadExpenseFile()                C:\Users\skkae\Documents\Expenses.txt
	//button3: //testing loadIncomeFile(String filePath)   C:\Users\skkae\Documents\Income.txt
	//button4: //addExpense()
	//button5: //printExpensebyType()
	//button6: //I made extra buttons for lisa
	//button7:
	
	static JFrame currentFrame;
	static ExpenseManager manager;
	
	static JLabel monthlySavingsCounter;
	
	public static void main(String[] args) {
		
		Users.add(new User("Bob Jimothy", "mypwd123"));
		Users.add(new User ("a", "a"));
		
		openSignIn();
	}
	
	static User signIn(String usr, String pswd) {
		for (User u : Users) {
			if (u.username.equals(usr) && u.pwd.equals(pswd)) {
				return u;
			}
		}
		
		JOptionPane.showMessageDialog(currentFrame, "Username or password incorrect");;
		return null;
	}
	
	static void openSignIn() {
		currentFrame = new JFrame("Sign in");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		currentFrame.setSize(350, 350);
		currentFrame.setLayout(new BorderLayout());
		
		JTextField userNameField = new JTextField();
		userNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		userNameField.setPreferredSize(new Dimension(100, 50));
		
		JLabel usrLabel = new JLabel("Username");
		
		JPanel usrPanel = new JPanel();
		usrPanel.setLayout(new GridLayout(2, 1));
		usrPanel.add(usrLabel);
		usrPanel.add(userNameField);
		
		JTextField passwordField = new JTextField();
		passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		passwordField.setPreferredSize(new Dimension(100, 50));
		
		JLabel passwordLabel = new JLabel("Password");
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setLayout(new GridLayout(2, 1));
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		
		JPanel entryPanel = new JPanel();
		entryPanel.setLayout(new FlowLayout());
		entryPanel.add(usrPanel);
		entryPanel.add(passwordPanel);
		
		JButton submitButton = new JButton ("Submit");
		submitButton.setOpaque(true);
		submitButton.setPreferredSize(new Dimension(100, 50));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(submitButton);
		
		submitButton.addActionListener(e -> {
			User usrResult = signIn(userNameField.getText(), passwordField.getText());
			if (usrResult != null) {
				manager = new ExpenseManager(usrResult);
				currentFrame.dispose();
				openMainWindow();
			}
		});
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2, 1));
		centerPanel.add(entryPanel);
		centerPanel.add(buttonPanel);
		
		currentFrame.add(centerPanel, BorderLayout.CENTER);
		
		currentFrame.setVisible(true);
	}
	
	static void updateElements(DefaultListModel<String> expenseList, DefaultListModel<String> incomeList, JComboBox<String> expenseTypeList) {
		expenseList.clear();
		incomeList.clear();
		expenseTypeList.removeAllItems();
		for (Expense e : manager.userAtHand.getSpending()) {
			expenseList.addElement(e.toString());
		}
		for (Wage i : manager.userAtHand.getIncome()) {
			incomeList.addElement(i.toString());
		}
		expenseTypeList.addItem("None");
		for (Expense e : manager.userAtHand.getSpending()) {
			Boolean duplicate = false;
			for (int i = 0; i < expenseTypeList.getItemCount(); i++) {
				if (e.getType().equals(expenseTypeList.getItemAt(i))) {
					duplicate = true;
				}
			}
			if (!duplicate) expenseTypeList.addItem(e.getType());
		}
		updateMonthlySavingsUI();
	}
	
	static void updateElements(DefaultListModel<String> expenseList, DefaultListModel<String> incomeList) {
		expenseList.clear();
		incomeList.clear();
		for (Expense e : manager.userAtHand.getSpending()) {
			expenseList.addElement(e.toString());
		}
		for (Wage i : manager.userAtHand.getIncome()) {
			incomeList.addElement(i.toString());
		}
		updateMonthlySavingsUI();
	}
	
	static void updateMonthlySavingsUI() {
		monthlySavingsCounter.setText("Monthly Savings: " + Double.toString(manager.userAtHand.getMonthlySavings(manager)));
	}
	
	static void openMainWindow() {
		currentFrame = new JFrame("Seven Pink Buttons");
        currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currentFrame.setSize(600, 300);
        currentFrame.setLayout(new BorderLayout());
        
        DefaultListModel<String> expenseModel = new DefaultListModel<>();
        DefaultListModel<String> incomeModel = new DefaultListModel<>();

        JList<String> expenseJList = new JList<>(expenseModel);
        JScrollPane expenseBox = new JScrollPane(expenseJList);
        
        JLabel expenseLabel = new JLabel("Expenses:");
        
        JComboBox<String> expenseTypeSelect = new JComboBox<>();
        expenseTypeSelect.addItem("None");
        expenseTypeSelect.addActionListener(e -> {
        	updateElements(expenseModel, incomeModel);
        	if (expenseTypeSelect.getSelectedItem() != "None") {
    			//filter list
    			for (int x = expenseModel.getSize() - 1; x >= 0; x--) {
    				System.out.println(x);
    				if (!expenseModel.get(x).split(",")[0].trim().equalsIgnoreCase((String) expenseTypeSelect.getSelectedItem()))  {
    					expenseModel.remove(x);
    				}
    			}
    		}
        });
        
        JPanel expenseSubTextPanel = new JPanel();
        expenseSubTextPanel.setLayout(new GridLayout(1, 2));
        expenseSubTextPanel.add(expenseLabel);
        expenseSubTextPanel.add(expenseTypeSelect);
        
        JPanel expensePanel = new JPanel();
        expensePanel.setLayout(new GridLayout(2, 1));
        expensePanel.add(expenseSubTextPanel);
        expensePanel.add(expenseBox);
        
        JList<String> incomeJList = new JList<>(incomeModel);
        JScrollPane incomeBox = new JScrollPane(incomeJList);
        
        JLabel incomeLabel = new JLabel("Income:");
        
        JPanel incomePanel = new JPanel();
        incomePanel.setLayout(new GridLayout(2, 1));
        incomePanel.add(incomeLabel);
        incomePanel.add(incomeBox);
        
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(1, 2, 10, 5));
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        listPanel.add(expensePanel);
        listPanel.add(incomePanel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // 2 rows, 4 columns
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
   
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1));
        centerPanel.add(listPanel);
        centerPanel.add(buttonPanel);
        currentFrame.add(centerPanel, BorderLayout.CENTER);

        // BUTTON 1
        JButton button1 = new JButton("Add Monthly Income");
        button1.setBackground(new Color(255, 182, 193)); // Pink
        button1.setOpaque(true);
        button1.setBorderPainted(false);
        button1.addActionListener(e -> {
        	String job = JOptionPane.showInputDialog("What is your job title:"); 
        	if (job == null) return;
        	
        	String Month = JOptionPane.showInputDialog("Enter the month:"); 
        	if (Month == null) return;
        	
        	String monthlyIncome1 = JOptionPane.showInputDialog("Enter your monthly Wage:");
        	if (monthlyIncome1 == null) return;
        	
        	double monthlyIncome = Double.parseDouble(monthlyIncome1.trim());
        	
    		//creating a Wage object
        	manager.addMonthlyIncome(new Wage(job, monthlyIncome, Month)); 
        	updateElements(expenseModel, incomeModel, expenseTypeSelect);
        });
        buttonPanel.add(button1);

        // BUTTON 2
        JButton button2 = new JButton("Add ExpensesFile");
        button2.setBackground(new Color(255, 182, 193));
        button2.setOpaque(true);
        button2.setBorderPainted(false);
        button2.addActionListener(e -> {
        	//testing loadExpenseFile()
    		//System.out.println("Enter file name: ");
        	JFileChooser j = new JFileChooser();
        	
        	int result = j.showOpenDialog(null);
        	
        	if (result == JFileChooser.APPROVE_OPTION) {
        		manager.loadExpenseFile(j.getSelectedFile().getAbsolutePath());
        	}
        	updateElements(expenseModel, incomeModel, expenseTypeSelect);
        });
        buttonPanel.add(button2);

        // BUTTON 3
        JButton button3 = new JButton("Add IncomeFile");
        button3.setBackground(new Color(255, 182, 193));
        button3.setOpaque(true);
        button3.setBorderPainted(false);
       // button3.addActionListener(e -> JOptionPanel.showMessageDialog)
        button3.addActionListener(e -> {
        	//testing loadIncomeFile(String filePath)
    		//use this filename: C:\Users\skkae\Documents\Income.txt
        	
        	JFileChooser j = new JFileChooser();
        	
        	int result = j.showOpenDialog(null);
        	
        	if (result == JFileChooser.APPROVE_OPTION) {
        		manager.loadIncomeFile(j.getSelectedFile().getAbsolutePath());
        	}
        	updateElements(expenseModel, incomeModel, expenseTypeSelect);
        });
        buttonPanel.add(button3);

        // BUTTON 4
        JButton button4 = new JButton("Add Expense");
        button4.setBackground(new Color(255, 182, 193));
        button4.setOpaque(true);
        button4.setBorderPainted(false);
        button4.addActionListener(e -> {
    		//test run addExpense()
        	String type = JOptionPane.showInputDialog("What is the Expense type:"); 
        	if (type == null) return;
        	
        	String amtInput = JOptionPane.showInputDialog("Enter the amount:"); 
        	if (amtInput == null) return;
        	
        	double amount = Double.parseDouble(amtInput.trim());
        	String freqInput = JOptionPane.showInputDialog("Enter a yearly frequency (1 for once per year, 12 for monthly, or 24 for biweekly:");
        	if (freqInput == null) return;
        	
        	Integer yearlyFreq = Integer.parseInt(freqInput);
        	
    	    
    		//creating a Wage object
    		Expense expense = new Expense(type, amount, yearlyFreq);
    		manager.addExpense(expense);
    		updateElements(expenseModel, incomeModel, expenseTypeSelect);
        });
        buttonPanel.add(button4);

        // BUTTON 5
        JButton button5 = new JButton("Print Expenses");
        button5.setBackground(new Color(255, 182, 193));
        button5.setOpaque(true);
        button5.setBorderPainted(false);
        button5.addActionListener(e -> {
        	manager.PrintExpensebyType();
        });
        buttonPanel.add(button5);

        // BUTTON 6
        JButton button6 = new JButton("Print Full Report");
        button6.setBackground(new Color(255, 182, 193));
        button6.setOpaque(true);
        button6.setBorderPainted(false);
        button6.addActionListener(e -> {
        	manager.PrintFullreport();
        });	        
        buttonPanel.add(button6);

        // BUTTON 7
        JButton button7 = new JButton("Print Monthly Savings");
        button7.setBackground(new Color(255, 182, 193));
        button7.setOpaque(true);
        button7.setBorderPainted(false);
        button7.addActionListener(e -> {
        	System.out.println(manager.userAtHand.getMonthlySavings(manager));
        });
        buttonPanel.add(button7);
        
        monthlySavingsCounter = new JLabel("Monthly Savings: " + manager.userAtHand.getMonthlySavings(manager));
        
        JPanel counterPanel = new JPanel();
        counterPanel.setLayout(new FlowLayout());
        counterPanel.add(monthlySavingsCounter);
        currentFrame.add(counterPanel, BorderLayout.SOUTH);
        
        // Add empty label to fill last cell (2x4 = 8 total spots)
        buttonPanel.add(new JLabel());

        currentFrame.setVisible(true);
	}
}
