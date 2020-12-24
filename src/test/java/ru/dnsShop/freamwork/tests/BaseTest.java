package ru.dnsShop.freamwork.tests;

import org.junit.After;
import org.junit.Before;
import ru.dnsShop.freamwork.managers.InitializeManager;
import ru.dnsShop.freamwork.managers.ManagerPages;

public class BaseTest {

    protected ManagerPages app = ManagerPages.getManagerPages();

    @Before
    public void beforeEach() {
        InitializeManager.initFramework();
    }

//    @After
//    public void afterEach() {
//        InitializeManager.quitFramework();
//    }
}
