package controlador;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.*;
import modelo.dao.*;

import java.io.IOException;
import java.util.*;

public class controlador extends HttpServlet {
    usuarioDAO usuariodao = new usuarioDAO();
    usuario usuario = new usuario();

    cursoDAO cursodao = new cursoDAO();
    curso curso = new curso();

    notasDAO notasdao = new notasDAO();
    notas nota = new notas();

    comunicados comunicados = new comunicados();
    comunicadosDAO comunicadosdao = new comunicadosDAO();

    agenda agenda = new agenda();
    agendaDAO agendadao = new agendaDAO();

    horario horario = new horario();
    horarioDAO horariodao = new horarioDAO();

    asistencia asistencia = new asistencia();
    asistenciaDAO asistenciadao = new asistenciaDAO();

    estudiante estudiante = new estudiante();
    estudianteDAO estudiantedao = new estudianteDAO();

    apoderado apoderado = new apoderado();
    apoderadoDAO apoderadodao = new apoderadoDAO();

    director director = new director();
    directorDAO directordao = new directorDAO();

    private List listarCusos;
    private List listarNotas;
    private List listarComunicados;
    private List listarAgenda;
    private List listarHorario;
    private List listarAsistencia;
    private List listarCursosProfesor;
    private List listarAgendaProfesor;
    private List listarCursosDirector;
    private List listarEstudiantesDirector;
    private List listarApoderadosDirector;
    private List listarProfesoresDirector;
    private List listarAgendaDirector;
    private List listarGradoDirector;
    private List listarApoderadorDirector;

    private List listarSeccionDirector;
    private List listarProfesorDirector;
    private List listarTurnoDirector;
    private List listarProfesorPorUsuario;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        System.out.println("menu: "+ menu);
        String accion = request.getParameter("accion");
        HttpSession session = request.getSession();
        usuario usuarioSesion = (usuario) session.getAttribute("usuarioSesion");

