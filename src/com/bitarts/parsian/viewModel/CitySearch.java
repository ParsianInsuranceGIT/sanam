package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.constantItems.City;

/**
 * Created by IntelliJ IDEA.
 * User: navit
 * Date: 2/5/12
 * Time: 12:51 PM
 * To change this template use File | Settings | File Templates.
 */

public class CitySearch
{
    private City city;
    private City parent;
    private String title;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City getParent() {
        return parent;
    }

    public void setParent(City parent) {
        this.parent = parent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

