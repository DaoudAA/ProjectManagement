<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Succès - Projet Inséré</title>
</head>
<body>
    <h2>Succès - Projet Inséré</h2>
    <p>${successMessage}</p>
    <form action="ProjectServlet" method="get">
        <input type="hidden" name="action" value="list"/>
        <input type="submit" value="List Projects"/>
    </form>
</body>
</html>