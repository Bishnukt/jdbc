//GST Billing System
import java.math.BigDecimal;
import java.sql.*;
// import java.time.LocalDate;
import java.util.Scanner;

public class bill_system {
    private static String customer_name,item_name,category;
    private static double price,total_amount,gst;
    private static int item_quantity,bill_no;
    private static Date date;
    private static String url="jdbc:mysql://localhost:3306/cc11";
    private static Scanner in=new Scanner(System.in);
    
    static void new_bill(Connection con)
    {
        System.out.println();
        String query="insert into gst_bill_system(customer_name,item_name,item_quantity,date,total_amount,item_category) values(?,?,?,(select curdate()),?,?)";
        System.out.print("Enter Customer's Name- ");
        customer_name=in.nextLine();
        System.out.print("Enter item name- ");
        item_name=in.nextLine();
        System.out.print("Enter the price- ");
        price=in.nextDouble();
        System.out.print("Enter quantitiy of items- ");
        item_quantity=in.nextInt();
        System.out.print("Enter item category- ");
        category=in.next();
        in.nextLine();
        total_amount=item_quantity*price;
        gst=((double)18/(double)100)*total_amount;
        // System.out.println(((double)18 / (double)100) * total_amount);
        total_amount+=gst;
        // System.out.println("gst="+gst+" total="+total_amount);
        try{
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1, customer_name);
            pstmt.setString(2, item_name);
            pstmt.setInt(3, item_quantity);
            BigDecimal val=new BigDecimal(total_amount);
            pstmt.setBigDecimal(4, val);
            pstmt.setString(5,category);
            pstmt.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return;
        }
    }

    static void view_bill(Connection con)
    {
        System.out.println();
        String query="select * from gst_bill_system where bill_no=?";
        BigDecimal paid=null;
        String applied_gst=null;
        try {
            PreparedStatement pstmt=con.prepareStatement(query);
            System.out.print("Enter the bill no. to view the bill- ");
            bill_no=in.nextInt();
    
            pstmt.setInt(1, bill_no);
            ResultSet res=pstmt.executeQuery();
            if(!res.next())
            {
                System.err.println("No record found\n");
                return ;
            }
            customer_name=res.getString(2);
            item_name=res.getString(3);
            item_quantity=res.getInt(4);
            applied_gst=res.getString(5);
            date=res.getDate(6);
            paid=res.getBigDecimal(7);
            // total_amount=val.doubleValue();
            category=res.getString(6);  
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // e.printStackTrace();
            return;
        }

        System.out.println("\nHere's your Bill.\n");
        System.out.println("Dated- "+date);
        System.out.println("Customer Name= "+customer_name+"\tItems Bought- "+item_name);
        System.out.println("Total Amount Paid Rs"+paid+" including GST applied "+applied_gst+"\n\n");
        in.nextLine();
        System.out.print("Press Enter to continue...");
        in.nextLine();
    }

    public static void main(String args[]) 
    {
        Connection con=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,"temp", "pass");
        }
        catch(SQLException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
            System.exit(0);
        }
        int ch=0;
        while(true){
            System.out.println("1. Create a New Bill. \n2. View Bill \n3. Exit\n");
            System.out.print("Enter your choice- ");
            ch=in.nextInt();
            switch(ch)
            {
                case 1:
                        new_bill(con);
                        break;
                case 2:
                        view_bill(con);
                        break;
                case 3:
                        try{
                            con.close();
                        }
                        catch(SQLException e)
                        {
                            System.out.println(e.getMessage());
                        }
                        finally{
                            in.close();
                            System.exit(0);
                        }
                default:
                        System.out.println("Invalid choice, please try again.");
            }

        }
    }
}
