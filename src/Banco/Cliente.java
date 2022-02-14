package Banco;

public class Cliente {
	
	private int ID_CLIENTE;
	private int NIT;
	private String NOMBRE;
	private int BLOQUEADO; // 0 = not block & 1 = block

	public Cliente( int id_cliente, int nit, String nombre)
	{
		super();
		this.ID_CLIENTE= id_cliente;
		this.NIT= nit;
		this.NOMBRE = nombre;
		this.BLOQUEADO = 0; // default, this account is not block
	}

	public int getID_CLIENTE() {
		return ID_CLIENTE;
	}

	public void setID_CLIENTE(int iD_CLIENTE) {
		ID_CLIENTE = iD_CLIENTE;
	}

	public int getNIT() {
		return NIT;
	}

	public void setNIT(int nIT) {
		NIT = nIT;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public int getBLOQUEADO() {
		return BLOQUEADO;
	}

	public void setBLOQUEADO(int bLOQUEADO) {
		BLOQUEADO = bLOQUEADO;
	}
	
	
}
