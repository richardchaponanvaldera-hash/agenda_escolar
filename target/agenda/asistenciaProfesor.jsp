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
    <title>Title</title>
</head>
<body>
<div class="container">
    <h4 class="fw-bold mt-4 mb-2">Asistencia</h4>
    <hr>


    <form action="controlador?menu=asistencia&accion=guardar" method="POST" accept-charset="UTF-8">
        <input type="hidden" name="idCurso" value="${curso.getIdCurso()}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Asistencia</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="estudiante" items="${estudiantes}">
                <tr>
                    <td>${estudiante.getNombre()}</td>
                    <td>${estudiante.getApellido()}</td>
                    <td>
                        <div class="form-check form-switch">
                            <input class="form-check-input" type="checkbox"
                                   name="asistencia_${estudiante.getIdEstudiante()}"
                                   value="1" checked>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="text-center">
            <button type="submit" class="btn btn-primary">Guardar</button>
        </div>
    </form>
</div>

</body>
</html>
