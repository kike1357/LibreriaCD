package libreriaBaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Metodos {
    
     Connection connect = null;
     ResultSet rs = null;
     
/**
     * Se usa para conectar la base de datos con nuestro programa
     * 
     * @param url el nombre de nuestra base de datos y donde está alojada
     * @param user nombre de usuario con el que accedemos a nuestra base de datos
     * @param password contraseña que tiene el usuario para acceder a la base
     */ 
public Connection Conectar(String url,String user,String password){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Se ha conectado");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connect;
}

/**
     * Utilizado para insertar registros en la tabla
     * 
     * @param tabla Nombre de nuestra tabla en donde se insertan los datos
     * @param datos Los valores que se insertan en la tabla
     */
public void insert(String tabla, String datos) {
    
        PreparedStatement pst;
        try {
            pst = connect.prepareStatement("insert into " + tabla + " values(" + datos + ")");
            pst.execute();
            System.out.println("Inserción realizada");
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }

}

/**
     * Elimina un registro insertado en la tabla
     * 
     * @param tabla Nombre de la tabla en donde queremos que se elimine el registro que introduccimos
     * @param primaryKey Número que identifica a la fila
     */
public void delete(String tabla, int primaryKey) {
    
        PreparedStatement pst;
        try {
            pst = connect.prepareStatement("delete from " + tabla + " where id=" + primaryKey);
            pst.execute();
            System.out.println("eliminado");
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

/**
     * Muestra la información registrada en la tabla
     * 
     * @param tabla es el nombre de nuestra tabla
     * @param campos muestra los campos a consultar
     * @param columna nombre de la columna
     */
public ResultSet consultar(String tabla, String campo, int columna){
    
       Statement st;
       String[]datos= new String[columna];
       
        try {
            st= connect.createStatement();
            rs=st.executeQuery("select "+campo+" from "+tabla);  
        while(rs.next()){
   
            for (int i=0; i<datos.length; i++) {
                datos[i]=rs.getString(i+1);
                 System.out.println(i);       
            }
        } 
        
        }catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

/**
     * Sirve para actualizar un campo que ya teniamos introducido
     *
     * @param tabla Nombre de nuestra tabla donde se va a actualizar el registro ya introducido
     * @param campo Nombre del registro a actualizar
     * @param campoActualizado Nombre del campo actualizado
     * @param primaryKey El valor que identifica a la fila
     */
public void actualizar(String tabla, String campo, String campoActualizado, int primaryKey) {
        PreparedStatement pst;
        try {
            pst = connect.prepareStatement("update " + tabla + " set " + campo + "=" + campoActualizado + " where .id=" + primaryKey);
            pst.execute();
            System.out.println("Se ha actualizado");
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

/**
     * cierra la conexion del programa con la base de datos
     */
public void desconectar() {
        try {
            connect.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

