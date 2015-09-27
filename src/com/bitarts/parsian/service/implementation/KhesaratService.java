package com.bitarts.parsian.service.implementation;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.dao.KhesaratDao;
import com.bitarts.parsian.dao.TransitionDAO;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.service.IKhesaratService;
import com.bitarts.parsian.service.ITransitionLogService;
import com.bitarts.parsian.viewModel.KhesaratVM;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/13/12
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class KhesaratService implements IKhesaratService {
    private KhesaratDao khesaratDao;
    private TransitionDAO transitionDAO;
    private ITransitionLogService transitionLogService;

    public KhesaratDao getKhesaratDao() {
        return khesaratDao;
    }

    public void setKhesaratDao(KhesaratDao khesaratDao) {
        this.khesaratDao = khesaratDao;
    }

    public TransitionDAO getTransitionDAO() {
        return transitionDAO;
    }

    public void setTransitionDAO(TransitionDAO transitionDAO) {
        this.transitionDAO = transitionDAO;
    }

    public ITransitionLogService getTransitionLogService() {
        return transitionLogService;
    }

    public void setTransitionLogService(ITransitionLogService transitionLogService) {
        this.transitionLogService = transitionLogService;
    }

    public void saveKhesarat(Khesarat khesarat) {
        khesarat.setCreatedDate(DateUtil.getCurrentDate());
        khesaratDao.saveOrUpdate(khesarat);
    }

    public void updateKhesarat(Khesarat khesarat)
    {
        khesaratDao.update(khesarat);
    }

    public void saveKhesaratHavale(KhesaratHavale havale) {
        havale.setCreatedDate(DateUtil.getCurrentDate());
        try {
            khesaratDao.saveOrUpdate(havale);
        } catch (Exception ex) {
            khesaratDao.merge(havale);
        }
    }

    public void updateHavale(KhesaratHavale havale)
    {
        khesaratDao.update(havale);
    }

    public String createShomareParvande(String codeKargozar)
    {
        return "2211/"+ codeKargozar + "/" + String.valueOf(DateUtil.extractYear(DateUtil.getCurrentDate())).substring(2) + "/" + StringUtils.leftPad(String.valueOf(countKhesarat() + 1), 6, "0");
    }

    public String createShomareHavale(Khesarat khesarat) {
        return StringUtils.leftPad(String.valueOf(khesaratDao.countHavale(khesarat) + 1), 4, "0");
    }

    public List<Khesarat> findByVaziatParvande(Integer stateId) {
        return khesaratDao.findByVaziatParvande(stateId);
    }

    public Khesarat findById(Long id) {
        return khesaratDao.findById(id);
    }

    public KhesaratHavale findHavaleById(Long id) {
        return khesaratDao.findHavaleById(id);
    }

    public void deleteHavaleById(Long id) {
        khesaratDao.deleteHavaleById(id);
    }

    public long countKhesarat() {
        return khesaratDao.count();

    }

    public List<Khesarat> findAll() {
        return khesaratDao.findAll();
    }

    public PaginatedListImpl<KhesaratVM> loadKhesarats(PaginatedListImpl<KhesaratVM> pgList, KhesaratVM srchModel)
    {
        return khesaratDao.loadKhesarats(pgList, srchModel);
    }

    public List<Khesarat> searchKhesarat(City ostan, City shahr, Namayande namayande, Namayande vahedSodur, Tarh tarh, String noeGharardad, String nameBimeGozar, Integer noeKhesarat, String ellat, String azTarikh, String taTarikh) {
        return khesaratDao.searchKhesarat(ostan, shahr, namayande, vahedSodur, tarh, noeGharardad, nameBimeGozar, noeKhesarat, ellat, azTarikh, taTarikh);
    }

    @Transactional
    public void transitKhesarat(User theUser, Long khesaratId, Integer transitionId, String logmessage) {
        Khesarat khesarat = findById(khesaratId);
        Transition transition = transitionDAO.findById(transitionId);
        khesarat.setState(transition.getStateEnd());

        transitionLogService.logKhesaratTransition(theUser, khesarat, transition, logmessage);
        khesarat.setKarshenasKhesarat(theUser);
        updateKhesarat(khesarat);
    }

}
