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
<html>
<head>
    <title>Inicio</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            /*background-image: url('./imagenes/imagen_login.jpg');*/
            background-color: #feffbd;
            background-size: cover;
            background-position: center;
            display: flex;
            align-items: center;
            justify-content: flex-end;
        }
        .img{
            width: 100%;
        }

        .form-container {
            width: 45rem;
            height: 100%;
            display: flex;
            align-items: center;
            background-color: #fff;
            padding: 2rem;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.25);
        }

        .form-container .form-label {
            font-weight: bold;
        }

        .logo {
            width: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            margin-bottom: 80px;
            text-align: center;
        }


        @media (max-width: 768px) {
            .form-container {
                width: 80%;
                margin: auto;
            }
        }
    </style>
</head>
<body>
    <div class="img">
        <img class="w-100" src="./imagenes/imagen_login.jpg"/>
    </div>
    <div class="form-container">
        <div class="w-100">
            <form action="login" method="POST" accept-charset="UTF-8">
                <div class="logo">
                    <div class="div">
                        <img src="./imagenes/logo.png"  width="140px" />
                    </div>
                    <h1 class="fw-bold">I.E Augusto B. Leguia</h1>
                </div>

                <p>Ingresa tus datos para iniciar sesion</p>

                <div class="mb-3">
                    <label class="form-label fw-bold">Código</label>
                    <input type="text" name="txtcodigo" class="form-control" placeholder="123456" required>
                </div>
                <div class="mb-3">
                    <label class="form-label fw-bold">Contraseña</label>
                    <input type="password" name="txtcontrasena" class="form-control" placeholder="*****" required>
                </div>
                <div class="text-center">
                    <input type="submit" name="accion" value="Iniciar Sesion"  class="btn btn-primary w-100">
                </div>
            </form>
            <%-- Mostrar mensaje de error si existe --%>
            <c:if test="${not empty error}">
                <div class="alert alert-danger mt-3">${error}</div>
            </c:if>
        </div>
    </div>
</body>
</html>