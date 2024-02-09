<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Succès - Tâche Supprimée</title>
</head>
<body>
    <h2>Succès - Tâche Supprimée</h2>
    <p>${successMessage}</p>
    <form action="TaskServlet" method="get">
        <input type="hidden" name="action" value="list"/>
        <input type="submit" value="List Tasks"/>
    </form>
</body>
</html>
