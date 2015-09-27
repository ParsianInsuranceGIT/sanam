package com.bitarts.parsian.service.karmozd;

import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.dao.KarmozdDAO;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.SeniorSubset;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.KhateSanad;
import com.bitarts.parsian.model.asnadeSodor.Sanad;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.model.karmozd.*;
import com.bitarts.parsian.service.IAsnadeSodorService;
import com.bitarts.parsian.service.INamayandeService;
import com.bitarts.parsian.service.IQueryService;
import com.bitarts.parsian.service.ISequenceService;
import com.bitarts.parsian.service.calculations.KarmozdCalculate;
import com.bitarts.parsian.viewModel.KarmozdNamayandeVM;
import com.bitarts.parsian.viewModel.KarmozdTashvighiVosuliVM;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/18/11
 * Time: 2:28 PM
 */
public class KarmozdService implements IKarmozdService {
    private KarmozdDAO karmozdDAO;
    private IAsnadeSodorService asnadeSodorService;
    private ISequenceService sequenceService;
    private IQueryService queryService;
    private INamayandeService namayandeService;

    public IQueryService getQueryService()
    {
        return queryService;
    }

    public INamayandeService getNamayandeService()
    {
        return namayandeService;
    }

    public void setNamayandeService(INamayandeService namayandeService)
    {
        this.namayandeService = namayandeService;
    }

    public void setQueryService(IQueryService queryService)
    {
        this.queryService = queryService;
    }

    public IAsnadeSodorService getAsnadeSodorService()
    {
        return asnadeSodorService;
    }

    public void setAsnadeSodorService(IAsnadeSodorService asnadeSodorService)
    {
        this.asnadeSodorService = asnadeSodorService;
    }

    public long karmozdBimename(Bimename bimename, Estelam estelam)
    {
        KarmozdGhest.Tarefe tarefe = KarmozdCalculate.getTarefeBimename(bimename);
        long sarmaye = (long) (estelam.getSarmaye_paye_fot_long() * 0.03);
        long haghBimeAval;
        long sepordeEbteda = 0l;
        long haghBimeOrSarmaye = sarmaye;
        if (tarefe.equals(KarmozdGhest.Tarefe.YEK_SE_NAVAD_DO) || tarefe.equals(KarmozdGhest.Tarefe.YEK_CHAHAR_NAVAD_DO))
        {
            long haghBimeBeduneMaliatVaPoosheshha = queryService.getJameSadereForGhestbandiKasrMaliatEzafi(bimename.getId());
//            if (estelam.getMablagh_seporde_ebtedaye_sal() != null)
//                sepordeEbteda = Long.parseLong(estelam.getMablagh_seporde_ebtedaye_sal().replaceAll(",", "").trim());
//            haghBimeAval = (long) ((haghBimeBeduneMaliatVaPoosheshha + sepordeEbteda) * 0.75);
            haghBimeAval = (long) (haghBimeBeduneMaliatVaPoosheshha * 0.75);
            if (haghBimeAval < sarmaye)
                haghBimeOrSarmaye = haghBimeAval;
        }
        return haghBimeOrSarmaye;
    }

    private void karmozdNotTalighi(KhateSanad khateSanad,String azTarikhVosoli, String taTarikhVosoli, Ghest ghest, List<Ghest> followGhestList, Karmozd karmozdOb, List<BlackList> blackLists, HashMap<Namayande,List<KarmozdGhest>> followKarmozdGhestMap,Namayande namayande, Bimename bimename,Credebit bedehiGhest,Elhaghiye elhaghiyeT)
    {
        if
        (
            khateSanad.getKarmozdGhestList().size()==0 &&
//            DateUtil.isGreaterThanOrEqual(khateSanad.getSanad().getCreatedDate(), azTarikhVosoli) &&
            DateUtil.isGreaterThanOrEqual(taTarikhVosoli, khateSanad.getSanad().getCreatedDate())
        )
        {
            KarmozdGhest.Tarefe tar = KarmozdCalculate.getTarefeBimename(bimename);
//--------------------------------------------------For Elhaghie Taghirat-----------------------------------------------
            if (elhaghiyeT != null && DateUtil.isGreaterThan(elhaghiyeT.getCreatedDate(), bedehiGhest.getCreatedDate()))
            {
                if(ghest.getKarmozdReal()!=null && ghest.getPreKarmozdReal()==null)
                {
                    ghest.setPreKarmozdReal(ghest.getKarmozdReal());
                    ghest.setKarmozdReal(KarmozdCalculate.calRealKarmozdForGhest(khateSanad.getBedehiCredebit(), tar, null));
                    followGhestList.add(ghest);
                }
            }
//----------------------------------------------------------------------------------------------------------------------
            else
            {
                if (ghest.getKarmozdReal() == null)
                {
                    ghest.setKarmozdReal(KarmozdCalculate.calRealKarmozdForGhest(khateSanad.getBedehiCredebit(), tar, asnadeSodorService));
                    followGhestList.add(ghest);
                }
            }

            KarmozdGhest  karmozdGhestOb=new KarmozdGhest(khateSanad,karmozdOb,ghest,tar);

            karmozdGhestOb.setKarmozdAmount(KarmozdCalculate.calKarmozdForKarmozdGhest(karmozdGhestOb,tar, khateSanad.getBedehiCredebit().getPishnehad(), khateSanad.getBedehiCredebit(),null));
            karmozdGhestOb.setNamayandeId(khateSanad.getBedehiCredebit().getPishnehad().getNamayande().getId());
            for(BlackList bl : blackLists)
            {
                if(ghest.getGhestBandi().getBimename().getId().equals(bl.getBimename().getId()))
                {
                    karmozdGhestOb.setBlackList(bl);
                    break;
                }
            }

            if(ghest.getGhestBandi().getSaleBimeiInt() > ghest.getGhestBandi().getBimename().getCurrentSaleBimei())
            {
                karmozdGhestOb.setType(KarmozdGhest.Type.DAR_ENTEZAR_SARRESID);
                karmozdGhestOb.setWasPaid(false);
            }
            else if(ghest.getGhestBandi().getSaleBimeiInt() <= ghest.getGhestBandi().getBimename().getCurrentSaleBimei())
            {
                if(bimename.getPishnehad().getOptions().equals("CODE_MOVAGHAT"))
                {
                    karmozdGhestOb.setType(KarmozdGhest.Type.CODE_MOVAGHAT);
                    karmozdGhestOb.setWasPaid(false);
                }
                else
                {
                    karmozdGhestOb.setType(KarmozdGhest.Type.ADI);
                    karmozdGhestOb.setWasPaid(true);

                    ebtlOrBzkhrdCheckAndHandling(bimename, karmozdOb, khateSanad, ghest, tar, karmozdGhestOb, namayande, followKarmozdGhestMap, azTarikhVosoli, taTarikhVosoli);
                }
            }
            List<KarmozdGhest> kgList = new ArrayList<KarmozdGhest>();
            if (followKarmozdGhestMap.containsKey(namayande))
            {
                kgList = followKarmozdGhestMap.get(namayande);
                followKarmozdGhestMap.remove(namayande);
            }
            kgList.add(karmozdGhestOb);
            followKarmozdGhestMap.put(namayande, kgList);
        }
    }

    private void tgrCheckAndHandling(KarmozdGhest kg, Bimename bimename, Karmozd karmozdOb, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap, String azTarikh, String taTarikh,Credebit bedehiGhest,Ghest ghest,KarmozdGhest.Tarefe tarefe)
    {
        Elhaghiye elhaghiyeT= bimename.getElTaghiratToFirstYear();

        if (elhaghiyeT != null && DateUtil.isGreaterThan(elhaghiyeT.getCreatedDate(), bedehiGhest.getCreatedDate()))
        {
            Namayande namayande = kg.getNamayandeId()!=null?namayandeService.getNamayandeById(kg.getNamayandeId()):kg.getKarmozdNamayande().getNamayande();
            KarmozdGhest karmozdGhestTgr = new KarmozdGhest(kg.getKhateSanad(), karmozdOb, kg.getGhest(), kg.getTarefe());

            ghest.setPreKarmozdReal(ghest.getKarmozdReal());
            ghest.setKarmozdReal(KarmozdCalculate.calRealKarmozdForGhest(bedehiGhest, tarefe,null));
            karmozdGhestTgr.setKarmozdAmount(KarmozdCalculate.calKarmozdTgr(kg));
            karmozdGhestTgr.setTarefe(kg.getTarefe());
            karmozdGhestTgr.setNamayandeId(kg.getKhateSanad().getBedehiCredebit().getPishnehad().getNamayande().getId());
            karmozdGhestTgr.setType(KarmozdGhest.Type.TAGHIRAT);

            List<KarmozdGhest> kgTgrList = new ArrayList<KarmozdGhest>();
            if (followKarmozdGhestMap.containsKey(namayande))
            {
                kgTgrList = followKarmozdGhestMap.get(namayande);
                followKarmozdGhestMap.remove(namayande);
            }

            ebtlOrBzkhrdCheckAndHandling(bimename, karmozdOb, karmozdGhestTgr.getKhateSanad(), karmozdGhestTgr.getGhest(), karmozdGhestTgr.getTarefe(), karmozdGhestTgr, namayande, followKarmozdGhestMap, azTarikh, taTarikh);
            update(ghest);
            saveKarmozdGhest(karmozdGhestTgr);
            kgTgrList.add(karmozdGhestTgr);
            followKarmozdGhestMap.put(namayande, kgTgrList);
            kg.setKarmozdTaghirat(karmozdGhestTgr);
        }
    }

