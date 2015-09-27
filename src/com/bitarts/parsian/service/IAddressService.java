package com.bitarts.parsian.service;

import com.bitarts.parsian.model.pishnahad.Address;

/**
 * Created by IntelliJ IDEA.
 * User: Arron3
 * Date: 3/9/11
 * Time: 8:34 PM
 */
public interface IAddressService extends IBaseService {
    public static final String SERVICE_NAME = "addressService";

    public void addNewAddress(Address address);

//    public List<SearchResult> doSearch(String neshaniManzel, String kodePostiManzel, String telephoneManzel, String telephoneHamrah);


}
