<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="co.kozao.internmanagement.model.Supervisor" %>

<%
Supervisor supervisor =
        (Supervisor) session.getAttribute("supervisor");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>

<h1>Dashboard</h1>

<p>
Bienvenue
<strong>
<%= supervisor.getLogin() %>
</strong>
</p>

<p>
ID :
<%= supervisor.getId() %>
</p>

<a href="logout">
    Déconnexion
</a>

</body>
</html>