 <%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <title>Attendance Portal</title>
</head>
<body>
    <h2>Student Attendance</h2>
    <form action="AttendanceServlet" method="post">
        Student ID: <input type="text" name="studentID" required><br><br>
        Date: <input type="date" name="date" required><br><br>
        Status:
        <select name="status">
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select><br><br>
        <input type="submit" value="Submit Attendance">
    </form>
</body>
</html>
