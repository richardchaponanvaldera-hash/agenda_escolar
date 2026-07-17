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
    <h1 class="fw-bold mt-2 mb-2">NOTAS</h1>

    <div class="container text-center">
        <div class="">
<c:if test="${usuarioSesion.getIdRol() == 3}">
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead class="table-dark text-center">
                    <tr>
                        <th>Curso</th>
                        <th>Nota 1</th>
                        <th>Nota 2</th>
                        <th>Nota 3</th>
                        <th>Nota 4</th>
                        <th>Promedio</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="nota" items="${notas}">
                        <tr class="text-center">
                            <td>${nota.nombreCurso}</td>
                            <td>${nota.calificacion1}</td>
                            <td>${nota.calificacion2}</td>
                            <td>${nota.calificacion3}</td>
                            <td>${nota.calificacion4}</td>
                            <td>${nota.promedio}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

</c:if>
            <c:if test="${usuarioSesion.getIdRol() == 2}">

                <div class="row gap-3">
            <c:forEach var="cur" items="${cursosProfesor}">


                <div class="col-lg-4 col-sm-6">
                    <a class="link-underline link-underline-opacity-0" href="controlador?menu=informacionNota&idCurso=${cur.getIdCurso()}">
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
        </div>

        <!-- Bloque para asistencia de los hijos del apoderado -->
        <c:if test="${usuarioSesion.getIdRol() == 4}">
            <c:forEach var="entry" items="${notasAgrupados}">
                <!-- Encabezado para el estudiante -->
                <div class="table-responsive">
                    <h3>${entry.value[0].nombreEstudiante} ${entry.value[0].apellidoEstudiante}</h3>
                    <table class="table table-bordered">
                        <thead class="table-dark text-center">
                        <tr>
                            <th>Curso</th>
                            <th>Calificación 1</th>
                            <th>Calificación 2</th>
                            <th>Calificación 3</th>
                            <th>Calificación 4</th>
                            <th>Promedio</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="notas" items="${entry.value}">
                            <tr>
                                <td>${notas.nombreCurso}</td>
                                <td>${notas.calificacion1}</td>
                                <td>${notas.calificacion2}</td>
                                <td>${notas.calificacion3}</td>
                                <td>${notas.calificacion4}</td>
                                <td>${notas.promedio}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </c:forEach>
        </c:if>


    </div>
</div>
</body>
</html>
