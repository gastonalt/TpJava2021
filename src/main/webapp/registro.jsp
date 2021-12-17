<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Registro</title>
<!-- <link href="css/registro.css"> </link> -->
<link rel="stylesheet" href="css/registro.css" type="text/css">
</head>
<body>
	<%@include file="Template/header.html" %> <!-- ACA IMPORTAMOS EL HEADER -->
	<!-- ACA VA EL BODY -->
	<div class="container">
	<c:if test="${user != null}">
		<form style="width: 40%" class= "form1" action="update" method="post">
			<h2>Editar un usuario</h2>
			<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
		  <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Editar usuario</label>
		    <input type="text" class="form-control" id="email" aria-describedby="emailHelp"  name="username" value="<c:out value='${user.username}' />">
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">Editar contraseña</label>
		    <input type="password" class="form-control" id="password1" name="password" value="<c:out value='${user.password}' />">
		  </div>
		   <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">Ingrese la contraseña una vez más</label>
		    <input type="password" class="form-control" id="password2" name="repeatpassword" value="<c:out value='${user.password}' />">
		  </div>
		  <div class="mb-3 form-check">
		    <input type="checkbox" class="form-check-input" id="exampleCheck1" name="isAdmin">
		    <label class="form-check-label" for="exampleCheck1" class = "check">Soy administrador</label>
		    <p>Estado actual: <b><c:out value='${user.isAdmin}' /></b></p>
		  </div>
		  <button type="submit" class="btn btn-dark">Guardar</button>
	</c:if>
	<c:if test="${user == null}">
	<form style="width: 40%" class= "form1" action="signUpFw" method="post">
			<h2>Registro de nuevo Usuario</h2>
		  <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Ingrese su usuario</label>
		    <input type="text" class="form-control" id="email" aria-describedby="emailHelp"  name="username">
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">Ingrese una contraseña</label>
		    <input type="password" class="form-control" id="password1" name="password">
		  </div>
		   <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">Ingrese la contraseña una vez más</label>
		    <input type="password" class="form-control" id="password2" name="repeatpassword">
		  </div>
		  <div class="mb-3 form-check">
		    <input type="checkbox" class="form-check-input" id="exampleCheck1" name="isAdmin">
		    <label class="form-check-label" for="exampleCheck1" class = "check">Soy administrador</label>
		  </div>
		  	<button type="submit" class="btn btn-dark">Registrarme</button>
		  </c:if>
		</form>
		<form class="form1" action="login" method="get">
			<div>
				<button type="submit" class="btn btn-light">Ya tengo una cuenta</button>
			</div>
		</form>
	</div>

	<%@include file="Template/footer.html" %><!-- Footer-->
</body>
</html>