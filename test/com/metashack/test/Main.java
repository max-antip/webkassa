package com.metashack.test;


import com.metashack.Webkassa;
import com.metashack.messages.Check;
import com.metashack.messages.Response;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Main {

    private static final String HOST = "http://devkkm.webkassa.kz/api/";
    private static final String LOGIN = "";
    private static final String PASS = "";
    private static final int OK = 200;
    private static final String UTF_8 = "UTF-8";
    private Webkassa wk;

    private String tempToken;

    @Before
    public void initWebKassa() {
        wk = new Webkassa(HOST);
    }


    @Test
    public void logonTest() {

        try {
            HttpResponse httpResp = wk.logon(LOGIN, PASS);
            assertEquals(httpResp.getStatusLine().getStatusCode(), OK);
            String result = EntityUtils.toString(httpResp.getEntity(), UTF_8);
            Response response = wk.getResponse(result);
            assertNotNull(response.Data.Token);
            tempToken = response.Data.Token;
            System.out.println("Logon response: ");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void sendCheckTest() {

        Check check = new Check();

        check.cashBoxUniqueNumber = "1A";
        check.change = 100D;
        check.operationType = 2L;
        check.customerEmail = "max@mail.ru";
        check.externalCheckNumber = "1";
        check.roundType = 2;
        check.token = tempToken;

        Check.Position pos = new Check.Position();
        pos.count = 1D;
        pos.price = 319D;
        pos.discount = 0D;
        pos.discountDeleted = false;
        pos.isStorno = false;
        pos.markup = 0D;
        pos.positionCode = "1";
        pos.positionName = "Сметана";
        pos.tax = 13D;
        pos.taxType = 1L;
        pos.sectionCode = "1221";

        Check.Payment payment = new Check.Payment();
        payment.sum = 638D;
        payment.paymentType = 1L;

        check.addPayment(payment);
        check.addPosition(pos);

        try {
            HttpResponse httpResp = wk.sendCheck(check);
            assertEquals(httpResp.getStatusLine().getStatusCode(), OK);
            String result = EntityUtils.toString(httpResp.getEntity(), UTF_8);
            Response response = wk.getResponse(result);
            System.out.println("Check response: ");
            System.out.println(result);
            assertEquals(response.Errors.size(), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }




/*                    assertEquals(resp.getStatusLine().getStatusCode(), OK);
            String result = EntityUtils.toString(resp.getEntity(), UTF_8);
            //todo test logon response
            System.out.println(result);*/


    }


}
