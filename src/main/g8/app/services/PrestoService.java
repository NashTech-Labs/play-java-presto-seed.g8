package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrestoService {

  public String getData(String catalogName, String databaseName, String tableName) {

    final String JDBC_DRIVER = "com.facebook.presto.jdbc.PrestoDriver";
    final String CATALOG_NAME = catalogName;
    final String DB_URL = "jdbc:presto://localhost:8080/" + CATALOG_NAME + "/" + databaseName;
    StringBuilder result = new StringBuilder();

    //  Database Credentials
    final String USER = "username";
    final String PASS = "password";

    Connection conn = null;
    Statement stmt = null;
    try {
      //Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //Execute a query
      stmt = conn.createStatement();
      String sql;
      sql = "select columnName from " + tableName ;

      ResultSet res = stmt.executeQuery(sql);

      //Extract data from result set
      while (res.next()) {
        //Retrieve by column name
        String data = res.getString("columnName");

        //Display values
        result.append(data + "\n");
      }
      //STEP 6: Clean-up environment
      res.close();
      stmt.close();
      conn.close();
    } catch (SQLException se) {
      //Handle errors for JDBC
      se.printStackTrace();
    } catch (Exception e) {
      //Handle errors for Class.forName
      e.printStackTrace();
    } finally {
      //finally block used to close resources
      try {
        if (stmt != null) stmt.close();
      } catch (SQLException se2) {
        se2.printStackTrace();
      }
      try {
        if (conn != null) conn.close();
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }
    return result.toString();
  }
}


