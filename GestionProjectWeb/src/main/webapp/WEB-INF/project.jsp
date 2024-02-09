<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Project Details</title>
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
    <form action="ProjectServlet" method="post">
        <input type="hidden" name="action" value="${pmodel.mode eq 'edit' ? 'update' : 'add'}"/>
        <table>
            <tr>
                <td>Code:</td>
                <td><input type="text" name="code" value="${pmodel.code}"/></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><input type="text" name="description" value="${pmodel.description}"/></td>
            </tr>
            <tr>
                <td>Start Date:</td>
                <td><input type="date" name="startdate" value="${pmodel.startdate.toLocalDateTime().toLocalDate()}"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="${pmodel.mode eq 'edit' ? 'Update' : 'Create'} Project"/>
                </td>
            </tr>
        </table>
    </form>
	<br>
    <form action="ProjectServlet" method="post">
        <input type="hidden" name="action" value="remove"/>
        <input type="hidden" name="code" value="${pmodel.code}"/>
        <input type="submit" value="Delete Project"/>
    </form>
	<br>
    <form action="ProjectServlet" method="get">
        <input type="hidden" name="action" value="list"/>
        <input type="submit" value="List Projects"/>
    </form>
</body>
</html>
