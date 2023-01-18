import java.sql.*;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner in = new Scanner(System.in);
        // System.out.print("Enter the root password- ");
        // String pass=in.next();
        // String query="use test;create table test1(Name varchar(30),Roll_no int
        // primary key";
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "bishnu", "Bishnupass");
        Statement stmt = con.createStatement();
        stmt.executeUpdate("use test");
        // stmt.executeUpdate("use test");
        // stmt.executeUpdate("create table test1(Name varchar(30),Roll_no int primary
        // key)");
        // stmt.executeUpdate(query);
        String query2="insert into test1(Name,Roll_no) values(?,?)";
        PreparedStatement pstmt= con.prepareStatement(query2);
        pstmt.setString(1,"Bishnu");
        pstmt.setInt(2,2);
        pstmt.executeUpdate();
        System.out.println("Data insertion success.");
        con.close();
    }
}