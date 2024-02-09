<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Task Details</title>
    <style>
        form {
            border: 2px solid black;
            padding: 10px;
            margin-bottom: 10px;
            display: inline-block;
        }

        table {
            width: 100%;
        }

        td {
            padding: 5px;
        }

        input[type="date"],
        input[type="text"],
        input[type="submit"] {
            margin: 5px 0;
        }
    </style>
</head>
<body>
<form action="TaskServlet" method="post">
    <input type="hidden" name="action" value="${tmodel.mode eq 'edit' ? 'update' : 'add'}"/>
    <table>
        <tr>
            <td>Code:</td>
            <td><input type="text" name="code" value="${tmodel.code}"/></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><input type="text" name="description" value="${tmodel.description}"/></td>
        </tr>
        <tr>
            <td>Start Date:</td>
            <td><input type="date" name="startdate" value="${tmodel.startdate.toLocalDateTime().toLocalDate()}"/></td>
        </tr>
        <tr>
            <td>End Date:</td>
            <td><input type="date" name="enddate" value="${tmodel.enddate.toLocalDateTime().toLocalDate()}"/></td>
        </tr>
        <tr>
            <td>Project Code:</td>
            <td>
                <input list="projects" name="projectcode" value="${tmodel.project.code}">
                <datalist id="projects">
                    <c:forEach var="project" items="${pmodel.projects}">
                        <option value="${project.code}">
                    </c:forEach>
                </datalist>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="${tmodel.mode eq 'edit' ? 'Update' : 'Create'} Task"/>
            </td>
        </tr>
    </table>
</form>
<br>
<form action="TaskServlet" method="post">
    <input type="hidden" name="action" value="remove"/>
    <input type="hidden" name="code" value="${tmodel.code}"/>
    <input type="submit" value="Delete Task"/>
</form>
<br>
<form action="TaskServlet" method="get">
    <input type="hidden" name="action" value="list"/>
    <input type="submit" value="List Tasks"/>
</form>
</body>
</html>
