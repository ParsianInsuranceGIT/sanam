package com.bitarts.parsian.service.implementation;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.dao.NamayandeDAO;
import com.bitarts.parsian.dao.SequenceDAO;
import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.Sequence;
import com.bitarts.parsian.model.bimename.Bimename;
import com.bitarts.parsian.model.bimename.DarkhastBazkharid;
import com.bitarts.parsian.service.ISequenceService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Aug 9, 2011
 * Time: 12:24:16 AM
 */
public class SequenceService implements ISequenceService {
    private SequenceDAO sequenceDAO;
    private NamayandeDAO namayandeDAO;


    public boolean isRepetitiousIssuanceCode(String issuanceCode)
    {
        return sequenceDAO.isRepetitiousIssuanceCode(issuanceCode);

    }


    public long getBankInfoSerialId()
    {
        Long seriId=sequenceDAO.getLastSerialBankInfo();
        if(seriId==null) return 1;
        return seriId + 1;
    }

    public void setSequenceDAO(SequenceDAO sequenceDAO) {
        this.sequenceDAO = sequenceDAO;
    }

    public String nextShomareBimename(Namayande vahedSodoor) {
        String currentYear = DateUtil.getCurrentDate().substring(2, 4);
        String shomareBimename = "2210/"+vahedSodoor.getKodeNamayandeKargozar()+"/"+ currentYear +"/";
        String shomareBimenameCount = sequenceDAO.getSequenceCountSPNamayandeYear("SUB_SHOMARE_BIMENAME", vahedSodoor.getId(), currentYear);
        if(shomareBimenameCount.startsWith("ERR1")) {
            Sequence subShomareBimenameSequence = new Sequence("SUB_SHOMARE_BIMENAME", "000002", vahedSodoor, currentYear);
            sequenceDAO.updateSequence(subShomareBimenameSequence);
            shomareBimenameCount = "000001";
        }
        shomareBimename += shomareBimenameCount;
        return shomareBimename;
    }

    public String nextShomareRadif(Namayande namayande) {
        String currentYear = DateUtil.getCurrentDate().substring(2, 4);
        String shomareRadif = namayande.getKodeNamayandeKargozar()+"/"+ currentYear+"/";
        String shomareRadifCount = sequenceDAO.getSequenceCountSPNamayandeYear("SUB_CODE_RAHGIRI_PISHNAHAD", namayande.getId(),currentYear);
        if (shomareRadifCount.startsWith("ERR1")) {
            Sequence subshomareRadif = new Sequence("SUB_CODE_RAHGIRI_PISHNAHAD", "000002", namayande,currentYear);
            sequenceDAO.updateSequence(subshomareRadif);
            shomareRadifCount = "000001";
        }
        shomareRadif += shomareRadifCount;
        return shomareRadif;
    }

    public String nextShomareDarkhastBazkharid() {
        String shomareDarkhast = sequenceDAO.getSequenceCountSP("SUB_SHOMARE_DARKHAST_BAZKHARID");
        if (shomareDarkhast.startsWith("ERR1")) {
            Sequence subShomareDarkhasteBazkharid = new Sequence("SUB_SHOMARE_DARKHAST_BAZKHARID", "000002");
            sequenceDAO.updateSequence(subShomareDarkhasteBazkharid);
            shomareDarkhast = "000001";
        }
        return shomareDarkhast;
    }

    public String nextShomareVam(Namayande vahedSodor)
    {
        String currentYear = DateUtil.getCurrentDate().substring(2, 4);
        String sequenceVam = sequenceDAO.getSequenceCountSPNamayandeYear("SUB_SHOMARE_VAM", vahedSodor.getId(),currentYear);
        if (sequenceVam.startsWith("ERR1")) {
            Sequence subShomareVam = new Sequence("SUB_SHOMARE_VAM", "000002",vahedSodor,currentYear);
            sequenceDAO.updateSequence(subShomareVam);
            sequenceVam = "000001";
        }
        String shomareVam="2214/"+vahedSodor.getKodeNamayandeKargozar()+"/"+ currentYear+"/"+sequenceVam;
        return shomareVam;
    }

