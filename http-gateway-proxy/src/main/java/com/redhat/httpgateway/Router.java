package com.redhat.httpgateway;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Router {

	private static final String BASE_HTTP_URI_FRAGMENT="http://localhost:";
    private static final String BASE_FABRIC_NAME = "fabric:gateway";
    private static final String BASE_CXF_CLUSTER = "fabric:gateway";
    private static final Logger log = LoggerFactory.getLogger(Router.class);
    
    private Map<String, String> someMap = new HashMap<String,String>(); 
    private File routingFile = null; 
    private String endpointFileLocation; 
    Properties endpointProperties = new Properties(); 
    
    public void init() throws Exception{
    	InputStream fileInput = new FileInputStream(endpointFileLocation); 
    	 
    	endpointProperties.load(fileInput);
    	
    }
    

    public String routePlease(Exchange exchange) {
    	log.info("entered route please");
        // delegate to method that will do name/mapping resolution
    	String port = endpointProperties.getProperty((String)exchange.getIn().getHeader(Exchange.HTTP_URI));
    	String result= BASE_HTTP_URI_FRAGMENT + port + exchange.getIn().getHeader(Exchange.HTTP_URI);
    	log.info(result);
    	return result;  
    	 
    }
    
    private String resolveEndpointName(Exchange exchange) {
        // REAL CODE FOR MAPPING CAN GO HERE
        String path = exchange.getIn().getHeader(Exchange.HTTP_URI, String.class);
        String endpoint = BASE_FABRIC_NAME + path.replaceFirst("/", "");
        log.info("Endpoint that the recipient list will use: {}", endpoint);
        return endpoint;
    }


	public String getEndpointFileLocation() {
		return endpointFileLocation;
	}


	public void setEndpointFileLocation(String endpointLocation) {
		this.endpointFileLocation = endpointLocation;
	}
    
    
}
