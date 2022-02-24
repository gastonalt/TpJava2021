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
    <form style="width:50%" action="pcarmada" method="post">


			<!-- CPU -->

          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">CPU</label>
            <select  id="cpu" name="cpu" required>
				<c:forEach items="${cpus}" var="cpu">
				<option value="<c:out value="${cpu.id}"/>">
				    <c:out value="${cpu.descripcion}"/>
				</option>
				</c:forEach>
				</select>   
          </div>
          
          
          			<!-- GPU -->

          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">GPU</label>
            <select  id="gpu" name="gpu" required>
				<c:forEach items="${gpus}" var="gpu">
				<option value="<c:out value="${gpu.id}"/>">
				    <c:out value="${gpu.descripcion}"/>
				</option>
				</c:forEach>
				</select>   
          </div>
          
          
          			<!-- MEMORIA -->

          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">MEMORIA</label>
            <select  id="memoria" name="memoria" required>
				<c:forEach items="${memos}" var="memo">
				<option value="<c:out value="${memo.id}"/>">
				    <c:out value="${memo.descripcion}"/>
				</option>
				</c:forEach>
				</select>   
          </div>


			<!-- MOTHER -->

          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">MOTHER</label>
            <select  id="gabinete" name="motherboard" required>
				<c:forEach items="${mothers}" var="motherboard">
				<option value="<c:out value="${motherboard.id}"/>">
				    <c:out value="${motherboard.descripcion}"/>
				</option>
				</c:forEach>
				</select>   
          </div>   
          
          			<!-- GABINETE -->

          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">GABINETE</label>
            <select  id="gabinete" name="gabinete" required>
				<c:forEach items="${gabinetes}" var="gabinete">
				<option value="<c:out value="${gabinete.id}"/>">
				    <c:out value="${gabinete.descripcion}"/>
				</option>
				</c:forEach>
				</select>   
          </div>
          
          			<!-- FUENTE -->

          <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">FUENTE</label>
            <select  id="tamano" name="fuente" required>
				<c:forEach items="${fuentes}" var="fuente">
				<option value="<c:out value="${fuente.id}"/>">
				    <c:out value="${fuente.descripcion}"/>
				</option>
				</c:forEach>
				</select>   
          </div>      


			<!-- OBSERVACIONES -->

 		<div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Observaciones</label>
            <input type="text" class="form-control" id="exampleInputEmail1" name="observaciones">
          </div>

         
        <button type="submit" class="btn btn-dark">Crear</button>
    </form>
</div>
</body>
</html>