    private void ebtlOrBzkhrdCheckAndHandling(Bimename bimename, Karmozd karmozdOb, KhateSanad khateSanad, Ghest ghest, KarmozdGhest.Tarefe tar,KarmozdGhest karmozdGhestOb,Namayande namayande, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap, String azTarikh,String taTarikh)
    {
        if
        (
            (bimename.getTarikhEbtalForKrmzd() != null && DateUtil.betweenEq(bimename.getTarikhEbtalForKrmzd(), azTarikh, taTarikh)) ||
            (bimename.getTarikhBazkharidForKrmzd() != null && DateUtil.betweenEq(bimename.getTarikhBazkharidForKrmzd(), azTarikh, taTarikh))
        )
        {
            KarmozdGhest karmozdGhestBargashti = new KarmozdGhest(khateSanad, karmozdOb, ghest, tar);
            karmozdGhestBargashti.setKarmozdAmount(-KarmozdCalculate.calKarmozdBargashtiEbtalOrBazkharid(karmozdGhestOb));
            karmozdGhestBargashti.setNamayandeId(namayande.getId());
            karmozdGhestBargashti.setType(KarmozdGhest.Type.KARMOZD_BARGASHTI);

            List<KarmozdGhest> kgBargashtiList = new ArrayList<KarmozdGhest>();
            if (followKarmozdGhestMap.containsKey(namayande))
            {
                kgBargashtiList = followKarmozdGhestMap.get(namayande);
                followKarmozdGhestMap.remove(namayande);
            }
            kgBargashtiList.add(karmozdGhestBargashti);
            followKarmozdGhestMap.put(namayande, kgBargashtiList);
            karmozdGhestOb.setKarmozdBargashti(karmozdGhestBargashti);
        }
    }

    public void disjoinKarmozdsAndCreateTadilat(Integer sanadId)
    {
        List<KarmozdGhest> kgList=disjoinKarmozds(sanadId);
        if (kgList==null) return;
        for(KarmozdGhest kg:kgList)
        {
            String desc="حذف قسط بیمه نامه "+kg.getDescription();
            KarmozdTadilat tadilat=new KarmozdTadilat(kg.getKarmozdNamayande().getNamayande().getKodeNamayandeKargozar(),(Long.toString(kg.getKarmozdAmount()*-1)),kg.getDescription(),desc,desc,kg);
            karmozdDAO.save(tadilat);
        }
    }
    public void saveTadilat(KarmozdTadilat tadilat)
    {
        karmozdDAO.save(tadilat);
    }
    public List<KarmozdGhest> disjoinKarmozds(Integer sanadId)
    {
        Sanad sanad=asnadeSodorService.findSanadById(sanadId);
        if(sanad==null) return null ;
        Set<Ghest> ghestSet=new HashSet<Ghest>();
        Set<KhateSanad> khateSanadSet=sanad.getKhateSanadSet();
        Iterator<KhateSanad> khateSanadIterator=khateSanadSet.iterator();
        List<KarmozdGhest> karmozdGhestList=new ArrayList<KarmozdGhest>();
        while (khateSanadIterator.hasNext())
        {
            KhateSanad khateSanad=khateSanadIterator.next();
            if(khateSanad.getBedehiCredebit().getGhest()==null)
                continue;
            Ghest ghest=khateSanad.getBedehiCredebit().getGhest();
            Set<KarmozdGhest> karmozdGhestSet=ghest.getkarmozdSet();
            if(karmozdGhestSet==null ||ghestSet.contains(ghest))
                continue;
            Iterator<KarmozdGhest> karmozdGhestIterator=karmozdGhestSet.iterator();
            while (karmozdGhestIterator.hasNext())
            {
                KarmozdGhest karmozdGhest=karmozdGhestIterator.next();
                karmozdGhest.setGhest(null);
                karmozdGhest.setKhateSanad(null);
                karmozdGhest.setDescription(ghest.getGhestBandi().getBimename().getShomare());
                karmozdDAO.update(karmozdGhest);
                if(!karmozdGhest.getType().equals(KarmozdGhest.Type.CODE_MOVAGHAT))
                    karmozdGhestList.add(karmozdGhest);
            }
            ghestSet.add(ghest);
        }
        return karmozdGhestList;
    }

    private void karmozdTalighi(KhateSanad khateSanad, Ghest ghest, Karmozd karmozdOb, List<BlackList> blackLists, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap,Namayande namayande,Bimename bimename,String azTarikh, String taTarikh)
    {
        KarmozdGhest drEntzrKrmzd = khateSanad.getDarEntezarSarresidKarmozd();
        if(drEntzrKrmzd!=null)
        {
            if(ghest.getGhestBandi().getSaleBimeiInt() <= ghest.getGhestBandi().getBimename().getCurrentSaleBimei())
            {
                KarmozdGhest.Tarefe tar = KarmozdCalculate.getTarefeBimename(bimename);
                KarmozdGhest karmozdGhestOb = new KarmozdGhest(khateSanad, karmozdOb, ghest, tar);
                karmozdGhestOb.setKarmozdAmount(drEntzrKrmzd.getKarmozdAmount());
                karmozdGhestOb.setNamayandeId(drEntzrKrmzd.getNamayandeId());
                for (BlackList bl : blackLists)
                {
                    if(ghest.getGhestBandi().getBimename().getId().equals(bl.getBimename().getId()))
                    {
                        karmozdGhestOb.setBlackList(bl);
                        break;
                    }
                }

                if (bimename.getPishnehad().getOptions().equals("CODE_MOVAGHAT"))
                {
                    karmozdGhestOb.setType(KarmozdGhest.Type.CODE_MOVAGHAT);
                    karmozdGhestOb.setWasPaid(false);
                }
                else
                {
                    karmozdGhestOb.setType(KarmozdGhest.Type.TALIGHI);
                    karmozdGhestOb.setWasPaid(true);
                    List<KarmozdGhest> karGhests = new ArrayList<KarmozdGhest>();
                    for (KarmozdGhest kg : khateSanad.getKarmozdGhestList())
                    {
                        kg.setWasPaid(true);
                        karGhests.add(kg);
                    }
                    karmozdDAO.saveKarmozdGhestList(karGhests);
                    ebtlOrBzkhrdCheckAndHandling(bimename, karmozdOb, karmozdGhestOb.getKhateSanad(), karmozdGhestOb.getGhest(), karmozdGhestOb.getTarefe(), karmozdGhestOb, namayande, followKarmozdGhestMap, azTarikh, taTarikh);
                    tgrCheckAndHandling(karmozdGhestOb,bimename,karmozdOb,followKarmozdGhestMap,azTarikh,taTarikh,ghest.getCredebit(),ghest,tar);
                }

                List<KarmozdGhest> kgList = new ArrayList<KarmozdGhest>();
                if (followKarmozdGhestMap.containsKey(namayande))
                {
                    kgList = followKarmozdGhestMap.get(namayande);
                    followKarmozdGhestMap.remove(namayande);
                }
                kgList.add(karmozdGhestOb);
                followKarmozdGhestMap.put(namayande, kgList);
            }
        }
    }

