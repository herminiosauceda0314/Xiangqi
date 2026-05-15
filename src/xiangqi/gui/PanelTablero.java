/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.gui;

import xiangqi.jugador.Player;
import xiangqi.modelo.*;
import xiangqi.storage.StorageImpl;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;

/**
 *
 * @author hermi
 */
public class PanelTablero extends JPanel {

    private static final int FILAS = 10;
    private static final int COLS = 9;

    private static final Color COLOR_CLARO = new Color(240, 217, 181);
    private static final Color COLOR_OSCURO = new Color(100, 140, 80);
    private static final Color COLOR_SEL = new Color(255, 255, 100);
    private static final Color COLOR_MOVIBLE = new Color(100, 220, 100);
    private static final Color COLOR_RIO_BORDE = new Color(100, 160, 220);

    private AppFrame app;
    private StorageImpl storage;
    private Player jugadorRojo;
    private Player jugadorNegro;

    private Pieza[][] tablero = new Pieza[FILAS][COLS];
    private JButton[][] celdas = new JButton[FILAS][COLS];
    private int filaSeleccionada = -1;
    private int colSeleccionada = -1;
    private boolean turnoRojo = true;

    private ArrayList<Pieza> piezasComidasPorRojo = new ArrayList<>();
    private ArrayList<Pieza> piezasComidasPorNegro = new ArrayList<>();

    private JLabel lblTurno;
    private JPanel panelComidasRojo;
    private JPanel panelComidasNegro;

    public PanelTablero(AppFrame app, StorageImpl storage,
            Player jugadorRojo, Player jugadorNegro) {
        this.app = app;
        this.storage = storage;
        this.jugadorRojo = jugadorRojo;
        this.jugadorNegro = jugadorNegro;
        inicializarTablero();
        initUI();
    }

    private void inicializarTablero() {
        tablero[0][0] = new Carro(0, 0, false);
        tablero[0][1] = new Caballo(0, 1, false);
        tablero[0][2] = new Elefante(0, 2, false);
        tablero[0][3] = new Oficial(0, 3, false);
        tablero[0][4] = new General(0, 4, false);
        tablero[0][5] = new Oficial(0, 5, false);
        tablero[0][6] = new Elefante(0, 6, false);
        tablero[0][7] = new Caballo(0, 7, false);
        tablero[0][8] = new Carro(0, 8, false);
        tablero[2][1] = new Cannon(2, 1, false);
        tablero[2][7] = new Cannon(2, 7, false);
        tablero[3][0] = new Soldado(3, 0, false);
        tablero[3][2] = new Soldado(3, 2, false);
        tablero[3][4] = new Soldado(3, 4, false);
        tablero[3][6] = new Soldado(3, 6, false);
        tablero[3][8] = new Soldado(3, 8, false);

        tablero[9][0] = new Carro(9, 0, true);
        tablero[9][1] = new Caballo(9, 1, true);
        tablero[9][2] = new Elefante(9, 2, true);
        tablero[9][3] = new Oficial(9, 3, true);
        tablero[9][4] = new General(9, 4, true);
        tablero[9][5] = new Oficial(9, 5, true);
        tablero[9][6] = new Elefante(9, 6, true);
        tablero[9][7] = new Caballo(9, 7, true);
        tablero[9][8] = new Carro(9, 8, true);
        tablero[7][1] = new Cannon(7, 1, true);
        tablero[7][7] = new Cannon(7, 7, true);
        tablero[6][0] = new Soldado(6, 0, true);
        tablero[6][2] = new Soldado(6, 2, true);
        tablero[6][4] = new Soldado(6, 4, true);
        tablero[6][6] = new Soldado(6, 6, true);
        tablero[6][8] = new Soldado(6, 8, true);
    }

    private void initUI() {
        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(30, 30, 30));

