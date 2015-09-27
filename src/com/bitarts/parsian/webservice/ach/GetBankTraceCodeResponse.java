/**
 * GetBankTraceCodeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bitarts.parsian.webservice.ach;

public class GetBankTraceCodeResponse  implements java.io.Serializable {
    private java.lang.String getBankTraceCodeResult;

    public GetBankTraceCodeResponse() {
    }

    public GetBankTraceCodeResponse(
           java.lang.String getBankTraceCodeResult) {
           this.getBankTraceCodeResult = getBankTraceCodeResult;
    }


    /**
     * Gets the getBankTraceCodeResult value for this GetBankTraceCodeResponse.
     * 
     * @return getBankTraceCodeResult
     */
    public java.lang.String getGetBankTraceCodeResult() {
        return getBankTraceCodeResult;
    }


    /**
     * Sets the getBankTraceCodeResult value for this GetBankTraceCodeResponse.
     * 
     * @param getBankTraceCodeResult
     */
    public void setGetBankTraceCodeResult(java.lang.String getBankTraceCodeResult) {
        this.getBankTraceCodeResult = getBankTraceCodeResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetBankTraceCodeResponse)) return false;
        GetBankTraceCodeResponse other = (GetBankTraceCodeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getBankTraceCodeResult==null && other.getGetBankTraceCodeResult()==null) || 
             (this.getBankTraceCodeResult!=null &&
              this.getBankTraceCodeResult.equals(other.getGetBankTraceCodeResult())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getGetBankTraceCodeResult() != null) {
            _hashCode += getGetBankTraceCodeResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetBankTraceCodeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.ACH.parsianInsurance.ir/", ">GetBankTraceCodeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getBankTraceCodeResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.ACH.parsianInsurance.ir/", "GetBankTraceCodeResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
