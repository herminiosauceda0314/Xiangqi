/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.piezas;

/**
 *
 * @author hermi
 */
public final class General extends Pieza {

    public General(int fila, int columna, boolean esRojo) {
        super(fila, columna, esRojo);
    }

    @Override
    public boolean esMovimientoValido(int filaDestino, int colDestino, Pieza[][] tablero) {
        int difFila = Math.abs(filaDestino - fila);
        int difCol = Math.abs(colDestino - columna);

        if (!((difFila == 1 && difCol == 0) || (difFila == 0 && difCol == 1))) {
            return false;
        }

        if (!enPalacio(filaDestino, colDestino)) {
            return false;
        }

        Pieza destino = tablero[filaDestino][colDestino];
        if (destino != null && destino.esRojo() == this.esRojo) {
            return false;
        }
        return true;
    }

    private boolean enPalacio(int f, int c) {
        boolean colValida = (c >= 3 && c <= 5);
        if (esRojo) {
            return colValida && (f >= 7 && f <= 9);
        } else {
            return colValida && (f >= 0 && f <= 2);
        }
    }
}
