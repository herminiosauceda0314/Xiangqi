/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.piezas;

/**
 *
 * @author hermi
 */
public abstract class Pieza {

    protected int fila;
    protected int columna;
    protected boolean esRojo;

    public Pieza(int fila, int columna, boolean esRojo) {
        this.fila = fila;
        this.columna = columna;
        this.esRojo = esRojo;
    }

    public abstract boolean esMovimientoValido(int filaDestino, int colDestino, Pieza[][] tablero);

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean esRojo() {
        return esRojo;
    }

    public void setPos(int fila, int col) {
        this.fila = fila;
        this.columna = col;
    }
}