        JPanel panelNorte = new JPanel(new GridBagLayout());
        panelNorte.setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8, 0, 2, 0);

        JLabel lblTitulo = new JLabel("XIANGQI", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(255, 215, 0));
        panelNorte.add(lblTitulo, gbc);

        lblTurno = new JLabel(getTurnoTexto(), SwingConstants.CENTER);
        lblTurno.setFont(new Font("Arial", Font.BOLD, 16));
        lblTurno.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.insets = new Insets(2, 0, 8, 0);
        panelNorte.add(lblTurno, gbc);

        JPanel panelTablero = new JPanel(new GridLayout(FILAS, COLS));
        panelTablero.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLS; c++) {
                JButton celda = new JButton();
                celda.setFocusPainted(false);
                celda.setPreferredSize(new java.awt.Dimension(68, 68));

                celda.setBackground(getColorCelda(f, c));

                if (f == 4) {
                    celda.setBorder(BorderFactory.createMatteBorder(
                            1, 1, 3, 1, Color.DARK_GRAY));
                } else if (f == 5) {
                    celda.setBorder(BorderFactory.createMatteBorder(
                            3, 1, 1, 1, Color.DARK_GRAY));
                } else {
                    celda.setBorder(BorderFactory.createMatteBorder(
                            1, 1, 1, 1, Color.DARK_GRAY));
                }

                actualizarCelda(celda, f, c);

                final int fila = f;
                final int col = c;
                celda.addActionListener(e -> manejarClick(fila, col));

                celdas[f][c] = celda;
                panelTablero.add(celda);
            }
        }

        JPanel panelNumeros = new JPanel(new GridLayout(FILAS, 1));
        panelNumeros.setBackground(new Color(30, 30, 30));
        for (int f = 0; f < FILAS; f++) {
            JLabel lbl = new JLabel(String.valueOf(FILAS - f), SwingConstants.CENTER);
            lbl.setFont(new Font("Arial", Font.BOLD, 12));
            lbl.setForeground(new Color(255, 215, 0));
            lbl.setPreferredSize(new java.awt.Dimension(25, 68));
            panelNumeros.add(lbl);
        }

        JPanel panelLetras = new JPanel(new GridLayout(1, COLS));
        panelLetras.setBackground(new Color(30, 30, 30));
        String[] letras = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
        for (String letra : letras) {
            JLabel lbl = new JLabel(letra, SwingConstants.CENTER);
            lbl.setFont(new Font("Arial", Font.BOLD, 12));
            lbl.setForeground(new Color(255, 215, 0));
            panelLetras.add(lbl);
        }

        JPanel panelTableroYNumeros = new JPanel(new BorderLayout());
        panelTableroYNumeros.setBackground(new Color(30, 30, 30));
        panelTableroYNumeros.add(panelTablero, BorderLayout.CENTER);
        panelTableroYNumeros.add(panelNumeros, BorderLayout.EAST);

        JPanel espacioLetras = new JPanel();
        espacioLetras.setBackground(new Color(30, 30, 30));
        espacioLetras.setPreferredSize(new java.awt.Dimension(25, 20));

        JPanel panelLetrasConMargen = new JPanel(new BorderLayout());
        panelLetrasConMargen.setBackground(new Color(30, 30, 30));
        panelLetrasConMargen.add(panelLetras, BorderLayout.CENTER);
        panelLetrasConMargen.add(espacioLetras, BorderLayout.EAST);

        JPanel panelConCoordenadas = new JPanel(new BorderLayout());
        panelConCoordenadas.setBackground(new Color(30, 30, 30));
        panelConCoordenadas.add(panelTableroYNumeros, BorderLayout.CENTER);
        panelConCoordenadas.add(panelLetrasConMargen, BorderLayout.SOUTH);

        JPanel panelIzquierdo = crearPanelJugador(
                jugadorNegro.getUsername(),
                "♚", Color.BLACK,
                "NEGRO", Color.LIGHT_GRAY,
                false
        );

        JPanel panelDerecho = crearPanelJugador(
                jugadorRojo.getUsername(),
                "♔", new Color(180, 0, 0),
                "ROJO", new Color(255, 100, 100),
                true
        );

        // ── Contenedor con GridBagLayout ──────────────────────
        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setBackground(new Color(30, 30, 30));
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.BOTH;
        g.gridy = 0;
        g.weighty = 1;

        g.gridx = 0;
        g.weightx = 0;
        g.insets = new Insets(0, 6, 0, 6);
        contenedor.add(panelIzquierdo, g);

        g.gridx = 1;
        g.weightx = 1;
        g.insets = new Insets(0, 0, 0, 0);
        contenedor.add(panelConCoordenadas, g);

        g.gridx = 2;
        g.weightx = 0;
        g.insets = new Insets(0, 6, 0, 6);
        contenedor.add(panelDerecho, g);

        add(panelNorte, BorderLayout.NORTH);
        add(contenedor, BorderLayout.CENTER);
    }

    private JPanel crearPanelJugador(String nombre, String simbolo,
            Color colorSimbolo, String etiquetaColor, Color colorEtiqueta,
            boolean conBotonRetirar) {

        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            public java.awt.Dimension getPreferredSize() {
                return new java.awt.Dimension(130, super.getPreferredSize().height);
            }

            @Override
            public java.awt.Dimension getMinimumSize() {
                return new java.awt.Dimension(130, 0);
            }

            @Override
            public java.awt.Dimension getMaximumSize() {
                return new java.awt.Dimension(130, Integer.MAX_VALUE);
            }
        };
        panel.setBackground(new Color(40, 40, 40));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80), 1),
                BorderFactory.createEmptyBorder(8, 6, 8, 6)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1;
        g.insets = new Insets(3, 3, 3, 3);

        JLabel lblNombre = new JLabel(nombre, SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 12));
        lblNombre.setForeground(Color.WHITE);
        g.gridy = 0;
        panel.add(lblNombre, g);

        JLabel lblSimbolo = new JLabel(simbolo, SwingConstants.CENTER);
        lblSimbolo.setFont(new Font("Serif", Font.PLAIN, 42));
        lblSimbolo.setForeground(colorSimbolo);
        g.gridy = 1;
        panel.add(lblSimbolo, g);

        JLabel lblColor = new JLabel(etiquetaColor, SwingConstants.CENTER);
        lblColor.setFont(new Font("Arial", Font.BOLD, 11));
        lblColor.setForeground(colorEtiqueta);
        g.gridy = 2;
        panel.add(lblColor, g);

        javax.swing.JSeparator sep = new javax.swing.JSeparator();
        sep.setForeground(new Color(80, 80, 80));
        g.gridy = 3;
        g.insets = new Insets(6, 3, 3, 3);
        panel.add(sep, g);

        JLabel lblTituloComidas = new JLabel("Piezas comidas", SwingConstants.CENTER);
        lblTituloComidas.setFont(new Font("Arial", Font.BOLD, 10));
        lblTituloComidas.setForeground(new Color(255, 215, 0));
        g.gridy = 4;
        g.insets = new Insets(3, 3, 3, 3);
        panel.add(lblTituloComidas, g);

        JPanel cajaPiezas = new JPanel(new java.awt.FlowLayout(
                java.awt.FlowLayout.CENTER, 2, 2));
        cajaPiezas.setBackground(new Color(25, 25, 25));
        cajaPiezas.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(90, 90, 90), 1),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));
        cajaPiezas.setPreferredSize(new java.awt.Dimension(110, 110));
        cajaPiezas.setMinimumSize(new java.awt.Dimension(110, 110));

        g.gridy = 5;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weighty = 0;
        panel.add(cajaPiezas, g);

        if (conBotonRetirar) {
            panelComidasRojo = cajaPiezas;
        } else {
            panelComidasNegro = cajaPiezas;
        }

        JPanel spacer = new JPanel();
        spacer.setBackground(new Color(40, 40, 40));
        g.gridy = 6;
        g.weighty = 1;
        g.fill = GridBagConstraints.BOTH;
        panel.add(spacer, g);

        if (conBotonRetirar) {
            JButton btnRetirar = new JButton("Retirar");
            btnRetirar.setFont(new Font("Arial", Font.BOLD, 12));
            btnRetirar.setForeground(Color.WHITE);
            btnRetirar.setBackground(new Color(160, 0, 0));
            btnRetirar.setFocusPainted(false);
            btnRetirar.addActionListener(e -> confirmarRetiro());
            g.gridy = 7;
            g.weighty = 0;
            g.fill = GridBagConstraints.HORIZONTAL;
            g.insets = new Insets(6, 3, 3, 3);
            panel.add(btnRetirar, g);
        }

        return panel;
    }

    private Color getColorCelda(int f, int c) {
        if ((f + c) % 2 == 0) {
            return COLOR_CLARO;
        } else {
            return COLOR_OSCURO;
        }
    }

    private void actualizarCelda(JButton celda, int f, int c) {
        Pieza p = tablero[f][c];
        if (p == null) {
            celda.setText("");
            celda.setIcon(null);
        } else {
            celda.setText("");
            javax.swing.ImageIcon icon = getImagen(p);
            if (icon != null) {
                celda.setIcon(icon);
            } else {
                celda.setFont(new Font("Serif", Font.PLAIN, 30));
                celda.setForeground(p.esRojo() ? new Color(180, 0, 0) : Color.BLACK);
            }
        }
    }

    private javax.swing.ImageIcon getImagen(Pieza p) {
        String color = p.esRojo() ? "rojo" : "negro";
        String nombre = "";

        if (p instanceof General) {
            nombre = "general";
        } else if (p instanceof Oficial) {
            nombre = "oficial";
        } else if (p instanceof Elefante) {
            nombre = "elefante";
        } else if (p instanceof Caballo) {
            nombre = "caballo";
        } else if (p instanceof Carro) {
            nombre = "carro";
        } else if (p instanceof Cannon) {
            nombre = "cannon";
        } else if (p instanceof Soldado) {
            nombre = "soldado";
        }

        String ruta = "/fichas/" + nombre + "_" + color + ".png";
        java.net.URL url = getClass().getResource(ruta);

        if (url != null) {
            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(url);
            java.awt.Image img = icon.getImage()
                    .getScaledInstance(52, 52, java.awt.Image.SCALE_SMOOTH);
            return new javax.swing.ImageIcon(img);
        }
        return null;
    }

    private void actualizarPiezasComidas() {
        panelComidasRojo.removeAll();
        panelComidasNegro.removeAll();

        for (Pieza p : piezasComidasPorRojo) {
            JLabel lbl = new JLabel();
            javax.swing.ImageIcon icon = getImagen(p);
            if (icon != null) {
                java.awt.Image img = icon.getImage()
                        .getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
                lbl.setIcon(new javax.swing.ImageIcon(img));
            } else {
                lbl.setForeground(Color.BLACK);
                lbl.setFont(new Font("Serif", Font.PLAIN, 12));
            }
            panelComidasRojo.add(lbl);
        }

        for (Pieza p : piezasComidasPorNegro) {
            JLabel lbl = new JLabel();
            javax.swing.ImageIcon icon = getImagen(p);
            if (icon != null) {
                java.awt.Image img = icon.getImage()
                        .getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
                lbl.setIcon(new javax.swing.ImageIcon(img));
            } else {
                lbl.setForeground(new Color(180, 0, 0));
                lbl.setFont(new Font("Serif", Font.PLAIN, 12));
            }
            panelComidasNegro.add(lbl);
        }

        panelComidasRojo.revalidate();
        panelComidasRojo.repaint();
        panelComidasNegro.revalidate();
        panelComidasNegro.repaint();
    }

    private void mostrarMovimientosValidos(int filaOrigen, int colOrigen) {
        Pieza p = tablero[filaOrigen][colOrigen];
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLS; c++) {
                if (f == filaOrigen && c == colOrigen) {
                    continue;
                }
                if (p.esMovimientoValido(f, c, tablero)) {
                    if (!dejaGeneralesEnfrentados(filaOrigen, colOrigen, f, c)) {
                        celdas[f][c].setBackground(COLOR_MOVIBLE);
                    }
                }
            }
        }
    }

    private void limpiarMovimientosValidos() {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLS; c++) {
                if (f == filaSeleccionada && c == colSeleccionada) {
                    continue;
                }
                celdas[f][c].setBackground(getColorCelda(f, c));
            }
        }
    }

    private boolean dejaGeneralesEnfrentados(int filaOrigen, int colOrigen,
            int filaDestino, int colDestino) {
        Pieza[][] copia = new Pieza[FILAS][COLS];
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLS; c++) {
                copia[f][c] = tablero[f][c];
            }
        }
        copia[filaDestino][colDestino] = copia[filaOrigen][colOrigen];
        copia[filaOrigen][colOrigen] = null;

        int filaGenRojo = -1, colGenRojo = -1;
        int filaGenNegro = -1, colGenNegro = -1;

        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLS; c++) {
                if (copia[f][c] instanceof General) {
                    if (copia[f][c].esRojo()) {
                        filaGenRojo = f;
                        colGenRojo = c;
                    } else {
                        filaGenNegro = f;
                        colGenNegro = c;
                    }
                }
            }
        }

        if (filaGenRojo != -1 && filaGenNegro != -1 && colGenRojo == colGenNegro) {
            int colGen = colGenRojo;
            int filaMin = Math.min(filaGenRojo, filaGenNegro) + 1;
            int filaMax = Math.max(filaGenRojo, filaGenNegro);
            for (int f = filaMin; f < filaMax; f++) {
                if (copia[f][colGen] != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private String getTurnoTexto() {
        if (turnoRojo) {
            return "► Turno de " + jugadorRojo.getUsername() + " (ROJO)";
        } else {
            return "► Turno de " + jugadorNegro.getUsername() + " (NEGRO)";
        }
    }

    private void manejarClick(int fila, int col) {
        Pieza piezaClickeada = tablero[fila][col];

        if (filaSeleccionada == -1) {
            if (piezaClickeada == null) {
                return;
            }
            if (piezaClickeada.esRojo() != turnoRojo) {
                return;
            }
            filaSeleccionada = fila;
            colSeleccionada = col;
            celdas[fila][col].setBackground(COLOR_SEL);
            mostrarMovimientosValidos(fila, col);
            return;
        }

        if (filaSeleccionada == fila && colSeleccionada == col) {
            limpiarMovimientosValidos();
            deseleccionar();
            return;
        }

        Pieza piezaSeleccionada = tablero[filaSeleccionada][colSeleccionada];

        if (piezaClickeada != null && piezaClickeada.esRojo() == turnoRojo) {
            limpiarMovimientosValidos();
            deseleccionar();
            filaSeleccionada = fila;
            colSeleccionada = col;
            celdas[fila][col].setBackground(COLOR_SEL);
            mostrarMovimientosValidos(fila, col);
            return;
        }

        if (piezaSeleccionada.esMovimientoValido(fila, col, tablero)) {

            if (dejaGeneralesEnfrentados(filaSeleccionada, colSeleccionada, fila, col)) {
                JOptionPane.showMessageDialog(null,
                        "Movimiento inválido: los Generales no pueden verse de frente.",
                        "Movimiento Ilegal", JOptionPane.WARNING_MESSAGE);
                limpiarMovimientosValidos();
                deseleccionar();
                return;
            }

            boolean ganoJuego = piezaClickeada instanceof General
                    && piezaClickeada.esRojo() != turnoRojo;

            if (piezaClickeada != null) {
                if (turnoRojo) {
                    piezasComidasPorRojo.add(piezaClickeada);
                } else {
                    piezasComidasPorNegro.add(piezaClickeada);
                }
                actualizarPiezasComidas();
            }

            tablero[fila][col] = piezaSeleccionada;
            tablero[filaSeleccionada][colSeleccionada] = null;
            piezaSeleccionada.setPos(fila, col);

            celdas[filaSeleccionada][colSeleccionada].setText("");
            celdas[filaSeleccionada][colSeleccionada].setIcon(null);

            int f = filaSeleccionada;
            int c = colSeleccionada;
            celdas[f][c].setBackground(getColorCelda(f, c));

            actualizarCelda(celdas[fila][col], fila, col);
            limpiarMovimientosValidos();
            filaSeleccionada = -1;
            colSeleccionada = -1;

            if (ganoJuego) {
                terminarJuego(turnoRojo ? jugadorRojo : jugadorNegro,
                        turnoRojo ? jugadorNegro : jugadorRojo,
                        false);
                return;
            }

            turnoRojo = !turnoRojo;
            lblTurno.setText(getTurnoTexto());

        } else {
            limpiarMovimientosValidos();
            deseleccionar();
        }
    }

    private void deseleccionar() {
        if (filaSeleccionada != -1) {
            int f = filaSeleccionada;
            int c = colSeleccionada;
            celdas[f][c].setBackground(getColorCelda(f, c));
        }
        filaSeleccionada = -1;
        colSeleccionada = -1;
    }

    private void confirmarRetiro() {
        int res = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro que deseas retirarte?",
                "Confirmar Retiro",
                JOptionPane.YES_NO_OPTION
        );
        if (res == JOptionPane.YES_OPTION) {
            Player perdedor = turnoRojo ? jugadorRojo : jugadorNegro;
            Player ganador = turnoRojo ? jugadorNegro : jugadorRojo;
            terminarJuego(ganador, perdedor, true);
        }
    }

    private void terminarJuego(Player ganador, Player perdedor, boolean retiro) {
        ganador.ganarPartida();
        storage.actualizarPlayer(ganador);

        String mensaje;
        if (retiro) {
            mensaje = perdedor.getUsername() + " SE HA RETIRADO, FELICIDADES "
                    + ganador.getUsername() + ", HAS GANADO 3 PUNTOS";
        } else {
            mensaje = ganador.getUsername() + " VENCIO A " + perdedor.getUsername()
                    + ", FELICIDADES HAS GANADO 3 PUNTOS";
        }

        storage.guardarLog(ganador.getUsername(), mensaje);
        storage.guardarLog(perdedor.getUsername(), mensaje);

        JOptionPane.showMessageDialog(this, mensaje, "Fin del juego",
                JOptionPane.INFORMATION_MESSAGE);

        app.getContenedor().add(new PanelMenuPrincipal(app, storage), "PRINCIPAL");
        app.getContenedor().revalidate();
        app.getContenedor().repaint();
        app.mostrar("PRINCIPAL");
    }
}
