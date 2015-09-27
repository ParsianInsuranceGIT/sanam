package com.bitarts.parsian.action.sodureJami;

import com.bitarts.parsian.model.constantItems.City;
import com.bitarts.parsian.model.estelam.Estelam;
import com.bitarts.parsian.model.pishnahad.*;
import com.bitarts.parsian.service.IConstantItemsService;
import com.bitarts.parsian.viewModel.PishnehadDuplicationRules;
import jxl.Cell;
import jxl.Sheet;

import java.text.NumberFormat;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * Date: 12/13/12
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExcelExtraction {
    public static Pishnehad extractPishnehad(Sheet sheet, int i, IConstantItemsService constantItemsService, PishnehadDuplicationRules duplicationRules) {
        Pishnehad pishnehad = new Pishnehad();
//        Estelam estelam = new Estelam();

//        pishnehad.setEstelam(estelam);

        // ==================================== Bime Gozar ======================================== //
        Cell nameBimeGozar = sheet.getCell(0, i);
        Cell nameKhanevadegieBimeGozar = sheet.getCell(1, i);
        Cell namePedareBimeGozar = sheet.getCell(2, i);
        Cell jensiateBimeGozar = sheet.getCell(3, i);
        Cell vaziatTaaholeBimeGozar = sheet.getCell(4, i);
        Cell shenasnameBimeGozar = sheet.getCell(5, i);
        Cell mahalleSodurBimeGozar = sheet.getCell(74, i);
        Cell tarikhTavallodBimeGozar = sheet.getCell(7, i);
        Cell mahalleTavalodBimeGozar = sheet.getCell(73, i);
        Cell kodeMelliBimeGozar = sheet.getCell(9, i);
        Cell shoghlAsliBimeGozar = sheet.getCell(10, i);
        Cell shoghlFariBimeGozar = sheet.getCell(11, i);
        Cell nesbatBaBimeShodeBimeGozar = sheet.getCell(12, i);

        // ============ Addresse bime gozar
        Cell shahrMahallSokunatBimeGozar = sheet.getCell(75, i);
        Cell neshaniManzelBimeGozar = sheet.getCell(15, i);
        Cell kodePostiManzelBimeGozar = sheet.getCell(16, i);
        Cell pishShomareManzelBimeGozar = sheet.getCell(17, i);
        Cell tellManzelBimeGozar = sheet.getCell(18, i);
        Cell hamrahBimeGozar = sheet.getCell(19, i);
        Cell shahrMahallKarBimeGozar = sheet.getCell(76, i);
        Cell neshaniMahallKarBimeGozar = sheet.getCell(22, i);
        Cell kodePostiKarBimeGozar = sheet.getCell(23, i);
        Cell pishShomareKarBimeGozar = sheet.getCell(24, i);
        Cell tellKarBimeGozar = sheet.getCell(25, i);
        Cell daramadMahianeBimeGozar = sheet.getCell(26, i);
        Cell postElectronicBimeGozar = sheet.getCell(27, i);

        if (duplicationRules.getBimegozarSource() == PishnehadDuplicationRules.PishnehadCopySource.AzExcel) {
            if (kodeMelliBimeGozar.getContents() == null || kodeMelliBimeGozar.getContents().isEmpty())
                return null;

            Shakhs shakhsBimeGozar = new Shakhs();
            shakhsBimeGozar.setName(nameBimeGozar.getContents());
            shakhsBimeGozar.setNameKhanevadegi(nameKhanevadegieBimeGozar.getContents());
            shakhsBimeGozar.setNamePedar(namePedareBimeGozar.getContents());
            shakhsBimeGozar.setIraniOrAtbaeKhareji(Shakhs.Melliat.IRANI);
            shakhsBimeGozar.setKodeMelliShenasayi(kodeMelliBimeGozar.getContents());
            shakhsBimeGozar.setShomareShenasnameh(shenasnameBimeGozar.getContents());
            shakhsBimeGozar.setMahalleSodoreShenasnameh(constantItemsService.findCityById(Long.parseLong(mahalleSodurBimeGozar.getContents())));
            shakhsBimeGozar.setMahalleTavallod(constantItemsService.findCityById(Long.parseLong(mahalleTavalodBimeGozar.getContents())));
            shakhsBimeGozar.setTarikheTavallod(tarikhTavallodBimeGozar.getContents());
            shakhsBimeGozar.setVaziateTaahol(vaziatTaaholeBimeGozar.getContents());
            shakhsBimeGozar.setJensiat(jensiateBimeGozar.getContents());
            shakhsBimeGozar.setPishvand(shakhsBimeGozar.getJensiat().equals("مرد") ? "آقای" : "خانم");
            shakhsBimeGozar.setShoghleAsli(shoghlAsliBimeGozar.getContents());
            shakhsBimeGozar.setShoghleFarei(shoghlFariBimeGozar.getContents());
            shakhsBimeGozar.setType(Shakhs.BimeGozarType.HAGHIGHI);

            City mahalSokunatBimeGozar = constantItemsService.findCityById(Long.parseLong(shahrMahallSokunatBimeGozar.getContents()));
            City ostanMahalSokunatBimeGozar = null;
            if (mahalSokunatBimeGozar != null)
                ostanMahalSokunatBimeGozar = constantItemsService.findCityByIdForPid(mahalSokunatBimeGozar.getCityPid());

            City mahalKarBimeGozar = constantItemsService.findCityById(Long.parseLong(shahrMahallKarBimeGozar.getContents()));
            City ostanMahalKarBimeGozar = null;
            if (mahalKarBimeGozar != null)
                ostanMahalKarBimeGozar = constantItemsService.findCityByIdForPid(mahalKarBimeGozar.getCityPid());

            Address addressBimeGozar = new Address(
                    ostanMahalSokunatBimeGozar,
                    mahalSokunatBimeGozar,
                    neshaniManzelBimeGozar.getContents(),
                    kodePostiManzelBimeGozar.getContents(),
                    pishShomareManzelBimeGozar.getContents(),
                    tellManzelBimeGozar.getContents(),
                    hamrahBimeGozar.getContents(),
                    ostanMahalKarBimeGozar,
                    mahalKarBimeGozar,
                    neshaniMahallKarBimeGozar.getContents(),
                    kodePostiKarBimeGozar.getContents(),
                    pishShomareKarBimeGozar.getContents(),
                    tellKarBimeGozar.getContents(),
                    postElectronicBimeGozar.getContents());

            BimeGozar bimeGozar = new BimeGozar();
            bimeGozar.setShakhs(shakhsBimeGozar);
            bimeGozar.setNesbatBabimeShode(nesbatBaBimeShodeBimeGozar.getContents());
            bimeGozar.setDaramadeMahiane(Long.parseLong(daramadMahianeBimeGozar.getContents()));

            pishnehad.setBimeGozar(bimeGozar);
            pishnehad.setAddressBimeGozar(addressBimeGozar);
        }

        // ==================================== Bime Shode ======================================== //
        Cell nameBimeShode = sheet.getCell(28, i);
        Cell nameKhanevadegieBimeShode = sheet.getCell(29, i);
        Cell namePedareBimeShode = sheet.getCell(30, i);
        Cell jensiateBimeShode = sheet.getCell(31, i);
        Cell vaziatTaaholeBimeShode = sheet.getCell(32, i);
        Cell shenasnameBimeShode = sheet.getCell(33, i);
        Cell mahalleSodurBimeShode = sheet.getCell(77, i);
        Cell tarikhTavallodBimeShode = sheet.getCell(35, i);
        Cell mahalleTavalodBimeShode = sheet.getCell(76, i);
        Cell kodeMelliBimeShode = sheet.getCell(37, i);
        Cell shoghlAsliBimeShode = sheet.getCell(38, i);
        Cell shoghlFariBimeShode = sheet.getCell(39, i);

        // ============ Addresse bime gozar
        Cell shahrMahallSokunatBimeShode = sheet.getCell(78, i);
        Cell neshaniManzelBimeShode = sheet.getCell(42, i);
        Cell kodePostiManzelBimeShode = sheet.getCell(43, i);
        Cell pishShomareManzelBimeShode = sheet.getCell(44, i);
        Cell tellManzelBimeShode = sheet.getCell(45, i);
        Cell hamrahBimeShode = sheet.getCell(46, i);
        Cell shahrMahallKarBimeShode = sheet.getCell(79, i);
        Cell neshaniMahallKarBimeShode = sheet.getCell(49, i);
        Cell kodePostiKarBimeShode = sheet.getCell(50, i);
        Cell pishShomareKarBimeShode = sheet.getCell(51, i);
        Cell tellKarBimeShode = sheet.getCell(52, i);
        Cell postElectronicBimeShode = sheet.getCell(53, i);

        if (duplicationRules.getBimeshodeSource() == PishnehadDuplicationRules.PishnehadCopySource.AzExcel) {
            if (kodeMelliBimeShode.getContents() == null || kodeMelliBimeShode.getContents().isEmpty())
                return null;

            Shakhs shakhsBimeShode = new Shakhs();
            shakhsBimeShode.setName(nameBimeShode.getContents());
            shakhsBimeShode.setNameKhanevadegi(nameKhanevadegieBimeShode.getContents());
            shakhsBimeShode.setNamePedar(namePedareBimeShode.getContents());
            shakhsBimeShode.setIraniOrAtbaeKhareji(Shakhs.Melliat.IRANI);
            shakhsBimeShode.setKodeMelliShenasayi(kodeMelliBimeShode.getContents());
            shakhsBimeShode.setShomareShenasnameh(shenasnameBimeShode.getContents());
            shakhsBimeShode.setMahalleSodoreShenasnameh(constantItemsService.findCityById(Long.parseLong(mahalleSodurBimeShode.getContents())));
            shakhsBimeShode.setMahalleTavallod(constantItemsService.findCityById(Long.parseLong(mahalleTavalodBimeShode.getContents())));
            shakhsBimeShode.setTarikheTavallod(tarikhTavallodBimeShode.getContents());
            shakhsBimeShode.setVaziateTaahol(vaziatTaaholeBimeShode.getContents());
            shakhsBimeShode.setJensiat(jensiateBimeShode.getContents());
            shakhsBimeShode.setPishvand(shakhsBimeShode.getJensiat().equals("مرد") ? "آقای" : "خانم");
            shakhsBimeShode.setShoghleAsli(shoghlAsliBimeShode.getContents());
            shakhsBimeShode.setShoghleFarei(shoghlFariBimeShode.getContents());
            shakhsBimeShode.setType(Shakhs.BimeGozarType.HAGHIGHI);

            City mahalSokunatBimeShode = constantItemsService.findCityById(Long.parseLong(shahrMahallSokunatBimeShode.getContents()));
            City ostanMahalSokunatBimeShode = null;
            if (mahalSokunatBimeShode != null)
                ostanMahalSokunatBimeShode = constantItemsService.findCityByIdForPid(mahalSokunatBimeShode.getCityPid());

            City mahalKarBimeShode = constantItemsService.findCityById(Long.parseLong(shahrMahallKarBimeShode.getContents()));
            City ostanMahalKarBimeShode = null;
            if (mahalKarBimeShode != null)
                ostanMahalKarBimeShode = constantItemsService.findCityByIdForPid(mahalKarBimeShode.getCityPid());

            Address addressBimeShode = new Address(
                    ostanMahalSokunatBimeShode,
                    mahalSokunatBimeShode,
                    neshaniManzelBimeShode.getContents(),
                    kodePostiManzelBimeShode.getContents(),
                    pishShomareManzelBimeShode.getContents(),
                    tellManzelBimeShode.getContents(),
                    hamrahBimeShode.getContents(),
                    ostanMahalKarBimeShode,
                    mahalKarBimeShode,
                    neshaniMahallKarBimeShode.getContents(),
                    kodePostiKarBimeShode.getContents(),
                    pishShomareKarBimeShode.getContents(),
                    tellKarBimeShode.getContents(),
                    postElectronicBimeShode.getContents());

            BimeShode bimeShode = new BimeShode();
            bimeShode.setShakhs(shakhsBimeShode);

            pishnehad.setBimeShode(bimeShode);
            pishnehad.setAddressBimeShode(addressBimeShode);
        }

        // ==================================== Bimename ======================================== //
        Cell sarmayePayeFot = sheet.getCell(54, i);
        Cell nerkheAfzayesheSalaneSarmayeFot = sheet.getCell(55, i);
        Cell hagheBimeAvalie = sheet.getCell(56, i);
        Cell hagheBimeMonazam = sheet.getCell(57, i);
        Cell nahvePardakhteHagheBime = sheet.getCell(58, i);
        Cell nerkheAfzayesheSalaneHagheBime = sheet.getCell(59, i);
        Cell moddat = sheet.getCell(60, i);
        Cell sarmayeFotDarAsareHadese = sheet.getCell(61, i);
        Cell sarmayeNaghseOzv = sheet.getCell(62, i);
        Cell sarmayeAmraz = sheet.getCell(63, i);
        Cell moafiat = sheet.getCell(64, i);

        if (duplicationRules.getBimenameSource() == PishnehadDuplicationRules.PishnehadCopySource.AzExcel) {
            if (sarmayePayeFot.getContents() == null || sarmayePayeFot.getContents().isEmpty())
                return null;

            pishnehad.setEstelam(new Estelam());

            pishnehad.getEstelam().setSarmaye_paye_fot(NumberFormat.getNumberInstance().format(Long.parseLong(sarmayePayeFot.getContents())));
            pishnehad.getEstelam().setNerkh_afzayesh_salaneh_sarmaye_fot(NumberFormat.getNumberInstance().format(Long.parseLong(nerkheAfzayesheSalaneSarmayeFot.getContents())));
            pishnehad.getEstelam().setMablagh_seporde_ebtedaye_sal(NumberFormat.getNumberInstance().format(Long.parseLong(hagheBimeAvalie.getContents())));
            pishnehad.getEstelam().setHagh_bime_pardakhti(NumberFormat.getNumberInstance().format(Long.parseLong(hagheBimeMonazam.getContents())));
            String nahveString = "";
            if (nahvePardakhteHagheBime.getContents().equals("ماهانه")) nahveString = "mah";
            else if (nahvePardakhteHagheBime.getContents().equals("سه ماهه")) nahveString = "3mah";
            else if (nahvePardakhteHagheBime.getContents().equals("شش ماهه")) nahveString = "6mah";
            else if (nahvePardakhteHagheBime.getContents().equals("سالانه")) nahveString = "sal";
            else if (nahvePardakhteHagheBime.getContents().equals("یکجا")) nahveString = "yekja";
            if(nahveString.isEmpty())
                throw new NullPointerException();
            pishnehad.getEstelam().setNahve_pardakht_hagh_bime(nahveString);
            pishnehad.getEstelam().setModat_bimename(NumberFormat.getNumberInstance().format(Long.parseLong(moddat.getContents())));
            pishnehad.getEstelam().setNerkh_afzayesh_salaneh_hagh_bime(NumberFormat.getNumberInstance().format(Long.parseLong(nerkheAfzayesheSalaneHagheBime.getContents())));

            pishnehad.getEstelam().setSarmaye_paye_fot_dar_asar_hadese(NumberFormat.getNumberInstance().format(Long.parseLong(sarmayeFotDarAsareHadese.getContents())));
            if (sarmayeFotDarAsareHadese.getContents() == null || Long.parseLong(sarmayeFotDarAsareHadese.getContents()) == 0)
                pishnehad.getEstelam().setPooshesh_fot_dar_asar_haadese("no");
            else
                pishnehad.getEstelam().setPooshesh_fot_dar_asar_haadese("yes");

            pishnehad.getEstelam().setSarmaye_pooshesh_naghs_ozv(NumberFormat.getNumberInstance().format(Long.parseLong(sarmayeNaghseOzv.getContents())));
            if (sarmayeNaghseOzv.getContents() == null || Long.parseLong(sarmayeNaghseOzv.getContents()) == 0)
                pishnehad.getEstelam().setPooshesh_naghs_ozv("no");
            else
                pishnehad.getEstelam().setPooshesh_naghs_ozv("yes");

            pishnehad.getEstelam().setSarmaye_pooshesh_amraz_khas(NumberFormat.getNumberInstance().format(Long.parseLong(sarmayeAmraz.getContents())));
            if (sarmayeAmraz.getContents() == null || Long.parseLong(sarmayeAmraz.getContents()) == 0)
                pishnehad.getEstelam().setPooshesh_amraz_khas("no");
            else
                pishnehad.getEstelam().setPooshesh_amraz_khas("yes");

            String moafiatString = moafiat.getContents();
            if(moafiatString.equals("دارد"))
                moafiatString = "yes";
            if (moafiatString.equals("ندارد"))
                moafiatString = "no";
            if(moafiatString.isEmpty())
                throw new NullPointerException();
            pishnehad.getEstelam().setPooshesh_moafiat(moafiatString);
        }

        // ==================================== Soalate Omoomi ======================================== //
        Cell ghadeBimeShode = sheet.getCell(65, i);
        Cell vazneBimeShode = sheet.getCell(66, i);
        Cell vaziateNezamVazife = sheet.getCell(67, i);
        Cell ellateMoafiat = sheet.getCell(68, i);

        if (duplicationRules.getSoalateOmoomiSource() == PishnehadDuplicationRules.PishnehadCopySource.AzExcel) {
            if (ghadeBimeShode.getContents() == null || ghadeBimeShode.getContents().isEmpty())
                return null;

            SoalateOmomiAzBimeShode soalat = new SoalateOmomiAzBimeShode();
            soalat.setGhad_bime_shode(ghadeBimeShode.getContents());
            soalat.setVazn_bime_shode(vazneBimeShode.getContents());
            soalat.setKhdemat_nezaam_vazife(vaziateNezamVazife.getContents());
            soalat.setMoaafiyat_pezeshki(ellateMoafiat.getContents());

            pishnehad.setSoalateOmomiAzBimeShode(soalat);
        }

        // ==================================== Salamate Khanevade ======================================== //
        Cell vaziateHayateMadar = sheet.getCell(69, i);
        Cell vaziateHayatePedar = sheet.getCell(70, i);

        if (duplicationRules.getVaziateSalamateKhanevadeSource() == PishnehadDuplicationRules.PishnehadCopySource.AzExcel) {
            if (vaziateHayateMadar.getContents() == null || vaziateHayateMadar.getContents().isEmpty())
                return null;

            VaziateSalamatiKhanevadeyeBimeShode vaziateMadar = new VaziateSalamatiKhanevadeyeBimeShode();
            vaziateMadar.setNesbatBabimeShode("مادر");
            vaziateMadar.setVaziateHayat(vaziateHayateMadar.getContents());
            vaziateMadar.setPishnehad(pishnehad);

            VaziateSalamatiKhanevadeyeBimeShode vaziatePedar = new VaziateSalamatiKhanevadeyeBimeShode();
            vaziatePedar.setNesbatBabimeShode("پدر");
            vaziatePedar.setVaziateHayat(vaziateHayatePedar.getContents());
            vaziatePedar.setPishnehad(pishnehad);

            LinkedList<VaziateSalamatiKhanevadeyeBimeShode> vaziatSalamati = new LinkedList<VaziateSalamatiKhanevadeyeBimeShode>();
            vaziatSalamati.add(vaziateMadar);
            vaziatSalamati.add(vaziatePedar);
            pishnehad.setVaziateSalamatiKhanevadeyeBimeShode(vaziatSalamati);
        }

        return pishnehad;
    }
}
