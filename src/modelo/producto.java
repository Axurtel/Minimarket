package modelo;

public class producto {

	private int codigo;
	private String producto;
	private String marca;
	private int lote;
	private double precio;
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getProducto() {
		return producto;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public int getLote() {
		return lote;
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
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public void setLote(int lote) {
		this.lote = lote;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
