package com.bitarts.parsian.viewModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: a-khezri
 * Date: 10/29/14
 * Time: 4:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class CashTurnoverReportVM
{

    private String fromDate;
    private String toDate;
    private List<CashTurnoverRowsVM> cashTurnoverRowsVMList;
    private Long totalCredit;
    private Long totalCreditWithoutBargashti;
    private Long totalDebit;

    public Long getTotalCreditWithoutBargashti()
    {
        return totalCreditWithoutBargashti;
    }

    public void setTotalCreditWithoutBargashti(Long totalCreditWithoutBargashti)
    {
        this.totalCreditWithoutBargashti = totalCreditWithoutBargashti;
    }

    public Long getTotalCredit()
    {
        return totalCredit;
    }

    public void setTotalCredit(Long totalCredit)
    {
        this.totalCredit = totalCredit;
    }

    public Long getTotalDebit()
    {
        return totalDebit;
    }

    public void setTotalDebit(Long totalDebit)
    {
        this.totalDebit = totalDebit;
    }

    public List<CashTurnoverRowsVM> getCashTurnoverRowsVMList()
    {
        return cashTurnoverRowsVMList;
    }

    public void setCashTurnoverRowsVMList(List<CashTurnoverRowsVM> cashTurnoverRowsVMList)
    {
        this.cashTurnoverRowsVMList = cashTurnoverRowsVMList;
    }

    public String getFromDate()
    {
        return fromDate;
    }

    public void setFromDate(String fromDate)
    {
        this.fromDate = fromDate;
    }

    public String getToDate()
    {
        return toDate;
    }

    public void setToDate(String toDate)
    {
        this.toDate = toDate;
    }
}
