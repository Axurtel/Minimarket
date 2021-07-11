package modelo;

public class recepcion {

	private String sentencia, emisor, cargo, razon, fecha;

	public String getSentencia() {
		return sentencia;
	}
	
	public String getEmisor() {
		return emisor;
	}
	
	public String getCargo() {
		return cargo;
	}
	
	public String getRazon() {
		return razon;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setSentencia(String sentencia) {
		this.sentencia = sentencia;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public void setRazon(String razon) {
		this.razon = razon;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}	
}
