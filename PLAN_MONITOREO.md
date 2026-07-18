# 📊 Plan de Monitoreo — Sistema de Gestión Escolar

**Etapa:** 4 — Monitoreo (Avance 90%)
**Proyecto:** Sistema de Gestión Escolar — I.E. Augusto B. Leguía

## 1. Objetivo

Definir las prácticas, herramientas y métricas necesarias para observar el comportamiento del sistema en producción y detectar problemas antes de que afecten a los usuarios.

## 2. Alcance

Aplica a los componentes: servidor web (Nginx), backend (API), base de datos (MySQL) y frontend.

## 3. Métricas monitoreadas

| Métrica | Herramienta / método | Umbral aceptable | Acción si se excede |
|---|---|---|---|
| Tiempo de respuesta | Logs de aplicación / performance tools | < 200 ms | Revisar consultas lentas a BD |
| Uso de CPU | Docker stats / health tools | < 70% | Escalar recursos o revisar procesos |
| Uso de memoria | Docker stats | < 80% | Revisar fugas de memoria |
| Disponibilidad (uptime) | Health checks periódicos | ≥ 99.5% | Activar plan de contingencia |
| Errores en logs | Consola de logs del sistema | 0 errores críticos/día | Escalar a soporte técnico |

## 4. Registro de eventos (Logs)

Todos los eventos relevantes se registran con nivel de severidad:

- **info** — inicio/cierre de sesión, acciones normales
- **success** — operaciones completadas (backup creado, registro guardado)
- **warning** — intentos fallidos de login, eliminación de registros
- **error** — fallos de restauración, errores de sistema

Los logs se conservan en un buffer de los últimos 100 eventos y deben exportarse/archivarse antes de superar ese límite en un entorno de producción real.

## 5. Health checks

Verificación periódica (cada 5 minutos en producción) del estado de:

- API (`/health`)
- Base de datos (conexión activa)
- Servidor web (Nginx)
- Última ejecución de backup

## 6. Alertas

| Evento | Severidad | Canal sugerido |
|---|---|---|
| Caída de servicio | Crítica | Notificación inmediata al equipo técnico |
| CPU/memoria > 85% sostenido 10 min | Alta | Correo / Slack |
| Backup fallido | Alta | Correo al Director/administrador |
| Intentos de login fallidos repetidos | Media | Log de seguridad |

## 7. Responsables

| Rol | Responsabilidad |
|---|---|
| Equipo de desarrollo | Mantener el módulo de logs y métricas |
| Director / administrador del sistema | Revisar el panel de monitoreo semanalmente |
| Soporte técnico | Atender alertas críticas |

## 8. Revisión del plan

Este plan debe revisarse cada semestre académico o tras cualquier incidente relevante.
