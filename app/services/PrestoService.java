package services;


import scala.Int;

import java.sql.*;


public class PrestoService {
    public static void main(String args[]) {

        final String JDBC_DRIVER = "com.facebook.presto.jdbc.PrestoDriver";
        final String DB_URL = "jdbc:presto://localhost:8086/carbondata/demo";

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
            //conn.setCatalog("hive");

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "select * from employee";

            ResultSet result=stmt.executeQuery(sql);
          /*  ResultSet res = stmt.executeQuery(sql);*/

            /*System.out.println(">>>>"+res);*/
            //STEP 5: Extract data from result set
           /* while (res.next()) {
                //Retrieve by column name

               *//* String imei = res.getString("name");
                Integer check_year = res.getInt("age");

                //Display values
                System.out.println("imei: " + imei + "\n check_year : " + check_year);*//*
                System.out.println("success");
            }*/
            //STEP 6: Clean-up environment
            // rs.close();
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
    }
}


