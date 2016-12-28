/**
 * 
 */
package py.una.pol.vone.simulator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo que representa el nodo virtual
 * @author fersauce
 * @version 1.0
 */
public class VirtualNode {
	private String nombre;
	private int identificador;
	private int capacidadCPU;
	private List<VirtualEdge> adyacentes;
	private boolean asignado;
	/**
	 * Constructor de la clase.
	 */
	public VirtualNode() {
	}
	/**
	 * Constructor de la clase
	 * @param nombre Nombre a asignar al nodo.
	 * @param capacidadCPU capacidad de CPU del nodo.
	 * @param id identificador del nodo virtual.
	 */
	public VirtualNode(int id, String nombre, int capacidadCPU) {
		super();
		this.nombre = nombre;
		this.identificador = id;
		this.capacidadCPU = capacidadCPU;
		this.adyacentes = new ArrayList<VirtualEdge>();
		this.asignado = false;
	}
	
	/**
	 * Getter del identificador.
	 * @return identificador del nodo virtual.
	 */
	public int getIdentificador() {
		return identificador;
	}
	/**
	 * Setter del identificador.
	 * @param identificador identificador a setear en el atributo.
	 */
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	/**
	 * @return el nombre del enlace
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre nombre a colocar al nodo.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the capacidadCPU
	 */
	public int getCapacidadCPU() {
		return capacidadCPU;
	}
	/**
	 * @param capacidadCPU the capacidadCPU to set
	 */
	public void setCapacidadCPU(int capacidadCPU) {
		this.capacidadCPU = capacidadCPU;
	}
	/**
	 * @return the adyacentes
	 */
	public List<VirtualEdge> getAdyacentes() {
		return adyacentes;
	}
	/**
	 * @param adyacentes the adyacentes to set
	 */
	public void setAdyacentes(List<VirtualEdge> adyacentes) {
		this.adyacentes = adyacentes;
	}
	/**
	 * @return the asignado
	 */
	public boolean isAsignado() {
		return asignado;
	}
	/**
	 * @param asignado the asignado to set
	 */
	public void setAsignado(boolean asignado) {
		this.asignado = asignado;
	}
}
