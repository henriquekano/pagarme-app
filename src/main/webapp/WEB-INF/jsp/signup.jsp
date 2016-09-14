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
			<label>name <input type="text" name="name">
			</label>
		</div>
		<div>
			<label>document_number <input type="text"
				name="document_number">
			</label>
		</div>
		<div>
			<label>email <input type="text" name="email">
			</label>
		</div>
		<div>
			<label>street <input type="text" name="street">
			</label>
		</div>
		<div>
			<label>neighborhood <input type="text" name="neighborhood">
			</label>
		</div>
		<div>
			<label>zipcode <input type="text" name="zipcode">
			</label>
		</div>
		<div>
			<label>street_number <input type="text" name="street_number">
			</label>
		</div>
		<div>
			<label>complementary <input type="text" name="complementary">
			</label>
		</div>
		<div>
			<label>phoneNumber <input type="text" name="phoneNumber">
			</label>
		</div>
		<div>
			<label>phoneDdd <input type="text" name="phoneDdd">
			</label>
		</div>
		<div>
			<input type="submit" value="Sign In" />
		</div>
	</form>
</body>
</html>