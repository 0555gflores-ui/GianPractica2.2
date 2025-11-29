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
import com.daoexample.models.Curso;

public class CursoDao implements IDao<Curso, String> {

    private final Connection connection;
    private PreparedStatement preQuery;
    private boolean isSuccess = false;

    private final Curso curso;
    private final List<Curso> cursos;

    private static final String INSERT_CURSO = "INSERT INTO Curso (codigo, nombre) VALUES(?,?)";
    private static final String SELECT_ALL_CURSOS = "SELECT codigo, nombre FROM Curso";
    private static final String SELECT_CURSO_BY_CODIGO = "SELECT codigo, nombre FROM Curso WHERE codigo = ?";
    private static final String UPDATE_CURSO = "UPDATE Curso SET nombre=? WHERE codigo = ?";
    private static final String DELETE_CURSO = "DELETE FROM Curso WHERE codigo = ?";

    public CursoDao() {
        connection = ConnectToDB.getInstance().getConection();
        curso = new Curso();
        cursos = new ArrayList<>();
    }

    @Override
    public boolean createRecord(Curso model) {
        try {
            preQuery = connection.prepareStatement(INSERT_CURSO);
            preQuery.setString(1, model.getCodigo());
            preQuery.setString(2, model.getNombre());

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            Logger.getLogger(CursoDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public Curso readRecord(String codigo) {
        try {
            preQuery = connection.prepareStatement(SELECT_CURSO_BY_CODIGO);
            preQuery.setString(1, codigo);
            ResultSet data = preQuery.executeQuery();

            if (data.next()) {
                curso.setCodigo(data.getString("codigo"));
                curso.setNombre(data.getString("nombre"));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return curso;
    }

    @Override
    public boolean updateRecord(Curso model, String codigo) {
        try {
            preQuery = connection.prepareStatement(UPDATE_CURSO);
            preQuery.setString(1, model.getNombre());
            preQuery.setString(2, codigo);

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    @Override
    public boolean deleteRecord(String codigo) {
        try {
            preQuery = connection.prepareStatement(DELETE_CURSO);
            preQuery.setString(1, codigo);

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    @Override
    public List<Curso> readRecords() {
        try {
            preQuery = connection.prepareStatement(SELECT_ALL_CURSOS);
            ResultSet data = preQuery.executeQuery();

            while (data.next()) {
                cursos.add(new Curso(
                        data.getString("codigo"),
                        data.getString("nombre")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }
}
