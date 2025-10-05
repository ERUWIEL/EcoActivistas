package com.mycompany.ecoactivistas.controller;

import com.mycompany.ecoactivistas.dao.ActivistaDAO;
import com.mycompany.ecoactivistas.interfaces.IActivistaDAO;
import com.mycompany.ecoactivistas.model.Activista;
import java.sql.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author martinbl
 */
public class ActivistaController {

    private final IActivistaDAO activistaDAO;

    public ActivistaController() {
        this.activistaDAO = new ActivistaDAO();
    }

    // Insertar un nuevo activista con validaciones
    public boolean agregarActivista(String nombre, String telefono, Date fchIngreso) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre del activista no puede estar vacío.");
            return false;
        }
        if (fchIngreso == null) {
            System.err.println("La fecha de ingreso es obligatoria.");
            return false;
        }
        if (telefono == null) {
            telefono = "";
        }

        Activista activista = new Activista();
        activista.setNombre(nombre.trim());
        activista.setTelefono(telefono.trim());
        activista.setFchIngreso(fchIngreso);

        return activistaDAO.insertar(activista);
    }

    // Obtener un activista por ID
    public Activista obtenerActivista(int idActivista) {
        if (idActivista <= 0) {
            System.err.println("ID de activista inválido.");
            return null;
        }
        return activistaDAO.obtenerPorId(idActivista);
    }

    // Obtener todos los activistas
    public List<Activista> listarActivistas() {
        return activistaDAO.obtenerTodos();
    }

    // Actualizar activista con validaciones
    public boolean actualizarActivista(int idActivista, String nombre, String telefono, Date fchIngreso) {
        if (idActivista <= 0) {
            System.err.println("ID de activista inválido.");
            return false;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre del activista no puede estar vacío.");
            return false;
        }
        if (fchIngreso == null) {
            System.err.println("La fecha de ingreso es obligatoria.");
            return false;
        }
        if (telefono == null) {
            telefono = "";
        }

        Activista activista = new Activista();
        activista.setIdActivista(idActivista);
        activista.setNombre(nombre.trim());
        activista.setTelefono(telefono.trim());
        activista.setFchIngreso(fchIngreso);

        return activistaDAO.actualizar(activista);
    }

    // Eliminar activista con validación de ID
    public boolean eliminarActivista(int idActivista) {
        if (idActivista <= 0) {
            System.err.println("ID de activista inválido.");
            return false;
        }
        return activistaDAO.eliminar(idActivista);
    }

    public DefaultTableModel obtenerTablaActivistas() {
        String[] columnas = {"ID", "NOMBRE", "TELEFÓNO", "INGRESO"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Activista> lista = activistaDAO.obtenerTodos();
        for (Activista a : lista) {
            modelo.addRow(new Object[]{a.getIdActivista(), a.getNombre(), a.getTelefono(), a.getFchIngreso()});
        }
        return modelo;
    }

    public DefaultTableModel obtenerTablaActivistasPorFiltro(String filtro) {
        String[] columnas = {"ID", "NOMBRE", "TELEFÓNO", "INGRESO"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Activista> lista = activistaDAO.obtenerTodosPorFiltro(filtro);
        for (Activista a : lista) {
            modelo.addRow(new Object[]{a.getIdActivista(), a.getNombre(), a.getTelefono(), a.getFchIngreso()});
        }
        return modelo;
    }
    
     public DefaultTableModel obtenerTablaActivistasPorFiltroModal(String filtro) {
        String[] columnas = {"ID", "NOMBRE", "TELEFÓNO"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Activista> lista = activistaDAO.obtenerTodosPorFiltroModal(filtro);
        for (Activista a : lista) {
            modelo.addRow(new Object[]{a.getIdActivista(), a.getNombre(), a.getTelefono()});
        }
        return modelo;
    }
}
