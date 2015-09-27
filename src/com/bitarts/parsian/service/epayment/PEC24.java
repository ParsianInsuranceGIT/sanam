package com.bitarts.parsian.service.epayment;

import com.bitarts.parsian.webservice.epayment.EShopServiceLocator;
import com.bitarts.parsian.webservice.epayment.EShopServiceSoap_PortType;
import org.apache.axis.holders.UnsignedByteHolder;
import org.apache.axis.types.UnsignedByte;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.TypeMappingRegistry;
import javax.xml.rpc.handler.HandlerRegistry;
import javax.xml.rpc.holders.LongHolder;
import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 6/21/11
 * Time: 4:50 PM
 */
public class PEC24 {
  public static void main(String[] argv) throws ServiceException {
    // Please, do not remove this line from file template, here invocation of web service will be inserted
        EShopServiceLocator l =new EShopServiceLocator();

        EShopServiceSoap_PortType lsoap = l.getEShopServiceSoap12();
        String pin = "78d6LN3o6isGu5Wj448C";
        String callBackUrl = "http://77.237.83.140/PaymentTest/PaymentResult.aspx";
        LongHolder authority = new LongHolder(0);
        UnsignedByte statusVal = new UnsignedByte(100);
        UnsignedByteHolder status = new UnsignedByteHolder(statusVal);

        try {
          System.out.println("Status before payment: "+status.value);
          lsoap.pinPaymentRequest(pin,1,896,callBackUrl,authority,status);
//            lsoap.pinPaymentEnquiry(pin,0,status);
          System.out.println("Status after payment: "+status.value);
        } catch (RemoteException e) {
          e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
  }
}