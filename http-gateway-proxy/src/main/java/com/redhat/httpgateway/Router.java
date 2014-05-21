package com.redhat.httpgateway;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ceposta on 5/15/14.
 */
public class Router {


    private static final String BASE_FABRIC_NAME = "fabric:gateway";
    private static final String BASE_CXF_CLUSTER = "fabric:gateway";
    private static final Logger LOG = LoggerFactory.getLogger(Router.class);
    
    private Map<String, String> someMap = new HashMap<String,String>(); 
    private File someFile = null; 
    private String endpointLocation; 
    
    public void init(){
    	//load map from some properties file
    	
    }
    

    public String routePlease(Exchange exchange) {

        // delegate to method that will do name/mapping resolution
    	return someMap.get(exchange.getIn().getHeader(Exchange.HTTP_URI)); 
       // return resolveEndpointName(exchange);
    }
    
    public String routeGateway(Exchange exchange){
    	String endpoint = BASE_CXF_CLUSTER;
    	LOG.info("Endpoint that the recipient list will use: {}", endpoint);
    	return endpoint;
    }

    private String resolveEndpointName(Exchange exchange) {
        // REAL CODE FOR MAPPING CAN GO HERE
        String path = exchange.getIn().getHeader(Exchange.HTTP_URI, String.class);
        String endpoint = BASE_FABRIC_NAME + path.replaceFirst("/", "");
        LOG.info("Endpoint that the recipient list will use: {}", endpoint);
        return endpoint;
    }
    
    
}
