package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.common.displaytag.PagingUtil;
import com.bitarts.common.util.DateUtil;
import com.bitarts.common.util.StringUtil;
import com.bitarts.parsian.Core.Constant;
import com.bitarts.parsian.model.*;
import com.bitarts.parsian.model.asnadeSodor.*;
import com.bitarts.parsian.model.karmozd.BlackList;
import com.bitarts.parsian.model.karmozd.Karmozd;
import com.bitarts.parsian.model.karmozd.KarmozdGhest;
import com.bitarts.parsian.model.karmozd.KarmozdNamayande;
import com.bitarts.parsian.service.*;
import com.bitarts.parsian.service.karmozd.IKarmozdService;
import com.bitarts.parsian.util.OmrUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 4/20/11
 * Time: 6:28 PM
 */
public class StrutsFileUpload extends BaseAction implements ServletContextAware {
    private IClinicService clinicService;
    private ISequenceService sequenceService;
    private ILoginService loginService;
    private IAsnadeSodorService asnadeSodorService;
    private INamayandeService namayandeService;
    private IKarmozdService karmozdService;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String fileCaption;
    private Bargozarandeh bargozar;
    private List<Sanad> sanads;
    private PaginatedListImpl<BankInfo> bankinfos;
    private String bankMaghsad;
    private String realPath = "";
//    private User user;
    private boolean nosession;
    private BankInfo bankInfo;
    private String shouldSabt;
    private long serialBankInfo;
    private String page;
    private Boolean hasErrorRow;
    private Integer pageNumber;
//    private PaginatedListImpl<KhateSanad> khateSanadListPaginated;
//
//    public PaginatedListImpl<KhateSanad> getKhateSanadListPaginated()
//    {
//        return khateSanadListPaginated;
//    }
//
//    public void setKhateSanadListPaginated(PaginatedListImpl<KhateSanad> khateSanadListPaginated)
//    {
//        this.khateSanadListPaginated = khateSanadListPaginated;
//    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setServletContext(ServletContext servletContext) {
        realPath = servletContext.getRealPath("/");
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        this.clinicService = (IClinicService) wac.getBean(IClinicService.SERVICE_NAME);
        this.loginService = (ILoginService) wac.getBean(ILoginService.SERVICE_NAME);
        this.asnadeSodorService = (IAsnadeSodorService) wac.getBean(IAsnadeSodorService.SERVICE_NAME);
        this.karmozdService = (IKarmozdService) wac.getBean((IKarmozdService.SERVICE_NAME));
        this.namayandeService = (INamayandeService) wac.getBean(INamayandeService.SERVICE_NAME);
        this.sequenceService = (ISequenceService) wac.getBean(ISequenceService.SERVICE_NAME);
    }

    public List<String[]> getExcelContent() throws Exception {
        List<String[]> excelContent = new ArrayList<String[]>();
        try {
            String fullFileName = realPath + "content/upload/" + uploadFileName;
            File theFile = new File(fullFileName);
            FileUtils.copyFile(upload, theFile);
            Workbook w = Workbook.getWorkbook(theFile);
            Sheet sheet = w.getSheet(0);
            int rows = sheet.getRows();
            int clos = sheet.getColumns();
            for (int i = 0; i < rows; i++) {
                String[] theRow = new String[clos];
                for (int j = 0; j < clos; j++) {
                    Cell cell = sheet.getCell(j, i);
                    if (cell != null && cell.getContents() != null) {
                        theRow[j] = cell.getContents();
                    } else {
                        theRow[j] = "";
                    }
                }
                excelContent.add(theRow);
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            throw new Exception();
        }
        return excelContent;
    }

    private Karmozd karmozd;

    public String doUploadForKarmozdTadilat() {
        try {
            karmozd = karmozdService.findById(karmozd.getId());

            PaginatedListImpl<KarmozdNamayande> knPgList = new PaginatedListImpl<KarmozdNamayande>();
            knPgList.setPageNumber(0);
            knPgList.setObjectsPerPage(Integer.MAX_VALUE);
            List<KarmozdNamayande> knList = karmozdService.findKarmozdNamayandeByKarmozdId(knPgList, karmozd.getId(), null).getList();
            List<BlackList> blackLists = karmozdService.loadAllBlackList(karmozd.getType().equals(Karmozd.Type.Karmozd_Vosuli) ? BlackList.Type.KARMOZD_VOSULI : BlackList.Type.KARMOZD_PARDAKHTI);

            if (karmozd == null) return ERROR;
            List<String[]> excelContent = getExcelContent();
            List<KarmozdGhest> karmozdTadilatList = new ArrayList<KarmozdGhest>();
            Iterator<String[]> excelContentItrIterator = excelContent.iterator();
            if (!excelContentItrIterator.hasNext()) {
                addActionError("فايل آپلود شده خالي بود.");
                return SUCCESS;
            }
            excelContentItrIterator.next();

            while (excelContentItrIterator.hasNext()) {
                String[] row = excelContentItrIterator.next();

//                KarmozdTadilat karmozdTadilat = new KarmozdTadilat(karmozd, row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]);
                KarmozdGhest karmozdTadilat = new KarmozdGhest();
                karmozdTadilat.setKarmozd(karmozd);
                karmozdTadilat.setNamayandeId(namayandeService.getNamayandeByKodeKargozar(row[1]).getId());
                karmozdTadilat.setKarmozdAmount(Long.parseLong(row[3].replaceAll(",", "").trim()));
                karmozdTadilat.setDescription(row[7]);
                karmozdTadilat.setType(KarmozdGhest.Type.TADILI);


                for (KarmozdNamayande kn : knList) {
                    if (karmozdTadilat.getNamayandeId().equals(kn.getNamayande().getId())) {
                        karmozdTadilat.setKarmozdNamayande(kn);
                    }
                }
                karmozdService.saveKarmozdGhest(karmozdTadilat);
                if (row[8] != null) {
                    KarmozdGhest kg = karmozdService.findKarmozdGhestById(Long.parseLong(row[8]));
                    if (kg != null) {
                        kg.setKarmozdTadili(karmozdTadilat);
                        karmozdService.saveKarmozdGhest(kg);
                        karmozdTadilat.setGhest(kg.getGhest());
                        karmozdTadilat.setKhateSanad(kg.getKhateSanad());
                        if (!karmozd.getType().equals(Karmozd.Type.Karmozd_Seniors))
                            karmozdTadilat.setTarefe(kg.getTarefe());
                        karmozdService.saveKarmozdGhest(karmozdTadilat);
                    }
                }
                karmozdTadilatList.add(karmozdTadilat);
            }
            if (karmozdTadilatList.isEmpty()) {
                addActionError("فايل آپلود شده خالي بود.");
                return SUCCESS;
            }

            for (KarmozdGhest kt : karmozdTadilatList) {

                if (kt.getKarmozdNamayande() == null) {
                    Namayande n = namayandeService.getNamayandeById(kt.getNamayandeId());
                    if (n != null) {
                        KarmozdNamayande karNama = karmozdService.findKarmozdNamayandeForNamayande(karmozd.getId(), n.getId());
                        if (karNama != null) {
                            kt.setKarmozdNamayande(karNama);
                            karNama.setAmount(karNama.getAmount() + kt.getKarmozdAmount());
                        } else {
                            karNama = new KarmozdNamayande();
                            karNama.setKarmozd(karmozd);
                            karNama.setAmount(kt.getKarmozdAmount());
                            karNama.setState(KarmozdNamayande.State.ELAM_BE_MALI_NASHODE);
                            karNama.setNamayande(n);
                            karmozdService.saveKarmozdNamayande(karNama);
                            kt.setKarmozdNamayande(karNama);
                        }
                    }
                }
            }

            karmozdService.saveKarmozdGhestList(karmozdTadilatList);
            addActionMessage("فايل با موفقيت آپلود شد.");
        } catch (Exception e) {
            addActionError("خطا");
        }
        return SUCCESS;
    }

    public Integer processOmrFileUpload(BankInfo bankInfo, String shomareFish, String kodeShobe, String tarikhFish, String codeMoshtari, String amount, Credebit credebitsByCodeMoshtari) {

        if ((bankMaghsad.equals("17038494") || bankMaghsad.equals("0185007111")) && !OmrUtil.getShomareHesabTejarat(codeMoshtari).equals(bankMaghsad))
        {
            bankInfo.setStateId(-2);
            bankInfo.setStatus(BankInfo.BankInfoStatus.NOT_MATCH_SHOMARE_HESAB);
//      chon mablagh ra check mikonim, tekrari upload nemishe. in control rooye upload tarhhaye jami moshkelsaz shode.
//        }
//        else if ((clinicService.doesBankInfoExist(shomareFish, kodeShobe, tarikhFish))) {
//            bankInfo.setStateId(-2);
//            bankInfo.setStatus(BankInfo.BankInfoStatus.TEKRARI);
//            shouldSabt = "no";
        } else {
            Long amountParamLong = Long.parseLong(amount.replaceAll(",", "").trim());
            if (credebitsByCodeMoshtari == null) {
                bankInfo.setStatus(BankInfo.BankInfoStatus.NO_GHEST);
                bankInfo.setStateId(-2);
            } else if (credebitsByCodeMoshtari.getRemainingAmount_long() > amountParamLong) {
                bankInfo.setStatus(BankInfo.BankInfoStatus.AGHSAT_OMR_MABLAGH_KAMTAR);
                bankInfo.setStateId(-3);
            } else if (credebitsByCodeMoshtari.getRemainingAmount_long() < amountParamLong) {
                bankInfo.setStatus(BankInfo.BankInfoStatus.AGHSAT_OMR_MABLAGH_MAZAD);
                bankInfo.setStateId(-3);
            } else {
                bankInfo.setStatus(BankInfo.BankInfoStatus.Z_AGHSAT_OMR);
                bankInfo.setStateId(-1);
            }
        }
        return 0;
    }

    public Integer processPishPardakhtOmrFileUpload(BankInfo bankInfo, String shomareFish, String kodeShobe, String tarikhFish) {
        if ((clinicService.doesBankInfoExist(shomareFish, kodeShobe, tarikhFish))) {
            bankInfo.setStateId(-2);
            bankInfo.setStatus(BankInfo.BankInfoStatus.TEKRARI);
            shouldSabt = "no";
        } else {
            bankInfo.setStatus(BankInfo.BankInfoStatus.Z_PISH_PARDAKHT_OMR);
            bankInfo.setStateId(-1);
        }

        return 0;
    }

    public Integer processTaeedVosolFileUpload(BankInfo bankInfo, String codeMoshtari, String amount, String excelDate, String excelTime) {
        Credebit credebitsByCodeMoshtari = asnadeSodorService.findPardakhtShenaseDarCredebitByCodeMoshtari(extractCodeMoshtariFromDesc(codeMoshtari));
        if(credebitsByCodeMoshtari==null)
            credebitsByCodeMoshtari=asnadeSodorService.findCredebitDaryafteCheckByCodeMoshtari(extractCodeMoshtariFromDesc(codeMoshtari));
        if (credebitsByCodeMoshtari != null) {
            Credebit credebit = processCredebitFileUpload(credebitsByCodeMoshtari, bankInfo, amount, excelDate, excelTime);
            if (credebit != null) {
                bankInfo.setStateId(-4);
                shouldSabt = "no";
            } else {
                bankInfo.setStateId(-3);
                shouldSabt = "no";
            }
        } else {
            bankInfo.setStateId(-2);
            bankInfo.setStatus(BankInfo.BankInfoStatus.VOSOL_TEKRARI_GHEYRE_MOJAZ);
            shouldSabt = "no";
        }
        return 0;
    }

    @Transactional
    private void setBankInformationForBargozar(String bankName, String kodeShobe, String nameShobe, String shomareHesab, User user) {
        bargozar.setNameBank(bankName);
        bargozar.setKodeShobe(kodeShobe);
        bargozar.setNameShobe(nameShobe);
        bargozar.setShomareHesab(shomareHesab);
        bargozar.setUser(user);
//        clinicService.addNewBargozar(bargozar);
    }

    public Credebit processCredebitFileUpload(Credebit credebit, BankInfo bankInfo, String amount, String excelDate, String excelTime) {
        Long amountParamLong = Long.parseLong(amount.replaceAll(",", "").trim());

        if (credebit != null) {
            bankInfo.setCredebit(credebit);
            if (credebit.getVosoulState() != null && credebit.getVosoulState().equals(Credebit.VaziyatVosoul.TAEED_VOSOUL)) {
                if (credebit.getBankInfoList() != null && credebit.getBankInfoList().size() > 0) {
                    boolean isTekrari = false;
                    for (BankInfo bankInfo1 : credebit.getBankInfoList())
                        if ((bankInfo1.getTaarikh() != null && bankInfo1.getTaarikh().equals(excelDate)) && (bankInfo1.getTime() != null && bankInfo1.getTime().equals(excelTime))) {
                            isTekrari = true;
                        }
                    if (!isTekrari) {
                        bankInfo.setStatus(BankInfo.BankInfoStatus.VOSOL_TEKRARI);
                        return credebit;
                    }
                }
            } else {
                if (amount != null) {
                    if (credebit.getAmount_long().equals(amountParamLong)) {
                        bankInfo.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MOSAVI);
                        return credebit;
                    } else if (credebit.getAmount_long() < amountParamLong) {
                        bankInfo.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD);
                        return credebit;
                    } else if (credebit.getAmount_long() > amountParamLong) {
                        bankInfo.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_KAMTAR);
                        return credebit;
                    }
                }
            }
        }
        bankInfo.setStatus(BankInfo.BankInfoStatus.VOSOL_TEKRARI_GHEYRE_MOJAZ);
        return null;
    }
     //b-h   read bank excel file
    public String readExcel(){
        boolean isPaging = false;
        Integer pageNumber = 0;

        if (isExport()) {
            bankinfos = new PaginatedListImpl<BankInfo>();
            bankinfos = PagingUtil.getPaginatedList((List<BankInfo>) getFromSession("bankInfoExcel"));
            bankinfos.setPageNumber(0);
            bankinfos.setObjectsPerPage(Integer.MAX_VALUE);
            return SUCCESS;
        }

        if (page != null) {
            pageNumber = Integer.parseInt(page) - 1;
            isPaging = true;
        }

        if (isPaging) {
//            serialBankInfo = (Long) getSession().get("serialBankInfo");
            bankinfos = new PaginatedListImpl<BankInfo>();
            bankinfos.setPageNumber(pageNumber);
            bankinfos.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
//            bankinfos = PagingUtil.getPaginatedList(clinicService.loadBankInfosBySerial(serialBankInfo,null));
            bankinfos = PagingUtil.getPaginatedList((List<BankInfo>) getFromSession("bankInfoExcel"));
        } else {
           Map<Credebit,List<BankInfo>> mapOfCredebits=new HashMap<Credebit, List<BankInfo>>();
           bargozar=new Bargozarandeh();
           bargozar.setTaarikh(DateUtil.getCurrentDate());
            String username=SecurityContextHolder.getContext().getAuthentication().getName();
            User user=loginService.findUserByUsername(username);
            setBankInformationForBargozar("پارسیان", "1001", "میرداماد شرقی", "0201136462000", user);
            Daftar daftar=asnadeSodorService.findDaftarById(user.getDaftar().getId());
            bargozar.setDaftar(daftar);
            List<BankInfo> bankInfoList=new ArrayList<BankInfo>();
            try {
                //read excel
                String fullFileName = realPath + "content/upload/" + uploadFileName;
                File theFile = new File(fullFileName);
                FileUtils.copyFile(upload, theFile);
                Workbook w = Workbook.getWorkbook(theFile);
                Sheet sheet = w.getSheet(0);

                hasErrorRow = false;
                long bankInfoSerialId = sequenceService.getBankInfoSerialId();

                for (int i = 1; i < sheet.getRows(); i++) {
                    BankInfo bankInfo=new BankInfo();
                    Cell cell0i = sheet.getCell(0, i);
                    Cell cell1i = sheet.getCell(1, i);
                    Cell cell2i = sheet.getCell(2, i);

                    String cell2Str = "";
                    if (cell2i.getContents() != null && cell2i.getContents().length() > 0) {
                        cell2Str = cell2i.getContents().substring(0, cell2i.getContents().length() - 4) +
                            "/" + cell2i.getContents().substring(cell2i.getContents().length() - 4, cell2i.getContents().length() - 2) +
                            "/" + cell2i.getContents().substring(cell2i.getContents().length() - 2, cell2i.getContents().length());
                    }
                    Cell cell3i = sheet.getCell(3, i);
                    Cell cell4i = sheet.getCell(4, i);
                    Cell cell5i = sheet.getCell(5, i);
                    Cell cell6i = sheet.getCell(6, i);
                    Cell cell7i = sheet.getCell(7, i);
                    Cell cell8i = sheet.getCell(8, i);
                    //اگر كد مشتري يا مبلغ خالي باشد آن سطر ناديده گرفته مي شود
                    if (cell0i.getContents().equals("") && cell2Str.equals("") && cell4i.getContents().equals(""))
                        continue;

                    bankInfo.setCreatedTime(DateUtil.getCurrentTime());
                    bankInfo.setCreatedDate(DateUtil.getCurrentDate());
                    bankInfo.setKodeDaryaft(cell1i.getContents());
                    bankInfo.setShomareFish(cell0i.getContents());
                    bankInfo.setDesc(cell2i.getContents() );
                    bankInfo.setBardasht(cell3i.getContents());
                    bankInfo.setMablagh(cell4i.getContents());

                    //set kardan mablagh
                    if (bankInfo.getMablagh().indexOf(".") > -1)
                      bankInfo.setMablagh(cell4i.getContents().substring(0, cell4i.getContents().indexOf(".")).replaceAll(",", ""));

                    //validation sakhtar mablagh
                    if (!StringUtil.isNumber(bankInfo.getMablagh())) {
                        bankInfo.setStatus(BankInfo.BankInfoStatus.INVALID_AMOUNT);
                        bankInfo.setStateId(-10);
                        //shouldSabt = "no";
                        hasErrorRow=true;
                    }
                    bankInfo.setKodeShobe(cell5i.getContents());
                    bankInfo.setTime(cell6i.getContents());
                    bankInfo.setTaarikh(StringUtil.unifyDateFormat(StringUtil.toAnsii(cell7i.getContents())));
                    //validate tarikh vosoul
                    if (!bankInfo.getTaarikh().matches("\\d{4}/\\d{2}/\\d{2}") || !bankInfo.getTime().matches("\\d{1,2}:\\d{2}:\\d{2}")) {
                        bankInfo.setStatus(BankInfo.BankInfoStatus.INVALID_DATE);
                        bankInfo.setStateId(-9);
                   // shouldSabt = "no";
                        hasErrorRow=true;
                    }

                    bankInfo.setIdUploaded(cell8i.getContents());
                    bankInfo.setCodeMoshtari(extractCodeMoshtariFromDesc(StringUtil.toAnsii(bankInfo.getDesc())));
                    bankInfo.setSerialId(bankInfoSerialId);
                    bankInfo.setBargozarandeh(bargozar);

                //peida kardan recordhaye tekrari va recordhayi k etebar baraye anha vojood nadarad
                    findRecordhayeTekrariVaGheirMojaz(bankInfo,user);

                    if(bankInfo.getCredebit() !=null){
                        if(bankInfo.getStatus()==null || !bankInfo.getStatus().equals(BankInfo.BankInfoStatus.VOSOL_TEKRARI)){
                            if(mapOfCredebits.containsKey(bankInfo.getCredebit())){
                                List<BankInfo> currentBankInfos=mapOfCredebits.get(bankInfo.getCredebit());
                                currentBankInfos.add(bankInfo);
                                mapOfCredebits.put(bankInfo.getCredebit(),currentBankInfos);
                            }
                            else{
                                List<BankInfo> blist=new ArrayList<BankInfo>();
                                blist.add(bankInfo);
                                mapOfCredebits.put(bankInfo.getCredebit(),blist);
                            }
                        }
                        else
                            bankInfoList.add(bankInfo);
                    }
                    else{
                        bankInfoList.add(bankInfo);
                        hasErrorRow=true;
                    }
                }
            }
            catch(Exception e){
               e.printStackTrace();
               addActionError(e.getMessage());
               return SUCCESS;
            }
             // mapOfCredebits: be ezaye har credebit list bankinfos
            //  bankInfoList list kole bankinfos :felan faghat gheir mojaz ha dar aan rikhte shode
            if (hasErrorRow) {
                addActionError("این فایل حاوی سطرهایی با مقادیر نا معتبر است");
            }

            List<BankInfo> finalBankInfoListForVosoul=new ArrayList<BankInfo>();

            List<BankInfo> temp=SetBankInfosStatus(mapOfCredebits,finalBankInfoListForVosoul);
//            for(BankInfo b:temp)
//                System.out.println("mablagh2:"+b.getMablagh());
            bankInfoList.addAll(temp);
//            for(BankInfo b:bankInfoList)
//                System.out.println("mablagh3:"+b.getMablagh());
            putToSession("hasErrorRow", hasErrorRow);
         //   putToSession("serialBankInfo", serialBankInfo);
            putToSession("bargozar", bargozar);
            bankinfos = PagingUtil.getPaginatedList(bankInfoList);

            putToSession("bankInfoExcel", bankInfoList); //baraye namayesh dar khorouji
            putToSession("finalBankInfoListForVosoul",finalBankInfoListForVosoul);//baraye anjam amaliat(pivot shode)
            Map<String, Integer> typesReportMap = new HashMap<String, Integer>();
            for(BankInfo bankInfo1:bankInfoList){
                Integer countTemp = 0;
                if (typesReportMap.containsKey(bankInfo1.getVaziyateEtebarDesc())) {
                    countTemp = typesReportMap.get(bankInfo1.getVaziyateEtebarDesc());
                    countTemp++;
                    typesReportMap.remove(bankInfo1.getVaziyateEtebarDesc());
                    typesReportMap.put(bankInfo1.getVaziyateEtebarDesc(), countTemp);
                } else {
                    typesReportMap.put(bankInfo1.getVaziyateEtebarDesc(), 1);
                }
            }
            putToSession("typesReportMap", typesReportMap);  //kholase natayej barresi



     }
        return SUCCESS;
    }
    //b-h
    public void findRecordhayeTekrariVaGheirMojaz(BankInfo bankInfo,User user){
        if(bankInfo.getStatus()==null ){ //check baraye in ast k ghablan status invalid_amount ya invalid_date baraye bankinfo set nashode bashad
            Credebit credebit=asnadeSodorService.findAllCredebitsByCodeMoshtari(bankInfo.getCodeMoshtari(),user);
            if(credebit !=null){
                bankInfo.setCredebit(credebit);
                if(credebit.getVosoulState().equals(Credebit.VaziyatVosoul.TAEED_VOSOUL)){
                    if(credebit.getBankInfoList() !=null && credebit.getBankInfoList().size()>0)
                        for(BankInfo CredebitbankInfo:credebit.getBankInfoList())
                            if(CredebitbankInfo.getTaarikh().equals(bankInfo.getTaarikh()) && CredebitbankInfo.getTime().equals(bankInfo.getTime()))
                            {
                                bankInfo.setStatus(BankInfo.BankInfoStatus.VOSOL_TEKRARI);
                                bankInfo.setStateId(-4);
                                break;
                            }
                }

            }
            else{
                bankInfo.setStateId(-2);
                bankInfo.setStatus(BankInfo.BankInfoStatus.VOSOL_TEKRARI_GHEYRE_MOJAZ);
            }
        }
    }
    //b-h
    public List<BankInfo> SetBankInfosStatus(Map<Credebit,List<BankInfo>> credebitsList,List<BankInfo> finalBankInfoList){
        List<BankInfo> allBankInfos=new ArrayList<BankInfo>();

        for(Credebit credebit:credebitsList.keySet()){
            BankInfo sumOfBankInfos=new BankInfo();
            int totalAmount=0;
            Map<String,List<String>> uniqueRecords=new HashMap<String, List<String>>();
            for(BankInfo bankInfo1:credebitsList.get(credebit)) {
                boolean istekrari=false;
                //check kardan inke record tekrari dar file nabashad
                if(!uniqueRecords.containsKey(bankInfo1.getTaarikh())){
                    List<String> times=new ArrayList<String>();
                    times.add(bankInfo1.getTime());
                    uniqueRecords.put(bankInfo1.getTaarikh(),times);
                }
                else{
                    List<String> times=uniqueRecords.get(bankInfo1.getTaarikh());
                    istekrari=BankInfoRecordIsNotTekrari(times,bankInfo1.getTime());
                }
             //  if(bankInfo1.getStatus()==null || !bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.VOSOL_TEKRARI))
                if(!istekrari)
                    totalAmount+=Long.parseLong(bankInfo1.getMablagh().replaceAll(",","").trim());
                else {
                    bankInfo1.setStatus(BankInfo.BankInfoStatus.VOSOL_TEKRARI);
                    bankInfo1.setStateId(-4);
                }

            }
            if(!credebit.getVosoulState().equals(Credebit.VaziyatVosoul.TAEED_VOSOUL)) {
                if(credebit.getAmount_long()>totalAmount){
                    for(BankInfo bankInfo1:credebitsList.get(credebit)){
                        if(bankInfo1.getStatus()==null || !bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.VOSOL_TEKRARI)) {
                            bankInfo1.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_KAMTAR);
                            bankInfo1.setStateId(-4);
                        }

                         allBankInfos.add(bankInfo1);
                    }
                    sumOfBankInfos.setCodeMoshtari(credebitsList.get(credebit).get(0).getCodeMoshtari());
                    sumOfBankInfos.setMablagh(String.valueOf(totalAmount));
                    sumOfBankInfos.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_KAMTAR);
                    sumOfBankInfos.setTaarikh(credebitsList.get(credebit).get(0).getTaarikh());


                }
                else if(credebit.getAmount_long()<totalAmount) {
                    for(BankInfo bankInfo1:credebitsList.get(credebit)){
                        if(bankInfo1.getStatus()==null || !bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.VOSOL_TEKRARI)){
                            bankInfo1.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD);
                            bankInfo1.setStateId(-4);
                        }
                        allBankInfos.add(bankInfo1);

                    }
                    sumOfBankInfos.setCodeMoshtari(credebitsList.get(credebit).get(0).getCodeMoshtari());
                    sumOfBankInfos.setMablagh(String.valueOf(totalAmount));
                    sumOfBankInfos.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD);
                    sumOfBankInfos.setTaarikh(credebitsList.get(credebit).get(0).getTaarikh());
                }
                else {
                    for(BankInfo bankInfo1:credebitsList.get(credebit)){
                       if(bankInfo1.getStatus()==null || !bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.VOSOL_TEKRARI)){
                            bankInfo1.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MOSAVI);
                            bankInfo1.setStateId(-4);
                        }

                        allBankInfos.add(bankInfo1);
                    }
                    sumOfBankInfos.setCodeMoshtari(credebitsList.get(credebit).get(0).getCodeMoshtari());
                    sumOfBankInfos.setMablagh(String.valueOf(totalAmount));
                    sumOfBankInfos.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MOSAVI);
                    sumOfBankInfos.setTaarikh(credebitsList.get(credebit).get(0).getTaarikh());
                }
            }
            else{
                for(BankInfo bankInfo1:credebitsList.get(credebit)){
                    if(bankInfo1.getStatus()==null || !bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.VOSOL_TEKRARI)) {
                        bankInfo1.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD);
                        bankInfo1.setStateId(-4);
                    }
                    allBankInfos.add(bankInfo1);

                }
                sumOfBankInfos.setCodeMoshtari(credebitsList.get(credebit).get(0).getCodeMoshtari());
                sumOfBankInfos.setMablagh(String.valueOf(totalAmount+credebit.getAmount_long()));
                sumOfBankInfos.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD);
                sumOfBankInfos.setTaarikh(credebitsList.get(credebit).get(0).getTaarikh());
            }
            finalBankInfoList.add(sumOfBankInfos);
        }

        return allBankInfos;
    }
    public boolean BankInfoRecordIsNotTekrari(List<String> times,String recordTime){
         Iterator iterator=times.iterator();
        while(iterator.hasNext()){
            if(iterator.next().equals(recordTime))
                return true;
        }
        return false;
    }
    //b-h
    public String doTaeedVosoul(){
        Bargozarandeh bargozar=(Bargozarandeh) getFromSession("bargozar");
        if(bargozar != null)
            clinicService.addNewBargozar(bargozar);
        List<BankInfo> bankInfoList=(List<BankInfo>)getFromSession("bankInfoExcel");
        Iterator<BankInfo> iterator=bankInfoList.iterator();
        while(iterator.hasNext()){
            BankInfo bankInfo1= (BankInfo)iterator.next();
            if(bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.VOSOL_TEKRARI_GHEYRE_MOJAZ) || bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.INVALID_AMOUNT) || bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.INVALID_DATE))
                iterator.remove();
        }

        if(bankInfoList !=null && bankInfoList.size()>0)
            clinicService.saveBankInfos(bankInfoList);
        List<BankInfo> finalBankInfoList =(List<BankInfo>)getFromSession("finalBankInfoListForVosoul");
        Integer tedadVosouli=0;
        if(finalBankInfoList !=null && finalBankInfoList.size()>0){
            for(BankInfo bankInfo: finalBankInfoList){
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                User user = loginService.findUserByUsername(username);
                Credebit credebit=asnadeSodorService.findAllCredebitsByCodeMoshtari(bankInfo.getCodeMoshtari(),user);
                List<KhateSanad> EtebarkhateSanadList = asnadeSodorService.findAllKhateSanadByEtebarCredebitId(credebit.getId());
                if(bankInfo.getStatus().equals(BankInfo.BankInfoStatus.Z_MABLAGH_KAMTAR)){

                    if(EtebarkhateSanadList !=null && EtebarkhateSanadList.size()>0){
                        boolean  validEtebar=true;
                        for (KhateSanad khateSanad : EtebarkhateSanadList) {

                            if ( DateUtil.isGreaterThan(DateUtil.getCurrentDate(), DateUtil.addDaysWithTatilat(khateSanad.getSanad().getCreatedDate(), 3))) {
                                validEtebar = false;
                                break;
                            }
                        }
                        if(validEtebar){
                            for(KhateSanad khateSanad : EtebarkhateSanadList)
                                asnadeSodorService.deleteSanadForBankExcelFile(khateSanad.getSanad().getId());
                            credebit.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
                            credebit.setVosoulDate(bankInfo.getTaarikh());
                            credebit.setAmount_long(Long.parseLong(bankInfo.getMablagh().replaceAll(",","").trim()));
                            credebit.setRemainingAmount_long(Long.parseLong(bankInfo.getMablagh().replaceAll(",","").trim()));
                            tedadVosouli++;
                        }
                    }
                    else{   //etebar mablagh kamtar sanad nakhorde
                        credebit.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
                        credebit.setVosoulDate(bankInfo.getTaarikh());
                        credebit.setAmount_long(Long.parseLong(bankInfo.getMablagh().replaceAll(",","").trim()));
                        credebit.setRemainingAmount_long(Long.parseLong(bankInfo.getMablagh().replaceAll(",","").trim()));
                        tedadVosouli++;
                    }
                }
                else if (bankInfo.getStatus().equals(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD)){
                    credebit.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
                    credebit.setVosoulDate(bankInfo.getTaarikh());
                    Long bankinfoAmount=Long.parseLong(bankInfo.getMablagh().replaceAll(",","").trim());
                    Long diffAmount=bankinfoAmount-credebit.getAmount_long();
                    credebit.setAmount_long(credebit.getAmount_long()+diffAmount);
                    credebit.setRemainingAmount_long(credebit.getRemainingAmount_long()+diffAmount);
                    tedadVosouli++;
                }
                else if(bankInfo.getStatus().equals(BankInfo.BankInfoStatus.Z_MABLAGH_MOSAVI)){
                   credebit.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
                   credebit.setVosoulDate(bankInfo.getTaarikh());
                   tedadVosouli++;
                }
                asnadeSodorService.saveCredebitObj(credebit);
                if (credebit.getCredebitType() != null && credebit.getCredebitType().equals(Credebit.CredebitType.DARYAFTE_CHECK)) {
                    credebit.getDaryafteCheck().setStatus(DaryafteCheck.Status.VOSUL);
                    asnadeSodorService.updateDaryafteCheck(credebit.getDaryafteCheck());
                }
                asnadeSodorService.updateCredebit(credebit);
                if(!bankInfo.getStatus().equals(BankInfo.BankInfoStatus.Z_MABLAGH_KAMTAR) && EtebarkhateSanadList!=null && EtebarkhateSanadList.size()>0){
                    for(KhateSanad etebarKhateSanad:EtebarkhateSanadList){

                        if(etebarKhateSanad.getBedehiCredebit().getRemainingAmount_long() ==0){
                           boolean validBedehi=true;
                           String vosoulDate=bankInfo.getTaarikh();
                           List<KhateSanad> BedehiKhateSanad=asnadeSodorService.findAllKhateSanadByCredebitId(etebarKhateSanad.getBedehiCredebit().getId());
                           for(KhateSanad bedehikhatesanad:BedehiKhateSanad){
                               Credebit etebar=bedehikhatesanad.getEtebarCredebit();
                                   if(etebar.getVosoulState().equals(Credebit.VaziyatVosoul.TAEED_VOSOUL)){
                                       if(DateUtil.isGreaterThan(etebar.getVosoulDate(), vosoulDate))
                                           vosoulDate= etebar.getVosoulDate();
                                   }
                                   else{
                                       validBedehi=false;
                                       break;
                                   }
                               }
                           if(validBedehi){
                               etebarKhateSanad.getBedehiCredebit().setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
                               etebarKhateSanad.getBedehiCredebit().setVosoulDate(vosoulDate);
                               asnadeSodorService.updateCredebit(etebarKhateSanad.getBedehiCredebit());
                           }

                        }
                    }
                }
            }
        }
        shouldSabt=tedadVosouli.toString();
        return SUCCESS;
    }
    public String executeExcel() throws Exception {
        boolean isPaging = false;
        Integer pageNumber = 0;

        if (isExport()) {
            bankinfos = new PaginatedListImpl<BankInfo>();
            bankinfos = PagingUtil.getPaginatedList((List<BankInfo>) getFromSession("bankInfoExcel"));
            bankinfos.setPageNumber(0);
            bankinfos.setObjectsPerPage(Integer.MAX_VALUE);
            return SUCCESS;
        }

        if (page != null) {
            pageNumber = Integer.parseInt(page) - 1;
            isPaging = true;
        }

        if (isPaging) {
            serialBankInfo = (Long) getSession().get("serialBankInfo");
            bankinfos = new PaginatedListImpl<BankInfo>();
            bankinfos.setPageNumber(pageNumber);
            bankinfos.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
//            bankinfos = PagingUtil.getPaginatedList(clinicService.loadBankInfosBySerial(serialBankInfo,null));
            bankinfos = PagingUtil.getPaginatedList((List<BankInfo>) getFromSession("bankInfoExcel"));
        } else {
            List<BankInfo> bankInfosExcel = new ArrayList<BankInfo>();
            bargozar = new Bargozarandeh();
            String currDate = DateUtil.getCurrentDate();
            bargozar.setTaarikh(currDate);
            String currTime = DateUtil.getCurrentTime();

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User currentUser = loginService.findUserByUsername(username);
            if (bankMaghsad.equals("17038494"))
                setBankInformationForBargozar("تجارت", "170", "سپهبد قرنی", "17038494", currentUser);
            else if (bankMaghsad.equals("0185007111"))
                setBankInformationForBargozar("تجارت", "170", "سپهبد قرنی", "0185007111", currentUser);
            else if (bankMaghsad.equals("17123130"))
                setBankInformationForBargozar("تجارت", "170", "سپهبد قرنی", "17123130", currentUser);
            else if (bankMaghsad.equals("81011989"))
                setBankInformationForBargozar("پارسیان", "1060", "ونک", "81011989", currentUser);
            else if (bankMaghsad.equals("0200234164006"))
                setBankInformationForBargozar("پارسیان", "1001", "بلوار کشاورز", "0200234164006", currentUser);
            else if (bankMaghsad.equals("0200234164006_G"))
                setBankInformationForBargozar("پارسیان", "1001", "بلوار کشاورز (پرداخت گروهی)", "0200234164006", currentUser);
            else if (bankMaghsad.equals("0201136462000_G"))
                setBankInformationForBargozar("پارسیان", "1001", "میرداماد شرقی (پرداخت گروهی)", "0201136462000", currentUser);
            else
                setBankInformationForBargozar("پارسیان", "1001", "میرداماد شرقی", "0201136462000", currentUser);

            try {
                String fullFileName = realPath + "content/upload/" + uploadFileName;
                File theFile = new File(fullFileName);
                FileUtils.copyFile(upload, theFile);
                Workbook w;
                w = Workbook.getWorkbook(theFile);
                Sheet sheet = w.getSheet(0);
                shouldSabt = "yes";
                hasErrorRow = false;
                long bankInfoSerialId = sequenceService.getBankInfoSerialId();
                for (int i = 1; i < sheet.getRows(); i++) {
                    Cell cell0i = sheet.getCell(0, i);
                    Cell cell1i = sheet.getCell(1, i);
                    Cell cell2i = sheet.getCell(2, i);
                    String cell2Str = "";
                    if (cell2i.getContents() != null && cell2i.getContents().length() > 0) {
                        cell2Str = cell2i.getContents().substring(0, cell2i.getContents().length() - 4) +
                                "/" + cell2i.getContents().substring(cell2i.getContents().length() - 4, cell2i.getContents().length() - 2) +
                                "/" + cell2i.getContents().substring(cell2i.getContents().length() - 2, cell2i.getContents().length());
                    }
                    Cell cell3i = sheet.getCell(3, i);
                    Cell cell4i = sheet.getCell(4, i);
                    Cell cell5i = sheet.getCell(5, i);
                    Cell cell6i = sheet.getCell(6, i);
                    Cell cell7i = sheet.getCell(7, i);
                    Cell cell8i = sheet.getCell(8, i);
                    if (cell0i.getContents().equals("") && cell2Str.equals("") && cell4i.getContents().equals(""))
                        continue;

                    BankInfo bankInfo = new BankInfo();
                    if (bankMaghsad.equals("0185007111") ||bankMaghsad.equals("17038494") || bankMaghsad.equals("17123130")) {
                        Credebit credebitsByCodeMoshtari = asnadeSodorService.findCredebitByCodeMoshtari(extractCodeMoshtariFromDesc((StringUtil.toAnsii(cell6i.getContents()))));
                        if (bankMaghsad.equals("17038494") || bankMaghsad.equals("0185007111"))
                            processOmrFileUpload(bankInfo, cell4i.getContents(), null, StringUtil.unifyDateFormat(cell2Str), (StringUtil.toAnsii(cell6i.getContents())), cell5i.getContents(), credebitsByCodeMoshtari);
                        else if (bankMaghsad.equals("17123130"))
                            processTaeedVosolFileUpload(bankInfo, (StringUtil.toAnsii(cell2i.getContents())), cell4i.getContents(), StringUtil.unifyDateFormat(cell2Str), "-");

                        bankInfo.setCreatedTime(currTime);
                        String bimenameShomare = "";
                        if (credebitsByCodeMoshtari != null && credebitsByCodeMoshtari.getBimename() != null)
                            bimenameShomare = " (" + credebitsByCodeMoshtari.getBimename().getShomare() + ")";
                        bankInfo.setCreatedDate(currDate);
                        bankInfo.setShomareFish(cell4i.getContents());
                        bankInfo.setDesc(cell0i.getContents() + bimenameShomare);
                        bankInfo.setBardasht(cell3i.getContents());
                        bankInfo.setMablagh(cell5i.getContents());
                        if (bankInfo.getMablagh().indexOf(".") > -1)
                            bankInfo.setMablagh(cell5i.getContents().substring(0, cell5i.getContents().indexOf(".")).replaceAll(",", ""));
                        bankInfo.setTaarikh(StringUtil.unifyDateFormat(cell2Str));
//                        if (!bankInfo.getTaarikh().matches("\\d{4}/\\d{2}/\\d{2}") || !bankInfo.getTime().matches("\\d{1,2}:\\d{2}:\\d{2}")){
//                            hasErrorRow = true;
//                            continue;
//                        }
                        bankInfo.setCodeMoshtari(extractCodeMoshtariFromDesc(cell6i.getContents()));
                        bankInfo.setSerialId(bankInfoSerialId);
                        bankInfo.setBargozarandeh(bargozar);
                        bankInfosExcel.add(bankInfo);
                    } else {
                        Credebit credebitsByCodeMoshtari = asnadeSodorService.findCredebitByCodeMoshtari(extractCodeMoshtariFromDesc((StringUtil.toAnsii(cell2i.getContents()))));
                        if (bankMaghsad.equals("81011989") || bankMaghsad.equals("0200234164006_G")|| bankMaghsad.equals("0201136462000_G"))
                            processOmrFileUpload(bankInfo, cell0i.getContents(), cell5i.getContents(), StringUtil.unifyDateFormat(StringUtil.toAnsii(cell7i.getContents())), (StringUtil.toAnsii(cell2i.getContents())), cell4i.getContents(), credebitsByCodeMoshtari);
                        else if (bankMaghsad.equals("0200234164006"))
                            processPishPardakhtOmrFileUpload(bankInfo, cell0i.getContents(), cell5i.getContents(), StringUtil.unifyDateFormat(StringUtil.toAnsii(cell7i.getContents())));
                        else if (bankMaghsad.equals("0201136462000") || bankMaghsad.equals("4757575763"))
                            processTaeedVosolFileUpload(bankInfo, (StringUtil.toAnsii(cell2i.getContents())), cell4i.getContents(), cell7i.getContents(), cell6i.getContents());
                        String bimenameShomare = "";
                        if (credebitsByCodeMoshtari != null && credebitsByCodeMoshtari.getBimename() != null)
                            bimenameShomare = " (" + credebitsByCodeMoshtari.getBimename().getShomare() + ")";
                        bankInfo.setCreatedTime(currTime);
                        bankInfo.setCreatedDate(currDate);
                        bankInfo.setKodeDaryaft(cell1i.getContents());
                        bankInfo.setShomareFish(cell0i.getContents());
                        bankInfo.setDesc(cell2i.getContents() + bimenameShomare);
                        bankInfo.setBardasht(cell3i.getContents());
                        bankInfo.setMablagh(cell4i.getContents());
                        if (bankInfo.getMablagh().indexOf(".") > -1)
                            bankInfo.setMablagh(cell4i.getContents().substring(0, cell4i.getContents().indexOf(".")).replaceAll(",", ""));
//                        if (!bankInfo.getMablagh().matches("-?\\d+(\\.\\d+)?") || bankInfo.getMablagh().equals("0")) {
                        if (!StringUtil.isNumber(bankInfo.getMablagh())) {
                            bankInfo.setStatus(BankInfo.BankInfoStatus.INVALID_AMOUNT);
                            bankInfo.setStateId(-10);
                            shouldSabt = "no";
                        }
                        bankInfo.setKodeShobe(cell5i.getContents());
                        bankInfo.setTime(cell6i.getContents());
                        bankInfo.setTaarikh(StringUtil.unifyDateFormat(StringUtil.toAnsii(cell7i.getContents())));
                  /*      if (!bankInfo.getTaarikh().matches("\\d{4}/\\d{2}/\\d{2}") || !bankInfo.getTime().matches("\\d{1,2}:\\d{2}:\\d{2}")){
                            hasErrorRow = true;
                            continue;
                        }*/
                        if (!bankInfo.getTaarikh().matches("\\d{4}/\\d{2}/\\d{2}") || !bankInfo.getTime().matches("\\d{1,2}:\\d{2}:\\d{2}")) {
                            bankInfo.setStatus(BankInfo.BankInfoStatus.INVALID_DATE);
                            bankInfo.setStateId(-9);
                            shouldSabt = "no";
                        }
/*
                        System.out.println(bankInfo.getStateId()+"***"+bankInfo.getStatus());
*/
                        bankInfo.setIdUploaded(cell8i.getContents());
                        bankInfo.setCodeMoshtari(extractCodeMoshtariFromDesc(StringUtil.toAnsii(bankInfo.getDesc())));
                        bankInfo.setSerialId(bankInfoSerialId);
                        bankInfo.setBargozarandeh(bargozar);
                        bankInfosExcel.add(bankInfo);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                addActionError(e.getMessage());
                return SUCCESS;
            }

            if (hasErrorRow) {
                addActionError("این فایل حاوی سطرهایی با مقادیر نا معتبر است");
            }

            putToSession("hasErrorRow", hasErrorRow);
            putToSession("serialBankInfo", serialBankInfo);
            putToSession("bargozar", bargozar);

            Map<String, Integer> typesReportMap = new HashMap<String, Integer>();
            Map<String, Integer> codeMoshtariTekrariMap = new HashMap<String, Integer>();
            Map<Credebit, List<BankInfo>> mablaghKamtarMap = new HashMap<Credebit, List<BankInfo>>();
            Map<Credebit, List<BankInfo>> mablaghMosaviMap = new HashMap<Credebit, List<BankInfo>>();
            List<BankInfo> bankInfoOmrList = new ArrayList<BankInfo>();
            List<BankInfo> bankInfoTaeedVosolList = new ArrayList<BankInfo>();
            //CodeMoshtari Tekrari dar file
            if (!bankMaghsad.equals("17123130") && !bankMaghsad.equals("0201136462000") && !bankMaghsad.equals("4757575763"))
            {
                for (BankInfo bankInfo1 : bankInfosExcel) {
                    String codeMoshtari = bankInfo1.getCodeMoshtari();
                    if(codeMoshtari != null) {
                        int count = 1;
                        for (BankInfo bankInfo2 : bankInfosExcel) {
                            if (bankInfo2.getCodeMoshtari() != null && bankInfo2.getCodeMoshtari().equals(codeMoshtari) && bankInfo1 != bankInfo2) {
                                count++;
                                bankInfo2.setStatus(BankInfo.BankInfoStatus.TEKRARI_CM);
                                bankInfo2.setStateId(-3);
                            }
                        }
                        if (count > 1 && !codeMoshtariTekrariMap.containsKey(codeMoshtari)) {
                            codeMoshtariTekrariMap.put(codeMoshtari, count);
                            bankInfo1.setStatus(BankInfo.BankInfoStatus.TEKRARI_CM);
                            bankInfo1.setStateId(-3);
                        }
                    }
                }
            }
            for (BankInfo bankInfo1 : bankInfosExcel) {
                //Count ValidationTypes
                Integer countTemp = 0;
                if (typesReportMap.containsKey(bankInfo1.getVaziyateEtebarDesc())) {
                    countTemp = typesReportMap.get(bankInfo1.getVaziyateEtebarDesc());
                    countTemp++;
                    typesReportMap.remove(bankInfo1.getVaziyateEtebarDesc());
                    typesReportMap.put(bankInfo1.getVaziyateEtebarDesc(), countTemp);
                } else {
                    typesReportMap.put(bankInfo1.getVaziyateEtebarDesc(), 1);
                }

                if (bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.Z_AGHSAT_OMR)
                        || bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.Z_PISH_PARDAKHT_OMR)
                        || bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.TEKRARI_CM))
//                        || bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.AGHSAT_OMR_MABLAGH_KAMTAR)
//                        || bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.AGHSAT_OMR_MABLAGH_MAZAD))
                    bankInfoOmrList.add(bankInfo1);
                else if (bankInfo1.getStatus() != null && (bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD)
                        || bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.VOSOL_TEKRARI)))//|| bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.TEKRARI_CM))
                    bankInfoTaeedVosolList.add(bankInfo1);
                else if (bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.Z_MABLAGH_KAMTAR)) {
                    if (mablaghKamtarMap.containsKey(bankInfo1.getCredebit())) {
                        List<BankInfo> bankInfosT = mablaghKamtarMap.get(bankInfo1.getCredebit());
                        bankInfosT.add(bankInfo1);
                        mablaghKamtarMap.remove(bankInfo1.getCredebit());
                        mablaghKamtarMap.put(bankInfo1.getCredebit(), bankInfosT);
                    } else {
                        List<BankInfo> bankInfosT = new ArrayList<BankInfo>();
                        bankInfosT.add(bankInfo1);
                        mablaghKamtarMap.put(bankInfo1.getCredebit(), bankInfosT);
                    }
                } else if (bankInfo1.getStatus().equals(BankInfo.BankInfoStatus.Z_MABLAGH_MOSAVI)) {
                    if (mablaghMosaviMap.containsKey(bankInfo1.getCredebit())) {
                        List<BankInfo> bankInfosT = mablaghMosaviMap.get(bankInfo1.getCredebit());
                        bankInfosT.add(bankInfo1);
                        mablaghMosaviMap.remove(bankInfo1.getCredebit());
                        mablaghMosaviMap.put(bankInfo1.getCredebit(), bankInfosT);
                    } else {
                        List<BankInfo> bankInfosT = new ArrayList<BankInfo>();
                        bankInfosT.add(bankInfo1);
                        mablaghMosaviMap.put(bankInfo1.getCredebit(), bankInfosT);
                    }
                }
            }

            Collections.sort(bankInfosExcel);
            Collections.sort(bankInfoOmrList);
            Collections.sort(bankInfoTaeedVosolList);

            bankinfos = PagingUtil.getPaginatedList(bankInfosExcel);
            if (bankInfosExcel != null && bankInfosExcel.size() > 0)
                serialBankInfo = bankInfosExcel.get(0).getSerialId();

            mablaghKamtar(mablaghKamtarMap, bankInfoTaeedVosolList);
            mablaghMosavi(mablaghMosaviMap, bankInfoTaeedVosolList);
            putToSession("typesReportMap", typesReportMap);
            putToSession("codeMoshtariTekrariMap", codeMoshtariTekrariMap);
            putToSession("bankInfoExcel", bankInfosExcel);
            putToSession("bankInfoOmrList", bankInfoOmrList);
            putToSession("bankInfoTaeedVosolList", bankInfoTaeedVosolList);
        }
        return SUCCESS;
    }

    private void mablaghMosavi(Map<Credebit, List<BankInfo>> mablaghMosaviMap, List<BankInfo> bankInfoTaeedVosolList) {
        for (Credebit credebit : mablaghMosaviMap.keySet()) {
            List<BankInfo> bankInfoMList = mablaghMosaviMap.get(credebit);
            if (bankInfoMList.size() == 1) {
                Long totalMablagh = 0l;
                for (BankInfo bankInfo1 : bankInfoTaeedVosolList) {
                    if (bankInfo1.getCredebit().getShomareMoshtari().equals(credebit.getShomareMoshtari())) {
                        totalMablagh += Long.parseLong(bankInfo1.getMablagh().replaceAll(",", "").trim());
                    }
                }
                if (totalMablagh > 0) {
                    List<BankInfo> copy = new ArrayList<BankInfo>();
                    for (BankInfo bankInfo1 : bankInfoTaeedVosolList) {
                        copy.add(bankInfo1);
                    }
                    for (BankInfo bankInfo1 : copy) {
                        if (bankInfoTaeedVosolList.contains(bankInfo1) && credebit.getShomareMoshtari().equals(bankInfo1.getCredebit().getShomareMoshtari())) {
                            bankInfoTaeedVosolList.remove(bankInfo1);
                            bankInfo1.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD);
                            bankInfoTaeedVosolList.add(bankInfo1);
                        }
                    }
                    bankInfoMList.get(0).setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD);
                    bankInfoTaeedVosolList.add(bankInfoMList.get(0));
                } else {
                    for (BankInfo bankInfo1 : bankInfoMList) {
                        bankInfo1.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_KAMTAR);
                        bankInfoTaeedVosolList.add(bankInfo1);
                    }
                    bankInfoMList.get(0).setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MOSAVI);
                }

            } else if (bankInfoMList.size() > 1) {
                Long totalMablagh = 0l;
                for (BankInfo bankInfo1 : bankInfoMList) {
                    Long amountParamLong = Long.parseLong(bankInfo1.getMablagh().replaceAll(",", "").trim());
                    totalMablagh += amountParamLong;
                }

                List<BankInfo> copy = new ArrayList<BankInfo>();
                for (BankInfo bankInfo1 : bankInfoMList) {
                    copy.add(bankInfo1);
                }

                for (BankInfo bankInfo1 : bankInfoTaeedVosolList) {
                    copy.add(bankInfo1);
                }

                for (BankInfo bankInfo1 : copy) {
                    if (bankInfo1.getCredebit().getShomareMoshtari().equals(credebit.getShomareMoshtari())) {
                        if (totalMablagh > 0) {
                            bankInfoTaeedVosolList.remove(bankInfo1);
                            bankInfo1.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD);
                            bankInfoTaeedVosolList.add(bankInfo1);
                            totalMablagh += Long.parseLong(bankInfo1.getMablagh().replaceAll(",", "").trim());
                        }
                    }
                }
            }
        }
    }

    private void mablaghKamtar(Map<Credebit, List<BankInfo>> mablaghKamtarMap, List<BankInfo> bankInfoTaeedVosolList) {
        for (Credebit credebit : mablaghKamtarMap.keySet()) {
            List<BankInfo> bankInfoMList = mablaghKamtarMap.get(credebit);
            if (bankInfoMList.size() == 1) {
                Long totalMablagh = 0l;
                for (BankInfo bankInfo1 : bankInfoTaeedVosolList) {
                    if (bankInfo1.getCredebit().getShomareMoshtari().equals(credebit.getShomareMoshtari())) {
                        totalMablagh += Long.parseLong(bankInfo1.getMablagh().replaceAll(",", "").trim());
                    }
                }
                if (totalMablagh.equals(credebit.getAmount_long())) {
                    List<BankInfo> copy = new ArrayList<BankInfo>();
                    for (BankInfo bankInfo1 : bankInfoTaeedVosolList) {
                        copy.add(bankInfo1);
                    }
                    for (BankInfo bankInfo1 : copy) {
                        if (bankInfoTaeedVosolList.contains(bankInfo1) && credebit.getShomareMoshtari().equals(bankInfo1.getCredebit().getShomareMoshtari())) {
                            bankInfoTaeedVosolList.remove(bankInfo1);
                            bankInfo1.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MOSAVI);
                            bankInfoTaeedVosolList.add(bankInfo1);
                        }
                    }
                    bankInfoMList.get(0).setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MOSAVI);
                    bankInfoTaeedVosolList.add(bankInfoMList.get(0));

                } else if (totalMablagh > credebit.getAmount_long()) {
                    for (BankInfo bankInfo1 : bankInfoMList) {
                        bankInfo1.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD);
                        bankInfoTaeedVosolList.add(bankInfo1);
                    }
                    bankInfoMList.get(0).setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD);
                } else {
                    for (BankInfo bankInfo1 : bankInfoMList) {
                        bankInfo1.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_KAMTAR);
                        bankInfoTaeedVosolList.add(bankInfo1);
                    }
                    bankInfoMList.get(0).setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_KAMTAR);
                }

            } else if (bankInfoMList.size() > 1) {
                Long totalMablagh = 0l;
                for (BankInfo bankInfo1 : bankInfoMList) {
                    Long amountParamLong = Long.parseLong(bankInfo1.getMablagh().replaceAll(",", "").trim());
                    totalMablagh += amountParamLong;
                }

                List<BankInfo> copy = new ArrayList<BankInfo>();
                for (BankInfo bankInfo1 : bankInfoTaeedVosolList) {
                    copy.add(bankInfo1);
                }

                for (BankInfo bankInfo1 : copy) {
                    if (bankInfo1.getCredebit().getShomareMoshtari().equals(credebit.getShomareMoshtari())) {
                        if (totalMablagh > 0) {
                            bankInfoTaeedVosolList.remove(bankInfo1);
                            bankInfo1.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD);
                            bankInfoTaeedVosolList.add(bankInfo1);
                            totalMablagh += Long.parseLong(bankInfo1.getMablagh().replaceAll(",", "").trim());
                        }
                    }
                }

                if (totalMablagh.equals(credebit.getAmount_long())) {
                    for (BankInfo bankInfo1 : bankInfoMList) {
                        bankInfo1.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MOSAVI);
                        bankInfoTaeedVosolList.add(bankInfo1);
                    }
                } else if (totalMablagh > credebit.getAmount_long()) {
                    for (BankInfo bankInfo1 : bankInfoMList) {
                        bankInfo1.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD);
                        bankInfoTaeedVosolList.add(bankInfo1);
                    }
                } else {
                    for (BankInfo bankInfo1 : bankInfoMList) {
                        bankInfoTaeedVosolList.add(bankInfo1);
                    }
                }
            }
        }
    }

    public String finalizeUpload() {
        List<BankInfo> theBankInfos = (List<BankInfo>) getFromSession("bankInfoOmrList");

        if (theBankInfos != null && theBankInfos.size() > 0) {
            PaginatedListImpl<KhateSanad> khateSanadListPaginated = new PaginatedListImpl<KhateSanad>();
            khateSanadListPaginated.setPageNumber(1);
            khateSanadListPaginated.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);
            khateSanadListPaginated.setList(new ArrayList<KhateSanad>());
            if (isExport()) {

                khateSanadListPaginated = (PaginatedListImpl<KhateSanad>) getFromSession("khateSanadListPaginated");
                khateSanadListPaginated.setPageNumber(0);
                khateSanadListPaginated.setObjectsPerPage(Integer.MAX_VALUE);
            } else if (page != null) {
                khateSanadListPaginated = (PaginatedListImpl<KhateSanad>) getFromSession("khateSanadListPaginated");
                khateSanadListPaginated.setPageNumber(Integer.parseInt(page) - 1);
                khateSanadListPaginated.setObjectsPerPage(Constant.MAX_OBJECTS_PER_PAGE);

            } else {
                Bargozarandeh bargozarandeh = clinicService.addNewBargozar(bargozar);
                serialBankInfo = (Long) getSession().get("serialBankInfo");
                hasErrorRow = (Boolean) getSession().get("hasErrorRow");
                if (hasErrorRow != null && hasErrorRow.equals(true))
                    addActionError("این فایل حاوی سطرهایی با مقادیر نا معتبر است");
                Set<BankInfo> savedInfos = clinicService.addBankInfosBatch(theBankInfos, bargozarandeh);
                bargozarandeh.setBankInfos(savedInfos);
                clinicService.updateBargozar(bargozarandeh);
                List<Credebit> credebits = new ArrayList<Credebit>();
                for (BankInfo savedInfo : savedInfos) {
                    Credebit darpar;
                    if (bargozarandeh.getShomareHesab().equals("0200234164006"))
                        darpar = new Credebit(savedInfo.getMablagh(), sequenceService.nextShenaseCredebit(), null, null, Credebit.CredebitType.PISHPARDAKHT);
                    else
                        darpar = new Credebit(savedInfo.getMablagh(), sequenceService.nextShenaseCredebit(), null, null, Credebit.CredebitType.DARYAFTE_FISH);
                    DaryafteFish darFish = new DaryafteFish();
                    darpar.setShomareMoshtari(savedInfo.getCodeMoshtari());
                    if (savedInfo.getTaarikh().length() == 8)
                        savedInfo.setTaarikh("13" + savedInfo.getTaarikh());
                    darFish.setBank(bargozar.getNameBank());
                    darFish.setKodeShobe(savedInfo.getKodeShobe());
                    darFish.setShomareSanadBank(savedInfo.getKodeDaryaft());
                    darFish.setTozihat(savedInfo.getDesc());
                    darFish.setShomareFish(savedInfo.getShomareFish());
                    darFish.setTarikh(savedInfo.getTaarikh());
                    darFish.setTime(savedInfo.getTime());
                    darFish.setBankInfo(savedInfo);
                    asnadeSodorService.saveDaryafteFishElectroniki(darFish);
                    darpar.setDaryafteFish(darFish);
                    darpar.setAuthorityId(darFish.getShomareSanadBank());
                    darpar.setTimeFish(darFish.getTime());
                    darpar.setDateFish(darFish.getTarikh());
                    if (bargozarandeh.getShomareHesab().equals("17038494"))
                        darpar.setBankName(Constant.CREDEBIT_BANK_TEJARAT_SEPAHBOD_GHARANI);
                    else if (bargozarandeh.getShomareHesab().equals("0185007111"))
                        darpar.setBankName(Constant.CREDEBIT_BANK_TEJARAT_SEPAHBOD_GHARANI_NEW);
                    else if (bargozarandeh.getShomareHesab().equals("0200234164006"))
                    {
                        if(bargozarandeh.getNameShobe().equals("بلوار کشاورز (پرداخت گروهی)"))
                            darpar.setBankName(Constant.CREDEBIT_BANK_PARSIAN_BOLVAR_KESHAVARZ_GROUP);
                        else
                            darpar.setBankName(Constant.CREDEBIT_BANK_PARSIAN_BOLVAR_KESHAVARZ);
                    }
                    else if (bargozarandeh.getShomareHesab().equals("81011989"))
                        darpar.setBankName(Constant.CREDEBIT_BANK_PARSIAN_VANAK);
                    else//(bankMaghsad.equals("0201136462000"))
                    {
                        if (bargozarandeh.getNameShobe().equals("میرداماد شرقی (پرداخت گروهی)"))
                            darpar.setBankName(Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD_GROUP);
                        else
                            darpar.setBankName(Constant.CREDEBIT_BANK_TEJARAT_EL_MIRDAMAD);
                    }
                    asnadeSodorService.saveCredebitObj(darpar);
                    savedInfo.setCredebit(darpar);
                    clinicService.addNewBankInfo(savedInfo);
                    credebits.add(darpar);
                }
                //if (credebits != null && credebits.size() > 0)
                //    asnadeSodorService.saveCredebitList(credebits);
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                User user = null;
                if (username != null) {
                    user = loginService.findUserByUsername(username);
                }
                sanads = new ArrayList<Sanad>();
                for (Credebit c : credebits) {
                    asnadeSodorService.refreshObject(c);
                    if (c.getShomareMoshtari() != null && c.getShomareMoshtari().length() > 14) {
                        Credebit bedehi = asnadeSodorService.findBedehiByCodeMoshtari(c.getShomareMoshtari(), c.getAmount_long());
                        if(bedehi!=null && c.getBankInfoList().get(0).getStatus().equals(BankInfo.BankInfoStatus.TEKRARI_CM) ) {
                            List<Credebit> bedehiList = asnadeSodorService.findBedehiByBimenameAndAmount(bedehi.getBimename().getShomare(), c.getAmount_long());
                            if(bedehiList.size() > 0)
                                bedehi = bedehiList.get(0);
                            else
                                bedehi = null;
                        }

                        if (bedehi != null && bedehi.getRemainingAmount_long() > 0) {
                            Sanad sanad = asnadeSodorService.sabteSanad(user, bedehi, c, Sanad.NoeSanad.GHABZE_RESID, Sanad.Vaziat.DAEM, true);
                            sanads.add(sanad);
                            khateSanadListPaginated.getList().addAll(sanad.getKhateSanadSet());
                        }
                    }
                }
                khateSanadListPaginated.setFullListSize(sanads.size());
                shouldSabt = String.valueOf(savedInfos.size());
            }
            putToSession("khateSanadListPaginated", khateSanadListPaginated);
        } else {
            shouldSabt = "0";
        }

     //baraye taeed vosoul
        List<BankInfo> theBankInfosEtebar = (List<BankInfo>) getFromSession("bankInfoTaeedVosolList");
        if (theBankInfosEtebar != null && theBankInfosEtebar.size() > 0)
            clinicService.saveBankInfos(theBankInfosEtebar);
        Integer tedadVosoli = 0;
        List<String> shensaePardakhtTekrari = new ArrayList<String>();
        for (BankInfo bankinfoEtebar : theBankInfosEtebar) {
            if (bankinfoEtebar.getCodeMoshtari() != null) {
                Credebit etebar = asnadeSodorService.findPardakhtShenaseDarCredebitByCodeMoshtari(bankinfoEtebar.getCodeMoshtari());
                if(etebar == null)
                    etebar=asnadeSodorService.findCredebitDaryafteCheckByCodeMoshtari(bankinfoEtebar.getCodeMoshtari()) ;
                if (etebar != null) {
                    List<KhateSanad> khateSanadListEtebar = asnadeSodorService.findAllKhateSanadByEtebarCredebitId(etebar.getId());
                    if (bankinfoEtebar.getStatus().equals(BankInfo.BankInfoStatus.Z_MABLAGH_KAMTAR)) {
                        Boolean validEtebar = true;

                            for (KhateSanad khateSanadEtebar : khateSanadListEtebar) {

                                if (etebar.getCredebitType().equals(Credebit.CredebitType.PARDAKHT_SHENASEDAR) && DateUtil.isGreaterThan(DateUtil.getCurrentDate(), DateUtil.addDaysWithTatilat(khateSanadEtebar.getSanad().getCreatedDate(), 3))) {
                                    validEtebar = false;
                                }
                            }

                        if (validEtebar)
                        {
                            if (khateSanadListEtebar != null && khateSanadListEtebar.size() > 0) {
                                etebar.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
                                etebar.setVosoulDate(bankinfoEtebar.getTaarikh());
                            }
                            else
                            {
                                etebar.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
                                etebar.setAmount_long(Long.parseLong(bankinfoEtebar.getMablagh().replaceAll(",", "").trim()));
                                etebar.setRemainingAmount_long(Long.parseLong(bankinfoEtebar.getMablagh().replaceAll(",", "").trim()));
                            }
                        }

                    } else {
                        etebar.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
                        etebar.setVosoulDate(bankinfoEtebar.getTaarikh());
                    }

                    if (bankinfoEtebar != null)
                        if (bankinfoEtebar.getStatus().equals(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD)) {
                            Long totalAmount = 0l;
                            for (BankInfo bankinfoEtebar1 : theBankInfosEtebar) {
                                if (bankinfoEtebar1.getCodeMoshtari().equals(etebar.getShomareMoshtari())) {
                                    totalAmount += Long.parseLong(bankinfoEtebar1.getMablagh().replaceAll(",", "").trim());
                                }
                            }

                            Long diff = totalAmount - etebar.getAmount_long();
                            etebar.setAmount_long(totalAmount);
                            etebar.setRemainingAmount_long(etebar.getRemainingAmount_long() + diff);

                            asnadeSodorService.saveCredebitObj(etebar);
                        } else {
                            Long totalAmount = 0l;
                            for (BankInfo bankinfoEtebar1 : theBankInfosEtebar) {
                                if (bankinfoEtebar1.getCodeMoshtari().equals(etebar.getShomareMoshtari())) {
                                    totalAmount += Long.parseLong(bankinfoEtebar1.getMablagh().replaceAll(",", "").trim());
                                }
                            }

                            if (bankinfoEtebar.getStatus().equals(BankInfo.BankInfoStatus.VOSOL_TEKRARI)) {
                                if (!shensaePardakhtTekrari.contains(bankinfoEtebar.getCodeMoshtari())) {
                                    shensaePardakhtTekrari.add(bankinfoEtebar.getCodeMoshtari());
                                    asnadeSodorService.executeCredebitFileUpload(etebar, bankinfoEtebar, totalAmount);
                                } else {
                                    bankinfoEtebar.setStatus(BankInfo.BankInfoStatus.Z_MABLAGH_MAZAD);

                                }
                            } else {
                                asnadeSodorService.executeCredebitFileUpload(etebar, bankinfoEtebar, totalAmount);
                            }
                        }
                    if (etebar.getCredebitType() != null && etebar.getCredebitType().equals(Credebit.CredebitType.DARYAFTE_CHECK)) {
                        etebar.getDaryafteCheck().setStatus(DaryafteCheck.Status.VOSUL);
                        asnadeSodorService.updateDaryafteCheck(etebar.getDaryafteCheck());
                    }
                    asnadeSodorService.updateCredebit(etebar);
                    tedadVosoli++;
                    for (KhateSanad khateSanadEtebar : khateSanadListEtebar) {
                        bankinfoEtebar.setCredebit(etebar);
                        if (!bankinfoEtebar.getStatus().equals(BankInfo.BankInfoStatus.Z_MABLAGH_KAMTAR)) {
                            boolean isValidBedehi = true;
                            String maxDateEtebar = bankinfoEtebar.getTaarikh();
                            Credebit bedehi = khateSanadEtebar.getBedehiCredebit();
                            if (bedehi.getRemainingAmount_long() != 0) {
                                isValidBedehi = false;
                            } else {
                                List<KhateSanad> khateSanadListBedehi = asnadeSodorService.findAllKhateSanadByCredebitId(bedehi.getId());
                                for (KhateSanad khateSanadbedehi : khateSanadListBedehi) {
                                    if (khateSanadbedehi.getEtebarCredebit().getVosoulState().equals(Credebit.VaziyatVosoul.TAEED_VOSOUL)) {
                                        if (DateUtil.isGreaterThan(khateSanadbedehi.getEtebarCredebit().getVosoulDate(), maxDateEtebar))
                                            maxDateEtebar = khateSanadbedehi.getEtebarCredebit().getVosoulDate();
                                    } else {
                                        isValidBedehi = false;
                                    }
                                }
                            }
                            if (isValidBedehi) {
                                bedehi.setVosoulDate(maxDateEtebar);
                                bedehi.setVosoulState(Credebit.VaziyatVosoul.TAEED_VOSOUL);
                                asnadeSodorService.updateCredebit(bedehi);
                            }
                        }
                    }
                }
            }
            shouldSabt = tedadVosoli.toString();
        }


        return SUCCESS;
    }

