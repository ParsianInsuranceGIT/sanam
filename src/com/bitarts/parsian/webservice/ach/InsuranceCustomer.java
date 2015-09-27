/**
 * InsuranceCustomer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bitarts.parsian.webservice.ach;

public class InsuranceCustomer  implements java.io.Serializable {
    private java.lang.String userName;

    private java.lang.String passwrd;

    private java.lang.String customerISSN;

    private java.lang.String persianDate;

    private java.lang.String type;

    public InsuranceCustomer() {
    }

    public InsuranceCustomer(
           java.lang.String userName,
           java.lang.String passwrd,
           java.lang.String customerISSN,
           java.lang.String persianDate,
           java.lang.String type) {
           this.userName = userName;
           this.passwrd = passwrd;
           this.customerISSN = customerISSN;
           this.persianDate = persianDate;
           this.type = type;
    }


    /**
     * Gets the userName value for this InsuranceCustomer.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this InsuranceCustomer.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the passwrd value for this InsuranceCustomer.
     * 
     * @return passwrd
     */
    public java.lang.String getPasswrd() {
        return passwrd;
    }


    /**
     * Sets the passwrd value for this InsuranceCustomer.
     * 
     * @param passwrd
     */
    public void setPasswrd(java.lang.String passwrd) {
        this.passwrd = passwrd;
    }


    /**
     * Gets the customerISSN value for this InsuranceCustomer.
     * 
     * @return customerISSN
     */
    public java.lang.String getCustomerISSN() {
        return customerISSN;
    }


    /**
     * Sets the customerISSN value for this InsuranceCustomer.
     * 
     * @param customerISSN
     */
    public void setCustomerISSN(java.lang.String customerISSN) {
        this.customerISSN = customerISSN;
    }


    /**
     * Gets the persianDate value for this InsuranceCustomer.
     * 
     * @return persianDate
     */
    public java.lang.String getPersianDate() {
        return persianDate;
    }


    /**
     * Sets the persianDate value for this InsuranceCustomer.
     * 
     * @param persianDate
     */
    public void setPersianDate(java.lang.String persianDate) {
        this.persianDate = persianDate;
    }


    /**
     * Gets the type value for this InsuranceCustomer.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this InsuranceCustomer.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InsuranceCustomer)) return false;
        InsuranceCustomer other = (InsuranceCustomer) obj;
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
            ((this.customerISSN==null && other.getCustomerISSN()==null) || 
             (this.customerISSN!=null &&
              this.customerISSN.equals(other.getCustomerISSN()))) &&
            ((this.persianDate==null && other.getPersianDate()==null) || 
             (this.persianDate!=null &&
              this.persianDate.equals(other.getPersianDate()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType())));
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
        if (getCustomerISSN() != null) {
            _hashCode += getCustomerISSN().hashCode();
        }
        if (getPersianDate() != null) {
            _hashCode += getPersianDate().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InsuranceCustomer.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.ACH.parsianInsurance.ir/", ">InsuranceCustomer"));
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
        elemField.setFieldName("customerISSN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.ACH.parsianInsurance.ir/", "CustomerISSN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("persianDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.ACH.parsianInsurance.ir/", "PersianDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.ACH.parsianInsurance.ir/", "Type"));
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
