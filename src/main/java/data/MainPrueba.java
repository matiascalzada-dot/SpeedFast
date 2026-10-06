package data;

import java.sql.Connection;
import java.sql.SQLException;
import data.PedidoDAO;

public class MainPrueba {

    public static void main(String[] args) {


        try {
            Connection conexion = ConexionDB.conectar();

            System.out.println("¡Conexión exitosa a MySQL!");

            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error al conectar con MySQL:");
            e.printStackTrace();
        }
    }
}