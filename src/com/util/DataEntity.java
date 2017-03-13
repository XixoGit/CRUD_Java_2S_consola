package com.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.entidades.Carrera;
import com.entidades.Inscripcion;
import com.entidades.Usuario;
/*
* CLASE QUE ADMINISTRA LOS SELECT, INSERT, DELETE Y UPDATE DE LAS ENTIDADES USUARIO,INSCRIPCION Y CARRERA
**/
public class DataEntity {
	Conexion conexion=new Conexion();   //instanciamos la conexion

	// mostramos la tabla usuario en forma de un obj arralist de tipo usuario(en realidad retorna un usuario)
	public ArrayList<Usuario> mostrarUsuarios() throws SQLException{    

		ArrayList<Usuario> usuario=new ArrayList<Usuario>();    //creamos el objeto usuario de tipo <Usuario>               
		PreparedStatement pst= conexion.getConexion().prepareStatement("SELECT * FROM usuario ORDER BY apellido_usu ASC");    
		ResultSet rs = pst.executeQuery();     // la consulta muestra los usuarios de menor a mayor(A-Z)

		while(rs.next()){              //mientras la consulta traiga datos de la tabla...
			
			Usuario u=new Usuario();              //instanciamos la clase Usuario para acceder a los getter and setters
			u.setId(rs.getInt("id_usu"));
			u.setNombre(rs.getString("nombre_usu"));
			u.setApellido(rs.getString("apellido_usu"));
			u.setEmail(rs.getString("email_usu"));             //trabajamos con los setters and getters
			u.setNacimient(rs.getString("fec_nac_usu"));
			u.setPass(rs.getString("contrasenia_usu"));
			u.setId_insc_usuario(rs.getInt("usu_id_insc"));
			usuario.add(u);                                    //agragamos el objeto u al arraylist <Usuario>
		}
		rs.close();                                      //cerramos el resulset
		return usuario;        //retorna el arraylist usuario para ser usado por la vista main
	}

	//******************************/ 	MOSTRAR USUARIOS con JOIN      /******************************************
//mostramos las tres tablas usando tres arraylist , con las tres entidades y sus getters and setters 
	//aplicamos todos los buenos conceptos de programacion y tambien se Base de datos
	public void mostrarUsuariosJOIN() throws SQLException{   
   
		String query2="SELECT u.nombre_usu,u.apellido_usu, i.nombre_insc,i.fecha_insc , c.nombre_carrera "
				+ "FROM usuario u INNER JOIN inscripcion i ON u.usu_id_insc=i.id_insc"
				+ " INNER JOIN carrera c  ON c.id_carrera=i.insc_id_carrera;";   //la query con el join

		ArrayList<Usuario> usuario=new ArrayList<Usuario>(); 
		ArrayList<Inscripcion> inscripcion=new ArrayList<Inscripcion>();   
		ArrayList<Carrera> carrera=new ArrayList<Carrera>();             //creamos los tres arraylist
		PreparedStatement prepare=conexion.getConexion().prepareStatement(query2);    
		ResultSet resulset = prepare.executeQuery();    //obtenemos la conexion e insertamos la query en un solo paso

		while(resulset.next()){                  //mientras la consulta traiga datos de la tabla...
			Usuario u=new Usuario();
			Inscripcion insc=new Inscripcion();        // instanciamos las entidades
			Carrera car=new Carrera();
			u.setNombre(resulset.getString("nombre_usu"));     
			u.setApellido(resulset.getString("apellido_usu"));   //trabajamos con los setters and getters
			insc.setNombre(resulset.getString("nombre_insc"));       //de cada entidad
			insc.setFecha(resulset.getString("fecha_insc"));
			car.setNombre(resulset.getString("nombre_carrera"));

			usuario.add(u);
			inscripcion.add(insc);     //agregamos los obj entidades al los arraylist
			carrera.add(car);
		}
		resulset.close();   //cerramos el resulset

		for (int i = 0; i <  usuario.size(); i++) {                  //MOSTRAMOS LOS DATOS 
			System.out.println("----------------------------");
			System.out.println("NOMBRE:     "+usuario.get(i).getNombre());
			System.out.println("APELLIDO:   "+usuario.get(i).getApellido());
			System.out.println("NOMBRE INSCRIPCION:   " + inscripcion.get(i).getNombre());
			System.out.println("FECHA INSCRIPCION:   " + inscripcion.get(i).getFecha());
			System.out.println("CARRERA:   " + carrera.get(i).getNombre());
		}
		System.out.println("metodo metodoMostrarUser ejecutado!!");
	}

