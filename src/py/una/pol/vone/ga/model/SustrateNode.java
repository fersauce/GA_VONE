/**
 * 
 */
package py.una.pol.vone.ga.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fersauce
 * Modelo que representa al nodo sustrato.
 */
public class SustrateNode {
	private int ID;
	private String nombre;
	private int capacidadCPU;
	private int[] CPU;
	private List<SustrateEdge> adyacentes;
	/**
	 * 
	 */
	public SustrateNode() {
		// TODO Auto-generated constructor stub
	}
	
	public SustrateNode(int id, String nombre, int capacidadCPU, int[] CPU){
		this.ID = id;
		this.nombre = nombre;
		this.capacidadCPU = capacidadCPU;
		this.CPU = new int[this.capacidadCPU];
		this.adyacentes = new ArrayList<SustrateEdge>();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidadCPU() {
		return capacidadCPU;
	}

	public void setCapacidadCPU(int capacidadCPU) {
		this.capacidadCPU = capacidadCPU;
	}

	public int[] getCPU() {
		return CPU;
	}

	public void setCPU(int[] cPU) {
		CPU = cPU;
	}

	public List<SustrateEdge> getAdyacentes() {
		return adyacentes;
	}

	public void setAdyacentes(List<SustrateEdge> adyacentes) {
		this.adyacentes = adyacentes;
	}
	
	

}
