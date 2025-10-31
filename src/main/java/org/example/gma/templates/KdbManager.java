package org.example.gma.templates;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class KdbManager {

    Connection connection;

    public KdbManager(String url,String user,String password) throws SQLException {
        // Initialize the connection
        this.connection = DriverManager.getConnection(url,user,password); // Replace with actual connection initialization
    }

    public String executeTransactionless(List<String> queries)  {
        for(String query: queries){
            try {
                connection.createStatement().execute(query);
            }catch (SQLException e){
                e.printStackTrace();
                return "Error executing query: " + query + " - " + e.getMessage();
            }
        }
        return "All queries executed successfully";
    }

    public String executeTransaction(List<String> queries){
        try {
            connection.setAutoCommit(false); // Start transaction

            for (String query : queries) {
                connection.createStatement().execute(query); // Execute each query
            }

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            try {
                connection.rollback(); // Rollback transaction on error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return "Transaction failed: " + e.getMessage();
        }
        return "Transaction executed successfully";
    }
}
