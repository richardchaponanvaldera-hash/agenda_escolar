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
    <title>Apoderados</title>
</head>
<meta charset="UTF-8">
<body>
<div class="container">
    <h1 class="fw-bold mt-2 mb-2">Apoderados

        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
            +
        </button>
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal2">
            + Hijo
        </button>

    </h1>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Agregar Apoderado</h1>
                </div>
                <div class="modal-body">
                    <form action="controlador?menu=apoderados" method="POST" accept-charset="UTF-8">
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

    <!-- Modal -->
    <div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel2">Agregar Apoderado - Hijo</h1>
                </div>
                <div class="modal-body">
                    <form action="controlador?menu=apoderados" method="POST" accept-charset="UTF-8">
                        <div class="mb-3">
                            <label class="form-label">Apoderados</label>

                            <select name="txtapoderado" class="form-select" aria-label="Default select example">
                                <option selected>Seleccione Apoderado</option>
                                <c:forEach var="apod" items="${apodDirector}">
                                    <option value="${apod.getIdApoderado()}">${apod.getApoderadoNombre()} ${apod.getApoderadoApellido()}</option>
                                </c:forEach>
                            </select>

                        </div>

                        <div class="mb-3">
                            <label class="form-label">Hijo</label>

                            <select name="txtestudiante" class="form-select" aria-label="Default select example">
                                <option selected>Seleccione hijo</option>
                                <c:forEach var="estudiante" items="${estudiantesDirector}">
                                    <option value="${estudiante.getIdEstudiante()}">${estudiante.getEstudianteNombre()} ${estudiante.getEstudianteApellido()}</option>
                                </c:forEach>
                            </select>

                        </div>


                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Salir</button>
                            <input type="submit" name="accion" value="Agregar" class="btn btn-primary">
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
                <th>ID Apoderado</th>
                <th>DNI</th>
                <th>Correo</th>
                <th>Contraseña</th>
                <th>Apoderado</th>
                <th>Estudiante</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="apoderado" items="${apoderadosDirector}">
                <tr class="text-center">
                    <td>${apoderado.getIdApoderado()}</td>
                    <td>${apoderado.getDni()}</td>
                    <td>${apoderado.getCorreo()}</td>
                    <td>${apoderado.getContrasena()}</td>
                    <td>${apoderado.getApoderadoNombre()} ${apoderado.getApoderadoApellido()}</td>
                    <td>${apoderado.getEstudianteNombre()} ${apoderado.getEstudianteApellido()}</td>
                    <td>
                        <a class="btn btn-danger" href="controlador?menu=apoderados&accion=Eliminar&id=${apoderado.getIdApoderado()}"><i class="bi bi-trash"></i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
