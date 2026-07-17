# ============================================
# DOCKERFILE - Sistema de Gestión Escolar
# I.E. Augusto B. Leguía - Mochumí
# ============================================

# Etapa 1: Usar imagen base de Nginx (ligera)
FROM nginx:alpine

# Información del maintainer
LABEL maintainer="I.E. Augusto B. Leguía"
LABEL description="Sistema de Gestión Escolar"
LABEL version="1.0.0"

# Eliminar configuración por defecto de Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copiar archivos del proyecto al directorio de Nginx
COPY index.html /usr/share/nginx/html/
COPY css/ /usr/share/nginx/html/css/
COPY js/ /usr/share/nginx/html/js/
COPY images/ /usr/share/nginx/html/images/

# Copiar configuración personalizada de Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Exponer el puerto 80
EXPOSE 80

# Healthcheck para verificar que el contenedor está saludable
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost/ || exit 1

# Comando por defecto (Nginx en primer plano)
CMD ["nginx", "-g", "daemon off;"]