        if (menu != null && menu.equals("menu")) {
            if (usuarioSesion != null) {
                request.setAttribute("usuarioNombre", usuarioSesion); // Poner el usuario en el request
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            } else {
                response.sendRedirect("index.jsp"); // Si no hay usuario en sesión, redirige al login
            }
        }
        if (menu != null && menu.equals("cursos")){
            if ("listar".equals(accion)) {
                listarCusos = cursodao.listaCursos(usuarioSesion.getIdUsuario());
                request.setAttribute("cursos", listarCusos);
                listarCursosProfesor = cursodao.listaCursosPorProfesor(usuarioSesion.getIdUsuario());
                request.setAttribute("cursosProfesor", listarCursosProfesor);

                listarCursosDirector = directordao.listaCursosDirector();
                request.setAttribute("cursosDirector", listarCursosDirector);


                listarSeccionDirector = directordao.listaSeccionDirector();
                request.setAttribute("seccionDirector", listarSeccionDirector);

                listarProfesorDirector = directordao.listaProfesorDirector();
                request.setAttribute("profesorDirector", listarProfesorDirector);

                listarTurnoDirector = directordao.listaTurnoDirector();
                request.setAttribute("turnoDirector", listarTurnoDirector);

                // Agrupar hijos por apoderado y verificar en consola
                Map<Integer, List<apoderado>> hijosAgrupados = apoderadodao.listaHijosAgrupadosPorApoderado(usuarioSesion.getIdUsuario());
                for (Map.Entry<Integer, List<apoderado>> entry : hijosAgrupados.entrySet()) {
                    System.out.println("ID Hijo: " + entry.getKey());
                    List<apoderado> estudiantes = entry.getValue();

                    if (!estudiantes.isEmpty()) {
                        apoderado primerEstudiante = estudiantes.get(0);
                        System.out.println("  Nombre Estudiante: " + primerEstudiante.getNombreEstudiante() + " " + primerEstudiante.getApellidoEstudiante());
                    }

                    for (apoderado hijo : estudiantes) {
                        System.out.println("  Curso: " + hijo.getNombreCurso());
                        System.out.println("  Período Académico: " + hijo.getPeridoAcademico());
                    }
                    System.out.println("------------------------------");
                }

                // Enviar datos al JSP
                request.setAttribute("cursosApoderado", hijosAgrupados);
            }

            if ("Agregar".equals(accion)) {
                String idProfesor = request.getParameter("txtProfesorDirector");
                String idTurno = request.getParameter("txtTurnoDirector");
                String idSeccion = request.getParameter("txtSeccionDirector");
                String silabo = request.getParameter("txtSilabo");
                String nombreCurso = request.getParameter("txtNombreCurso");
                String periodoAcad = request.getParameter("txtPeriodoAca");

                director.setIdProfessor(Integer.parseInt(idProfesor));
                director.setIdTurno(Integer.parseInt(idTurno));
                director.setIdSeccion(Integer.parseInt(idSeccion));
                director.setSilabo(silabo);
                director.setNombreCurso(nombreCurso);
                director.setPeriodoAcademico(periodoAcad);


                directordao.agregaCurso(director);

                request.setAttribute("mensaje", "Curso agregado con éxito");
            }
            request.getRequestDispatcher("cursos.jsp").forward(request, response);

            if ("Guardar".equals(accion)) {
                String seccion = request.getParameter("txtseccion");

                director.setSeccion(seccion);

                directordao.agregaSeccion(director);

                request.setAttribute("mensaje", "Seccion agregado con éxito");
            }
            request.getRequestDispatcher("cursos.jsp").forward(request, response);

            if ("Aceptar".equals(accion)) {
                String turno = request.getParameter("txtturno");

                director.setTurno(turno);

                directordao.agregaTurno(director);

                request.setAttribute("mensaje", "Turno agregado con éxito");
            }
            request.getRequestDispatcher("cursos.jsp").forward(request, response);
            if ("Asignar".equals(accion)) {
                String idcurso = request.getParameter("txtcurso");
                String dia = request.getParameter("txtdia");
                String horainicio = request.getParameter("txthorainicio");
                String horafin = request.getParameter("txthorafin");

                director.setIdCurso(Integer.parseInt(idcurso));
                director.setDia(Integer.parseInt(dia));
                director.setHoraInicio(horainicio);
                director.setHoraFin(horafin);
                director.setMes(11);
                director.setYear(2024);

                directordao.agregaHorario(director);

                request.setAttribute("mensaje", "Turno agregado con éxito");
            }

            request.getRequestDispatcher("cursos.jsp").forward(request, response);
        }
        if (menu != null && menu.equals("informacionCurso")) {
            String idCursoParam = request.getParameter("idCurso");

            if (idCursoParam != null) {
                try {
                    int idCurso = Integer.parseInt(idCursoParam);
                    informacionCurso cursoInfo = cursodao.obtenerCursoPorId(idCurso);
                    System.out.println("Curso cargado: " + cursoInfo.getNombreCurso());
                    request.setAttribute("curso", cursoInfo);

                    List<estudiante> estudiantes = estudiantedao.listaEstudiantesPorCurso(idCurso);
                    request.setAttribute("estudiantes", estudiantes);

                } catch (NumberFormatException e) {
                    System.out.println("Error: ID de curso no válido");
                }
            }

            request.getRequestDispatcher("informacionCurso.jsp").forward(request, response);
        }
        if (menu != null && menu.equals("asistenciaProfesor")) {
            String idCursoParam = request.getParameter("idCurso");

            if (idCursoParam != null) {
                try {
                    int idCurso = Integer.parseInt(idCursoParam);
                    informacionCurso cursoInfo = cursodao.obtenerCursoPorId(idCurso);
                    System.out.println("Curso cargado: " + cursoInfo.getNombreCurso());
                    request.setAttribute("curso", cursoInfo);

                    List<estudiante> estudiantes = estudiantedao.listaEstudiantesPorCurso(idCurso);
                    request.setAttribute("estudiantes", estudiantes);

                } catch (NumberFormatException e) {
                    System.out.println("Error: ID de curso no válido");
                }
            }

            request.getRequestDispatcher("asistenciaProfesor.jsp").forward(request, response);
        }
        if (menu != null && menu.equals("asistencia")){
            if ("listar".equals(accion)) {
                listarAsistencia = asistenciadao.listaAsistencia(usuarioSesion.getIdUsuario());
                request.setAttribute("asistencia", listarAsistencia);

                listarCursosProfesor = cursodao.listaCursosPorProfesor(usuarioSesion.getIdUsuario());
                request.setAttribute("cursosProfesor", listarCursosProfesor);

                Map<Integer, List<apoderado>> estudiantesAgrupados = apoderadodao.listaAsistenciaApoderado(usuarioSesion.getIdUsuario());
                request.setAttribute("asistenciaApoderado", estudiantesAgrupados);

            }
            if ("guardar".equals(accion)) {
                String idCursoParam = request.getParameter("idCurso");
                if (idCursoParam != null) {
                    try {
                        int idCurso = Integer.parseInt(idCursoParam);
                        Enumeration<String> parameterNames = request.getParameterNames();
                        List<asistencia> asistencias = new ArrayList<>();

                        while (parameterNames.hasMoreElements()) {
                            String paramName = parameterNames.nextElement();

                            if (paramName.startsWith("asistencia_")) {
                                int idEstudiante = Integer.parseInt(paramName.split("_")[1]);
                                boolean estado = "1".equals(request.getParameter(paramName));

                                asistencia nuevaAsistencia = new asistencia();
                                nuevaAsistencia.setIdEstudiante(idEstudiante);
                                nuevaAsistencia.setIdCurso(idCurso); // Asigna el idCurso
                                nuevaAsistencia.setEstado(estado ? 1 : 0);
                                nuevaAsistencia.setFecha(java.time.LocalDate.now().toString()); // Fecha actual
                                asistencias.add(nuevaAsistencia);
                            }
                        }


                        asistenciadao.guardarAsistencias(asistencias);

                    } catch (NumberFormatException e) {
                        System.out.println("Error: ID de curso no válido");
                    }
                }
            }

            request.getRequestDispatcher("asistencia.jsp").forward(request, response);
        }
        if (menu != null && menu.equals("notas")){
            if ("listar".equals(accion)) {
                listarNotas = notasdao.listaNotas(usuarioSesion.getIdUsuario());
                request.setAttribute("notas", listarNotas);
                listarCursosProfesor = cursodao.listaCursosPorProfesor(usuarioSesion.getIdUsuario());
                request.setAttribute("cursosProfesor", listarCursosProfesor);

                Map<Integer, List<apoderado>> notasAgrupados = apoderadodao.listaNotasApoderado(usuarioSesion.getIdUsuario());
                request.setAttribute("notasAgrupados", notasAgrupados);
            }

            request.getRequestDispatcher("notas.jsp").forward(request, response);
        }
        if (menu != null && menu.equals("informacionNota")) {
            String idCursoParam = request.getParameter("idCurso");


            if (idCursoParam != null) {
                try {
                    int idCurso = Integer.parseInt(idCursoParam);
                    informacionCurso cursoInfo = cursodao.obtenerCursoPorId(idCurso);
                    request.setAttribute("curso", cursoInfo);
                    List<notas> estudiantesNotas = notasdao.listaEstudiantesConNotasPorCurso(idCurso);
                    request.setAttribute("estudiantesNotas", estudiantesNotas);

                    List<notas> estudiantesNotasCurso = notasdao.listaEstudiantesPorCurso(idCurso);
                    request.setAttribute("estudiantesNotasCurso", estudiantesNotasCurso);

                } catch (NumberFormatException e) {
                    System.out.println("Error: ID de curso no válido");
                }
            }

            if ("Guardar".equals(accion)) {

                String idEstudiante = request.getParameter("txtestudiante");
                String calificacion1 = request.getParameter("txtcalificacion1");
                String calificacion2 = request.getParameter("txtcalificacion2");
                String calificacion3 = request.getParameter("txtcalificacion3");
                String calificacion4 = request.getParameter("txtcalificacion4");
                String idCursoParam2 = request.getParameter("idCurso");
                int idCurso = Integer.parseInt(idCursoParam2);
                nota.setIdEstudiante(Integer.parseInt(idEstudiante));
                nota.setCalificacion1(Double.parseDouble(calificacion1));
                nota.setCalificacion2(Double.parseDouble(calificacion2));
                nota.setCalificacion3(Double.parseDouble(calificacion3));
                nota.setCalificacion4(Double.parseDouble(calificacion4));
                nota.setIdCurso(idCurso);

                notasdao.agregarNota(nota);

                request.setAttribute("mensaje", "Nota agregada con éxito");
            }
            if ("Eliminar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));

                notasdao.eliminarNota(id);
                request.setAttribute("mensaje", "Contenido Eliminado con éxito");
            }

            request.getRequestDispatcher("informacionNota.jsp").forward(request, response);
        }
        if (menu != null && menu.equals("horario")){
            if ("listar".equals(accion)) {
                listarHorario = horariodao.listaHorario(usuarioSesion.getIdUsuario());
                request.setAttribute("horario", listarHorario);
                listarAgendaProfesor = horariodao.listaHorarioProfesor(usuarioSesion.getIdUsuario());
                request.setAttribute("horarioProfesor", listarAgendaProfesor);

                Map<Integer, List<apoderado>> horarioApoderado = apoderadodao.listaHorarioApoderado(usuarioSesion.getIdUsuario());
                request.setAttribute("horarioApoderado", horarioApoderado);
            }
            request.getRequestDispatcher("horario.jsp").forward(request, response);
        }
        if (menu != null && menu.equals("agenda")){
            if ("listar".equals(accion)) {
                listarAgenda = agendadao.listaAgenda(usuarioSesion.getIdUsuario());
                request.setAttribute("agenda", listarAgenda);
                listarAgendaProfesor = agendadao.listaAgendaProfesor(usuarioSesion.getIdUsuario());
                request.setAttribute("agendaProfesor", listarAgendaProfesor);

                listarProfesorPorUsuario = agendadao.listarProfesorPorUsuario(usuarioSesion.getIdUsuario());
                request.setAttribute("profesorCurso", listarProfesorPorUsuario);

                listarCursosProfesor = cursodao.listaCursosPorProfesor(usuarioSesion.getIdUsuario());
                request.setAttribute("cursosProfesor", listarCursosProfesor);

                listarAgendaDirector = directordao.listaAgendaDirector();
                request.setAttribute("agendaDirector", listarAgendaDirector);

                Map<Integer, List<apoderado>> agendaAgrupados = apoderadodao.listaAgendaApoderado(usuarioSesion.getIdUsuario());
                request.setAttribute("agendaAgrupados", agendaAgrupados);
            }
            if ("Aceptar".equals(accion)) {
                request.setCharacterEncoding("UTF-8");
                String idCurso = request.getParameter("txtcurso");
                String contenido = request.getParameter("txtcontenido");
                String idProfesor = request.getParameter("txtprofesor");

                agenda.setIdProfesor(Integer.parseInt(idProfesor));
                agenda.setIdCurso(Integer.parseInt(idCurso));
                agenda.setDescripcion(contenido);
                agenda.setEstado(1);

                agendadao.agregaAgenda(agenda);

                request.setAttribute("mensaje", "Agenda agregado con éxito");
            }
            if ("Eliminar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));

                agendadao.eliminarAgenda(id);
                request.setAttribute("mensaje", "Contenido Eliminado con éxito");
            }
            request.getRequestDispatcher("agenda.jsp").forward(request, response);
        }
        if (menu != null && menu.equals("comunicados")){
            if ("listar".equals(accion)) {
                listarComunicados = comunicadosdao.listaComunicados();
                request.setAttribute("comunicados", listarComunicados);
            }
            if ("Aceptar".equals(accion)) {
                request.setCharacterEncoding("UTF-8");
                String contenido = request.getParameter("txtcontenido");

                comunicados.setContenido(contenido);

                comunicadosdao.agregaComunicado(comunicados);
                request.setAttribute("mensaje", "Contenido agregado con éxito");
            }
            if ("Eliminar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));

                comunicadosdao.eliminarComunicado(id);
                request.setAttribute("mensaje", "Contenido Eliminado con éxito");
            }
            request.getRequestDispatcher("comunicados.jsp").forward(request, response);
        }
        if (menu != null && menu.equals("profesores")){
            if ("listar".equals(accion)) {
                listarProfesoresDirector = directordao.listaProfesoresDirector();
                request.setAttribute("profesoresDirector", listarProfesoresDirector);
            }
            if ("Aceptar".equals(accion)) {
                String nombre = request.getParameter("txtnombre");
                String apellido = request.getParameter("txtapellido");
                String correo = request.getParameter("txtcorreo");
                String contrasena = request.getParameter("txtcontrasena");
                String dni = request.getParameter("txtdni");
                String fechaNacimiento = request.getParameter("txtfechaNacimiento");

                director.setProfessorNombre(nombre);
                director.setProfessorApellido(apellido);
                director.setCorreo(correo);
                director.setContrasena(contrasena);
                director.setDni(dni);
                director.setFecha(fechaNacimiento);
                director.setIdRol(2);


                directordao.agregarProfesor(director);

                // Enviar un mensaje según el resultado
                request.setAttribute("mensaje", "Profesor Agregado con éxito");
            }
            if ("Eliminar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));

                directordao.eliminarProfesor(id);
                request.setAttribute("mensaje", "Profesor Eliminado con éxito");
            }
            request.getRequestDispatcher("profesores.jsp").forward(request, response);
        }
        if (menu != null && menu.equals("apoderados")){
            if ("listar".equals(accion)) {
                listarApoderadosDirector = directordao.listaApoderadosDirector();
                request.setAttribute("apoderadosDirector", listarApoderadosDirector);

                listarApoderadorDirector = directordao.listaApodDirector();
                request.setAttribute("apodDirector", listarApoderadorDirector);

                listarEstudiantesDirector = directordao.listaEstudiantesDirector();
                request.setAttribute("estudiantesDirector", listarEstudiantesDirector);
            }
            if ("Aceptar".equals(accion)) {
                String nombre = request.getParameter("txtnombre");
                String apellido = request.getParameter("txtapellido");
                String correo = request.getParameter("txtcorreo");
                String contrasena = request.getParameter("txtcontrasena");
                String dni = request.getParameter("txtdni");
                String fechaNacimiento = request.getParameter("txtfechaNacimiento");
                String estudiantehijo = request.getParameter("txtestudiante");

                director.setApoderadoNombre(nombre);
                director.setApoderadoApellido(apellido);
                director.setCorreo(correo);
                director.setContrasena(contrasena);
                director.setDni(dni);
                director.setFecha(fechaNacimiento);
                director.setIdRol(4);


                directordao.agregarApoderado(director);

                // Enviar un mensaje según el resultado
                request.setAttribute("mensaje", "Apoderado Agregado con éxito");
            }
            request.getRequestDispatcher("apoderados.jsp").forward(request, response);
            if ("Agregar".equals(accion)) {
                String idApoderado = request.getParameter("txtapoderado");
                String idEstudiante = request.getParameter("txtestudiante");


                director.setIdEstudiante(Integer.parseInt(idEstudiante));
                director.setIdApoderado(Integer.parseInt(idApoderado));


                directordao.agregarApoderadoHijo(director);

                // Enviar un mensaje según el resultado
                request.setAttribute("mensaje", "Hijo agregado con éxito");
            }
            request.getRequestDispatcher("apoderados.jsp").forward(request, response);
            if ("Eliminar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));

                directordao.eliminarApoderado(id);
                request.setAttribute("mensaje", "Apoderado Eliminado con éxito");
            }

            request.getRequestDispatcher("apoderados.jsp").forward(request, response);
        }
        if (menu != null && menu.equals("estudiantes")){
            if ("listar".equals(accion)) {
                listarEstudiantesDirector = directordao.listaEstudiantesDirector();
                request.setAttribute("estudiantesDirector", listarEstudiantesDirector);

                listarGradoDirector = directordao.listaGradoDirector();
                request.setAttribute("gradoDirector", listarGradoDirector);

                listarCursosDirector = directordao.listaCursosDirector();
                request.setAttribute("cursosDirector", listarCursosDirector);
            }
            if ("Aceptar".equals(accion)) {
                String nombre = request.getParameter("txtnombre");
                String apellido = request.getParameter("txtapellido");
                String correo = request.getParameter("txtcorreo");
                String contrasena = request.getParameter("txtcontrasena");
                String dni = request.getParameter("txtdni");
                String fechaNacimiento = request.getParameter("txtfechaNacimiento");

                String grado = request.getParameter("txtgrado");

                director.setEstudianteNombre(nombre);
                director.setEstudianteApellido(apellido);
                director.setCorreo(correo);
                director.setContrasena(contrasena);
                director.setDni(dni);
                director.setFecha(fechaNacimiento);
                director.setGrado(grado);
                director.setIdRol(3);
                String periodo = "2025-2025";
                director.setPeriodoAcademico(periodo);

                directordao.agregarEstudiante(director);

                // Enviar un mensaje según el resultado
                request.setAttribute("mensaje", "Estudiante Agregado con éxito");
            }
            request.getRequestDispatcher("estudiantes.jsp").forward(request, response);
            if ("Eliminar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));

                directordao.eliminarEstudiante(id);
                request.setAttribute("mensaje", "Estudiante Eliminado con éxito");
            }
            request.getRequestDispatcher("estudiantes.jsp").forward(request, response);
            if ("Agregar".equals(accion)) {
                String idcurso = request.getParameter("txtcurso");
                String idestudiante = request.getParameter("txtestudiante");

                director.setIdEstudiante(Integer.parseInt(idestudiante));
                director.setIdCurso(Integer.parseInt(idcurso));

                directordao.agregaCursoEstudiante(director);

                request.setAttribute("mensaje", "Estudiante - Curso Agregado con éxito");
            }


            request.getRequestDispatcher("estudiantes.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}