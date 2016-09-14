<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Spring Security Example </title>
    </head>
    <body>
    	<c:if test="${param.error}">
    		Login inválido
    	</c:if>
		<c:if test="${param.logout}">
    		Logout
    	</c:if>
        <form action="/login" method="POST">
            <div><label> User Name : <input type="text" name="username"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            <div><input type="submit" value="Sign In"/></div>
        </form>
        <a href="/login?signup">Nova conta</a>
    </body>
</html>