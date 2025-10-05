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
public class EcoActivistas {

    public static void main(String[] args) {
        // ---------------- CLIENTE ----------------
        ClienteController clienteCtrl = new ClienteController();
        System.out.println("=== Pruebas Cliente ===");

        // Agregar cliente
        boolean agregado = clienteCtrl.agregarCliente("Juan Pérez", "Calle Falsa 123", "555-1234");
        System.out.println("Cliente agregado: " + agregado);

        // Listar clientes
        List<Cliente> clientes = clienteCtrl.listarClientes();
        clientes.forEach(System.out::println);

        // Actualizar cliente
        if (!clientes.isEmpty()) {
            Cliente c = clientes.get(0);
            boolean actualizado = clienteCtrl.actualizarCliente(c.getIdCliente(), "Juan P.", "Av. Siempre Viva 742", "555-4321");
            System.out.println("Cliente actualizado: " + actualizado);
        }

        // ---------------- ACTIVISTA ----------------
        ActivistaController activistaCtrl = new ActivistaController();
        System.out.println("\n=== Pruebas Activista ===");

        // Agregar activista
        boolean activistaAgregado = activistaCtrl.agregarActivista("Ana López", "555-9876", Date.valueOf("2025-01-01"));
        System.out.println("Activista agregado: " + activistaAgregado);

        // Listar activistas
        List<Activista> activistas = activistaCtrl.listarActivistas();
        activistas.forEach(System.out::println);

        // ---------------- PROBLEMA ----------------
        ProblemaController problemaCtrl = new ProblemaController();
        System.out.println("\n=== Pruebas Problema ===");

      

        List<Problema> problemas = problemaCtrl.listarProblemas();
        problemas.forEach(System.out::println);

        // ---------------- PROBLEMA-ACTIVISTA ----------------
        ProblemaActivistaController paCtrl = new ProblemaActivistaController();
        System.out.println("\n=== Pruebas Problema-Activista ===");

        if (!problemas.isEmpty() && !activistas.isEmpty()) {
            Problema p = problemas.get(0);
            Activista a = activistas.get(0);

            boolean asignado = paCtrl.asignarActivista(p.getIdProblema(), a.getIdActivista());
            System.out.println("Activista asignado a problema: " + asignado);

            List<ProblemaActivista> relaciones = paCtrl.obtenerPorProblema(p.getIdProblema());
            relaciones.forEach(rel -> System.out.println("Problema " + rel.getIdProblema() + " - Activista " + rel.getIdActivista()));
        }

        System.out.println("\n=== Pruebas finalizadas ===");
    }
}