	//******************************/ 	INSERTA USUARIO    /******************************************
	public void insertaUsuario() throws SQLException{
		
		Usuario usuario=new Usuario();      //instancio la clase Usuario y uso sus metodos getter and setters
		int id=usuario.getId();
		String nombre=usuario.getNombre();
		String apellido=usuario.getApellido();
		String fechajava=usuario.getNacimient();
		String pass=usuario.getPass();
		String email=usuario.getEmail();
        int id_Fk=usuario.getId_insc_usuario();

		try{                                             //captura de datos por consola
			System.out.println("pon el id de usuario:");    
		    id=leeInt();
			System.out.println("Pon el nuevo nombre:");
			nombre=leeTexto();
			System.out.println("Pon el nuevo apellido:");
			apellido=leeTexto();
			System.out.println("Pon la fecha:(yyyy-MM-dd)");   //capturo el string 
			fechajava=leeTexto();
			java.sql.Date fechaDB=formatoFecha(fechajava);    //cambia a fecha de String a tipo Date Sql 
			System.out.println("Pon el password:");
			pass=leeTexto();
			System.out.println("Pon el  email:");
			email=leeTexto();
			System.out.println("pon el id FORANEO (Inscripcion : 1/2/3/4 )");
			id_Fk=leeInt();
		                                  
			String query="INSERT INTO usuario (`id_usu`, `nombre_usu`, `apellido_usu`, `fec_nac_usu`, `contrasenia_usu`,"
					+ " `email_usu`, `usu_id_insc`)" + "VALUES(?,?,?,?,?,?,?)";
			Connection conexion = Conexion.getConexion();                 
			PreparedStatement ps = conexion.prepareStatement(query);     // preparo la consulta, establesco la conexion,  y seteo la tabla usuario

			ps.setInt(1, id);               //insercion de datos a la tabla usuario
			ps.setString(2, nombre);
			ps.setString(3, apellido);  
			ps.setDate(4, fechaDB);         //fechaDB se transformo a tipo DateTime SQL
			ps.setString(5,pass);
			ps.setString(6,email);

			ps.setInt(7,id_Fk);          //el id foraneo
			ps.executeUpdate();         //ejecutamos la query 

		} catch (Exception e) {
			System.out.println("error2" + e);
		}
		System.out.println("metodo insertarUsuario ejecutado!!");
	}

	//******************************/ 	INSERTA INSCRIPCION   /******************************************
	
	public void insertaInscripcion() throws SQLException{
		
		Inscripcion inscripcion=new Inscripcion();  //instancio la clase Inscripcion y uso sus metodos getter and setters
		int id=inscripcion.getId();
		String nombre=inscripcion.getNombre();
		String fechajava=inscripcion.getFecha();
		int id_Fk=inscripcion.getId_insc_car();


		try{                                                       //captura de datos por consola 
			System.out.println("pon el id de la inscripcion:");
			id=leeInt();
			System.out.println("Pon el nuevo nombre de la inscripcion:");
			nombre=leeTexto();
			System.out.println("Pon la fecha:(yyyy-MM-dd)");
			fechajava=leeTexto();
			java.sql.Date fechaDB=formatoFecha(fechajava);    //cambia a fecha de String a tipo Date Sql 
			System.out.println("pon el id FORANEO(Carrera:1-2-3-4-5-6)");
			id_Fk=leeInt();                           
			                                                    // preparo la consulta, establesco la conexion,  y seteo la tabla usuario
			
			String query="INSERT INTO inscripcion (`id_insc`, `nombre_insc`, `fecha_insc`, `insc_id_carrera`) " + "VALUES(?,?,?,?)";
			Connection conexion = Conexion.getConexion();
			PreparedStatement ps = conexion.prepareStatement(query); 
		                                                                 	 
			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setDate(3, fechaDB);  
			ps.setInt(4, id_Fk);          //insercion de datos a la tabla inscripcion

			ps.executeUpdate();       //ejecutamos la query 

		} catch (Exception e) {
			System.out.println("error2" + e);
		}
		System.out.println("metodo insertarInscripcion ejecutado!!");

	}

