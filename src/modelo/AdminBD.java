package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class AdminBD {
    private Connection conexion;
    private ResultSet resultados;//objeto que almacena una tabla o lista de una conculta de una base de  datos
    //recibe una o varial filas de una tabla 
    private String controladorBD;
    private String host;
    private String puerto;
    private String baseDatos;
    private String url;
    private String usuario;
    private String contraseña;
    private Catalogo catalogo = new Catalogo();
    private Pelicula pelicula =new Pelicula();
    private DefaultTableModel catalogos;

    /*Constructor de la clase AdminBD*/
    public AdminBD(){
        controladorBD= "org.postgresql.Driver";
	host= "127.0.0.1";
	puerto = "5432";
	baseDatos="test";
	url ="jdbc:postgresql://"+host+":"+puerto+"/"+baseDatos;
	usuario="postgres";
	contraseña="p057gr35";
	resultados=null;
	conexion =null;
    }
	/*Metodo para conectarse a la base de datos*/
    public String conectate(){
       	String mensaje=null;
       	try{
            Class.forName(controladorBD); 
       
        }catch(ClassNotFoundException cnfe){
            mensaje="No se hallo el controlador de PostgreSQL";
        }
        try{
            conexion=DriverManager.getConnection(url,usuario,contraseña);
        }catch(SQLException sqe){
            mensaje ="conexion Fallo"; 
        }
        return mensaje;
    }
    /*Metodo para insertar una pelicula en la tabla*/
    public String insertarPelicula(Pelicula pelicula){
        String mensaje =null;
        String ordenSQL =null;
        Statement proposicion =null;
        /*variables de pelicula*/
        String codigo =null;
        String nombre =null;
        String actor =null;
        String genero =null;
        double precio =0.0;
        String valores =null;
		
        /*buscamos guardar info de pelicula en la base de datos*/
        this.pelicula =pelicula;
        codigo =pelicula.getCodigo();
        nombre =pelicula.getNombre();
        actor =pelicula.getActorPrincipal();
        genero =pelicula.getGenero();
        precio =pelicula.getPrecio();
        /*  anteriormemte agregamos imagen con este codigo
        imagen= hongo.getBytesImg();
        */
        mensaje =conectate();	
        if(conexion !=null){
            try{
                proposicion =conexion.createStatement();/*sirve para conectarse
                *sabe ir a la base de datos y ejecuta lo que le pidamos*/ 
                valores ="'" +codigo +"'," +"'" +nombre +"','" +actor +"'," 
                        +"'" +genero +"'," +"'" +precio +"'"; 
                ordenSQL ="INSERT INTO catalogo VALUES(" +valores +")";
                System.out.println(ordenSQL);
                proposicion.executeUpdate(ordenSQL);/*para modificar la base de
                *datos por eso no se necesita un result set*/
                proposicion.close();
            }catch(SQLException sqle){
                mensaje ="fallo actualizacion";
                sqle.printStackTrace();
            }finally{
                try{
                    conexion.close();
                }catch(SQLException sqle){
                    mensaje ="falla conexcion";			   
                }
            } 
        }else{
            mensaje ="fallo conexion";
        }
        return mensaje;
    }
	/*Metodo encargado de llenar tabla de peliculas*/
    public DefaultTableModel consultaPelicula( ){
        String mensaje=null;
        Statement proposicion;
        ResultSet rs;
        pelicula = new Pelicula();
        String ordenSQL;        
        catalogos = new DefaultTableModel();
        catalogos.setColumnIdentifiers(new Object[]{"CODIGO" ,"NOMBRE" 
                                    ,"ACTOR PRINCIPAL","GENERO","PRECIO"});
        mensaje = conectate();
        if(conexion !=null){
            try {
                proposicion =conexion.createStatement();
                ordenSQL ="SELECT * FROM catalogo";
                rs =proposicion.executeQuery(ordenSQL);												
                while ( rs.next() ) {
                    catalogos.addRow(new Object[]{
                    rs.getString("codigo") 
                    ,rs.getString("nombre") 
                    ,rs.getString("actor") 
                    ,rs.getString("genero") 
                    ,rs.getDouble("precio")});
                }
                rs.close();
                proposicion.close();
            }catch(SQLException sqle){
            //mensaje="fallo consulta";
            catalogos = null;
            sqle.printStackTrace();
            }finally{//para desconectarme
                try{
                    conexion.close();
                }catch(SQLException sqle){
                     //mensaje="falla conexion";	
                    catalogos =null;
                }
            }  
        }else{
            catalogos =null;
        }
        return catalogos;
    }		
}//fin clase 