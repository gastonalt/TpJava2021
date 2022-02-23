<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Listado de PC armadas</title>
</head>
<body>
	<%@include file="Template/header.html" %> <!-- ACA IMPORTAMOS EL HEADER -->
	<!-- ACA VA EL BODY -->
	<h1>Listado de PC armadas:</h1>
	
	<div class="table-responsive">
		<table class="table table-striped" style="width: 70%; margin: auto; border: 2px solid black; margin-top: 10px;">
			<h5>Lista de componentes:</h5>
			<thead class="table-dark">
				<th style="width: 15%; text-align: center">CPU</th>
				<th style="width: 85%; text-align: center">GPU</th>
				<th style="width: 15%; text-align: center">MEMORIA RAM</th>
				<th style="width: 15%; text-align: center">MOTHERBOARD</th>
				<th style="width: 15%; text-align: center">GABINETE</th>
				<th style="width: 10%; text-align: center">FUENTE</th>
			</thead>
			<c:forEach var="pc" items="${listaPcArmadas}">
				<tr style="text-align: center;">
					<td><c:out value="${pc.cpu.descripcion}" /></td>
					<td><c:out value="${pc.gpu.descripcion}" /></td>
					<td><c:out value="${pc.memoriadescripcion}" /></td>
					<td><c:out value="${pc.motherboard.descripcion}" /></td>
					<td><c:out value="${pc.gabinete.descripcion}" /></td>
					<td><c:out value="${pc.fuente.descripcion}" /></td>
					<td><a href="./componentes?id=<c:out value='${componente.id}'/>">Eliminar</a></td>
					<td><a href="./componentes?editar=<c:out value='${componente.id}'/>">Editar</a></td>					
				</tr>
			</c:forEach>
		</table>
	</div>

				<a href="./componentes?crear=1">Armar una nueva PC</a>

</body>
</html>