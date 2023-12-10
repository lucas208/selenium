package com.selenium.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeleniumApplicationTests {
	
	private static String driverPath = "C:\\Users\\usuario\\Documents\\Lucas\\Estudos\\IMD\\Engsoft_2023.2\\testes_2\\chromedriver-win64\\chromedriver.exe";
	private static WebDriver driver;
	
	@BeforeAll
	public static void login() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get("https://dev.e-rentav.com/auth/login");
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys("artur.cunha.108@ufrn.edu.br");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("#jBErW(ky");
		WebElement botaoEntrar = driver.findElement(By.className("btn-success"));
		botaoEntrar.click();
	}
	
	@Test
	void cadastroRemessaSemConta() {
		driver.get("https://dev.e-rentav.com/153/banking/remittances");
		driver.findElement(By.className("btn-success")).click();
		driver.findElement(By.name("vdollar")).sendKeys("5555.75");
		driver.findElement(By.className("btn-success")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		By feedback = By.className("invalid-feedback");
		WebElement proibicao = wait.until(ExpectedConditions.visibilityOfElementLocated(feedback));
		assertEquals(proibicao.getText(), "O campo id brokerage account é obrigatório.");
	}
	
	@Test
	void cadastroRemessaValorNegativo() {
		driver.get("https://dev.e-rentav.com/153/banking/remittances");
		driver.findElement(By.className("btn-success")).click();
		driver.findElement(By.name("vdollar")).sendKeys("-5555.75");
		driver.findElement(By.className("btn-success")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		By feedback = By.className("invalid-feedback");
		WebElement proibicao = wait.until(ExpectedConditions.visibilityOfElementLocated(feedback));
		assertEquals(proibicao.getText(), "O é inválido.");
	}
	
	@AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
