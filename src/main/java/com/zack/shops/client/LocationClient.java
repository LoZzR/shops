package com.zack.shops.client;

import com.zack.shops.client.gen.FindShopLocation;
import com.zack.shops.client.gen.FindShopLocationResponse;
import com.zack.shops.client.gen.ObjectFactory;

import javax.xml.bind.JAXBElement;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class LocationClient extends WebServiceGatewaySupport {

    public FindShopLocationResponse getLocation(int idShop) {
        FindShopLocation request = new FindShopLocation();
        request.setId(idShop);

        ObjectFactory of = new ObjectFactory();
        JAXBElement<FindShopLocation> reqjaxb = of.createFindShopLocation(request);
        
        @SuppressWarnings("unchecked")
		JAXBElement<FindShopLocationResponse> response = (JAXBElement<FindShopLocationResponse>) getWebServiceTemplate()
          .marshalSendAndReceive(reqjaxb);
        return response.getValue();
    }
}