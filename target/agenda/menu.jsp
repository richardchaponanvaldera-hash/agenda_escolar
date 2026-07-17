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

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menú Responsivo</title>

    <style>
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            overflow: hidden;
        }

        .main-container {
            display: flex;
            height: 100vh;
        }

        .sidebar {
            width: 250px;
            background-color: #ffffff;
            border-right: 1px solid #000;
            transition: transform 0.3s ease-in-out;
        }

        .sidebar.hidden {
            transform: translateX(-100%);
        }

        .top-bar {
            width: 100%;
            background-color: #dc3545;
            color: white;
            padding: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }


        .navbar-toggler {
            cursor: pointer;
            background: none;
            border: none;
            color: white;
        }


        .content {
            flex-grow: 1;
            overflow: hidden;
        }

        iframe {
            width: 100%;
            height: 100%;
            border: none;
        }

        .logo {
            width: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            margin-bottom: 80px;
            text-align: center;
        }

        .enlace{
            width: 100%;
            display: flex;
            gap: 5px;
            justify-content: center;
            align-content: center;
        }

        #close-btn{
            border: none;
            background: none;
            outline: none;
            visibility: hidden;
        }


        @media (max-width: 768px) {
            .sidebar {
                position: fixed;
                z-index: 1000;
                height: 100vh;
                top: 0;
                left: 0;
            }

            .sidebar.hidden {
                transform: translateX(-100%);
            }

            #close-btn{
                visibility: visible;
            }
        }

    </style>
</head>
<body>
<div class="main-container d-flex">
    <!-- Sidebar -->
    <div class="sidebar" id="side_nav">
        <div  class="d-flex justify-content-end">
            <button id="close-btn" aria-label="Cerrar menú">
                <i class="bi bi-x-lg fs-2"></i>
            </button>
        </div>
        <div class="logo mt-2">
            <img src="./imagenes/logo.png"  width="140px" />
            <h1 class="fw-bold fs-2">I.E Augusto B. Leguia</h1>
        </div>

        <ul class="list-unstyled px-2">
            <li><a href="controlador?menu=cursos&accion=listar" target="myFrame" class="px-3 py-2 btn enlace">
                <i class="bi bi-journal-bookmark-fill"></i><span>Cursos</span></a></li>
<c:if test="${ usuarioSesion.getIdRol() != 1}">
            <li><a href="controlador?menu=asistencia&accion=listar" target="myFrame"  class="px-3 py-2 btn enlace">
                <i class="bi bi-people"></i><span>Asistencia</span></a></li>
</c:if>
<c:if test="${ usuarioSesion.getIdRol() == 1}">
            <li><a href="controlador?menu=profesores&accion=listar" target="myFrame"  class="px-3 py-2 btn enlace">
                <i class="bi bi-people"></i><span>Profesores</span></a></li>
            <li><a href="controlador?menu=apoderados&accion=listar" target="myFrame"  class="px-3 py-2 btn enlace">
                <i class="bi bi-people"></i><span>Apoderados</span></a></li>
            <li><a href="controlador?menu=estudiantes&accion=listar" target="myFrame"  class="px-3 py-2 btn enlace">
                <i class="bi bi-people"></i><span>Estudiantes</span></a></li>
</c:if>
<c:if test="${ usuarioSesion.getIdRol() != 1}">
            <li><a href="controlador?menu=notas&accion=listar" target="myFrame"  class="px-3 py-2 btn enlace">
                <i class="bi bi-journal"></i><span>Notas</span></a></li>
            <li><a href="controlador?menu=horario&accion=listar" target="myFrame"  class="px-3 py-2 btn  enlace">
                <i class="bi bi-calendar-date"></i><span>Horario</span></a></li>
</c:if>
            <li><a href="controlador?menu=agenda&accion=listar" target="myFrame" class="px-3 py-2  btn  enlace">
                <i class="bi bi-folder2"></i><span>Agenda</span></a></li>
        </ul>
    </div>

    <!-- Main content -->
    <div class="content d-flex flex-column">
        <!-- Barra superior -->
        <div class="d-flex justify-content-between align-items-center bg-danger p-2">
            <button class="navbar-toggler text-white" id="toggler-btn" type="button">
                <i class="bi bi-list fs-4"></i>
            </button>
            <div class="d-flex align-items-center gap-2">
                <span class="fw-bold text-light me-3">Comunicados</span>
                <a href="controlador?menu=comunicados&accion=listar" target="myFrame">
                    <button type="button" class="btn btn-outline-light rounded-circle me-3">
                        <i class="bi bi-chat-left-text-fill"></i>
                    </button>
                </a>
                <div class="dropdown">
                    <button class="btn btn-light dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        ${usuarioNombre.nombre}
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li>
                            <form action="login" method="POST">
                                <button name="accion" value="Salir" class="dropdown-item">
                                    <i class="bi bi-arrow-bar-left"></i> Salir
                                </button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <iframe id="myFrame" name="myFrame" src="controlador?menu=cursos&accion=listar"></iframe>
    </div>
</div>



<script>
    const sidebar = document.getElementById('side_nav');
    const openBtn = document.getElementById('toggler-btn');
    const closeBtn = document.getElementById('close-btn');

    openBtn.addEventListener('click', () => {
        sidebar.classList.remove('hidden');
    });

    closeBtn.addEventListener('click', () => {
        sidebar.classList.add('hidden');
    });

    window.addEventListener('resize', () => {
        if (window.innerWidth > 768) {
            sidebar.classList.remove('hidden');
        } else {
            sidebar.classList.add('hidden');
        }
    });
</script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</body>
</html>
