/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.terceros;

import java.awt.Component;
import java.awt.Font;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class RenderLista extends JLabel implements ListCellRenderer {

    Hashtable<Object, ImageIcon> elementos;
//    ImageIcon icononulo = new ImageIcon(this.getClass().getResource("../Iconos/imagen nulo.png"));

    public RenderLista() {
        elementos = new Hashtable<Object, ImageIcon>();
        ImageIcon icono1 = new ImageIcon(this.getClass().getResource("/iconos/cdPequeno.png"));
        elementos.put(" ", icono1);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        try{
            if (elementos.get(value) != null) {
                setIcon(elementos.get(value));
                setText("" + value);
                if (isSelected) {
                    setFont(new Font("Tahoma", Font.BOLD, 11));
                } else {
                    setFont(null);
                }
            } else {
                setIcon(new ImageIcon(this.getClass().getResource("/iconos/cdPequeno.png")));
                setText("" + value);
                if (isSelected) {
                    setFont(new Font("Tahoma", Font.BOLD, 11));
                } else {
                    setFont(null);
                }
            }

        }catch(Exception e){
            
        }
        return this;
    }
}
