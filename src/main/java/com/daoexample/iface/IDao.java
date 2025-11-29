package com.daoexample.iface;

import java.util.List;

public interface IDao<T, V> {

    /**
     * 
     * @param model objeto que va a guardar
     * @return true si se ha creado bien
     */
    public boolean createRecord(T model);

    /**
     * 
     * @param idModel Clave del registro a consultar
     * @return Registro encontrado casteado al modelo
     */
    public T readRecord(V idModel);

    /**
     * 
     * @param model Datos nuevos
     * @param idModel Id de registro a cambiar
     * @return
     */
    public boolean updateRecord(T model, V idModel);

    /**
     * 
     * @param idModel Id del registro a eliminar
     * @return True si no ha petado
     */
    public boolean deleteRecord(V idModel);

    /**
     * 
     * @return Devuelve todos los registro de una entidad
     */
    public List<T> readRecords();

}
