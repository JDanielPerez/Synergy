package Banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.*;

public class Main {

	static int select = -1;
	private static String url ="jdbc:sqlserver://jdperez\\sqlexpress;databaseName=Banco";
	private static String user = "usuarioSQL";
	private static String password = "123";
	public static void main(String[] args)
	{
		LocalDate fecha = LocalDate.now();
		while(select != 0)
		{
			try
			{
				String lectura = JOptionPane.showInputDialog(null,
						"Elige una opción:"
						+"\n 1. Crear cliente."
						+"\n 2. Crear cuenta."
						+"\n 3. Deposito. "
						+"\n 4. Retiro."
						+"\n 5. Transferencia."
						+"\n 6. Borrar cliente."
						+"\n 7. Buscar cliente.");
				
				select = Integer.parseInt(lectura);
				
				switch(select)
				{
				case 1: //crear clinte
					JOptionPane.showMessageDialog(null, "Por favor rellene los siguientes campos:");
					int nit = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese nit"));
					String name = JOptionPane.showInputDialog(null, "Ingrese el nombre:");
					Crear_Cliente(nit,name);
					break;
					
				case 2: //crear cuenta
					JOptionPane.showMessageDialog(null, "Por favor rellene los siguiente campos:");
					int idc = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID del cliente:"));
					int nc = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el Numero de cuenta:"));
					Crear_Cuenta(idc,nc);
					break;
				case 3://Deposito
					
					JOptionPane.showMessageDialog(null, "Por favor rellene los siguiente campos:");
					int ic= Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID de la cuenta:"));
					double v = Double.parseDouble(JOptionPane.showInputDialog(null,"Ingrese el valor de la operación:"));
					String fe= String.valueOf(fecha);
					String t="D";
					Depositar(ic,t,v,fe);
					break;
				case 4:// retiro
					JOptionPane.showMessageDialog(null, "Por favor rellene los siguiente campos:");
					int ic1= Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID de la cuenta:"));
					double v1 = Double.parseDouble(JOptionPane.showInputDialog(null,"Ingrese el valor de la operación:"));
					String fe1= String.valueOf(fecha);
					String t1="R";
					Retirar(ic1,t1,v1,fe1);
					break;
				case 5: // Deposito x transferencia
					JOptionPane.showMessageDialog(null, "Por favor rellene los siguiente campos:");
					int ids= Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el ID de la cuenta de donde saldra el dinero:"));
					int idr= Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la cuenta que va a recibir el dinero:"));
					double v2 = Double.parseDouble(JOptionPane.showInputDialog(null,"Ingrese el valor de la operación:"));
					String fe2= String.valueOf(fecha);
					String dt="DT";
					String rt="RT";
					if(Retirar(ids,rt,v2,fe2)==1)
					{
					Depositar(idr,dt,v2,fe2);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Fallo en la transacción.");
					}
					break;
				case 6: // borrar cliente
					int ib = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id del usuario a borrar:"));
					BorrarCliente(ib);
					break;
				case 7:// Buscar cliente
					int i=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el Id del cliente"));
					BuscarCliente(i);
					break;
					
				}
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null,"Error");
			}
		}
	}
	
	public static void BuscarCliente(int i)
	{
		int a = i;
		
		try
		{
			Connection connection = DriverManager.getConnection(url, user, password);
			
			
				String sql ="SELECT * FROM TblCliente WHERE ID_CLIENTE=?";
						
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, a);
				
				ResultSet rs = statement.executeQuery();
				
				while(rs.next())
				{
					JOptionPane.showMessageDialog(null, "Los datos del cliente son:\nID_CLIENTE: "+rs.getInt("ID_CLIENTE")
					+ "\nNIT: "+rs.getInt("NIT")+ "\nNOMBRE: "+rs.getString("NOMBRE")+"\nBLOQUEADO: "+rs.getInt("BLOQUEADO"));
				}
				rs.close();
			connection.close();	
			
		}
		catch(SQLException e) 
		{
			System.out.println("oops, there´s an error:");
			e.printStackTrace();	
		}
	}
	
	public static void BorrarCliente(int ib)
	{
		int a = ib;

		try
		{
			Connection connection = DriverManager.getConnection(url, user, password);
			
			
				String sql ="DELETE TblMovimiento "
						+   "FROM TblCuenta t INNER JOIN TblCliente tm ON t.ID_CLIENTE = tm.ID_CLIENTE "
						+   "WHERE t.ID_CLIENTE=? "
						+   "DELETE TblCuenta FROM TblCuenta WHERE ID_CLIENTE=? "
						+   "DELETE TblCliente FROM TblCliente WHERE ID_CLIENTE=?";
						
						 
				
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, a);
				statement.setInt(2, a);
				statement.setInt(3, a);
				
				int rows = statement.executeUpdate();
				if(rows >0)
				{
					JOptionPane.showMessageDialog(null, "Eliminado con exito.");
				}else
				{
					JOptionPane.showMessageDialog(null,"No pudo ser eliminado, o no ha sido encontrado.");
					
				}
				
			connection.close();	
			
		}
		catch(SQLException e) 
		{
			System.out.println("oops, there´s an error:");
			e.printStackTrace();	
		}
	}

	public static void Crear_Cliente( int nit, String name)
	{
		int b = nit;
		String c = name;
		
		try
		{
			Connection connection = DriverManager.getConnection(url, user, password);
			
			
				String sql ="IF(SELECT * FROM TblCliente WHERE NIT=? = NULL ) "
						+ "BEGIN "
						+ "RAISERERROR ('NO SE ENCUENTRA EL USUARIO')"
						+ "ROLLBACK TRANSACTION"
						+ "END "
						+ "ELSE "
						+ "BEGIN "
						 //+ "IF NOT EXISTS( SELECT 1 FROM TblCliente WHERE NIT="+b+")"
						+ "INSERT INTO TblCliente (NIT, NOMBRE) "
						+ "VALUES (?,?) "
						+ "END ";
				
				PreparedStatement statement = connection.prepareStatement(sql);
				
				statement.setInt(1, b);
				statement.setInt(2, b);
				statement.setString(3, c);
				
				
				int rows = statement.executeUpdate();
				if(rows >0)
				{
					JOptionPane.showMessageDialog(null, "Cliente creado con exito.");
				}else
				{
					JOptionPane.showMessageDialog(null,"No pudo ser creado, verifique los datos.");
					
				}
				
			connection.close();		
		
		
		}
		catch(SQLException e) 
		{
			System.out.println("oops, there´s an error:");
			e.printStackTrace();	
		}			
		
	}
	public static void Crear_Cuenta(int idc, int nc)
	{
		int a = idc;
		int b= nc;
		
		try
		{
			Connection connection = DriverManager.getConnection(url, user, password);
			
			
				String sql ="IF NOT EXISTS( SELECT * FROM TblCuenta WHERE ID_CLIENTE=?)"
						+"BEGIN "
						+"INSERT INTO TblCuenta (ID_CLIENTE,NUMERO_CUENTA) "
						+"VALUES (?,?) "
						+"END ";
				
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, a);
				statement.setInt(2, a);
				statement.setInt(3, b);
				
				
				int rows = statement.executeUpdate();
				if(rows >0)
				{
					JOptionPane.showMessageDialog(null, "Cuenta creada con exito.");
				}else
				{
					JOptionPane.showMessageDialog(null,"No pudo ser creada, verifique los datos");
					
				}
				
			connection.close();	
			
		}
		catch(SQLException e) 
		{
			System.out.println("oops, there´s an error:");
			e.printStackTrace();	
		}
			
	}
	
	public static int Retirar(int idcuenta, String tipo, double valor, String fecha) 
	{
		int idc = idcuenta;
		double v = valor;
		String f=fecha;
		String t=tipo;
		int con=0;
		try
		{
			Connection connection = DriverManager.getConnection(url, user, password);
			
				String sql ="IF EXISTS( SELECT 1 FROM TblCuenta WHERE ID_CUENTA= "+idc+" AND BLOQUEADA=0) "
						// Error no toma encuenta las demas condiciones, solo la del id_cuenta
						+ "INSERT INTO TblMovimiento (ID_CUENTA, TIPO, VALOR, FECHA_MOVIMIENTO) "
						+ "VALUES (?,?,?,?)"
						+ "UPDATE TblCuenta SET SALDO_ACTUAL = SALDO_ACTUAL-"+v+" WHERE ID_CUENTA= "+idc
						+ "UPDATE TblCuenta SET NUMERO_OPERACIONES= NUMERO_OPERACIONES+1 WHERE ID_CUENTA= "+idc ;
				
				PreparedStatement statement = connection.prepareStatement(sql);
				
				
				statement.setInt(1, idc);
				statement.setString(2, t);
				statement.setDouble(3, v);
				statement.setString(4, f);
				
				
				
				int rows = statement.executeUpdate();
				if(rows >0)
				{
					JOptionPane.showMessageDialog(null, "Transacción exitosa.");
					con=1;
					return con;
				}else
				{
					JOptionPane.showMessageDialog(null,"Fallo en la transacción.");
					
				}
				
			connection.close();	
			
		}
		catch(SQLException e) 
		{
			System.out.println("oops, there´s an error:");
			e.printStackTrace();	
		}
		return con;
		
	}
	public static void Depositar(int idcuenta, String tipo, double valor, String fecha)
	{
		int idc = idcuenta;
		double v = valor;
		String f=fecha;
		String t=tipo;
		
		try
		{
			Connection connection = DriverManager.getConnection(url, user, password);
			
			
				String sql ="IF EXISTS( SELECT 1 FROM TblCuenta WHERE ID_CUENTA="+idc+" AND BLOQUEADA=0)"
						+ "INSERT INTO TblMovimiento (ID_CUENTA, TIPO, VALOR, FECHA_MOVIMIENTO) "
						+ "VALUES (?,?,?,?)"
						+ "UPDATE TblCuenta SET SALDO_ACTUAL = SALDO_ACTUAL+"+v+" WHERE ID_CUENTA="+idc 
						+ "UPDATE TblCuenta SET NUMERO_OPERACIONES = NUMERO_OPERACIONES+1 WHERE ID_CUENTA="+idc;
						
				
				PreparedStatement statement = connection.prepareStatement(sql);
				
				statement.setInt(1, idc);
				statement.setString(2, t);
				statement.setDouble(3, v);
				statement.setString(4, f);
				
				int rows = statement.executeUpdate();
				if(rows >0)
				{
					JOptionPane.showMessageDialog(null, "Transacción exitosa.");
				}else
				{
					JOptionPane.showMessageDialog(null,"Fallo en la transacción.");
					
				}
				
			connection.close();	
			
		}
		catch(SQLException e) 
		{
			System.out.println("oops, there´s an error:");
			e.printStackTrace();	
		}
	
	}

}
