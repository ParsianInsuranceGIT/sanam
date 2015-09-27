package com.bitarts.parsian.service.implementation;

import com.bitarts.common.util.DateUtil;
import com.bitarts.parsian.dao.LogDAO;
import com.bitarts.parsian.model.User;
import com.bitarts.parsian.model.bimename.Darkhast;
import com.bitarts.parsian.model.bimename.DarkhastTaghirat;
import com.bitarts.parsian.model.bimename.Elhaghiye;
import com.bitarts.parsian.model.constantItems.ConstantSoalateVaziateSalamati;
import com.bitarts.parsian.model.log.PishnehadChangeHistoryLog;
import com.bitarts.parsian.model.log.PishnehadFields;
import com.bitarts.parsian.model.pishnahad.*;
import com.bitarts.parsian.service.IConstantItemsService;
import com.bitarts.parsian.service.ILogService;
import com.bitarts.parsian.viewModel.PishnehadFieldChanges;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/25/11
 * Time: 2:51 PM
 */
public class LogService implements ILogService {
    private LogDAO logDAO;
    private IConstantItemsService constantItemsService;

    public void setLogDAO(LogDAO logDAO) {
        this.logDAO = logDAO;
    }


    public void logPishnehadChanges(Pishnehad oldPishnehad, Pishnehad newPishnehad, User user) {
        List<PishnehadFields> pishnehadFieldsList = logDAO.findAllFields();
        for (PishnehadFields field : pishnehadFieldsList) {
            Serializable fromObjValue = getValueOfProperty(oldPishnehad, field.getField());
            Serializable toObjValue = getValueOfProperty(newPishnehad, field.getField());
            if (fromObjValue != null && toObjValue != null && !fromObjValue.equals(toObjValue)) {
                logRecord(user, oldPishnehad, field, fromObjValue.toString(), toObjValue.toString());
            }
        }
    }

    public Set<PishnehadChangeHistoryLog> getPishnehadHistoryLogs(Integer pishnehadIdInteger) {
        return logDAO.findHistoryLogsByPishnehadId(pishnehadIdInteger);
    }

