/**
 * 
 */
package py.una.pol.vone.ga.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fernandosaucedo
 * Modelo que representa el nodo virtual
 */
public class VirtualNode {
	private String nombre;
	private int capacidadCPU;
	private List<VirtualEdge> adyacentes;
	private boolean asignado;
	/**
	 * 
	 */
	public VirtualNode() {
		// TODO Auto-generated constructor stub
	}
	public VirtualNode(String nombre, int capacidadCPU, 
			int frequencySlotsNecesarios) {
		super();
		this.nombre = nombre;
		this.capacidadCPU = capacidadCPU;
		this.adyacentes = new ArrayList<VirtualEdge>();
		this.asignado = false;
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
