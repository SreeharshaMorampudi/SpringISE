
/**
 * @file SimpleFormInsert.java
 */
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

@WebServlet("/Registration")
public class Registration extends HttpServlet {
   private static final long serialVersionUID = 1L;
   Integer count=0;
   public Registration() 
   {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
      String User_Name = request.getParameter("user");
      String PASSWORD= request.getParameter("user_pwd");
      String EMAIL= request.getParameter("email");
      String GENDER= request.getParameter("gender");
      String PHONE = request.getParameter("mobile");
            
      String keyword = request.getParameter("user");
      ValidateUsrname(keyword, response);
     
     if(count==0 && PASSWORD!="" && User_Name !="")
     {
    	 System.out.println(count);
      Connection connection = null;
      String insertSql = "INSERT INTO TechTableUsers values (default, ?, ?, ?, ?, ?)";

      try 
      {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, User_Name);
         preparedStmt.setString(2, PASSWORD);
         preparedStmt.setString(3, EMAIL);
         preparedStmt.setString(4, GENDER);
         preparedStmt.setString(5, PHONE);
         preparedStmt.execute();
         connection.close();
         
      } 
      catch (Exception e) 
      {
         System.out.println("Exception at line 60"+e.getMessage());
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Registration Successful";
      String loginHeader="Continue to Login!!";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n"+ //
            "<h2 align=\"center\">" + loginHeader + "</h2>\n" //
            );

      out.println("<a href=\"Login.html\">Login Here</a> <br>");
      out.println("</body></html>");
   }
     else
     {
    	 response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         String title = "Registration Unsuccessful";
         String error="Username already exists!!";
         String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
         out.println(docType + //
               "<html>\n" + //
               "<head><title>" + title + "</title></head>\n" + //
               "<body bgcolor=\"#f0f0f0\">\n" + //
               "<h2 align=\"center\">" + title + "</h2>\n"+ //
               "<h2 align=\"center\">" + error + "</h2>\n" //
               );
         
         out.println("<a href=\"Registration.html\">Register Again</a> <br>");
         out.println("</body></html>");
     }
   }

   private void ValidateUsrname(String keyword, HttpServletResponse response) throws IOException {
	System.out.println("Validating Username");
	   response.setContentType("text/html");
	      Connection connection = null;
	      PreparedStatement preparedStatement = null;
	      try {
	         DBConnection.getDBConnection();
	         connection = DBConnection.connection;
	         
	            String selectSQL = "SELECT COUNT(User_Name) AS User_Count FROM TechTableUsers WHERE User_Name = ?";
	            System.out.println("after query at 109: "+connection.prepareStatement(selectSQL));
	            preparedStatement = connection.prepareStatement(selectSQL);
	            preparedStatement.setString(1, keyword);
	            System.out.println("after query at 112: "+preparedStatement.executeQuery());
	         ResultSet rs = preparedStatement.executeQuery();
	         while (rs.next()) 
	         {
	             count = rs.getInt("User_Count");	            
	         }
	         System.out.println("count in rs: "+count);
	         rs.close();       
	      	} 
	      catch (SQLException se) 
	      {
	         System.out.println("Exception at line 123: "+se.getMessage());
	      } catch (Exception e) {
	    	  System.out.println("Exception at line 125: "+e.getMessage());
	      } finally 
	      {
	         try {
	            if (preparedStatement != null)
	               preparedStatement.close();
	         } catch (SQLException se2) {
	         }
	         try {
	            if (connection != null)
	               connection.close();
	         } catch (SQLException se) {
	        	 System.out.println("Exception at line 136: "+se.getMessage());
	         }
	          
	      }
   }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
      doGet(request, response);
   }

}
