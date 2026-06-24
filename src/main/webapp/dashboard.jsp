<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Superviseur</title>
</head>
<body>
    <h2>Espace Superviseur - Gestion des Stagiaires</h2>
    
s
    <p>Bienvenue, M. <strong>${supervisor.login}</strong></p> 
    
    <hr>
    <h3>Ajouter un Stagiaire</h3>
   
    <form action="interManagement" method="post">
        <input type="hidden" name="action" value="add">
        <input type="text" name="name" placeholder="Nom" required>
        <input type="text" name="surname" placeholder="Prénom" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="date" name="startDate" required>
        <input type="date" name="endDate" required>
        <input type="number" name="group" placeholder="Groupe" required>
        <button type="submit">Enregistrer</button>
    </form>

    <hr>
    <h3>Liste des Stagiaires sous votre responsabilité</h3>
    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th><th>Nom</th><th>Prénom</th><th>Email</th><th>Début</th><th>Fin</th><th>Groupe</th><th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="intern" items="${listeStagiaires}">
                <tr>
                    <td>${intern.id}</td>
                    <td>${intern.name}</td>
                    <td>${intern.surname}</td>
                    <td>${intern.email}</td>
                    <td>${intern.startDate}</td>
                    <td>${intern.endDate}</td>
                    <td>${intern.group}</td>
                    <td>
                        <%-- Les liens pointent également vers la servlet interManagement --%>
                        <a href="interManagement?action=edit&id=${intern.id}">Modifier</a> | 
                        <a href="interManagement?action=delete&id=${intern.id}" onclick="return confirm('Voulez-vous vraiment supprimer ce stagiaire ?')">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty listeStagiaires}">
                <tr>
                    <td colspan="8" style="text-align: center; color: gray;">Aucun stagiaire enregistré pour le moment.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</body>
</html>