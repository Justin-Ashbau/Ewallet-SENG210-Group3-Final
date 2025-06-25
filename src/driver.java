import java.util.Scanner;

public class driver {
	//this is just my test of my code 
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter file name: ");
		String FileName = scnr.nextLine();
		ExpenseManager test = new ExpenseManager();
		test.loadExpenseFile(FileName);
		
		
		
	}
	

}
