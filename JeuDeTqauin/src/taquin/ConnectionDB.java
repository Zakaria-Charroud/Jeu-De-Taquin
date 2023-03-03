package taquin;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConnectionDB {

    Connection connection = null;

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
            return connection;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Connection failed");
            return null;
        }

    }
}
