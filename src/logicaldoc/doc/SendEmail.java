/**
 * SendEmail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package logicaldoc.doc;

public class SendEmail  implements java.io.Serializable {
    private java.lang.String sid;

    private long[] docIds;

    private java.lang.String recipients;

    private java.lang.String arg3;

    private java.lang.String arg4;

    public SendEmail() {
    }

    public SendEmail(
           java.lang.String sid,
           long[] docIds,
           java.lang.String recipients,
           java.lang.String arg3,
           java.lang.String arg4) {
           this.sid = sid;
           this.docIds = docIds;
           this.recipients = recipients;
           this.arg3 = arg3;
           this.arg4 = arg4;
    }


    /**
     * Gets the sid value for this SendEmail.
     * 
     * @return sid
     */
    public java.lang.String getSid() {
        return sid;
    }


    /**
     * Sets the sid value for this SendEmail.
     * 
     * @param sid
     */
    public void setSid(java.lang.String sid) {
        this.sid = sid;
    }


    /**
     * Gets the docIds value for this SendEmail.
     * 
     * @return docIds
     */
    public long[] getDocIds() {
        return docIds;
    }


    /**
     * Sets the docIds value for this SendEmail.
     * 
     * @param docIds
     */
    public void setDocIds(long[] docIds) {
        this.docIds = docIds;
    }

    public long getDocIds(int i) {
        return this.docIds[i];
    }

    public void setDocIds(int i, long _value) {
        this.docIds[i] = _value;
    }


    /**
     * Gets the recipients value for this SendEmail.
     * 
     * @return recipients
     */
    public java.lang.String getRecipients() {
        return recipients;
    }


    /**
     * Sets the recipients value for this SendEmail.
     * 
     * @param recipients
     */
    public void setRecipients(java.lang.String recipients) {
        this.recipients = recipients;
    }


    /**
     * Gets the arg3 value for this SendEmail.
     * 
     * @return arg3
     */
    public java.lang.String getArg3() {
        return arg3;
    }


    /**
     * Sets the arg3 value for this SendEmail.
     * 
     * @param arg3
     */
    public void setArg3(java.lang.String arg3) {
        this.arg3 = arg3;
    }


    /**
     * Gets the arg4 value for this SendEmail.
     * 
     * @return arg4
     */
    public java.lang.String getArg4() {
        return arg4;
    }


    /**
     * Sets the arg4 value for this SendEmail.
     * 
     * @param arg4
     */
    public void setArg4(java.lang.String arg4) {
        this.arg4 = arg4;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SendEmail)) return false;
        SendEmail other = (SendEmail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sid==null && other.getSid()==null) || 
             (this.sid!=null &&
              this.sid.equals(other.getSid()))) &&
            ((this.docIds==null && other.getDocIds()==null) || 
             (this.docIds!=null &&
              java.util.Arrays.equals(this.docIds, other.getDocIds()))) &&
            ((this.recipients==null && other.getRecipients()==null) || 
             (this.recipients!=null &&
              this.recipients.equals(other.getRecipients()))) &&
            ((this.arg3==null && other.getArg3()==null) || 
             (this.arg3!=null &&
              this.arg3.equals(other.getArg3()))) &&
            ((this.arg4==null && other.getArg4()==null) || 
             (this.arg4!=null &&
              this.arg4.equals(other.getArg4())));
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
        if (getSid() != null) {
            _hashCode += getSid().hashCode();
        }
        if (getDocIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRecipients() != null) {
            _hashCode += getRecipients().hashCode();
        }
        if (getArg3() != null) {
            _hashCode += getArg3().hashCode();
        }
        if (getArg4() != null) {
            _hashCode += getArg4().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendEmail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://document.webservice.logicaldoc.com/", "sendEmail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docIds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "docIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipients");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recipients"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arg3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arg3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arg4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arg4"));
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
