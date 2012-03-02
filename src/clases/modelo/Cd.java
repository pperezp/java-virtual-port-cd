/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.modelo;

/**
 *
 * @author Patricio Pérez Pinto
 */
import clases.hsql.conexion.Conexion;
import clases.hsql.conexion.ConexionAux;
import clases.utilidades.formularios.Cambiar;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cd {

    public static List getListaDeEstuches() {
        List<Estuche> estuches = new ArrayList<Estuche>();
        try {
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("SELECT * FROM estuche;");
            while(Conexion.rs.next()){
                estuches.add(new Estuche(Conexion.rs.getString("cod_est"), Conexion.rs.getString("num_est")));
            }
            Conexion.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estuches;
    }

    /**
     * Método para obtener todos los cds de un estuche a traves de su codigo
     * @param codEstuche
     * @return
     */
    public static List<Cd> getTodosLosCds(String codEstuche) {
//        ConexionAux.conexionHSQL("BD/CDS");
        List<Cd> cds = new ArrayList<Cd>();
        try {
            ConexionAux.sentencia = ConexionAux.con.createStatement();
            ConexionAux.rs = ConexionAux.sentencia.executeQuery(
            "SELECT cd.cod_cd, estuche.num_est, cd.esp_cd, cd.nom_cd, categoria.nom_cat, cd.con_cd "+
            "FROM estuche, cd, categoria "+
            "WHERE estuche.cod_est = cd.cod_est and categoria.cod_cat = cd.cod_cat "
           + "and estuche.cod_est = "+codEstuche+" ORDER BY cd.esp_cd ASC");
            while(ConexionAux.rs.next()){
                String codigo = ConexionAux.rs.getString("cod_cd");
                String estuche = ConexionAux.rs.getString("num_est");
                String espacio = ConexionAux.rs.getString("esp_cd");
                String nombre = ConexionAux.rs.getString("nom_cd");
                String categoria = ConexionAux.rs.getString("nom_cat");
                String contenido = Cambiar.doblesAcomillas(ConexionAux.rs.getString("con_cd"));

                cds.add(new Cd(codigo, nombre, categoria, espacio, estuche, contenido));
            }
            ConexionAux.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cds;
    }
    
    public static int getCantidadDeCds() {
        int cantidadDeCds = 0;
        try {
            ConexionAux.sentencia = ConexionAux.con.createStatement();
            ConexionAux.rs = ConexionAux.sentencia.executeQuery(
            "SELECT count(0) as cont "+
            "FROM cd");
            if(ConexionAux.rs.next()){
                cantidadDeCds = ConexionAux.rs.getInt("cont");
            }
            ConexionAux.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidadDeCds;
    }

    public static Cd getCd(String codigoCD) {
        Cd cd = null;
//        ConexionAux.conexionHSQL("BD/CDS");
        try {
            ConexionAux.sentencia = ConexionAux.con.createStatement();
            ConexionAux.rs = ConexionAux.sentencia.executeQuery(
            "SELECT cd.cod_cd, estuche.num_est, cd.esp_cd, cd.nom_cd, categoria.nom_cat, cd.con_cd "+
            "FROM estuche, cd, categoria "+
            "WHERE estuche.cod_est = cd.cod_est and categoria.cod_cat = cd.cod_cat "
           + "and cd.cod_cd = "+codigoCD);
            if(ConexionAux.rs.next()){
                String codigo = ConexionAux.rs.getString("cod_cd");
                String estuche = ConexionAux.rs.getString("num_est");
                String espacio = ConexionAux.rs.getString("esp_cd");
                String nombre = ConexionAux.rs.getString("nom_cd");
                String categoria = ConexionAux.rs.getString("nom_cat");
                String contenido = Cambiar.doblesAcomillas(ConexionAux.rs.getString("con_cd"));

                cd = new Cd(codigo, nombre, categoria, espacio, estuche, contenido);
            }
            ConexionAux.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cd;
    }

    public static Estuche getEstuche(String codigoEst) {
        Estuche estuche = null;
        Conexion.conexionHSQL("BD/CDS");
        try {
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("SELECT * FROM estuche WHERE cod_est = "+codigoEst);
            if(Conexion.rs.next()){
                String codigo = Conexion.rs.getString("cod_est");
                String numero = Conexion.rs.getString("num_est");
                estuche = new Estuche(codigo, numero);
            }
            Conexion.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estuche;
    }

    public static List<Categoria> getTodasLasCategorias() {
        List<Categoria> lista = new ArrayList<Categoria>();
        try {
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("SELECT * FROM categoria");
            while(Conexion.rs.next()){
                String codigo = Conexion.rs.getString("cod_cat");
                String nombre = Conexion.rs.getString("nom_cat");
                lista.add(new Categoria(codigo, nombre));
            }
            Conexion.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static int getCantidadDeCategorias(Estuche estuche, Categoria categoria) {
        int cantidad = 0;
        try {
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("SELECT COUNT(0) AS cantidad FROM cd WHERE cod_cat = "+categoria.getCodigo()+" AND cod_est = "+estuche.getCodigo());
            if(Conexion.rs.next()){
                cantidad = Conexion.rs.getInt("cantidad");
            }
            Conexion.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }

    public static int getCantidadDeDiscos(Estuche estuche) {
        int cantidad = 0;
        try {
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("SELECT COUNT(0) AS cantidad FROM cd WHERE cod_est = "+estuche.getCodigo());
            if(Conexion.rs.next()){
                cantidad = Conexion.rs.getInt("cantidad");
            }
            Conexion.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }

    public static Cd getCd(int codEst, int esp) {
        Cd cd = null;
        try {
            ConexionAux.sentencia = ConexionAux.con.createStatement();
            ConexionAux.rs = ConexionAux.sentencia.executeQuery(
             "SELECT cd.cod_cd, estuche.num_est, cd.esp_cd, cd.nom_cd, categoria.nom_cat, cd.con_cd "
           + "FROM cd, estuche, categoria "
           + "WHERE cd.cod_cat = categoria.cod_cat and cd.cod_est = estuche.cod_est AND estuche.num_est = "+codEst+" AND cd.esp_cd = "+esp);
            
            if(ConexionAux.rs.next()){
                String codigo = ConexionAux.rs.getString("cod_cd");
                String estuche = ConexionAux.rs.getString("num_est");
                String espacio = ConexionAux.rs.getString("esp_cd");
                String nombre = ConexionAux.rs.getString("nom_cd");
                String categoria = ConexionAux.rs.getString("nom_cat");
                String contenido = Cambiar.doblesAcomillas(ConexionAux.rs.getString("con_cd"));

                cd = new Cd(codigo, nombre, categoria, espacio, estuche, contenido);
            }
            
            ConexionAux.sentencia.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cd;
    }

    private String codigo;
    private String nombre;
    private String categoria;
    private String espacio;
    private String estuche;
    private String contenido;
    private int codigoCategoria;
    private int codigoEstuche;

    public Cd(String nombre, String categoria, String espacio, String estuche, String contenido) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.espacio = espacio;
        this.estuche = estuche;
        this.contenido = contenido;

        codigoCategoria = buscarCodigoCategoria(categoria);
        codigoEstuche = buscarCodigoEstuche(estuche);
    }

    /*CONSTRUCTOR CON EL CODIGO DE LA BD*/
    public Cd(String codigo, String nombre, String categoria, String espacio, String estuche, String contenido) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.espacio = espacio;
        this.estuche = estuche;
        this.contenido = contenido;

        codigoCategoria = buscarCodigoCategoria(categoria);
        codigoEstuche = buscarCodigoEstuche(estuche);
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getEspacio() {
        return espacio;
    }

    public void setEspacio(String espacio) {
        this.espacio = espacio;
    }

    public String getEstuche() {
        return estuche;
    }

    public void setEstuche(String estuche) {
        this.estuche = estuche;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Cd{" + "nombre=" + nombre + "categoria=" + categoria + "espacio=" + espacio + "estuche=" + estuche + "contenido=" + contenido + '}';
    }

    /**
     * @return the codigoCategoria
     */
    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    /**
     * @param codigoCategoria the codigoCategoria to set
     */
    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    /**
     * @return the codigoEstuche
     */
    public int getCodigoEstuche() {
        return codigoEstuche;
    }

    /**
     * @param codigoEstuche the codigoEstuche to set
     */
    public void setCodigoEstuche(int codigoEstuche) {
        this.codigoEstuche = codigoEstuche;
    }

    public static int buscarCodigoCategoria(String categoria) {
        int codigo = -1;
        try {
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("SELECT cod_cat FROM categoria WHERE nom_cat = '"+categoria+"'");
            if(Conexion.rs.next()){
                codigo = Conexion.rs.getInt("cod_cat");
            }
            Conexion.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigo;
    }

    public static int buscarCodigoEstuche(String estuche) {
        int codigo = -1;
        try {
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("SELECT cod_est FROM estuche WHERE num_est = '"+estuche+"'");
            if(Conexion.rs.next()){
                codigo = Conexion.rs.getInt("cod_est");
            }
            Conexion.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigo;
    }

    public static String getNombreCd(int estuche, int espacio) {
        String nombre = null;
        try {
            estuche = buscarCodigoEstuche(Integer.toString(estuche));
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("SELECT nom_cd FROM cd WHERE esp_cd = "+espacio+" AND cod_est = "+estuche);
            if(Conexion.rs.next()){
                nombre = Conexion.rs.getString("nom_cd");
            }
            Conexion.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombre;
    }
    
    public static boolean isEspacioOcupado(int estuche, int espacio) {
        boolean isEspacioOcupado = false;
        try {
            estuche = Cd.buscarCodigoEstuche(Integer.toString(estuche));
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("SELECT * FROM cd WHERE esp_cd = "+espacio+" AND cod_est = "+estuche);
            if(Conexion.rs.next()){
                isEspacioOcupado = true;
            }
            Conexion.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isEspacioOcupado;
    }

    public static boolean reemplazarCd(int estuche, int espacio, Cd discoNuevo) {
        boolean discoActualizado = false;
        estuche = Cd.buscarCodigoEstuche(Integer.toString(estuche));
        try {
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.sentencia.execute(
            "UPDATE cd "
          + "SET nom_cd = '"+discoNuevo.getNombre()+"', cod_cat = "+discoNuevo.getCodigoCategoria()+", con_cd = '"+discoNuevo.getContenido()+"' "
          + "WHERE esp_cd = "+espacio+" AND cod_est = "+estuche+"");
            Conexion.sentencia.close();
            discoActualizado = true;
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return discoActualizado;
    }

    public static List<Cd> buscarCd(String cdAbuscar) {
//        ConexionAux.conexionHSQL("BD/CDS");
        /*VA A BUSCAR EN EL NOMBRE DEL CD, EN LA CATEGORIA O EN EL CONTENIDO*/
        List<Cd> cds = new ArrayList<Cd>();
        try {
            ConexionAux.sentencia = ConexionAux.con.createStatement();
            ConexionAux.rs = ConexionAux.sentencia.executeQuery(
            "SELECT cd.cod_cd, estuche.num_est, cd.esp_cd, cd.nom_cd, categoria.nom_cat, cd.con_cd "+
            "FROM estuche, cd, categoria "+
            "WHERE estuche.cod_est = cd.cod_est and categoria.cod_cat = cd.cod_cat "
           + "and (cd.nom_cd LIKE '%"+cdAbuscar+"%' or categoria.nom_cat LIKE '%"+cdAbuscar+"%' or cd.con_cd LIKE '%"+cdAbuscar+"%')");
            while(ConexionAux.rs.next()){
                String codigo = ConexionAux.rs.getString("cod_cd");
                String estuche = ConexionAux.rs.getString("num_est");
                String espacio = ConexionAux.rs.getString("esp_cd");
                String nombre = ConexionAux.rs.getString("nom_cd");
                String categoria = ConexionAux.rs.getString("nom_cat");
                String contenido = Cambiar.doblesAcomillas(ConexionAux.rs.getString("con_cd"));

                cds.add(new Cd(codigo, nombre, categoria, espacio, estuche, contenido));
            }
            ConexionAux.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cds;
    }
    
    public static List<Cd> buscarCd(String cdAbuscar, String nombreCategoria) {
//        ConexionAux.conexionHSQL("BD/CDS");
        /*VA A BUSCAR EN EL NOMBRE DEL CD, EN LA CATEGORIA O EN EL CONTENIDO*/
        List<Cd> cds = new ArrayList<Cd>();
        try {
            ConexionAux.sentencia = ConexionAux.con.createStatement();
            ConexionAux.rs = ConexionAux.sentencia.executeQuery(
            "SELECT cd.cod_cd, estuche.num_est, cd.esp_cd, cd.nom_cd, categoria.nom_cat, cd.con_cd "+
            "FROM estuche, cd, categoria "+
            "WHERE estuche.cod_est = cd.cod_est and categoria.cod_cat = cd.cod_cat "
           + "and (cd.nom_cd LIKE '%"+cdAbuscar+"%' or categoria.nom_cat LIKE '%"+cdAbuscar+"%' or cd.con_cd LIKE '%"+cdAbuscar+"%')"
            + " and categoria.nom_cat = '"+nombreCategoria+"'");
            while(ConexionAux.rs.next()){
                String codigo = ConexionAux.rs.getString("cod_cd");
                String estuche = ConexionAux.rs.getString("num_est");
                String espacio = ConexionAux.rs.getString("esp_cd");
                String nombre = ConexionAux.rs.getString("nom_cd");
                String categoria = ConexionAux.rs.getString("nom_cat");
                String contenido = Cambiar.doblesAcomillas(ConexionAux.rs.getString("con_cd"));

                cds.add(new Cd(codigo, nombre, categoria, espacio, estuche, contenido));
            }
            ConexionAux.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cds;
    }

    public static List<Cd> buscarCd(String cdAbuscar, int numEst) {
//        ConexionAux.conexionHSQL("BD/CDS");
        /*VA A BUSCAR EN EL NOMBRE DEL CD, EN LA CATEGORIA O EN EL CONTENIDO*/
        List<Cd> cds = new ArrayList<Cd>();
        try {
            ConexionAux.sentencia = ConexionAux.con.createStatement();
            ConexionAux.rs = ConexionAux.sentencia.executeQuery(
            "SELECT cd.cod_cd, estuche.num_est, cd.esp_cd, cd.nom_cd, categoria.nom_cat, cd.con_cd "+
            "FROM estuche, cd, categoria "+
            "WHERE estuche.cod_est = cd.cod_est and categoria.cod_cat = cd.cod_cat "
           + "and (cd.nom_cd LIKE '%"+cdAbuscar+"%' or categoria.nom_cat LIKE '%"+cdAbuscar+"%' or cd.con_cd LIKE '%"+cdAbuscar+"%')"
            + " and estuche.num_est = '"+numEst+"'");
            while(ConexionAux.rs.next()){
                String codigo = ConexionAux.rs.getString("cod_cd");
                String estuche = ConexionAux.rs.getString("num_est");
                String espacio = ConexionAux.rs.getString("esp_cd");
                String nombre = ConexionAux.rs.getString("nom_cd");
                String categoria = ConexionAux.rs.getString("nom_cat");
                String contenido = Cambiar.doblesAcomillas(ConexionAux.rs.getString("con_cd"));

                cds.add(new Cd(codigo, nombre, categoria, espacio, estuche, contenido));
            }
            ConexionAux.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cds;
    }
    
    public static List<Cd> buscarCd(String cdAbuscar, String nombreCategoria, int numEst) {
//        ConexionAux.conexionHSQL("BD/CDS");
        /*VA A BUSCAR EN EL NOMBRE DEL CD, EN LA CATEGORIA O EN EL CONTENIDO*/
        List<Cd> cds = new ArrayList<Cd>();
        try {
            ConexionAux.sentencia = ConexionAux.con.createStatement();
            ConexionAux.rs = ConexionAux.sentencia.executeQuery(
            "SELECT cd.cod_cd, estuche.num_est, cd.esp_cd, cd.nom_cd, categoria.nom_cat, cd.con_cd "+
            "FROM estuche, cd, categoria "+
            "WHERE estuche.cod_est = cd.cod_est and categoria.cod_cat = cd.cod_cat "
           + "and (cd.nom_cd LIKE '%"+cdAbuscar+"%' or categoria.nom_cat LIKE '%"+cdAbuscar+"%' or cd.con_cd LIKE '%"+cdAbuscar+"%')"
            + " and categoria.nom_cat = '"+nombreCategoria+"' and estuche.num_est = "+numEst);
            while(ConexionAux.rs.next()){
                String codigo = ConexionAux.rs.getString("cod_cd");
                String estuche = ConexionAux.rs.getString("num_est");
                String espacio = ConexionAux.rs.getString("esp_cd");
                String nombre = ConexionAux.rs.getString("nom_cd");
                String categoria = ConexionAux.rs.getString("nom_cat");
                String contenido = Cambiar.doblesAcomillas(ConexionAux.rs.getString("con_cd"));

                cds.add(new Cd(codigo, nombre, categoria, espacio, estuche, contenido));
            }
            ConexionAux.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cds;
    }
    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
