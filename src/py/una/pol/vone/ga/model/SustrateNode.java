/**
 * 
 */
package py.una.pol.vone.ga.model;

import java.util.ArrayList;

/**
 * Modelo que representa al nodo sustrato.
 * @author fersauce
 */
public class SustrateNode {
	private int ID;
	private String nombre;
	private int capacidadCPU;
	private String [] CPU;
	private ArrayList<SustrateEdge> adyacentes;
	/**
	 * Constructor de clase.
	 */
	public SustrateNode() {
	}
	
	/**
	 * Constructor de clase.
	 * @param id id del nodo.
	 * @param nombre nombre asignado al nodo.
	 * @param capacidadCPU capacidad total del nodo.
	 */
	public SustrateNode(int id, String nombre, int capacidadCPU){
		super();
		this.ID = id;
		this.nombre = nombre;
		this.capacidadCPU = capacidadCPU;
		this.CPU = new String [this.capacidadCPU];
		this.adyacentes = new ArrayList<SustrateEdge>();
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
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
	 * @return the cPU
	 */
	public String[] getCPU() {
		return CPU;
	}

	/**
	 * @param cPU the cPU to set
	 */
	public void setCPU(String[] cPU) {
		CPU = cPU;
	}

	/**
	 * @return the adyacentes
	 */
	public ArrayList<SustrateEdge> getAdyacentes() {
		return adyacentes;
	}

	/**
	 * @param adyacentes the adyacentes to set
	 */
	public void setAdyacentes(ArrayList<SustrateEdge> adyacentes) {
		this.adyacentes = adyacentes;
	}
}
