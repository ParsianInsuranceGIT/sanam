package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.KhateSanad;
import com.bitarts.parsian.model.constantItems.Tarh;
import com.bitarts.parsian.model.karmozd.KarmozdNamayande;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 4/14/14
 * Time: 11:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class KarmozdTashvighiVosuliVM
{
    private Long namayandeId;
    private KhateSanad khateSanad;
    private Ghest ghest;
    private KarmozdNamayande karmozdNamayande;
    private String nahvePardakht;
    private Tarh tarh;
    private String tarikhSodurBimename;

    public KarmozdTashvighiVosuliVM(KhateSanad khateSanad, Ghest ghest, KarmozdNamayande karmozdNamayande, Long namayandeId, String nahvePardakht,Tarh tarh,String tarikhSodurBimename)
    {
        this.khateSanad = khateSanad;
        this.ghest = ghest;
        this.karmozdNamayande = karmozdNamayande;
        this.namayandeId=namayandeId;
        this.nahvePardakht=nahvePardakht;
        this.tarh=tarh;
        this.tarikhSodurBimename=tarikhSodurBimename;
    }

    public String getTarikhSodurBimename()
    {
        return tarikhSodurBimename;
    }

    public void setTarikhSodurBimename(String tarikhSodurBimename)
    {
        this.tarikhSodurBimename = tarikhSodurBimename;
    }

    public String getNahvePardakht()
    {
        return nahvePardakht;
    }

    public void setNahvePardakht(String nahvePardakht)
    {
        this.nahvePardakht = nahvePardakht;
    }

    public Tarh getTarh()
    {
        return tarh;
    }

    public void setTarh(Tarh tarh)
    {
        this.tarh = tarh;
    }

    public Long getNamayandeId()
    {
        return namayandeId;
    }

    public void setNamayandeId(Long namayandeId)
    {
        this.namayandeId = namayandeId;
    }

    public KhateSanad getKhateSanad()
    {
        return khateSanad;
    }

    public void setKhateSanad(KhateSanad khateSanad)
    {
        this.khateSanad = khateSanad;
    }

    public Ghest getGhest()
    {
        return ghest;
    }

    public void setGhest(Ghest ghest)
    {
        this.ghest = ghest;
    }

    public KarmozdNamayande getKarmozdNamayande()
    {
        return karmozdNamayande;
    }

    public void setKarmozdNamayande(KarmozdNamayande karmozdNamayande)
    {
        this.karmozdNamayande = karmozdNamayande;
    }
}
