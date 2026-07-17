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
    <h1 class="fw-bold mt-2 mb-2">Profesores
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
            +
        </button>
    </h1>


    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Agregar Profesor</h1>
                </div>
                <div class="modal-body">
                    <form action="controlador?menu=profesores" method="POST" accept-charset="UTF-8">
                        <div class="mb-3">
                            <label class="form-label">Nombre</label>
                            <input type="text" name="txtnombre" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Apellido</label>
                            <input type="text" name="txtapellido" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Correo</label>
                            <input type="text" name="txtcorreo" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Contraseña</label>
                            <input type="text" name="txtcontrasena" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">DNI</label>
                            <input type="text" name="txtdni" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Fecha de Nacimiento</label>
                            <input type="date" name="txtfechaNacimiento" class="form-control">
                        </div>


                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Salir</button>
                            <input type="submit" name="accion" value="Aceptar" class="btn btn-primary">
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>



    <div class="table-responsive">
        <table class="table table-bordered">
            <thead class="table-dark text-center">
            <tr>
                <th>ID Profesor</th>
                <th>DNI</th>
                <th>Correo</th>
                <th>Contraseña</th>
                <th>Profesor</th>
                <th>Curso</th>
                <th>Turno</th>
                <th>Sección</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="profesor" items="${profesoresDirector}">
                <tr class="text-center">
                    <td>${profesor.getIdProfessor()}</td>
                    <td>${profesor.getDni()}</td>
                    <td>${profesor.getCorreo()}</td>
                    <td>${profesor.getContrasena()}</td>
                    <td>${profesor.getProfessorNombre()} ${profesor.getProfessorApellido()}</td>
                    <td>${profesor.getNombreCurso()}</td>
                    <td>${profesor.getTurno()}</td>
                    <td>${profesor.getSeccion()}</td>
                    <td>
                        <a class="btn btn-danger" href="controlador?menu=profesores&accion=Eliminar&id=${profesor.getIdProfessor()}"><i class="bi bi-trash"></i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
