<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion Supervisor</title>

<style>
    body {
        font-family: Arial, sans-serif;
        background: #f4f6f9;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    .container {
        background: white;
        padding: 30px;
        width: 380px;
        border-radius: 10px;
        box-shadow: 0 5px 20px rgba(0,0,0,0.1);
    }

    h1 {
        text-align: center;
        margin-bottom: 20px;
        color: #2c3e50;
    }

    label {
        font-weight: bold;
        font-size: 14px;
        display: block;
        margin-bottom: 5px;
        color: #34495e;
    }

    input {
        width: 100%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 6px;
        outline: none;
        transition: 0.2s;
    }

    input:focus {
        border-color: #3498db;
        box-shadow: 0 0 4px rgba(52,152,219,0.3);
    }

    button {
        width: 100%;
        padding: 10px;
        background: #2ecc71;
        color: white;
        border: none;
        border-radius: 6px;
        font-weight: bold;
        cursor: pointer;
        transition: 0.3s;
    }

    button:hover {
        background: #27ae60;
    }

    .error {
        background: #ffdddd;
        color: #c0392b;
        padding: 10px;
        border-radius: 6px;
        margin-bottom: 15px;
        text-align: center;
    }

    .link {
        text-align: center;
        margin-top: 15px;
    }

    .link a {
        color: #3498db;
        text-decoration: none;
    }

    .link a:hover {
        text-decoration: underline;
    }
</style>

</head>

<body>

<div class="container">

    <h1>Connexion</h1>

    <%
        String error = (String) request.getAttribute("error");

        if (error != null) {
    %>
        <div class="error">
            <%= error %>
        </div>
    <%
        }
    %>

    <form action="login" method="post">

        <label>Login</label>
        <input type="text" name="login"
            value="<%= request.getAttribute("login") != null ? request.getAttribute("login") : "" %>"
            required>

        <label>Mot de passe</label>
        <input type="password" name="password" required>

        <button type="submit">Se connecter</button>

    </form>

    <div class="link">
        <a href="inscription">Créer un compte</a>
    </div>

</div>

</body>
</html>