    public void karmozdProcessForVosulOrPooshesh(List<KhateSanad> khateSanadList,String azTarikhVosoli, String taTarikhVosoli, List<Ghest> followGhestList, Karmozd karmozdOb, List<BlackList> blackLists, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap, Karmozd.Type type)
    {
        for(KhateSanad khateSanad : khateSanadList)
        {
            Credebit bedehi= khateSanad.getBedehiCredebit();
            Bimename bimename = bedehi.getPishnehad().getBimename();
            Namayande namayande = bimename.getPishnehad().getNamayande();
            Ghest ghest = bedehi.getGhest();

            KarmozdGhest karmozdGhestOb = new KarmozdGhest(khateSanad,karmozdOb,ghest,null);
            karmozdGhestOb.setNamayandeId(namayande.getId());
            if (type.equals(Karmozd.Type.Karmozd_Vosuli))
            {
                if(ghest.getKarmozdVosuli()==null)
                {
                    ghest.setKarmozdVosuli(asnadeSodorService.getKarmozdVosul(bimename.getTarikhSodour(), bimename.getPishnehad().getTarh(), bedehi.getAmount_long(), ghest.getMaliatLong(), ghest.getHaghBimePoosheshhayeEzafiLong(), false, null));
                    asnadeSodorService.saveGhest(ghest);
                }
                karmozdGhestOb.setKarmozdAmount(KarmozdCalculate.calKarmozdVosuliOrPoosheshForGYekja(ghest.getKarmozdVosuli(),Long.parseLong(khateSanad.getAmount().replaceAll(",","").trim()),bedehi.getAmount_long(), KarmozdGhest.Type.VOSULI));
                karmozdGhestOb.setType(KarmozdGhest.Type.VOSULI);
            }
            if (type.equals(Karmozd.Type.Karmozd_Pooshesh_Ezafi))
            {
                if (ghest.getKarmozdPoosheshEzafi() == null)
                {
                    ghest.setKarmozdPoosheshEzafi(asnadeSodorService.getKarmozdPoosheshEzafi(ghest.getHaghBimePoosheshhayeEzafiLong(),bimename.getPishnehad().getTarh(), bimename.getTarikhSodour()));
                    asnadeSodorService.saveGhest(ghest);
                }
                    karmozdGhestOb.setKarmozdAmount(KarmozdCalculate.calKarmozdVosuliOrPoosheshForGYekja(ghest.getKarmozdPoosheshEzafi(), Long.parseLong(khateSanad.getAmount().replaceAll(",", "").trim()), bedehi.getAmount_long(), KarmozdGhest.Type.POOSHESH_EZAFI));
                    karmozdGhestOb.setType(KarmozdGhest.Type.POOSHESH_EZAFI);
            }

            if (!karmozdGhestOb.getKarmozdAmount().equals(0))
            {
                if (blackLists != null)
                {
                    for (BlackList bl : blackLists)
                    {
                        if (bimename.getId().equals(bl.getBimename().getId()))
                        {
                            karmozdGhestOb.setBlackList(bl);
                            break;
                        }
                    }
                }

                List<KarmozdGhest> kgList = new ArrayList<KarmozdGhest>();
                if (followKarmozdGhestMap.containsKey(namayande))
                {
                    kgList = followKarmozdGhestMap.get(namayande);
                    followKarmozdGhestMap.remove(namayande);
                }
                kgList.add(karmozdGhestOb);
                followKarmozdGhestMap.put(namayande, kgList);
            }
        }
    }

    public void karmozdGheyreYekjaProcess(List<KhateSanad> khateSanadList,String azTarikhVosoli, String taTarikhVosoli, List<Ghest> followGhestList, Karmozd karmozdOb, List<BlackList> blackLists, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap)
    {       int i=0;

        for (KhateSanad khateSanad : khateSanadList)
        {
            i++;
            Ghest ghest=khateSanad.getBedehiCredebit().getGhest();
            Credebit bedehiGhest = ghest.getCredebit();
            Bimename bimename =ghest.getGhestBandi().getBimename();
            Elhaghiye elhaghiyeT = bimename.getElTaghiratToFirstYear();
            Namayande namayande = bimename.getPishnehad().getNamayande();

            karmozdNotTalighi(khateSanad, azTarikhVosoli, taTarikhVosoli, ghest, followGhestList, karmozdOb, blackLists, followKarmozdGhestMap, namayande, bimename, bedehiGhest, elhaghiyeT);

            karmozdTalighi(khateSanad, ghest, karmozdOb, blackLists, followKarmozdGhestMap,namayande,bimename,azTarikhVosoli,taTarikhVosoli);

        }

        saveOrUpdateAll(followGhestList);
    }

    public void karmozdYekjaProcess(List<Ghest> ghestListYekja, String taTarikhVosoli,String azTarikhVosoli, List<Ghest> followGhestList, Karmozd karmozdOb, List<BlackList> blackLists, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap)
    {
        for(Ghest ghest : ghestListYekja)
        {
            Namayande namayande = ghest.getGhestBandi().getBimename().getPishnehad().getNamayande();
            if
            (
                (ghest.getCredebit().getKarmozdGhestList() == null || ghest.getCredebit().getKarmozdGhestList().size() == 0 )  &&
                DateUtil.isGreaterThanOrEqual(taTarikhVosoli, ghest.getGhestBandi().getBimename().getTarikhSodour())&& //khateSanad.getSanad().getCreatedDate()) &&
                DateUtil.isGreaterThanOrEqual(ghest.getGhestBandi().getBimename().getTarikhSodour(),azTarikhVosoli)&&//(khateSanad.getSanad().getCreatedDate(),azTarikhVosoli)
                ghest.getGhestBandi().getBimename().getCurrentSaleBimei()==0
            )
            {
                if(ghest.getKarmozdReal()==null)
                {
                    ghest.setKarmozdReal(KarmozdCalculate.calRealKarmozdForGhest(ghest.getCredebit(),KarmozdCalculate.getTarefeBimename(ghest.getGhestBandi().getBimename()), null));
                    followGhestList.add(ghest);
                }
                Bimename bimename=ghest.getGhestBandi().getBimename();
                KarmozdGhest.Tarefe tar= KarmozdCalculate.getTarefeBimename(bimename);
                KarmozdGhest  karmozdGhestOb=new KarmozdGhest(ghest.getCredebit(),karmozdOb,ghest,tar);
                karmozdGhestOb.setNamayandeId(ghest.getCredebit().getPishnehad().getNamayande().getId());
                karmozdGhestOb.setKarmozdAmount(KarmozdCalculate.calKarmozdForKarmozdGhest(karmozdGhestOb,tar, ghest.getCredebit().getPishnehad(), ghest.getCredebit(),null));
                karmozdGhestOb.setSaleBimeei(0);
                for(BlackList bl : blackLists)
                {
                    if(ghest.getGhestBandi().getBimename().getId().equals(bl.getBimename().getId()))
                    {
                        karmozdGhestOb.setBlackList(bl);
                        break;
                    }
                }
                karmozdGhestOb.setTarefe(tar);

                if(bimename.getPishnehad().getOptions().equals("CODE_MOVAGHAT"))
                {
                    karmozdGhestOb.setType(KarmozdGhest.Type.CODE_MOVAGHAT);
                }
                else
                {
                    karmozdGhestOb.setType(KarmozdGhest.Type.ADI);
                }
//                    followKarmozdGhestList.add(karmozdGhestOb);
                List<KarmozdGhest> kgList = new ArrayList<KarmozdGhest>();
                if (followKarmozdGhestMap.containsKey(namayande))
                {
                    kgList = followKarmozdGhestMap.get(namayande);
                    followKarmozdGhestMap.remove(namayande);
                }
                kgList.add(karmozdGhestOb);
                followKarmozdGhestMap.put(namayande, kgList);
            }
//            else if(ghest.getCredebit().getKarmozdGhestListAdi().size()>0 && ghest.getCredebit().getKarmozdGhestListAdi().size()<3)
            else if(ghest.getGhestBandi().getBimename().getCurrentSaleBimei() == 1 || ghest.getGhestBandi().getBimename().getCurrentSaleBimei() == 2)
            {
                Bimename bimename=ghest.getGhestBandi().getBimename();
                KarmozdGhest.Tarefe tar = KarmozdCalculate.getTarefeBimename(bimename);
                KarmozdGhest  karmozdGhestOb=new KarmozdGhest(ghest.getCredebit(),karmozdOb,ghest,tar);
                karmozdGhestOb.setNamayandeId(bimename.getPishnehad().getNamayande().getId());
                karmozdGhestOb.setKarmozdAmount(KarmozdCalculate.calKarmozdForKarmozdGhest(karmozdGhestOb,tar, ghest.getCredebit().getPishnehad(),ghest.getCredebit(),null));
                for(BlackList bl : blackLists)
                {
                    if(ghest.getGhestBandi().getBimename().getId().equals(bl.getBimename().getId()))
                    {
                        karmozdGhestOb.setBlackList(bl);
                        break;
                    }
                }
                karmozdGhestOb.setTarefe(KarmozdCalculate.getTarefeBimename(bimename));

                if (bimename.getPishnehad().getOptions().equals("CODE_MOVAGHAT"))
                {
                    if (ghest.getGhestBandi().getBimename().getCurrentSaleBimei() == 1)
                    {
                        if (ghest.getCredebit().getKarmozdGhestMovaghatSale1() != null)
                        {
                            continue;
                        }
                        else
                        {
                            karmozdGhestOb.setType(KarmozdGhest.Type.CODE_MOVAGHAT);
                            karmozdGhestOb.setSaleBimeei(1);
                        }
                    }

                    else if(ghest.getGhestBandi().getBimename().getCurrentSaleBimei() == 2)
                    {
                        if (ghest.getCredebit().getKarmozdGhestMovaghatSale2() != null)
                        {
                            continue;
                        }
                        else
                        {
                            karmozdGhestOb.setType(KarmozdGhest.Type.CODE_MOVAGHAT);
                            karmozdGhestOb.setSaleBimeei(2);
                        }
                    }
//                    else continue;
                }

                else
                {
                    if (ghest.getGhestBandi().getBimename().getCurrentSaleBimei() == 1)
                    {
                        if (ghest.getCredebit().getKarmozdGhestAdiSale1() != null)
                        {
                            continue;
                        }
                        else
                        {
                            karmozdGhestOb.setType(KarmozdGhest.Type.ADI);
                            karmozdGhestOb.setSaleBimeei(1);
                        }
                    }

                    else if (ghest.getGhestBandi().getBimename().getCurrentSaleBimei() == 2)
                    {
                        if (ghest.getCredebit().getKarmozdGhestAdiSale2() != null)
                        {
                            continue;
                        }
                        else
                        {
                            karmozdGhestOb.setType(KarmozdGhest.Type.ADI);
                            karmozdGhestOb.setSaleBimeei(2);
                        }
                    }
//                    else continue;
                }
//                    followKarmozdGhestList.add(karmozdGhestOb);
                List<KarmozdGhest> kgList = new ArrayList<KarmozdGhest>();
                if (followKarmozdGhestMap.containsKey(namayande))
                {
                    kgList = followKarmozdGhestMap.get(namayande);
                    followKarmozdGhestMap.remove(namayande);
                }
                kgList.add(karmozdGhestOb);
                followKarmozdGhestMap.put(namayande, kgList);
            }
        }
    }

