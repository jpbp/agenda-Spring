package com.agenda.controller.integracao;

import org.json.JSONException;

import org.junit.jupiter.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.*;
import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

//inicar o teste com portas aleatorias 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class AgendaIntegracaoTest {
	
	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	@Test
	public void testObterContato() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/obter/1"),
				HttpMethod.GET, entity, String.class);

		String expected = "{\"codigo\":1,\"nome\":\"jp\",\"telefone\":\"5454545\",\"email\":\"aisjiajsi\",\"cep\":\"37200080\",\"rua\":\"Rua Primeiro de Maio\",\"bairro\":\"Centro\",\"cidade\":\"Lavras\",\"ibge\":\"3138203\",\"uf\":\"MG\"}";
		System.out.println(response.getBody().toString());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}	
	
	
}
