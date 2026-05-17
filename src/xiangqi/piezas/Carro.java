/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.piezas;

/**
 *
 * @author hermi
 */
public class Carro extends PiezaLineal {

    public Carro(int fila, int columna, boolean esRojo) {
        super(fila, columna, esRojo);
    }

    @Override
    public boolean esMovimientoValido(int filaDestino, int colDestino, Pieza[][] tablero) {
        if (!esMovimientoRecto(filaDestino, colDestino)) {
            return false;
        }
        if (contarPiezasEnMedio(filaDestino, colDestino, tablero) != 0) {
            return false;
        }
        Pieza destino = tablero[filaDestino][colDestino];
        if (destino != null && destino.esRojo() == this.esRojo) {
            return false;
        }
        return true;
    }
}