    public String nextShomareSerialeBimename(Namayande vahedSodor) {
        String shomareBimenameCount = sequenceDAO.getSequenceCountSPNamayande("SUB_SHOMARE_SERIAL_BIMENAME", vahedSodor.getId());
        if (shomareBimenameCount.startsWith("ERR1")) {
            Sequence subShomareBimenameSequence = new Sequence("SUB_SHOMARE_SERIAL_BIMENAME", "000002", vahedSodor);
            sequenceDAO.updateSequence(subShomareBimenameSequence);
            shomareBimenameCount = "000001";
        }
        return shomareBimenameCount;
    }

    public String nextShomareSerialePrintBimename() {
        String shomareSerialePrintBimename = "240" + DateUtil.getCurrentDate().substring(2, 4);
        String shomareSerialePrintBimenameCount = sequenceDAO.getSequenceCountSP("SUB_SHOMARE_SERIALE_PRINTE_BIMENAME");
        if (shomareSerialePrintBimenameCount.startsWith("ERR1")) {
            Sequence subShomareSerialePrintBimename = new Sequence("SUB_SHOMARE_SERIALE_PRINTE_BIMENAME", "000002");
            sequenceDAO.updateSequence(subShomareSerialePrintBimename);
            shomareSerialePrintBimenameCount = "000001";
        }
        shomareSerialePrintBimename += shomareSerialePrintBimenameCount;
        return shomareSerialePrintBimename;
    }

    public synchronized String nextShomareRadifElhaghie() {
        synchronized (SequenceService.class) {
            return sequenceDAO.nextShomareRadifElhaghie();
        }
    }

    public String nextShomareBahremandiAzManafe(Bimename bimename)
    {
        List<DarkhastBazkharid> bardashtList=bimename.getDarkhastBardashtAzAndukhteFinal();
        int countBardasht;
        if(bardashtList==null)
            countBardasht=0;
        else
            countBardasht=bardashtList.size();
        String shomareBardasht="2216/"+bimename.getShomare().substring(5)+"/00"+ (countBardasht+1);
        return shomareBardasht;
    }

    public synchronized String nextShomareElhaghiye(Bimename bimename) {
        String shomareElhaghie = bimename.getShomare() + "/";
        String shomareElhaghieCount = sequenceDAO.getSequenceCountSPBimename("SUB_SHOMARE_ELHAGHIYE", bimename.getId());
        if (shomareElhaghieCount.startsWith("ERR1")) {
            Sequence subShomareElhaghiye = new Sequence("SUB_SHOMARE_ELHAGHIYE", "002",bimename);
            sequenceDAO.updateSequence(subShomareElhaghiye);
            shomareElhaghieCount = "001";
        }
        shomareElhaghie += shomareElhaghieCount;
        return shomareElhaghie;
    }

    public String nextShenaseCredebit() {
        String shenaseCredebit = "4" + DateUtil.getCurrentDate().substring(2, 4) + "800";
        String shenaseCredebitCount = sequenceDAO.getSequenceCountSP("SUB_SHOMARE_SERIALE_BEDEHI");
        if (shenaseCredebitCount.startsWith("ERR1")) {
            Sequence subShenaseCredebit = new Sequence("SUB_SHOMARE_SERIALE_BEDEHI", "0000000002");
            sequenceDAO.updateSequence(subShenaseCredebit);
            shenaseCredebitCount = "0000000001";
        }
        shenaseCredebit += shenaseCredebitCount;
        return shenaseCredebit;
    }
    public String nextShomareMosharekat(){
        String shomareMosharekat = sequenceDAO.getSequenceCountSP("SHOMARE_SERIALE_MOSHAREKAT");
        if (shomareMosharekat.startsWith("ERR1")) {
            Sequence subMosharekat = new Sequence("SHOMARE_SERIALE_MOSHAREKAT", "0000000002");
            sequenceDAO.updateSequence(subMosharekat);
            shomareMosharekat = "0000000001";
        }
        return shomareMosharekat;
    }

