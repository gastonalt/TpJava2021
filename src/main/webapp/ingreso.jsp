<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Ingreso</title>
<link rel="stylesheet" href="css/registro.css" type="text/css">
</head>
<body>
	<%@include file="Template/header.html" %> <!-- ACA IMPORTAMOS EL HEADER -->
	<!-- ACA VA EL BODY -->
	<div class="container">
		<c:if test="${usr!=null && pwd!=null}">
			<div class="alert alert-warning" role="alert">Usuario o contraseña inválida.</div>
		</c:if>
		<form style="width: 40%" class= "form1" action="loginFw" method="POST">
			<h2>Ingreso de un Usuario</h2>
		  <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Ingrese su usuario</label>
		    <input type="text" class="form-control" id="email" name="username" aria-describedby="emailHelp">
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">Ingrese su contraseña</label>
		    <input type="password" class="form-control" id="password1" name="password">
		  </div>
		  <button type="submit" class="btn btn-dark">Ingresar</button>
		</form>
		<form class="form1" action="signup" method="get">
				<button type="submit" class="btn btn-light">Aún no tengo una cuenta</button>
		</form>
	</div>
	
	<%@include file="Template/footer.html" %>
</body>
</html>