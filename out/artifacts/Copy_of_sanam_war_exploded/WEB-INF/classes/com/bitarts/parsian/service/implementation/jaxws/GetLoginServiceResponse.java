
package com.bitarts.parsian.service.implementation.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getLoginServiceResponse", namespace = "http://implementation.service.parsian.bitarts.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLoginServiceResponse", namespace = "http://implementation.service.parsian.bitarts.com/")
public class GetLoginServiceResponse {

    @XmlElement(name = "return", namespace = "")
    private com.bitarts.parsian.service.ILoginService _return;

    /**
     * 
     * @return
     *     returns ILoginService
     */
    public com.bitarts.parsian.service.ILoginService getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.bitarts.parsian.service.ILoginService _return) {
        this._return = _return;
    }

}
