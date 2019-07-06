package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;

import static com.lazerycode.selenium.util.AssignDriver.initQueryObjects;

public class BaiduHomePage {

    private Query searchBar = new Query().defaultLocator(By.id("kw"));
    private Query baiduSearch = new Query().defaultLocator(By.className("s_btn"));
    private Query imFeelingLucky = new Query().defaultLocator(By.name("btnI"));

    public BaiduHomePage() throws Exception {
        initQueryObjects(this, DriverBase.getDriver());
    }
    public BaiduHomePage enterSearchTerm(String searchTerm) {
        searchBar.findWebElement().clear();
        searchBar.findWebElement().sendKeys(searchTerm);
        return this;
    }

    public BaiduHomePage submitSearch() {
        baiduSearch.findWebElement().submit();

        return this;
    }

    public void getLucky() {
        imFeelingLucky.findWebElement().click();
    }

}
