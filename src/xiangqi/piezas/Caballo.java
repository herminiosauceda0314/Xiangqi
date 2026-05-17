/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.piezas;

/**
 *
 * @author hermi
 */
public class Caballo extends Pieza {

    public Caballo(int fila, int columna, boolean esRojo) {
        super(fila, columna, esRojo);
    }

    @Override
    public boolean esMovimientoValido(int filaDestino, int colDestino, Pieza[][] tablero) {
        int difFila = Math.abs(filaDestino - fila);
        int difCol = Math.abs(colDestino - columna);

        if (!((difFila == 2 && difCol == 1) || (difFila == 1 && difCol == 2))) {
            return false;
        }

        if (difFila == 2) {
            int filaMedia = fila + (filaDestino > fila ? 1 : -1);
            if (tablero[filaMedia][columna] != null) {
                return false;
            }
        } else {
            int colMedia = columna + (colDestino > columna ? 1 : -1);
            if (tablero[fila][colMedia] != null) {
                return false;
            }
        }

        Pieza destino = tablero[filaDestino][colDestino];
        if (destino != null && destino.esRojo() == this.esRojo) {
            return false;
        }

        return true;
    }
}
