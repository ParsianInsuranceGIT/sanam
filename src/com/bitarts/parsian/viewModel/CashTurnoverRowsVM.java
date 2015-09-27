package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.asnadeSodor.Credebit;
import com.bitarts.parsian.model.asnadeSodor.KhateSanad;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 10/30/14
 * Time: 1:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class CashTurnoverRowsVM implements Comparable<CashTurnoverRowsVM>
{
    @Override
    public int compareTo(CashTurnoverRowsVM o)
    {
        return type.toString().compareTo(o.type.toString());
    }

    public static enum Type
    {
        DEBIT, CREDIT
    }
    private List<KhateSanad> khateSanadList;
    private Credebit.CredebitType credebitType;//Partial type
    private Long totalKhateSanadAmount;
    private Type type;

    public java.util.List<KhateSanad> getKhateSanadList()
    {
        return khateSanadList;
    }

    public void setKhateSanadList(List<KhateSanad> khateSanadList)
    {
        this.khateSanadList = khateSanadList;
    }

    public Long getTotalKhateSanadAmount()
    {
        return totalKhateSanadAmount;
    }

    public void setTotalKhateSanadAmount(Long totalKhateSanadAmount)
    {
        this.totalKhateSanadAmount = totalKhateSanadAmount;
    }

    public String getCredebitTypeFa()
    {
        return Credebit.getCredebitTypeFarsi(this.credebitType);
    }

    public Credebit.CredebitType getCredebitType()
    {
        return credebitType;
    }

    public void setCredebitType(Credebit.CredebitType credebitType)
    {
        this.credebitType = credebitType;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public String getTypeFa()
    {
        if (this.type.equals(Type.CREDIT)) return "اعتبارات خرج شده";
        if (this.type.equals(Type.DEBIT)) return "بدهی های پرداخت شده";
        return "";
    }
}
