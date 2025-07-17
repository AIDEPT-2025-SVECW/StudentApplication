<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bvraju.aidept.StudentApplication.model.Student" %>

<html>
<head>
    <title>Student List</title>
    <style>
        table {
            width: 60%;
            border-collapse: collapse;
            margin: 20px auto;
        }
        th, td {
            padding: 8px 12px;
            border: 1px solid #888;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2 style="text-align:center;">Student Details</h2>

    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        if (students != null && !students.isEmpty()) {
    %>

    <table>
        <tr>
            <th>Name</th>
            <th>Reg ID</th>
            <th>Section</th>
            <th>Department</th>
            <th>College</th>
        </tr>

        <%
            for (Student s : students) {
        %>
            <tr>
                <td><%= s.getName() %></td>
                <td><%= s.getRegId() %></td>
                <td><%= s.getSe() %></td>
                <td><%= s.getDept() %></td>
                <td><%= s.getCollege() %></td>
            </tr>
        <%
            }
        %>
    </table>

    <%
        } else {
    %>
        <p style="text-align:center; color: red;">No student data available.</p>
    <%
        }
    %>
</body>
</html>
