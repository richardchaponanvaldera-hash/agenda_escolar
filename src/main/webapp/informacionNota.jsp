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
    <title>Title</title>
</head>
<body>
<div class="container">
    <h4 class="fw-bold mt-4 mb-2">Notas
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
            +
        </button>
    </h4>
    <hr>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Agregar Nota</h1>
                </div>
                <div class="modal-body">
                    <form action="controlador?menu=informacionNota" method="POST" accept-charset="UTF-8">
                        <input type="hidden" name="idCurso" value="${curso.getIdCurso()}">
                        <div class="mb-3">
                            <label class="form-label">Estudiante</label>
                            <select name="txtestudiante" class="form-select" aria-label="Default select example" required>
                                <option value="">Seleccione Estudiante</option>
                                <c:forEach var="estudiantenotas" items="${estudiantesNotasCurso}">
                                    <option value="${estudiantenotas.getIdEstudiante()}">${estudiantenotas.getNombre()} ${estudiantenotas.getApellido()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Calificación 1</label>
                            <input type="text" name="txtcalificacion1" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Calificación 2</label>
                            <input type="text" name="txtcalificacion2" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Calificación 3</label>
                            <input type="text" name="txtcalificacion3" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Calificación 4</label>
                            <input type="text" name="txtcalificacion4" class="form-control" required>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Salir</button>
                            <input type="submit" name="accion" value="Guardar" class="btn btn-primary">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>



        <input type="hidden" name="idCurso" value="${curso.getIdCurso()}">
        <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Calificación 1</th>
                <th>Calificación 2</th>
                <th>Calificación 3</th>
                <th>Calificación 4</th>
                <th>Promedio</th>
                <th>Acción</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="estudiante" items="${estudiantesNotas}">
                <tr>
                    <td>${estudiante.getNombre()}</td>
                    <td>${estudiante.getApellido()}</td>
                    <td>${estudiante.getCalificacion1()}</td>
                    <td>${estudiante.getCalificacion2()}</td>
                    <td>${estudiante.getCalificacion3()}</td>
                    <td>${estudiante.getCalificacion4()}</td>
                    <td>${estudiante.promedio}</td>
                    <td>
                        <a class="btn btn-danger" href="controlador?menu=informacionNota&accion=Eliminar&id=${estudiante.getIdCalificacion()}"><i class="bi bi-trash"></i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>

</div>

</body>
</html>
