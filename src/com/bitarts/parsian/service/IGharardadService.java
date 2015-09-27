package com.bitarts.parsian.service;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.MafadeGharardad;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.bimename.Gharardad;
import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.viewModel.GharardadSearch;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 5/13/12
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IGharardadService extends IBaseService {
    public static final String SERVICE_NAME = "gharardadService";

    public void sabtGharardad(Gharardad gharardad, String tozih, User user);

    public Gharardad findById(Long id);

    public List<Gharardad> search(String shomare, String aztarikh, String tatarikh, String nameNamayande, String kodeNamayande, String nameSherkat, String shomareSabt, City ostan, City shahr, User userCreator);

    public void removeById(Long id);

    public String createShomareGharardad(String kodeNamayande, String saal);

    public List<Gharardad> findAll();

    public List<Gharardad> findAllByNamayande(Integer namayandeId);

    public PaginatedListImpl<Gharardad> findAllowedGharardads(User user, PaginatedListImpl<Gharardad> gharardadhaPaginated);

    public PaginatedListImpl<Gharardad> search(GharardadSearch gharardadSearch, User user, PaginatedListImpl<Gharardad> searchGHPaginated);

    public void saveMafadeGharardad(MafadeGharardad mafad);
}
