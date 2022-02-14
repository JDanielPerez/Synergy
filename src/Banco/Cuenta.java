package Banco;

public class Cuenta {

	private int ID_CUENTA;
	private int ID_CLIENTE;
	private int BLOQUEADA;
	private double SALDO_ACTUAL;
	private double SALDO_MINIMO;
	private int NUMERO_DE_OPERACIONES;
	private int NUMERO_CUENTA;
	
	public Cuenta(int id_cuenta, int id_cliente,double saldo_minimo,int numero_cuenta)
	{
		this.ID_CUENTA = id_cuenta;
		this.ID_CLIENTE = id_cliente;
		this.BLOQUEADA = 0;
		this.SALDO_ACTUAL = 0;
		this.SALDO_MINIMO = saldo_minimo;
		this.NUMERO_DE_OPERACIONES = 0;
		this.NUMERO_CUENTA = numero_cuenta;
	}

	public int getID_CUENTA() {
		return ID_CUENTA;
	}

	public void setID_CUENTA(int iD_CUENTA) {
		ID_CUENTA = iD_CUENTA;
	}

	public int getID_CLIENTE() {
		return ID_CLIENTE;
	}

	public void setID_CLIENTE(int iD_CLIENTE) {
		ID_CLIENTE = iD_CLIENTE;
	}

	public int getBLOQUEADA() {
		return BLOQUEADA;
	}

	public void setBLOQUEADA(int bLOQUEADA) {
		BLOQUEADA = bLOQUEADA;
	}

	public double getSALDO_ACTUAL() {
		return SALDO_ACTUAL;
	}

	public void setSALDO_ACTUAL(double sALDO_ACTUAL) {
		SALDO_ACTUAL = sALDO_ACTUAL;
	}

	public double getSALDO_MINIMO() {
		return SALDO_MINIMO;
	}

	public void setSALDO_MINIMO(double sALDO_MINIMO) {
		SALDO_MINIMO = sALDO_MINIMO;
	}

	public int getNUMERO_DE_OPERACIONES() {
		return NUMERO_DE_OPERACIONES;
	}

	public void setNUMERO_DE_OPERACIONES(int nUMERO_DE_OPERACIONES) {
		NUMERO_DE_OPERACIONES = nUMERO_DE_OPERACIONES;
	}

	public int getNUMERO_CUENTA() {
		return NUMERO_CUENTA;
	}

	public void setNUMERO_CUENTA(int nUMERO_CUENTA) {
		NUMERO_CUENTA = nUMERO_CUENTA;
	}
	
}
