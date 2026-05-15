/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.modelo;

/**
 *
 * @author hermi
 */
public class Elefante extends Pieza {

    public Elefante(int fila, int columna, boolean esRojo) {
        super(fila, columna, esRojo);
    }

    @Override
    public boolean esMovimientoValido(int filaDestino, int colDestino, Pieza[][] tablero) {
        int difFila = Math.abs(filaDestino - fila);
        int difCol = Math.abs(colDestino - columna);

        if (difFila != 2 || difCol != 2) {
            return false;
        }

        if (esRojo && filaDestino > 4) {
            return false;
        }
        if (!esRojo && filaDestino < 5) {
            return false;
        }

        int ojFila = (fila + filaDestino) / 2;
        int ojCol = (columna + colDestino) / 2;
        if (tablero[ojFila][ojCol] != null) {
            return false;
        }

        Pieza destino = tablero[filaDestino][colDestino];
        if (destino != null && destino.esRojo() == this.esRojo) {
            return false;
        }

        return true;
    }
}
