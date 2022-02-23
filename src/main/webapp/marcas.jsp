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
			  No se puede borrar la marca porque esta en uso.
			</div>
		</c:if>

		<table class="table table-striped" style="width: 70%; margin: auto; border: 2px solid black; margin-top: 10px;">
			<h5>Lista de Marcas</h5>
			<thead class="table-dark">
				<th style="width: 20%; text-align: center">Id Marca</th>
				<th style="width: 50%; text-align: center">Descripcion</th>
				<th style="width: 15%; text-align: center">Editar</th>
				<th style="width: 15%; text-align: center">Eliminar</th>
			</thead>
			<c:forEach var="marca" items="${listaMarcas}">
				<tr style="text-align: center;">
					<td><c:out value="${marca.id}" /></td>
					<td><c:out value="${marca.descripcion}" /></td>
					<td><a href="marcas?editar=<c:out value='${marca.id}'/>">Editar</a></td>
					<td><a href="marcas?eliminar=<c:out value='${marca.id}'/>">Eliminar</a></td>
					<!-- 					<td>
					<c:if test="${user.isAdmin == 'true'}"> <input type="checkbox" checked="true"/> </c:if>
					<c:if test="${user.isAdmin != 'true'}"> <input type="checkbox"/> </c:if>
					</td>
					  -->
				</tr>
			</c:forEach>
		</table>
		
		<table class="table table-striped" style="width: 70%; margin: auto; border: 2px solid black; margin-top: 10px;">
			<h5>Lista de Marcas Borradas</h5>
			<thead class="table-dark">
				<th style="width: 20%; text-align: center">Id Marca</th>
				<th style="width: 50%; text-align: center">Descripcion</th>
				<th style="width: 15%; text-align: center">Editar</th>
				<th style="width: 15%; text-align: center">Restaurar</th>
			</thead>
			<c:forEach var="marca" items="${listaMarcasBorradas}">
				<tr style="text-align: center;">
					<td><c:out value="${marca.id}" /></td>
					<td><c:out value="${marca.descripcion}" /></td>
					<td><a href="marcas?editar=<c:out value='${marca.id}'/>">Editar</a></td>
					<td><a href="marcas?restaurar=<c:out value='${marca.id}'/>">Restaurar</a></td>
					<td>
					<!-- <c:if test="${user.isAdmin == 'true'}"> <input type="checkbox" checked="true"/> </c:if>
					<c:if test="${user.isAdmin != 'true'}"> <input type="checkbox"/> </c:if>
					</td>
					  -->
				</tr>
			</c:forEach>
		</table>

		<a href="./marcas?crear=1">Crear una nueva Marca</a>

</body>
</html>