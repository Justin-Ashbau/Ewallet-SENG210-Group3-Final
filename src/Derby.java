import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Derby {
	private static String dbURLembedded="jdbc:derby:C:\\Users\\ashba\\SENG210Final";
	private static Connection conn = null;
	
	public static void createConnection() {
	    try {
	        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	        conn = DriverManager.getConnection(dbURLembedded);
	    } catch (Exception except) {
	        except.printStackTrace();
	    }
	}

	public static void addExpense(int user_id, Expense e) {
	    String sql = "INSERT INTO Expense (user_id, source, amount, yearlyfrequency) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, user_id);
	        pstmt.setString(2, e.getType());
	        pstmt.setDouble(3, e.getAmount());
	        pstmt.setInt(4, e.getYearlyFrequency());
	        pstmt.executeUpdate();
	    } catch (SQLException sqlExcept) {
	        sqlExcept.printStackTrace();
	    }
	}
	
	public static ArrayList<Expense> getExpensesForUser(int user_id) {
        ArrayList<Expense> expenses = new ArrayList<>();
        String sql = "SELECT source, amount, yearlyfrequency FROM Expense WHERE user_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user_id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String source = rs.getString("source");
                    double amount = rs.getDouble("amount");
                    int yearlyfrequency = rs.getInt("yearlyfrequency");
                    
                    Expense expense = new Expense(source, amount, yearlyfrequency);
                    expenses.add(expense);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return expenses;
    }

	public static int expenseLength(int user_id) {
	    String sql = "SELECT COUNT(*) AS expense_count FROM Expense WHERE user_id = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, user_id);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("expense_count");
	            }
	        }
	    } catch (SQLException sqlExcept) {
	        sqlExcept.printStackTrace();
	    }
	    return 0;
	}
	public static void addIncome(int user_id, Wage w) {
	    String sql = "INSERT INTO Income (user_id, source, amount, Month) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, user_id);
	        pstmt.setString(2, w.getType());
	        pstmt.setDouble(3, w.getAmount());
	        pstmt.setString(4, w.getMonth());
	        pstmt.executeUpdate();
	    } catch (SQLException sqlExcept) {
	        sqlExcept.printStackTrace();
	    }
	}

	public static ArrayList<Wage> getIncomeForUser(int user_id) {
	    ArrayList<Wage> incomeList = new ArrayList<>();
	    String sql = "SELECT source, amount, Month FROM Income WHERE user_id = ?";
	    
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, user_id);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                String source = rs.getString("source");
	                double amount = rs.getDouble("amount");
	                String month = rs.getString("Month");
	                
	                Wage wage = new Wage(source, amount, month);
	                incomeList.add(wage);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return incomeList;
	}

	public static int incomeLength(int user_id) {
	    String sql = "SELECT COUNT(*) AS income_count FROM Income WHERE user_id = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, user_id);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("income_count");
	            }
	        }
	    } catch (SQLException sqlExcept) {
	        sqlExcept.printStackTrace();
	    }
	    return 0;
	}
	public static void addUser(String username, String password) {
	    String sql = "INSERT INTO Users (login, password) VALUES (?, ?)";
	    
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, username);
	        pstmt.setString(2, password);
	        pstmt.executeUpdate();
	        System.out.println("User added successfully.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static Integer getUserIdByLogin(String login) {
	    String sql = "SELECT user_id FROM Users WHERE login = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, login);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("user_id");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	public static boolean validateUser(String login, String password) {
	    String sql = "SELECT COUNT(*) AS count FROM Users WHERE login = ? AND password = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, login);
	        pstmt.setString(2, password);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("count") > 0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}
