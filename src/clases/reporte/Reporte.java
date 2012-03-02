/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.reporte;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
/**
 *
 * @author Patricio Pérez Pinto
 */
public class Reporte {
    public static void generarGraficoPDF(List listaObjetos, String nombreArchivoJasper, String nombreReportePDF, Map parametros){
        try {
            JasperReport reporte = (JasperReport)JRLoader.loadObject(nombreArchivoJasper);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(listaObjetos));
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File(nombreReportePDF));
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void generarGraficoHTML(List listaObjetos, String nombreArchivoJasper, String nombreReportePDF, Map parametros){
        try {
            JasperReport reporte = (JasperReport)JRLoader.loadObject(nombreArchivoJasper);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(listaObjetos));
            JRExporter exporter = new JRHtmlExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File(nombreReportePDF));
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
