
package com.bitarts.parsian.service.implementation.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "estelamCalc", namespace = "http://implementation.service.parsian.bitarts.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "estelamCalc", namespace = "http://implementation.service.parsian.bitarts.com/", propOrder = {
    "arg0",
    "arg1",
    "arg2",
    "arg3",
    "arg4",
    "arg5",
    "arg6",
    "arg7"
})
public class EstelamCalc {

    @XmlElement(name = "arg0", namespace = "")
    private String arg0;
    @XmlElement(name = "arg1", namespace = "")
    private String arg1;
    @XmlElement(name = "arg2", namespace = "")
    private long arg2;
    @XmlElement(name = "arg3", namespace = "")
    private long arg3;
    @XmlElement(name = "arg4", namespace = "")
    private int arg4;
    @XmlElement(name = "arg5", namespace = "")
    private long arg5;
    @XmlElement(name = "arg6", namespace = "")
    private int arg6;
    @XmlElement(name = "arg7", namespace = "")
    private int arg7;

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    /**
     * 
     * @return
     *     returns long
     */
    public long getArg2() {
        return this.arg2;
    }

    /**
     * 
     * @param arg2
     *     the value for the arg2 property
     */
    public void setArg2(long arg2) {
        this.arg2 = arg2;
    }

    /**
     * 
     * @return
     *     returns long
     */
    public long getArg3() {
        return this.arg3;
    }

    /**
     * 
     * @param arg3
     *     the value for the arg3 property
     */
    public void setArg3(long arg3) {
        this.arg3 = arg3;
    }

    /**
     * 
     * @return
     *     returns int
     */
    public int getArg4() {
        return this.arg4;
    }

    /**
     * 
     * @param arg4
     *     the value for the arg4 property
     */
    public void setArg4(int arg4) {
        this.arg4 = arg4;
    }

    /**
     * 
     * @return
     *     returns long
     */
    public long getArg5() {
        return this.arg5;
    }

    /**
     * 
     * @param arg5
     *     the value for the arg5 property
     */
    public void setArg5(long arg5) {
        this.arg5 = arg5;
    }

    /**
     * 
     * @return
     *     returns int
     */
    public int getArg6() {
        return this.arg6;
    }

    /**
     * 
     * @param arg6
     *     the value for the arg6 property
     */
    public void setArg6(int arg6) {
        this.arg6 = arg6;
    }

    /**
     * 
     * @return
     *     returns int
     */
    public int getArg7() {
        return this.arg7;
    }

    /**
     * 
     * @param arg7
     *     the value for the arg7 property
     */
    public void setArg7(int arg7) {
        this.arg7 = arg7;
    }

}
