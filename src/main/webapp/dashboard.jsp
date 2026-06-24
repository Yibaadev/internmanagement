<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard Superviseur</title>

<style>
    body {
        font-family: Arial, sans-serif;
        background: #f4f6f9;
        margin: 0;
        padding: 0;
    }

    .header {
        background: #2c3e50;
        color: white;
        padding: 15px 30px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .header a {
        color: #fff;
        text-decoration: none;
        background: #e74c3c;
        padding: 8px 12px;
        border-radius: 5px;
    }

    .container {
        padding: 30px;
    }

    h2 {
        margin-top: 0;
        color: #2c3e50;
    }

    .card {
        background: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 5px 15px rgba(0,0,0,0.08);
        margin-bottom: 20px;
    }

    h3 {
        margin-top: 0;
        color: #34495e;
    }

    input {
        padding: 10px;
        margin: 5px;
        border: 1px solid #ccc;
        border-radius: 6px;
    }

    button {
        padding: 10px 15px;
        background: #3498db;
        color: white;
        border: none;
        border-radius: 6px;
        cursor: pointer;
    }

    button:hover {
        background: #2980b9;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        background: white;
        border-radius: 10px;
        overflow: hidden;
        box-shadow: 0 5px 15px rgba(0,0,0,0.08);
    }

    th {
        background: #3498db;
        color: white;
        padding: 12px;
    }

    td {
        padding: 10px;
        border-bottom: 1px solid #eee;
        text-align: center;
    }

    tr:hover {
        background: #f1f1f1;
    }

    .edit {
        color: #2980b9;
        text-decoration: none;
        font-weight: bold;
    }

    .delete {
        color: #e74c3c;
        text-decoration: none;
        font-weight: bold;
    }

    .mode {
        padding: 10px;
        border-radius: 6px;
        margin-bottom: 10px;
        font-weight: bold;
    }

    .add {
        background: #e8f9f1;
        color: #27ae60;
    }

    .update {
        background: #fff3e0;
        color: #e67e22;
    }

    .cancel a {
        color: #e74c3c;
        text-decoration: none;
        font-weight: bold;
    }
</style>

</head>

<body>

<div class="header">
    <h2>Dashboard Superviseur</h2>
    <div>
        Bienvenue, <strong>${supervisor.login}</strong>
        <a href="logout">Déconnexion</a>
    </div>
</div>

<div class="container">

    <div class="card">

        <h3>
            <c:choose>
                <c:when test="${not empty internToEdit}">
                    Modifier un Stagiaire
                </c:when>
                <c:otherwise>
                    Ajouter un Stagiaire
                </c:otherwise>
            </c:choose>
        </h3>

        <c:choose>
            <c:when test="${not empty internToEdit}">
                <div class="mode update"> Mode modification actif</div>
            </c:when>
            <c:otherwise>
                <div class="mode add">Mode ajout actif</div>
            </c:otherwise>
        </c:choose>

        <form action="interManagement" method="post">

            <c:choose>
                <c:when test="${not empty internToEdit}">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="${internToEdit.id}">
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="action" value="add">
                </c:otherwise>
            </c:choose>

            <input type="text" name="name" placeholder="Nom"
                value="${internToEdit.name}" required>

            <input type="text" name="surname" placeholder="Prénom"
                value="${internToEdit.surname}" required>

            <input type="email" name="email" placeholder="Email"
                value="${internToEdit.email}" required>

            <input type="date" name="startDate"
                value="${internToEdit.startDate}" required>

            <input type="date" name="endDate"
                value="${internToEdit.endDate}" required>

            <input type="number" name="group"
                value="${internToEdit.group}" required>

            <button type="submit">
                <c:choose>
                    <c:when test="${not empty internToEdit}">
                        Mettre à jour
                    </c:when>
                    <c:otherwise>
                        Enregistrer
                    </c:otherwise>
                </c:choose>
            </button>

        </form>

        <c:if test="${not empty internToEdit}">
            <div class="cancel">
                <a href="interManagement"> Annuler la modification</a>
            </div>
        </c:if>

    </div>

    <div class="card">

        <h3>Liste des Stagiaires</h3>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Email</th>
                    <th>Début</th>
                    <th>Fin</th>
                    <th>Groupe</th>
                    <th>Actions</th>
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
                            <a class="edit"
                               href="interManagement?action=edit&id=${intern.id}">
                               Modifier
                            </a>
                            |
                            <a class="delete"
                               href="interManagement?action=delete&id=${intern.id}"
                               onclick="return confirm('Supprimer ce stagiaire ?')">
                               Supprimer
                            </a>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty listeStagiaires}">
                    <tr>
                        <td colspan="8">Aucun stagiaire trouvé</td>
                    </tr>
                </c:if>
            </tbody>

        </table>

    </div>

</div>

</body>
</html>