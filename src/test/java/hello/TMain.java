package hello;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TMain {

	public static StringBuffer header = new StringBuffer();
	public static StringBuffer body = new StringBuffer();
	static {
		
		header.append("POST /ws HTTP/1.0").append("\r\n");
		header.append("Content-Type: text/xml; charset=utf-8").append("\r\n");
		header.append("Accept: application/soap+xml, application/dime, multipart/related, text/*").append("\r\n");
		header.append("User-Agent: Axis/1.4").append("\r\n");
		header.append("Host: localhost:8080").append("\r\n");
		header.append("Cache-Control: no-cache").append("\r\n");
		header.append("Pragma: no-cache").append("\r\n");
		header.append("SOAPAction: \"\"").append("\r\n");
		
		//body
//		header.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//		header.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
//		header.append("<soapenv:Body><addPerson xmlns=\"http://impl.service\"><p>");
//		header.append("<ns1:age xmlns:ns1=\"http://bean.model\">4</ns1:age><ns2:id xmlns:ns2=\"http://bean.model\">3</ns2:id><ns3:name xmlns:ns3=\"http://bean.model\">4</ns3:name>");
//		header.append("</p></addPerson></soapenv:Body></soapenv:Envelope>");
		body.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		body.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:gs=\"http://spring.io/guides/gs-producing-web-service\">");
		body.append("<soapenv:Header/><soapenv:Body>");
		body.append("<gs:getUserparaRequest><gs:id>23223</gs:id><gs:method>heartbeat</gs:method></gs:getUserparaRequest>");
		body.append("</soapenv:Body></soapenv:Envelope>");


		body.append("\r\n");
	}

	public static void main(String[] args) {
		header.append("Content-Length: " + body.length()).append("\r\n");
		header.append("\r\n");
		header.append("");
		header.append(body.toString());
		
		Socket sock = null;
		try {
			sock = new Socket("localhost", 8080);
			OutputStream out = sock.getOutputStream();
			out = new BufferedOutputStream(out, 8192);
			out.write(header.toString().getBytes("iso-8859-1"));
			out.flush();

			InputStream inp = new BufferedInputStream(sock.getInputStream());

			byte b = 0;
			int len = 0;
			
			ByteArrayOutputStream buf = new ByteArrayOutputStream(4097);
			
			while( (b = (byte) inp.read()) != -1){
				buf.write(b);
				len++;
			}
			
			System.out.println(new String(buf.toByteArray(), 0, len, "utf-8"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
