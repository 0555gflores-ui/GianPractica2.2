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
import com.daoexample.models.Aula;

public class AulaDao implements IDao<Aula, String> {

    private final Connection connection;
    private PreparedStatement preQuery;
    private boolean isSuccess = false;

    private final Aula aula;
    private final List<Aula> aulas;

    private static final String INSERT_AULA = "INSERT INTO Aula (codigo, piso, pupitres) VALUES(?,?,?)";
    private static final String SELECT_ALL_AULAS = "SELECT codigo, piso, pupitres FROM Aula";
    private static final String SELECT_AULA_BY_CODIGO = "SELECT codigo, piso, pupitres FROM Aula WHERE codigo = ?";
    private static final String UPDATE_AULA = "UPDATE Aula SET piso=?, pupitres=? WHERE codigo = ?";
    private static final String DELETE_AULA = "DELETE FROM Aula WHERE codigo = ?";

    public AulaDao() {
        connection = ConnectToDB.getInstance().getConection();
        aula = new Aula();
        aulas = new ArrayList<>();
    }

    @Override
    public boolean createRecord(Aula model) {
        try {
            preQuery = connection.prepareStatement(INSERT_AULA);
            preQuery.setString(1, model.getCodigo());
            preQuery.setInt(2, model.getPiso());
            preQuery.setInt(3, model.getPupitres());

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            Logger.getLogger(AulaDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public Aula readRecord(String codigo) {
        try {
            preQuery = connection.prepareStatement(SELECT_AULA_BY_CODIGO);
            preQuery.setString(1, codigo);
            ResultSet data = preQuery.executeQuery();

            if (data.next()) {
                aula.setCodigo(data.getString("codigo"));
                aula.setPiso(data.getInt("piso"));
                aula.setPupitres(data.getInt("pupitres"));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AulaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return aula;
    }

    @Override
    public boolean updateRecord(Aula model, String codigo) {
        try {
            preQuery = connection.prepareStatement(UPDATE_AULA);
            preQuery.setInt(1, model.getPiso());
            preQuery.setInt(2, model.getPupitres());
            preQuery.setString(3, codigo);

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AulaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    @Override
    public boolean deleteRecord(String codigo) {
        try {
            preQuery = connection.prepareStatement(DELETE_AULA);
            preQuery.setString(1, codigo);

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AulaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    @Override
    public List<Aula> readRecords() {
        try {
            preQuery = connection.prepareStatement(SELECT_ALL_AULAS);
            ResultSet data = preQuery.executeQuery();

            while (data.next()) {
                aulas.add(new Aula(
                        data.getString("codigo"),
                        data.getInt("piso"),
                        data.getInt("pupitres")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AulaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aulas;
    }
}
