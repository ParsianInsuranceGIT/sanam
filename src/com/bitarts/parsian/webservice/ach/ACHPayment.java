package com.bitarts.parsian.webservice.ach;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.action.CredebitAction;
import com.bitarts.parsian.action.LoginAction;
import org.slf4j.LoggerFactory;

public class ACHPayment {
    private static final String WEBSERVICE_USERNAME = "lifeach";
    private static final String WEBSERVICE_PASSWORD = "LifeACh";

    public static String achPaymentRequest(String bedehiIdentifier, int amount, String reason, String receiver, String accountNo,
                                           String shomareShenasname, String kodeMelli, String username, String tel, String kodeVahedSodor) {
        final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginAction.class);
        try {
            ServiceLocator locator = new ServiceLocator();
            ServiceSoap_PortType service = locator.getServiceSoap();
            String signer1 = "1038", signer2 = "1030";
            if(amount < 200000000) {
                signer1 = "1028";
                signer2 = "1038";
            }
            return service.payRequest(WEBSERVICE_USERNAME, WEBSERVICE_PASSWORD, DateUtil.getCurrentDate(),
                    bedehiIdentifier, amount, reason, receiver, accountNo, "", "", shomareShenasname, kodeMelli,
                "", 1, "", tel, kodeVahedSodor, username, 13, signer1, signer2, tel, bedehiIdentifier);
        } catch(Exception ex) {
            logger.info(ex.toString());
            return "-9";
        }
    }

    public static String achPaymentRequestSanam(String bedehiIdentifier, int amount, String reason, String receiver, String accountNo,
                                           String shomareShenasname, String kodeMelli, String username, String tel, String kodeVahedSodor) {
        final org.slf4j.Logger logger = LoggerFactory.getLogger(CredebitAction.class);
        try {
            ServiceLocator locator = new ServiceLocator();
            ServiceSoap_PortType service = locator.getServiceSoap();
            String signer1 = "2453", signer2 = "2646";
            String result = service.payRequest("sanam", "$5S@n@m2$", DateUtil.getCurrentDate(),
                    bedehiIdentifier, amount, reason, receiver, accountNo, "", "", shomareShenasname, kodeMelli,
                    "", 1, "", tel, kodeVahedSodor, username, 16, signer1, signer2, tel, bedehiIdentifier.replaceAll("/",""));
            return result;
        } catch(Exception ex) {
            logger.info(ex.toString());
            return "-9";
        }
    }

    public static void main(String[] args){
        try {
            String response = achPaymentRequest("123456789012", 1000, "123456",
                    "test", "620150000001899300189909", "123", "1234567891", "test", "09123456789", "123");
            System.out.println(response);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
