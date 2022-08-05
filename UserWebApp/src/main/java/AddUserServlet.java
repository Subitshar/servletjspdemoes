

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/adduserservlet")
public class AddUserServlet extends HttpServlet {
	
	Connection connection;
	@Override
	public void init(ServletConfig config) throws ServletException {
			try {
				ServletContext context=config.getServletContext();
				System.out.println("ReadUserSevlet.init() method. DB connection created");
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(context.getInitParameter("dburl"),
						context.getInitParameter("dbuser"), context.getInitParameter("dbpassword"));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		
		
		try (PreparedStatement statement = connection.prepareStatement("insert into user values (?,?,?,?)");) {

			statement.setString(1,firstname);
			statement.setString(2,lastname);
			statement.setString(3,email);
			statement.setString(4,password);
			
			int rowsInserted = statement.executeUpdate();
			System.out.println("Number of rows inserted: " + rowsInserted);
			
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			pw.write("User added Successfully");
			pw.write("<p><a href=\"Home.html\">Home</a></p>");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
	}
	
	@Override
	public void destroy() {
		try {
			System.out.println("AddUserSevlet.destroy() method. DB connection closed");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
