package Banco;

public class Movimiento {

	private int MOVIMIENTO;
	private int ID_CUENTA;
	private String TIPO; // (D: Deposito, DT=Deposito x transferencia, R=Retiro, RT= Retiro x Transferencia,
	private double VALOR;
	private String FECHA_MOVIMIENTO;
	
	public Movimiento( int movimiento, int id_cuenta, String tipo, double valor, String fecha_movimiento)
	{
		this.MOVIMIENTO = movimiento;
		this.ID_CUENTA = id_cuenta;
		this.TIPO= tipo;
		this.VALOR=valor;
		this.FECHA_MOVIMIENTO= fecha_movimiento;
	}

	public int getMOVIMIENTO() {
		return MOVIMIENTO;
	}

	public void setMOVIMIENTO(int mOVIMIENTO) {
		MOVIMIENTO = mOVIMIENTO;
	}

	public int getID_CUENTA() {
		return ID_CUENTA;
	}

	public void setID_CUENTA(int iD_CUENTA) {
		ID_CUENTA = iD_CUENTA;
	}

	public String getTIPO() {
		return TIPO;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	public double getVALOR() {
		return VALOR;
	}

	public void setVALOR(double vALOR) {
		VALOR = vALOR;
	}

	public String getFECHA_MOVIMIENTO() {
		return FECHA_MOVIMIENTO;
	}

	public void setFECHA_MOVIMIENTO(String fECHA_MOVIMIENTO) {
		FECHA_MOVIMIENTO = fECHA_MOVIMIENTO;
	}
}
