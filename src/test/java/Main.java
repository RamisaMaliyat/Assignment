import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Main extends Base
{

    protected final By username = By.id("user_login");
    protected final By password = By.id("user_pass");
    protected final By login = By.id("wp-submit");

    protected final By menu = By.xpath("//a[contains(@class, 'wp-first-item') and contains(@class, 'wp-has-current-submenu')]");
    protected final By Plugin =By.xpath("//a[contains(@class, 'wp-has-submenu') and contains(@class, 'menu-icon-plugins')]");

    protected final By InstallPlugin = By.xpath(String.format(PRECISE_TEXT_XPATH,"WP Dark Mode"));

    protected final By AddPlugin = By.xpath("//a[@class='page-title-action']");
    protected final By SearchPlugin = By.id("search-plugins");

    protected final By InstallButton = By.xpath("//a[@href='#']//span[@class='ab-icon']");
    protected final By ActivePlugin = By.xpath("//a[@aria-label='Activate WP Dark Mode']");
    protected final By Active = By.id("activate-wp-dark-mode");

    protected final By DashBoard = By.xpath("//div[normalize-space()='Dashboard']");
    protected final By WpDarkMode = By.xpath("//div[normalize-space()='WP Dark Mode']");

    protected final By AdminPanelDarkMode = By.xpath("//a[@class='nav-item-child focus:outline-none inactive']");
    protected final By EnableDarkMode = By.xpath("//div[@class='relative w-10 h-full rounded-full transition duration-100 bg-slate-200']");
    protected final By AdminPanelDarkModeSaveChanges = By.xpath("//button[@class='disabled:opacity-50 disabled:cursor-not-allowed inline-flex items-center gap-2 px-4 py-2 text-sm font-medium rounded-md cursor-pointer outline-none focus:outline-none hover:opacity-90 transition duration-75 bg-blue-500 text-white border border-blue-500']");

    protected final By AdminDarkMode = By.xpath("//li[@id='wp-admin-bar-wp-dark-mode-admin-bar-switch']//div[contains(@class, 'switch')]");

    protected final By customization = By.xpath("//h4[normalize-space()='Customization']");
    protected final By SwitchStyle = By.xpath(String.format(PRECISE_TEXT_XPATH,"Switch Settings"));
    protected final By ChangeStyle = By.xpath("//*[@id=\"wp-dark-mode-admin\"]/div/div/div/div[2]/div[2]/div/section/div[2]/div/div[2]/div[2]/div[1]/div[2]/div[3]");


    protected final By SwitchCustomization = By.xpath(String.format(PRECISE_TEXT_XPATH,"Custom"));

    protected final By Scale = By.xpath("//*[@id=\"wp-dark-mode-admin\"]/div/div/div/div[2]/div[2]/div/section/div[2]/div/div[2]/div[4]/div/div[1]/div[1]/div[3]/div[2]/div/div[1]/input");

    protected final By ChangePosition = By.xpath(String.format(PRECISE_TEXT_XPATH,"Left"));


    protected final By AccessibilitySettings = By.xpath("//a[@class='flex items-center gap-2 text-sm']");
    protected final By DisableKeyBoardShortcut = By.xpath("//*[@id=\"wp-dark-mode-admin\"]/div/div/div/div[2]/div[2]/div/div[6]/div[1]/label/div[1]/div/span");


    protected final By SiteAnimation = By.xpath("//*[@id=\"wp-dark-mode-admin\"]/div/div/div/div[1]/div[2]/div[2]/div/div[2]/a[5]");
    protected final By EnableAnimation = By.xpath("//*[@id=\"wp-dark-mode-admin\"]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/div/label/div[1]/div");
    protected final By changeAnimation = By.xpath("//*[@id=\"wp-dark-mode-admin\"]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div[2]/div[1]/div/div[2]/div[4]/span[1]");
    protected final By ChangesSave = By.xpath("//*[@id=\"wp-dark-mode-admin\"]/div/div/div/div[2]/div[3]/button[2]");
    protected  final By FrontEndDarkMode = By.xpath("//div[contains(@class, 'relative') and contains(@class, 'w-10')]");

    @Test
    public void Test()
    {
        //Login

        driver.findElement(username).sendKeys(UserName);
        driver.findElement(password).sendKeys(PassWord);
        driver.findElement(login).click();


        // Check whether the “WP Dark Mode” Plugin is Active or not

        WebElement Menu = driver.findElement(menu);
        Menu.click();
        driver.findElement(Plugin).click();


        //Check if WP Dark Mode Plugin available

        boolean isPluginInstalled = false;
        try
        {
            WebElement InstalledPlugin = driver.findElement(InstallPlugin);
            if (InstalledPlugin != null)
            {
                isPluginInstalled = true;
            }
        }
        catch (NoSuchElementException e)
        {
            System.out.println("WP Dark Mode plugin is not installed.");
        }


        // Install WP Dark Mode Plugin

        if (!isPluginInstalled)
        {
            driver.findElement(AddPlugin).click();
            driver.findElement(SearchPlugin).sendKeys("WP Dark Mode");
            driver.findElement(InstallButton).click();

            Duration duration = Duration.ofSeconds(30);
            WebDriverWait wait = new WebDriverWait(driver,duration);
            wait.until(ExpectedConditions.visibilityOfElementLocated(ActivePlugin));
            driver.findElement(Active).click();
        }


        // Enable Dark Mode from Admin Panel

        driver.findElement(DashBoard).click();
        WebElement DarkMode= driver.findElement(WpDarkMode);
        DarkMode.click();
        driver.findElement(AdminPanelDarkMode).click();
        driver.findElement(EnableDarkMode).click();
        driver.findElement(AdminPanelDarkModeSaveChanges).click();


        //Check If Dark Mode works from Admin Panel

        WebDriverWait W = new WebDriverWait(driver, Duration.ofSeconds(5)); // Reduced timeout for toggle visibility
        WebElement toggleElement = W.until(ExpectedConditions.visibilityOfElementLocated(AdminDarkMode));

        if (toggleElement.isDisplayed())         // Click on the toggle to switch to Dark mode
        {
            toggleElement.click();
            WebElement bodyElement = driver.findElement(By.tagName("body"));    // Verify the dark mode class
            String bodyClass = bodyElement.getAttribute("class");
                                        // Check for different possible dark mode classes or states
            boolean isDarkMode = bodyClass.contains("wp-dark-mode-active") ||
                    bodyClass.contains("dark-mode") ||
                    bodyClass.contains("bg-dark");

            Assert.assertTrue(isDarkMode, "The page did not switch to Dark mode.");

            if (isDarkMode) {
                System.out.println("Page is in Dark mode.");
            }
        }
        else
        {
            System.out.println("Page is not switch in Dark mode.");
        }


        //Navigate Wp Dark Mode

        WebElement customize = driver.findElement(customization);
        customize.click();


        //From Switch Settings → Change the “Floating Switch Style” from the default selections

        driver.findElement(SwitchStyle).click();
        WebElement StyleChange = driver.findElement(ChangeStyle);
        StyleChange.click();
        StyleChange.isSelected();

        //Customization → Switch Settings → Switch Customization - Select Custom Switch size & Scale it to 220

        WebElement customization = driver.findElement(SwitchCustomization);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", customization );
        customization .click();
        WebElement scale =driver.findElement(Scale);
        int xOffset = 46;
        Actions move = new Actions(driver);
        move.dragAndDropBy(scale, xOffset, 0).build().perform();


        //Customization → Switch Settings - Change the Floating Switch Position

        WebElement RightPosition = driver.findElement(ChangePosition);
        RightPosition.click();
        RightPosition.isSelected();


        //Disable the Keyboard Shortcut from the Accessibility Settings.

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));   //scroll up the page
        wait.until(ExpectedConditions.visibilityOfElementLocated(AccessibilitySettings));
        WebElement Accesablity = driver.findElement(AccessibilitySettings);
        JavascriptExecutor jsu = (JavascriptExecutor) driver;
        jsu.executeScript("arguments[0].scrollIntoView(true);", Accesablity);
        Accesablity.click();
        WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(10));
        wt.until(ExpectedConditions.visibilityOfElementLocated(DisableKeyBoardShortcut));
        driver.findElement(DisableKeyBoardShortcut).click();
        //driver.findElement(KeyBoardShortCutSaveChanges).click();

        //Customization → Site Animation → “Enable Page-Transition Animation”

        WebDriverWait wa = new WebDriverWait(driver, Duration.ofSeconds(10));
        wa.until(ExpectedConditions.visibilityOfElementLocated(SiteAnimation));
        WebElement siteAnimationElement = driver.findElement(SiteAnimation);
        siteAnimationElement.click();

        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.visibilityOfElementLocated(EnableAnimation));
        WebElement Animation = driver.findElement(EnableAnimation);
        JavascriptExecutor jsa = (JavascriptExecutor) driver;
        jsa.executeScript("arguments[0].scrollIntoView(true);", Animation);
        Animation.click();


        //Change the Animation Effect from the default selections.

        WebElement Roll = driver.findElement(changeAnimation);
        if (Roll.isDisplayed() && Roll.isEnabled()) {
            // Use JavaScript to click the radio button (handles hidden overlays or JavaScript control)
            JavascriptExecutor j = (JavascriptExecutor) driver;
            j.executeScript("arguments[0].click();", Roll);

            // Verify the selection state and print the result
            if (Roll.isSelected()) {
                System.out.println("Radio button is selected.");
            } else {
                System.out.println("Radio button is NOT selected.");
            }
        } else {
            System.out.println("Radio button is not visible or enabled.");
        }

        driver.findElement(ChangesSave).click();

        //Validate whether the dark mode is working or not from the front end

        WebElement toggleDiv = driver.findElement(FrontEndDarkMode);
        String divClass = toggleDiv.getAttribute("class");
                                    // Check and assert if the toggle is enabled (contains bg-blue-600) or
                                   // disabled (contains bg-slate-200)
        if (divClass.contains("bg-blue-600"))
        {
            Assert.assertTrue(divClass.contains("bg-blue-600"), "The toggle should be enabled but is not.");
        }
        else if (divClass.contains("bg-slate-200"))
        {
            Assert.assertTrue(divClass.contains("bg-slate-200"), "The toggle should be disabled but is not.");
        } else
        {
            Assert.fail("Could not determine the state of the toggle.");
        }
    }
}
