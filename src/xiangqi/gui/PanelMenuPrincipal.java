/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.gui;

import xiangqi.jugador.Player;
import xiangqi.almacenamiento.AlmacenamientoImp;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;

/**
 *
 * @author hermi
 */
public class PanelMenuPrincipal extends JPanel {

    private static final Color FONDO = new Color(45, 32, 22);
    private static final Color DORADO = new Color(250, 220, 170);
    private static final Color TEXTO = new Color(210, 185, 145);
    private static final Color BTN_FONDO = new Color(75, 55, 40);
    private static final Color BTN_BORDE = new Color(180, 140, 90);

    private AppFrame app;
    private AlmacenamientoImp almacenamiento;

    public PanelMenuPrincipal(AppFrame app, AlmacenamientoImp almacenamiento) {
        this.app = app;
        this.almacenamiento = almacenamiento;
        initUI();
    }

    private void initUI() {
        setBackground(FONDO);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel lblXiangqi = new JLabel("♔  XIANGQI  ♚", SwingConstants.CENTER);
        lblXiangqi.setFont(new Font("Serif", Font.BOLD, 32));
        lblXiangqi.setForeground(DORADO);
        gbc.gridy = 0;
        gbc.ipadx = 0;
        add(lblXiangqi, gbc);

        JLabel lblBienvenida = new JLabel("BIENVENIDO: " + app.getPlayerLogueado().getUsername(),SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 16));
        lblBienvenida.setForeground(TEXTO);
        gbc.gridy = 1;
        add(lblBienvenida, gbc);

        JButton btnJugar = crearBoton("1. Jugar Xiangqi");
        JButton btnCuenta = crearBoton("2. Mi Cuenta");
        JButton btnReportes = crearBoton("3. Reportes");
        JButton btnLogout = crearBoton("4. Cerrar Sesion");

        gbc.ipadx = 120;
        gbc.gridy = 2;
        add(btnJugar, gbc);
        gbc.gridy = 3;
        add(btnCuenta, gbc);
        gbc.gridy = 4;
        add(btnReportes, gbc);
        gbc.gridy = 5;
        add(btnLogout, gbc);

        btnJugar.addActionListener(e -> abrirJuego());
        btnCuenta.addActionListener(e -> {
            app.getContenedor().add(new PanelMiCuenta(app, almacenamiento), "MICUENTA");
            app.mostrar("MICUENTA");
        });
        btnReportes.addActionListener(e -> {
            app.getContenedor().add(new PanelReportes(app, almacenamiento), "REPORTES");
            app.mostrar("REPORTES");
        });
        btnLogout.addActionListener(e -> {
            app.setPlayerLogueado(null);
            app.mostrar("INICIO");
        });
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setForeground(DORADO);
        btn.setBackground(BTN_FONDO);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(BTN_BORDE, 1));
        return btn;
    }

    private void abrirJuego() {
        java.util.ArrayList<Player> todos = almacenamiento.getTodosLosPlayers();
        todos.removeIf(p -> p.getUsername().equals(app.getPlayerLogueado().getUsername()));

        if (todos.isEmpty()) {
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(FONDO);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;

            JLabel msg = new JLabel("No hay otros jugadores registrados.", SwingConstants.CENTER);
            msg.setForeground(TEXTO);
            msg.setFont(new Font("Arial", Font.PLAIN, 14));
            gbc.gridy = 0;
            panel.add(msg, gbc);

            JButton btnVolver = crearBoton("Volver");
            gbc.gridy = 1;
            panel.add(btnVolver, gbc);

            btnVolver.addActionListener(e -> app.mostrar("PRINCIPAL"));
            app.getContenedor().add(panel, "NOJUGADORES");
            app.mostrar("NOJUGADORES");
            return;
        }

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(FONDO);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel titulo = new JLabel("Selecciona tu oponente:", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 22));
        titulo.setForeground(DORADO);
        gbc.gridy = 0;
        gbc.ipadx = 0;
        panel.add(titulo, gbc);

        for (int i = 0; i < todos.size(); i++) {
            Player oponente = todos.get(i);
            JButton btnOponente = crearBoton(oponente.getUsername());
            gbc.gridy = i + 1;
            gbc.ipadx = 100;
            panel.add(btnOponente, gbc);

            btnOponente.addActionListener(e -> {
                app.getContenedor().add(
                        new PanelTablero(app, almacenamiento, app.getPlayerLogueado(), oponente),
                        "TABLERO"
                );
                app.mostrar("TABLERO");
            });
        }

        JButton btnVolver = crearBoton("Volver");
        gbc.gridy = todos.size() + 1;
        gbc.ipadx = 100;
        panel.add(btnVolver, gbc);
        btnVolver.addActionListener(e -> app.mostrar("PRINCIPAL"));

        app.getContenedor().add(panel, "SELECCIONAROPONENTE");
        app.mostrar("SELECCIONAROPONENTE");
    }
}
