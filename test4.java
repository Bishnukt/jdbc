import java.sql.*;
import java.util.Scanner;
import java.util.Random;

public class test4 {
    private static Scanner in=new Scanner(System.in);
    private static String name;
    private static int id,age;

    public static void main(String args[]) throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        String url="jdbc:mysql://localhost:3306/test";
        Connection con=DriverManager.getConnection(url, "bishnu","Bishnupass");
        // Statement stmt=con.createStatement();
        // stmt.executeUpdate("create table okay(Name varchar(30),age int,id int primary key)");
        String query="insert into okay values(?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(query);
        for(int i=1;i<=100;i++)
        {
            id=i;
            name=rndm();
            age=num();
            // System.out.println(name);
            // System.out.println(age);
            pstmt.setString(1,name);
            pstmt.setInt(2, age);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        }
        con.close();
        in.close();
    }
    
    static String rndm(){
    // create a string of all characters
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // create random string builder
    StringBuilder sb = new StringBuilder();

    // create an object of Random class
    Random random = new Random();

    // specify length of random string
    int length = 7;

    for (int i = 0; i < length; i++) {

        // generate random index number
        int index = random.nextInt(alphabet.length());

        // get character specified by index
        // from the string
        char randomChar = alphabet.charAt(index);

        // append the character to string builder
        sb.append(randomChar);
    }

    String randomString = sb.toString();
    return randomString;
    }

    static int num()
    {
        int min = 18;
        int max = 80;
        // Generate random double value from 200 to 400
        // System.out.println("Random value of type double between " + min + " to " + max + ":");
        // double a = Math.random() * (max - min + 1) + min;
        // System.out.println(a);
        // Generate random int value from 200 to 400
        // System.out.println("Random value of type int between " + min + " to " + max + ":");
        int b = (int) (Math.random() * (max - min + 1) + min);
        // System.out.println(b);
        return b;
    }
}
