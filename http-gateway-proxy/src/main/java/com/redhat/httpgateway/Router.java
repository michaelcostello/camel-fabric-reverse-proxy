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

import com.redhat.httpgateway.exception.BindingException;

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
    
    

    public String routePlease(Exchange exchange) throws Exception{
    	  return resolveEndpointName(exchange);
    	  
    }
    
    private String resolveEndpointName(Exchange exchange) throws Exception{
        // REAL CODE FOR MAPPING CAN GO HERE 
    	log.debug("entered route please");
    	log.debug("path presented " + exchange.getIn().getHeader(Exchange.HTTP_URI));
        String result = null;
    	String port = endpointProperties.getProperty((String)exchange.getIn().getHeader(Exchange.HTTP_URI));
    	if (port != null){
    		if (port.length() > 0){
    			result= BASE_HTTP_URI_FRAGMENT + port + exchange.getIn().getHeader(Exchange.HTTP_URI) +"?bridgeEndpoint=true";
    			//result= BASE_HTTP_URI_FRAGMENT + port; 
    		}
    	}else{
    		throw new BindingException("Unable to lookup Endpoint for " + exchange.getIn().getHeader(Exchange.HTTP_URI));
    	}
    	
    	log.info("the result is " + result);
    	return result;
    }


	public String getEndpointFileLocation() {
		return endpointFileLocation;
	}


	public void setEndpointFileLocation(String endpointLocation) {
		this.endpointFileLocation = endpointLocation;
	}
    
    
}