    public String nextShomareSanad() {
        String shomareSanad = "4" + DateUtil.getCurrentDate().substring(2, 4) + "800";
        String shomareSanadCount = sequenceDAO.getSequenceCountSP("SHOMARE_SANAD");
        if (shomareSanadCount.startsWith("ERR1")) {
            Sequence subShomareSanad = new Sequence("SHOMARE_SANAD", "000000002");
            sequenceDAO.updateSequence(subShomareSanad);
            shomareSanadCount = "000000001";
        }
        int count = 0;
        while (shomareSanadCount != null && shomareSanadCount.equals("")){
            if (count > 100)
                break;
            shomareSanadCount = sequenceDAO.getSequenceCountSP("SHOMARE_SANAD");
        }
        shomareSanad += shomareSanadCount;
        return shomareSanad;
    }


    public String nextShomareMoshtari(String codeVahedSodoor, String serial, String datePart, String typeString){
            String shomare = StringUtils.leftPad(codeVahedSodoor, 4, "0") + StringUtils.leftPad(serial, 6, "0") + datePart + typeString;
            int sum = Integer.parseInt(String.valueOf(shomare.charAt(0))) * 15
                    + Integer.parseInt(String.valueOf(shomare.charAt(1))) * 14
                    + Integer.parseInt(String.valueOf(shomare.charAt(2))) * 13
                    + Integer.parseInt(String.valueOf(shomare.charAt(3))) * 12
                    + Integer.parseInt(String.valueOf(shomare.charAt(4))) * 11
                    + Integer.parseInt(String.valueOf(shomare.charAt(5))) * 10
                    + Integer.parseInt(String.valueOf(shomare.charAt(6))) * 9
                    + Integer.parseInt(String.valueOf(shomare.charAt(7)))
                    + Integer.parseInt(String.valueOf(shomare.charAt(8))) * 2
                    + Integer.parseInt(String.valueOf(shomare.charAt(9))) * 3
                    + Integer.parseInt(String.valueOf(shomare.charAt(10))) * 4
                    + Integer.parseInt(String.valueOf(shomare.charAt(11))) * 5
                    + Integer.parseInt(String.valueOf(shomare.charAt(12))) * 6
                    + Integer.parseInt(String.valueOf(shomare.charAt(13))) * 7
                    + Integer.parseInt(String.valueOf(shomare.charAt(14))) * 8;
            int checkNumber = sum % 99;
            return shomare + StringUtils.leftPad(String.valueOf(checkNumber), 2, "0");
    }

    public String generateNextShomareMoshtari(Namayande namayande, String typeSystem,String format,Long credebitAmount)
    {
        synchronized (SequenceService.class) {
            if (format.equals("120")) {
                if(credebitAmount==null)
                    return generateNextShomareMoshtariTypeA(namayande, typeSystem);
                else
                    return generateNextShomareMoshtariTypeD(namayande, typeSystem,"0185007111", credebitAmount);
            }else if (format.equals("110") || format.equals("130")) {
                return generateNextShomareMoshtariTypeA(namayande, typeSystem);
            } else if (format.equals("241")) {
                return generateNextShomareMoshtariTypeC(namayande, typeSystem, "2177777733");
            } else if (format.equals("242")) {
                return generateNextShomareMoshtariTypeC(namayande, typeSystem, "4757575763");
            } else if (format.equals("220") || format.equals("230")) {
                return generateNextShomareMoshtariTypeB(namayande, typeSystem);
            } else
                return "Error";
        }
    }

    public boolean isShomareMoshtariValid(String number,String format)
    {
        Namayande namayande =namayandeDAO.getNamayandeByIssuanceCode(number.substring(0,4));
        if(namayande==null)
            return false;
        else
        {
            if(format.equals("110") || format.equals("120") || format.equals("130"))
            {
                return isShomareMoshtariTypeAValid(number,namayande);
            }
            else if(format.equals("241"))
            {
                return isShomareMoshtariTypeCValid(number,"2177777733",namayande);
            }
            else if(format.equals("242"))
            {
                return isShomareMoshtariTypeCValid(number,"4757575763",namayande);
            }
            else if(format.equals("220") || format.equals("230"))
            {
                return isShomareMoshtariTypeBValid(number,namayande);
            }
            else
                return false;
        }
    }