    public void karmozdYekjaProcessForVosulOrPooshesh(List<Ghest> ghestListYekja, String taTarikhVosoli, String azTarikhVosoli, List<Ghest> followGhestList, Karmozd karmozdOb, List<BlackList> blackLists, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap,Karmozd.Type type)
    {
        for (Ghest ghest : ghestListYekja)
        {
            Bimename bimename = ghest.getGhestBandi().getBimename();
            Namayande namayande = bimename.getPishnehad().getNamayande();

            KarmozdGhest karmozdGhestOb = new KarmozdGhest(ghest.getCredebit(), karmozdOb, ghest,null);
            karmozdGhestOb.setNamayandeId(namayande.getId());
            if(type.equals(Karmozd.Type.Karmozd_Vosuli))
            {
                karmozdGhestOb.setKarmozdAmount(ghest.getKarmozdVosuli());
                karmozdGhestOb.setType(KarmozdGhest.Type.VOSULI);
            }
            if(type.equals(Karmozd.Type.Karmozd_Pooshesh_Ezafi))
            {
                karmozdGhestOb.setKarmozdAmount(ghest.getKarmozdPoosheshEzafi());
                karmozdGhestOb.setType(KarmozdGhest.Type.POOSHESH_EZAFI);
            }

            if (!karmozdGhestOb.getKarmozdAmount().equals(0))
            {
                if(blackLists!=null)
                {
                    for (BlackList bl : blackLists)
                    {
                        if (bimename.getId().equals(bl.getBimename().getId()))
                        {
                            karmozdGhestOb.setBlackList(bl);
                            break;
                        }
                    }
                }

                List<KarmozdGhest> kgList = new ArrayList<KarmozdGhest>();
                if (followKarmozdGhestMap.containsKey(namayande))
                {
                    kgList = followKarmozdGhestMap.get(namayande);
                    followKarmozdGhestMap.remove(namayande);
                }
                kgList.add(karmozdGhestOb);
                followKarmozdGhestMap.put(namayande, kgList);
            }
        }
    }

    public void karmozdTaghirCodeDaemProcess(List<KarmozdGhest> kgTaghirCode,Karmozd karmozdOb, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap,String azTarikh, String taTarikh)
    {
        for(KarmozdGhest kg : kgTaghirCode)
        {
            Namayande namayande= kg.getGhest().getCredebit().getPishnehad().getNamayande();
            Bimename bimename = kg.getGhest().getGhestBandi().getBimename();
            KarmozdGhest karmozdGhestDaem = null;
            if(kg.getGhest().getCredebit().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("yekja"))
            {
                karmozdGhestDaem = new KarmozdGhest(kg.getGhest().getCredebit(),karmozdOb,kg.getGhest(),kg.getTarefe());
                Integer bimeYear=DateUtil.getBimeYear(kg.getKarmozd().getCreatedDate(), bimename.getTarikhShorou());
                karmozdGhestDaem.setKarmozdAmount(KarmozdCalculate.calKarmozdForKarmozdGhestYekjayeMovaghat(karmozdGhestDaem, karmozdGhestDaem.getTarefe(), bimeYear));
                karmozdGhestDaem.setSaleBimeei(kg.getSaleBimeei());
            }
            else
            {
                Elhaghiye elhaghieT = bimename.getElTaghiratToFirstYear();
                Long karmozdReal=null;
                if (elhaghieT != null && DateUtil.betweenEq(elhaghieT.getCreatedDate(), azTarikh, taTarikh))
                {
                    karmozdReal= KarmozdCalculate.calRealKarmozdForGhest(kg.getGhest().getCredebit(),kg.getTarefe(),null);
                    if (kg.getGhest().getPreKarmozdReal() == null)
                    {
                        kg.getGhest().setPreKarmozdReal(kg.getGhest().getKarmozdReal());
                        kg.getGhest().setKarmozdReal(karmozdReal);
                        update(kg.getGhest());
                    }
                }
                karmozdGhestDaem = new KarmozdGhest(kg.getKhateSanad(), karmozdOb, kg.getGhest(), kg.getTarefe());
                karmozdGhestDaem.setKarmozdAmount(KarmozdCalculate.calKarmozdForKarmozdGhest(karmozdGhestDaem, karmozdGhestDaem.getTarefe(), karmozdGhestDaem.getGhest().getCredebit().getPishnehad(), null,karmozdReal));
            }
            karmozdGhestDaem.setNamayandeId(namayande.getId());
            karmozdGhestDaem.setTarefe(kg.getTarefe());
            karmozdGhestDaem.setType(KarmozdGhest.Type.TAGHIR_CODE_DAEM);

            ebtlOrBzkhrdCheckAndHandling(bimename, karmozdOb, karmozdGhestDaem.getKhateSanad(), karmozdGhestDaem.getGhest(), karmozdGhestDaem.getTarefe(), karmozdGhestDaem, namayande, followKarmozdGhestMap, azTarikh, taTarikh);

            List<KarmozdGhest> kgList = new ArrayList<KarmozdGhest>();
            if (followKarmozdGhestMap.containsKey(namayande))
            {
                kgList = followKarmozdGhestMap.get(namayande);
                followKarmozdGhestMap.remove(namayande);
            }

            saveKarmozdGhest(karmozdGhestDaem);
            kgList.add(karmozdGhestDaem);
            followKarmozdGhestMap.put(namayande, kgList);
            kg.setKarmozdCodeMovaghat(karmozdGhestDaem);
        }
    }

