package com.bitarts.parsian.service.ScheduleService;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.service.factory.InsuranceServiceFactory;
import com.bitarts.parsian.webservice.sms.SMSSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * User: a.sabzechian
 * Date: 12/16/13
 * Time: 1:29 PM
 */

public class SMSServiceTask {
    public void SendSmsForGhest() {
        final Logger logger = LoggerFactory.getLogger(SMSServiceTask.class);
        String header = String.format("sendSmsForGhest started at : " + DateUtil.getCurrentTime());
        logger.info(String.format(header));
        Map<Integer, List<String>> informationsForSMS = InsuranceServiceFactory.getWithoutSessionAsnadeSodorService().findInformationGhest10DayBeforeForSMS();

        for (List<String> informationForSMS : informationsForSMS.values()){
            try{
                String firstName = informationForSMS.get(0);
                String lastName = informationForSMS.get(1);
                String cellPhoneNo = informationForSMS.get(2);
                String description = "صدور بيمه نامه";
                String issueDate = DateUtil.addDays(DateUtil.getCurrentDate(),10);
                String text= "بيمه گذار گرامي، پرداخت حق بيمه عمروسرمايه گذاري شما در تاريخ" +
                        issueDate +
                        " يادآوري ميگردد. با پرداخت به موقع حق بيمه، از مزاياي سود روزشمار بهره مند شويد. همچنين جهت پرداخت اينترنتي اقساط، مي توانيد از سامانه خدمات الکترونيک به آدرس زير استفاده فرمائيد http://lifecrm.parsianinsurance.com *بيمه پارسيان*";
                String policyId = informationForSMS.get(3);
                boolean b = SMSSender.sendSMS(firstName, lastName, cellPhoneNo, text, description, policyId, 8);
                header = String.format("SMSSender.sendSMS returned "+ b +" for " + firstName + lastName + policyId +" at "+ DateUtil.getCurrentTime());
                logger.info(String.format(header));
            } catch(Exception e){
                header = String.format("Exception SendSmsForGhest "+ informationForSMS.get(3));
                logger.info(String.format(header));
                logger.error(e.toString());
                e.printStackTrace();
            }
        }
        header = String.format("sendSmsForGhest ended at : " + DateUtil.getCurrentTime());
        logger.info(String.format(header));
    }

    public void findInformationGhest20DayAfter() {
        Map<Integer, List<String>> informationsForSMS = InsuranceServiceFactory.getWithoutSessionAsnadeSodorService().findInformationGhest20DayAfterForSMS();
        final Logger logger = LoggerFactory.getLogger(SMSServiceTask.class);
        String header = String.format("findInformationGhest20DayAfter started at : " + DateUtil.getCurrentTime());
        logger.info(String.format(header));
        for (List<String> informationForSMS : informationsForSMS.values()){
            try{
                String firstName = informationForSMS.get(0);
                String lastName = informationForSMS.get(1);
                String cellPhoneNo = informationForSMS.get(2);
                String description = "صدور بيمه نامه";
                String issueDate = DateUtil.minusDays(DateUtil.getCurrentDate(), 20);
                String text= "بيمه گذار گرامي، پرداخت حق بيمه عمروسرمايه گذاري شما در تاريخ" +
                        issueDate +
                        " يادآوري ميگردد. با پرداخت به موقع حق بيمه، از مزاياي سود روزشمار بهره مند شويد. همچنين جهت پرداخت اينترنتي اقساط، مي توانيد از سامانه خدمات الکترونيک به آدرس زير استفاده فرمائيد http://lifecrm.parsianinsurance.com *بيمه پارسيان*";
                String policyId = informationForSMS.get(3);
                boolean b = SMSSender.sendSMS(firstName, lastName, cellPhoneNo, text, description, policyId, 8);
                header = String.format("SMSSender.sendSMS returned " + b + " for " + firstName + lastName + policyId + " at " + DateUtil.getCurrentTime());
                logger.info(String.format(header));
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        header = String.format("findInformationGhest20DayAfter ended at : " + DateUtil.getCurrentTime());
        logger.info(String.format(header));
    }
}