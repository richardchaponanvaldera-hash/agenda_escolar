<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="bootstrap/bootstrap.jsp"%>
<%
    String mensaje = (String) request.getAttribute("mensaje");
    if (mensaje != null) {
%>
<div class="alert alert-success">
    <%= mensaje %>
</div>
<%
    }
%>

<html>
<head>
    <title>Agenda</title>
</head>
<meta charset="UTF-8">
<body>
<div class="container">
    <h1 class="fw-bold mt-2 mb-2">
        AGENDA
<c:if test="${ usuarioSesion.getIdRol() == 2}">


    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
    + Agregar
    </button>
</c:if>
    </h1>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
    <div class="modal-header">
    <h1 class="modal-title fs-5" id="exampleModalLabel">Agregar Nota</h1>
    </div>
    <div class="modal-body">
        <form action="controlador?menu=agenda" method="POST" accept-charset="UTF-8">
            <div class="mb-3">
                <label class="form-label">Curso</label>

                <select name="txtcurso" class="form-select" aria-label="Default select example">
                    <option selected>Seleccione Curso</option>
                    <c:forEach var="cursos" items="${cursosProfesor}">
                        <option value="${cursos.getIdCurso()}">${cursos.getNombreCurso()}</option>
                    </c:forEach>
                </select>

            </div>

            <div class="mb-3">
                <label class="form-label">Contenido</label>

                <textarea class="form-control" name="txtcontenido" rows="3"></textarea>

            </div>

            <c:forEach var="pro" items="${profesorCurso}">
                <input type="hidden" name="txtprofesor" value="${pro.getIdProfesor()}">
            </c:forEach>


            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Salir</button>
                <input type="submit" name="accion" value="Aceptar" class="btn btn-primary">
            </div>
        </form>

    </div>
    </div>
    </div>
    </div>

    <div>
        <div class="card p-3 mb-2 bg-secondary-subtle text-secondary-emphasis">
            <c:forEach var="agen" items="${agenda}">
                <div class="card mt-2">
                    <div class="card-body">
                        <p class="text-center fs-5">${agen.getDescripcion()}</p>
                        <p class="text-end fw-semibold">${agen.getNombreCurso()}</p>
                        <p class="text-end">${agen.getFechaCreacion()}</p>
                    </div>
                </div>
            </c:forEach>


            <c:forEach var="agen" items="${agendaProfesor}">
                <div class="card mt-2">
                    <div class="card-body">
                        <p class="text-center fs-5">${agen.getDescripcion()}</p>
                        <p class="text-end fw-semibold">${agen.getNombreCurso()}</p>
                        <p class="text-end">${agen.getFechaCreacion()}</p>
                        <a class="btn btn-danger" href="controlador?menu=agenda&accion=Eliminar&id=${agen.getIdAgenda()}"><i class="bi bi-trash"></i></a>
                    </div>
                </div>
            </c:forEach>


                <c:forEach var="entry" items="${agendaAgrupados}">
                    <div class="table-responsive">
                        <h3>${entry.value[0].nombreEstudiante} ${entry.value[0].apellidoEstudiante}</h3>
                        <c:forEach var="agen" items="${entry.value}">
                            <div class="card mt-2">
                                <div class="card-body">
                                    <p class="text-center fs-5">${agen.getDescripcion()}</p>
                                    <p class="text-end fw-semibold">${agen.getNombreCurso()}</p>
                                    <p class="text-end">${agen.getFecha()}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>


<c:if test="${ usuarioSesion.getIdRol() == 1}">
    <c:forEach var="agen" items="${agendaDirector}">
        <div class="card mt-2">
            <div class="card-body">
                <p class="text-center fs-5">${agen.getDescripcion()}</p>
                <p class="text-end fw-semibold">${agen.getNombreCurso()}</p>
                <p class="text-end">${agen.getFecha()}</p>
            </div>
        </div>
    </c:forEach>
</c:if>


        </div>
    </div>
</div>

</body>
</html>
