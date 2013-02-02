import java.util.List;

import mx.com.creditoreal.ws.client.Client;
import mx.com.creditoreal.ws.dto.SolicitudDecididasDia;
import mx.com.creditoreal.ws.dto.SolicitudDia;
import mx.com.creditoreal.ws.dto.CarteraGeneradaDia;


public class TestWS2 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		Client cliente = new Client(true);
		
//		List<SolicitudDia> solicitudes=cliente.getSolicitudesDia("9999", "20121213", "", "");
//		for(SolicitudDia s:solicitudes){
//			System.out.println(s.getNombre());
//		}
		
//		List<SolicitudDecididasDia> solicitudes2=cliente.getSolicitudesDecididasDia("9999", "20121213", "", "");
//		for(SolicitudDecididasDia s : solicitudes2){
//			System.out.println(s.getNombre());
//		}
		
//		List<ComprasDia> compras=cliente.getComprasDia("9999", "20121213", "", "");
//		for(ComprasDia c: compras){
//			System.out.println(c.getNombre());
//		}
		
		List<CarteraGeneradaDia> carteras=cliente.getCarteraGeneradaDia("9999", "20121213", "", "");
		for(CarteraGeneradaDia cartera: carteras){
			System.out.println(cartera.getNombre());
		}
		
//		String r=
//		cliente.recepcionPago(
//				"Usuario" , "Password" , "1" , "00000001570815" , "0000000001-7" , "9999" , "001" , "NOR" , "51" , "646.67" , "0" , "0000099990-1"
//				, "04" , "28" , "2011"
//				);
//		
//		System.out.println("R: " + r);
		
		
	}

}
