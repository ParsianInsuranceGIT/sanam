package com.bitarts.parsian.service;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.Khesarat;
import com.bitarts.parsian.model.KhesaratHavale;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.viewModel.KhesaratVM;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/13/12
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IKhesaratService extends IBaseService {
    public static final String SERVICE_NAME = "khesaratService";

    public void saveKhesarat(Khesarat khesarat);

    public void updateKhesarat(Khesarat khesarat);

    public void saveKhesaratHavale(KhesaratHavale havale);

    public void updateHavale(KhesaratHavale havale);

    public String createShomareParvande(String codeKargozar);

    public String createShomareHavale(Khesarat khesarat);

    public List<Khesarat> findByVaziatParvande(Integer khesaratInit);

    public Khesarat findById(Long id);

    public KhesaratHavale findHavaleById(Long id);

    public void deleteHavaleById(Long id);

    long countKhesarat();

    public List<Khesarat> findAll();

    public PaginatedListImpl<KhesaratVM> loadKhesarats(PaginatedListImpl<KhesaratVM> pgList, KhesaratVM srchModel);

    public List<Khesarat> searchKhesarat(City ostan, City shahr, Namayande namayande, Namayande vahedSodur, Tarh tarh, String noeGharardad, String nameBimeGozar, Integer noeKhesarat, String ellat, String azTarikh, String taTarikh);

    public void transitKhesarat(User theUser, Long khesaratId, Integer transitionId, String logmessage);
}
