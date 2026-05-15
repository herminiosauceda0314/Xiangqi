/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.modelo;

/**
 *
 * @author hermi
 */
public class Cannon extends Pieza {

    public Cannon(int fila, int columna, boolean esRojo) {
        super(fila, columna, esRojo);
    }

    @Override
    public boolean esMovimientoValido(int filaDestino, int colDestino, Pieza[][] tablero) {
        if (fila != filaDestino && columna != colDestino) {
            return false;
        }

        int piezasEnMedio = contarPiezasEnMedio(filaDestino, colDestino, tablero);
        Pieza destino = tablero[filaDestino][colDestino];

        if (destino == null) {
            return piezasEnMedio == 0;
        } else {
            return piezasEnMedio == 1 && destino.esRojo() != this.esRojo;
        }
    }

    private int contarPiezasEnMedio(int filaDestino, int colDestino, Pieza[][] tablero) {
        int count = 0;
        if (fila == filaDestino) {
            int inicio = Math.min(columna, colDestino) + 1;
            int fin = Math.max(columna, colDestino);
            for (int c = inicio; c < fin; c++) {
                if (tablero[fila][c] != null) {
                    count++;
                }
            }
        } else {
            int inicio = Math.min(fila, filaDestino) + 1;
            int fin = Math.max(fila, filaDestino);
            for (int f = inicio; f < fin; f++) {
                if (tablero[f][columna] != null) {
                    count++;
                }
            }
        }
        return count;
    }
}
