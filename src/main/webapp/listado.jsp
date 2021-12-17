<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Listado de usuarios</title>
</head>
<body>
	<%@include file="Template/header.html" %> <!-- ACA IMPORTAMOS EL HEADER -->
	<!-- ACA VA EL BODY -->
	
	<div class="table-responsive">
		<table class="table table-striped" style="width: 70%; margin: auto; border: 2px solid black; margin-top: 10px;">
			<h5>Lista de Usuarios</h5>
			<thead class="table-dark">
				<th style="width: 85%; text-align: center">Usuario</th>
				<th style="width: 15%; text-align: center">¿Es Administrador?</th>
				<th style="width: 15%; text-align: center">Editar</th>
				<th style="width: 15%; text-align: center">Eliminar</th>
			</thead>
			<c:forEach var="user" items="${listaUsuarios}">
				<tr style="text-align: center;">
					<td><c:out value="${user.username}" /></td>
					<td>
					<c:if test="${user.isAdmin == 'true'}"> <input type="checkbox" checked="true"/> </c:if>
					<c:if test="${user.isAdmin != 'true'}"> <input type="checkbox"/> </c:if>
					</td>
					<td><a href="edit?id=<c:out value='${user.id}'/>">Editar</a></td>
					<td><a href="delete?id=<c:out value='${user.id}'/>">Eliminar</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	
	
	<%@include file="Template/footer.html" %> <!-- ACA IMPORTAMOS EL FOOTER -->	
	</body>
</html>