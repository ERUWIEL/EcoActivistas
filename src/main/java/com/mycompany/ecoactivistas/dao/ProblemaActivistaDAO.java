/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecoactivistas.dao;

import com.mycompany.ecoactivistas.config.ConexionDB;
import com.mycompany.ecoactivistas.interfaces.IProblemaActivistaDAO;
import com.mycompany.ecoactivistas.model.ProblemaActivista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martinbl
 */
public class ProblemaActivistaDAO implements IProblemaActivistaDAO {

    @Override
    public boolean insertar(ProblemaActivista relacion) {
        String sql = "INSERT INTO Problema_Activista (idProblema, idActivista) VALUES (?, ?)";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, relacion.getIdProblema());
            ps.setInt(2, relacion.getIdActivista());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar relación Problema-Activista: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<ProblemaActivista> obtenerPorProblema(int idProblema) {
        String sql = "SELECT idProblema, idActivista FROM Problema_Activista WHERE idProblema = ? LIMIT 100";
        List<ProblemaActivista> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProblema);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProblemaActivista relacion = new ProblemaActivista();
                relacion.setIdProblema(rs.getInt("idProblema"));
                relacion.setIdActivista(rs.getInt("idActivista"));
                lista.add(relacion);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener relaciones por problema: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<ProblemaActivista> obtenerPorActivista(int idActivista) {
        String sql = "SELECT idProblema,idActivista  FROM Problema_Activista WHERE idActivista = ? LIMIT 100";
        List<ProblemaActivista> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idActivista);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProblemaActivista relacion = new ProblemaActivista();
                relacion.setIdProblema(rs.getInt("idProblema"));
                relacion.setIdActivista(rs.getInt("idActivista"));
                lista.add(relacion);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener relaciones por activista: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean eliminar(int idProblema, int idActivista) {
        String sql = "DELETE FROM Problema_Activista WHERE idProblema = ? AND idActivista = ?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProblema);
            ps.setInt(2, idActivista);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar relación Problema-Activista: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<ProblemaActivista> obtenerTodos() {
        String sql = "SELECT idProblema, idActivista FROM Problema_Activista LIMIT 100";
        List<ProblemaActivista> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProblemaActivista problemaActivista = new ProblemaActivista();
                problemaActivista.setIdProblema(rs.getInt("idProblema"));
                problemaActivista.setIdActivista(rs.getInt("idActivista"));
                lista.add(problemaActivista);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todos los problemaActivista: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<ProblemaActivista> obtenerTodosPorFiltro(String filtro) {
        String sql = "SELECT idProblema, idActivista FROM Problema_Activista WHERE idProblema LIKE ? LIMIT 100";
        List<ProblemaActivista> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProblemaActivista problemaActivista = new ProblemaActivista();
                problemaActivista.setIdProblema(rs.getInt("idProblema"));
                problemaActivista.setIdActivista(rs.getInt("idActivista"));
                lista.add(problemaActivista);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todos los problemaActivista: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<ProblemaActivista> obtenerTodosPorFiltroModal(String filtro) {
        String sql = "SELECT idProblema, idActivista FROM Problema_Activista WHERE idProblema LIKE ? OR idActivista LIKE ? LIMIT 100";
        List<ProblemaActivista> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProblemaActivista problemaActivista = new ProblemaActivista();
                problemaActivista.setIdProblema(rs.getInt("idProblema"));
                problemaActivista.setIdActivista(rs.getInt("idActivista"));
                lista.add(problemaActivista);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todos los problemaActivista: " + e.getMessage());
        }
        return lista;
    }

}