    public boolean isShomareMoshtariTypeAValid(String number,Namayande namayande)
    {
        if(number.length()<17 || number.length()>17)
        {
            return false;
        }
        else
        {
            int sum = Integer.parseInt(String.valueOf(number.charAt(0))) * 15
            + Integer.parseInt(String.valueOf(number.charAt(1))) * 14
            + Integer.parseInt(String.valueOf(number.charAt(2))) * 13
            + Integer.parseInt(String.valueOf(number.charAt(3))) * 12
            + Integer.parseInt(String.valueOf(number.charAt(4))) * 11
            + Integer.parseInt(String.valueOf(number.charAt(5))) * 10
            + Integer.parseInt(String.valueOf(number.charAt(6))) * 9
            + Integer.parseInt(String.valueOf(number.charAt(7)))
            + Integer.parseInt(String.valueOf(number.charAt(8))) * 2
            + Integer.parseInt(String.valueOf(number.charAt(9))) * 3
            + Integer.parseInt(String.valueOf(number.charAt(10))) * 4
            + Integer.parseInt(String.valueOf(number.charAt(11))) * 5
            + Integer.parseInt(String.valueOf(number.charAt(12))) * 6
            + Integer.parseInt(String.valueOf(number.charAt(13))) * 7
            + Integer.parseInt(String.valueOf(number.charAt(14))) * 8;

            String digitCode=((Integer)(sum % 99)).toString();
            String checkDigit="";
            if(digitCode.length()<2)
                checkDigit = "0" + digitCode;
            else
                checkDigit = digitCode;
            if(checkDigit.equals(number.substring(15, 17)))
            {
//                String sequence = number.substring(8,14);
//                String year = number.substring(4,6);
//                if(sequenceDAO.isAvailableSequenceForNamayande(namayande.getId(),year,sequence))
//                    return true;
//                else
//                    return false;
                return true;
            }
            else
                return false;
        }
    }

    public boolean isShomareMoshtariTypeBValid(String number,Namayande namayande)
    {
        if(number.length()<15 || number.length()>15)
        {
            return false;
        }
        else
        {
            String mokamel9 = Long.toString((10000000000000L - Long.parseLong(number.substring(0,13))) - 1);
            int sum=0;
            for(int i=0;i<mokamel9.length();i++)
            {
                sum += Integer.parseInt(String.valueOf(mokamel9.charAt(i))) * (i+1);
            }

            String sumStr=Integer.toString(sum);
            String checkDigit="";
            if(sumStr.length()<2)
                checkDigit = "0" + sumStr;
            else
                checkDigit = sumStr.substring(sumStr.length()-2,sumStr.length());

            if(checkDigit.equals(number.substring(13, 15)))
            {
//                String sequence = number.substring(7,13);
//                String year = number.substring(4,6);
//                if(sequenceDAO.isAvailableSequenceForNamayande(namayande.getId(),year,sequence))
//                    return true;
//                else
//                    return false;
                return true;
            }
            else
                return false;
        }
    }

