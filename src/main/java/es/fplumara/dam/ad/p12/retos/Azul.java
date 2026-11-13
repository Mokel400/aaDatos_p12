package es.fplumara.dam.ad.p12.retos;

import es.fplumara.dam.ad.p12.modelo.Videojuego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static es.fplumara.dam.ad.p12.Principal.crearVideojuego;

public class Azul {

    public static List<Videojuego> buscarMultijugador(Connection conexion, int jugadoresMinimos) throws SQLException{
        List<Videojuego> videojuegosConMinimoJugadores = new ArrayList<>();
        String sql = "SELECT id, titulo, max_jugadores, puntuacion, descripcion FROM videojuego WHERE max_jugadores >= ? ORDER BY max_jugadores DESC, id";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, jugadoresMinimos);
            try (ResultSet resultado = preparedStatement.executeQuery()) {
                videojuegosConMinimoJugadores.add(crearVideojuego(resultado));
            }
        }
        return videojuegosConMinimoJugadores;
    }
}
