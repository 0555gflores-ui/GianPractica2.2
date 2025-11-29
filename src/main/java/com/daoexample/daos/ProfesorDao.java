package com.daoexample.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.daoexample.ConnectToDB;
import com.daoexample.iface.IDao;
import com.daoexample.models.Profesor;

public class ProfesorDao implements IDao<Profesor, String> {

    private final Connection connection;
    private PreparedStatement preQuery;
    private boolean isSuccess = false;

    private final Profesor profesor;
    private final List<Profesor> profesores;

    private static final String INSERT_PROFESOR = "INSERT INTO Profesor (dni, nombre, apellidos, direccion, fecha_nacimiento, codigo_postal, telefono) VALUES(?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_PROFESORES = "SELECT dni, nombre, apellidos, direccion, fecha_nacimiento, codigo_postal, telefono FROM Profesor";
    private static final String SELECT_PROFESOR_BY_DNI = "SELECT dni, nombre, apellidos, direccion, fecha_nacimiento, codigo_postal, telefono FROM Profesor WHERE dni = ?;";
    private static final String UPDATE_PROFESOR = "UPDATE Profesor SET nombre=?, apellidos=?, direccion=?, fecha_nacimiento=?, codigo_postal=?, telefono=? WHERE dni = ?;";
    private static final String DELETE_PROFESOR = "DELETE FROM Profesor WHERE dni = ?;";

    public ProfesorDao() {
        connection = ConnectToDB.getInstance().getConection();
        profesor = new Profesor();
        profesores = new ArrayList<>();
    }

    @Override
    public boolean createRecord(Profesor model) {
        try {
            preQuery = connection.prepareStatement(INSERT_PROFESOR);
            preQuery.setString(1, model.getDni());
            preQuery.setString(2, model.getNombre());
            preQuery.setString(3, model.getApellidos());
            preQuery.setString(4, model.getDireccion());
            preQuery.setString(5, model.getFechaNacimiento());
            preQuery.setString(6, model.getCodigoPostal());
            preQuery.setString(7, model.getTelefono());

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }

        } catch (SQLException e) {
            Logger.getLogger(ProfesorDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public Profesor readRecord(String dni) {
        try {
            preQuery = connection.prepareStatement(SELECT_PROFESOR_BY_DNI);
            preQuery.setString(1, dni);
            ResultSet data = preQuery.executeQuery();

            if (data.next()) {
                profesor.setDni(data.getString("dni"));
                profesor.setNombre(data.getString("nombre"));
                profesor.setApellidos(data.getString("apellidos"));
                profesor.setDireccion(data.getString("direccion"));
                profesor.setFechaNacimiento(data.getString("fecha_nacimiento"));
                profesor.setCodigoPostal(data.getString("codigo_postal"));
                profesor.setTelefono(data.getString("telefono"));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfesorDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return profesor;
    }

    @Override
    public boolean updateRecord(Profesor model, String dni) {
        try {
            preQuery = connection.prepareStatement(UPDATE_PROFESOR);
            preQuery.setString(1, model.getNombre());
            preQuery.setString(2, model.getApellidos());
            preQuery.setString(3, model.getDireccion());
            preQuery.setString(4, model.getFechaNacimiento());
            preQuery.setString(5, model.getCodigoPostal());
            preQuery.setString(6, model.getTelefono());
            preQuery.setString(7, dni);

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProfesorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    @Override
    public boolean deleteRecord(String dni) {
        try {
            preQuery = connection.prepareStatement(DELETE_PROFESOR);
            preQuery.setString(1, dni);

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProfesorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    @Override
    public List<Profesor> readRecords() {
        try {
            preQuery = connection.prepareStatement(SELECT_ALL_PROFESORES);
            ResultSet data = preQuery.executeQuery();

            while (data.next()) {
                profesores.add(new Profesor(
                        data.getString("dni"),
                        data.getString("nombre"),
                        data.getString("apellidos"),
                        data.getString("direccion"),
                        data.getString("fecha_nacimiento"),
                        data.getString("codigo_postal"),
                        data.getString("telefono")
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProfesorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return profesores;
    }
}
