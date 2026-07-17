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
    <title>Agregar Curso</title>
</head>
<body>
<div class="container">
    <h1 class="fw-bold mt-2 mb-2">CURSOS
        <c:if test="${ usuarioSesion.getIdRol() == 1}">

            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                +
            </button>

            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalsecion">
                + Seccion
            </button>

            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal2">
                + Horario
            </button>

        </c:if>
    </h1>


    <!-- Modal -->
    <div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel2" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel2">Agregar Horario</h1>
                </div>
                <div class="modal-body">

                    <form action="controlador?menu=cursos" method="POST" accept-charset="UTF-8">
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
                            <label class="form-label">Dia</label>
                            <select name="txtdia" class="form-select" aria-label="Default select example">
                                <option selected>Seleccionar Dia</option>
                                <option value="1">Lunes</option>
                                <option value="2">Martes</option>
                                <option value="3">Miercoles</option>
                                <option value="4">Jueves</option>
                                <option value="5">Viernes</option>

                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Hora Inicio</label>
                            <select name="txthorainicio" class="form-select" aria-label="Default select example">
                                <option selected>Seleccionar hora</option>
                                <option value="08:00:00">08:00:00</option>
                                <option value="09:00:00">09:00:00</option>
                                <option value="10:00:00">10:00:00</option>
                                <option value="11:00:00">11:00:00</option>
                                <option value="12:00:00">12:00:00</option>
                                <option value="13:00:00">13:00:00</option>
                                <option value="14:00:00">14:00:00</option>
                                <option value="15:00:00">15:00:00</option>
                                <option value="16:00:00">16:00:00</option>
                                <option value="17:00:00">17:00:00</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Hora Fin</label>
                            <select name="txthorafin" class="form-select" aria-label="Default select example">
                                <option selected>Seleccionar hora</option>
                                <option value="09:00:00">09:00:00</option>
                                <option value="10:00:00">10:00:00</option>
                                <option value="11:00:00">11:00:00</option>
                                <option value="12:00:00">12:00:00</option>
                                <option value="13:00:00">13:00:00</option>
                                <option value="14:00:00">14:00:00</option>
                                <option value="15:00:00">15:00:00</option>
                                <option value="16:00:00">16:00:00</option>
                                <option value="17:00:00">17:00:00</option>
                                <option value="18:00:00">18:00:00</option>
                            </select>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Salir</button>
                            <input type="submit" name="accion" value="Asignar" class="btn btn-primary">
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Agregar Curso</h1>
                </div>
                <div class="modal-body">

                    <form action="controlador?menu=cursos" method="POST" accept-charset="UTF-8">
                        <div class="mb-3">
                            <label class="form-label">Profesor</label>

                            <select name="txtProfesorDirector" class="form-select" aria-label="Default select example">
                                <option selected>Seleccione Profesor</option>
                                <c:forEach var="profDi" items="${profesorDirector}">
                                    <option value="${profDi.getIdProfessor()}">${profDi.getProfessorNombre()} ${profDi.getProfessorApellido()}</option>
                                </c:forEach>
                            </select>

                        </div>

                        <div class="mb-3">
                            <label class="form-label">Turno</label>

                            <select name="txtTurnoDirector" class="form-select" aria-label="Default select example">
                                <option selected>Seleccione Turno</option>
                                <c:forEach var="turnDi" items="${turnoDirector}">
                                    <option value="${turnDi.getIdTurno()}">${turnDi.getTurno()}</option>
                                </c:forEach>
                            </select>

                        </div>

                        <div class="mb-3">
                            <label class="form-label">Seccion</label>

                            <select name="txtSeccionDirector" class="form-select" aria-label="Default select example">
                                <option selected>Seleccione Seccion</option>
                                <c:forEach var="seccDi" items="${seccionDirector}">
                                    <option value="${seccDi.getIdSeccion()}">${seccDi.getSeccion()}</option>
                                </c:forEach>
                            </select>

                        </div>

                        <div class="mb-3">
                            <label class="form-label">Silabo</label>
                            <input type="text" name="txtSilabo" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Nombre del Curso</label>
                            <input type="text" name="txtNombreCurso" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Periodo Academico</label>
                            <input type="text" name="txtPeriodoAca" class="form-control">
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


    <!-- Seccion -->
    <div class="modal fade" id="modalsecion" tabindex="-1" aria-labelledby="ModalSeccionLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="ModalSeccionLabel">Agregar Sección</h1>
                </div>
                <div class="modal-body">

                    <form action="controlador?menu=cursos" method="POST" accept-charset="UTF-8">

                        <div class="mb-3">
                            <label class="form-label">Seccion</label>
                            <input type="text" name="txtseccion" class="form-control">
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

    <!-- Turno -->
    <div class="modal fade" id="modalturno" tabindex="-1" aria-labelledby="ModalTurnoLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="ModalTurnoLabel">Agregar Turno</h1>
                </div>
                <div class="modal-body">

                    <form action="controlador?menu=cursos" method="POST" accept-charset="UTF-8">

                        <div class="mb-3">
                            <label class="form-label">Turno</label>
                            <input type="text" name="txtturno" class="form-control">
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


    <div class="container text-center">
        <div class="row gap-3">
