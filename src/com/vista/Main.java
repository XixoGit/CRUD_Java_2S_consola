package com.vista;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import com.util.Conexion;
import com.util.DataEntity;
import com.entidades.*;

/*
 * Clase main que funciona como vista de usuario.Desplega un Case con opciones
 * instancia la clase DataEntity con sus metodos
 * 
 */

public class Main {

	 
	static int opcion=0;
	private static DataEntity dataentity = new DataEntity(); //instanciamos la clase Dataentyty para usar sus metodos
	static ArrayList<Usuario> usuario;   //creamos un obj arraylist tipo usuario porque pensamos mostrar como 
	                                     //un obj <ÇUsuario>

	public static void main(String[] args) throws SQLException {

		while(opcion<8){

			System.out.println("\n | 1: MOSTRAR USUARIOS (Apellido:A-Z)   \t | 2: MOSTRAR USUARIOS-INSCRIPC-CARRERA JOIN");
            System.out.println("\n | 3: INSERTAR USUARIOS  \t\t\t | 4: INSERTAR INSCRIPCION");
			System.out.println("\n | 5: INSERTAR CARRERA \t\t\t         | 6: EDITAR USUARIO");
			System.out.println("\n | 7: ELIMINA USUARIO \t\t\t         | ----------------------");
			System.out.println("\n | 8: SALIR");
			System.out.println("\n\n Ingrese Opcion: ");
			int opcion=dataentity.leeInt();

			switch(opcion){
			case 1:
				  // como el metodo mostrarUsuario retorna un obj <usuario> usamos el for para mostrarlo aqui
				  
				System.out.println(" \n**************************//MOSTRAR USUARIOS ,SELECT//*************************** ");
				try {
					usuario= dataentity.mostrarUsuarios();  //llamamos al metodo <usuario> mostrarUsuarios();  
					
					for (int i = 0; i <  usuario.size(); i++) { 
						System.out.println("----------------------------");
                        System.out.println("ID:         "+usuario.get(i).getId());
						System.out.println("NOMBRE:     "+usuario.get(i).getApellido());
						System.out.println("APELLIDO:   "+usuario.get(i).getNombre());
						System.out.println("EMAIL:      "+usuario.get(i).getEmail());
						System.out.println("NACIMIENTO: "+usuario.get(i).getNacimient());
						System.out.println("PASSWORD:   "+usuario.get(i).getPass());
						System.out.println("FOREYKEY:   "+usuario.get(i).getId_insc_usuario());
					}

				} catch (SQLException e) {
					System.out.println("error" + e );
				}
				break;

			case 2:
				System.out.println(" \n******************//MOSTRAR USUARIOS ,SELECT JOIN//*************************** ");
				try {
					dataentity.mostrarUsuariosJOIN();  // llamamos al metodo void mostrarUsuarioJOIN
				} catch (SQLException e) {
					System.out.println("error" + e );
				}
				break;

			case 3:
				System.out.println("\n ******************//INSERTA USUARIOS , INSERT//*************************");
				dataentity.insertaUsuario();          //llamamos al metodo void  insertaUsuario();   
				break;
			case 4:
				System.out.println("\n ******************//INSERTA INSCRIPCION , INSERT//****************************");
				dataentity.insertaInscripcion();             //llamamos al metodo void 
				break;
			case 5:
				System.out.println("\n ******************//INSERTA CARRERA , INSERT//****************************");
				dataentity.insertaCarrera();                //llamamos al metodo void  insertaCarrera();  
				break;
			case 6:
				System.out.println("\n ******************//EDITA USUARIOS , DELETE//****************************");
				dataentity.editaUsuario();               //llamamos al metodo   void editaUsuario();   
				break;
			case 7:
				System.out.println("\n ******************//ELIMINA USUARIOS , DELETE//****************************");
				dataentity.eliminaUsuario();              //llamamos al metodo void  eliminaUsuario(); 
				break;

			default :
				System.out.println("Default");
			}

			if(opcion==8)break;        //saliendo de la aplicacin

		}  	 
		Conexion.cierraConexion();  //cierra la conexion al salir


		System.out.println("SALIENDO DE LA APLICACION");
		System.out.println("\n\n\n\n\t************ CopyRight FRANK ESCOBAR & DIEGO GODOY - fedark@hotmail.com ***********");
		System.out.println("cerrando conexion");



	}





}
