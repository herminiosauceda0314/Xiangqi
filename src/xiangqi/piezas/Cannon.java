/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.piezas;

/**
 *
 * @author hermi
 */
public class Cannon extends PiezaLineal {

    public Cannon(int fila, int columna, boolean esRojo) {
        super(fila, columna, esRojo);
    }

    @Override
    public boolean esMovimientoValido(int filaDestino, int colDestino, Pieza[][] tablero) {
        if (!esMovimientoRecto(filaDestino, colDestino)) {
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
}
