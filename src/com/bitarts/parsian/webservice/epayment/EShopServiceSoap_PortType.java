/**
 * EShopServiceSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bitarts.parsian.webservice.epayment;

public interface EShopServiceSoap_PortType extends java.rmi.Remote {
    public void pinSettlement(java.lang.String pin, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinPaymentRequest(java.lang.String pin, int amount, int orderId, java.lang.String callbackUrl, javax.xml.rpc.holders.LongHolder authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinPaymentRequestWithExtra(java.lang.String pin, java.math.BigDecimal amount, long orderId, java.lang.String callbackUrl, java.lang.String additionalData, javax.xml.rpc.holders.LongHolder authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinBillPaymentRequest(java.lang.String pin, int amount, int orderId, java.lang.String callbackUrl, java.lang.String billType, java.lang.String billIdentity, javax.xml.rpc.holders.LongHolder authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinPersianInsurancePaymentRequest(java.lang.String pin, int amount, int orderId, java.lang.String callbackUrl, java.lang.String customerId, javax.xml.rpc.holders.LongHolder authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinIsacoPaymentRequest(java.lang.String pin, int amount, int orderId, java.lang.String callbackUrl, java.lang.String deligateCode, java.lang.String deligatePass, int paymentType, javax.xml.rpc.holders.LongHolder authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinPaymentEnquiry(java.lang.String pin, long authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void paymentEnquiry(java.lang.String pin, long authority, org.apache.axis.holders.UnsignedByteHolder status, javax.xml.rpc.holders.LongHolder invoiceNumber) throws java.rmi.RemoteException;
    public void paymentEnquiryWithTransData(java.lang.String pin, long authority, org.apache.axis.holders.UnsignedByteHolder status, javax.xml.rpc.holders.LongHolder invoiceNumber, javax.xml.rpc.holders.StringHolder truncatedCardNo, javax.xml.rpc.holders.StringHolder encCardNo) throws java.rmi.RemoteException;
    public void paymentEnquiryWithAmount(java.lang.String pin, long authority, org.apache.axis.holders.UnsignedByteHolder status, javax.xml.rpc.holders.LongHolder invoiceNumber, javax.xml.rpc.holders.BigDecimalHolder amount) throws java.rmi.RemoteException;
    public void pinVoidPayment(java.lang.String pin, int orderId, int orderToVoid, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinReversal(java.lang.String pin, int orderId, int orderToReversal, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinSetDefaultCallbackUrl(java.lang.String pin, java.lang.String url, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinRefundPayment(java.lang.String pin, int orderId, int orderToRefund, int amount, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinRefund(java.lang.String pin, int orderId, int orderToRefund, int amount, java.lang.String destSheba, javax.xml.rpc.holders.LongHolder authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinBatchPaymentRequest(java.lang.String pin, java.lang.String callbackUrl, javax.xml.rpc.holders.LongHolder batchAuthority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinBatchBillPaymentRequest(java.lang.String pin, int amount, int orderId, java.lang.String billType, java.lang.String billIdentity, long batchAuthority, javax.xml.rpc.holders.LongHolder authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinBatchItemPaymentRequest(java.lang.String pin, int amount, int orderId, long batchAuthority, javax.xml.rpc.holders.LongHolder authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinBatchItemPaymentRequestWithData(java.lang.String pin, int amount, int orderId, long batchAuthority, java.lang.String additionalData, javax.xml.rpc.holders.LongHolder authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinBillPaymentRequestTC(java.lang.String pin, int amount, int orderId, java.lang.String callbackUrl, java.lang.String billIdentity, javax.xml.rpc.holders.LongHolder authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinBillPaymentRequestEL(java.lang.String pin, int amount, int orderId, java.lang.String callbackUrl, java.lang.String billIdentity, javax.xml.rpc.holders.LongHolder authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinBillPaymentRequestGA(java.lang.String pin, int amount, int orderId, java.lang.String callbackUrl, java.lang.String billIdentity, javax.xml.rpc.holders.LongHolder authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinBillPaymentRequestMC(java.lang.String pin, int amount, int orderId, java.lang.String callbackUrl, java.lang.String billIdentity, javax.xml.rpc.holders.LongHolder authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinBillPaymentRequestMN(java.lang.String pin, int amount, int orderId, java.lang.String callbackUrl, java.lang.String billIdentity, javax.xml.rpc.holders.LongHolder authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
    public void pinBillPaymentRequestWA(java.lang.String pin, int amount, int orderId, java.lang.String callbackUrl, java.lang.String billIdentity, javax.xml.rpc.holders.LongHolder authority, org.apache.axis.holders.UnsignedByteHolder status) throws java.rmi.RemoteException;
}
