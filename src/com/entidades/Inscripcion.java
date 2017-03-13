package com.entidades;

public class Inscripcion {
	
	    private int id;
	    private String nombre;
	    private String fecha;
        private int id_insc_car;
		public Inscripcion(int id, String nombre, String fecha, int id_insc_car) {
			//super();
			this.id = id;
			this.nombre = nombre;
			this.fecha = fecha;
			this.id_insc_car = id_insc_car;
		}
		public Inscripcion() {
			super();
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
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public int getId_insc_car() {
			return id_insc_car;
		}
		public void setId_insc_car(int id_insc_car) {
			this.id_insc_car = id_insc_car;
		}
		@Override
		public String toString() {
			return "Inscripcion [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", id_insc_car=" + id_insc_car
					+ "]";
		}
        
        

}
