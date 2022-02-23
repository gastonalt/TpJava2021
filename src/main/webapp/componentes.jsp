<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Listado de componentes</title>
</head>
<body>
	<%@include file="Template/header.html" %> <!-- ACA IMPORTAMOS EL HEADER -->
	<!-- ACA VA EL BODY -->
	<h1>Listado de componentes:</h1>
	
	<div style=" display:flex; justify-content: center; ">
		<div  class="input-group mb-3"  style="width:50%;">
			<form action="componentes?buscar=true" method="get">
			  <input type="text" class="form-control" placeholder="Buscar un componente" aria-describedby="button-addon2" name="searchInput">
			  <input class="btn btn-outline-secondary" type="submit" id="button-addon2" value="Buscar"/>
		  	</form>
	  	</div>
	</div>	
	
	<div class="table-responsive">
		<table class="table table-striped" style="width: 70%; margin: auto; border: 2px solid black; margin-top: 10px;">
			<h5>Lista de componentes</h5>
			<thead class="table-dark">
				<th style="width: 15%; text-align: center">Marca</th>
				<th style="width: 15%; text-align: center">Tipo</th>
				<th style="width: 85%; text-align: center">Descripcion</th>
				<th style="width: 15%; text-align: center">Tamaño</th>
				<th style="width: 15%; text-align: center">Socket</th>
				<th style="width: 10%; text-align: center">Consumo</th>
				<th style="width: 10%; text-align: center">Stock</th>
				<th style="width: 15%; text-align: center">Precio</th>
				<th style="width: 10%; text-align: center">Borrar</th>
				<th style="width: 10%; text-align: center">Editar</th>
			</thead>
			<c:forEach var="componente" items="${listaComponentes}">
				<tr style="text-align: center;">
					<td><c:out value="${componente.marca.descripcion}" /></td>
					<td><c:out value="${componente.tipo.descripcion}" /></td>
					<td><c:out value="${componente.descripcion}" /></td>
					<td><c:out value="${componente.tamano.descripcion}" /></td>
					<td><c:out value="${componente.socket.descripcion}" /></td>
					<td><c:out value="${componente.consumo}" /></td>
					<td><c:out value="${componente.stock}" /></td>
					<td><c:out value="${componente.precio}" /></td>
					<td><a href="./componentes?id=<c:out value='${componente.id}'/>">Eliminar</a></td>
					<td><a href="./componentes?editar=<c:out value='${componente.id}'/>">Editar</a></td>					
				</tr>
			</c:forEach>
		</table>
		
		
		<table class="table table-striped" style="width: 70%; margin: auto; border: 2px solid black; margin-top: 10px;">
			<h5>Componentes sin stock</h5>
			<thead class="table-dark">
				<th style="width: 15%; text-align: center">Marca</th>
				<th style="width: 15%; text-align: center">Tipo</th>
				<th style="width: 85%; text-align: center">Descripcion</th>
				<th style="width: 15%; text-align: center">Tamaño</th>
				<th style="width: 15%; text-align: center">Socket</th>
				<th style="width: 10%; text-align: center">Consumo</th>
				<th style="width: 10%; text-align: center">Stock</th>
				<th style="width: 15%; text-align: center">Precio</th>
				<th style="width: 10%; text-align: center">Borrar</th>
				<th style="width: 10%; text-align: center">Editar</th>
			</thead>
			<c:forEach var="componente" items="${listaComponentesSS}">
				<tr style="text-align: center;">
					<td><c:out value="${componente.marca.descripcion}" /></td>
					<td><c:out value="${componente.tipo.descripcion}" /></td>
					<td><c:out value="${componente.descripcion}" /></td>
					<td><c:out value="${componente.tamano.descripcion}" /></td>
					<td><c:out value="${componente.socket.descripcion}" /></td>
					<td><c:out value="${componente.consumo}" /></td>
					<td><c:out value="${componente.stock}" /></td>
					<td><c:out value="${componente.precio}" /></td>
					<td><a href="./componentes?id=<c:out value='${componente.id}'/>">Eliminar</a></td>
					<td><a href="./componentes?editar=<c:out value='${componente.id}'/>">Editar</a></td>
					<!--
					<td>
					<c:if test="${user.isAdmin == 'true'}"> <input type="checkbox" checked="true"/> </c:if>
					<c:if test="${user.isAdmin != 'true'}"> <input type="checkbox"/> </c:if>
					</td>
					<td><a href="edit?id=<c:out value='${user.id}'/>">Editar</a></td>
					<td><a href="delete?id=<c:out value='${user.id}'/>">Eliminar</a></td>
					 -->
				</tr>
			</c:forEach>
		</table>
		
				<table class="table table-striped" style="width: 70%; margin: auto; border: 2px solid black; margin-top: 10px;">
			<h5>Componentes borrados</h5>
			<thead class="table-dark">
				<th style="width: 15%; text-align: center">Marca</th>
				<th style="width: 15%; text-align: center">Tipo</th>
				<th style="width: 85%; text-align: center">Descripcion</th>
				<th style="width: 15%; text-align: center">Tamaño</th>
				<th style="width: 15%; text-align: center">Socket</th>
				<th style="width: 10%; text-align: center">Consumo</th>
				<th style="width: 10%; text-align: center">Stock</th>
				<th style="width: 15%; text-align: center">Precio</th>
				<th style="width: 10%; text-align: center">Restaurar</th>
				<th style="width: 10%; text-align: center">Editar</th>
			</thead>
			<c:forEach var="componente" items="${listaComponentesBorrados}">
				<tr style="text-align: center;">
					<td><c:out value="${componente.marca.descripcion}" /></td>
					<td><c:out value="${componente.tipo.descripcion}" /></td>
					<td><c:out value="${componente.descripcion}" /></td>
					<td><c:out value="${componente.tamano.descripcion}" /></td>
					<td><c:out value="${componente.socket.descripcion}" /></td>
					<td><c:out value="${componente.consumo}" /></td>
					<td><c:out value="${componente.stock}" /></td>
					<td><c:out value="${componente.precio}" /></td>
					<td><a href="./componentes?restaurar=<c:out value='${componente.id}'/>">Restaurar</a></td>
					<td><a href="./componentes?editar=<c:out value='${componente.id}'/>">Editar</a></td>
					<!--
					<td>
					<c:if test="${user.isAdmin == 'true'}"> <input type="checkbox" checked="true"/> </c:if>
					<c:if test="${user.isAdmin != 'true'}"> <input type="checkbox"/> </c:if>
					</td>
					<td><a href="edit?id=<c:out value='${user.id}'/>">Editar</a></td>
					<td><a href="delete?id=<c:out value='${user.id}'/>">Eliminar</a></td>
					 -->
				</tr>
			</c:forEach>
		</table>
	</div>

				<a href="./componentes?crear=1">Crear un nuevo componente</a>

</body>
</html>