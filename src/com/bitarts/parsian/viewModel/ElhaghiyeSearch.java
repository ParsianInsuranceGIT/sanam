package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.bimename.Elhaghiye;

/**
 * Created by IntelliJ IDEA.
 * User: a-khezri
 * Date: 5/5/13
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class ElhaghiyeSearch
{
    private String  shomareElhaghiye;
    private Elhaghiye.ElhaghiyeType elhaghiyeType;
    private String azTarikhAsar,taTarikhAsar;
    private String azCreatedDate,taCreatedDate;
    private String shomareBimename;
    private String azTarikhDarkhast,taTarikhDarkhast;

    public String getAzTarikhDarkhast()
    {
        return azTarikhDarkhast;
    }

    public void setAzTarikhDarkhast(String azTarikhDarkhast)
    {
        this.azTarikhDarkhast = azTarikhDarkhast;
    }

    public String getTaTarikhDarkhast()
    {
        return taTarikhDarkhast;
    }

    public void setTaTarikhDarkhast(String taTarikhDarkhast)
    {
        this.taTarikhDarkhast = taTarikhDarkhast;
    }

    public String getShomareBimename()
    {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename)
    {
        this.shomareBimename = shomareBimename;
    }

    public String getShomareElhaghiye()
    {
        return shomareElhaghiye;
    }

    public void setShomareElhaghiye(String shomareElhaghiye)
    {
        this.shomareElhaghiye = shomareElhaghiye;
    }

    public Elhaghiye.ElhaghiyeType getElhaghiyeType()
    {
        return elhaghiyeType;
    }

    public void setElhaghiyeType(Elhaghiye.ElhaghiyeType elhaghiyeType)
    {
        this.elhaghiyeType = elhaghiyeType;
    }

    public String getAzTarikhAsar()
    {
        return azTarikhAsar;
    }

    public void setAzTarikhAsar(String azTarikhAsar)
    {
        this.azTarikhAsar = azTarikhAsar;
    }

    public String getTaTarikhAsar()
    {
        return taTarikhAsar;
    }

    public void setTaTarikhAsar(String taTarikhAsar)
    {
        this.taTarikhAsar = taTarikhAsar;
    }

    public String getAzCreatedDate()
    {
        return azCreatedDate;
    }

    public void setAzCreatedDate(String azCreatedDate)
    {
        this.azCreatedDate = azCreatedDate;
    }

    public String getTaCreatedDate()
    {
        return taCreatedDate;
    }

    public void setTaCreatedDate(String taCreatedDate)
    {
        this.taCreatedDate = taCreatedDate;
    }
}
