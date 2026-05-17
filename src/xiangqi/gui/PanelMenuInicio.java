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
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author hermi
 */
public class PanelMenuInicio extends JPanel {

    private static final Color FONDO = new Color(45, 32, 22);
    private static final Color DORADO = new Color(250, 220, 170);
    private static final Color TEXTO = new Color(210, 185, 145);
    private static final Color BTN_FONDO = new Color(75, 55, 40);
    private static final Color BTN_BORDE = new Color(180, 140, 90);

    private AppFrame app;
    private BaseDatos storage;

    public PanelMenuInicio(AppFrame app, BaseDatos storage) {
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
        gbc.ipadx = 120;

        JLabel lblXiangqi = new JLabel("♔  XIANGQI  ♚", SwingConstants.CENTER);
        lblXiangqi.setFont(new Font("Serif", Font.BOLD, 36));
        lblXiangqi.setForeground(DORADO);
        gbc.gridy = 0;
        gbc.ipadx = 0;
        add(lblXiangqi, gbc);

        JLabel lblSub = new JLabel("MENU DE INICIO", SwingConstants.CENTER);
        lblSub.setFont(new Font("Serif", Font.BOLD, 18));
        lblSub.setForeground(TEXTO);
        gbc.gridy = 1;
        add(lblSub, gbc);

        JButton btnLogin = crearBoton("Login");
        JButton btnCrear = crearBoton("Crear Player");
        JButton btnSalir = crearBoton("Salir");

        gbc.ipadx = 120;
        gbc.gridy = 2;
        add(btnLogin, gbc);
        gbc.gridy = 3;
        add(btnCrear, gbc);
        gbc.gridy = 4;
        add(btnSalir, gbc);

        btnLogin.addActionListener(e -> mostrarLogin());
        btnCrear.addActionListener(e -> mostrarCrear());
        btnSalir.addActionListener(e -> System.exit(0));
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

    private void mostrarLogin() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(FONDO);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel titulo = new JLabel("LOG IN", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 28));
        titulo.setForeground(DORADO);
        gbc.gridy = 0;
        gbc.ipadx = 0;
        panel.add(titulo, gbc);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUser.setForeground(TEXTO);
        gbc.gridy = 1;
        panel.add(lblUser, gbc);

        JTextField txtUser = new JTextField(15);
        txtUser.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2;
        gbc.ipadx = 150;
        panel.add(txtUser, gbc);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPass.setForeground(TEXTO);
        gbc.gridy = 3;
        gbc.ipadx = 0;
        panel.add(lblPass, gbc);

        JPasswordField txtPass = new JPasswordField(15);
        txtPass.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 4;
        gbc.ipadx = 150;
        panel.add(txtPass, gbc);

        JButton btnEntrar = crearBoton("Entrar");
        gbc.gridy = 5;
        gbc.ipadx = 80;
        panel.add(btnEntrar, gbc);

        JButton btnVolver = crearBoton("Volver");
        gbc.gridy = 6;
        panel.add(btnVolver, gbc);

        app.getContenedor().add(panel, "LOGIN");
        app.getCardLayout().show(app.getContenedor(), "LOGIN");
        app.getContenedor().revalidate();
        app.getContenedor().repaint();

        btnEntrar.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword());
            Player player = storage.buscarPlayer(user);

            if (player != null && player.getPassword().equals(pass) && player.isActivo()) {
                JOptionPane.showMessageDialog(null,"¡Login exitoso! Bienvenido " + player.getUsername(), "Login", JOptionPane.INFORMATION_MESSAGE);
                app.setPlayerLogueado(player);
                app.getContenedor().add(new PanelMenuPrincipal(app, storage), "PRINCIPAL");
                app.getContenedor().revalidate();
                app.getContenedor().repaint();
                app.mostrar("PRINCIPAL");
            } else {
                JOptionPane.showMessageDialog(null,"Usuario o password incorrecto. Intenta de nuevo.", "Error de Login", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVolver.addActionListener(e -> app.mostrar("INICIO"));
    }

    private void mostrarCrear() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(FONDO);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel titulo = new JLabel("CREAR PLAYER", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 28));
        titulo.setForeground(DORADO);
        gbc.gridy = 0;
        gbc.ipadx = 0;
        panel.add(titulo, gbc);

        JLabel lblUser = new JLabel("Username (único):");
        lblUser.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUser.setForeground(TEXTO);
        gbc.gridy = 1;
        panel.add(lblUser, gbc);

        JTextField txtUser = new JTextField(15);
        txtUser.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2;
        gbc.ipadx = 150;
        panel.add(txtUser, gbc);

        JLabel lblPass = new JLabel("Password (exactamente 5 caracteres):");
        lblPass.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPass.setForeground(TEXTO);
        gbc.gridy = 3;
        gbc.ipadx = 0;
        panel.add(lblPass, gbc);

        JPasswordField txtPass = new JPasswordField(15);
        txtPass.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 4;
        gbc.ipadx = 150;
        panel.add(txtPass, gbc);

        JButton btnCrear = crearBoton("Crear");
        gbc.gridy = 5;
        gbc.ipadx = 80;
        panel.add(btnCrear, gbc);

        JButton btnVolver = crearBoton("Volver");
        gbc.gridy = 6;
        panel.add(btnVolver, gbc);

        app.getContenedor().add(panel, "CREAR");
        app.getCardLayout().show(app.getContenedor(), "CREAR");
        app.getContenedor().revalidate();
        app.getContenedor().repaint();

        btnCrear.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword());

            if (user.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El username no puede estar vacío.","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (storage.buscarPlayer(user) != null) {
                JOptionPane.showMessageDialog(null,"Ese username ya existe, elige otro.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!Player.passwordValido(pass)) {
                JOptionPane.showMessageDialog(null,"El password debe tener exactamente 5 caracteres.","Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Player nuevo = new Player(user, pass);
            storage.crearPlayer(nuevo);
            JOptionPane.showMessageDialog(null,"¡Player creado exitosamente! Bienvenido " + nuevo.getUsername(), "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
            app.setPlayerLogueado(nuevo);
            app.getContenedor().add(new PanelMenuPrincipal(app, storage), "PRINCIPAL");
            app.getContenedor().revalidate();
            app.getContenedor().repaint();
            app.mostrar("PRINCIPAL");
        });

        btnVolver.addActionListener(e -> app.mostrar("INICIO"));
    }
}