    public void karmozdBeCodeMovaghatProcess(List<KarmozdGhest> kgCodeMovaghat,Karmozd karmozdOb, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap,String azTarikh, String taTarikh)
    {
        for(KarmozdGhest kg : kgCodeMovaghat)
        {
            Bimename bimename = kg.getGhest().getGhestBandi().getBimename();
            Namayande namayande= bimename.getPishnehad().getNamayande();
            KarmozdGhest karmozdGhestMovaghat = null;
            if(kg.getGhest().getCredebit().getPishnehad().getEstelam().getNahve_pardakht_hagh_bime().equals("yekja"))
            {
                karmozdGhestMovaghat = new KarmozdGhest(kg.getGhest().getCredebit(),karmozdOb,kg.getGhest(),kg.getTarefe());
                Integer bimeYear=DateUtil.getBimeYear(kg.getKarmozd().getCreatedDate(), bimename.getTarikhShorou());
                karmozdGhestMovaghat.setKarmozdAmount(KarmozdCalculate.calKarmozdForKarmozdGhestYekjayeMovaghat(karmozdGhestMovaghat, karmozdGhestMovaghat.getTarefe(), bimeYear));
                karmozdGhestMovaghat.setSaleBimeei(kg.getSaleBimeei());
            }
            else
            {
                Elhaghiye elhaghieT = bimename.getElTaghiratToFirstYear();
                Long karmozdReal=null;
                if (elhaghieT != null && DateUtil.isGreaterThanOrEqual(taTarikh, elhaghieT.getCreatedDate()))
                {
                    karmozdReal= KarmozdCalculate.calRealKarmozdForGhest(kg.getGhest().getCredebit(),kg.getTarefe(),null);
                    if (kg.getGhest().getPreKarmozdReal() == null)
                    {
                        kg.getGhest().setPreKarmozdReal(kg.getGhest().getKarmozdReal());
                        kg.getGhest().setKarmozdReal(karmozdReal);
                        update(kg.getGhest());
                    }
                }
                karmozdGhestMovaghat = new KarmozdGhest(kg.getKhateSanad(), karmozdOb, kg.getGhest(), kg.getTarefe());
                karmozdGhestMovaghat.setKarmozdAmount(KarmozdCalculate.calKarmozdForKarmozdGhest(karmozdGhestMovaghat, karmozdGhestMovaghat.getTarefe(), karmozdGhestMovaghat.getGhest().getCredebit().getPishnehad(), null,karmozdReal));
            }
            karmozdGhestMovaghat.setNamayandeId(namayande.getId());
            karmozdGhestMovaghat.setTarefe(kg.getTarefe());
            karmozdGhestMovaghat.setType(KarmozdGhest.Type.PARDAKHTE_CODE_MOVAGHATI);

            ebtlOrBzkhrdCheckAndHandling(bimename, karmozdOb, karmozdGhestMovaghat.getKhateSanad(), karmozdGhestMovaghat.getGhest(), karmozdGhestMovaghat.getTarefe(), karmozdGhestMovaghat, namayande, followKarmozdGhestMap, azTarikh, taTarikh);

            List<KarmozdGhest> kgList = new ArrayList<KarmozdGhest>();
            if (followKarmozdGhestMap.containsKey(namayande))
            {
                kgList = followKarmozdGhestMap.get(namayande);
                followKarmozdGhestMap.remove(namayande);
            }

            saveKarmozdGhest(karmozdGhestMovaghat);
            kgList.add(karmozdGhestMovaghat);
            followKarmozdGhestMap.put(namayande, kgList);
            kg.setKarmozdCodeMovaghat(karmozdGhestMovaghat);
        }
    }

    public void karmozdEbtalOrBazkharidProcess(List<KarmozdGhest> kgBzkhrdOrEbtlShde,Karmozd karmozdOb, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap)
    {
        for(KarmozdGhest kg : kgBzkhrdOrEbtlShde)
        {
            Namayande namayande = kg.getKarmozdNamayande().getNamayande();
            KarmozdGhest karmozdGhestBargashti = new KarmozdGhest(kg.getKhateSanad(),karmozdOb,kg.getGhest(),kg.getTarefe());
            karmozdGhestBargashti.setKarmozdAmount(-KarmozdCalculate.calKarmozdBargashtiEbtalOrBazkharid(kg));
            karmozdGhestBargashti.setTarefe(kg.getTarefe());
            karmozdGhestBargashti.setNamayandeId(kg.getKhateSanad().getBedehiCredebit().getPishnehad().getNamayande().getId());
            karmozdGhestBargashti.setType(KarmozdGhest.Type.KARMOZD_BARGASHTI);
//            followKarmozdGhestList.add(karmozdGhestBargashti);
            List<KarmozdGhest> kgBargashtiList = new ArrayList<KarmozdGhest>();
            if (followKarmozdGhestMap.containsKey(namayande))
            {
                kgBargashtiList = followKarmozdGhestMap.get(namayande);
                followKarmozdGhestMap.remove(namayande);
            }
            saveKarmozdGhest(karmozdGhestBargashti);
            kgBargashtiList.add(karmozdGhestBargashti);
            followKarmozdGhestMap.put(namayande, kgBargashtiList);

            kg.setKarmozdBargashti(karmozdGhestBargashti);
        }
    }

    public void karmozdTaghiratProcess(List<KarmozdGhest> kgTaghirat,Karmozd karmozdOb, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap,String azTarikh,String taTarikh)
    {
        for(KarmozdGhest kg : kgTaghirat)
        {
            Namayande namayande = kg.getKarmozdNamayande().getNamayande();
            KarmozdGhest karmozdGhestTgr = new KarmozdGhest(kg.getKhateSanad(),karmozdOb,kg.getGhest(),kg.getTarefe());

            Long karmozdReal = null;
            if (kg.getGhest().getPreKarmozdReal() == null)
            {
                karmozdReal = KarmozdCalculate.calRealKarmozdForGhest(kg.getGhest().getCredebit(), kg.getTarefe(), null);
                kg.getGhest().setPreKarmozdReal(kg.getGhest().getKarmozdReal());
                kg.getGhest().setKarmozdReal(karmozdReal);
                update(kg.getGhest());
            }

            karmozdGhestTgr.setKarmozdAmount(KarmozdCalculate.calKarmozdTgr(kg));
            karmozdGhestTgr.setTarefe(kg.getTarefe());
            karmozdGhestTgr.setNamayandeId(kg.getKarmozdNamayande().getNamayande().getId());
            karmozdGhestTgr.setType(KarmozdGhest.Type.TAGHIRAT);

            List<KarmozdGhest> kgTgrList = new ArrayList<KarmozdGhest>();
            if (followKarmozdGhestMap.containsKey(namayande))
            {
                kgTgrList = followKarmozdGhestMap.get(namayande);
                followKarmozdGhestMap.remove(namayande);
            }

            Bimename b = karmozdGhestTgr.getGhest().getGhestBandi().getBimename();

            ebtlOrBzkhrdCheckAndHandling(b, karmozdOb, karmozdGhestTgr.getKhateSanad(), karmozdGhestTgr.getGhest(), karmozdGhestTgr.getTarefe(), karmozdGhestTgr, namayande, followKarmozdGhestMap, azTarikh, taTarikh);
            saveKarmozdGhest(karmozdGhestTgr);
            kgTgrList.add(karmozdGhestTgr);
            followKarmozdGhestMap.put(namayande, kgTgrList);
            kg.setKarmozdTaghirat(karmozdGhestTgr);
        }
    }

