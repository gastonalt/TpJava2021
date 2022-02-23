<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Alta/Modificacion Componente</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body style="padding: 0; margin: 0;">
<%@include file="Template/header.html" %>
<div style="display:flex; height: 80vh; align-items: center; justify-content: center; padding-top: 15em;">
    <c:if test="${componenteExistente == null}">
    <form style="width:50%" action="componentes" method="post">
        <div class="mb-3">
          <label for="exampleInputEmail1" class="form-label">Descripcion</label>
          <input type="text" class="form-control" id="exampleInputEmail1" name="descripcion">
        </div>
          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Tamaño</label>
            <select  id="tamano" name="tamano" required>
				<c:forEach items="${tamanos}" var="tamano">
				<option value="<c:out value="${tamano.id}"/>">
				    <c:out value="${tamano.descripcion}"/>
				</option>
				</c:forEach>
				</select>   
            <!-- <input type="number" class="form-control" id="exampleInputEmail1" name="marca"> -->
          </div>
          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Socket</label>
            <select  id="socket" name="socket" required>
				<c:forEach items="${sockets}" var="socket">
				<option value="<c:out value="${socket.id}"/>">
				    <c:out value="${socket.descripcion}"/>
				</option>
				</c:forEach>
				</select>   
            <!-- <input type="number" class="form-control" id="exampleInputEmail1" name="marca"> -->
          </div>
          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Consumo</label>
            <input type="number" class="form-control" id="exampleInputEmail1" name="consumo">
          </div>
          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Precio</label>
            <input type="number" class="form-control" id="exampleInputEmail1" name="precio">
          </div>
          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Marca</label>
            <select  id="marca" name="marca" required>
				<c:forEach items="${marcas}" var="marca">
				<option value="<c:out value="${marca.id}"/>">
				    <c:out value="${marca.descripcion}"/>
				</option>
				</c:forEach>
				</select>   
            <!-- <input type="number" class="form-control" id="exampleInputEmail1" name="marca"> -->
          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Tipo</label>
            <select  id="tipo" name="tipo" required>
				<c:forEach items="${tipos}" var="tipo">
				<option value="<c:out value="${tipo.id}"/>">
				    <c:out value="${tipo.descripcion}"/>
				</option>
				</c:forEach>
				</select>   
            <!-- <input type="number" class="form-control" id="exampleInputEmail1" name="marca"> -->
          </div>
          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Stock</label>
            <input type="number" class="form-control" id="exampleInputEmail1" name="stock">
          </div>
        <button type="submit" class="btn btn-dark">Crear</button>
    </form>
    </c:if>
    <c:if test="${componenteExistente != null}">
    <form style="width:50%" action="componentes?id=<c:out value='${componenteExistente.id}'/>&borrado=<c:out value='${componenteExistente.borrado}'/>" method="post">
        <div class="mb-3">
          <label for="exampleInputEmail1" class="form-label">Descripcion</label>
          <input type="text" class="form-control" id="exampleInputEmail1" value="<c:out value='${componenteExistente.descripcion}' />" name="descripcion">
        </div>
          <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Tamaño</label>
   				<select  id="tamano" name="tamano">
				<option value="<c:out value="${componenteExistente.tamano.id}"/>">
				    <c:out value="${componenteExistente.tamano.descripcion}"/>
				</option>
				<c:forEach items="${tamanos}" var="tamano">
				<option value="<c:out value="${tamano.id}"/>">
				    <c:out value="${tamano.descripcion}"/>
				</option>
				</c:forEach>
				</select>
			</div>
          <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Socket</label>
   				<select  id="socket" name="socket">
				<option value="<c:out value="${componenteExistente.socket.id}"/>">
				    <c:out value="${componenteExistente.socket.descripcion}"/>
				</option>
				<c:forEach items="${sockets}" var="socket">
				<option value="<c:out value="${socket.id}"/>">
				    <c:out value="${socket.descripcion}"/>
				</option>
				</c:forEach>
				</select>
			</div>
          <div class="mb-3">
            <label for="exampleInputEmail1" class="form- label">Consumo</label>
            <input type="number" class="form-control" id="exampleInputEmail1" name="consumo" value="<c:out value='${componenteExistente.consumo}' />">
          </div>
          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Precio</label>
            <input type="number" class="form-control" id="exampleInputEmail1" name="precio" value="<c:out value='${componenteExistente.precio}' />">
          </div>
          <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Marca</label>
   				<select  id="marca" name="marca">
				<option value="<c:out value="${componenteExistente.marca.id}"/>">
				    <c:out value="${componenteExistente.marca.descripcion}"/>
				</option>
				<c:forEach items="${marcas}" var="marca">
				<option value="<c:out value="${marca.id}"/>">
				    <c:out value="${marca.descripcion}"/>
				</option>
				</c:forEach>
				</select>
			</div>	
          <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Tipo</label>
   				<select  id="tipo" name="tipo">
				<option value="<c:out value="${componenteExistente.tipo.id}"/>">
				    <c:out value="${componenteExistente.tipo.descripcion}"/>
				</option>
				<c:forEach items="${tipos}" var="tipo">
				<option value="<c:out value="${tipo.id}"/>">
				    <c:out value="${tipo.descripcion}"/>
				</option>
				</c:forEach>
				</select>
			</div>
          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Stock</label>
            <input type="number" class="form-control" id="exampleInputEmail1" name="stock" value="<c:out value='${componenteExistente.stock}' />">
          </div>
          <input type="hidden" class="form-control" id="exampleInputEmail1" name="borrado" value="<c:out value='${componenteExistente.borrado}' />">
        <button type="submit" class="btn btn-dark">Editar</button>
    </form>
    </c:if>
</div>
</body>
</html>