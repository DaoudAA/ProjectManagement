<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Erreur - Échec de la Mise à Jour de la Tâche</title>
</head>
<body>
    <h2>Erreur - Échec de la Mise à Jour de la Tâche</h2>
    <p>${tmodel.errors}</p>
    <form action="TaskServlet" method="post">
        <input type="hidden" name="action" value="edit"/>
        <input type="hidden" name="code" value="${task.code}"/>
        <input type="submit" value="Réessayer la Mise à Jour de la Tâche"/>
    </form>
    <br>
    <form action="TaskServlet" method="get">
        <input type="hidden" name="action" value="list"/>
        <input type="submit" value="Liste des Tâches"/>
    </form>
</body>
</html>
