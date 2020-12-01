package com.agenda.funcional;

import java.net.MalformedURLException;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class AgendaFuncionalTest {
	@Test
	public void testFuncionalCerto() throws InterruptedException, MalformedURLException {
		//instanciando o webdrive
		DesiredCapabilities cap =  DesiredCapabilities.chrome();
		//esse ip é do meu pc
		//********************ip fixo do servidor, docker selenuim ***************************
		WebDriver driver = new RemoteWebDriver(new URL("http://177.105.35.42:4444/wd/hub"),cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//entrar no site
		//********************ip fixo do server docker do ngnix att ***************************
		System.out.println("aqui1");
		//navegar para o link da agenda
		driver.navigate().to("http://177.105.34.103:9005/agenda");
		//clicar no botao cadastrar contato
		driver.findElement(By.id("add")).click();
		Thread.sleep(3000);
		//preencher o nome com joao paulo
		driver.findElement(By.id("nome")).sendKeys("joao paulo pena");
		//preencher o nome com joao paulo
		driver.findElement(By.id("email")).sendKeys("joao@joao");
		//preencher o nome com joao paulo
		driver.findElement(By.id("telefone")).sendKeys("975585455");
		//cep
		driver.findElement(By.id("cep")).sendKeys("37200080");
		//cep
		driver.findElement(By.id("rua")).sendKeys("");
		driver.findElement(By.id("bairro")).sendKeys("");
		driver.findElement(By.id("cidade")).sendKeys("");
		driver.findElement(By.id("uf")).sendKeys("");
		driver.findElement(By.id("ibge")).sendKeys("");
		System.out.println("aqui4");
		//clicar no btn salvar
		driver.findElement(By.id("salvar")).click();
	
		System.out.println("aqui5");
		//ir para listar
		driver.findElement(By.id("listar")).click();
		driver.quit();
	}
}
