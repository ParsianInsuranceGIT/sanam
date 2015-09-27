package com.bitarts.parsian.service;

import com.bitarts.parsian.model.Namayande;
import com.bitarts.parsian.model.bimename.Bimename;

/**
 * Created by IntelliJ IDEA.
 * User: Arron0
 * Date: Aug 9, 2011
 * Time: 12:23:10 AM
 */
public interface ISequenceService extends IBaseService  {
    public static final String SERVICE_NAME = "sequenceService";

    public boolean isRepetitiousIssuanceCode(String issuanceCode);

    public long getBankInfoSerialId();

    public String nextShomareBimename(Namayande vahedSodur);

    public String nextShomareRadif(Namayande namayande);

    public String nextShomareDarkhastBazkharid();

    public String nextShomareVam(Namayande vahedSodor);

    public String nextShomareSerialePrintBimename();

    public String nextShomareRadifElhaghie();

    public String nextShomareSerialeBimename(Namayande vahedSodor);

    public String nextShomareBahremandiAzManafe(Bimename bimename);

    public String nextShomareElhaghiye(Bimename bimename);

    public String nextShenaseCredebit();

    public String nextShomareMoshtari(String codeVahedSodoor, String serial, String datePart, String typeString);

    public String generateNextShomareMoshtari(Namayande namayande, String typeSystem,String format,Long credebitAmount);

    public String nextShomareMosharekat();

    public String nextShomareSanad();

    public boolean isShomareMoshtariValid(String number,String format);
}
