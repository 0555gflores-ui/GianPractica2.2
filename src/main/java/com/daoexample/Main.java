package com.daoexample;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.daoexample.factory.DaoFactory;
import com.daoexample.iface.IDao;
import com.daoexample.models.Alumno;
import com.daoexample.models.Asignatura;
import com.daoexample.models.Aula;
import com.daoexample.models.Curso;
import com.daoexample.models.Profesor;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean runApp = true;

        while (runApp) {

            System.out.println("""
                \n====== GESTIÓN ACADEMIA ======
                Selecciona una categoría:
                [1] Alumnos
                [2] Profesores
                [3] Asignaturas
                [4] Aulas
                [5] Cursos
                [6] Asignatura-Aula (horarios)
                [0] Salir
                """);

            int option = sc.nextInt();

            switch (option) {
                case 1 ->
                    menuAlumnos(sc);
                case 2 ->
                    menuProfesores(sc);
                case 3 ->
                    menuAsignaturas(sc);
                case 4 ->
                    menuAulas(sc);
                case 5 ->
                    menuCursos(sc);

                case 0 -> {
                    runApp = false;
                    System.out.println("Cerrando aplicación...");
                }
                default ->
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void menuAlumnos(Scanner sc) {

        IDao<Alumno, String> dao = DaoFactory.getDao(DaoFactory.DaoType.ALUMNO);
        Alumno alumno = new Alumno();
        List<Alumno> alumnos = new ArrayList<>();
        boolean subRun = true;

        while (subRun) {

            System.out.println("""
                \n---- GESTIÓN ALUMNOS ----
                [1] Crear alumno
                [2] Actualizar alumno
                [3] Eliminar alumno
                [4] Buscar alumno
                [5] Mostrar todos
                [0] Volver
                """);

            int op = sc.nextInt();

            switch (op) {
                case 1 -> {
                    System.out.println("DNI:");
                    alumno.setDni(sc.next());
                    System.out.println("Nombre:");
                    alumno.setNombre(sc.next());
                    System.out.println("Apellidos:");
                    alumno.setApellidos(sc.next());
                    System.out.println("Dirección:");
                    alumno.setDireccion(sc.next());
                    System.out.println("Fecha nacimiento:");
                    alumno.setFechaNacimiento(sc.next());
                    System.out.println("Código Postal:");
                    alumno.setCodigoPostal(sc.next());
                    System.out.println("Teléfono:");
                    alumno.setTelefono(sc.next());

                    dao.createRecord(alumno);
                }

                case 2 -> {
                    System.out.println("DNI del alumno a actualizar:");
                    String id = sc.next();
                    System.out.println("Nuevo nombre:");
                    alumno.setNombre(sc.next());
                    System.out.println("Nuevos apellidos:");
                    alumno.setApellidos(sc.next());
                    System.out.println("Nueva dirección:");
                    alumno.setDireccion(sc.next());
                    System.out.println("Nueva fecha nacimiento:");
                    alumno.setFechaNacimiento(sc.next());
                    System.out.println("Nuevo CP:");
                    alumno.setCodigoPostal(sc.next());
                    System.out.println("Nuevo teléfono:");
                    alumno.setTelefono(sc.next());

                    dao.updateRecord(alumno, id);
                }

                case 3 -> {
                    System.out.println("DNI del alumno a eliminar:");
                    String id = sc.next();
                    dao.deleteRecord(id);
                }

                case 4 -> {
                    System.out.println("DNI a buscar:");
                    String id = sc.next();
                    Alumno a = dao.readRecord(id);
                    System.out.println(a != null ? a : "No encontrado");
                }

                case 5 -> {
                    alumnos = dao.readRecords();
                    alumnos.forEach(System.out::println);
                }

                case 0 ->
                    subRun = false;
                default ->
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void menuProfesores(Scanner sc) {

        IDao<Profesor, String> dao = DaoFactory.getDao(DaoFactory.DaoType.PROFESOR);
        Profesor profesor = new Profesor();
        List<Profesor> profesores = new ArrayList<>();
        boolean subRun = true;

        while (subRun) {

            System.out.println("""
                \n---- GESTIÓN PROFESORES ----
                [1] Crear profesor
                [2] Actualizar profesor
                [3] Eliminar profesor
                [4] Buscar profesor
                [5] Mostrar todos
                [0] Volver
                """);

            int op = sc.nextInt();

            switch (op) {
                case 1 -> {
                    System.out.println("DNI:");
                    profesor.setDni(sc.next());
                    System.out.println("Nombre:");
                    profesor.setNombre(sc.next());
                    System.out.println("Apellidos:");
                    profesor.setApellidos(sc.next());
                    System.out.println("Dirección:");
                    profesor.setDireccion(sc.next());
                    System.out.println("Fecha nacimiento:");
                    profesor.setFechaNacimiento(sc.next());
                    System.out.println("CP:");
                    profesor.setCodigoPostal(sc.next());
                    System.out.println("Teléfono:");
                    profesor.setTelefono(sc.next());
                    dao.createRecord(profesor);
                }

                case 2 -> {
                    System.out.println("DNI a actualizar:");
                    String id = sc.next();
                    System.out.println("Nuevo nombre:");
                    profesor.setNombre(sc.next());
                    System.out.println("Nuevos apellidos:");
                    profesor.setApellidos(sc.next());
                    System.out.println("Nueva dirección:");
                    profesor.setDireccion(sc.next());
                    System.out.println("Nueva fecha nacimiento:");
                    profesor.setFechaNacimiento(sc.next());
                    System.out.println("Nuevo CP:");
                    profesor.setCodigoPostal(sc.next());
                    System.out.println("Nuevo teléfono:");
                    profesor.setTelefono(sc.next());
                    dao.updateRecord(profesor, id);
                }

                case 3 -> {
                    System.out.println("DNI a eliminar:");
                    dao.deleteRecord(sc.next());
                }

                case 4 -> {
                    System.out.println("DNI a buscar:");
                    Profesor p = dao.readRecord(sc.next());
                    System.out.println(p != null ? p : "No encontrado");
                }

                case 5 -> {
                    profesores = dao.readRecords();
                    profesores.forEach(System.out::println);
                }

                case 0 ->
                    subRun = false;
                default ->
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void menuAsignaturas(Scanner sc) {

        IDao<Asignatura, String> dao = DaoFactory.getDao(DaoFactory.DaoType.ASIGNATURA);
        Asignatura asignatura = new Asignatura();
        List<Asignatura> asignaturas = new ArrayList<>();
        boolean subRun = true;

        while (subRun) {

            System.out.println("""
                \n---- GESTIÓN ASIGNATURAS ----
                [1] Crear asignatura
                [2] Actualizar asignatura
                [3] Eliminar asignatura
                [4] Buscar asignatura
                [5] Mostrar todas
                [0] Volver
                """);

            int op = sc.nextInt();

            switch (op) {
                case 1 -> {
                    System.out.println("Código:");
                    asignatura.setCodigo(sc.next());
                    System.out.println("Nombre:");
                    asignatura.setNombre(sc.next());
                    System.out.println("Horas semana:");
                    asignatura.setHorasPorSemana(sc.nextInt());
                    dao.createRecord(asignatura);
                }

                case 2 -> {
                    System.out.println("Código a actualizar:");
                    String codigo = sc.next();
                    System.out.println("Nuevo nombre:");
                    asignatura.setNombre(sc.next());
                    System.out.println("Nuevas horas semana:");
                    asignatura.setHorasPorSemana(sc.nextInt());
                    dao.updateRecord(asignatura, codigo);
                }

                case 3 -> {
                    System.out.println("Código a eliminar:");
                    dao.deleteRecord(sc.next());
                }

                case 4 -> {
                    System.out.println("Código a buscar:");
                    Asignatura a = dao.readRecord(sc.next());
                    System.out.println(a != null ? a : "No encontrado");
                }

                case 5 -> {
                    asignaturas = dao.readRecords();
                    asignaturas.forEach(System.out::println);
                }

                case 0 ->
                    subRun = false;
                default ->
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void menuAulas(Scanner sc) {

        IDao<Aula, String> dao = DaoFactory.getDao(DaoFactory.DaoType.AULA);
        Aula aula = new Aula();
        List<Aula> aulas = new ArrayList<>();
        boolean subRun = true;

        while (subRun) {

            System.out.println("""
                \n---- GESTIÓN AULAS ----
                [1] Crear aula
                [2] Actualizar aula
                [3] Eliminar aula
                [4] Buscar aula
                [5] Mostrar todas
                [0] Volver
                """);

            int op = sc.nextInt();

            switch (op) {
                case 1 -> {
                    System.out.println("Código:");
                    aula.setCodigo(sc.next());
                    System.out.println("Piso:");
                    aula.setPiso(sc.nextInt());
                    System.out.println("Nº pupitres:");
                    aula.setPupitres(sc.nextInt());
                    dao.createRecord(aula);
                }

                case 2 -> {
                    System.out.println("Código a actualizar:");
                    String codigo = sc.next();
                    System.out.println("Nuevo piso:");
                    aula.setPiso(sc.nextInt());
                    System.out.println("Nuevos pupitres:");
                    aula.setPupitres(sc.nextInt());
                    dao.updateRecord(aula, codigo);
                }

                case 3 -> {
                    System.out.println("Código a eliminar:");
                    dao.deleteRecord(sc.next());
                }

                case 4 -> {
                    System.out.println("Código a buscar:");
                    Aula a = dao.readRecord(sc.next());
                    System.out.println(a != null ? a : "No encontrado");
                }

                case 5 -> {
                    aulas = dao.readRecords();
                    aulas.forEach(System.out::println);
                }

                case 0 ->
                    subRun = false;
                default ->
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void menuCursos(Scanner sc) {

        IDao<Curso, String> dao = DaoFactory.getDao(DaoFactory.DaoType.CURSO);
        Curso curso = new Curso();
        List<Curso> cursos = new ArrayList<>();
        boolean subRun = true;

        while (subRun) {

            System.out.println("""
                \n---- GESTIÓN CURSOS ----
                [1] Crear curso
                [2] Actualizar curso
                [3] Eliminar curso
                [4] Buscar curso
                [5] Mostrar todos
                [0] Volver
                """);

            int op = sc.nextInt();

            switch (op) {
                case 1 -> {
                    System.out.println("Código:");
                    curso.setCodigo(sc.next());
                    System.out.println("Nombre:");
                    curso.setNombre(sc.next());
                    dao.createRecord(curso);
                }

                case 2 -> {
                    System.out.println("Código a actualizar:");
                    String codigo = sc.next();
                    System.out.println("Nuevo nombre:");
                    curso.setNombre(sc.next());
                    dao.updateRecord(curso, codigo);
                }

                case 3 -> {
                    System.out.println("Código a eliminar:");
                    dao.deleteRecord(sc.next());
                }

                case 4 -> {
                    System.out.println("Código a buscar:");
                    Curso c = dao.readRecord(sc.next());
                    System.out.println(c != null ? c : "No encontrado");
                }

                case 5 -> {
                    cursos = dao.readRecords();
                    cursos.forEach(System.out::println);
                }

                case 0 ->
                    subRun = false;
                default ->
                    System.out.println("Opción inválida");
            }
        }
    }
}
