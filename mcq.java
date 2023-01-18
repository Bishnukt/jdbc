import java.sql.*;
import java.util.Scanner;

public class mcq{
    private static String ques;
    private static int ans;
    private static String opt[]=new String[4];
    private static Scanner in=new Scanner(System.in);
    static String url="jdbc:mysql://localhost:3306/cc11";
    static void input(Connection con) throws SQLException
    {
        String query="insert into mcq(ques,opt1,opt2,opt3,opt4,ans) values(?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(query);
        System.out.print("Enter the question: ");
        ques=in.nextLine();
        System.out.println();
        System.out.println("Enter 4 options for answer- ");
        for(int i=1;i<=4;i++)
        {
            System.out.print("Option"+i+": ");
            opt[i-1]=in.nextLine();
        }
        System.out.print("Enter the correct option no.- ");
        ans=in.nextInt();
        // pstmt.setInt(1, no);
        pstmt.setString(1,ques);
        for(int i=0;i<4;i++)
        {
            pstmt.setString(i+2, opt[i]);
        }
        pstmt.setInt(6, ans);
        pstmt.executeUpdate();
    }

    static void solve(Connection con) throws SQLException{

        String query="select * from mcq";
        Statement stmt=con.createStatement();
        ResultSet res=stmt.executeQuery(query);
        while(res.next())
        {
            int usr;
            int no=res.getInt(1);
            ques=res.getString(2);
            for(int i=0;i<4;i++)
                opt[i]=res.getString(i+3);
            ans=res.getInt(7);
            System.out.println("Q"+no+"."+ques);
            // System.out.print("Options- ");
            for(int i=0;i<4;i++)
                System.out.println("\t"+(i+1)+"."+opt[i]+" ");
            System.out.print("Answer-Enter Option no.- ");
            do{
                usr=in.nextInt();
            }while(!(usr>=1 && usr<=4));
            if(ans==usr)
                System.out.println("Correct Answer.");
            else System.out.println("Wrong Answer. Correct Answer is Option no."+ans+"- "+opt[ans-1]);
            System.out.println();
        }
        System.out.println("\n");
    }

    public static void main(String args[]) throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Connection con=DriverManager.getConnection(url,"bishnu","Bishnupass");
        int choice;
        while(true)
        {
            System.out.print("1.Insert\n2.Solve\n3.Exit\n\nEnter your choice- ");
            choice=in.nextInt();
            in.nextLine();
            switch(choice)
            {
                case 1:
                    System.out.println();
                    input(con);
                    break;
                case 2:
                    System.out.println();
                    solve(con);
                    break;
                case 3:
                    in.close();
                    con.close();
                    System.exit(0);
                default: System.out.println("Wrong Choice. Try again.");
            }
        }
    }
}