<c:if test="${ usuarioSesion.getIdRol() != 2}">

            <c:forEach var="cur" items="${cursos}">
                <div class="col-lg-4 col-sm-6">
                    <a class="link-underline link-underline-opacity-0" href="controlador?menu=informacionCurso&idCurso=${cur.getIdCurso()}">
                        <div class="card">
                            <img src="https://cdn-3.expansion.mx/dims4/default/f93f66f/2147483647/strip/true/crop/6048x4032+0+0/resize/1200x800!/format/webp/quality/60/?url=https%3A%2F%2Fcdn-3.expansion.mx%2Fd9%2Fed%2F7e1caa7b48ba8d744c6d885ecd44%2Felearning.jpg" class="card-img-top" alt="image">
                            <div class="card-body">
                                <p class="fs-3 fw-bold">${cur.getNombreCurso()}</p>
                            </div>
                        </div>
                    </a>
                </div>
            </c:forEach>
</c:if>

            <c:forEach var="cur" items="${cursosProfesor}">
                <div class="col-lg-4 col-sm-6">
                    <a class="link-underline link-underline-opacity-0" href="controlador?menu=informacionCurso&idCurso=${cur.getIdCurso()}">
                        <div class="card">
                            <img src="https://cdn-3.expansion.mx/dims4/default/f93f66f/2147483647/strip/true/crop/6048x4032+0+0/resize/1200x800!/format/webp/quality/60/?url=https%3A%2F%2Fcdn-3.expansion.mx%2Fd9%2Fed%2F7e1caa7b48ba8d744c6d885ecd44%2Felearning.jpg" class="card-img-top" alt="image">
                            <div class="card-body">
                                <p class="fs-3 fw-bold">${cur.getNombreCurso()}</p>
                            </div>
                        </div>
                    </a>
                </div>
            </c:forEach>
<c:if test="${ usuarioSesion.getIdRol() == 1}">
            <c:forEach var="cur" items="${cursosDirector}">
                <div class="col-lg-4 col-sm-6">
                    <a class="link-underline link-underline-opacity-0" href="controlador?menu=informacionCurso&idCurso=${cur.getIdCurso()}">
                        <div class="card">
                            <img src="https://cdn-3.expansion.mx/dims4/default/f93f66f/2147483647/strip/true/crop/6048x4032+0+0/resize/1200x800!/format/webp/quality/60/?url=https%3A%2F%2Fcdn-3.expansion.mx%2Fd9%2Fed%2F7e1caa7b48ba8d744c6d885ecd44%2Felearning.jpg" class="card-img-top" alt="image">
                            <div class="card-body">
                                <p class="fs-3 fw-bold">${cur.getNombreCurso()}</p>
                            </div>
                        </div>
                    </a>
                </div>
            </c:forEach>

</c:if>

            <c:forEach var="entry" items="${cursosApoderado}">
                <div class="col">

                    <c:set var="primerEstudiante" value="${entry.value[0]}" />
                    <h4 class="fw-bold text-center"> ${primerEstudiante.nombreEstudiante} ${primerEstudiante.apellidoEstudiante}</h4>
                    <p>Grado: ${primerEstudiante.grado}</p>

                    <c:forEach var="cur" items="${entry.value}">
                        <div class="d-flex justify-content-center">
                            <a class="link-underline link-underline-opacity-0" href="controlador?menu=informacionCurso&idCurso=${cur.idCurso}">
                                <div class="card mt-5">
                                    <img src="https://cdn-3.expansion.mx/dims4/default/f93f66f/2147483647/strip/true/crop/6048x4032+0+0/resize/1200x800!/format/webp/quality/60/?url=https%3A%2F%2Fcdn-3.expansion.mx%2Fd9%2Fed%2F7e1caa7b48ba8d744c6d885ecd44%2Felearning.jpg" class="card-img-top" alt="image">
                                    <div class="card-body">
                                        <p class="fs-3 fw-bold">${cur.nombreCurso}</p>
                                        <p>Período Académico: ${cur.peridoAcademico}</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>



        </div>
    </div>

</div>
</body>
</html>
