/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.reporte.hilo;

import clases.reporte.Reporte;
import clases.utilidades.archivos.Ejecutar;
import clases.utilidades.archivos.Guardar;
import clases.utilidades.archivos.P;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class HiloReporte extends Thread{
    private final String sep = P.SEPARADOR;
    private final List lista;
    private final Map parametros;
    private final JLabel lblMensaje;
    
    public HiloReporte(List lista, Map parametros, JLabel lblMensaje){
        this.lista = lista;
        this.parametros = parametros;
        this.lblMensaje = lblMensaje;
    }
    
    @Override
    public void run(){
        HiloMensaje hm = new HiloMensaje(this, lblMensaje);
        hm.start();
        if(Guardar.getExtension().equalsIgnoreCase(".pdf")){
            Reporte.generarGraficoPDF(lista, "reportes"+sep+"reporte1.jasper", Guardar.getRuta(), parametros);
        }else if (Guardar.getExtension().equalsIgnoreCase(".html")){
            Reporte.generarGraficoHTML(lista, "reportes"+sep+"reporte1.jasper", Guardar.getRuta(), parametros);
        }
        System.out.println(Guardar.getExtension());
        hm.interrupt();
        System.out.println("RUTA DEL REPORTE -------> "+"\""+Guardar.getRuta()+"\"");
        lblMensaje.setVisible(false);
        Ejecutar.ejecutarComando("\""+Guardar.getRuta()+"\"");
    }
}
