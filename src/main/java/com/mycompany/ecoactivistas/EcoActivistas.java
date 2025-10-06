package com.mycompany.ecoactivistas;

import com.mycompany.ecoactivistas.config.ConexionDB;
import com.mycompany.ecoactivistas.controller.ActivistaController;
import com.mycompany.ecoactivistas.controller.ClienteController;
import com.mycompany.ecoactivistas.controller.ProblemaActivistaController;
import com.mycompany.ecoactivistas.controller.ProblemaController;
import com.mycompany.ecoactivistas.model.Activista;
import com.mycompany.ecoactivistas.model.Cliente;
import com.mycompany.ecoactivistas.model.Problema;
import com.mycompany.ecoactivistas.model.ProblemaActivista;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author martinbl
 */
import java.sql.Date;
import java.util.List;

public class EcoActivistas {

    public static void main(String[] args) {
        // ---------------- CLIENTE ----------------
        ClienteController clienteCtrl = new ClienteController();
        System.out.println("=== Pruebas Cliente ===");

        // Agregar clientes
        clienteCtrl.agregarCliente("Juan Perez", "Calle Falsa 123", "1356789013");
        clienteCtrl.agregarCliente("Maria Garcia", "Avenida Siempreviva 742", "1010101010");
        System.out.println("Clientes agregados.");

        // Listar clientes
        System.out.println("\n--- Lista de Clientes ---");
        List<Cliente> clientes = clienteCtrl.listarClientes();
        clientes.forEach(System.out::println);

        // Actualizar cliente
        if (!clientes.isEmpty()) {
            Cliente c = clientes.get(0);
            boolean actualizado = clienteCtrl.actualizarCliente(c.getIdCliente(), "Juan Perez", "Nueva Direccion 456", "3456789012");
            System.out.println("\nCliente actualizado: " + actualizado);
        }

        // Eliminar un cliente
        if (clientes.size() > 1) {
            Cliente aEliminar = clientes.get(1);
            boolean eliminado = clienteCtrl.eliminarCliente(aEliminar.getIdCliente());
            System.out.println("Cliente eliminado: " + eliminado);
        }

        System.out.println("\n--- Lista de Clientes Despues de Cambios ---");
        clienteCtrl.listarClientes().forEach(System.out::println);

        // ---------------- ACTIVISTA ----------------
        ActivistaController activistaCtrl = new ActivistaController();
        System.out.println("\n=== Pruebas Activista ===");

        // Agregar activistas
        activistaCtrl.agregarActivista("Ana Lopez", "1234567890", Date.valueOf("2024-01-15"));
        activistaCtrl.agregarActivista("Carlos Ruiz", "2345678901", Date.valueOf("2023-11-20"));
        System.out.println("Activistas agregados.");

        // Listar activistas
        System.out.println("\n--- Lista de Activistas ---");
        List<Activista> activistas = activistaCtrl.listarActivistas();
        activistas.forEach(System.out::println);
        
        // Actualizar activista
        if (!activistas.isEmpty()) {
            Activista act = activistas.get(0);
            activistaCtrl.actualizarActivista(act.getIdActivista(), "Ana Lopez", "1234567899", act.getFchIngreso());
            System.out.println("\nActivista actualizado.");
        }
        
        System.out.println("\n--- Lista de Activistas Despues de Actualizar ---");
        activistaCtrl.listarActivistas().forEach(System.out::println);


        // ---------------- PROBLEMA ----------------
        ProblemaController problemaCtrl = new ProblemaController();
        System.out.println("\n=== Pruebas Problema ===");
        
        int idClienteParaProblema = -1;
        List<Cliente> clientesActuales = clienteCtrl.listarClientes();
        if(!clientesActuales.isEmpty()){
            idClienteParaProblema = clientesActuales.get(0).getIdCliente();
        }

        // Agregar problemas
        if(idClienteParaProblema != -1){
            problemaCtrl.agregarProblema(Date.valueOf("2025-10-01"), null, "pendiente", idClienteParaProblema, "Contaminacion de rio");
            problemaCtrl.agregarProblema(Date.valueOf("2025-11-05"), null, "pendiente", idClienteParaProblema, "Tala ilegal de arboles");
            System.out.println("Problemas agregados.");
        }

        // Listar problemas
        System.out.println("\n--- Lista de Problemas ---");
        List<Problema> problemas = problemaCtrl.listarProblemas();
        problemas.forEach(System.out::println);

        // Actualizar estado de un problema
        if (!problemas.isEmpty()) {
            Problema p = problemas.get(0);
            boolean actualizado = problemaCtrl.actualizarProblema(p.getIdProblema(), p.getFchIni(), Date.valueOf("2025-12-20"), "concluido", p.getIdCliente(), p.getDescripcion());
            System.out.println("\nEstado de problema actualizado: " + actualizado);
        }

        System.out.println("\n--- Lista de Problemas Despues de Cambios ---");
        problemaCtrl.listarProblemas().forEach(System.out::println);

        // ---------------- PROBLEMA-ACTIVISTA ----------------
        ProblemaActivistaController paCtrl = new ProblemaActivistaController();
        System.out.println("\n=== Pruebas Problema-Activista ===");
        
        List<Problema> problemasActivos = problemaCtrl.listarProblemas();
        List<Activista> activistasDisponibles = activistaCtrl.listarActivistas();

        if (!problemasActivos.isEmpty() && activistasDisponibles.size() >= 2) {
            Problema p = problemasActivos.get(1); // El de "Tala ilegal"
            Activista a1 = activistasDisponibles.get(0);
            Activista a2 = activistasDisponibles.get(1);

            // Asignar dos activistas al mismo problema
            paCtrl.asignarActivista(p.getIdProblema(), a1.getIdActivista());
            paCtrl.asignarActivista(p.getIdProblema(), a2.getIdActivista());
            System.out.println("\nActivistas asignados al problema " + p.getIdProblema());
            
            // Listar activistas de un problema
            System.out.println("\n--- Activistas del Problema " + p.getIdProblema() + " ---");
            List<ProblemaActivista> relaciones = paCtrl.obtenerPorProblema(p.getIdProblema());
            relaciones.forEach(rel -> System.out.println("Problema " + rel.getIdProblema() + " - Activista " + rel.getIdActivista()));

            // Eliminar una asignacion
            boolean eliminada = paCtrl.eliminarAsignacion(p.getIdProblema(), a2.getIdActivista());
            System.out.println("\nAsignacion de activista eliminada: " + eliminada);

            System.out.println("\n--- Activistas del Problema " + p.getIdProblema() + " Despues de Eliminar ---");
            paCtrl.obtenerPorProblema(p.getIdProblema()).forEach(rel -> System.out.println("Problema " + rel.getIdProblema() + " - Activista " + rel.getIdActivista()));
        }

        System.out.println("\n=== Pruebas finalizadas ===");
    }
}