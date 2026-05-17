/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.piezas;

/**
 *
 * @author hermi
 */
public final class Soldado extends Pieza {

    public Soldado(int fila, int columna, boolean esRojo) {
        super(fila, columna, esRojo);
    }

    @Override
    public final boolean esMovimientoValido(int filaDestino, int colDestino, Pieza[][] tablero) {
        int difFila = filaDestino - fila;
        int difCol = Math.abs(colDestino - columna);

        if (esRojo) {
            boolean cruzoRio = fila <= 4;
            if (!cruzoRio) {
                return difFila == -1 && difCol == 0;
            } else {
                return (difFila == -1 && difCol == 0) || (difFila == 0 && difCol == 1);
            }
        } else {
            boolean cruzoRio = fila >= 5;
            if (!cruzoRio) {
                return difFila == 1 && difCol == 0;
            } else {
                return (difFila == 1 && difCol == 0) || (difFila == 0 && difCol == 1);
            }
        }
    }
}
