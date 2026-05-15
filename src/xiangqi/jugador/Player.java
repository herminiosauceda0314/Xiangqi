/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.jugador;

import java.time.LocalDate;

/**
 *
 * @author hermi
 */
public class Player {

    private String username;
    private String password;
    private int puntos;
    private String fechaIngreso;
    private boolean activo;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.puntos = 0;
        this.fechaIngreso = LocalDate.now().toString();
        this.activo = true;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void ganarPartida() {
        this.puntos += 3;
    }

    public static boolean passwordValido(String pass) {
        return pass != null && pass.length() == 5;
    }
}
