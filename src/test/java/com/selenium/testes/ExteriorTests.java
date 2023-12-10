package com.selenium.testes;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ExteriorTests {
	private static String driverPath = ""; // Setting default driver for compatibility
	private static WebDriver driver;
	
	@BeforeAll
	public static void login() {
		if (!driverPath.isEmpty()) {
			System.setProperty("webdriver.chrome.driver", driverPath);
		}
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dev.e-rentav.com/auth/login");
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys("artur.cunha.108@ufrn.edu.br");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("#jBErW(ky");
		WebElement botaoEntrar = driver.findElement(By.className("btn-success"));
		botaoEntrar.click();
	}
	
	@Test
	void novaOperacao() {
		
		WebElement exterior = driver.findElement(By.cssSelector("#sidenav-collapse-main > ul > li:nth-child(6)"));
	    exterior.click();

	    WebElement lancamentos = driver.findElement(By.cssSelector("#navbar-exterior > ul > li:nth-child(2) > a"));
	    lancamentos.click();


	    WebElement addNovo = driver.findElement(By.cssSelector("#header > div > div > div > div.col-xs-12.col-sm-8.col-md-7 > div > a.btn.btn-success.btn-sm"));
	    addNovo.click();

	    WebElement tipo = driver.findElement(By.cssSelector("#operation > div.card-body > div > div:nth-child(1) > div > div > div > input"));
	    tipo.click();
	    tipo.sendKeys("Equity Option");

	    WebElement conta = driver.findElement(By.cssSelector("#operation > div.card-body > div > div:nth-child(2) > div > div > div > input"));
	    conta.click();
	    conta.sendKeys("U8746345 - IB");
	    conta.sendKeys(Keys.DOWN);
	    conta.sendKeys(Keys.ENTER);

	    WebElement desc = driver.findElement(By.id("description"));
	    desc.click();
	    desc.sendKeys("TESTE 051223 59 PUT");

	    WebElement dataOp = driver.findElement(By.id("date_time_trade"));
	    dataOp.click();
	    dataOp.sendKeys("04102023");

	    WebElement dtVenc = driver.findElement(By.id("expiration_date"));
	    dtVenc.click();
	    dtVenc.sendKeys("05122023");

	    WebElement simbolo = driver.findElement(By.id("symbol"));
	    simbolo.click();
	    simbolo.sendKeys("TEST");

	    WebElement qtd = driver.findElement(By.id("quantity"));
	    qtd.click();
	    qtd.sendKeys("5");


	    WebElement mult = driver.findElement(By.id("multiplier"));

	    mult.click();
	    mult.sendKeys(Keys.CONTROL + "a");
	    mult.sendKeys(Keys.DELETE);
	    mult.sendKeys("100");

	    WebElement valTot = driver.findElement(By.name("value"));
	    valTot.sendKeys(Keys.CONTROL + "a");
	    valTot.sendKeys(Keys.DELETE);
	    valTot.sendKeys("160.00");


	    WebElement valComm = driver.findElement(By.name("commissions"));
	    valComm.sendKeys(Keys.CONTROL + "a");
	    valComm.sendKeys(Keys.DELETE);
	    valComm.sendKeys("1.00");

	    WebElement taxaAdmin = driver.findElement(By.name("fees"));
	    taxaAdmin.sendKeys(Keys.CONTROL + "a");
	    taxaAdmin.sendKeys(Keys.DELETE);
	    taxaAdmin.sendKeys("0.13");

	    WebElement strike = driver.findElement(By.name("strike_price"));
	    strike.sendKeys(Keys.CONTROL + "a");
	    strike.sendKeys(Keys.DELETE);
	    strike.sendKeys("59");

	    WebElement posicao = driver.findElement(By.cssSelector("#operation > div.card-body > div > div:nth-child(13) > div > div > div > input"));
	    posicao.click();
	    posicao.sendKeys("SELL_TO_OPEN");
	    posicao.sendKeys(Keys.DOWN);
	    posicao.sendKeys(Keys.ENTER);


	    WebElement callPut = driver.findElement(By.cssSelector("#operation > div.card-body > div > div:nth-child(14) > div > div > div > input"));
	    callPut.click();
	    callPut.sendKeys("PUT");
	    callPut.sendKeys(Keys.DOWN);
	    callPut.sendKeys(Keys.ENTER);

	    WebElement numOrdem = driver.findElement(By.name("order"));
	    numOrdem.click();
	    numOrdem.sendKeys("");

	    WebElement salvarBtn = driver.findElement(By.className("btn-success"));
	    salvarBtn.click();
	    
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		By feedback = By.className("alert");
		WebElement validAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(feedback));		
		
		assertTrue(validAlert.getText().contains("Operação adicionado!"));
	}
	@AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
