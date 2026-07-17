package test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * Clase de pruebas arquitectónicas que utiliza ArchUnit para verificar reglas de diseño.
 * Esta clase garantiza que las dependencias entre capas sigan buenas prácticas,
 * como la separación de responsabilidades y la ausencia de dependencias cíclicas.
 */
class ArchitectureTest {

    /**
     * Importa todas las clases del paquete raíz del proyecto para analizarlas.
     * El paquete base es "com.integrador.agenda".
     */
    private final JavaClasses importedClasses = new ClassFileImporter().importPackages("com.integrador.agenda");

    /**
     * Verifica que los controladores no dependan directamente de los DAOs.
     * Esto garantiza una separación adecuada entre la capa de controladores (lógica de negocio o presentación)
     * y la capa de acceso a datos.
     */
    @Test
    void controllersShouldNotDependOnDaos() {
        noClasses()
                .that().resideInAPackage("..controlador..") // Clases en el paquete "controlador"
                .should().dependOnClassesThat().resideInAPackage("..modelo.dao..") // No deben depender de DAOs
                .allowEmptyShould(true) // Permite que la prueba pase si no se encuentran clases relevantes
                .check(importedClasses); // Verifica la regla

        // Depuración: Muestra los nombres de las clases importadas
        importedClasses.forEach(clazz -> System.out.println(clazz.getName()));
    }

    /**
     * Verifica que los DAOs no accedan directamente a los controladores.
     * Esto asegura que la capa de acceso a datos esté desacoplada de la capa de controladores.
     */
    @Test
    void daosShouldNotAccessControllers() {
        noClasses()
                .that().resideInAPackage("..modelo.dao..") // Clases en el paquete "modelo.dao"
                .should().dependOnClassesThat().resideInAPackage("..controlador..") // No deben depender de controladores
                .allowEmptyShould(true) // Permite que la prueba pase si no hay clases relevantes
                .check(importedClasses); // Verifica la regla

        // Depuración: Muestra los nombres de las clases importadas
        importedClasses.forEach(clazz -> System.out.println(clazz.getName()));
    }

    /**
     * Verifica que no existan dependencias cíclicas entre clases en el paquete "controlador".
     * Esto evita que los controladores dependan unos de otros en un ciclo, lo que dificulta el mantenimiento.
     */
    @Test
    void noCyclicDependenciesBetweenControladores() {
        noClasses()
                .that().resideInAPackage("..controlador..") // Clases en el paquete "controlador"
                .should().dependOnClassesThat().resideInAPackage("..controlador..") // No deben formar ciclos
                .allowEmptyShould(true) // Permite que la prueba pase si no hay clases relevantes
                .check(importedClasses); // Verifica la regla

        // Depuración: Muestra los nombres de las clases importadas
        importedClasses.forEach(clazz -> System.out.println(clazz.getName()));
    }

    /**
     * Verifica que no existan dependencias cíclicas entre clases en el paquete "modelo.dao".
     * Esto evita que los DAOs dependan unos de otros en un ciclo, lo que complica la modularidad.
     */
    @Test
    void noCyclicDependenciesBetweenDaos() {
        noClasses()
                .that().resideInAPackage("..modelo.dao..") // Clases en el paquete "modelo.dao"
                .should().dependOnClassesThat().resideInAPackage("..modelo.dao..") // No deben formar ciclos
                .allowEmptyShould(true) // Permite que la prueba pase si no hay clases relevantes
                .check(importedClasses); // Verifica la regla

        // Depuración: Muestra los nombres de las clases importadas
        importedClasses.forEach(clazz -> System.out.println(clazz.getName()));
    }
}



