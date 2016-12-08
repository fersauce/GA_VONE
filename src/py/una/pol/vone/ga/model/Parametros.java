package py.una.pol.vone.ga.model;

/**
 * Esta clase tiene la finalidad de almacenar todos los parametros a utilizar en
 * el simulador.
 * 
 * @author Fernando Saucedo
 * @version 1.0
 * @since 2016-12-07
 */
public class Parametros {
	private int minCPUFisico;
	private int maxCPUFisico;
	private int numeroFSFisico;
	private int minCPUVirtual;
	private int maxCPUVirtual;
	private int minFSVirtual;
	private int maxFSVirtual;
	private String redUtilizada;

	/**
	 * @return the redUtilizada
	 */
	public String getRedUtilizada() {
		return redUtilizada;
	}

	/**
	 * @param redUtilizada the redUtilizada to set
	 */
	public void setRedUtilizada(String redUtilizada) {
		this.redUtilizada = redUtilizada;
	}

	public Parametros() {
	}

	/**
	 * Getter del atributo minCPUFisico
	 * @return el valor minimo seteado de CPU fisica que puede tener un nodo de la red.
	 */
	public int getMinCPUFisico() {
		return minCPUFisico;
	}

	/**
	 * Setter del atributo minCPUFisico
	 * @param minCPUFisico el valor a setear como el minimo de CPU fisica que puede tener un nodo de la red.
	 */
	public void setMinCPUFisico(int minCPUFisico) {
		this.minCPUFisico = minCPUFisico;
	}

	/**
	 * Getter del atributo maxCPUFisico
	 * @return el valor minimo seteado de CPU fisica que puede tener un nodo de la red.
	 */
	public int getMaxCPUFisico() {
		return maxCPUFisico;
	}

	/**
	 * Setter del atributo maxCPUFisico
	 * @param maxCPUFisico el valor a setear como el minimo de CPU fisica que puede tener un nodo de la red
	 */
	public void setMaxCPUFisico(int maxCPUFisico) {
		this.maxCPUFisico = maxCPUFisico;
	}

	/**
	 * Getter del atributo numeroFSFisico
	 * @return el valor seteado de frequency slots que puede tener cada enlace de la red.
	 */
	public int getNumeroFSFisico() {
		return numeroFSFisico;
	}

	/**
	 * 
	 * @param numeroFSFisico the numeroFSFisico to set
	 */
	public void setNumeroFSFisico(int numeroFSFisico) {
		this.numeroFSFisico = numeroFSFisico;
	}

	/**
	 * Getter del atributo minCPUVirtual
	 * @return the minCPUVirtual
	 */
	public int getMinCPUVirtual() {
		return minCPUVirtual;
	}

	/**
	 * @param minCPUVirtual the minCPUVirtual to set
	 */
	public void setMinCPUVirtual(int minCPUVirtual) {
		this.minCPUVirtual = minCPUVirtual;
	}

	/**
	 * @return the maxCPUVirtual
	 */
	public int getMaxCPUVirtual() {
		return maxCPUVirtual;
	}

	/**
	 * @param maxCPUVirtual the maxCPUVirtual to set
	 */
	public void setMaxCPUVirtual(int maxCPUVirtual) {
		this.maxCPUVirtual = maxCPUVirtual;
	}

	/**
	 * @return the minFSVirtual
	 */
	public int getMinFSVirtual() {
		return minFSVirtual;
	}

	/**
	 * @param minFSVirtual the minFSVirtual to set
	 */
	public void setMinFSVirtual(int minFSVirtual) {
		this.minFSVirtual = minFSVirtual;
	}

	/**
	 * @return the maxFSVirtual
	 */
	public int getMaxFSVirtual() {
		return maxFSVirtual;
	}

	/**
	 * @param maxFSVirtual the maxFSVirtual to set
	 */
	public void setMaxFSVirtual(int maxFSVirtual) {
		this.maxFSVirtual = maxFSVirtual;
	}
}
