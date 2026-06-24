<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connection</title>
</head>
<body>

	<h1>Connection Supervisor</h1>

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

	<form action="login" method="post">

		<div>
			<label>Login</label> <input type="text" name="login">
		</div>

		<br>

		<div>
			<label>Mot de passe</label> <input type="password" name="password">
		</div>

		<br>

		<button type="submit">Se connecter</button>

	</form>

	<br>

	<a href="inscription"> Créer un compte </a>

</body>
</html>