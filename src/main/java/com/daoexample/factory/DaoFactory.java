package com.daoexample.factory;

import com.daoexample.daos.AlumnoDao;
import com.daoexample.daos.AsignaturaDao;
import com.daoexample.daos.AulaDao;
import com.daoexample.daos.CursoDao;
import com.daoexample.daos.ProfesorDao;
import com.daoexample.iface.IDao;

public class DaoFactory {

    public enum DaoType {
        ALUMNO,
        PROFESOR,
        ASIGNATURA,
        CURSO,
        AULA
    }

    public static <T, V> IDao<T, V> getDao(DaoType daoType) {

        return switch (daoType) {

            case ALUMNO ->
                (IDao<T, V>) new AlumnoDao();

            case PROFESOR ->
                (IDao<T, V>) new ProfesorDao();

            case ASIGNATURA ->
                (IDao<T, V>) new AsignaturaDao();

            case CURSO ->
                (IDao<T, V>) new CursoDao();

            case AULA ->
                (IDao<T, V>) new AulaDao();
        };
    }
}