    public boolean isShomareMoshtariTypeCValid(String number,String shomareHesab,Namayande namayande)
    {
        if(number.length()<17 || number.length()>17)
        {
            return false;
        }
        else
        {
            int sumNum  = Integer.parseInt(String.valueOf(number.charAt(0))) * 3
                    + Integer.parseInt(String.valueOf(number.charAt(1))) * 5
                    + Integer.parseInt(String.valueOf(number.charAt(2))) * 7
                    + Integer.parseInt(String.valueOf(number.charAt(3))) * 11
                    + Integer.parseInt(String.valueOf(number.charAt(4))) * 13
                    + Integer.parseInt(String.valueOf(number.charAt(5))) * 17
                    + Integer.parseInt(String.valueOf(number.charAt(6))) * 19
                    + Integer.parseInt(String.valueOf(number.charAt(7))) * 23
                    + Integer.parseInt(String.valueOf(number.charAt(8))) * 29
                    + Integer.parseInt(String.valueOf(number.charAt(9))) * 31
                    + Integer.parseInt(String.valueOf(number.charAt(10))) * 37
                    + Integer.parseInt(String.valueOf(number.charAt(11))) * 41
                    + Integer.parseInt(String.valueOf(number.charAt(12))) * 43
                    + Integer.parseInt(String.valueOf(number.charAt(13))) * 47
                    + Integer.parseInt(String.valueOf(number.charAt(14))) * 53;

            int sumShomareHesab = Integer.parseInt(String.valueOf(shomareHesab.charAt(0))) * 5
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(1))) * 7
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(2))) * 11
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(3))) * 13
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(4))) * 17
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(5))) * 19
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(6))) * 23
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(7))) * 29
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(8))) * 31
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(9))) * 37;
            String checkDigit= ((Integer )((sumNum + sumShomareHesab) % 99)).toString();
            if(checkDigit.length() == 1) checkDigit = "0" + checkDigit;
            if(checkDigit.equals(number.substring(15, 17)))
            {
//                String sequence = number.substring(8,14);
//                String year = number.substring(4,6);
//                if(sequenceDAO.isAvailableSequenceForNamayande(namayande.getId(),year,sequence))
//                    return true;
//                else
//                    return false;
                return true;
            }
            else
                return false;
        }
    }

    public boolean isShomareMoshtariTypeDValid(String number,String shomareHesab,Long amount,Namayande namayande)
    {
        if(number.length()<17 || number.length()>17)
        {
            return false;
        }
        else
        {
            int sumNum  = Integer.parseInt(String.valueOf(number.charAt(0))) * 3
                        + Integer.parseInt(String.valueOf(number.charAt(1))) * 5
                        + Integer.parseInt(String.valueOf(number.charAt(2))) * 7
                        + Integer.parseInt(String.valueOf(number.charAt(3))) * 11
                        + Integer.parseInt(String.valueOf(number.charAt(4))) * 13
                        + Integer.parseInt(String.valueOf(number.charAt(5))) * 17
                        + Integer.parseInt(String.valueOf(number.charAt(6))) * 19
                        + Integer.parseInt(String.valueOf(number.charAt(7))) * 23
                        + Integer.parseInt(String.valueOf(number.charAt(8))) * 29
                        + Integer.parseInt(String.valueOf(number.charAt(9))) * 31
                        + Integer.parseInt(String.valueOf(number.charAt(10))) * 37
                        + Integer.parseInt(String.valueOf(number.charAt(11))) * 41
                        + Integer.parseInt(String.valueOf(number.charAt(12))) * 43
                        + Integer.parseInt(String.valueOf(number.charAt(13))) * 47
                        + Integer.parseInt(String.valueOf(number.charAt(14))) * 53;

            int sumShomareHesab = Integer.parseInt(String.valueOf(shomareHesab.charAt(0))) * 5
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(1))) * 7
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(2))) * 11
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(3))) * 13
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(4))) * 17
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(5))) * 19
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(6))) * 23
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(7))) * 29
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(8))) * 31
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(9))) * 37;

            Integer[] primes = new Integer[]{7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61};
            int sumAmount = 0;
            for (int i = amount.toString().length() - 1; i >= 0; i--)
            {
                sumAmount += Integer.parseInt(String.valueOf(amount.toString().charAt(i))) * primes[i];
            }

            String checkDigit= ((Integer )((sumNum + sumShomareHesab + sumAmount) % 99)).toString();
            if(checkDigit.length() == 1) checkDigit = "0" + checkDigit;
            if(checkDigit.equals(number.substring(15, 17)))
            {
//                String sequence = number.substring(8,14);
//                String year = number.substring(4,6);
//                if(sequenceDAO.isAvailableSequenceForNamayande(namayande.getId(),year,sequence))
//                    return true;
//                else
//                    return false;
                return true;
            }
            else
                return false;
        }
    }

    public String generateNextShomareMoshtariTypeC(Namayande namayande, String typeSystem, String shomareHesab)
    {
        synchronized (SequenceService.class) {
            String number = namayande.getIssuanceCode() + DateUtil.getCurrentDate().substring(2, 4) + typeSystem;
           /* String serialLast = sequenceDAO.getLastSequence(namayande.getId(), DateUtil.getCurrentDate().substring(2, 4));
            String serial = "";
            if (serialLast == null) {
                serial += "000001";
            }*/
            String serialLast = sequenceDAO.getSequenceCountSPNamayandeYear("SUB_SHOMARE_MOSHTARI",namayande.getId(), DateUtil.getCurrentDate().substring(2, 4));
            String serial = "";
            if(serialLast.startsWith("ERR1")) {
                Sequence subShomareMoshtariSequence = new Sequence("SUB_SHOMARE_MOSHTARI", "000002", namayande, DateUtil.getCurrentDate().substring(2, 4));
                sequenceDAO.updateSequence(subShomareMoshtariSequence);
                serial = "000001";
            }
            /*if (serialLast == null) {
                serial += "000001";
            }*/ else {
                serial += ((Integer) (Integer.parseInt(serialLast) + 1)).toString();
                if (serial.length() < 6) {
                    String var = "";
                    for (int i = 0; i < (6 - serial.length()); i++) {
                        var += "0";
                    }
                    var += serial;
                    serial = var;
                }
            }
          /*  Sequence sequence = new Sequence("SUB_SHOMARE_MOSHTARI", serial, namayande, DateUtil.getCurrentDate().substring(2, 4));
            sequenceDAO.save(sequence);*/
            number += "0" + serial + "1";
            int sumNum = Integer.parseInt(String.valueOf(number.charAt(0))) * 3
                    + Integer.parseInt(String.valueOf(number.charAt(1))) * 5
                    + Integer.parseInt(String.valueOf(number.charAt(2))) * 7
                    + Integer.parseInt(String.valueOf(number.charAt(3))) * 11
                    + Integer.parseInt(String.valueOf(number.charAt(4))) * 13
                    + Integer.parseInt(String.valueOf(number.charAt(5))) * 17
                    + Integer.parseInt(String.valueOf(number.charAt(6))) * 19
                    + Integer.parseInt(String.valueOf(number.charAt(7))) * 23
                    + Integer.parseInt(String.valueOf(number.charAt(8))) * 29
                    + Integer.parseInt(String.valueOf(number.charAt(9))) * 31
                    + Integer.parseInt(String.valueOf(number.charAt(10))) * 37
                    + Integer.parseInt(String.valueOf(number.charAt(11))) * 41
                    + Integer.parseInt(String.valueOf(number.charAt(12))) * 43
                    + Integer.parseInt(String.valueOf(number.charAt(13))) * 47
                    + Integer.parseInt(String.valueOf(number.charAt(14))) * 53;

            int sumShomareHesab = Integer.parseInt(String.valueOf(shomareHesab.charAt(0))) * 5
                    + Integer.parseInt(String.valueOf(shomareHesab.charAt(1))) * 7
                    + Integer.parseInt(String.valueOf(shomareHesab.charAt(2))) * 11
                    + Integer.parseInt(String.valueOf(shomareHesab.charAt(3))) * 13
                    + Integer.parseInt(String.valueOf(shomareHesab.charAt(4))) * 17
                    + Integer.parseInt(String.valueOf(shomareHesab.charAt(5))) * 19
                    + Integer.parseInt(String.valueOf(shomareHesab.charAt(6))) * 23
                    + Integer.parseInt(String.valueOf(shomareHesab.charAt(7))) * 29
                    + Integer.parseInt(String.valueOf(shomareHesab.charAt(8))) * 31
                    + Integer.parseInt(String.valueOf(shomareHesab.charAt(9))) * 37;
            String checkDigit = ((Integer) ((sumNum + sumShomareHesab) % 99)).toString();
            if (checkDigit.length() < 2) {
                String digitCode = "0" + checkDigit;
                number += digitCode;
            } else
                number += checkDigit;
            sequenceDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
            return number;
        }
    }
    //new Type Tejarat 0185007111
    public String generateNextShomareMoshtariTypeD(Namayande namayande, String typeSystem, String shomareHesab, Long amount)
    {
        synchronized (SequenceService.class)
        {
            String number = namayande.getIssuanceCode() + DateUtil.getCurrentDate().substring(2, 4) + typeSystem;

            String serialLast = sequenceDAO.getSequenceCountSPNamayandeYear("SUB_SHOMARE_MOSHTARI",namayande.getId(), DateUtil.getCurrentDate().substring(2, 4));
            String serial = "";
            if(serialLast.startsWith("ERR1"))
            {
                Sequence subShomareMoshtariSequence = new Sequence("SUB_SHOMARE_MOSHTARI", "000002", namayande, DateUtil.getCurrentDate().substring(2, 4));
                sequenceDAO.updateSequence(subShomareMoshtariSequence);
                serial = "000001";
            }
            else
            {
                serial += ((Integer) (Integer.parseInt(serialLast) + 1)).toString();
                if (serial.length() < 6) {
                    String var = "";
                    for (int i = 0; i < (6 - serial.length()); i++) {
                        var += "0";
                    }
                    var += serial;
                    serial = var;
                }
            }

            number += "10" + serial ;
            int sumNum = Integer.parseInt(String.valueOf(number.charAt(0))) * 3
                       + Integer.parseInt(String.valueOf(number.charAt(1))) * 5
                       + Integer.parseInt(String.valueOf(number.charAt(2))) * 7
                       + Integer.parseInt(String.valueOf(number.charAt(3))) * 11
                       + Integer.parseInt(String.valueOf(number.charAt(4))) * 13
                       + Integer.parseInt(String.valueOf(number.charAt(5))) * 17
                       + Integer.parseInt(String.valueOf(number.charAt(6))) * 19
                       + Integer.parseInt(String.valueOf(number.charAt(7))) * 23
                       + Integer.parseInt(String.valueOf(number.charAt(8))) * 29
                       + Integer.parseInt(String.valueOf(number.charAt(9))) * 31
                       + Integer.parseInt(String.valueOf(number.charAt(10))) * 37
                       + Integer.parseInt(String.valueOf(number.charAt(11))) * 41
                       + Integer.parseInt(String.valueOf(number.charAt(12))) * 43
                       + Integer.parseInt(String.valueOf(number.charAt(13))) * 47
                       + Integer.parseInt(String.valueOf(number.charAt(14))) * 53;

            int sumShomareHesab = Integer.parseInt(String.valueOf(shomareHesab.charAt(0))) * 5
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(1))) * 7
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(2))) * 11
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(3))) * 13
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(4))) * 17
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(5))) * 19
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(6))) * 23
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(7))) * 29
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(8))) * 31
                                + Integer.parseInt(String.valueOf(shomareHesab.charAt(9))) * 37;

            Integer[] primes=new Integer[]{7,11,13,17,19,23,29,31,37,41,43,47,53,59,61};
            int sumAmount =0;
            int j=primes.length-1;
            for(int i=amount.toString().length()-1;i>=0;i--)
            {
                sumAmount += Integer.parseInt(String.valueOf(amount.toString().charAt(i)))* primes[j];
                j--;
            }

            String checkDigit = ((Integer) ((sumNum + sumShomareHesab + sumAmount) % 99)).toString();
            if (checkDigit.length() < 2)
            {
                String digitCode = "0" + checkDigit;
                number += digitCode;
            }
            else
                number += checkDigit;
            sequenceDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
            return number;
        }
    }

    public String generateNextShomareMoshtariTypeB(Namayande namayande, String typeSystem)
    {
        synchronized (SequenceService.class) {
            String number = namayande.getIssuanceCode() + DateUtil.getCurrentDate().substring(2, 4) + typeSystem;
//            String serialLast = sequenceDAO.getLastSequence(namayande.getId(), DateUtil.getCurrentDate().substring(2, 4));
            String serialLast = sequenceDAO.getSequenceCountSPNamayandeYear("SUB_SHOMARE_MOSHTARI",namayande.getId(), DateUtil.getCurrentDate().substring(2, 4));
            String serial = "";
            if(serialLast.startsWith("ERR1")) {
                Sequence subShomareMoshtariSequence = new Sequence("SUB_SHOMARE_MOSHTARI", "000002", namayande, DateUtil.getCurrentDate().substring(2, 4));
                sequenceDAO.updateSequence(subShomareMoshtariSequence);
                serial = "000001";
            }
            /*if (serialLast == null) {
                serial += "000001";
            }*/ else {
                serial += ((Integer) (Integer.parseInt(serialLast) + 1)).toString();
                if (serial.length() < 6) {
                    String var = "";
                    for (int i = 0; i < (6 - serial.length()); i++) {
                        var += "0";
                    }
                    var += serial;
                    serial = var;
                }
            }
          //  Sequence sequence = new Sequence("SUB_SHOMARE_MOSHTARI", serial, namayande, DateUtil.getCurrentDate().substring(2, 4));
//            sequenceDAO.save(sequence);
            number += serial;
            String mokamel9 = Long.toString(((10000000000000L) - Long.parseLong(number)) - 1);

            //if isuanceCode like '9%' or '99%' or ...
            int differenceSize = number.length() - mokamel9.length();
            for (int i=0; i < differenceSize; i++)
                mokamel9 = "0" + mokamel9;

            int sum = 0;
            for (int i = 0; i < mokamel9.length(); i++) {
                sum += Integer.parseInt(String.valueOf(mokamel9.charAt(i))) * (i + 1);
            }

            String sumStr = Integer.toString(sum);
            if (sumStr.length() < 2)
                number += "0" + sumStr;
            else
                number += sumStr.substring(sumStr.length() - 2, sumStr.length());

            sequenceDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
            return number;
        }
    }

    public String generateNextShomareMoshtariTypeA(Namayande namayande, String typeSystem)
    {
        synchronized (SequenceService.class) {
            String number = namayande.getIssuanceCode() + DateUtil.getCurrentDate().substring(2, 4) + typeSystem;
      /*      String serialLast = sequenceDAO.getLastSequence(namayande.getId(), DateUtil.getCurrentDate().substring(2, 4));
            String serial = "";
            if (serialLast == null) {
                serial = "000001";
            } */
            String serialLast = sequenceDAO.getSequenceCountSPNamayandeYear("SUB_SHOMARE_MOSHTARI",namayande.getId(), DateUtil.getCurrentDate().substring(2, 4));
            String serial = "";
            if(serialLast.startsWith("ERR1")) {
                Sequence subShomareMoshtariSequence = new Sequence("SUB_SHOMARE_MOSHTARI", "000002", namayande, DateUtil.getCurrentDate().substring(2, 4));
                sequenceDAO.updateSequence(subShomareMoshtariSequence);
                serial = "000001";
            }else {
                serial = ((Integer) (Integer.parseInt(serialLast) + 1)).toString();
                if (serial.length() < 6) {
                    String var = "";
                    for (int i = 0; i < (6 - serial.length()); i++) {
                        var += "0";
                    }
                    var += serial;
                    serial = var;
                }
            }
           /* Sequence sequence = new Sequence("SUB_SHOMARE_MOSHTARI", serial, namayande, DateUtil.getCurrentDate().substring(2, 4));
            sequenceDAO.save(sequence);*/
            number += "00" + serial;
            int sum = Integer.parseInt(String.valueOf(number.charAt(0))) * 15
                    + Integer.parseInt(String.valueOf(number.charAt(1))) * 14
                    + Integer.parseInt(String.valueOf(number.charAt(2))) * 13
                    + Integer.parseInt(String.valueOf(number.charAt(3))) * 12
                    + Integer.parseInt(String.valueOf(number.charAt(4))) * 11
                    + Integer.parseInt(String.valueOf(number.charAt(5))) * 10
                    + Integer.parseInt(String.valueOf(number.charAt(6))) * 9
                    + Integer.parseInt(String.valueOf(number.charAt(7)))
                    + Integer.parseInt(String.valueOf(number.charAt(8))) * 2
                    + Integer.parseInt(String.valueOf(number.charAt(9))) * 3
                    + Integer.parseInt(String.valueOf(number.charAt(10))) * 4
                    + Integer.parseInt(String.valueOf(number.charAt(11))) * 5
                    + Integer.parseInt(String.valueOf(number.charAt(12))) * 6
                    + Integer.parseInt(String.valueOf(number.charAt(13))) * 7
                    + Integer.parseInt(String.valueOf(number.charAt(14))) * 8;
            String digitCode = ((Integer) (sum % 99)).toString();
            if (digitCode.length() < 2) {
                String checkDigit = "0" + digitCode;
                number += checkDigit;
            } else
                number += digitCode;
            sequenceDAO.getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
            return number;
        }
    }

    public void setNamayandeDAO(NamayandeDAO namayandeDAO)
    {
        this.namayandeDAO = namayandeDAO;
    }

    public NamayandeDAO getNamayandeDAO()
    {
        return namayandeDAO;
    }
}
