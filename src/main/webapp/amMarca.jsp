<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>AM de marcas</title>
</head>
<body>
	<%@include file="Template/header.html" %> <!-- ACA IMPORTAMOS EL HEADER -->
	<!-- ACA VA EL BODY -->
    <div id="div1">
        <c:if test="${marcaExistente == null}">
        <form action="marcas" method="post" style="width:50%">
            <div class="mb-3">
              <label for="Descripcion" class="form-label">Descripcion</label>
              <input type="text" class="form-control" id="Descripcion" name="descripcion" aria-describedby="DescripcionHelp">
              <div id="DescripcionHelp" class="form-text">Ingrese la descripcion de la Marca.</div>
            </div>
            <button type="submit" class="btn btn-dark">Crear</button>
          </form>
          </c:if>
         <c:if test="${marcaExistente != null}">
           <form action="marcas?id=<c:out value='${marcaExistente.id}'/>" method="post" style="width:50%">
            <div class="mb-3">
              <label for="Descripcion" class="form-label">Descripcion</label>
              <input type="text" class="form-control" id="Descripcion" name="descripcion" aria-describedby="DescripcionHelp" value="<c:out value='${marcaExistente.descripcion}' />">
              <div id="DescripcionHelp" class="form-text">Ingrese la descripcion de la Marca.</div>
            </div>
            <button type="submit" class="btn btn-dark">Editar</button>
          </form>
          </c:if>
    </div>
    <%@include file="Template/footer.html" %>
</body>
</html>