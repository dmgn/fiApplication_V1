package com.floatinvoice.test.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:Beans.xml")
@WebAppConfiguration

public class FIApplicationControllerTests {

	@Autowired
	private WebApplicationContext wac;
	
	private ObjectMapper objMapper;
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		this.mockMvc = webAppContextSetup(this.wac).build();
		objMapper = wac.getBean(ObjectMapper.class);
	}
	
	@Test
	public void testViewFiApplication() throws Exception{
		
		RequestBuilder req = get("/viewKYCApp?refId=F881C61808764D9087653B0D5AA0B0E8&acro=DTCC").contentType(MediaType.APPLICATION_JSON)
				.header("remote-user", "abc.xyz@gmail.com");
		MvcResult res = mockMvc.perform(req).andDo(print()).andReturn();
		
	}
	
}
