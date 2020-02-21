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

        WebElement element ;

        assertEquals("http://localhost:8080/projeto_pw_simulador_war_exploded/", webDriver.getCurrentUrl());

        Thread.sleep(2000L);
        element = webDriver.findElement(By.id("email"));
        element.sendKeys("user@email.com");
        element = webDriver.findElement(By.id("password"));
        element.sendKeys("123456");

        element = webDriver.findElement(By.id("button-login"));
        Thread.sleep(2000L);
        element.click();

        element = webDriver.findElement(By.id("button-user"));
        Thread.sleep(2000L);
        element.click();

        element = webDriver.findElement(By.id("button-logout"));
        Thread.sleep(3000L);
        element.click();

    }

    @Test
    public void testeComprarAtivo() throws InterruptedException {
        WebElement element ;
        assertEquals("http://localhost:8080/projeto_pw_simulador_war_exploded/", webDriver.getCurrentUrl());

        Thread.sleep(2000L);
        element = webDriver.findElement(By.id("email"));
        element.sendKeys("user3@email.com");
        element = webDriver.findElement(By.id("password"));
        element.sendKeys("123456");

        element = webDriver.findElement(By.id("button-login"));
        Thread.sleep(2000L);
        element.click();

        element = webDriver.findElement(By.id("button-ativos"));
        element.click();
        Thread.sleep(5000L);

        element = webDriver.findElement(By.id("quantidade"));
        element.sendKeys("1");
        element = webDriver.findElement(By.id("button-comprar"));
        element.click();
        Thread.sleep(2000L);


    }

    @Test
    public void comprarSemSaldo() throws InterruptedException {

        WebElement element ;
        assertEquals("http://localhost:8080/projeto_pw_simulador_war_exploded/", webDriver.getCurrentUrl());

        Thread.sleep(2000L);
        element = webDriver.findElement(By.id("email"));
        element.sendKeys("user@email.com");
        element = webDriver.findElement(By.id("password"));
        element.sendKeys("123456");

        element = webDriver.findElement(By.id("button-login"));
        Thread.sleep(2000L);
        element.click();

        element = webDriver.findElement(By.id("button-user"));
        Thread.sleep(2000L);
        element.click();

        element = webDriver.findElement(By.id("button-carteira"));
        Thread.sleep(2000L);
        element.click();

        element = webDriver.findElement(By.id("button-fechar"));
        Thread.sleep(2000L);
        element.click();
        Thread.sleep(4000L);


        element = webDriver.findElement(By.id("button-ativos"));
        element.click();
        Thread.sleep(5000L);

        element = webDriver.findElement(By.id("quantidade"));
        element.sendKeys("1");
        element = webDriver.findElement(By.id("button-comprar"));
        element.click();
        Thread.sleep(4000L);

 }

        @Test
        public void exibirHistorico() throws InterruptedException {

            WebElement element ;
            assertEquals("http://localhost:8080/projeto_pw_simulador_war_exploded/", webDriver.getCurrentUrl());

            Thread.sleep(2000L);
            element = webDriver.findElement(By.id("email"));
            element.sendKeys("user@email.com");
            element = webDriver.findElement(By.id("password"));
            element.sendKeys("123456");

            element = webDriver.findElement(By.id("button-login"));
            Thread.sleep(2000L);
            element.click();

            element = webDriver.findElement(By.id("button-historico"));
            Thread.sleep(2000L);
            element.click();
            Thread.sleep(5000L);

            element = webDriver.findElement(By.id("button-index"));
            Thread.sleep(2000L);
            element.click();

            element = webDriver.findElement(By.id("button-user"));
            Thread.sleep(2000L);
            element.click();

            element = webDriver.findElement(By.id("button-logout"));
            Thread.sleep(3000L);
            element.click();

        }

            @Test
            public void editarDados() throws InterruptedException {
                WebElement element ;
                assertEquals("http://localhost:8080/projeto_pw_simulador_war_exploded/", webDriver.getCurrentUrl());

                Thread.sleep(2000L);
                element = webDriver.findElement(By.id("email"));
                element.sendKeys("user@email.com");
                element = webDriver.findElement(By.id("password"));
                element.sendKeys("123456");

                element = webDriver.findElement(By.id("button-login"));
                Thread.sleep(2000L);
                element.click();

                element = webDriver.findElement(By.id("button-user"));
                Thread.sleep(2000L);
                element.click();

                element = webDriver.findElement(By.id("button-dados"));
                Thread.sleep(2000L);
                element.click();

                element = webDriver.findElement(By.id("button-editardados"));
                Thread.sleep(2000L);
                element.click();

                element = webDriver.findElement(By.id("editar-nome"));
                element.clear();
                element.sendKeys("yasuo maestria sete fon");

                element = webDriver.findElement(By.id("button-salvaredit"));
                Thread.sleep(3000L);
                element.click();
                Thread.sleep(3000L);
            }

            @Test
            public void cadastrarUsuario() throws InterruptedException {

                WebElement element ;
                assertEquals("http://localhost:8080/projeto_pw_simulador_war_exploded/", webDriver.getCurrentUrl());

                element = webDriver.findElement(By.id("button-cadastrar"));
                Thread.sleep(2000L);
                element.click();

                Thread.sleep(2000L);
                element = webDriver.findElement(By.id("nome"));
                element.sendKeys("teste");
                element = webDriver.findElement(By.id("dataNasc"));
                element.sendKeys("05/05/2005");
                element = webDriver.findElement(By.id("email"));
                element.sendKeys("teste2@email.com");
                element = webDriver.findElement(By.id("senha"));
                element.sendKeys("123456");

                element = webDriver.findElement(By.id("button-salvar"));
                Thread.sleep(2000L);
                element.click();
                Thread.sleep(2000L);

                element = webDriver.findElement(By.id("email"));
                element.sendKeys("teste2@email.com");
                element = webDriver.findElement(By.id("password"));
                element.sendKeys("123456");

                element = webDriver.findElement(By.id("button-login"));
                Thread.sleep(2000L);
                element.click();


            }

}
