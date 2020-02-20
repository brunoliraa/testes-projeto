package selenium;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SeleniumTest {

    private static WebDriver webDriver;



    @BeforeClass
    public static void setUpTest() {
//        System.setProperty("webdriver.chrome.driver", "chromedriver");
//        webDriver = new ChromeDriver();
        //webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        System.setProperty("webdriver.gecko.driver", "/home/bruno/pw1/geckodriver");
        webDriver = new FirefoxDriver();
        //webDriver.get("https://www.lynda.com/Selenium-tutorials/Mastering-Selenium-Testing-Tools/521207-2.html");
       }

    @Before
    public void setUp() {
        webDriver.get("http://localhost:8080/projeto_pw_simulador_war_exploded/");
    }

    @AfterClass
    public static void tearDownTest() {
        webDriver.quit();
    }

    @Test
    public void testaLogin() throws InterruptedException {

        // Entrar na página de login
        WebElement element ;
        //element.click();

        assertEquals("http://localhost:8080/projeto_pw_simulador_war_exploded/", webDriver.getCurrentUrl());

        // Preencher o formulário e clicar no botão de login
       // Thread.sleep(10000L);

        Thread.sleep(5000L);
        element = webDriver.findElement(By.id("email"));
        element.sendKeys("user@email.com");
        element = webDriver.findElement(By.id("password"));
        element.sendKeys("123456");

        element = webDriver.findElement(By.id("button-login"));
        Thread.sleep(2000L);
        element.click();

//        Thread.sleep(5000L);
//        element = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div[1]/div/input"));
//        element.sendKeys("user@email.com");
//        element = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div[2]/div/input"));
//        element.sendKeys("123456");
//
//        element = webDriver.findElement(By.id("button-login"));
//        Thread.sleep(2000L);
//        element.click();

//
//        Thread.sleep(5000L);
//        element = webDriver.findElement(By.cssSelector("input[name=email]"));
//        element.sendKeys("user@email.com");
//        element = webDriver.findElement(By.cssSelector("input[name=senha]"));
//        element.sendKeys("123456");
//
//        element = webDriver.findElement(By.id("button-login"));
//        Thread.sleep(2000L);
//        element.click();



    }
}
