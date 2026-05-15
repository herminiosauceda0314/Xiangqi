/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.gui;
import xiangqi.jugador.Player;
import xiangqi.storage.StorageImpl;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;

/**
 *
 * @author hermi
 */
public class AppFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel contenedor;
    private StorageImpl storage;
    private Player playerLogueado;

    public AppFrame(StorageImpl storage) {
        this.storage= storage;
        this.cardLayout= new CardLayout();
        this.contenedor= new JPanel(cardLayout);
        initUI();
    }

    private void initUI() {
        setTitle("Xiangqi - Ajedrez Chino");
        setSize(750, 700);//setSize(525, 650); // era 450, 500
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        javax.swing.UIManager.put("OptionPane.background",        new Color(30, 30, 30));
        javax.swing.UIManager.put("Panel.background",             new Color(30, 30, 30));
        javax.swing.UIManager.put("OptionPane.messageForeground", new Color(255, 215, 0));
        javax.swing.UIManager.put("Button.background",            new Color(60, 60, 60));
        javax.swing.UIManager.put("Button.foreground",            new Color(255, 215, 0));

        contenedor.add(new PanelMenuInicio(this, storage), "INICIO");

        add(contenedor);

        cardLayout.show(contenedor, "INICIO");

        setVisible(true);
    }


    public void mostrar(String pantalla) {
        cardLayout.show(contenedor, pantalla);
    }

    public void setPlayerLogueado(Player p) {
        this.playerLogueado = p;
    }

    public Player getPlayerLogueado() {
        return playerLogueado;
    }

    public StorageImpl getStorage() {
        return storage;
    }

    public JPanel getContenedor() {
        return contenedor;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
    
    
}
