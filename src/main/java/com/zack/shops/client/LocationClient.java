package com.zack.shops.client;

import com.zack.shops.client.gen.FindShopLocation;
import com.zack.shops.client.gen.FindShopLocationResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;;

public class LocationClient extends WebServiceGatewaySupport {

    public FindShopLocationResponse getLocation(int idShop) {
        FindShopLocation request = new FindShopLocation();
        request.setId(idShop);;

        FindShopLocationResponse response = (FindShopLocationResponse) getWebServiceTemplate()
          .marshalSendAndReceive(request);
        return response;
    }
}