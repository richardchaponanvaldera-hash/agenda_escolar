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
  <title>Estudiante</title>
</head>
<body>
<div class="container">
  <h1 class="fw-bold mt-2 mb-2">Estudiantes
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
      +
    </button>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal2">
      + Curso
    </button>
  </h1>


  <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">Agregar Estudiante</h1>
        </div>
        <div class="modal-body">
          <form action="controlador?menu=estudiantes" method="POST" accept-charset="UTF-8">
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

            <div class="mb-3">
              <label class="form-label">Grado</label>

  <select name="txtgrado" class="form-select" aria-label="Default select example">
    <option selected>Seleccione Grado</option>
  <c:forEach var="grado" items="${gradoDirector}">
    <option value="${grado.getIdGrado()}">${grado.getGrado()}</option>
  </c:forEach>
    </select>

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


  <!-- Modal 2 -->
  <div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel2" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel2">Agregar Curso - Estudiante</h1>
        </div>
        <div class="modal-body">

          <form action="controlador?menu=estudiantes" method="POST" accept-charset="UTF-8">
            <div class="mb-3">
              <label class="form-label">Curso</label>

              <select name="txtcurso" class="form-select" aria-label="Default select example">
                <option selected>Seleccione Curso</option>
                <c:forEach var="cur" items="${cursosDirector}">
                  <option value="${cur.getIdCurso()}">${cur.getNombreCurso()}</option>
                </c:forEach>
              </select>

            </div>

            <div class="mb-3">
              <label class="form-label">Estudiante</label>

              <select name="txtestudiante" class="form-select" aria-label="Default select example">
                <option selected>Seleccione Estudiante</option>
                <c:forEach var="estudiantes" items="${estudiantesDirector}">
                  <option value="${estudiantes.getIdEstudiante()}">${estudiantes.getEstudianteNombre()} ${estudiantes.getEstudianteApellido()}</option>
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
        <th>ID Estudiante</th>
        <th>DNI</th>
        <th>Correo</th>
        <th>Contraseña</th>
        <th>Estudiante</th>
        <th>Apoderado</th>
        <th>Acciones</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="estudiante" items="${estudiantesDirector}">
        <tr class="text-center">
          <td>${estudiante.getIdEstudiante()}</td>
          <td>${estudiante.getDni()}</td>
          <td>${estudiante.getCorreo()}</td>
          <td>${estudiante.getContrasena()}</td>
          <td>${estudiante.getEstudianteNombre()} ${estudiante.getEstudianteApellido()}</td>
          <td>${estudiante.getApoderadoNombre()} ${estudiante.getApoderadoApellido()}</td>
          <td>
            <a class="btn btn-danger" href="controlador?menu=estudiantes&accion=Eliminar&id=${estudiante.getIdEstudiante()}"><i class="bi bi-trash"></i></a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

</body>
</html>
