package com.bitarts.parsian.action;

import com.bitarts.common.action.BaseAction;
import com.bitarts.common.displaytag.PaginatedListImpl;
import com.bitarts.parsian.model.constantItems.City;
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
public class CityAction extends BaseAction implements ServletContextAware {
    private PaginatedListImpl<City> listCities;
    private IConstantItemsService constantItemsService;
    private Long cityId;
    private City newCity;
    private String sname, stabe;
    private Long scode, scodetabe;

    public void setServletContext(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        constantItemsService = (IConstantItemsService) wac.getBean("constantItemsService");
    }

    public String deleteCity() {
        try{
            constantItemsService.removeCityById(cityId);
        }catch(Exception e){
            addActionError("به دلایل وابستگی شهر مورد نظر با سایر داده ها، حذف آن امکان پذیر نمی باشد.");
            return SUCCESS;
        }
        addActionMessage("شهر با موفقیت حذف شد.");
        return SUCCESS;
    }

    public String sabtCity() {

        if (cityId != null) {
            newCity = constantItemsService.findCityById(cityId);
        }

        return SUCCESS;
    }

    public String listAllCities() {
        listCities = new PaginatedListImpl<City>();
        listCities.setObjectsPerPage(15);
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
        listCities.setPageNumber(pageNumber);
        listCities = constantItemsService.findAllCities(listCities, sname, scode, stabe, scodetabe);
        getSession().put("listCities", listCities);
        return SUCCESS;
    }

    public String addCity() {
        constantItemsService.saveNewCity(newCity);
        addActionMessage("شهر با موفقیت اضافه یا ویرایش شد.");
        return SUCCESS;
    }

    public City getParentCity(Long cityId) {
        if (cityId == null) return null;
        return constantItemsService.findCityByIdForPid(constantItemsService.findCityById(cityId).getCityPid());
    }

    public PaginatedListImpl<City> getListCities() {
        return listCities;
    }

    public void setListCities(PaginatedListImpl<City> listCities) {
        this.listCities = listCities;
    }

    public IConstantItemsService getConstantItemsService() {
        return constantItemsService;
    }

    public void setConstantItemsService(IConstantItemsService constantItemsService) {
        this.constantItemsService = constantItemsService;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public City getNewCity() {
        return newCity;
    }

    public void setNewCity(City newCity) {
        this.newCity = newCity;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getStabe() {
        return stabe;
    }

    public void setStabe(String stabe) {
        this.stabe = stabe;
    }

    public Long getScode() {
        return scode;
    }

    public void setScode(Long scode) {
        this.scode = scode;
    }

    public Long getScodetabe() {
        return scodetabe;
    }

    public void setScodetabe(Long scodetabe) {
        this.scodetabe = scodetabe;
    }
}
