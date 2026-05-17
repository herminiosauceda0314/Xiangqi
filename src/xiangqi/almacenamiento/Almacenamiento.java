/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.almacenamiento;

import java.util.ArrayList;
import xiangqi.jugador.Player;

/**
 *
 * @author hermi
 */
public interface Almacenamiento {

    //de player
    void crearPlayer(Player p);

    Player buscarPlayer(String username);

    void eliminarPlayer(String username);

    void actualizarPlayer(Player p);

    ArrayList<Player> getTodosLosPlayers();

    //logs
    void guardarLog(String username, String log);

    ArrayList<String> getLogsDePlayer(String username);

}
