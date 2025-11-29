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
import com.daoexample.models.Asignatura;

public class AsignaturaDao implements IDao<Asignatura, String> {

    private final Connection connection;
    private PreparedStatement preQuery;
    private boolean isSuccess = false;

    private final Asignatura asignatura;
    private final List<Asignatura> asignaturas;

    private static final String INSERT_ASIGNATURA
            = "INSERT INTO Asignatura (codigo, nombre, horas_semana) VALUES(?,?,?)";

    private static final String SELECT_ALL_ASIGNATURAS
            = "SELECT codigo, nombre, horas_semana FROM Asignatura";

    private static final String SELECT_ASIGNATURA_BY_CODIGO
            = "SELECT codigo, nombre, horas_semana FROM Asignatura WHERE codigo = ?";

    private static final String UPDATE_ASIGNATURA
            = "UPDATE Asignatura SET nombre=?, horas_semana=? WHERE codigo = ?";

    private static final String DELETE_ASIGNATURA
            = "DELETE FROM Asignatura WHERE codigo = ?";

    public AsignaturaDao() {
        connection = ConnectToDB.getInstance().getConection();
        asignatura = new Asignatura();
        asignaturas = new ArrayList<>();
    }

    @Override
    public boolean createRecord(Asignatura model) {
        try {
            preQuery = connection.prepareStatement(INSERT_ASIGNATURA);
            preQuery.setString(1, model.getCodigo());
            preQuery.setString(2, model.getNombre());
            preQuery.setInt(3, model.getHorasPorSemana());

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            Logger.getLogger(AsignaturaDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return isSuccess;
    }

    public Asignatura readRecordByCodigo(String codigo) {
        try {
            preQuery = connection.prepareStatement(SELECT_ASIGNATURA_BY_CODIGO);
            preQuery.setString(1, codigo);
            ResultSet data = preQuery.executeQuery();

            if (data.next()) {
                asignatura.setCodigo(data.getString("codigo"));
                asignatura.setNombre(data.getString("nombre"));
                asignatura.setHorasPorSemana(data.getInt("horas_semana"));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignaturaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return asignatura;
    }

    public boolean updateRecord(Asignatura model, String codigo) {
        try {
            preQuery = connection.prepareStatement(UPDATE_ASIGNATURA);
            preQuery.setString(1, model.getNombre());
            preQuery.setInt(2, model.getHorasPorSemana());
            preQuery.setString(3, codigo);

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignaturaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    public boolean deleteRecord(String codigo) {
        try {
            preQuery = connection.prepareStatement(DELETE_ASIGNATURA);
            preQuery.setString(1, codigo);

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignaturaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    @Override
    public List<Asignatura> readRecords() {
        try {
            preQuery = connection.prepareStatement(SELECT_ALL_ASIGNATURAS);
            ResultSet data = preQuery.executeQuery();

            while (data.next()) {

                asignaturas.add(new Asignatura(
                        data.getString("codigo"),
                        data.getString("nombre"),
                        data.getInt("horasPorSemana")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignaturaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return asignaturas;
    }

    @Override
    public Asignatura readRecord(String idModel) {
        return readRecordByCodigo(idModel);
    }

}
