<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page by RB</title>
<link rel="stylesheet" href="reg.css">
</head>
<body>
	
	<form action="register" method="post">
        <h3>Register!</h3>
  
        <label>Username</label>
        <input
          type="text"
          class="inputText"
          name="name1"
          placeholder="Enter your name..."
        />

        <label>Email</label>
        <input
          type="email"
          class="inputText"
          name="email1"
          placeholder="Enter email..."
        />
  
        <label>Password</label>
        <input
          type="password"
          class="inputText"
          name="pass1"
          placeholder="Enter password..."
        />

        <label>City</label>
        <input
          type="text"
          class="inputText"
          name="city1"
          placeholder="Enter city name..."
        />
  
        <button>Register</button>
      </form>

</body>
</html>