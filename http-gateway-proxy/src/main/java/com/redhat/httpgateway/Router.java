package com.redhat.httpgateway;

import org.apache.camel.Exchange;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.GetDataBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ceposta on 5/15/14.
 */
public class Router {


    private static final String BASE_FABRIC_NAME = "fabric:gateway";
    private static final Logger LOG = LoggerFactory.getLogger(Router.class);
    
    private CuratorFramework client; 

    public String routePlease(Exchange exchange) {

        // delegate to method that will do name/mapping resolution
        return resolveEndpointName(exchange);
    }

    private String resolveEndpointName(Exchange exchange) {
        // REAL CODE FOR MAPPING CAN GO HERE
        String path = exchange.getIn().getHeader(Exchange.HTTP_URI, String.class);
        String endpoint = BASE_FABRIC_NAME + path.replaceFirst("/", "");
        LOG.info("Endpoint that the recipient list will use: {}", endpoint);
        return endpoint;
    }
    
    
}
