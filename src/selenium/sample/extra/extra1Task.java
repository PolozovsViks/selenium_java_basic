package selenium.sample.extra;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class extra1Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/act";
    String new_url = "https://kristinek.github.io/site/examples/link1";
//
    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();

        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void navigateBack() throws Exception {
//        TODO
//        open page with url "https://kristinek.github.io/site/examples/po"
        driver.get("https://kristinek.github.io/site/examples/po");

//        click "More > " for the top left element
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/p/a")).click();

//        check that the url now "https://kristinek.github.io/site/examples/po1"
        assertEquals("https://kristinek.github.io/site/examples/po1", driver.getCurrentUrl());
        Thread.sleep(900);

//        using driver navigation go back to "https://kristinek.github.io/site/examples/po"
        driver.navigate().back();

//        check that the page now is "https://kristinek.github.io/site/examples/po"
        assertEquals("https://kristinek.github.io/site/examples/po", driver.getCurrentUrl());

    }

    @Test
    public void navigateForward() throws Exception {
//        TODO
//        open page with url "https://kristinek.github.io/site/examples/po"
        driver.get("https://kristinek.github.io/site/examples/po");

//        click "More > " for the top left element
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/p/a")).click();

//        using driver navigation go back to "https://kristinek.github.io/site/examples/po"
        driver.navigate().back();

//        using driver navigation go forward to "https://kristinek.github.io/site/examples/po1"
        driver.navigate().forward();

//        check that the page now is "https://kristinek.github.io/site/examples/po1"
        assertEquals("https://kristinek.github.io/site/examples/po1", driver.getCurrentUrl());

    }

    @Test
    public void refresh() throws Exception {
//        TODO
//        open page "https://kristinek.github.io/site/examples/act"
        driver.get("https://kristinek.github.io/site/examples/act");

//        click on "Show" button in 'Button' section
//        driver.findElement(By.cssSelector(".w3-small#show_text")).click();


//        check that text "I am here!" is seen
//        refresh page
//        check that text "I am here!" is not seen
    }
}
