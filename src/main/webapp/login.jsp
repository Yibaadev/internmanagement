<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>

	<h2>Connexion Supervisor</h2>

	<%
		String erreur = (String) request.getAttribute("erreur");
		if(erreur != null){
	%>

	<p style="color: red;">
		<%= erreur %>
	</p>

	<%}%>

	<form action="login" method="post">

		<label>Login :</label><br> <input type="text" name="login"
			required><br> <br> <label>Password :</label><br>
		<input type="password" name="password" required><br> <br>

		<button type="submit">Se connecter</button>

	</form>

	<br>

	<a href="inscription"> Créer un compte </a>

</body>
</html>