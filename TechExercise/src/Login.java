import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public Login() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
      String uname = request.getParameter("user");
      String upwd= request.getParameter("user_pwd");
      System.out.println("Entered Login.java doget");
      search(uname,upwd, response);
   }

   void search(String username, String password, HttpServletResponse response) throws IOException {
      System.out.println("Entered search function");
	   response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Login Result";
      String success="Login Success!";
      String failure="Login Failed please try again with valid credentials";
      
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");

      Connection connection = null;
      PreparedStatement preparedStatement = null;
      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         System.out.println("Entered try block in login.java");
         System.out.println(username);
         System.out.println(password);
         if (username.isEmpty() || password.isEmpty())
         {
        	 System.out.println("Entered if in try");
      	      out.println("<h1 align=\"center\">" + failure + "</h1>\n");
      	      out.println("<a href=\"Login.html\">Login Again</a> <br>");
         } 
         else  
         {
            String selectSQL = "SELECT User_Name,PASSWORD FROM TechTableUsers WHERE User_Name = ?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, username);
            System.out.println("Final Query:" +preparedStatement);
         }
         ResultSet rs = preparedStatement.executeQuery();

         while (rs.next()) {
      
            String userName = rs.getString("User_Name");
            String userPwd = rs.getString("PASSWORD");
            
            System.out.println("Result set username line 68"+userName);
            System.out.println("Result set Password line 69"+userPwd);

            if (userName!="" && userPwd!="" && userName.equals(username) && userPwd.equals(password)) 
            {
            		out.println("<h1 align=\"center\">" + success + "</h1>\n");
               
            }
            else
            {
            	out.println("<h1 align=\"center\">" + failure + "</h1>\n");
            }
         }
         out.println("</body></html>");
         rs.close();
         preparedStatement.close();
         connection.close();
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (preparedStatement != null)
               preparedStatement.close();
         } catch (SQLException se2) {
         }
         try {
            if (connection != null)
               connection.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
