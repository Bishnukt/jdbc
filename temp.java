import java.sql.*;

public class temp{
	private static final String driver="com.mysql.cj.jdbc.Driver";
	private static final String con="jdbc:mysql://localhost:3306/";

public static void main(String[] args) throws ClassNotFoundException,SQLException
{
	System.out.println(driver);
	Class.forName(driver);
	Connection c=DriverManager.getConnection(con,"temp","dummy");
	System.out.println("Worked.");
}
}
