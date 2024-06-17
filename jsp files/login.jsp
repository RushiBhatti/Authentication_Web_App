<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login page by RB</title>
<link rel="stylesheet" href="login.css">
</head>
<body>

<form action="login" method="post">
      <h3>Login!</h3>

      <label for="username">Email</label>
      <input
        type="email"
        class="inputText"
        name="email2"
        placeholder="Enter email..."
        id="username"
      />

      <label for="password">Password</label>
      <input
        type="password"
        class="inputText"
        name="pass2"
        placeholder="Enter password..."
        id="password"
      />

      <button>Login</button>
    </form>
	
</body>
</html>