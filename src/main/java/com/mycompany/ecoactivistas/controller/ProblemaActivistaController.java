/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecoactivistas.controller;

import com.mycompany.ecoactivistas.dao.ProblemaActivistaDAO;
import com.mycompany.ecoactivistas.interfaces.IProblemaActivistaDAO;
import com.mycompany.ecoactivistas.model.ProblemaActivista;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author martinbl
 */
public class ProblemaActivistaController {

    private final IProblemaActivistaDAO paDAO;

    public ProblemaActivistaController() {
        this.paDAO = new ProblemaActivistaDAO();
    }

    // Asignar un activista a un problema
    public boolean asignarActivista(int idProblema, int idActivista) {
        if (idProblema <= 0 || idActivista <= 0) {
            System.err.println("IDs inválidos para asignación.");
            return false;
        }

        ProblemaActivista relacion = new ProblemaActivista();
        relacion.setIdProblema(idProblema);
        relacion.setIdActivista(idActivista);

        return paDAO.insertar(relacion);
    }

    // Listar activistas asignados a un problema
    public List<ProblemaActivista> obtenerPorProblema(int idProblema) {
        if (idProblema <= 0) {
            System.err.println("ID de problema inválido.");
            return null;
        }
        return paDAO.obtenerPorProblema(idProblema);
    }

    // Listar problemas asignados a un activista
    public List<ProblemaActivista> obtenerPorActivista(int idActivista) {
        if (idActivista <= 0) {
            System.err.println("ID de activista inválido.");
            return null;
        }
        return paDAO.obtenerPorActivista(idActivista);
    }

    // Eliminar asignación
    public boolean eliminarAsignacion(int idProblema, int idActivista) {
        if (idProblema <= 0 || idActivista <= 0) {
            System.err.println("IDs inválidos para eliminación.");
            return false;
        }
        return paDAO.eliminar(idProblema, idActivista);
    }
    
    
    public DefaultTableModel obtenerTablaProblemasActivistas() {
        String[] columnas = {"ID", "PROBLEMA", "ACTIVISTA"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<ProblemaActivista> lista = paDAO.obtenerTodos();
        for (ProblemaActivista pa : lista) {
            modelo.addRow(new Object[]{pa.getIdProblema(), pa.getIdActivista()});
        }
        return modelo;
    }
    
    public DefaultTableModel obtenerTablaProblemasActivistasPorFiltro(String filtro) {
        String[] columnas = {"ID", "PROBLEMA", "ACTIVISTA"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<ProblemaActivista> lista = paDAO.obtenerTodosPorFiltro(filtro);
        for (ProblemaActivista pa : lista) {
            modelo.addRow(new Object[]{pa.getIdProblema(), pa.getIdActivista()});
        }
        return modelo;
    }
    
    public DefaultTableModel obtenerTablaProblemasActivistasPorFiltroModal(String filtro) {
        String[] columnas = {"ID", "PROBLEMA", "ACTIVISTA"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<ProblemaActivista> lista = paDAO.obtenerTodosPorFiltroModal(filtro);
        for (ProblemaActivista pa : lista) {
            modelo.addRow(new Object[]{pa.getIdProblema(), pa.getIdActivista()});
        }
        return modelo;
    }
}
