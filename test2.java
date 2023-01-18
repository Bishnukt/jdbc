import java.sql.*;
import java.util.Scanner;
public class test2 {
    private static Scanner in=new Scanner(System.in);
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
        String query="insert into test1 values(?,?)";
        PreparedStatement pstmt=con.prepareStatement(query);

        int i=0;
        System.out.print("How many names to insert: ");
        i=in.nextInt();
        System.out.println("Enter "+i+" names and their roll no.:");
        while(i>0)
        {
            System.out.println();
            System.out.print("Name- ");
            name= in.next();
            System.out.print("Roll No.- ");
            roll=in.nextInt();
            pstmt.setString(1,name);
            pstmt.setInt(2,roll);
            pstmt.executeUpdate();
            i--;
        }
    }
}