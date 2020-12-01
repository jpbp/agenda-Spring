package com.agenda.controller.unitario;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import com.agenda.controller.AgendaController;
import com.agenda.models.Agenda;
import com.agenda.repository.AgendaRepository;

import io.restassured.http.ContentType;

@WebMvcTest
class AgendaApplicationTests {

	@Autowired
	private AgendaController ac;
	@MockBean 
	// não podemos usar o repository real da aplicaçao, temos q fazer
	//seja apenas uma dependencia, a implementação no pode interfir, por ser um test unit
	private AgendaRepository ar;
	@BeforeEach // inicia toda vez em cada teste
	public void setup() {
		//quando for subir o teste, so vai instanciar esse controller
		standaloneSetup(ac);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarContato() {
		//criando um mock, instanciando o repository falso do componente 
		when(this.ar.findByCodigo(1L)).thenReturn(new Agenda(1,"jp","jp@jp.com"));
		given().accept(ContentType.JSON).when().get("/obter/{codigo}",1L).then().statusCode(HttpStatus.OK.value());
	}

}
