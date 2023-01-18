import java.sql.*;
import java.util.Scanner;

public class frequency {
    private static Scanner in = new Scanner(System.in);
    private static String str;
    private static char arr[];
    private static int[] freq;

    public static void main(String args[]) throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        String url="jdbc:mysql://localhost:3306/test";
        Connection con=DriverManager.getConnection(url, "bishnu", "Bishnupass");
        System.out.println("Enter the string: ");
        str=in.nextLine();
        in.close();
        insert_str(str,con);
        // try{
        // insert_str(str, con);
        // }
        // catch(SQLException se)
        // {
        //     System.err.println(se.getMessage());
        // }
        arr=str.toCharArray();
        arr=count(str,arr);
        for (int i = 0; i < freq.length; i++) {
            if (arr[i] != ' ' && arr[i] != '0')
            {
                char let=arr[i];
                String query="update frequency set "+let+"=? where string=?";
                PreparedStatement pstmt=con.prepareStatement(query);
                pstmt.setInt(1,freq[i]);
                pstmt.setString(2, str);
                pstmt.executeUpdate();
            }
        }
        con.close();

    }
    public static char[] count(String str,char string[])
    {
        // int cnt[]=new int[str.length()];
        freq = new int[str.length()];
        int i, j;

        // Converts given string into character array
        // char string[] = str.toCharArray();

        for (i = 0; i < str.length(); i++) {
            freq[i] = 1;
            for (j = i + 1; j < str.length(); j++) {
                if (string[i] == string[j]) {
                    freq[i]++;

                    // Set string[j] to 0 to avoid printing visited character
                    string[j] = '0';
                }
            }
        }

        // Displays the each character and their corresponding frequency
        // System.out.println("Characters and their corresponding frequencies");
                // System.out.println(string[i] + "-" + freq[i]);
        for(i=0;i<string.length;i++)
            string[i]=Character.toUpperCase(string[i]);

        return string;
    }

    static void insert_str(String str, Connection con) throws SQLException {

        String query = "insert into frequency(string) values(?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, str);
        pstmt.executeUpdate();
    }
}