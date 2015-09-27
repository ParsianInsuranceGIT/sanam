package com.bitarts.parsian.viewModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: a-khezri
 * Date: 5/10/13
 * Time: 5:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchDarkhasts
{
    private String kodeMelliShenasayiBimeGozar;
    private String shomareBimename;
    private String darkhastType;
    private String darkhastState;
    private List<String> darkhastStateList;

    public List<String> getDarkhastStateList()
    {
        return darkhastStateList;
    }

    public void setDarkhastStateList(List<String> darkhastStateList)
    {
        this.darkhastStateList = darkhastStateList;
    }

    public String getDarkhastType()
    {
        return darkhastType;
    }

    public void setDarkhastType(String darkhastType)
    {
        this.darkhastType = darkhastType;
    }

    public String getDarkhastState()
    {
        return darkhastState;
    }

    public void setDarkhastState(String darkhastState)
    {
        this.darkhastState = darkhastState;
    }

    public String getKodeMelliShenasayiBimeGozar()
    {
        return kodeMelliShenasayiBimeGozar;
    }

    public void setKodeMelliShenasayiBimeGozar(String kodeMelliShenasayiBimeGozar)
    {
        this.kodeMelliShenasayiBimeGozar = kodeMelliShenasayiBimeGozar;
    }

    public String getShomareBimename()
    {
        return shomareBimename;
    }

    public void setShomareBimename(String shomareBimename)
    {
        this.shomareBimename = shomareBimename;
    }
}
