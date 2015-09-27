/**
 * Restore.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package logicaldoc.doc;

public class Restore  implements java.io.Serializable {
    private java.lang.String sid;

    private long docId;

    private long folderId;

    public Restore() {
    }

    public Restore(
           java.lang.String sid,
           long docId,
           long folderId) {
           this.sid = sid;
           this.docId = docId;
           this.folderId = folderId;
    }


    /**
     * Gets the sid value for this Restore.
     * 
     * @return sid
     */
    public java.lang.String getSid() {
        return sid;
    }


    /**
     * Sets the sid value for this Restore.
     * 
     * @param sid
     */
    public void setSid(java.lang.String sid) {
        this.sid = sid;
    }


    /**
     * Gets the docId value for this Restore.
     * 
     * @return docId
     */
    public long getDocId() {
        return docId;
    }


    /**
     * Sets the docId value for this Restore.
     * 
     * @param docId
     */
    public void setDocId(long docId) {
        this.docId = docId;
    }


    /**
     * Gets the folderId value for this Restore.
     * 
     * @return folderId
     */
    public long getFolderId() {
        return folderId;
    }


    /**
     * Sets the folderId value for this Restore.
     * 
     * @param folderId
     */
    public void setFolderId(long folderId) {
        this.folderId = folderId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Restore)) return false;
        Restore other = (Restore) obj;
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
            this.docId == other.getDocId() &&
            this.folderId == other.getFolderId();
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
        _hashCode += new Long(getDocId()).hashCode();
        _hashCode += new Long(getFolderId()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Restore.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://document.webservice.logicaldoc.com/", "restore"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "docId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "folderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
