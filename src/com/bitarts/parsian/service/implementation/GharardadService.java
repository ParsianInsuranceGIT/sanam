package com.bitarts.parsian.service.implementation;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.dao.GharardadDAO;
import com.bitarts.parsian.model.MafadeGharardad;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.bimename.Gharardad;
import com.bitarts.parsian.model.bimename.GharardadTozihat;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.service.IGharardadService;
import com.bitarts.parsian.viewModel.GharardadSearch;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/13/12
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class GharardadService implements IGharardadService {
    private GharardadDAO gharardadDAO;

    public GharardadDAO getGharardadDAO() {
        return gharardadDAO;
    }

    public void setGharardadDAO(GharardadDAO gharardadDAO) {
        this.gharardadDAO = gharardadDAO;
    }

    public void sabtGharardad(Gharardad gharardad, String tozih, User user) {
        if (gharardad.getCreatedTime() == null)
            gharardad.setCreatedTime(new Date().getTime());
        if (gharardad.getCreatedDate() == null) gharardad.setCreatedDate(DateUtil.getCurrentDate());
        try {
            gharardadDAO.saveOrUpdate(gharardad);
        } catch (Exception ex) {
            gharardadDAO.merge(gharardad);
        }
        try {
            GharardadTozihat gt = new GharardadTozihat();
            gt.setCreatedTime(new Date().getTime());
            gt.setCreatedDate(DateUtil.getCurrentDate());
            gt.setGharardad(gharardad);
            gt.setTozih(tozih);
            gt.setUserCreator(user);
            getGharardadDAO().saveOrUpdate(gt);
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }


    public Gharardad findById(Long id) {
        return (Gharardad) gharardadDAO.findById(Gharardad.class, id);
    }

    public List<Gharardad> search(String shomare, String aztarikh, String tatarikh, String nameNamayande, String kodeNamayande, String nameSherkat, String shomareSabt, City ostan, City shahr, User userCreator) {
        return gharardadDAO.search(shomare, aztarikh, tatarikh, nameNamayande, kodeNamayande, nameSherkat, shomareSabt, ostan, shahr, userCreator);
    }

    public void removeById(Long id) {
        Gharardad g = (Gharardad) gharardadDAO.findById(Gharardad.class, id);
        gharardadDAO.deleteAll(g.getPishnehadList());
        gharardadDAO.delete(g);
    }

    public String createShomareGharardad(String kodeNamayande, String saal) {
        final int tmp = gharardadDAO.countGharardad();
        String retValue = "";
        if (tmp == 0)
            retValue = "31" + "/" + kodeNamayande + "/" + saal + "/" + "00000";
        else {
//            return "31ق" + "/" + kodeNamayande + "/" + saal + "/" + StringUtils.leftPad(String.valueOf(tmp.getId()), 5, "0");
            retValue = "31" + "/" + kodeNamayande + "/" + saal + "/" + StringUtils.leftPad(String.valueOf(tmp + 1), 5, "0");
        }
        return "ق" + retValue;
    }

    public List<Gharardad> findAll() {
        return gharardadDAO.findAllGharardad();
    }

    public List<Gharardad> findAllByNamayande(Integer namayandeId) {
        return gharardadDAO.findAllByNamayande(namayandeId);
    }

    public PaginatedListImpl<Gharardad> findAllowedGharardads(User user, PaginatedListImpl<Gharardad> gharardadhaPaginated) {
        return gharardadDAO.findAllowedGharardads(user, gharardadhaPaginated);
    }

    public PaginatedListImpl<Gharardad> search(GharardadSearch gharardadSearch, User user, PaginatedListImpl<Gharardad> searchGHPaginated) {
        return gharardadDAO.search(gharardadSearch, user, searchGHPaginated);
    }

    public void saveMafadeGharardad(MafadeGharardad mafad) {
        gharardadDAO.saveMafadeGharardad(mafad);
    }
}
