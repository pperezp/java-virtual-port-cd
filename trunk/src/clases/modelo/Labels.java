/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author Administrador
 */
public class Labels {
    public static List<JTextField> labels = new ArrayList<JTextField>();
    public static int CONTADOR = 20;
    public static int INDICE = 0;

    public static void borrarLabels() {
        boolean removeAll = labels.removeAll(labels);
        CONTADOR = 20;
        INDICE = 0;
    }
}