//    public String execute() throws Exception {
//
//        bargozar = new Bargozarandeh();
//        bargozar.setTaarikh(DateUtil.getCurrentDate());
//        bargozar.setNameBank("تجارت");
//        bargozar.setKodeShobe("170");
//        bargozar.setNameShobe("سپهبد قرنی");
//        bargozar.setShomareHesab("17038494");
//        List<BankInfo> bankInfosText = new ArrayList<BankInfo>();
//        try {
//            String fullFileName = uploadFileName;
//            List<Credebit> allTheGhests = asnadeSodorService.findAllBedehiCredebits();
//            RandomAccessFile raf = new RandomAccessFile(upload,"rws");
//            while(raf.getFilePointer()<=raf.length()){
//                String theLine = raf.readLine();
//                StringTokenizer st = new StringTokenizer(theLine);
//                BankInfo bankInfo = new BankInfo();
//                bankInfo.setKodeDaryaft((String) st.nextElement());
//                bankInfo.setTaarikh((String) st.nextElement());
//                bankInfo.setMablagh((String) st.nextElement());
//                bankInfo.setShomareFish((String) st.nextElement());
//                st.nextElement();
//
//                int truthChecker = 0;
//                for (Credebit ghest : allTheGhests) {
//                    if (truthChecker == 0){
//                        if (ghest.getShomareMoshtari().trim().equalsIgnoreCase(bankInfo.getKodeDaryaft())){
//                            if (Double.parseDouble(ghest.getRemainingAmount()) == Double.parseDouble(bankInfo.getMablagh())){
//                                truthChecker = 1;
//                            }else if (Double.parseDouble(ghest.getRemainingAmount()) > Double.parseDouble(bankInfo.getMablagh())){
//                                truthChecker = 2;
//                            }else if (Double.parseDouble(ghest.getRemainingAmount()) < Double.parseDouble(bankInfo.getMablagh())){
//                                truthChecker = 3;
//                            }
//                        }
//                    }
//                }
//                if (truthChecker == 0){
//                    bankInfo.setMainId(-1);
//                }else if (truthChecker == 2){
//                    bankInfo.setMainId(-2);
//                }else if (truthChecker == 3){
//                    bankInfo.setMainId(-3);
//                }else if (truthChecker == 1){
//                    bankInfo.setMainId(-4);
//                }
//
//                bankInfosText.add(bankInfo);
//            }
//
//        } catch (Exception e) {
//            addActionError(e.getMessage());
//        }
//        bankinfos = bankInfosText;
//        return SUCCESS;
//    }
     //b-h
    //for rerurning bank excel file
    private InputStream bankExcelFile;
    public String test(){
        try{
            File file=new File("C:\\Documents and Settings\\f-haghighi\\Desktop\\test.xls");
            Workbook w = Workbook.getWorkbook(file);
            FileInputStream fin=new FileInputStream(file);
            byte[] bFile = new byte[(int) file.length()];
            fin.read(bFile);
            fin.close();
           // ByteArrayOutputStream baos = new ByteArrayOutputStream();


            bankExcelFile = new ByteArrayInputStream(bFile);
        }
        catch (Exception e){
            e.printStackTrace();

        }
        return SUCCESS;
    }
    public InputStream getBankExcelFile() {
        return bankExcelFile;
    }

    public void setBankExcelFile(InputStream bankExcelFile) {
        this.bankExcelFile = bankExcelFile;
    }

    private String extractCodeMoshtariFromDesc(String desc) {
        if (desc == null) return null;
        Pattern pattern = Pattern.compile("\\d{17}");
        Matcher matcher = pattern.matcher(desc);
        while (matcher.find()) {
            return matcher.group();
        }
        pattern = Pattern.compile("\\d{15}");
        matcher = pattern.matcher(desc);
        while (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getFileCaption() {
        return fileCaption;
    }

    public void setFileCaption(String fileCaption) {
        this.fileCaption = fileCaption;
    }

    public PaginatedListImpl<BankInfo> getBankinfos() {
        return bankinfos;
    }

    public void setBankinfos(PaginatedListImpl<BankInfo> bankinfos) {
        this.bankinfos = bankinfos;
    }

    public Bargozarandeh getBargozar() {
        return bargozar;
    }

    public void setBargozar(Bargozarandeh bargozar) {
        this.bargozar = bargozar;
    }

    public List<Sanad> getSanads() {
        return sanads;
    }

    public void setSanads(List<Sanad> sanads) {
        this.sanads = sanads;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public boolean isNosession() {
        return nosession;
    }

    public void setNosession(boolean nosession) {
        this.nosession = nosession;
    }

    public BankInfo getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }

    public String getShouldSabt() {
        return shouldSabt;
    }

    public void setShouldSabt(String shouldSabt) {
        this.shouldSabt = shouldSabt;
    }

    public ISequenceService getSequenceService() {
        return sequenceService;
    }

    public void setSequenceService(ISequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    public String getBankMaghsad() {
        return bankMaghsad;
    }

    public void setBankMaghsad(String bankMaghsad) {
        this.bankMaghsad = bankMaghsad;
    }

    public Karmozd getKarmozd() {
        return karmozd;
    }

    public void setKarmozd(Karmozd karmozd) {
        this.karmozd = karmozd;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Boolean getHasErrorRow() {
        return hasErrorRow;
    }

    public void setHasErrorRow(Boolean hasErrorRow) {
        this.hasErrorRow = hasErrorRow;
    }
}
