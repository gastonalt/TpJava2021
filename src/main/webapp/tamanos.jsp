<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Listado de componentes</title>
</head>
<body>
	<%@include file="Template/header.html" %> <!-- ACA IMPORTAMOS EL HEADER -->
	<!-- ACA VA EL BODY -->

		<c:if test="${alertFKDelete == 'true'}">
			<div class="alert alert-danger" role="alert">
			  No se puede borrar la tamano porque esta en uso.
			</div>
		</c:if>

		<table class="table table-striped" style="width: 70%; margin: auto; border: 2px solid black; margin-top: 10px;">
			<h5>Lista de Tamanos</h5>
			<thead class="table-dark">
				<th style="width: 20%; text-align: center">Id Tamano</th>
				<th style="width: 50%; text-align: center">Descripcion</th>
				<th style="width: 15%; text-align: center">Editar</th>
				<th style="width: 15%; text-align: center">Eliminar</th>
			</thead>
			<c:forEach var="tamano" items="${listaTamanos}">
				<tr style="text-align: center;">
					<td><c:out value="${tamano.id}" /></td>
					<td><c:out value="${tamano.descripcion}" /></td>
					<td><a href="tamanos?editar=<c:out value='${tamano.id}'/>">Editar</a></td>
					<td><a href="tamanos?eliminar=<c:out value='${tamano.id}'/>">Eliminar</a></td>
					<td>
					<!-- <c:if test="${user.isAdmin == 'true'}"> <input type="checkbox" checked="true"/> </c:if>
					<c:if test="${user.isAdmin != 'true'}"> <input type="checkbox"/> </c:if>
					</td>
					  -->
				</tr>
			</c:forEach>
		</table>
		
		<table class="table table-striped" style="width: 70%; margin: auto; border: 2px solid black; margin-top: 10px;">
			<h5>Lista de Tamanos Borradas</h5>
			<thead class="table-dark">
				<th style="width: 20%; text-align: center">Id Tamano</th>
				<th style="width: 50%; text-align: center">Descripcion</th>
				<th style="width: 15%; text-align: center">Editar</th>
			</thead>
			<c:forEach var="tamano" items="${listaTamanosBorradas}">
				<tr style="text-align: center;">
					<td><c:out value="${tamano.id}" /></td>
					<td><c:out value="${tamano.descripcion}" /></td>
					<td><a href="tamanos?editar=<c:out value='${tamano.id}'/>">Editar</a></td>
					<td><a href="tamanos?restaurar=<c:out value='${tamano.id}'/>">Restaurar</a></td>
					<td>
					<!-- <c:if test="${user.isAdmin == 'true'}"> <input type="checkbox" checked="true"/> </c:if>
					<c:if test="${user.isAdmin != 'true'}"> <input type="checkbox"/> </c:if>
					</td>
					  -->
				</tr>
			</c:forEach>
		</table>

		<a href="./tamanos?crear=1">Crear un nuevo Tamano</a>

</body>
</html>