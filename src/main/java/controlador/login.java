package controlador;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.usuario;
import modelo.dao.usuarioDAO;

import java.io.IOException;

public class login extends HttpServlet {
    usuarioDAO usuariodao = new usuarioDAO();
    usuario usuario = new usuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        // Manejo de cierre de sesión
        if (accion != null && accion.equals("logout")) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("index.jsp");
        } else {
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("Iniciar Sesion")) {
            String codigo = request.getParameter("txtcodigo");
            String contrasena = request.getParameter("txtcontrasena");

            if (codigo != null && !codigo.isEmpty() && contrasena != null && !contrasena.isEmpty()) {
                usuario = usuariodao.validar(codigo, contrasena);
                if (usuario !=null && usuario.getCorreo() != null ) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuarioSesion", usuario);
                    response.sendRedirect("controlador?menu=menu");
                } else {
                    request.setAttribute("error", "Credenciales incorrectas");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Todos los campos son obligatorios");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("error", "Acción no reconocida");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
