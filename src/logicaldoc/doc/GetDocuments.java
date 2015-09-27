/**
 * GetDocuments.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package logicaldoc.doc;

public class GetDocuments  implements java.io.Serializable {
    private java.lang.String sid;

    private long[] docIds;

    public GetDocuments() {
    }

    public GetDocuments(
           java.lang.String sid,
           long[] docIds) {
           this.sid = sid;
           this.docIds = docIds;
    }


    /**
     * Gets the sid value for this GetDocuments.
     * 
     * @return sid
     */
    public java.lang.String getSid() {
        return sid;
    }


    /**
     * Sets the sid value for this GetDocuments.
     * 
     * @param sid
     */
    public void setSid(java.lang.String sid) {
        this.sid = sid;
    }


    /**
     * Gets the docIds value for this GetDocuments.
     * 
     * @return docIds
     */
    public long[] getDocIds() {
        return docIds;
    }


    /**
     * Sets the docIds value for this GetDocuments.
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

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetDocuments)) return false;
        GetDocuments other = (GetDocuments) obj;
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
              java.util.Arrays.equals(this.docIds, other.getDocIds())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDocuments.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://document.webservice.logicaldoc.com/", "getDocuments"));
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
