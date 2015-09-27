package com.bitarts.parsian.service;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.State;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.constantItems.ConstantSoalateVaziateSalamati;
import com.bitarts.parsian.model.management.Bank;
import com.bitarts.parsian.viewModel.PishnehadConstants;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/24/11
 * Time: 4:02 PM
 */
public interface IConstantItemsService extends IBaseService  {
    public static final String SERVICE_NAME = "constantItemsService";

    public PishnehadConstants findConstantsForPishnehadForm();

    List<City> getCities();

    List<City> getOstans();

    City findCityById(long cityId);

    List<City> findCityByName(String cityName);

    public City findCityByIdForPid(long idForPid);

    State findStateById(int stateId);

    ConstantSoalateVaziateSalamati findSoalVaziateSalamatiById(Integer id);

    List<State> findStatesByStateMachine(String pishnahad_system);

    void removeCityById(Long cityId);

    PaginatedListImpl<City> findAllCities(PaginatedListImpl<City> paginatedList, String sname, Long scode, String stabe, Long scodetabe);

    void saveNewCity(City newCity);


    void removeBankById(Integer id);

    Bank findBankById(Integer id);

    PaginatedListImpl<Bank> findAllBanks(PaginatedListImpl<Bank> listBanks);

    void saveNewBank(Bank bank);
}
