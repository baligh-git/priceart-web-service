package com.xantrix.webapp.UnitTest.ControllerTest;


import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.xantrix.webapp.Application;
import com.xantrix.webapp.appconf.AppConfig;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrezziControllerTest
{
    private MockMvc mockMvc;

    @Autowired
	private WebApplicationContext wac;

	@Before
	public void setup()
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }
    
    String JsonData =  "{\r\n" + 
    		"	\"codArt\":\"002000301\",\r\n" + 
    		"	\"idList\":\"3\",\r\n" + 
    		"	\"prezzo\":1.99\r\n" + 
    		"}";
			
	@Test
	public void A_GetPrzCodArtTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/prezzi/cerca/codice/002000301")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.prezzo").value("1.07")) 
			.andReturn();
	}

	//MODIFICARE LA PROPRIETA' application.listino=3
	@Test
	public void B_ErrGetPrzCodArtTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/prezzi/cerca/codice/002000301")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.prezzo").value("0.0")) 
			.andReturn();
	}

	@Test
	public void A_testInsPrezzo() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/prezzi/inserisci")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonData)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print());
	}

	@Test
	public void E_testDelPrezzo() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.delete("/prezzi/elimina/002000301/3")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value(200))
				.andExpect(jsonPath("$.message").value("Eliminazione Prezzo Eseguita Con Successo"))
				.andDo(print());
	}
	
}