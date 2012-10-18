import mx.com.creditoreal.ws.client.Client;
import mx.com.creditoreal.ws.dto.Adicional;
import mx.com.creditoreal.ws.dto.Solicitud;
import mx.com.creditoreal.ws.exception.ClientException;


public class TestWS1 {
	
	
	public static void main(String[]args) throws ClientException{
		Client client = new Client(true);
		
		Solicitud solicitud = new Solicitud();
		solicitud.setReferencia("3");
		solicitud.setDistribuidor("9999");
		solicitud.setSucursal("1111");
		solicitud.setVendedor("");
		solicitud.setDap("0");
		solicitud.setPercepciones(10D);
		solicitud.setDeducciones(1D);
		solicitud.setSolicitud("2".getBytes());
		solicitud.setPagare("1".getBytes());
		solicitud.setIdentificacion("1".getBytes());
		solicitud.setDocadA("1".getBytes());
		solicitud.setDocadB("1".getBytes());
		solicitud.setDocadC("1".getBytes());
		
		String consecutivo=client.solicitudZell(solicitud);
		System.out.println("consecutivo: "+ consecutivo);
		
		Adicional adicional = new Adicional();
		adicional.setConsecutivo(consecutivo);
		adicional.setDocAd("3".getBytes());
		
		boolean respuesta=client.documentoAdicional(adicional);
		System.out.println("Respuesta: "+ respuesta);
	}

}
