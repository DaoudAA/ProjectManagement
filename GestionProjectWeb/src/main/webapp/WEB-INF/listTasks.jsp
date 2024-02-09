<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List Tasks</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>Code</th>
            <th>Description</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Project</th>
            <th>Action</th>
        </tr>
        <c:forEach var="task" items="${tmodel.tasks}">
            <tr>
                <td>${task.code}</td>
                <td>${task.description}</td>
                <td>${task.startDate}</td>
                <td>${task.endDate}</td>
                <td>${task.project != null ? task.project.code : ''}</td>
                <td>
                    <form action="TaskServlet" method="get">
                        <input type="hidden" name="action" value="edit"/>
                        <input type="hidden" name="code" value="${task.code}"/>
                        <input type="submit" value="Edit"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p>${tmodel.errors}</p>
    <form action="TaskServlet" method="get">
        <input type="hidden" name="action" value="edit"/>
        <input type="submit" value="Add a Task"/>
    </form>
</body>
</html>