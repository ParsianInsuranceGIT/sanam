package com.bitarts.parsian.webservice.sms;

import com.bitarts.parsian.Core.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 * User: e-soleymani
 * Date: 7/9/13
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class SMSSender {
    private static final String SMS_WEBSERVICE_USERNAME = "lifeapp";
    private static final String SMS_WEBSERVICE_PASSWORD = "Lifeapp";
    private static final int SMS_SEND_APP_ID = 27;

    public static boolean sendSMS(String firstName, String lastName, String cellphoneNo, String text, String description, String policyId, int appName) {
        try {
            final Logger logger = LoggerFactory.getLogger(SMSSender.class);
            if(Constant.DEV_sendSMS){
                logger.info(String.format("SMS Disabled in Constant.Java ! to: %s bimename: %s", cellphoneNo, policyId));
                return false;
            }
            if(!validateText(text)){
                logger.info(String.format("Empty Text ! to: %s bimename: %s", cellphoneNo, policyId));
                throw new Exception("Empty Text Exception");
            }
            if(!validatePolicyId(policyId)){
                logger.info(String.format("Invalid PolicyId ! to: %s bimename: %s", cellphoneNo, policyId));
                throw new Exception("Invalid PolicyId: Empty");
            }
            if(!validateCellphoneNo(cellphoneNo)){
                logger.info(String.format("Invalid Cellphone ! to: %s bimename: %s", cellphoneNo, policyId));
                throw new Exception("Invalid Cellphone: "+cellphoneNo);
            }
            SMSServiceLocator locator = new SMSServiceLocator();
            SMSServiceSoap_PortType service = locator.getServiceSoap();
            String response = service.insertSMS(SMS_WEBSERVICE_USERNAME, SMS_WEBSERVICE_PASSWORD, firstName, lastName, cellphoneNo, text, description, policyId, appName);
            logger.info(String.format("SMS Sent ! to: %s bimename: %s Response: %s", cellphoneNo, policyId, response));
            return response.equals("0");
        } catch (ServiceException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return false;
    }

    private static boolean validateCellphoneNo(String cellphoneNo) {
        if(cellphoneNo == null){
            return false;
        }
        if (cellphoneNo.startsWith("09") && cellphoneNo.length() == 11) {
            for (int i = 0; i < cellphoneNo.length(); ++i) {
                char ch = Character.toUpperCase(cellphoneNo.charAt(i));
                if (!(ch >= '0' && ch <= '9')) {
                    return false;
                }
            }
            return true;
        }
        return false;

    }

    private static boolean validateText(String text){
        return text!=null && !text.isEmpty();
    }

    private static boolean validatePolicyId(String policyId){
        return policyId != null && !policyId.isEmpty();
    }

    public static void main(String[] args){
        System.out.println(sendSMS("Navid", "TehraniFar", "09121032419", "TEST", "desc", "123", 31));
    }
}
