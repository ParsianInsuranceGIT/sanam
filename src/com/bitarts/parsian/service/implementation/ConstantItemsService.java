package com.bitarts.parsian.service.implementation;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.dao.ConstantItemsDAO;
import com.bitarts.parsian.dao.StateDAO;
import com.bitarts.parsian.model.State;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.constantItems.ConstantForPishnehadForm;
import com.bitarts.parsian.model.constantItems.ConstantSoalateVaziateSalamati;
import com.bitarts.parsian.model.management.Bank;
import com.bitarts.parsian.service.IConstantItemsService;
import com.bitarts.parsian.viewModel.PishnehadConstants;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/24/11
 * Time: 4:03 PM
 */
public class ConstantItemsService implements IConstantItemsService {
    private ConstantItemsDAO constantItemsDAO;
    private StateDAO stateDAO;

    public void setStateDAO(StateDAO stateDAO) {
        this.stateDAO = stateDAO;
    }

    public void setConstantItemsDAO(ConstantItemsDAO constantItemsDAO) {
        this.constantItemsDAO = constantItemsDAO;
    }

    public PishnehadConstants findConstantsForPishnehadForm() {
        PishnehadConstants pishnehadConstants = new PishnehadConstants();

        List<ConstantForPishnehadForm> constantForPishnehadFormList = constantItemsDAO.findAllConstantForPishnehadForm();
        pishnehadConstants.setConstantForPishnehadFormList(constantForPishnehadFormList);

        List<ConstantSoalateVaziateSalamati> constantSoalateVaziateSalamatiList = constantItemsDAO.findConstantSoalateVaziateSalamati();
        pishnehadConstants.setConstantSoalateVaziateSalamatiSet(constantSoalateVaziateSalamatiList);

        return pishnehadConstants;
    }

    public List<City> getCities() {
        return constantItemsDAO.finaAllCities();
    }

    public List<City> getOstans() {
        return constantItemsDAO.findAllOstans();
    }

    public City findCityById(long cityId) {
        return constantItemsDAO.findCityById(cityId);
    }

    public List<City> findCityByName(String cityName) {
        return constantItemsDAO.findCityByName(cityName);
    }

    public City findCityByIdForPid(long idForPid) {
        return constantItemsDAO.findCityByIdForPid(idForPid);
    }

    @Transactional
    public State findStateById(int stateId) {
        return stateDAO.findById(stateId);
    }

    @Transactional
    public ConstantSoalateVaziateSalamati findSoalVaziateSalamatiById(Integer id) {
        return constantItemsDAO.findSoalVaziateSalamatiById(id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<State> findStatesByStateMachine(String pishnahad_system) {
        return constantItemsDAO.findStatesByStateMachine(pishnahad_system);
    }

    public void removeCityById(Long cityId){
        constantItemsDAO.removeCityById(cityId);
    }

    public PaginatedListImpl<City> findAllCities(PaginatedListImpl<City> paginatedList, String sname, Long scode, String stabe, Long scodetabe) {
        return constantItemsDAO.finaAllCities(paginatedList, sname, scode, stabe, scodetabe);
    }

    public PaginatedListImpl<Bank> findAllBanks(PaginatedListImpl<Bank> listBanks) {
        return constantItemsDAO.finaAllBanks(listBanks);
    }

    public void saveNewCity(City newCity) {
        constantItemsDAO.saveOrUpdate(newCity);
    }

    public void saveNewBank(Bank bank) {
        constantItemsDAO.saveOrUpdate(bank);
    }

    public void removeBankById(Integer id) {
        constantItemsDAO.deleteById(Bank.class, id);
    }

    public Bank findBankById(Integer id) {
        return (Bank) constantItemsDAO.findById(Bank.class, id);
    }
}
