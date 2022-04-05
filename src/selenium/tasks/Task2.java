package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


import java.io.File;

import static org.junit.Assert.*;

public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

//    @After
//    public void closeBrowser() {
//        driver.close();
//    }

    @Test
    public void initialFeedbackPage() throws Exception {

        WebElement nameInput = driver.findElement(By.id("fb_name"));
        WebElement ageInput = driver.findElement(By.id("fb_age"));

        WebElement langEng = driver.findElement(By.xpath("//*[@id='lang_check']/input[1]"));
        WebElement langFrench = driver.findElement(By.xpath("//*[@id='lang_check']/input[2]"));
        WebElement langSpanish = driver.findElement(By.xpath("//*[@id='lang_check']/input[3]"));
        WebElement langChinese = driver.findElement(By.xpath("//*[@id='lang_check']/input[4]"));

        WebElement genderMale = driver.findElement(By.xpath("//*[@id='fb_form']/form/div[4]/input[1]"));
        WebElement genderFemale = driver.findElement(By.xpath("//*[@id='fb_form']/form/div[4]/input[2]"));
        WebElement genderUnclear = driver.findElement(By.xpath("//*[@id='fb_form']/form/div[4]/label[3]"));

        WebElement chooseDropdown = driver.findElement(By.id("like_us"));

        WebElement sendButton = driver.findElement(By.xpath("//*[@id='fb_form']/form/button"));
//         TODO:
//         check that all field are empty and no tick are clicked
        assertEquals("", nameInput.getText());
        assertEquals("", ageInput.getText());

        assertFalse(langEng.isSelected());
        assertFalse(langFrench.isSelected());
        assertFalse(langSpanish.isSelected());
        assertFalse(langChinese.isSelected());

        assertFalse(genderMale.isSelected());
        assertFalse(genderFemale.isSelected());

//         "Don't know" is selected in "Genre"
        assertTrue(genderUnclear.isDisplayed());
        assertEquals(null, genderUnclear.getAttribute("value"));

//         "Choose your option" in "How do you like us?"
        assertEquals("Choose your option", chooseDropdown.findElement(By.xpath("//*[@id='like_us']/option[1]")).getText());

//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));

    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:

//         click "Send" without entering any data
        driver.findElement(By.xpath("//*[@id='fb_form']/form/button")).click();

//         check fields are empty or null
        assertEquals("", driver.findElement(By.xpath("//*[@id='name']")).getText());
        assertEquals("", driver.findElement(By.xpath("//*[@id='age']")).getText());
        assertEquals("", driver.findElement(By.xpath("//*[@id='language']")).getText());
        assertEquals("null", driver.findElement(By.xpath("//*[@id='gender']")).getText());
        assertEquals("null", driver.findElement(By.xpath("//*[@id='option']")).getText());
        assertEquals("", driver.findElement(By.xpath("//*[@id='comment']")).getText());

//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id='fb_thx']/div/div[2]/button[1]")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id='fb_thx']/div/div[2]/button[1]")).getCssValue("color"));


    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        driver.findElement(By.id("fb_name")).sendKeys("test");
        driver.findElement(By.id("fb_age")).sendKeys("27");

        driver.findElement(By.xpath("//*[@id='lang_check']/input[1]")).click();
        driver.findElement(By.xpath("//*[@id='lang_check']/input[3]")).click();

        driver.findElement(By.xpath("//*[@id='fb_form']/form/div[4]/input[1]")).click();

        Select dropdown = new Select(driver.findElement(By.id("like_us")));

        driver.findElement(By.id("like_us")).click();
        dropdown.selectByVisibleText("Good");

        driver.findElement(By.xpath("//*[@id='fb_form']/form/div[6]/textarea")).sendKeys("Test comment comment test");

        driver.findElement(By.xpath("//*[@id='fb_form']/form/button")).click();

//         check fields are filled correctly
        assertEquals("test", driver.findElement(By.xpath("//*[@id='name']")).getText());
        assertEquals("27", driver.findElement(By.xpath("//*[@id='age']")).getText());
        assertEquals("English,Spanish", driver.findElement(By.xpath("//*[@id='language']")).getText());
        assertEquals("male", driver.findElement(By.xpath("//*[@id='gender']")).getText());
        assertEquals("Good", driver.findElement(By.xpath("//*[@id='option']")).getText());
        assertEquals("Test comment comment test", driver.findElement(By.xpath("//*[@id='comment']")).getText());

//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id='fb_thx']/div/div[2]/button[1]")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id='fb_thx']/div/div[2]/button[1]")).getCssValue("color"));

    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        driver.findElement(By.id("fb_name")).sendKeys("test");

//         click "Send"
        driver.findElement(By.xpath("//*[@id='fb_form']/form/button")).click();

//         click "Yes"
        driver.findElement(By.xpath("//*[@id='fb_thx']/div/div[2]/button[1]")).click();

//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, test, for your feedback!", driver.findElement(By.xpath("//*[@id='message']")).getText());

//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id='fb_thx']/div")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id='fb_thx']/div")).getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.xpath("//*[@id='fb_form']/form/button")).click();

//         click "Yes"
        driver.findElement(By.xpath("//*[@id='fb_thx']/div/div[2]/button[1]")).click();

//         check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!", driver.findElement(By.xpath("//*[@id='message']")).getText());

//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id='fb_thx']/div")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id='fb_thx']/div")).getCssValue("color"));

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        driver.findElement(By.id("fb_name")).sendKeys("test");
        driver.findElement(By.id("fb_age")).sendKeys("27");

        driver.findElement(By.xpath("//*[@id='lang_check']/input[1]")).click();
        driver.findElement(By.xpath("//*[@id='lang_check']/input[3]")).click();

        driver.findElement(By.xpath("//*[@id='fb_form']/form/div[4]/input[1]")).click();

        Select dropdown = new Select(driver.findElement(By.id("like_us")));

        driver.findElement(By.id("like_us")).click();
        dropdown.selectByVisibleText("Good");

        driver.findElement(By.xpath("//*[@id='fb_form']/form/div[6]/textarea")).sendKeys("Test comment comment test");



//         click "Send"

        driver.findElement(By.xpath("//*[@id='fb_form']/form/button")).click();
        Thread.sleep(500);

//         click "No"
        driver.findElement(By.xpath("//*[@id='fb_thx']/div/div[2]/button[2]")).click();

//         check fields are filled correctly

        assertEquals("", driver.findElement(By.xpath("//*[@id='fb_name']")).getText());
        assertEquals("", driver.findElement(By.id("fb_age")).getText());

        assertTrue(driver.findElement(By.xpath("//*[@id='lang_check']/input[1]")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[@id='lang_check']/input[2]")).isSelected());
        assertTrue(driver.findElement(By.xpath("//*[@id='lang_check']/input[3]")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[@id='lang_check']/input[4]")).isSelected());

        assertTrue(driver.findElement(By.xpath("//*[@id='fb_form']/form/div[4]/input[1]")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[@id='fb_form']/form/div[4]/input[2]")).isSelected());

        assertTrue(driver.findElement(By.xpath("//*[@id='like_us']/option[2]")).isSelected());

        assertEquals("",driver.findElement(By.xpath("//*[@id='fb_form']/form/div[6]/textarea")).getText());


    }
}
