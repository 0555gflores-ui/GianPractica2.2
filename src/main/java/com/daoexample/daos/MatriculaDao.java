package com.daoexample.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.daoexample.ConnectToDB;
import com.daoexample.iface.IDao;
import com.daoexample.models.Matricula;

public class MatriculaDao implements IDao<Matricula, String> {

    private final Connection connection;
    private PreparedStatement preQuery;
    private boolean isSuccess = false;

    private final Matricula matricula;
    private final List<Matricula> matriculas;

    private static final String INSERT_MATRICULA = "INSERT INTO Matricula (alumno_dni, asignatura_codigo, calificacion, incidencias) VALUES(?,?,?,?)";
    private static final String SELECT_ALL_MATRICULAS = "SELECT alumno_dni, asignatura_codigo, calificacion, incidencias FROM Matricula";
    private static final String SELECT_MATRICULA_BY_ALUMNO_ASIGNATURA = "SELECT alumno_dni, asignatura_codigo, calificacion, incidencias FROM Matricula WHERE alumno_dni = ? AND asignatura_codigo = ?";
    private static final String UPDATE_MATRICULA = "UPDATE Matricula SET calificacion=?, incidencias=? WHERE alumno_dni = ? AND asignatura_codigo = ?";
    private static final String DELETE_MATRICULA = "DELETE FROM Matricula WHERE alumno_dni = ? AND asignatura_codigo = ?";

    public MatriculaDao() {
        connection = ConnectToDB.getInstance().getConection();
        matricula = new Matricula();
        matriculas = new ArrayList<>();
    }

    @Override
    public boolean createRecord(Matricula model) {
        try {
            preQuery = connection.prepareStatement(INSERT_MATRICULA);
            preQuery.setString(1, model.getAlumnoDni());
            preQuery.setString(2, model.getAsignaturaCodigo());
            preQuery.setFloat(3, model.getCalificacion());
            preQuery.setString(4, model.getIncidencias());

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            Logger.getLogger(MatriculaDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return isSuccess;
    }

    public Matricula readRecord(String alumnoDni, String asignaturaCodigo) {
        try {
            preQuery = connection.prepareStatement(SELECT_MATRICULA_BY_ALUMNO_ASIGNATURA);
            preQuery.setString(1, alumnoDni);
            preQuery.setString(2, asignaturaCodigo);
            ResultSet data = preQuery.executeQuery();

            if (data.next()) {
                matricula.setAlumnoDni(data.getString("alumno_dni"));
                matricula.setAsignaturaCodigo(data.getString("asignatura_codigo"));
                matricula.setCalificacion(data.getFloat("calificacion"));
                matricula.setIncidencias(data.getString("incidencias"));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MatriculaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return matricula;
    }

    public boolean updateRecord(Matricula model, String alumnoDni, String asignaturaCodigo) {
        try {
            preQuery = connection.prepareStatement(UPDATE_MATRICULA);
            preQuery.setFloat(1, model.getCalificacion());
            preQuery.setString(2, model.getIncidencias());
            preQuery.setString(3, alumnoDni);
            preQuery.setString(4, asignaturaCodigo);

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MatriculaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    public boolean deleteRecord(String alumnoDni, String asignaturaCodigo) {
        try {
            preQuery = connection.prepareStatement(DELETE_MATRICULA);
            preQuery.setString(1, alumnoDni);
            preQuery.setString(2, asignaturaCodigo);

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MatriculaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    @Override
    public List<Matricula> readRecords() {
        try {
            preQuery = connection.prepareStatement(SELECT_ALL_MATRICULAS);
            ResultSet data = preQuery.executeQuery();

            while (data.next()) {
                matriculas.add(new Matricula(
                        data.getString("alumno_dni"),
                        data.getString("asignatura_codigo"),
                        data.getFloat("calificacion"),
                        data.getString("incidencias")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MatriculaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matriculas;
    }

    @Override
    public Matricula readRecord(String idModel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean updateRecord(Matricula model, String idModel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteRecord(String idModel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
