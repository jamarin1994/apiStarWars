package com.b2w.starwarsapi.controllers;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public InfoHome home(HttpServletRequest request) {
		
		String docUrl = getDocumentationUrlFrom(request);
		String message = "Bienvenido al proyecto API de Star Wars usando Java, Spring boot y MongoDB, Para obtener más información, consulte la documentación.";
		return new InfoHome(message, docUrl);
				
//		return "Bienvenido al proyecto API de Star Wars usando Java, Spring boot y MongoDB, "
//				+ "Para obtener más información, consulte la documentación. in " + docUrl;
	}
	

	private String getDocumentationUrlFrom(HttpServletRequest request) {
		String scheme = request.getScheme();             // http
	    String serverName = request.getServerName();     // hostname.com
	    int serverPort = request.getServerPort();        // 80
	    
	    // Reconstruir la URL de solicitud original
	    StringBuilder url = new StringBuilder();
	    url.append(scheme).append("://").append(serverName);

	    if (serverPort != 80 && serverPort != 443)
	        url.append(":").append(serverPort);

	    url.append("/swagger-ui.html");
		String docUrl = url.toString();
		return docUrl;
	}
	
	
	// Clase interna
	
	public class InfoHome implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private String message;
		private String docUrl;
		
		
		public InfoHome() { }
		
		public InfoHome(String message, String docUrl) {
			this.message = message;
			this.docUrl = docUrl;
		}

		
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getDocUrl() {
			return docUrl;
		}

		public void setDocUrl(String docUrl) {
			this.docUrl = docUrl;
		}
		
	} 
}