	//******************************/ 	INSERTA CARRERA   /******************************************
	public void insertaCarrera() throws SQLException{
		
		Carrera carrera=new Carrera();      //instancio la clase Inscripcion y uso sus metodos getter and setters
		int id=carrera.getId();
		String nombre=carrera.getNombre();
		String duracion=carrera.getDuracion();
	                                                           	//captura de datos por consola
		try{
			System.out.println("pon el id de la carrera:");
			id=leeInt();
			System.out.println("Pon el nuevo nombre de la carrera:");
			nombre=leeTexto();
			System.out.println("Pon la duracion de la carrera:");
			duracion=leeTexto();
			                              // preparo la consulta, establesco la conexion,  y seteo la tabla usuario

			String query="INSERT INTO carrera (`id_carrera`, `nombre_carrera`, `duracion_carrera`) " + "VALUES(?,?,?)";
			Connection conexion = Conexion.getConexion();
			PreparedStatement ps = conexion.prepareStatement(query); 

			ps.setInt(1, id);                             //insercion de datos a la tabla carrera
			ps.setString(2, nombre);
			ps.setString(3, duracion);  

			ps.executeUpdate();  //ejecutamos la query 

		} catch (Exception e) {
			System.out.println("error2" + e);
		}
		System.out.println("metodo insertarCarrera ejecutado!!");


	}

	//******************************/ 	MODIFICA USUARIO    /******************************************

	public void editaUsuario() throws SQLException {

		Usuario usuario=new Usuario();          //instancio la clase Usuario y sus metodos getters and setters
		int id_us=usuario.getId();            
		String nom_us=usuario.getNombre();
		String fechajava=usuario.getNacimient();
		int id_Fk=usuario.getId_insc_usuario();
		try {
                                                               //captura de datos
			System.out.println("pon el numero de id:");
			id_us=leeInt();
			System.out.println("Pon el nombre:");
			nom_us=leeTexto();

			System.out.println("Pon la fecha:(yyyy-MM-dd)");
			fechajava=leeTexto();
			java.sql.Date fechaDB=formatoFecha(fechajava);    //cambia a fecha de String a tipo Date Sql 
			System.out.println("pon el id FORANEO (Inscripcion : 1/2/3/4 )");
			id_Fk=leeInt();
			                              // preparo la consulta, establesco la conexion,  y seteo la tabla usuario

			String query3="UPDATE `usuario` SET `nombre_usu` = ?,fec_nac_usu=?, `usu_id_insc` = ? WHERE `usuario`.`id_usu` = ?;";

			Connection conexion = Conexion.getConexion();
			PreparedStatement ps = conexion.prepareStatement(query3);
			ps.setString(1, nom_us);           //modificando los datos a la tabla carrera
			ps.setDate(2, fechaDB);
			ps.setInt(3,id_Fk);

			ps.setInt(4,id_us);              //AL modificar la tabla usuario el id va al final como ACA!!
			ps.executeUpdate();                  //ejecutamos la query 

			System.out.println("metodo editaUsuario ejecutado!!");                                           // modofica por el id() , y modica el nombre,dni,ape,tel
		} catch (Exception e) {
			System.out.println("error2"+ e);
		}
	}

	//******************************/ 	ELIMINA USUARIO     /******************************************

	public void eliminaUsuario() throws SQLException {     //DELETE por el id() , es decir por el id
		 
		Usuario usuario=new Usuario();    //instanciamos la clase Usuario y todos sus metodos getters and setters
		int id_users=usuario.getId();  
        Connection conexion = Conexion.getConexion();  //estalesco la conexion
		try {
			System.out.println("pon el numero de id:");  //lectura de datos
			id_users=leeInt();  
                                                                
			String query = "DELETE FROM `usuario` WHERE `id_usu` = ?";         // preparo la consulta    
			PreparedStatement ps = conexion.prepareStatement(query);

			ps.setInt(1, id_users);           // 1 no es el id 1 , sino que corresponde al primer campo 
			ps.executeUpdate();

			System.out.println("metodo eliminaUsuario ejecutado!!");		

		} catch (Exception e) {
			System.out.println("error2"+ e);
		}	                    	                             //metodo que elimina por numero de  (id)
	}				
	//--------------------------------------------------------------------------------------
	// Metodos auxiliares  

	//captura texto usando bufferes
	public static String leeTexto() throws Exception {     
		BufferedReader datoTexto = new BufferedReader(new InputStreamReader(System.in));
		return datoTexto.readLine();
	}
	//captura enteros
	public static int leeInt() {
		Scanner datoint = new Scanner(System.in);
		return datoint.nextInt();
	}
	//metodo que cambia un formato String a formato Date SQL para que haya persistencia entre las fechas
	public static java.sql.Date formatoFecha(String fechaJ) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
		java.util.Date dateStr =formatter.parse(fechaJ);
		java.sql.Date fechaSQL = new java.sql.Date(dateStr.getTime());

		return fechaSQL ;    //retorna la fecha ya en formato SQL
	}


}






