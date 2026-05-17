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
public class AlmacenamientoImp implements Almacenamiento {

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<String> logs = new ArrayList<>();

    @Override
    public void crearPlayer(Player p) {
        players.add(p);
    }

    @Override
    public Player buscarPlayer(String username) {
        return buscarPlayerR(username, 0);
    }

    private Player buscarPlayerR(String username, int num) {
        if (num >= players.size()) {
            return null;
        }
        if (players.get(num).getUsername().equalsIgnoreCase(username)) {
            return players.get(num);
        }
        return buscarPlayerR(username, num + 1);
    }

    @Override
    public void eliminarPlayer(String username) {
        Player encontrado = buscarPlayer(username);
        if (encontrado != null) {
            encontrado.setActivo(false);
        }
    }

    @Override
    public void actualizarPlayer(Player actualizado) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getUsername().equals(actualizado.getUsername())) {
                players.set(i, actualizado);
                return;
            }
        }
    }

    @Override
    public ArrayList<Player> getTodosLosPlayers() {
        ArrayList<Player> activos = new ArrayList<>();
        for (Player p : players) {
            if (p.isActivo()) {
                activos.add(p);
            }
        }
        return activos;
    }

    @Override
    public void guardarLog(String username, String mensaje) {
        logs.add(0, username + " | " + mensaje);
    }

    @Override
    public ArrayList<String> getLogsDePlayer(String username) {
        ArrayList<String> resultado = new ArrayList<>();
        for (String log : logs) {
            String[] partes = log.split("\\|");
            if (partes[0].trim().equals(username)) {
                resultado.add(partes[1].trim());
            }
        }
        return resultado;
    }

    public int contarLogsDePlayer(String username, int indice) {
        if (indice >= logs.size()) {
            return 0;
        }
        String[] partes = logs.get(indice).split("\\|");
        int cuenta = partes[0].trim().equals(username) ? 1 : 0;
        return cuenta + contarLogsDePlayer(username, indice + 1);
    }
}
