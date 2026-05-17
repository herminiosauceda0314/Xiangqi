/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.gui;

import xiangqi.jugador.Player;
import xiangqi.datos.BaseDatos;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author hermi
 */
public class PanelMiCuenta extends JPanel {

    private static final Color FONDO = new Color(45, 32, 22);
    private static final Color DORADO = new Color(250, 220, 170);
    private static final Color TEXTO = new Color(210, 185, 145);
    private static final Color BTN_FONDO = new Color(75, 55, 40);
    private static final Color BTN_BORDE = new Color(180, 140, 90);

    private AppFrame app;
    private BaseDatos storage;

    public PanelMiCuenta(AppFrame app, BaseDatos storage) {
        this.app = app;
        this.storage = storage;
        initUI();
    }

    private void initUI() {
        setBackground(FONDO);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        Player player = app.getPlayerLogueado();

        JLabel titulo = new JLabel("MI CUENTA", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 28));
        titulo.setForeground(DORADO);
        gbc.gridy = 0;
        gbc.ipadx = 0;
        add(titulo, gbc);

        JLabel lblUser = crearDato("Usuario:        " + player.getUsername());
        JLabel lblPuntos = crearDato("Puntos:         " + player.getPuntos());
        JLabel lblFecha = crearDato("Fecha ingreso:  " + player.getFechaIngreso());

        gbc.gridy = 1;
        add(lblUser, gbc);
        gbc.gridy = 2;
        add(lblPuntos, gbc);
        gbc.gridy = 3;
        add(lblFecha, gbc);

        JButton btnCambiarPass = crearBoton("Cambiar Password");
        JButton btnEliminar = crearBoton("Eliminar Mi Cuenta");
        JButton btnVolver = crearBoton("Volver");

        gbc.ipadx = 120;
        gbc.gridy = 4;
        add(btnCambiarPass, gbc);
        gbc.gridy = 5;
        add(btnEliminar, gbc);
        gbc.gridy = 6;
        add(btnVolver, gbc);

        btnCambiarPass.addActionListener(e -> mostrarCambiarPassword());
        btnEliminar.addActionListener(e -> mostrarEliminarCuenta());
        btnVolver.addActionListener(e -> app.mostrar("PRINCIPAL"));
    }

    private JLabel crearDato(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Monospaced", Font.PLAIN, 14));
        lbl.setForeground(TEXTO);
        return lbl;
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

    private void mostrarCambiarPassword() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(FONDO);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel titulo = new JLabel("CAMBIAR PASSWORD", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setForeground(DORADO);
        gbc.gridy = 0;
        gbc.ipadx = 0;
        panel.add(titulo, gbc);

        JLabel lblActual = new JLabel("Password actual:");
        lblActual.setForeground(TEXTO);
        lblActual.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        panel.add(lblActual, gbc);

        JPasswordField txtActual = new JPasswordField(15);
        gbc.gridy = 2;
        gbc.ipadx = 150;
        panel.add(txtActual, gbc);

        JLabel lblNuevo = new JLabel("Nuevo password (5 caracteres):");
        lblNuevo.setForeground(TEXTO);
        lblNuevo.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 3;
        gbc.ipadx = 0;
        panel.add(lblNuevo, gbc);

        JPasswordField txtNuevo = new JPasswordField(15);
        gbc.gridy = 4;
        gbc.ipadx = 150;
        panel.add(txtNuevo, gbc);

        JButton btnGuardar = crearBoton("Guardar");
        gbc.gridy = 5;
        gbc.ipadx = 80;
        panel.add(btnGuardar, gbc);

        JButton btnVolver = crearBoton("Volver");
        gbc.gridy = 6;
        panel.add(btnVolver, gbc);

        app.getContenedor().add(panel, "CAMBIARPASS");
        app.mostrar("CAMBIARPASS");

        btnGuardar.addActionListener(e -> {
            String actual = new String(txtActual.getPassword());
            String nuevo = new String(txtNuevo.getPassword());
            Player player = app.getPlayerLogueado();

            if (!actual.equals(player.getPassword())) {
                JOptionPane.showMessageDialog(null, "Password actual incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!Player.passwordValido(nuevo)) {
                JOptionPane.showMessageDialog(null, "El nuevo password debe tener exactamente 5 caracteres.","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            player.setPassword(nuevo);
            storage.actualizarPlayer(player);
            JOptionPane.showMessageDialog(null, "¡Password cambiado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            app.getContenedor().add(new PanelMiCuenta(app, storage), "MICUENTA");
            app.mostrar("MICUENTA");
        });

        btnVolver.addActionListener(e -> {
            app.getContenedor().add(new PanelMiCuenta(app, storage), "MICUENTA");
            app.mostrar("MICUENTA");
        });
    }

    private void mostrarEliminarCuenta() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(FONDO);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel titulo = new JLabel("ELIMINAR MI CUENTA", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setForeground(DORADO);
        gbc.gridy = 0;
        gbc.ipadx = 0;
        panel.add(titulo, gbc);

        JLabel lblConfirm = new JLabel("Ingresa tu password para confirmar:");
        lblConfirm.setForeground(TEXTO);
        lblConfirm.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        panel.add(lblConfirm, gbc);

        JPasswordField txtPass = new JPasswordField(15);
        gbc.gridy = 2;
        gbc.ipadx = 150;
        panel.add(txtPass, gbc);

        JButton btnEliminar = crearBoton("Confirmar Eliminar");
        btnEliminar.setForeground(Color.RED);
        gbc.gridy = 3;
        gbc.ipadx = 80;
        panel.add(btnEliminar, gbc);

        JButton btnVolver = crearBoton("Volver");
        gbc.gridy = 4;
        panel.add(btnVolver, gbc);

        app.getContenedor().add(panel, "ELIMINARCUENTA");
        app.mostrar("ELIMINARCUENTA");

        btnEliminar.addActionListener(e -> {
            String pass = new String(txtPass.getPassword());
            Player player = app.getPlayerLogueado();

            if (!pass.equals(player.getPassword())) {
                JOptionPane.showMessageDialog(null,"Password incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            storage.eliminarPlayer(player.getUsername());
            JOptionPane.showMessageDialog(null, "Cuenta eliminada exitosamente.", "Cuenta Eliminada", JOptionPane.INFORMATION_MESSAGE);
            app.setPlayerLogueado(null);
            app.mostrar("INICIO");
        });

        btnVolver.addActionListener(e -> {
            app.getContenedor().add(new PanelMiCuenta(app, storage), "MICUENTA");
            app.mostrar("MICUENTA");
        });
    }
}
