package com.zack.shops.config;

import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.zack.shops.client.LocationClient;

public class LocationClientConfig {

	@Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.zack.shops.client.gen");
        return marshaller;
    }
    @Bean
    public LocationClient countryClient(Jaxb2Marshaller marshaller) {
    	LocationClient client = new LocationClient();
        client.setDefaultUri("http://localhost:8080/LocationEJB/ShopService");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
