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
    <title>Información del Curso</title>
    <style>
        .content-user {
            width: 140px;
            height: 140px;
            background-color: #d1d1d1;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
<div class="container">

    <c:if test="${curso != null}">
        <h1 class="fw-bold mt-2 mb-2">${curso.getNombreCurso()}</h1>

        <div class="row align-items-start mt-5">
            <div class="col-12 col-md-4 d-flex justify-content-center align-items-center flex-column">
                <div class="content-user rounded-circle mb-3">
                    <img width="100px" src="./imagenes/user.png" alt="usuario">
                </div>
                <p><strong>Nombre Docente:</strong> ${curso.getProfesorNombre()}</p>
            </div>
            <div class="col-12 col-md-4">
                <p><strong>Periodo Academico:</strong> ${curso.getPeriodoAcademico()}</p>
                <p><strong>Seccion:</strong> ${curso.getSeccion()}</p>
                <p><strong>Turno:</strong> ${curso.getTurno()}</p>
            </div>
            <div class="col-12 col-md-4">
                <p><strong>Silabo:</strong> ${curso.getSilabo()}</p>
            </div>
        </div>
    </c:if>

    <c:if test="${usuarioSesion.getIdRol() == 1}">
        <h1 class="fw-bold mt-2 mb-2">Lista de Estudiantes</h1>
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead class="table-dark text-center">
                <tr>
                    <th>ID Estudiante</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Correo</th>
                    <th>DNI</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="estudiante" items="${estudiantes}">
                    <tr class="text-center">
                        <td>${estudiante.getIdEstudiante()}</td>
                        <td>${estudiante.getNombre()}</td>
                        <td>${estudiante.getApellido()}</td>
                        <td>${estudiante.getCorreo()}</td>
                        <td>${estudiante.getDni()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>


    <c:if test="${curso == null}">
        <p>El curso no existe o no se pudo cargar la información.</p>
    </c:if>



</div>

</body>
</html>
