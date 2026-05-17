/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xiangqi.gui;

import xiangqi.jugador.Player;
import xiangqi.almacenamiento.AlmacenamientoImp;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;

/**
 *
 * @author hermi
 */
public class PanelReportes extends JPanel {

    private static final Color FONDO = new Color(45, 32, 22);
    private static final Color DORADO = new Color(250, 220, 170);
    private static final Color TEXTO = new Color(210, 185, 145);
    private static final Color BTN_FONDO = new Color(75, 55, 40);
    private static final Color BTN_BORDE = new Color(180, 140, 90);

    private AppFrame app;
    private AlmacenamientoImp storage;

    public PanelReportes(AppFrame app, AlmacenamientoImp storage) {
        this.app = app;
        this.storage = storage;
        initUI();
    }

    private void initUI() {
        setBackground(FONDO);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("REPORTES", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 28));
        titulo.setForeground(DORADO);
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setBackground(new Color(30, 20, 12) );
        area.setForeground(TEXTO);
        area.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBorder(BorderFactory.createLineBorder(DORADO, 1));

        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setBackground(FONDO);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.ipadx = 120;

        JButton btnRanking = crearBoton("Ranking de Jugadores");
        JButton btnLogs = crearBoton("Mis Últimos Partidos");
        JButton btnVolver = crearBoton("Volver");

        gbc.gridy = 0;
        panelBotones.add(btnRanking, gbc);
        gbc.gridy = 1;
        panelBotones.add(btnLogs, gbc);
        gbc.gridy = 2;
        panelBotones.add(btnVolver, gbc);

        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnRanking.addActionListener(e -> mostrarRanking(area));
        btnLogs.addActionListener(e -> mostrarLogs(area));
        btnVolver.addActionListener(e -> app.mostrar("PRINCIPAL"));
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

    private void mostrarRanking(JTextArea area) {
        ArrayList<Player> jugadores = storage.getTodosLosPlayers();

        for (int i = 0; i < jugadores.size() - 1; i++) {
            for (int j = 0; j < jugadores.size() - 1 - i; j++) {
                if (jugadores.get(j).getPuntos() < jugadores.get(j + 1).getPuntos()) {
                    Player temp = jugadores.get(j);
                    jugadores.set(j, jugadores.get(j + 1));
                    jugadores.set(j + 1, temp);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== RANKING DE JUGADORES ===\n\n");
        sb.append(String.format("%-5s %-15s %-10s%n", "Nº", "USERNAME", "PUNTOS"));
        sb.append("--------------------------------\n");

        for (int i = 0; i < jugadores.size(); i++) {
            Player p = jugadores.get(i);
            sb.append(String.format("%-5d %-15s %-10d%n",
                    (i + 1), p.getUsername(), p.getPuntos()));
        }

        area.setText(sb.toString());
    }

    private void mostrarLogs(JTextArea area) {
        ArrayList<String> logs = storage.getLogsDePlayer(app.getPlayerLogueado().getUsername());

        StringBuilder sb = new StringBuilder();
        sb.append("=== MIS ULTIMOS PARTIDOS ===\n\n");

        if (logs.isEmpty()) {
            sb.append("No tienes partidos registrados aún.");
        } else {
            for (String log : logs) {
                sb.append(log).append("\n");
            }
        }

        area.setText(sb.toString());
    }
}
