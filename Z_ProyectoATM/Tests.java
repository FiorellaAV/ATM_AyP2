import java.io.IOException;
import java.util.*;
import org.junit.Assert;
import org.junit.Test;

public class Tests {

	@Test
	public void pruebaGetAliasDeCuentaGuardada() throws IOException{
		
		Cajero mf = new Cajero();
		Stack<Movimiento> listaDeMovimientos = new Stack<Movimiento>();
		CajaAhorroPesos cuenta = new CajaAhorroPesos("isla.pez.arbol", 12000.03, listaDeMovimientos);
		Assert.assertEquals(cuenta.getAlias(), mf.getCuenta(0).getAlias());		
	}
	@Test
	public void pruebaGetSaldoDeCuentaGuardada() throws IOException{
		Cajero mf = new Cajero();
		Stack<Movimiento> listaDeMovimientos = new Stack<Movimiento>();
		CajaAhorroPesos cuenta = new CajaAhorroPesos("isla.pez.arbol", 12000.03, listaDeMovimientos);

		Assert.assertEquals(cuenta.getSaldo(), mf.getCuenta(0).getSaldo(), 0);
	}
	@Test
	public void pruebaGetPinDeCuentaGuardado() throws IOException{
		Cajero mf = new Cajero();
		Stack<Movimiento> listaDeMovimientos = new Stack<Movimiento>();
		CajaAhorroPesos cuenta = new CajaAhorroPesos("isla.pez.arbol", 12000.03, listaDeMovimientos);
	
		Assert.assertEquals(cuenta.toString(), mf.getCuenta(0).toString());
	}
	@Test
	public void pruebaGetAliasDeCuenta2Guardado() throws IOException{
		Cajero mf = new Cajero();
		Stack<Movimiento> listaDeMovimientos = new Stack<Movimiento>();

		CuentaCorriente cuenta2 = new CuentaCorriente ("sol.monte.valle",-1021.90,25000.00, listaDeMovimientos);
	
		Assert.assertEquals(cuenta2.getAlias(), mf.getCuenta(2).getAlias());		
	}
	@Test
	public void pruebaGetSaldoDeCuenta2Guardado() throws IOException{
		Cajero mf = new Cajero();
		Stack<Movimiento> listaDeMovimientos = new Stack<Movimiento>();
		
		CuentaCorriente cuenta2 = new CuentaCorriente ("sol.monte.valle",-1021.90,25000.00, listaDeMovimientos);

		Assert.assertEquals(cuenta2.getSaldo(), mf.getCuenta(2).getSaldo(), 0);
	}
	
	@Test
	public void depositarEnCuentaGuardada() throws IOException{
		Cajero mf = new Cajero();
		Stack<Movimiento> listaDeMovimientos = new Stack<Movimiento>();
		CajaAhorroPesos cuenta = new CajaAhorroPesos("isla.pez.arbol", 12000.03, listaDeMovimientos);
		
		cuenta.incrementarSaldo(1000); 
		mf.getCuenta(0).incrementarSaldo(1000);

		Assert.assertEquals(cuenta.getSaldo(), mf.getCuenta(0).getSaldo(),0);	
	}
	@Test
	public void retirarEnCuentaGuardada() throws IOException, ErrorDeTransaccion{
		Cajero mf = new Cajero();
		Stack<Movimiento> listaDeMovimientos = new Stack<Movimiento>();
		CajaAhorroPesos cuenta = new CajaAhorroPesos("isla.pez.arbol", 12000.03, listaDeMovimientos);
		
		cuenta.reducirSaldo(1000);
		mf.getCuenta(0).reducirSaldo(1000);
		
		Assert.assertEquals(cuenta.getSaldo(),mf.getCuenta(0).getSaldo(),0);
	}
	@Test
	public void pruebaGetNumeroDeTarjetaGuardada() throws IOException{
		Cajero mf = new Cajero();
		
		
		Tarjeta tarjeta = new Tarjeta("12345678","1234","27102551236");
		
		Assert.assertEquals(tarjeta.getNumero(), mf.getTarjeta(0).getNumero());
	}
	@Test
	public void pruebaGetCuitDeTarjetaGuardada() throws IOException{
		Cajero mf = new Cajero();
		
		
		Tarjeta tarjeta = new Tarjeta("12345678","1234","20302006149");
		
		Assert.assertEquals(tarjeta.getCuit(), mf.getTarjeta(0).getCuit());
	}
	@Test
	public void pruebaGetPinDeTarjeta4Guardada() throws IOException{
		Cajero mf = new Cajero();
	
		Tarjeta tarjeta = new Tarjeta("45678901","4567","20311573951");
		
		Assert.assertEquals(tarjeta.getPin(), mf.getTarjeta(3).getPin());
	}
	@Test
	public void pruebaGetNumeroDeTarjetaEquivocada() throws IOException{
		Cajero mf = new Cajero();
		
		Tarjeta tarjeta2 = new Tarjeta("23456789","2345","27102551236");
		Assert.assertNotEquals(tarjeta2.getNumero(), mf.getTarjeta(0).getNumero());
	}
	@Test
	public void pruebaGetAliasDeCuenta3() throws IOException{
		Cajero mf = new Cajero();
		Stack<Movimiento> listaDeMovimientos = new Stack<Movimiento>();
		
		CajaAhorroDolares cuenta3 = new CajaAhorroDolares("lobo.luna.pasto", 200.00, listaDeMovimientos);
	
		Assert.assertEquals(cuenta3.getAlias(), mf.getCuenta(5).getAlias());
	}
			
