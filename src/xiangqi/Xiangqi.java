/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xiangqi;

import javax.swing.SwingUtilities;
import xiangqi.gui.AppFrame;
import xiangqi.almacenamiento.AlmacenamientoImp;

/**
 *
 * @author hermi
 */
public class Xiangqi {

    public static void main(String[] args) {
        AlmacenamientoImp storage = new AlmacenamientoImp();

        SwingUtilities.invokeLater(() -> {
            new AppFrame(storage);
        });
    }
}
