/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.modelo;

/**
 *
 * @author hermi
 */
public class Carro extends Pieza {

    public Carro(int fila, int columna, boolean esRojo) {
        super(fila, columna, esRojo);
    }

    @Override
    public boolean esMovimientoValido(int filaDestino, int colDestino, Pieza[][] tablero) {
        if (fila != filaDestino && columna != colDestino) {
            return false;
        }

        if (!caminoLibre(filaDestino, colDestino, tablero)) {
            return false;
        }

        Pieza destino = tablero[filaDestino][colDestino];
        if (destino != null && destino.esRojo() == this.esRojo) {
            return false;
        }

        return true;
    }

    private boolean caminoLibre(int filaDestino, int colDestino, Pieza[][] tablero) {
        if (fila == filaDestino) {
            int inicio = Math.min(columna, colDestino) + 1;
            int fin = Math.max(columna, colDestino);
            for (int c = inicio; c < fin; c++) {
                if (tablero[fila][c] != null) {
                    return false;
                }
            }
        } else {
            int inicio = Math.min(fila, filaDestino) + 1;
            int fin = Math.max(fila, filaDestino);
            for (int f = inicio; f < fin; f++) {
                if (tablero[f][columna] != null) {
                    return false;
                }
            }
        }
        return true;
    }
}
