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
import com.daoexample.models.Alumno;

public class AlumnoDao implements IDao<Alumno, String> {

    private final Connection connection;
    private PreparedStatement preQuery;
    private boolean isSuccess = false;

    private final Alumno alumno;
    private final List<Alumno> alumnos;

    private static final String INSERT_ALUMNO = "INSERT INTO Alumno (dni, nombre, apellidos, direccion, fecha_nacimiento, codigo_postal, telefono) VALUES(?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_ALUMNOS = "SELECT dni, nombre, apellidos, direccion, fecha_nacimiento, codigo_postal, telefono FROM Alumno";
    private static final String SELECT_ALUMNO_BY_DNI = "SELECT dni, nombre, apellidos, direccion, fecha_nacimiento, codigo_postal, telefono FROM Alumno WHERE dni = ?;";
    private static final String UPDATE_ALUMNO = "UPDATE Alumno SET nombre=?, apellidos=?, direccion=?, fecha_nacimiento=?, codigo_postal=?, telefono=? WHERE dni = ?;";
    private static final String DELETE_ALUMNO = "DELETE FROM Alumno WHERE dni = ?;";

    public AlumnoDao() {
        connection = ConnectToDB.getInstance().getConection();
        alumno = new Alumno();
        alumnos = new ArrayList<>();
    }

    @Override
    public boolean createRecord(Alumno model) {
        try {
            preQuery = connection.prepareStatement(INSERT_ALUMNO);
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
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public Alumno readRecord(String dni) {
        try {
            preQuery = connection.prepareStatement(SELECT_ALUMNO_BY_DNI);
            preQuery.setString(1, dni);
            ResultSet data = preQuery.executeQuery();

            if (data.next()) {
                alumno.setDni(data.getString("dni"));
                alumno.setNombre(data.getString("nombre"));
                alumno.setApellidos(data.getString("apellidos"));
                alumno.setDireccion(data.getString("direccion"));
                alumno.setFechaNacimiento(data.getString("fecha_nacimiento"));
                alumno.setCodigoPostal(data.getString("codigo_postal"));
                alumno.setTelefono(data.getString("telefono"));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumno;
    }

    @Override
    public boolean updateRecord(Alumno model, String dni) {
        try {
            preQuery = connection.prepareStatement(UPDATE_ALUMNO);
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
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    @Override
    public boolean deleteRecord(String dni) {
        try {
            preQuery = connection.prepareStatement(DELETE_ALUMNO);
            preQuery.setString(1, dni);

            if (preQuery.executeUpdate() > 0) {
                isSuccess = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSuccess;
    }

    @Override
    public List<Alumno> readRecords() {
        try {
            preQuery = connection.prepareStatement(SELECT_ALL_ALUMNOS);
            ResultSet data = preQuery.executeQuery();

            while (data.next()) {
                alumnos.add(new Alumno(
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
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumnos;
    }
}
