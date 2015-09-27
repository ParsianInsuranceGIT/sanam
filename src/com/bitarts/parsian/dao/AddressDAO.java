package com.bitarts.parsian.dao;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.model.pishnahad.Address;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 2/24/11
 * Time: 7:06 PM
 */
public class AddressDAO extends BaseDAO {
    public static String ID = "id";
    public static String telephoneHamrah = "telephoneHamrah";
    public static String neshaniManzel = "neshaniManzel";
    public static String kodePostiManzel = "kodePostiManzel";
    public static String telephoneManzel = "telephoneManzel";
    public static String neshaniMahalleKar = "neshaniMahalleKar";
    public static String kodePostiMahalleKar = "kodePostiMahalleKar";
    public static String telephoneMahalleKar = "telephoneMahalleKar";
    public static String shakhs = "shakhs";

    public void save(Address address, boolean bimeGozarHoghughi) {
        if (address != null) {
            if (bimeGozarHoghughi) {
                address.setOstaanManzel(null);
                address.setShahrManzel(null);
            }
            super.save(address);
        }
    }

    public void update(Address address, boolean bimeGozarHoghughi) {
        if (bimeGozarHoghughi) {
            address.setOstaanManzel(null);
            address.setShahrManzel(null);
        }
        super.update(address);
    }

    public Address findById(Integer id) {
        return (Address) getSession().get(Address.class, id);
    }
}
