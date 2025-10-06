/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ecoactivistas.interfaces;

import com.mycompany.ecoactivistas.model.ProblemaActivista;
import java.util.List;

/**
 *
 * @author martinbl
 */
public interface IProblemaActivistaDAO {
    boolean insertar(ProblemaActivista relacion);
    List<ProblemaActivista> obtenerPorProblema(int idProblema);
    List<ProblemaActivista> obtenerPorActivista(int idActivista);
    boolean eliminar(int idProblema, int idActivista);
    List<ProblemaActivista> obtenerTodos();
    List<ProblemaActivista> obtenerTodosPorFiltro(String filtro);
    List<ProblemaActivista> obtenerTodosPorFiltroModal(String filtro);
}


