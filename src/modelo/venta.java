package modelo;

public class venta {

	private int codigo, cantidad;
	private String producto;
	private double precio;
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getProducto() {
		return producto;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
