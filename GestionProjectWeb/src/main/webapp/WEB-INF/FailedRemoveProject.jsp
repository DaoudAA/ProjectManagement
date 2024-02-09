<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Erreur - Échec de la Suppression du Projet</title>
</head>
<body>
    <h2>Erreur - Échec de la Suppression du Projet</h2>
    <p>${pmodel.errors}</p>
    <form action="ProjectServlet" method="post">
        <input type="hidden" name="action" value="edit"/>
        <input type="hidden" name="code" value="${project.code}"/>
        <input type="submit" value="Réessayer la Suppression du Projet"/>
    </form>
    <br>
    <form action="ProjectServlet" method="get">
        <input type="hidden" name="action" value="list"/>
        <input type="submit" value="Liste des Projets"/>
    </form>
</body>
</html>
