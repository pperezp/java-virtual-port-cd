/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.hsql.modelo.tiposDeDatos;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class Tipo {
    public static Numerico numerico = new Numerico();
    public static CaracterString caracterString = new CaracterString();
    public static BinaryString binaryString = new BinaryString();;
    public static BitString bitString = new BitString();
    public static FechaYHora fecha_y_hora = new FechaYHora();
    public static AutoIncremento autoIncremento = new AutoIncremento();
}
