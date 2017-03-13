package com.entidades;

public class Usuario {
	

    private int id;
    private String nombre;
    private String apellido;
    private String nacimiento;
    private String pass;
    private String email;
    private int id_insc_usuario;
	public Usuario(int id, String nombre, String apellido, String nacimient, String pass, String email,int id_insc_usuario) {
		
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacimiento = nacimient;
		this.pass = pass;
		this.email = email;
		this.id_insc_usuario = id_insc_usuario;
	}
	public Usuario() {
		 
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNacimient() {
		return nacimiento;
	}
	public void setNacimient(String nacimient) {
		this.nacimiento = nacimient;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId_insc_usuario() {
		return id_insc_usuario;
	}
	public void setId_insc_usuario(int id_insc_usuario) {
		this.id_insc_usuario = id_insc_usuario;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", nacimient=" + nacimiento
				+ ", pass=" + pass + ", email=" + email + ", id_insc_usuario=" + id_insc_usuario + "]";
	}
	
    
    
    
}
