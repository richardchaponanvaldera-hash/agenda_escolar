<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="bootstrap/bootstrap.jsp"%>


<html>
<head>
    <meta charset="UTF-8">
    <title>Comunicados</title>
</head>
<body>
<div class="container">
    <c:if test="${not empty mensaje}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${mensaje}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>
    <h1 class="fw-bold mt-2 mb-2">COMUNICADOS
<c:if test="${ usuarioSesion.getIdRol() == 1}">


    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
        +
    </button>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Agregar Comunicado</h1>
                </div>
                <div class="modal-body">
                    <form action="controlador?menu=comunicados" method="POST" accept-charset="UTF-8">

                    <textarea class="form-control" name="txtcontenido" rows="3"></textarea>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Salir</button>
                        <input type="submit" name="accion" value="Aceptar" class="btn btn-primary">
                    </div>
                    </form>

                </div>
            </div>
        </div>
    </div>


</c:if>
    </h1>
    <div class="px-3">

        <div class="card p-3 mb-2 bg-secondary-subtle text-secondary-emphasis">
            <c:forEach var="com" items="${comunicados}">
                <div class="card mt-2">
                    <div class="card-body">
                        <p>${com.getContenido()}</p>

                        <p class="text-end">${com.getFecha()}</p>
                        <c:if test="${ usuarioSesion.getIdRol() == 1}">

                        <a class="btn btn-danger" href="controlador?menu=comunicados&accion=Eliminar&id=${com.getIdcomunicado()}"><i class="bi bi-trash"></i></a>
                        </c:if>
                    </div>

                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
