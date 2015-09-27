
package com.bitarts.parsian.service.implementation.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "setAsnadeSodorService", namespace = "http://implementation.service.parsian.bitarts.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setAsnadeSodorService", namespace = "http://implementation.service.parsian.bitarts.com/")
public class SetAsnadeSodorService {

    @XmlElement(name = "arg0", namespace = "")
    private com.bitarts.parsian.service.IAsnadeSodorService arg0;

    /**
     * 
     * @return
     *     returns IAsnadeSodorService
     */
    public com.bitarts.parsian.service.IAsnadeSodorService getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(com.bitarts.parsian.service.IAsnadeSodorService arg0) {
        this.arg0 = arg0;
    }

}
