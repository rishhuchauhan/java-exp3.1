import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/web_portal";
    private static final String USER = "root";
    private static final String PASS = "your_password_here";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String studentID = request.getParameter("studentID");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            PreparedStatement ps = conn.prepareStatement("INSERT INTO Attendance VALUES (?, ?, ?)");
            ps.setInt(1, Integer.parseInt(studentID));
            ps.setString(2, date);
            ps.setString(3, status);
            ps.executeUpdate();

            out.println("<h3>✅ Attendance submitted successfully!</h3>");
            out.println("<a href='attendance.jsp'>Go Back</a>");
            conn.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
