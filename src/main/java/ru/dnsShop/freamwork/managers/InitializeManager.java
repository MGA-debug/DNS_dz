package ru.dnsShop.freamwork.managers;

import java.util.concurrent.TimeUnit;

import static ru.dnsShop.freamwork.managers.DriverManagers.getDriver;
import static ru.dnsShop.freamwork.managers.DriverManagers.quitDriver;
import static ru.dnsShop.freamwork.util.PropertiesConstants.*;

public class InitializeManager {

    public static TestPropManager props = TestPropManager.getTestPropManager();

    public static void initFramework() {
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        getDriver().get(props.getProperty(APP_URL));
    }

    public static void quitFramework() {
        quitDriver();
    }
}
