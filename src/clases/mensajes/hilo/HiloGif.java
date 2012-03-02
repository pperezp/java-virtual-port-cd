/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.mensajes.hilo;

import clases.utilidades.archivos.HiloArchivo;
import clases.utilidades.formularios.Redimensionar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class HiloGif extends Thread {

    private HiloArchivo ha;
    private JLabel lblMen;
    private int cont;
    private final JPanel panel;

    /**
     *
     * @param ha Objeto del Tipo HiloRecuperarContraseña
     * @param frame Es el Frame que contiene el gif que dice enviando o procesando
     * @param lblMen Es el Label que contiene el mensaje enviando o procesando
     */
    public HiloGif(HiloArchivo ha , JPanel panel,JLabel lblMen) {
        this.ha = ha;
        this.lblMen = lblMen;
        this.panel = panel;
        cont = 0;
    }

    @Override
    public void run() {
        try {
            while (ha.isAlive()) {
                cont++;
                switch (cont) {
                    case 1: {
                        lblMen.setText(lblMen.getText().replaceAll("\\.", ""));
                        break;
                    }
                    case 2: {
                        lblMen.setText(lblMen.getText() + ".");
                        break;
                    }
                    case 3: {
                        lblMen.setText(lblMen.getText() + ".");
                        break;
                    }
                    case 4: {
                        lblMen.setText(lblMen.getText() + ".");
                        cont = 0;
                    }
                }


                if (!panel.isVisible()) {
                    panel.setVisible(true);
                }
                Thread.sleep(250);

            }
            
        } catch (InterruptedException ex) {
            panel.setVisible(false);
        }
        panel.setVisible(false);
    }
}
