package com.bitarts.parsian.service;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.KhateSanad;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.karmozd.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/18/11
 * Time: 2:25 PM
 */
public interface IQueryService extends IBaseService  {
    public static final String SERVICE_NAME = "queryService";

    @Transactional(readOnly = true)
    public long getJameSadereForGhestbandiKasrMaliatEzafi(int bimenameId);

    @Transactional(readOnly = true)
    public boolean searchKarmozdNamayanade_HasBedehi(Long karmozdId, String state, String namayandeName, Long namayandeId);
}
