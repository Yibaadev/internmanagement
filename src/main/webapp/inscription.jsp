<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription Supervisor</title>
</head>
<body>

	<h1>Inscription Supervisor</h1>

	<%
String error = (String) request.getAttribute("error");

if(error != null){
%>
	<p style="color: red;">
		<%= error %>
	</p>
	<%
}
%>

	<form action="inscription" method="post">

		<div>
			<label>Login</label> <input type="text" name="login">
		</div>

		<br>

		<div>
			<label>Mot de passe</label> <input type="password" name="password">
		</div>

		<br>

		<button type="submit">S'inscrire</button>

	</form>

	<br>

	<a href="login"> Déjà un compte ? Se connecter </a>

</body>
</html>