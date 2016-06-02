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

public void desconectar() {
        try {
            connect.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