	@Test
	public void pruebaGetUltimosMovimientos(){
		
		Stack<Movimiento> movimientos = new Stack<Movimiento>();
			
		Movimiento m1=new Movimiento("2020-05-30","Peron","isla.pez.arbol",50.00);
		Movimiento m2=new Movimiento("2020-05-30","Peron","isla.pez.arbol",50.00);
		Movimiento m3=new Movimiento("2020-05-30","Peron","isla.pez.arbol",50.00);
		Movimiento m4=new Movimiento("2020-05-30","Peron","isla.pez.arbol",50.00);
		Movimiento m5=new Movimiento("2020-05-30","Peron","isla.pez.arbol",50.00);
		Movimiento m6=new Movimiento("2020-05-30","Peron","isla.pez.arbol",50.00);
		Movimiento m7=new Movimiento("2020-05-30","Peron","isla.pez.arbol",50.00);
		Movimiento m8=new Movimiento("2020-05-30","Peron","isla.pez.arbol",50.00);
		Movimiento m9=new Movimiento("2020-05-30","Peron","isla.pez.arbol",50.00);
		
		movimientos.add(m1);movimientos.add(m2);movimientos.add(m3);movimientos.add(m4);movimientos.add(m5);movimientos.add(m6);
		movimientos.add(m7);movimientos.add(m8);movimientos.add(m9);
		
		CajaAhorroPesos cuenta = new CajaAhorroPesos("isla.pez.arbol",12000.00,movimientos);
	
		Stack<String> comparacion = cuenta.getUltimosMovimientos();
		for(String string : comparacion) {
			Assert.assertEquals("2020-05-30,Peron,isla.pez.arbol,50.0",string);
		}
	}
	
	@Test
	public void imprimir(){
		Cajero cajero = new Cajero();
		Stack<Movimiento> movimientos = new Stack<Movimiento>();
			
		Movimiento m1=new Movimiento("2020-05-30", "Peron","isla.pez.arbol", 1.00 );
		Movimiento m2=new Movimiento("2020-05-30", "Peron","isla.pez.arbol", 2.00 );
		Movimiento m3=new Movimiento("2020-05-30", "Peron","isla.pez.arbol", 3.00 );
		Movimiento m4=new Movimiento("2020-05-30", "Peron","isla.pez.arbol", 4.00 );
		Movimiento m5=new Movimiento("2020-05-30", "Peron","isla.pez.arbol", 5.00 );
		Movimiento m6=new Movimiento("2020-05-30", "Peron","isla.pez.arbol", 6.00 );
		Movimiento m7=new Movimiento("2020-05-30", "Peron","isla.pez.arbol", 7.00 );
		Movimiento m8=new Movimiento("2020-05-30", "Peron","isla.pez.arbol", 8.00 );
		Movimiento m9=new Movimiento("2020-05-30", "Peron","isla.pez.arbol", 9.00 );
		
		
		movimientos.add(m1);movimientos.add(m2);movimientos.add(m3);movimientos.add(m4);movimientos.add(m5);movimientos.add(m6);
		movimientos.add(m7);movimientos.add(m8);movimientos.add(m9);
		
		CajaAhorroPesos cuenta = new CajaAhorroPesos("isla.pez.arbol", 12000.00 ,movimientos);
	
		try {
			cajero.getUltimosMovimientos(cuenta);
		}catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
}
