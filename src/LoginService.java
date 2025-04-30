import java.sql.*;

public class LoginService {
    // Updated login method using parameterized SQL
    public boolean login(String userInput, String passInput) {
        boolean authenticated = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userInput);
            stmt.setString(2, passInput);
            rs = stmt.executeQuery();
            if (rs.next()) {
                authenticated = true;
            }
        } catch (SQLException e) {
            // ...existing error handling...
            e.printStackTrace();
        } finally {
            // ...existing cleanup code...
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return authenticated;
    }
    
    private Connection getConnection() throws SQLException {
        // ...existing code to establish connection...
        String url = "jdbc:yourdb://localhost:3306/yourdb";
        String user = "dbuser";
        String password = "dbpassword";
        return DriverManager.getConnection(url, user, password);
    }
}
