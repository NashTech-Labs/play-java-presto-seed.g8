package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrestoService {
  public String getData(String databaseName, String tableName) {

    final String JDBC_DRIVER = "com.facebook.presto.jdbc.PrestoDriver";
    final String DB_URL = "jdbc:presto://localhost:8086/hive-hadoop2/" + databaseName;
    String result = "";

    //  Database Credentials
    final String USER = "username";
    final String PASS = "password";

    Connection conn = null;
    Statement stmt = null;
    try {
      //STEP 2: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 3: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 4: Execute a query
      stmt = conn.createStatement();
      String sql;
      sql = "select * from " + tableName;

      ResultSet res = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      result = "name" + "age\n";
            while (res.next()) {
                //Retrieve by column name

                String name = res.getString("name");
                Integer age = res.getInt("age");

                //Display values
                System.out.println("imei: " + name + "\n check_year : " + age);
                System.out.println("success");
                result += name + age + "\n";
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


