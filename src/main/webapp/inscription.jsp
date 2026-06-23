<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription Supervisor</title>
</head>
<body>

<h2>Inscription Supervisor</h2>

<form action="inscription" method="post">

    <label>Login :</label><br>
    <input type="text" name="login" required><br><br>

    <label>Password :</label><br>
    <input type="password" name="password" required><br><br>

    <button type="submit">
        S'inscrire
    </button>

</form>

<br>

<a href="login">
    Se connecter
</a>

</body>
</html>