    public void karmozdDoubleTaghiratProcess(List<KarmozdGhest> kgDoubleTaghirat,Karmozd karmozdOb, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap,String azTarikh,String taTarikh)
    {
        for(KarmozdGhest kg : kgDoubleTaghirat)
        {
            Namayande namayande = kg.getKarmozdNamayande().getNamayande();
            KarmozdGhest karmozdGhestTgr = new KarmozdGhest(kg.getKhateSanad(),karmozdOb,kg.getGhest(),kg.getTarefe());

            Long karmozdReal = null;
            if (kg.getGhest().getPreKarmozdReal() == null)
            {
                karmozdReal = KarmozdCalculate.calRealKarmozdForGhest(kg.getGhest().getCredebit(), kg.getTarefe(), null);
                kg.getGhest().setPreKarmozdReal(kg.getGhest().getKarmozdReal());
                kg.getGhest().setKarmozdReal(karmozdReal);
                update(kg.getGhest());
            }

            karmozdGhestTgr.setKarmozdAmount(KarmozdCalculate.calKarmozdTgr(kg));
            karmozdGhestTgr.setTarefe(kg.getTarefe());
            karmozdGhestTgr.setNamayandeId(kg.getKarmozdNamayande().getNamayande().getId());
            karmozdGhestTgr.setType(KarmozdGhest.Type.TAGHIRAT);

            List<KarmozdGhest> kgTgrList = new ArrayList<KarmozdGhest>();
            if (followKarmozdGhestMap.containsKey(namayande))
            {
                kgTgrList = followKarmozdGhestMap.get(namayande);
                followKarmozdGhestMap.remove(namayande);
            }

            Bimename b = karmozdGhestTgr.getGhest().getGhestBandi().getBimename();

            ebtlOrBzkhrdCheckAndHandling(b, karmozdOb, karmozdGhestTgr.getKhateSanad(), karmozdGhestTgr.getGhest(), karmozdGhestTgr.getTarefe(), karmozdGhestTgr, namayande, followKarmozdGhestMap, azTarikh, taTarikh);
            saveKarmozdGhest(karmozdGhestTgr);
            kgTgrList.add(karmozdGhestTgr);


            KarmozdGhest oldKgTgr=kg.getKarmozdTaghirat();
            KarmozdGhest kgTgrBargasht= new KarmozdGhest(kg.getKhateSanad(), karmozdOb, kg.getGhest(), kg.getTarefe());
            kgTgrBargasht.setKarmozdAmount(0-oldKgTgr.getKarmozdAmount());
            kgTgrBargasht.setTarefe(kg.getTarefe());
            kgTgrBargasht.setNamayandeId(kg.getKarmozdNamayande().getNamayande().getId());
            kgTgrBargasht.setType(KarmozdGhest.Type.TAGHIRAT);
            saveKarmozdGhest(kgTgrBargasht);

            oldKgTgr.setKarmozdTaghirat(kgTgrBargasht);
            saveKarmozdGhest(oldKgTgr);
            kgTgrList.add(kgTgrBargasht);
            followKarmozdGhestMap.put(namayande, kgTgrList);
            kg.setKarmozdTaghirat(karmozdGhestTgr);
        }
    }

//    public boolean createKarmozdProject(String azTarikhVosoli, String taTarikhVosoli, User user,Karmozd karmozd)
//    {
//        List<Ghest> ghestList = findGhestsByTarikhVosoli(azTarikhVosoli, taTarikhVosoli);
//        List<Ghest> ghestListYekja = findGhestsYekja(azTarikhVosoli, taTarikhVosoli);
//        List<KarmozdGhest> kgBzkhrdOrEbtlShde = findKarmozdGhestsBazkharidOrEbtal(azTarikhVosoli,taTarikhVosoli);
//        List<KarmozdGhest> kgCodeMovaghat = findKarmozdGhestsCodeMovaghat(azTarikhVosoli,taTarikhVosoli);
//        List<KarmozdGhest> kgTaghirat = null;
//        List<BlackList> blackLists = loadAllBlackList();
//
//        if(ghestList.size()>0)// || ghestListYekja.size()>0)
//        {
//            Karmozd karmozdOb=new Karmozd();
//            karmozdOb.setUser(user);
//            karmozdOb.setCreatedDate(DateUtil.getCurrentDate());
//            karmozdOb.setAzTarikhVosoli(azTarikhVosoli);
//            karmozdOb.setTaTarikhVosoli(taTarikhVosoli);
//            karmozdOb.setDescription(karmozd.getDescription());
//            karmozdOb.setOnvan(karmozd.getOnvan());
//            String az = azTarikhVosoli.replaceAll("/","");
//            String ta = taTarikhVosoli.replaceAll("/","");
//            String str= az.substring(2,az.length()) + "/" + ta.substring(2,ta.length()) + "/";
//            String serial=getSerialKarmozd(str);
//            if(serial==null || serial.isEmpty())
//            {
//                serial = str + "01";
//            }
//            else
//            {
//                String newSerial=str;
//                int sequence=Integer.parseInt(serial.substring(serial.length()-2,serial.length()))+1;
//                if(Integer.toString(sequence).length()<2)
//                    newSerial += "0" + Integer.toString(sequence);
//                else
//                    newSerial += Integer.toString(sequence);
//                serial=newSerial;
//            }
//            karmozdOb.setSerial(serial);
//            save(karmozdOb);
//
//            List<Ghest> followGhestList=new ArrayList<Ghest>();
//            List<KarmozdGhest> followKarmozdGhestList=new ArrayList<KarmozdGhest>();
//
//            karmozdGheyreYekjaProcess(ghestList, azTarikhVosoli, taTarikhVosoli, followGhestList, karmozdOb, blackLists, followKarmozdGhestMap);
//            karmozdYekjaProcess(ghestListYekja, taTarikhVosoli, azTarikhVosoli, followGhestList, karmozdOb, blackLists, followKarmozdGhestList);
//            karmozdEbtalOrBazkharidProcess(kgBzkhrdOrEbtlShde,karmozdOb,followKarmozdGhestList);
//            karmozdBeCodeMovaghatProcess(kgCodeMovaghat,karmozdOb,followKarmozdGhestList);
//
//            asnadeSodorService.saveGhestList(followGhestList);
//            saveKarmozdGhestList(followKarmozdGhestList);
//            karmozdDAO.saveOrUpdateAll(kgBzkhrdOrEbtlShde);
//
//            List<KarmozdGhest> kgList=followKarmozdGhestList;
//            List<Integer> namayandeIdDistList= findDistinctNamayandeOfGhestByKarmozdId(karmozdOb.getId());
//            List<KarmozdGhest> followKgList=new ArrayList<KarmozdGhest>();
//            List<KarmozdNamayande> kanaList=new ArrayList<KarmozdNamayande>();
//            for(Integer namayandeIdDist : namayandeIdDistList)
//            {
//                List<KarmozdGhest> karmozdGhestList=new ArrayList<KarmozdGhest>();
//                long amount=0;
//                for(KarmozdGhest kg : kgList)
//                {
//                    Integer kgNamayandeId=0;
//                    if(kg.getGhest().getGhestBandi().getBimename().getPishnehad()!=null)
//                    {
//                        kgNamayandeId=kg.getGhest().getGhestBandi().getBimename().getPishnehad().getNamayande().getId();
//                        if(namayandeIdDist.equals(kgNamayandeId))
//                        {
//                            karmozdGhestList.add(kg);
//                            followKgList.add(kg);
//                            amount += kg.getKarmozdAmount();
//                        }
//                    }
//                }
//                if(amount!=0)
//                {
//                    KarmozdNamayande karmozdNamayande=new KarmozdNamayande();
//                    Namayande namayande=karmozdGhestList.get(0).getGhest().getGhestBandi().getBimename().getPishnehad().getNamayande();
//
//                    PaginatedListImpl<KarmozdNamayande> paginatedListKarmozdNamayande=new PaginatedListImpl<KarmozdNamayande>();
//                    paginatedListKarmozdNamayande.setPageNumber(0);
//                    paginatedListKarmozdNamayande.setObjectsPerPage(Integer.MAX_VALUE);
//                    paginatedListKarmozdNamayande = searchKarmozdNamayanade(paginatedListKarmozdNamayande, null, null, namayande.getName(), namayande.getId());
//                    long debits = 0;
////                    long credits = 0;
//                    for(KarmozdNamayande karnama : paginatedListKarmozdNamayande.getList())
//                    {
//                        if(karnama.getCredebit()!= null)
//                        {
//                            if(karnama.getCredebit().getCredebitType().equals(Credebit.CredebitType.BARGASHT_KARMOZD))
//                            {
//                                if(karnama.getCredebit().getRemainingAmount_long()>0)
//                                {
//                                    debits += karnama.getCredebit().getRemainingAmount_long();
//                                }
//                            }
////                            else //if (karnama.getCredebit().getCredebitType().equals(Credebit.CredebitType.PARDAKHT_KARMOZD)
////                            {
////                                if(karnama.getCredebit().getRemainingAmount_long()>0)
////                                {
////                                    credits += karnama.getCredebit().getRemainingAmount_long();
////                                }
////                            }
//                        }
//                    }
//                    karmozdNamayande.setBedehiAmount(debits);
//                    karmozdNamayande.setNamayande(namayande);
//                    karmozdNamayande.setState(KarmozdNamayande.State.ELAM_BE_MALI_NASHODE);
//                    karmozdNamayande.setKarmozd(karmozdOb);
//                    karmozdNamayande.setAmount(amount);
//                    karmozdNamayande.setKarmozdGhests(karmozdGhestList);
//
//                    kanaList.add(karmozdNamayande);
//                }
//            }
//            saveKarmozdNamayandeList(kanaList);
//            for(KarmozdGhest kg : followKgList)
//            {
//                Integer kgNamayandeId=0;
//                if(kg.getGhest().getGhestBandi().getBimename().getPishnehad()!=null)
//                {
//                    kgNamayandeId=kg.getGhest().getGhestBandi().getBimename().getPishnehad().getNamayande().getId();
//                    for(KarmozdNamayande kn : kanaList)
//                    {
//                        if(kn.getNamayande().getId().equals(kgNamayandeId))
//                        {
//                            kg.setKarmozdNamayande(kn);
//                            break;
//                        }
//                    }
//                }
//            }
//            saveKarmozdGhestList(followKgList);
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }

    public List findKarmozdGhestByKarmozdNamayandeId(Long karmozdNamayandeId)
    {
        return karmozdDAO.findKarmozdGhestByKarmozdNamayandeId(karmozdNamayandeId);
    }

    public void saveKarmozdNamayande(KarmozdNamayande kn)
    {
        karmozdDAO.saveOrUpdate(kn);
    }

    public KarmozdNamayande findKarmozdNamayandeForNamayande(Long karmozdId , Long namayandeId)
    {
        return karmozdDAO.findKarmozdNamayandeForNamayande(karmozdId,namayandeId);
    }

    public List<KarmozdGhest> findKarmozdGhestByBimenameId(Integer id, KarmozdNamayande.State state,Karmozd.Type karmozdType)
    {
        return karmozdDAO.findKarmozdGhestByBimenameId(id, state,karmozdType);
    }

    public PaginatedListImpl<BlackList> loadAllBlackListPaging(PaginatedListImpl<BlackList> pgList)
    {
        return karmozdDAO.loadAllBlackListPaging(pgList);
    }

    public BlackList findBlackListByBimenameId(Integer id,BlackList.Type type)
    {
        return karmozdDAO.findBlackListByBimenameId(id,type);
    }

    public  List<BlackList> loadAllBlackList(BlackList.Type type)
    {
        return karmozdDAO.loadAllBlackList(type);
    }

    public void addToBlackList(Bimename bimename,BlackList.Type type)
    {
        karmozdDAO.saveOrUpdateBlackList(bimename, type);
    }

    public KarmozdNamayande findKarmozdNamayandeById(Long id)
    {
        return karmozdDAO.findKarmozdNamayandeById(id);
    }

    public PaginatedListImpl<KarmozdNamayande> findKarmozdNamayandeByKarmozdId(PaginatedListImpl<KarmozdNamayande> pgList,Long karmozdId,String state)
    {
        return karmozdDAO.findKarmozdNamayandeByKarmozdId(pgList, karmozdId, state);
    }

    public String getSerialKarmozd(String str)
    {
        return karmozdDAO.getSerialKarmozd(str);
    }

    public PaginatedListImpl<KarmozdNamayande> searchKarmozdNamayanade(PaginatedListImpl<KarmozdNamayande> pgList,Long karmozdId, String state,String namayandeName,Long namayandeId)
    {
        return karmozdDAO.searchKarmozdNamayanade(pgList, karmozdId, state, namayandeName, namayandeId);
    }

    public void saveOrUpdateKarmozdNamayande(KarmozdNamayande karmozdNamayande)
    {
        karmozdDAO.saveOrUpdateKarmozdNamayande(karmozdNamayande);
    }

    public void saveKarmozdNamayandeList(List<KarmozdNamayande> knList)
    {
        karmozdDAO.saveKarmozdNamayandeList(knList);
    }

    public List<KhateSanad> findGhestsByTarikhVosoli(String azTarikhVosoli, String taTarikhVosoli)
    {
        return karmozdDAO.findGhestsByTarikhVosoli(azTarikhVosoli,taTarikhVosoli);
    }

    public List findKarmozdGhestsBazkharidOrEbtal(String azTarikh, String taTarikh)
    {
        return karmozdDAO.findKarmozdGhestsBazkharidOrEbtal(azTarikh, taTarikh);
    }

    public List<KarmozdGhest> findKarmozdGhestForTaghirat(String azTarikh, String taTarikh)
    {
        return karmozdDAO.findKarmozdGhestTaghirat(azTarikh,taTarikh);
    }

    public List<KarmozdGhest> findKarmozdGhestDoubleTaghirat(String azTarikh, String taTarikh)
    {
        return karmozdDAO.findKarmozdGhestDoubleTaghirat(azTarikh, taTarikh);
    }

    public List findKarmozdGhestsCodeMovaghat(String azTarikh, String taTarikh)
    {
        return karmozdDAO.findKarmozdGhestsMovaghat(azTarikh, taTarikh);
    }

    public List findKarmozdGhestsChangeNamayande(String azTarikh, String taTarikh)
    {
        return karmozdDAO.findKarmozdGhestsChangeNamayande(azTarikh, taTarikh);
    }

    public List<KhateSanad> findKhateSanadGYekjaForVosuliOrPoshesh(String azTarikhVosoli, String taTarikhVosoli, Karmozd.Type type)
    {
        return karmozdDAO.findKhateSanadGYekjaForVosuliOrPoshesh(azTarikhVosoli, taTarikhVosoli, type);
    }

    public List<Ghest> findGhestsYekjaForVosuliOrPoshesh(String azTarikhVosoli, String taTarikhVosoli, Karmozd.Type type)
    {
        return karmozdDAO.findGhestsYekjaForVosuliOrPoshesh(azTarikhVosoli,taTarikhVosoli,type);
    }

    public List findGhestsYekja(String azTarikhVosoli,String taTarikhVosoli)
    {
        return karmozdDAO.findGhestsYekja(azTarikhVosoli, taTarikhVosoli);
    }

    public List findKarmozdGhestByKarmozdId(Long karmozdId)
    {
        return karmozdDAO.findKarmozdGhestByKarmozdId(karmozdId);
    }

    public List findKarmozdGhestByKarmozdIdForNamayande(Long karmozdId,Integer namayandeId)
    {
        return karmozdDAO.findKarmozdGhestByKarmozdIdForNamayande(karmozdId,namayandeId);
    }

    public PaginatedListImpl<KarmozdGhest> findKarmozdGhestByKarmozdId(PaginatedListImpl<KarmozdGhest> pgList,Long karmozdId,Namayande n)
    {
        return karmozdDAO.findKarmozdGhestByKarmozdId(pgList,karmozdId,n);
    }

    public PaginatedListImpl<KarmozdGhest> searchKarmozdGhest(PaginatedListImpl<KarmozdGhest> pgList,Long karmozdId, String shomareMoshtari, String shenaseEtebar,String kodeNamayande,String shomareBimename)
    {
        return karmozdDAO.searchKarmozdGhest(pgList,karmozdId,shomareMoshtari,shenaseEtebar,kodeNamayande,shomareBimename);
    }

    public List findDistinctNamayandeOfGhestByKarmozdId(Long karmozdId)
    {
        return karmozdDAO.findDistinctNamayandeOfGhestByKarmozdId(karmozdId);
    }


    public PaginatedListImpl<Ghest> findGhestsByKarmozdId(PaginatedListImpl<Ghest> pgList,Long karmozdId)
    {

        return karmozdDAO.findGhestsByKarmozdId(pgList,karmozdId);
    }

    public PaginatedListImpl<Karmozd> findAllKarmozds(PaginatedListImpl<Karmozd> pgList,User user)
    {
        return karmozdDAO.findAllKarmozds(pgList,user);
    }

    public PaginatedListImpl findAllPaymentByKarmozdId(PaginatedListImpl pgList, long karmozdId, Namayande n,Karmozd.Type type)
    {
        return karmozdDAO.findAllPaymentByKarmozdId(pgList, karmozdId, n,type);
    }

    public Karmozd findById(Long id) {
        return karmozdDAO.findById(id);  //To change body of implemented methods use File | Settings | File Templates.

    }

    public KarmozdGhest findKarmozdGhestById(Long id)
    {
        return karmozdDAO.findKarmozdGhestById(id);
    }

    public List<Karmozd> findByNamayande(Integer namayandeId) {
        return karmozdDAO.findByNamayande(namayandeId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public KarmozdYekja findByIdYekja(Long id) {
        return karmozdDAO.findByIdYekja(id);
    }

    public List<KarmozdYekja> findByNamayandeYekja(Integer namayandeId) {
        return karmozdDAO.findByNamayandeYekja(namayandeId);
    }

    public KarmozdYekja findByBimenameYekja(Integer bimenameId) {
        return karmozdDAO.findByBimenameYekja(bimenameId);
    }

    public boolean isThereNotFinal()
    {
        return karmozdDAO.isThereNotFinal();
    }

    public void save(Karmozd k) {
        karmozdDAO.save(k);
    }

    public void update(Object o)
    {
        karmozdDAO.update(o);
    }

    public void saveKarmozdGhest(KarmozdGhest kg)
    {
        karmozdDAO.saveKarmozdGhest(kg);
    }

    public void saveKarmozdGhestList(List<KarmozdGhest> kgCollection)
    {
        karmozdDAO.saveOrUpdateAll(kgCollection);
    }

    public void saveOrUpdateAll(Collection col) {
        karmozdDAO.saveOrUpdateAll(col);
    }

    public void addKarmozedTadilatList(List<KarmozdTadilat> karmozdTadilatList) {
        karmozdDAO.saveOrUpdateAll(karmozdTadilatList);
    }

    public PaginatedListImpl<KarmozdTadilat> findKarmozedTadilat(PaginatedListImpl<KarmozdTadilat> pgList,Long karmozdId) {
        return karmozdDAO.findKarmozedTadilat(pgList,karmozdId);
    }

    public void pardakhtKarmozdForKarmozdNamayande(List<KarmozdNamayande> karmozdNamayandeList) {
        for (KarmozdNamayande kn : karmozdNamayandeList) {
            long knAmount = Long.parseLong(kn.getAmountForPaymentWithMaliat().replaceAll(",", "").trim());
            if (knAmount > 0) {
                Credebit credebit = new Credebit(Long.toString(knAmount), sequenceService.nextShenaseCredebit(), null, null, Credebit.CredebitType.PARDAKHT_KARMOZD);
                credebit.setStatus(Credebit.Status.SANAD_NA_KHORDE);
                asnadeSodorService.saveCredebit(credebit);

                kn.setState(KarmozdNamayande.State.ELAM_BE_MALI);
                kn.setCredebit(credebit);
                karmozdDAO.saveOrUpdateKarmozdNamayande(kn);

                if(kn.getKarmozd().getType().equals(Karmozd.Type.Karmozd_Pardakhti))
                {
                    List<KarmozdGhest> karmozdGhests = karmozdDAO.findKarmozdGhestByKarmozdNamayandeId(kn.getId());

                    for (KarmozdGhest kg : karmozdGhests) {
                        if (kg.getBlackList() == null && !kg.getType().equals(KarmozdGhest.Type.CODE_MOVAGHAT)) {
                            long karmozdPaid = 0l;
                            if (kg.getGhest().getKarmozdPaid() != null)
                                karmozdPaid = kg.getGhest().getKarmozdPaid() + kg.getKarmozdAmount(); //calculateKarmozd(kg);
                            else
                                karmozdPaid = kg.getKarmozdAmount(); //calculateKarmozd(kg);
                            kg.getGhest().setKarmozdPaid(karmozdPaid);
                            if(kg.getType().equals(KarmozdGhest.Type.TAGHIRAT))
                            {
                                kg.getGhest().setKarmozdTgr(kg.getGhest().getKarmozdTgr()!=null ? kg.getGhest().getKarmozdTgr()+kg.getKarmozdAmount() : kg.getKarmozdAmount());
                            }
                            asnadeSodorService.saveGhest(kg.getGhest());
                        }
                    }
                }
            } else if (knAmount < 0) {
                Credebit credebit = new Credebit(Long.toString(knAmount * (-1)), sequenceService.nextShenaseCredebit(), null, null, Credebit.CredebitType.BARGASHT_KARMOZD);
                credebit.setStatus(Credebit.Status.SANAD_NA_KHORDE);
                asnadeSodorService.saveCredebit(credebit);

                kn.setState(KarmozdNamayande.State.ELAM_BE_MALI);
                kn.setCredebit(credebit);
                karmozdDAO.saveOrUpdateKarmozdNamayande(kn);
            }
        }
    }

    public List<KarmozdGhest> findKarmozdGhestsForSenior(String azTarikh, String taTarikh)
    {
        return karmozdDAO.findKarmozdGhestsForSenior(azTarikh, taTarikh);
    }

    public void createKarmozdFromTadilat(Karmozd karmozd,String azTarikh,String taTarikh,Karmozd.Type type, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap)
    {
        List<KarmozdTadilat> tadilatList= karmozdDAO.findTadilatWithDate (type, azTarikh, taTarikh);
        for(KarmozdTadilat tadilat:tadilatList)
        {
            KarmozdGhest karmozdTadilat = new KarmozdGhest();
            karmozdTadilat.setKarmozd(karmozd);
            Namayande namayande= namayandeService.getNamayandeByKodeKargozar(tadilat.getXlCodeNamayande());
            karmozdTadilat.setNamayandeId(namayande.getId());
            karmozdTadilat.setKarmozdAmount(Long.parseLong(tadilat.getXlMablagh().replaceAll(",", "").trim()));
            karmozdTadilat.setDescription(tadilat.getXlSharh());
            karmozdTadilat.setType(KarmozdGhest.Type.TADILI);
            saveKarmozdGhest(karmozdTadilat);

            KarmozdGhest kg = tadilat.getKarmozdGhest();
            if (kg != null)
            {
                kg.setKarmozdTadili(karmozdTadilat);
                saveKarmozdGhest(kg);

                karmozdTadilat.setGhest(kg.getGhest());
                karmozdTadilat.setKhateSanad(kg.getKhateSanad());
                karmozdTadilat.setTarefe(kg.getTarefe());
                saveKarmozdGhest(karmozdTadilat);
            }
            List<KarmozdGhest> kgList = new ArrayList<KarmozdGhest>();
            if (followKarmozdGhestMap.containsKey(namayande))
            {
                kgList = followKarmozdGhestMap.get(namayande);
                followKarmozdGhestMap.remove(namayande);
            }
            kgList.add(karmozdTadilat);
            followKarmozdGhestMap.put(namayande, kgList);
            tadilat.setKarmozd(karmozd);
        }
        saveOrUpdateAll(tadilatList);
    }

    public void processCalcKarmozdSeniors(List<KarmozdGhest> kgForSeniorKarmozd, Karmozd karmozdOb, HashMap<Namayande, List<KarmozdGhest>> followKarmozdGhestMap)
    {
        for (KarmozdGhest kg : kgForSeniorKarmozd)
        {
            Namayande namayande = kg.getKarmozdNamayande().getNamayande();
            Namayande senior = namayande.getSenior();
            boolean isContinue = false;
            Bimename bimename = kg.getGhest().getGhestBandi().getBimename();
            for(SeniorSubset ss:senior.getSubsetList())
            {
                if(ss.getSubset().getId().equals(namayande.getId()))
                {
                    if(DateUtil.betweenEq(DateUtil.getCurrentDate(),ss.getContractDateFrom(),ss.getContractDateTo()))
                        isContinue=true;
                }
            }
            if (isContinue)
                continue;
            KarmozdGhest karmozdSenior = null;

            karmozdSenior = new KarmozdGhest();
            karmozdSenior.setCredebit(kg.getCredebit());
            karmozdSenior.setKarmozd(karmozdOb);
            karmozdSenior.setKhateSanad(kg.getKhateSanad());
            karmozdSenior.setGhest(kg.getGhest());
//            karmozdSenior.setTarefe(kg.getTarefe());
            karmozdSenior.setKarmozdAmount(KarmozdCalculate.calKarmozdSenior(kg.getKarmozdAmount() + (kg.getKarmozdTadili() == null ? 0l : kg.getKarmozdTadili().getKarmozdAmount())));

            karmozdSenior.setNamayandeId(senior.getId());
            karmozdSenior.setType(KarmozdGhest.Type.SENIOR);

            List<KarmozdGhest> kgList = new ArrayList<KarmozdGhest>();
            if (followKarmozdGhestMap.containsKey(senior))
            {
                kgList = followKarmozdGhestMap.get(senior);
                followKarmozdGhestMap.remove(senior);
            }

            saveKarmozdGhest(karmozdSenior);
            kgList.add(karmozdSenior);
            followKarmozdGhestMap.put(senior, kgList);
            kg.setKarmozdSenior(karmozdSenior);
        }
    }

    public void saveYekja(KarmozdYekja k) {
        karmozdDAO.saveYekja(k);
    }

    public void saveKarmozdGhestMap(HashMap<Namayande, List<KarmozdGhest>> kgMap) {
        for (List<KarmozdGhest> lkg :kgMap.values())
            karmozdDAO.saveKarmozdGhestList(lkg);
    }

    public KarmozdDAO getKarmozdDAO() {
        return karmozdDAO;
    }

    public void setKarmozdDAO(KarmozdDAO karmozdDAO) {
        this.karmozdDAO = karmozdDAO;
    }

    public ISequenceService getSequenceService() {
        return sequenceService;
    }

    public void setSequenceService(ISequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    public PaginatedListImpl<KarmozdNamayandeVM> getReportTashvighiList(PaginatedListImpl<KarmozdNamayandeVM> pgList,KarmozdNamayandeVM knSVM)
    {
        return karmozdDAO.findKarmozdNamayandeVMByKarmozdId(pgList, knSVM);
    }

    public String karmozdTashvighiStepOne(Karmozd karmozd,String azTarikh, String taTarikh)
    {
        return karmozdDAO.karmozdTashvighiStepOne(karmozd.getId(),azTarikh,taTarikh);
    }

    public void createKarmozdGhestTashvighi(Karmozd karmozd)
    {
        List<KarmozdTashvighiVosuliVM> khateSanads = karmozdDAO.getKhateSanadsOfEligibleNamayande(karmozd);
        for (KarmozdTashvighiVosuliVM khs : khateSanads)
        {
//            double vosul=0;
//            if (khs.getNahvePardakht().equals("yekja"))
//                vosul = AsnadeSodorService.getVosulYekja(khs.getTarikhSodurBimename(), khs.getTarh());
//            else
//                vosul = AsnadeSodorService.getVosulGyekja(khs.getTarikhSodurBimename(), khs.getTarh());
//            if (vosul >= 0.02)
//            {
                KarmozdGhest kgTashvighi = new KarmozdGhest(khs.getKhateSanad(), karmozd, khs.getGhest(), null);
                kgTashvighi.setKarmozdAmount(KarmozdCalculate.calKarmozdTashvighiVosuli(Long.parseLong(khs.getKhateSanad().getAmount().replace(",", "").trim())));
                kgTashvighi.setNamayandeId(khs.getNamayandeId());
                kgTashvighi.setType(KarmozdGhest.Type.KARMOZD_TASHVIGHI_VOSULI);
                kgTashvighi.setKarmozdNamayande(khs.getKarmozdNamayande());
                saveKarmozdGhest(kgTashvighi);
//            }
        }
    }

    public void setAmountTashvighiKarmozdNamayande(Karmozd karmozd)
    {
        karmozdDAO.setAmountTashvighiKarmozdNamayande(karmozd.getId());
    }
}
