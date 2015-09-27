/**
 * InsuranceCustomerResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bitarts.parsian.webservice.ach;

public class InsuranceCustomerResponse  implements java.io.Serializable {
    private java.lang.String insuranceCustomerResult;

    public InsuranceCustomerResponse() {
    }

    public InsuranceCustomerResponse(
           java.lang.String insuranceCustomerResult) {
           this.insuranceCustomerResult = insuranceCustomerResult;
    }


    /**
     * Gets the insuranceCustomerResult value for this InsuranceCustomerResponse.
     * 
     * @return insuranceCustomerResult
     */
    public java.lang.String getInsuranceCustomerResult() {
        return insuranceCustomerResult;
    }


    /**
     * Sets the insuranceCustomerResult value for this InsuranceCustomerResponse.
     * 
     * @param insuranceCustomerResult
     */
    public void setInsuranceCustomerResult(java.lang.String insuranceCustomerResult) {
        this.insuranceCustomerResult = insuranceCustomerResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InsuranceCustomerResponse)) return false;
        InsuranceCustomerResponse other = (InsuranceCustomerResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.insuranceCustomerResult==null && other.getInsuranceCustomerResult()==null) || 
             (this.insuranceCustomerResult!=null &&
              this.insuranceCustomerResult.equals(other.getInsuranceCustomerResult())));
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
        if (getInsuranceCustomerResult() != null) {
            _hashCode += getInsuranceCustomerResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InsuranceCustomerResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.ACH.parsianInsurance.ir/", ">InsuranceCustomerResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insuranceCustomerResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.ACH.parsianInsurance.ir/", "InsuranceCustomerResult"));
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
