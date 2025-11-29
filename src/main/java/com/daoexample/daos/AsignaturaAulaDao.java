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
import com.daoexample.models.AsignaturaAula;

public class AsignaturaAulaDao implements IDao<AsignaturaAula, String> {

    private final Connection connection;
    private PreparedStatement preQuery;
    private boolean isSuccess = false;

    private final AsignaturaAula asignaturaAula;
    private final List<AsignaturaAula> asignaturaAulas;

    private static final String INSERT_ASIGNATURA_AULA = "INSERT INTO Asignatura_Aula (asignatura_codigo, aula_codigo, mes, dia, hora) VALUES(?,?,?,?,?)";
    private static final String SELECT_ALL_ASIGNATURA_AULAS = "SELECT asignatura_codigo, aula_codigo, mes, dia, hora FROM Asignatura_Aula";
    private static final String SELECT_ASIGNATURA_AULA_BY_CODIGO = "SELECT asignatura_codigo, aula_codigo, mes, dia, hora FROM Asignatura_Aula WHERE asignatura_codigo = ? AND aula_codigo = ?";
    private static final String UPDATE_ASIGNATURA_AULA = "UPDATE Asignatura_Aula SET mes=?, dia=?, hora=? WHERE asignatura_codigo = ? AND aula_codigo = ?";
    private static final String DELETE_ASIGNATURA_AULA = "DELETE FROM Asignatura_Aula WHERE asignatura_codigo = ? AND aula_codigo = ?";

    public AsignaturaAulaDao() {
        connection = ConnectToDB.getInstance().getConection();
        asignaturaAula = new AsignaturaAula();
        asignaturaAulas = new ArrayList<>();
    }

    @Override
    public boolean createRecord(AsignaturaAula model) {
        try {
            preQuery = connection.prepareStatement(INSERT_ASIGNATURA_AULA);
            preQuery.setString(1, model.getAsignaturaCodigo());
            preQuery.setString(2, model.getAulaCodigo());
            preQuery.setInt(3, model.getMes());
            preQuery.setInt(4, model.getDia());
            preQuery.setInt(5, model.getHora());

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            Logger.getLogger(AsignaturaAulaDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return isSuccess;
    }

    public AsignaturaAula readRecord(String asignaturaCodigo, String aulaCodigo) {
        try {
            preQuery = connection.prepareStatement(SELECT_ASIGNATURA_AULA_BY_CODIGO);
            preQuery.setString(1, asignaturaCodigo);
            preQuery.setString(2, aulaCodigo);
            ResultSet data = preQuery.executeQuery();

            if (data.next()) {
                asignaturaAula.setAsignaturaCodigo(data.getString("asignatura_codigo"));
                asignaturaAula.setAulaCodigo(data.getString("aula_codigo"));
                asignaturaAula.setMes(data.getInt("mes"));
                asignaturaAula.setDia(data.getInt("dia"));
                asignaturaAula.setHora(data.getInt("hora"));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignaturaAulaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return asignaturaAula;
    }

    public boolean updateRecord(AsignaturaAula model, String asignaturaCodigo, String aulaCodigo) {
        try {
            preQuery = connection.prepareStatement(UPDATE_ASIGNATURA_AULA);
            preQuery.setInt(1, model.getMes());
            preQuery.setInt(2, model.getDia());
            preQuery.setInt(3, model.getHora());
            preQuery.setString(4, asignaturaCodigo);
            preQuery.setString(5, aulaCodigo);

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignaturaAulaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    public boolean deleteRecord(String asignaturaCodigo, String aulaCodigo) {
        try {
            preQuery = connection.prepareStatement(DELETE_ASIGNATURA_AULA);
            preQuery.setString(1, asignaturaCodigo);
            preQuery.setString(2, aulaCodigo);

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignaturaAulaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    @Override
    public List<AsignaturaAula> readRecords() {
        try {
            preQuery = connection.prepareStatement(SELECT_ALL_ASIGNATURA_AULAS);
            ResultSet data = preQuery.executeQuery();

            while (data.next()) {
                asignaturaAulas.add(new AsignaturaAula(
                        data.getString("asignatura_codigo"),
                        data.getString("aula_codigo"),
                        data.getInt("mes"),
                        data.getInt("dia"),
                        data.getInt("hora")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignaturaAulaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return asignaturaAulas;
    }

    @Override
    public AsignaturaAula readRecord(String idModel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean updateRecord(AsignaturaAula model, String idModel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteRecord(String idModel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
