
package com.bitarts.parsian.service.implementation.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getPishnehadServiceResponse", namespace = "http://implementation.service.parsian.bitarts.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPishnehadServiceResponse", namespace = "http://implementation.service.parsian.bitarts.com/")
public class GetPishnehadServiceResponse {

    @XmlElement(name = "return", namespace = "")
    private com.bitarts.parsian.service.IPishnehadService _return;

    /**
     * 
     * @return
     *     returns IPishnehadService
     */
    public com.bitarts.parsian.service.IPishnehadService getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.bitarts.parsian.service.IPishnehadService _return) {
        this._return = _return;
    }

}
