package com.bitarts.parsian.service.implementation;

import com.bitarts.parsian.dao.AsnadeSodorDAO;
import com.bitarts.parsian.model.karmozd.KarmozdGhest;
import com.bitarts.parsian.service.IQueryService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: n-tehranifar
 * Date: 8/21/13
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueryService implements IQueryService {

    private AsnadeSodorDAO asnadeSodorDAO;

    public void setAsnadeSodorDAO(AsnadeSodorDAO asnadeSodorDAO) {
        this.asnadeSodorDAO = asnadeSodorDAO;
    }

    public long getJameSadereForGhestbandiKasrMaliatEzafi(int bimenameId) {
        return asnadeSodorDAO.getJameSadereForGhestbandiKasrMaliatEzafi(bimenameId);
    }

    public boolean searchKarmozdNamayanade_HasBedehi(Long karmozdId, String state, String namayandeName, Long namayandeId) {
        return asnadeSodorDAO.searchKarmozdNamayanade_HasBedehi(karmozdId, state, namayandeName, namayandeId);
    }

}
