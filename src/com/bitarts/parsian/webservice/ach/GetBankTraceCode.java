/**
 * GetBankTraceCode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bitarts.parsian.webservice.ach;

public class GetBankTraceCode  implements java.io.Serializable {
    private java.lang.String userName;

    private java.lang.String passwrd;

    private java.lang.String traceCode;

    public GetBankTraceCode() {
    }

    public GetBankTraceCode(
           java.lang.String userName,
           java.lang.String passwrd,
           java.lang.String traceCode) {
           this.userName = userName;
           this.passwrd = passwrd;
           this.traceCode = traceCode;
    }


    /**
     * Gets the userName value for this GetBankTraceCode.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this GetBankTraceCode.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the passwrd value for this GetBankTraceCode.
     * 
     * @return passwrd
     */
    public java.lang.String getPasswrd() {
        return passwrd;
    }


    /**
     * Sets the passwrd value for this GetBankTraceCode.
     * 
     * @param passwrd
     */
    public void setPasswrd(java.lang.String passwrd) {
        this.passwrd = passwrd;
    }


    /**
     * Gets the traceCode value for this GetBankTraceCode.
     * 
     * @return traceCode
     */
    public java.lang.String getTraceCode() {
        return traceCode;
    }


    /**
     * Sets the traceCode value for this GetBankTraceCode.
     * 
     * @param traceCode
     */
    public void setTraceCode(java.lang.String traceCode) {
        this.traceCode = traceCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetBankTraceCode)) return false;
        GetBankTraceCode other = (GetBankTraceCode) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName()))) &&
            ((this.passwrd==null && other.getPasswrd()==null) || 
             (this.passwrd!=null &&
              this.passwrd.equals(other.getPasswrd()))) &&
            ((this.traceCode==null && other.getTraceCode()==null) || 
             (this.traceCode!=null &&
              this.traceCode.equals(other.getTraceCode())));
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
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getPasswrd() != null) {
            _hashCode += getPasswrd().hashCode();
        }
        if (getTraceCode() != null) {
            _hashCode += getTraceCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetBankTraceCode.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.ACH.parsianInsurance.ir/", ">GetBankTraceCode"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.ACH.parsianInsurance.ir/", "UserName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passwrd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.ACH.parsianInsurance.ir/", "Passwrd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("traceCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.ACH.parsianInsurance.ir/", "TraceCode"));
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
