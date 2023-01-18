import java.sql.*;
import java.util.Scanner;

public class test3 {
    // private static Scanner in = new Scanner(System.in);
    private static String name;
    private static int roll;

    public static void main(String args[]) throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        String url="jdbc:mysql://localhost:3306/test";
        Connection con=DriverManager.getConnection(url,"bishnu","Bishnupass");
        String query="SELECT * FROM test1";
        Statement stmt=con.createStatement();
        ResultSet res=stmt.executeQuery(query);

        while(res.next())
        {
            name=res.getString(1);
            roll=res.getInt(2);
            System.out.println(name+"   "+roll);
        }
    }
}