    @Transactional
    public List<PishnehadFieldChanges> getPishnehadChangesList(Pishnehad oldPishnehad, Pishnehad pishnehad) {
        List<PishnehadFieldChanges> pishnehadFieldChangesList = new ArrayList<PishnehadFieldChanges>();
        List<PishnehadFieldChanges> condidateList = new ArrayList<PishnehadFieldChanges>();

//        condidateList.add(new PishnehadFieldChanges("بیمه گذار",oldPishnehad.getBimeGozar().getId().toString(),pishnehad.getBimeGozar().getId().toString()));
//        condidateList.add(new PishnehadFieldChanges("بیمه شده",oldPishnehad.getBimeShode().getId().toString(),pishnehad.getBimeShode().getId().toString()));

        if (oldPishnehad.getBimeGozar().getShakhs().getType().equals(Shakhs.BimeGozarType.HOGHOGHI)) {

//            condidateList.add(new PishnehadFieldChanges("درآمد ماهیانه بیمه گذار", oldPishnehad.getBimeGozar().getDaramadeMahiane().toString(), pishnehad.getBimeGozar().getDaramadeMahiane().toString()));
            condidateList.add(new PishnehadFieldChanges("نسبت بیمه گذار با بیمه شده", oldPishnehad.getBimeGozar().getNesbatBabimeShode(), pishnehad.getBimeGozar().getNesbatBabimeShode(),"NESBAT"));
            condidateList.add(new PishnehadFieldChanges("بیمه گذار", oldPishnehad.getBimeGozar().getShakhs().getFullName(), pishnehad.getBimeGozar().getShakhs().getFullName(), "BG"));
//            condidateList.add(new PishnehadFieldChanges("استان محل زندگی بیمه گذار", String.valueOf(oldPishnehad.getAddressBimeGozar().getOstaanManzel().getCityName()), String.valueOf(pishnehad.getAddressBimeGozar().getOstaanManzel().getCityName())));
//            condidateList.add(new PishnehadFieldChanges("شهر محل زندگی بیمه گذار", String.valueOf(oldPishnehad.getAddressBimeGozar().getShahrManzel().getCityName()), String.valueOf(pishnehad.getAddressBimeGozar().getShahrManzel().getCityName())));
//            condidateList.add(new PishnehadFieldChanges("نشانی محل زندگی بیمه گذار", oldPishnehad.getAddressBimeGozar().getNeshaniManzel(), pishnehad.getAddressBimeGozar().getNeshaniManzel()));
//            condidateList.add(new PishnehadFieldChanges("کد پستی محل زندگی بیمه گذار", oldPishnehad.getAddressBimeGozar().getKodePostiManzel(), pishnehad.getAddressBimeGozar().getKodePostiManzel()));
            condidateList.add(new PishnehadFieldChanges("پست الکترونیک بیمه گذار", oldPishnehad.getAddressBimeGozar().getPosteElectronic(), pishnehad.getAddressBimeGozar().getPosteElectronic(), "AD"));
//            condidateList.add(new PishnehadFieldChanges("تلفن منزل بیمه گذار", oldPishnehad.getAddressBimeGozar().getTelephoneManzel(), pishnehad.getAddressBimeGozar().getTelephoneManzel()));
//            condidateList.add(new PishnehadFieldChanges("کد تلفن منزل بیمه گذار", oldPishnehad.getAddressBimeGozar().getCodeTelephoneManzel(), pishnehad.getAddressBimeGozar().getCodeTelephoneManzel()));
            condidateList.add(new PishnehadFieldChanges("تلفن همراه بیمه گذار", oldPishnehad.getAddressBimeGozar().getTelephoneHamrah(), pishnehad.getAddressBimeGozar().getTelephoneHamrah(), "AD"));
            condidateList.add(new PishnehadFieldChanges("استان محل کار بیمه گذار", String.valueOf(oldPishnehad.getAddressBimeGozar().getOstaanMahalleKar().getCityName()), String.valueOf(pishnehad.getAddressBimeGozar().getOstaanMahalleKar().getCityName()), "AD"));
            condidateList.add(new PishnehadFieldChanges("شهر محل کار بیمه گذار", String.valueOf(oldPishnehad.getAddressBimeGozar().getShahrMahalleKar().getCityName()), String.valueOf(pishnehad.getAddressBimeGozar().getShahrMahalleKar().getCityName()), "AD"));
            condidateList.add(new PishnehadFieldChanges("نشانی محل کار بیمه گذار", oldPishnehad.getAddressBimeGozar().getNeshaniMahalleKar(), pishnehad.getAddressBimeGozar().getNeshaniMahalleKar(), "AD"));
            condidateList.add(new PishnehadFieldChanges("کد پستی محل کار بیمه گذار", oldPishnehad.getAddressBimeGozar().getKodePostiMahallekar(), pishnehad.getAddressBimeGozar().getKodePostiMahallekar(), "AD"));
            condidateList.add(new PishnehadFieldChanges("تلفن محل کار بیمه گذار", oldPishnehad.getAddressBimeGozar().getTelephoneMahalleKar(), pishnehad.getAddressBimeGozar().getTelephoneMahalleKar(), "AD"));
            condidateList.add(new PishnehadFieldChanges("کد تلفن محل کار بیمه گذار", oldPishnehad.getAddressBimeGozar().getCodeTelephoneMahalleKar(), pishnehad.getAddressBimeGozar().getCodeTelephoneMahalleKar(), "AD"));
        } else {
            condidateList.add(new PishnehadFieldChanges("نسبت بیمه گذار با بیمه شده", oldPishnehad.getBimeGozar().getNesbatBabimeShode(), pishnehad.getBimeGozar().getNesbatBabimeShode(),"NESBAT"));
            condidateList.add(new PishnehadFieldChanges("بیمه گذار", oldPishnehad.getBimeGozar().getShakhs().getFullName(), pishnehad.getBimeGozar().getShakhs().getFullName(), "BG"));
            condidateList.add(new PishnehadFieldChanges("نام پدر بیمه گذار",oldPishnehad.getBimeGozar().getShakhs().getNamePedar(),pishnehad.getBimeGozar().getShakhs().getNamePedar(), "BG"));
            condidateList.add(new PishnehadFieldChanges("جنسیت بیمه گذار",oldPishnehad.getBimeGozar().getShakhs().getJensiat(),pishnehad.getBimeGozar().getShakhs().getJensiat(), "BG"));
            condidateList.add(new PishnehadFieldChanges("وضعیت تاهل بیمه گذار",oldPishnehad.getBimeGozar().getShakhs().getVaziateTaahol(),pishnehad.getBimeGozar().getShakhs().getVaziateTaahol(), "BG"));
            condidateList.add(new PishnehadFieldChanges("کد ملی بیمه گذار", oldPishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi(), pishnehad.getBimeGozar().getShakhs().getKodeMelliShenasayi(), "BG"));
            condidateList.add(new PishnehadFieldChanges("شماره شناسنامه بیمه گذار",oldPishnehad.getBimeGozar().getShakhs().getShomareShenasnameh(),pishnehad.getBimeGozar().getShakhs().getShomareShenasnameh(), "BG"));
            condidateList.add(new PishnehadFieldChanges("محل صدور شناسنامه بیمه گذار",oldPishnehad.getBimeGozar().getShakhs().getMahalleSodoreShenasnameh().getCityName(),pishnehad.getBimeGozar().getShakhs().getMahalleSodoreShenasnameh().getCityName(), "BG"));
            condidateList.add(new PishnehadFieldChanges("تاریخ تولد بیمه گذار",oldPishnehad.getBimeGozar().getShakhs().getTarikheTavallod(),pishnehad.getBimeGozar().getShakhs().getTarikheTavallod(), "BG"));
            condidateList.add(new PishnehadFieldChanges("محل تولد بیمه گذار",oldPishnehad.getBimeGozar().getShakhs().getMahalleTavallod().getCityName(),pishnehad.getBimeGozar().getShakhs().getMahalleTavallod().getCityName(), "BG"));
            condidateList.add(new PishnehadFieldChanges("شغل اصلی بیمه گذار",oldPishnehad.getBimeGozar().getShakhs().getShoghleAsli(),pishnehad.getBimeGozar().getShakhs().getShoghleAsli(), "BG"));
            condidateList.add(new PishnehadFieldChanges("شغل فرعی بیمه گذار",oldPishnehad.getBimeGozar().getShakhs().getShoghleFarei(),pishnehad.getBimeGozar().getShakhs().getShoghleFarei(), "BG"));
            condidateList.add(new PishnehadFieldChanges("پست الکترونیک بیمه گذار", oldPishnehad.getAddressBimeGozar().getPosteElectronic(), pishnehad.getAddressBimeGozar().getPosteElectronic(), "AD"));
            condidateList.add(new PishnehadFieldChanges("تلفن همراه بیمه گذار", oldPishnehad.getAddressBimeGozar().getTelephoneHamrah(), pishnehad.getAddressBimeGozar().getTelephoneHamrah(), "AD"));
            condidateList.add(new PishnehadFieldChanges("استان محل کار بیمه گذار", String.valueOf(oldPishnehad.getAddressBimeGozar().getOstaanMahalleKar().getCityName()), String.valueOf(pishnehad.getAddressBimeGozar().getOstaanMahalleKar().getCityName()), "AD"));
            condidateList.add(new PishnehadFieldChanges("شهر محل کار بیمه گذار", String.valueOf(oldPishnehad.getAddressBimeGozar().getShahrMahalleKar().getCityName()), String.valueOf(pishnehad.getAddressBimeGozar().getShahrMahalleKar().getCityName()), "AD"));
            condidateList.add(new PishnehadFieldChanges("نشانی محل کار بیمه گذار", oldPishnehad.getAddressBimeGozar().getNeshaniMahalleKar(), pishnehad.getAddressBimeGozar().getNeshaniMahalleKar(), "AD"));
            condidateList.add(new PishnehadFieldChanges("کد پستی محل کار بیمه گذار", oldPishnehad.getAddressBimeGozar().getKodePostiMahallekar(), pishnehad.getAddressBimeGozar().getKodePostiMahallekar(), "AD"));
            condidateList.add(new PishnehadFieldChanges("تلفن محل کار بیمه گذار", oldPishnehad.getAddressBimeGozar().getTelephoneMahalleKar(), pishnehad.getAddressBimeGozar().getTelephoneMahalleKar(), "AD"));
            condidateList.add(new PishnehadFieldChanges("کد تلفن محل کار بیمه گذار", oldPishnehad.getAddressBimeGozar().getCodeTelephoneMahalleKar(), pishnehad.getAddressBimeGozar().getCodeTelephoneMahalleKar(), "AD"));
            condidateList.add(new PishnehadFieldChanges("استان محل زندگی بیمه گذار", String.valueOf(oldPishnehad.getAddressBimeGozar().getOstaanManzel().getCityName()), String.valueOf(pishnehad.getAddressBimeGozar().getOstaanManzel().getCityName()),"AD"));
            condidateList.add(new PishnehadFieldChanges("شهر محل زندگی بیمه گذار", String.valueOf(oldPishnehad.getAddressBimeGozar().getShahrManzel().getCityName()), String.valueOf(pishnehad.getAddressBimeGozar().getShahrManzel().getCityName()),"AD"));
            condidateList.add(new PishnehadFieldChanges("نشانی محل زندگی بیمه گذار", oldPishnehad.getAddressBimeGozar().getNeshaniManzel(), pishnehad.getAddressBimeGozar().getNeshaniManzel(),"AD"));
            condidateList.add(new PishnehadFieldChanges("کد پستی محل زندگی بیمه گذار", oldPishnehad.getAddressBimeGozar().getKodePostiManzel(), pishnehad.getAddressBimeGozar().getKodePostiManzel(),"AD"));
            condidateList.add(new PishnehadFieldChanges("تلفن منزل بیمه گذار", oldPishnehad.getAddressBimeGozar().getTelephoneManzel(), pishnehad.getAddressBimeGozar().getTelephoneManzel(),"AD"));
            condidateList.add(new PishnehadFieldChanges("کد تلفن منزل بیمه گذار", oldPishnehad.getAddressBimeGozar().getCodeTelephoneManzel(), pishnehad.getAddressBimeGozar().getCodeTelephoneManzel(),"AD"));
            condidateList.add(new PishnehadFieldChanges("درآمد ماهیانه بیمه گذار", oldPishnehad.getBimeGozar().getDaramadeMahiane()!=null? oldPishnehad.getBimeGozar().getDaramadeMahiane().toString():null, pishnehad.getBimeGozar().getDaramadeMahiane()!=null?pishnehad.getBimeGozar().getDaramadeMahiane().toString():null,"DARAMAD"));

        }
        condidateList.add(new PishnehadFieldChanges("بیمه شده",oldPishnehad.getBimeShode().getShakhs().getFullName(), pishnehad.getBimeShode().getShakhs().getFullName(), "BS"));
        condidateList.add(new PishnehadFieldChanges("نام پدر بیمه شده",oldPishnehad.getBimeShode().getShakhs().getNamePedar(),pishnehad.getBimeShode().getShakhs().getNamePedar(), "BS"));
        condidateList.add(new PishnehadFieldChanges("جنسیت بیمه شده",oldPishnehad.getBimeShode().getShakhs().getJensiat(),pishnehad.getBimeShode().getShakhs().getJensiat(), "BS"));
        condidateList.add(new PishnehadFieldChanges("وضعیت تاهل بیمه شده",oldPishnehad.getBimeShode().getShakhs().getVaziateTaahol(),pishnehad.getBimeShode().getShakhs().getVaziateTaahol(), "BS"));
        condidateList.add(new PishnehadFieldChanges("کد ملی بیمه شده", oldPishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi(), pishnehad.getBimeShode().getShakhs().getKodeMelliShenasayi(), "BS"));
        condidateList.add(new PishnehadFieldChanges("شماره شناسنامه بیمه شده",oldPishnehad.getBimeShode().getShakhs().getShomareShenasnameh(),pishnehad.getBimeShode().getShakhs().getShomareShenasnameh(), "BS"));
        condidateList.add(new PishnehadFieldChanges("محل صدور شناسنامه بیمه شده",oldPishnehad.getBimeShode().getShakhs().getMahalleSodoreShenasnameh().getCityName(),pishnehad.getBimeShode().getShakhs().getMahalleSodoreShenasnameh().getCityName(), "BS"));
        condidateList.add(new PishnehadFieldChanges("تاریخ تولد بیمه شده",oldPishnehad.getBimeShode().getShakhs().getTarikheTavallod(),pishnehad.getBimeShode().getShakhs().getTarikheTavallod(), true, "BS"));
        condidateList.add(new PishnehadFieldChanges("محل تولد بیمه شده",oldPishnehad.getBimeShode().getShakhs().getMahalleTavallod().getCityName(),pishnehad.getBimeShode().getShakhs().getMahalleTavallod().getCityName(), "BS"));
        condidateList.add(new PishnehadFieldChanges("شغل اصلی بیمه شده",oldPishnehad.getBimeShode().getShakhs().getShoghleAsli(),pishnehad.getBimeShode().getShakhs().getShoghleAsli(), "BS"));
        condidateList.add(new PishnehadFieldChanges("شغل فرعی بیمه شده",oldPishnehad.getBimeShode().getShakhs().getShoghleFarei(),pishnehad.getBimeShode().getShakhs().getShoghleFarei(), "BS"));
        condidateList.add(new PishnehadFieldChanges("استان محل زندگی بیمه شده", String.valueOf(oldPishnehad.getAddressBimeShode().getOstaanManzel().getCityName()), String.valueOf(pishnehad.getAddressBimeShode().getOstaanManzel().getCityName()), "AD"));
        condidateList.add(new PishnehadFieldChanges("شهر محل زندگی بیمه شده", String.valueOf(oldPishnehad.getAddressBimeShode().getShahrManzel().getCityName()), String.valueOf(pishnehad.getAddressBimeShode().getShahrManzel().getCityName()), "AD"));
        condidateList.add(new PishnehadFieldChanges("نشانی محل زندگی بیمه شده", oldPishnehad.getAddressBimeShode().getNeshaniManzel(), pishnehad.getAddressBimeShode().getNeshaniManzel(), "AD"));
        condidateList.add(new PishnehadFieldChanges("کد پستی محل زندگی بیمه شده", oldPishnehad.getAddressBimeShode().getKodePostiManzel(), pishnehad.getAddressBimeShode().getKodePostiManzel(), "AD"));
        condidateList.add(new PishnehadFieldChanges("پست الکترونیک بیمه شده", oldPishnehad.getAddressBimeShode().getPosteElectronic(), pishnehad.getAddressBimeShode().getPosteElectronic(), "AD"));
        condidateList.add(new PishnehadFieldChanges("تلفن منزل بیمه شده", oldPishnehad.getAddressBimeShode().getTelephoneManzel(), pishnehad.getAddressBimeShode().getTelephoneManzel(), "AD"));
        condidateList.add(new PishnehadFieldChanges("کد تلفن منزل بیمه شده", oldPishnehad.getAddressBimeShode().getCodeTelephoneManzel(), pishnehad.getAddressBimeShode().getCodeTelephoneManzel(), "AD"));
        condidateList.add(new PishnehadFieldChanges("تلفن همراه بیمه شده", oldPishnehad.getAddressBimeShode().getTelephoneHamrah(), pishnehad.getAddressBimeShode().getTelephoneHamrah(), "AD"));
        condidateList.add(new PishnehadFieldChanges("استان محل کار بیمه شده", String.valueOf(oldPishnehad.getAddressBimeShode().getOstaanMahalleKar().getCityName()), String.valueOf(pishnehad.getAddressBimeShode().getOstaanMahalleKar().getCityName()), "AD"));
        condidateList.add(new PishnehadFieldChanges("شهر محل کار بیمه شده", String.valueOf(oldPishnehad.getAddressBimeShode().getShahrMahalleKar().getCityName()), String.valueOf(pishnehad.getAddressBimeShode().getShahrMahalleKar().getCityName()), "AD"));
        condidateList.add(new PishnehadFieldChanges("نشانی محل کار بیمه شده", oldPishnehad.getAddressBimeShode().getNeshaniMahalleKar(), pishnehad.getAddressBimeShode().getNeshaniMahalleKar(), "AD"));
        condidateList.add(new PishnehadFieldChanges("کد پستی محل کار بیمه شده", oldPishnehad.getAddressBimeShode().getKodePostiMahallekar(), pishnehad.getAddressBimeShode().getKodePostiMahallekar(), "AD"));
        condidateList.add(new PishnehadFieldChanges("تلفن محل کار بیمه شده", oldPishnehad.getAddressBimeShode().getTelephoneMahalleKar(), pishnehad.getAddressBimeShode().getTelephoneMahalleKar(), "AD"));
        condidateList.add(new PishnehadFieldChanges("کد تلفن محل کار بیمه شده", oldPishnehad.getAddressBimeShode().getCodeTelephoneMahalleKar(), pishnehad.getAddressBimeShode().getCodeTelephoneMahalleKar(), "AD"));

        condidateList.add(new PishnehadFieldChanges(" قد بیمه شده (سانتی متر)", oldPishnehad.getSoalateOmomiAzBimeShode().getGhad_bime_shode(), pishnehad.getSoalateOmomiAzBimeShode().getGhad_bime_shode()));
        condidateList.add(new PishnehadFieldChanges("وزن بیمه شده (کیلوگرم)", oldPishnehad.getSoalateOmomiAzBimeShode().getVazn_bime_shode(), pishnehad.getSoalateOmomiAzBimeShode().getVazn_bime_shode()));
        condidateList.add(new PishnehadFieldChanges(" وضعيت نظام وظيفه", oldPishnehad.getSoalateOmomiAzBimeShode().getKhdemat_nezaam_vazifeFarsi(), pishnehad.getSoalateOmomiAzBimeShode().getKhdemat_nezaam_vazifeFarsi()));
        condidateList.add(new PishnehadFieldChanges(" در صورت سایر بودن توضیحات آن، و در صورت معافیت پزشکی علت آن را توضیح دهید", oldPishnehad.getSoalateOmomiAzBimeShode().getMoaafiyat_pezeshki(), pishnehad.getSoalateOmomiAzBimeShode().getMoaafiyat_pezeshki()));
        condidateList.add(new PishnehadFieldChanges(" فعاليت جنبي مانند ورزش حرفه اي و مسافرت", oldPishnehad.getSoalateOmomiAzBimeShode().getTozih_faaliyat_janbi(), pishnehad.getSoalateOmomiAzBimeShode().getTozih_faaliyat_janbi()));

        condidateList.add(new PishnehadFieldChanges("فروشنده:  آیا پیشنهاد دهنده را شخصاً می شناسید؟", oldPishnehad.getForoshande().getPishnahaad_dahande_mishenaasid(), pishnehad.getForoshande().getPishnahaad_dahande_mishenaasid()));
        condidateList.add(new PishnehadFieldChanges("فروشنده:  از چه مدتی:", oldPishnehad.getForoshande().getModat_shenaakht(), pishnehad.getForoshande().getModat_shenaakht()));
        condidateList.add(new PishnehadFieldChanges("فروشنده:  آیا ملاحضات خاصی از سلامت فعلی یا گذشته او دارید؟", oldPishnehad.getForoshande().getMolaahezaat_az_salaamat_feli(), pishnehad.getForoshande().getMolaahezaat_az_salaamat_feli()));
        condidateList.add(new PishnehadFieldChanges("فروشنده: شرح دهيد", oldPishnehad.getForoshande().getTozihe_molaahezaat(), pishnehad.getForoshande().getTozihe_molaahezaat()));

        String rial = " ريال ";
        condidateList.add(new PishnehadFieldChanges("درصد افزايش سالانه سرمايه فوت", "%"+oldPishnehad.getEstelam().getNerkh_afzayesh_salaneh_sarmaye_fot(), "%"+pishnehad.getEstelam().getNerkh_afzayesh_salaneh_sarmaye_fot(), true));
        condidateList.add(new PishnehadFieldChanges("مبلغ سرمایه فوت", oldPishnehad.getEstelam().getSarmaye_paye_fot() + rial, pishnehad.getEstelam().getSarmaye_paye_fot() + rial, true));
        condidateList.add(new PishnehadFieldChanges("درصد افزایش حق بیمه", "%"+oldPishnehad.getEstelam().getNerkh_afzayesh_salaneh_hagh_bime(), "%"+pishnehad.getEstelam().getNerkh_afzayesh_salaneh_hagh_bime(), true));
        condidateList.add(new PishnehadFieldChanges("حق بیمه پرداختی", oldPishnehad.getEstelam().getHagh_bime_pardakhti() + rial, pishnehad.getEstelam().getHagh_bime_pardakhti() + rial, true));
        condidateList.add(new PishnehadFieldChanges("مدت بیمه نامه", oldPishnehad.getEstelam().getModat_bimename(), pishnehad.getEstelam().getModat_bimename(), false));
        condidateList.add(new PishnehadFieldChanges("نحوه ی پرداخت حق بیمه", oldPishnehad.getEstelam().getNahvePardakhtHaghBimeFarsi(), pishnehad.getEstelam().getNahvePardakhtHaghBimeFarsi(), true));
        condidateList.add(new PishnehadFieldChanges("پوشش فوت در اثر حادثه", oldPishnehad.getEstelam().getPooshesh_fot_dar_asar_haadeseFarsi(), pishnehad.getEstelam().getPooshesh_fot_dar_asar_haadeseFarsi(), true));
        condidateList.add(new PishnehadFieldChanges("حق بیمه اولیه", oldPishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal(), pishnehad.getEstelam().getMablagh_seporde_ebtedaye_sal(), true));
        condidateList.add(new PishnehadFieldChanges("سرمایه پوشش فوت در اثر حادثه", oldPishnehad.getEstelam().getSarmaye_paye_fot_dar_asar_hadese() + rial, pishnehad.getEstelam().getSarmaye_paye_fot_dar_asar_hadese() + rial, true));
        condidateList.add(new PishnehadFieldChanges("طبقه خطر پوشش فوت در اثر حادثه", oldPishnehad.getEstelam().getTabaghe_khatar(), pishnehad.getEstelam().getTabaghe_khatar(), true));
        condidateList.add(new PishnehadFieldChanges("پوشش نقص عضو", oldPishnehad.getEstelam().getPooshesh_naghs_ozvFarsi(), pishnehad.getEstelam().getPooshesh_naghs_ozvFarsi(), true));
        condidateList.add(new PishnehadFieldChanges("سرمایه پوشش نقص عضو", oldPishnehad.getEstelam().getSarmaye_pooshesh_naghs_ozv() + rial, pishnehad.getEstelam().getSarmaye_pooshesh_naghs_ozv() + rial, true));
        condidateList.add(new PishnehadFieldChanges("طبقه خطر پوشش نقص عضو", oldPishnehad.getEstelam().getTabaghe_khatar_naghsozv(), pishnehad.getEstelam().getTabaghe_khatar_naghsozv(), true));
        condidateList.add(new PishnehadFieldChanges("پوشش امراض خاص", oldPishnehad.getEstelam().getPooshesh_amraz_khasFarsi(), pishnehad.getEstelam().getPooshesh_amraz_khasFarsi(), true));
        condidateList.add(new PishnehadFieldChanges("سرمایه پوشش امراض خاص", oldPishnehad.getEstelam().getSarmaye_pooshesh_amraz_khas() + rial, pishnehad.getEstelam().getSarmaye_pooshesh_amraz_khas() + rial, true));
        condidateList.add(new PishnehadFieldChanges("پوشش معافیت", oldPishnehad.getEstelam().getPooshesh_moafiatFarsi(), pishnehad.getEstelam().getPooshesh_moafiatFarsi(), true));
        condidateList.add(new PishnehadFieldChanges("نحوه محاسبات", oldPishnehad.getEstelam().getNahve_kasr_hagh_bime_poosheshhaye_ezafiFarsi(), pishnehad.getEstelam().getNahve_kasr_hagh_bime_poosheshhaye_ezafiFarsi(), true));
        condidateList.add(new PishnehadFieldChanges("اضافه نرخ پزشکی", "%"+oldPishnehad.getEstelam().getDarsad_ezafe_nerkh_pezeshki(), "%"+pishnehad.getEstelam().getDarsad_ezafe_nerkh_pezeshki(), true));
        condidateList.add(new PishnehadFieldChanges("گروه", oldPishnehad.getGharardad() == null ? "-" : oldPishnehad.getGharardad().getNameSherkat(), pishnehad.getGharardad() == null ? "-" : pishnehad.getGharardad().getNameSherkat(), true));
        condidateList.add(new PishnehadFieldChanges("طرح", oldPishnehad.getTarh() == null ? "-" : oldPishnehad.getTarh().getName(), pishnehad.getTarh() == null ? "-" : pishnehad.getTarh().getName(), true));
        condidateList.add(new PishnehadFieldChanges("نوع قرارداد", oldPishnehad.getNoeGharardad(), pishnehad.getNoeGharardad()));
        List<EstefadeKonandeganAzSarmayeBime> oldListForAll = oldPishnehad.getEstefadeKonandeganAzSarmayeBime();
        List<EstefadeKonandeganAzSarmayeBime> oldListForDead = new ArrayList<EstefadeKonandeganAzSarmayeBime>();
        List<EstefadeKonandeganAzSarmayeBime> oldListForAlive = new ArrayList<EstefadeKonandeganAzSarmayeBime>();
        for (EstefadeKonandeganAzSarmayeBime estefadeKonandeganAzSarmayeBime : oldListForAll) {
            if (estefadeKonandeganAzSarmayeBime.getVaziateFotVaHayat().equalsIgnoreCase("fout")) {
                oldListForDead.add(estefadeKonandeganAzSarmayeBime);
            } else {
                oldListForAlive.add(estefadeKonandeganAzSarmayeBime);
            }

        }
        List<EstefadeKonandeganAzSarmayeBime> newListForAll = pishnehad.getEstefadeKonandeganAzSarmayeBime();
        List<EstefadeKonandeganAzSarmayeBime> newListForDead = new ArrayList<EstefadeKonandeganAzSarmayeBime>();
        List<EstefadeKonandeganAzSarmayeBime> newListForAlive = new ArrayList<EstefadeKonandeganAzSarmayeBime>();
        for (EstefadeKonandeganAzSarmayeBime estefadeKonandeganAzSarmayeBime : newListForAll) {
            if(estefadeKonandeganAzSarmayeBime.getVaziateFotVaHayat() != null) {
                if (estefadeKonandeganAzSarmayeBime.getVaziateFotVaHayat().equalsIgnoreCase("fout")) {
                    newListForDead.add(estefadeKonandeganAzSarmayeBime);
                } else {
                    newListForAlive.add(estefadeKonandeganAzSarmayeBime);
                }
            }
        }
        condidateList.add(new PishnehadFieldChanges("استفاده کننگان از سرمایه بیمه در قید حیات", oldListForAlive, newListForAlive));
        condidateList.add(new PishnehadFieldChanges("استفاده کننگان از سرمایه بیمه در صورت فوت", oldListForDead, newListForDead));

        List<VaziateSalamatiBimeShode> vaziateSalamatiOld = oldPishnehad.getVaziateSalamatiBimeShodeList();
        List<VaziateSalamatiBimeShode> vaziateSalamatiNew = pishnehad.getVaziateSalamatiBimeShodeList();
        condidateList.add(new PishnehadFieldChanges("وضعیت سلامتی بیمه شده", vaziateSalamatiOld, vaziateSalamatiNew));
        condidateList.add(new PishnehadFieldChanges("وضعیت سلامتی خانواده بیمه شده", oldPishnehad.getVaziateSalamatiKhanevadeyeBimeShode(), pishnehad.getVaziateSalamatiKhanevadeyeBimeShode()));
        condidateList.add(new PishnehadFieldChanges("سوابق بیمه ای",oldPishnehad.getSavabegheBimei(),pishnehad.getSavabegheBimei()));

        int c=1;
        for (PishnehadFieldChanges candidateLog : condidateList) {c++;
            if (candidateLog.getChangeType().equals(PishnehadFieldChanges.ChangeType.FIELD)) {
                if (!isFieldEquals(candidateLog.getFromValue(), candidateLog.getToValue())) {
                    pishnehadFieldChangesList.add(candidateLog);
                }
            } else if (candidateLog.getChangeType().equals(PishnehadFieldChanges.ChangeType.LIST))
            {
                if (
                        ((candidateLog.getFromValues() == null || candidateLog.getFromValues().size() == 0)&& (candidateLog.getToValues() != null && candidateLog.getToValues().size() != 0))
                          || ((candidateLog.getFromValues() != null && candidateLog.getFromValues().size() != 0)
                          && (candidateLog.getToValues() == null || candidateLog.getToValues().size() == 0))
                   )
                {
                    pishnehadFieldChangesList.add(candidateLog);
                }
                else if (
                        candidateLog.getFromValues() != null && candidateLog.getFromValues().size() != 0
                                && candidateLog.getToValues() != null && candidateLog.getToValues().size() != 0
                        )
                {
                    if (candidateLog.getFromValues().size() == candidateLog.getToValues().size()) {
                        for (Object candidateInstanceNew : candidateLog.getToValues()) {
                            for (Object candidateInstanceOld : candidateLog.getFromValues()) {
                                boolean compare = true;
                                VaziateSalamatiBimeShode newVaziateSamalatiBimeShode = null;
                                VaziateSalamatiBimeShode oldVaziateSamalatiBimeShode = null;
                                if ((candidateInstanceNew instanceof VaziateSalamatiBimeShode) && (candidateInstanceOld instanceof VaziateSalamatiBimeShode)) {
                                    newVaziateSamalatiBimeShode = (VaziateSalamatiBimeShode) candidateInstanceNew;
                                    oldVaziateSamalatiBimeShode = (VaziateSalamatiBimeShode) candidateInstanceOld;
                                }
                                if ((newVaziateSamalatiBimeShode != null) && (oldVaziateSamalatiBimeShode != null)) {
                                    if (newVaziateSamalatiBimeShode.getConstantSoalItem().getId().equals(oldVaziateSamalatiBimeShode.getConstantSoalItem().getId())) {
                                        compare = compare && (isFieldEquals(newVaziateSamalatiBimeShode.getJavabeSolal().toString(), oldVaziateSamalatiBimeShode.getJavabeSolal().toString()));
                                        if (!compare) {
                                            PishnehadFieldChanges changesForVaziateSalamati = new PishnehadFieldChanges();
                                            changesForVaziateSalamati.setChangeType(PishnehadFieldChanges.ChangeType.FIELD);
                                            changesForVaziateSalamati.setFromValue(oldVaziateSamalatiBimeShode.getJavabeSolalFarsi());
                                            changesForVaziateSalamati.setToValue(newVaziateSamalatiBimeShode.getJavabeSolalFarsi());
                                            ConstantSoalateVaziateSalamati theSoal = constantItemsService.findSoalVaziateSalamatiById(oldVaziateSamalatiBimeShode.getConstantSoalItem().getId());
                                            changesForVaziateSalamati.setSubject(theSoal.getMatneSoal());
                                            changesForVaziateSalamati.setCategory("VAZIAT_SALAMATI");
                                            pishnehadFieldChangesList.add(changesForVaziateSalamati);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //todo: vase vaziate salamati ham hamino copy paste kardam dorostesh konam ke log beshe taqiratesh.
                    if (candidateLog.getFromValues().size() == candidateLog.getToValues().size()) {
                        boolean changedKhanevade = false;
                        for(int i=0;i<candidateLog.getFromValues().size();i++)
                        {
                            boolean compare = true;
                            VaziateSalamatiKhanevadeyeBimeShode newVaziateKhanevade = null;
                            VaziateSalamatiKhanevadeyeBimeShode oldVaziateKhanevade = null;
                            if ((candidateLog.getToValues().get(i) instanceof VaziateSalamatiKhanevadeyeBimeShode) && (candidateLog.getFromValues().get(i) instanceof VaziateSalamatiKhanevadeyeBimeShode)) {
                                newVaziateKhanevade = (VaziateSalamatiKhanevadeyeBimeShode) candidateLog.getToValues().get(i);
                                oldVaziateKhanevade = (VaziateSalamatiKhanevadeyeBimeShode) candidateLog.getFromValues().get(i);
                            }
                            if ((newVaziateKhanevade != null) && (oldVaziateKhanevade != null))
                            {
                                compare = compare && (isFieldEquals(newVaziateKhanevade.getNesbatBabimeShode(), oldVaziateKhanevade.getNesbatBabimeShode()));
                                compare = compare && (isFieldEquals(newVaziateKhanevade.getSenneDarHayat(), oldVaziateKhanevade.getSenneDarHayat()));
                                compare = compare && (isFieldEquals(newVaziateKhanevade.getSenneDarZamaneFout(), oldVaziateKhanevade.getSenneDarZamaneFout()));
                                compare = compare && (isFieldEquals(newVaziateKhanevade.getVaziateHayat(), oldVaziateKhanevade.getVaziateHayat()));
                                compare = compare && (isFieldEquals(newVaziateKhanevade.getEllateFout(), oldVaziateKhanevade.getEllateFout()));
                                compare = compare && (isFieldEquals(newVaziateKhanevade.getSharheEllateFout(), oldVaziateKhanevade.getSharheEllateFout()));
                                compare = compare && (isFieldEquals(newVaziateKhanevade.getVaziateSalamati(), oldVaziateKhanevade.getVaziateSalamati()));
                            }
                            if (!compare)
                            {
                                changedKhanevade = true;
                            }
                            if (changedKhanevade) break;
                        }
                        if (changedKhanevade) {
                            pishnehadFieldChangesList.add(candidateLog);
                        }
                    }

                    if (candidateLog.getFromValues().size() == candidateLog.getToValues().size()) {
                        boolean changed = true;
                        for (Object candidateInstanceNew : candidateLog.getToValues()) {
                            for (Object candidateInstanceOld : candidateLog.getFromValues()) {
                                boolean compare = true;
                                EstefadeKonandeganAzSarmayeBime newEstefadeKonandeganAzSarmayeBime = new EstefadeKonandeganAzSarmayeBime();
                                EstefadeKonandeganAzSarmayeBime oldEstefadeKonandeganAzSarmayeBime = new EstefadeKonandeganAzSarmayeBime();
                                if ((candidateInstanceNew instanceof EstefadeKonandeganAzSarmayeBime) && (candidateInstanceOld instanceof EstefadeKonandeganAzSarmayeBime)) {
                                    newEstefadeKonandeganAzSarmayeBime = (EstefadeKonandeganAzSarmayeBime) candidateInstanceNew;
                                    oldEstefadeKonandeganAzSarmayeBime = (EstefadeKonandeganAzSarmayeBime) candidateInstanceOld;
                                }
                                if ((newEstefadeKonandeganAzSarmayeBime != null) && (oldEstefadeKonandeganAzSarmayeBime != null)) {
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getNoeZiNafea(), oldEstefadeKonandeganAzSarmayeBime.getNoeZiNafea()));
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getNesbatBabimeShode(), oldEstefadeKonandeganAzSarmayeBime.getNesbatBabimeShode()));
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getNesbatBabimeShode(), oldEstefadeKonandeganAzSarmayeBime.getNesbatBabimeShode()));
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getName(), oldEstefadeKonandeganAzSarmayeBime.getName()));
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getNameKhanevadegi(), oldEstefadeKonandeganAzSarmayeBime.getNameKhanevadegi()));
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getShomareShenasname(), oldEstefadeKonandeganAzSarmayeBime.getShomareShenasname()));
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getShomareSabt(), oldEstefadeKonandeganAzSarmayeBime.getShomareSabt()));
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getKodeMelli(), oldEstefadeKonandeganAzSarmayeBime.getKodeMelli()));
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getKodeEghtesadi(), oldEstefadeKonandeganAzSarmayeBime.getKodeEghtesadi()));
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getTarikhTavallod(), oldEstefadeKonandeganAzSarmayeBime.getTarikhTavallod()));
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getTarikhSabt(), oldEstefadeKonandeganAzSarmayeBime.getTarikhSabt()));
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getMahalleTavallod(), oldEstefadeKonandeganAzSarmayeBime.getMahalleTavallod()));
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getMahalleSabt(), oldEstefadeKonandeganAzSarmayeBime.getMahalleSabt()));
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getDarsadeSahm(), oldEstefadeKonandeganAzSarmayeBime.getDarsadeSahm()));
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getOlaviat(), oldEstefadeKonandeganAzSarmayeBime.getOlaviat()));
                                    compare = compare && (isFieldEquals(newEstefadeKonandeganAzSarmayeBime.getVaziateFotVaHayat(), oldEstefadeKonandeganAzSarmayeBime.getVaziateFotVaHayat()));
                                }
                                if (compare) {
                                    changed = false;
                                    break;
                                }
                            }
                            if (changed) break;
                        }
                        if (changed) {
                            pishnehadFieldChangesList.add(candidateLog);
                        }
                    }
                    if (candidateLog.getFromValues().size() != candidateLog.getToValues().size()) {
                        pishnehadFieldChangesList.add(candidateLog);
                    }
                }
            }
        }
        return pishnehadFieldChangesList;
    }

    public Elhaghiye mergeTwoElhaghiesForSameSaleAsar(Elhaghiye e1, Elhaghiye e2) {
        // tarikh sodoor e1 bayad ghabl az tarikh sodoor e2 bashad
        Elhaghiye mergedOne = new Elhaghiye();
        mergedOne.setState(e1.getState());
        mergedOne.setBimename(e1.getBimename());
        mergedOne.setCreatedDate(e1.getCreatedDate());
        mergedOne.setDarkhastBazkharid(e1.getDarkhastBazkharid());
        mergedOne.setElhaghiyeType(e1.getElhaghiyeType());
        mergedOne.setEmzaKonandeAval(e1.getEmzaKonandeAval());
        mergedOne.setEmzaKonandeDovom(e1.getEmzaKonandeDovom());
        mergedOne.setMablagh(e1.getMablagh());
        mergedOne.setMatn(e1.getMatn());
        mergedOne.setMozoo(e1.getMozoo());
        mergedOne.setTarikhAsar(e1.getTarikhAsar());
        mergedOne.setShomareElhaghiye(e1.getShomareElhaghiye());
        mergedOne.setRadifElhaghiye(e1.getRadifElhaghiye());
//        List<PishnehadFieldChanges> listTaghirat = getPishnehadChangesList(e1.getDarkhast().getDarkhastTaghirat().getOldPishnehad(), e2.getDarkhast().getDarkhastTaghirat().getNewPishnehad());
        DarkhastTaghirat dt = new DarkhastTaghirat();
        dt.setOldPishnehad(e1.getDarkhast().getDarkhastTaghirat().getOldPishnehad());
        dt.setNewPishnehad(e2.getDarkhast().getDarkhastTaghirat().getNewPishnehad());
        Darkhast d = new Darkhast();
        d.setDarkhastTaghirat(dt);
        d.setDarkhastType(Darkhast.DarkhastType.TAGHYIRAT);
        mergedOne.setDarkhast(d);
        return mergedOne;
    }


    public void getBimeGozarOfPishnehadChangesList() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private boolean isFieldEquals(String field1, String field2) {
        field1 = igonreArabicLetters(field1);
        field2 = igonreArabicLetters(field2);
//        if (field1 != null && (field1.equals("no") || field1.equals("0")))
            if (field1 != null && (field1.equals("no")))
            field1 = "";
        if (field1 == null)
            field1 = "";

//        if (field2 != null && (field2.equals("no") || field2.equals("0")))
            if (field2 != null && (field2.equals("no")))
            field2 = "";
        if (field2 == null)
            field2 = "";

        if (
                (field1 != null && field2 != null && (!field1.equalsIgnoreCase(field2)))
                )
            return false;
        return true;
    }

    private String igonreArabicLetters(String str) {
        if (str != null) {
            str = str.replaceAll("ي", "ی");
            str = str.replaceAll("ك", "ک");
            str = str.replaceAll(",","");
        }
        return str;
    }

    private Serializable getValueOfProperty(Pishnehad pishnehad, String propertyName) {
        Class objectClass = pishnehad.getClass();
        Field[] fields = objectClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(propertyName)) {
                return getObjectValue(pishnehad, propertyName);
            } else {
                try {
                    Object subPishnehad = PropertyUtils.getProperty(pishnehad, field.getName());
                    if (subPishnehad != null) {
                        Class subobjectClass = subPishnehad.getClass();
                        Field[] subFields = subobjectClass.getDeclaredFields();
                        for (Field subField : subFields) {
                            if (subField.getName().equals(propertyName)) {
                                return getObjectValue(subPishnehad, propertyName);
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private Serializable getObjectValue(Object obj, String property) {
        property = property.substring(0, 1).toUpperCase() + property.substring(1);
        Class objectClass = obj.getClass();
        Method[] methods = objectClass.getMethods();
        Serializable persistedObjectId = null;
        for (int ii = 0; ii < methods.length; ii++) {
            if (methods[ii].getName().equals("get" + property)) {
                try {
                    persistedObjectId = (Serializable) methods[ii].invoke(obj, null);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return persistedObjectId;
    }

    private void logRecord(User user, Pishnehad pishnehad, PishnehadFields pishnehadFields, String fromValue, String toValue) {
        PishnehadChangeHistoryLog pishnehadChangeHistoryLog = new PishnehadChangeHistoryLog();
        pishnehadChangeHistoryLog.setDate(DateUtil.getCurrentDate());
        pishnehadChangeHistoryLog.setTime(DateUtil.getCurrentTime());
        pishnehadChangeHistoryLog.setUser(user);
        pishnehadChangeHistoryLog.setPishnehad(pishnehad);
        pishnehadChangeHistoryLog.setPishnehadFields(pishnehadFields);
        pishnehadChangeHistoryLog.setFromValue(fromValue);
        pishnehadChangeHistoryLog.setToValue(toValue);
        logDAO.savePishnehadChangeHistoryLog(pishnehadChangeHistoryLog);
    }

    public IConstantItemsService getConstantItemsService() {
        return constantItemsService;
    }

    public void setConstantItemsService(IConstantItemsService constantItemsService) {
        this.constantItemsService = constantItemsService;
    }
}
