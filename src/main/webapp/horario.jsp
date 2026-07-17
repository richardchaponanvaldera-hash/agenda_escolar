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
    <title>Horario Semanal</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .table-horario {
            width: 100%;
            table-layout: fixed;
        }
        .table-horario th, .table-horario td {
            text-align: center;
            vertical-align: middle;
        }
        .table-horario td {
            height: 100px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="fw-bold mt-2 mb-2">Horario Semanal</h1>
    <c:if test="${usuarioSesion.getIdRol() == 3}">
    <table class="table table-bordered table-horario">
        <thead class="table-dark">
        <tr>
            <th>Lunes</th>
            <th>Martes</th>
            <th>Miércoles</th>
            <th>Jueves</th>
            <th>Viernes</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <c:forEach var="dia" begin="1" end="5">
                <td>
                    <c:forEach var="hor" items="${horario}">
                        <c:if test="${hor.getDia() == dia}">
                            <div>
                                <strong>${hor.getCurso()}</strong><br>
                                    ${hor.getHoraInicio()} - ${hor.getHoraFin()}
                            </div>
                            <hr>
                        </c:if>
                    </c:forEach>
                </td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
    </c:if>

<c:if test="${ usuarioSesion.getIdRol() == 2}">

    <table class="table table-bordered table-horario">
        <thead class="table-dark">
        <tr>
            <th>Lunes</th>
            <th>Martes</th>
            <th>Miércoles</th>
            <th>Jueves</th>
            <th>Viernes</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <c:forEach var="dia" begin="1" end="5">
                <td>
                    <c:forEach var="hor" items="${horarioProfesor}">
                        <c:if test="${hor.getDia() == dia}">
                            <div>
                                <strong>${hor.getCurso()}</strong><br>
                                    ${hor.getHoraInicio()} - ${hor.getHoraFin()}
                            </div>
                            <hr>
                        </c:if>
                    </c:forEach>
                </td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
</c:if>



<c:if test="${usuarioSesion.getIdRol() == 4}">
    <c:forEach var="entry" items="${horarioApoderado}">
    <div class="table-responsive">
        <h3>${entry.value[0].nombreEstudiante} ${entry.value[0].apellidoEstudiante}</h3>
    <table class="table table-bordered table-horario">
        <thead class="table-dark">
        <tr>
            <th>Lunes</th>
            <th>Martes</th>
            <th>Miércoles</th>
            <th>Jueves</th>
            <th>Viernes</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <c:forEach var="dia" begin="1" end="5">
                <td>
                    <c:forEach var="hor" items="${entry.value}">
                        <c:if test="${hor.getDiaClase() == dia}">
                            <div>
                                <strong>${hor.getNombreCurso()}</strong><br>
                                    ${hor.getHoraInicio()} - ${hor.getHoraFin()}
                            </div>
                            <hr>
                        </c:if>
                    </c:forEach>
                </td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
    </div>
    </c:forEach>
</c:if>
</div>
</body>
</html>
