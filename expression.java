import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;

public class expression {
    public static void main(String args[]) throws SQLException{

        String url="jdbc:mysql://localhost:3306/cc11";
        String exp;
        Scanner in =new Scanner(System.in);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.print("Enter the expression:- ");
        exp=in.nextLine();
        Connection con=DriverManager.getConnection(url, "temp", "dummy");
        String query="insert into expression(expression,ans) values(?,(select "+exp+"))";
        // String calc="select "+exp;
        // Statement stmt=con.createStatement();
        // ResultSet res=stmt.executeQuery(calc);
        // res.next();
        PreparedStatement pstmt=con.prepareStatement(query);
        pstmt.setString(1, exp);
        // pstmt.setInt(2, res.getInt(1));
        pstmt.executeUpdate();
        con.close();
        in.close();
    }
}
