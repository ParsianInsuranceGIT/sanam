package com.bitarts.parsian.service.implementation;

import com.bitarts.parsian.dao.AddressDAO;
import com.bitarts.parsian.model.pishnahad.Address;
import com.bitarts.parsian.service.IAddressService;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 4:20 PM
 */
public class AddressService implements IAddressService {
    private AddressDAO addressDAO;

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public void addNewAddress(Address address){

        addressDAO.save(address);
    }

//    public List<SearchResult> doSearch(String naam, String naam_khaanevaadegi, String naam_pedar, String code_melli, String shomare_shenaasnaame) {
//        return addressDAO.doSearch(naam, naam_khaanevaadegi, naam_pedar, code_melli, shomare_shenaasnaame);
//    }

}
