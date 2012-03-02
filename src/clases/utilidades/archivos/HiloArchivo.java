/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.utilidades.archivos;

import java.io.File;
import javax.swing.JTextArea;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class HiloArchivo extends Thread{
    private final File f;
    private final JTextArea area;
    private final boolean isTodo;
    
    public HiloArchivo(File f, JTextArea area, boolean isTodo) {
        this.f = f;
        this.area = area;
        this.area.setText("");
        this.isTodo = isTodo;
    }
    
    
    @Override
    public void run(){
        System.out.println("Recuerde que esto puede demorar mucho tiempo.\n Presione cancelar cuando guste");
        Rutas.setResultado("");
        if(isTodo){
            Rutas.imprimirTodo(f, area, this);
        }else{
            Rutas.imprimirSoloCarpetas(f, area, this);
        }
        
    }
    
}
