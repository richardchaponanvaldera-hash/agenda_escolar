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
    <meta charset="UTF-8">
    <title>Asistencia</title>
    <style>

        .attendance-table {
            width: 100%;
            margin: 20px auto;
            background-color: #fff;
            color: #000;
            border-collapse: collapse;
        }
        .attendance-table th, .attendance-table td {
            text-align: center;
            border: 1px solid #ddd;
            padding: 10px;
        }
        .attendance-table th {
            background-color: #343a40; /* Cabecera oscura */
            color: #fff;
        }
        .attendance-table td.day-column {
            font-weight: bold;
            text-align: left;
            padding-left: 15px;
        }
        .circle {
            width: 20px;
            height: 20px;
            border-radius: 50%;
            display: inline-block;
        }
        .green {
            background-color: green;
        }
        .yellow {
            background-color: orange;
        }
        .red {
            background-color: red;
        }
        .white {
            background-color: #fff;
            border: 1px solid #ddd;
        }
    </style>
</head>

<body>
<div class="container">
    <h1 class="fw-bold mt-2 mb-2">ASISTENCIA</h1>

    <!-- Bloque para usuarios que no sean apoderados -->
<c:if test="${usuarioSesion.getIdRol() == 3}">
        <table class="attendance-table">
            <thead>
            <tr>
                <th>Curso</th>
                <th>Fecha</th>
                <th>Día</th>
                <th>Asistió</th>
                <th>Falta</th>
            </tr>
            </thead>
            <tbody>
            <!-- Mostrar asistencia del usuario -->
            <c:forEach var="asis" items="${asistencia}">
                <tr>
                    <td>${asis.nombreCurso}</td>
                    <td>${asis.fecha}</td>
                    <td class="day-column">
                        <c:choose>
                            <c:when test="${asis.dia == 1}">Lunes</c:when>
                            <c:when test="${asis.dia == 2}">Martes</c:when>
                            <c:when test="${asis.dia == 3}">Miércoles</c:when>
                            <c:when test="${asis.dia == 4}">Jueves</c:when>
                            <c:when test="${asis.dia == 5}">Viernes</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <div class="circle ${asis.estado == 1 ? 'green' : 'white'}"></div>
                    </td>
                    <td>
                        <div class="circle ${asis.estado == 0 ? 'red' : 'white'}"></div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
</c:if>
    <c:if test="${usuarioSesion.getIdRol() == 2}">

        <div class="row gap-3">
        <c:forEach var="cur" items="${cursosProfesor}">


                <div class="col-lg-4 col-sm-6">
                    <a class="link-underline link-underline-opacity-0" href="controlador?menu=asistenciaProfesor&idCurso=${cur.getIdCurso()}">
                        <div class="card">
                            <img src="https://cdn-3.expansion.mx/dims4/default/f93f66f/2147483647/strip/true/crop/6048x4032+0+0/resize/1200x800!/format/webp/quality/60/?url=https%3A%2F%2Fcdn-3.expansion.mx%2Fd9%2Fed%2F7e1caa7b48ba8d744c6d885ecd44%2Felearning.jpg" class="card-img-top" alt="image">
                            <div class="card-body">
                                <p class="fs-3 fw-bold">${cur.getNombreCurso()}</p>
                            </div>
                        </div>
                    </a>
                </div>


        </c:forEach>
        </div>
    </c:if>

    <!-- Bloque para asistencia de los hijos del apoderado -->
    <c:if test="${usuarioSesion.getIdRol() == 4}">
        <c:forEach var="entry" items="${asistenciaApoderado}">
            <!-- Encabezado para el estudiante -->
            <div class="student-section">
                <h3>${entry.value[0].nombreEstudiante} ${entry.value[0].apellidoEstudiante}</h3>
                <table class="attendance-table">
                    <thead>
                    <tr>
                        <th>Curso</th>
                        <th>Fecha</th>
                        <th>Día</th>
                        <th>Asistió</th>
                        <th>Falta</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Iterar los cursos y asistencia del estudiante -->
                    <c:forEach var="asistencia" items="${entry.value}">
                        <tr>
                            <td>${asistencia.nombreCurso}</td>
                            <td>${asistencia.fecha}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${asistencia.diaClase == 1}">Lunes</c:when>
                                    <c:when test="${asistencia.diaClase == 2}">Martes</c:when>
                                    <c:when test="${asistencia.diaClase == 3}">Miércoles</c:when>
                                    <c:when test="${asistencia.diaClase == 4}">Jueves</c:when>
                                    <c:when test="${asistencia.diaClase == 5}">Viernes</c:when>
                                    <c:otherwise>Sin Día</c:otherwise>
                                </c:choose>
                            </td>

                            <td>
                                <div class="circle ${asistencia.estado == 1 ? 'green' : 'white'}"></div>
                            </td>
                            <td>
                                <div class="circle ${asistencia.estado == 0 ? 'red' : 'white'}"></div>
                            </td>

                        </tr>
                    </c:forEach>
                    <!-- Si no hay registros de asistencia para este estudiante -->
                    <c:if test="${empty entry.value}">
                        <tr>
                            <td colspan="4">No hay registros de asistencia para este estudiante.</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </c:forEach>

        <!-- Si no hay estudiantes asociados -->
        <c:if test="${empty asistenciaApoderado}">
            <p>No hay registros de asistencia para los hijos asociados.</p>
        </c:if>
    </c:if>


</div>
</body>

</html>
