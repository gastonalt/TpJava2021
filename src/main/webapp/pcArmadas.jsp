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
				<th style="width: 12.5%; text-align: center">CPU</th>
				<th style="width: 12.5%; text-align: center">GPU</th>
				<th style="width: 12.5%; text-align: center">MEMORIA RAM</th>
				<th style="width: 12.5%; text-align: center">MOTHERBOARD</th>
				<th style="width: 12.5%; text-align: center">GABINETE</th>
				<th style="width: 12.5%; text-align: center">FUENTE</th>
				<th style="width: 12.5%; text-align: center">OBSERVACIONES</th>
				<th style="width: 12.5%; text-align: center">ACCIONES</th>
			</thead>
			<c:forEach var="pc" items="${listaPcArmadas}">
				<tr style="text-align: center;">
					<td><c:out value="${pc.cpu.descripcion}" /></td>
					<td><c:out value="${pc.gpu.descripcion}" /></td>
					<td><c:out value="${pc.memoria.descripcion}" /></td>
					<td><c:out value="${pc.motherboard.descripcion}" /></td>
					<td><c:out value="${pc.gabinete.descripcion}" /></td>
					<td><c:out value="${pc.fuente.descripcion}" /></td>
					<td><c:out value="${pc.observaciones}" /></td>
					<td><a href="./pcarmada?id=<c:out value='${pc.id}'/>">BORRAR</a>
					<a href="./pcarmada?entregado=<c:out value='${pc.id}'/>">ENTREGADO</a></td>						
				</tr>
			</c:forEach>
		</table>
	
		<table class="table table-striped" style="width: 70%; margin: auto; border: 2px solid black; margin-top: 10px;">
			<h5>Componentes entregados:</h5>
			<thead class="table-dark">
				<th style="width: 15%; text-align: center">CPU</th>
				<th style="width: 15%; text-align: center">GPU</th>
				<th style="width: 15%; text-align: center">MEMORIA RAM</th>
				<th style="width: 15%; text-align: center">MOTHERBOARD</th>
				<th style="width: 15%; text-align: center">GABINETE</th>
				<th style="width: 15%; text-align: center">FUENTE</th>
				<th style="width: 15%; text-align: center">OBSERVACIONES</th>
			</thead>
			<c:forEach var="pc" items="${listaPcArmadasEntregadas}">
				<tr style="text-align: center;">
					<td><c:out value="${pc.cpu.descripcion}" /></td>
					<td><c:out value="${pc.gpu.descripcion}" /></td>
					<td><c:out value="${pc.memoria.descripcion}" /></td>
					<td><c:out value="${pc.motherboard.descripcion}" /></td>
					<td><c:out value="${pc.gabinete.descripcion}" /></td>
					<td><c:out value="${pc.fuente.descripcion}" /></td>
					<td><c:out value="${pc.observaciones}" /></td>			
				</tr>
			</c:forEach>
		</table>
	</div>
				<div style="display:block; width:100%">
				<a href="./pcarmada?crear=1">Armar una nueva PC</a>
				</div>
<%@include file="Template/footer.html" %>
</body>
</html>