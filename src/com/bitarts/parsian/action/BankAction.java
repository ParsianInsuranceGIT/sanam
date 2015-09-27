package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.management.Bank;
import com.bitarts.parsian.service.IConstantItemsService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: shepherd
 * Date: 9/9/12
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class BankAction extends BaseAction implements ServletContextAware {
    private PaginatedListImpl<Bank> listBanks;
    private IConstantItemsService constantItemsService;
    private String bankName;
    private Bank bank;


    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        constantItemsService = (IConstantItemsService) wac.getBean("constantItemsService");
    }

    public String deleteBank() {
        constantItemsService.removeBankById(bank.getId());
        addActionMessage("بانک با موفقیت حذف شد.");
        return SUCCESS;
    }

    public String sabtBank() {

        if (bank != null && bank.getId() != null) {
            bank = constantItemsService.findBankById(bank.getId());
        }

        return SUCCESS;
    }

    public String listAllBanks() {
        listBanks = new PaginatedListImpl<Bank>();
        listBanks.setObjectsPerPage(15);
        //Set Page #
        Integer pageNumber = 0;
        if (ActionContext.getContext().getParameters().size() > 0) {
            Set<String> qs = ActionContext.getContext().getParameters().keySet();
            for (String name : qs) {
                if (name.startsWith("d-") && name.endsWith("-p")) {
                    Object[] o = (Object[]) ActionContext.getContext().getParameters().get(name);
                    pageNumber = Integer.parseInt((String) o[0]) - 1;
                    break;
                }
            }
        }
        listBanks.setPageNumber(pageNumber);
        listBanks = constantItemsService.findAllBanks(listBanks);
        getSession().put("listBanks", listBanks);
        return SUCCESS;
    }

    public String addBank() {
        constantItemsService.saveNewBank(bank);
        addActionMessage("بانک با موفقیت اضافه یا ویرایش شد.");
        return SUCCESS;
    }

    public PaginatedListImpl<Bank> getListBanks() {
        return listBanks;
    }

    public void setListBanks(PaginatedListImpl<Bank> listBanks) {
        this.listBanks = listBanks;
    }

    public IConstantItemsService getConstantItemsService() {
        return constantItemsService;
    }

    public void setConstantItemsService(IConstantItemsService constantItemsService) {
        this.constantItemsService = constantItemsService;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
