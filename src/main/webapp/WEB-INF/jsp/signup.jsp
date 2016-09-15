<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
<title>Spring Security Example</title>
</head>
<body>
	<form action="/register" method="POST">
		<div>
			<label> User Name : <input type="text" name="user.username" />
			</label>
		</div>
		<div>
			<label> Password: <input type="password" name="user.password" />
			</label>
		</div>
		<div>
			<input type="submit" value="Sign In" />
		</div>
	</form>
</body>
</html>