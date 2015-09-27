package com.bitarts.parsian.service;

import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.model.pishnahad.Pishnehad;
import com.bitarts.parsian.model.pishnahad.Shakhs;
import com.bitarts.parsian.viewModel.SearchResult;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 3/8/11
 * Time: 4:18 PM
 */
public interface IShakhsService extends IBaseService  {
    public static final String SERVICE_NAME = "shakhsService";

    public void saveShakhs(Shakhs s);

    void updateShakhs(Shakhs shakhs);

    public boolean isThereKodeMelliShenasayi(String kodeMelliShenasayi);

    public boolean isThere2KodeMelliShenasayi(String kodeMelliShenasayi);

    List<SearchResult> doSearchHoghughi(String naam, String shomareSabt);

    boolean getAdamTaeedPezeshki(Shakhs shakhs);

    String isShakhsValid(String code_melli);

    public boolean checkDarkhastTaghirForPerson(String kodeMelliShenasayi);

    public class KodeMelliShenasayiDuplicated extends Exception {
    }

    public Shakhs addNewShakhs(Shakhs shakhs) throws KodeMelliShenasayiDuplicated;

    public Shakhs searchShakhs(String kodeMelli, boolean isHalateElhaghie);

    public Shakhs searchOrphanPersonRecord(String kodeMell, boolean isHalateElhaghie);

    public List<Shakhs> searchAllShakhsByCodeMeli(String kodeMeli);

    public List<SearchResult> doSearch(String code_melli, String searchBimeShode,String halateElhaghie);

    public Shakhs findShakhs(Integer id);

    public boolean isHavePishnehadOrCurrentDarkhast(String codeMelli);

    boolean isPersonEditable(String user, boolean haghighi, String code);

    public List<Pishnehad> findAllPishnehadsForShakhs(String kodeMelli, Shakhs.Role shakhsRole,List<Elhaghiye> elhaghiyeList);

    public List<Pishnehad> findAllPishnehadsForShakhs(String kodeMelli);
}
