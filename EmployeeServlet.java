import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/web_portal";
    private static final String USER = "root";
    private static final String PASS = "your_password_here";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String empId = request.getParameter("empId");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            if (empId != null && !empId.isEmpty()) {
                // Search specific employee
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM Employee WHERE EmpID=?");
                ps.setInt(1, Integer.parseInt(empId));
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    out.println("<h3>Employee Found:</h3>");
                    out.println("ID: " + rs.getInt(1) + "<br>Name: " + rs.getString(2) + "<br>Salary: " + rs.getDouble(3));
                } else {
                    out.println("<h3>No Employee Found!</h3>");
                }
            } else {
                // Display all employees
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");

                out.println("<h2>All Employees</h2>");
                out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Salary</th></tr>");
                while (rs.next()) {
                    out.println("<tr><td>" + rs.getInt(1) + "</td><td>" +
                                rs.getString(2) + "</td><td>" + rs.getDouble(3) + "</td></tr>");
                }
                out.println("</table>");
            }
            conn.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
