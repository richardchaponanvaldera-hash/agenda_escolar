# 🔧 Plan de Mantenimiento — Sistema de Gestión Escolar

**Etapa:** 5 — Mantenimiento (Avance 100%)
**Proyecto:** Sistema de Gestión Escolar — I.E. Augusto B. Leguía

## 1. Objetivo

Garantizar la continuidad, integridad y actualización del sistema mediante backups, tareas programadas (cron jobs) y scripts de mantenimiento.

## 2. Tipos de mantenimiento

| Tipo | Descripción | Frecuencia |
|---|---|---|
| Preventivo | Backups, limpieza de logs, optimización de BD | Diario / semanal |
| Correctivo | Corrección de errores reportados | Según incidencia |
| Adaptativo | Actualización de dependencias y librerías | Mensual |
| Perfectivo | Mejoras de rendimiento y usabilidad | Por sprint/versión |

## 3. Backups

- **Frecuencia:** diaria (03:00 a.m.)
- **Retención:** últimas 10 copias (7 días recomendado en producción)
- **Contenido:** cursos, profesores, estudiantes, apoderados, comunicados, eventos, horarios y secciones
- **Restauración:** disponible desde el panel de Mantenimiento (`restoreBackup`), con confirmación previa
- **Verificación:** cada backup debe probarse mensualmente restaurándolo en un entorno de prueba

## 4. Cron jobs programados

| Horario (cron) | Tarea | Estado |
|---|---|---|
| `0 3 * * *` | Backup diario de base de datos | ✅ Activo |
| `0 2 * * *` | Limpieza de logs con más de 30 días | ✅ Activo |
| `0 */6 * * *` | Limpieza de sesiones expiradas | ✅ Activo |
| `0 4 * * 0` | Mantenimiento semanal / optimización de BD | ✅ Activo |
| `0 0 1 * *` | Actualización mensual de dependencias | ⏳ Pendiente |

## 5. Scripts de mantenimiento

- Script de backup y compresión de base de datos
- Script de limpieza de logs antiguos
- Script de verificación de integridad de datos tras restauración

## 6. Plan de contingencia

1. **Detectar** el problema (logs, métricas, alertas)
2. **Contener** el incidente (aislar el componente afectado)
3. **Restaurar** desde el último backup válido
4. **Verificar** integridad de los datos y ejecutar pruebas
5. **Documentar** el incidente y aplicar mejoras preventivas

## 7. Control de versiones

Cada cambio relevante se documenta con número de versión (`vX.Y.Z`), fecha, descripción y lista de cambios, siguiendo el historial visible en la sección de Despliegue del sistema.

## 8. Responsables

| Rol | Responsabilidad |
|---|---|
| Equipo de desarrollo | Ejecutar y verificar backups, aplicar actualizaciones |
| Director / administrador | Autorizar restauraciones y revisar el historial de versiones |

## 9. Revisión del plan

Este plan debe revisarse cada semestre académico o después de ejecutar el plan de contingencia.
