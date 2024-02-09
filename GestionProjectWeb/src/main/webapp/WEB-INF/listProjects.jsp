<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List Projects</title>
</head>
<body>
<table border="1">
        <tr>
            <th>Code</th>
            <th>Description</th>
            <th>Start Date</th>
            <th>Action</th>
        </tr>
        <c:forEach var="project" items="${pmodel.projects}">
            <tr>
                <td>${project.code}</td>
                <td>${project.description}</td>
                <td>${project.startDate}</td>
                <td>
                    <form action="ProjectServlet" method="get">
                        <input type="hidden" name="action" value="edit"/>
                        <input type="hidden" name="code" value="${project.code}"/>
                        <input type="submit" value="Edit"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="ProjectServlet" method="get">
                        <input type="hidden" name="action" value="edit"/>
                        <input type="submit" value="Add a project"/>
                    </form>
</body>
</html>