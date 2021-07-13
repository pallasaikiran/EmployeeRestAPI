package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.example.demo.controller.EmployeeController;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	public RestTemplate getTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private EmployeeController employeeController;

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private EmployeeService employeeService;

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);

	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(json, clazz);
	}

	@Test
	public void updateEmployee() throws Exception {
		List<String> al = new ArrayList<String>();
		al.add("9912995709");
		al.add("kiran@piprasolutions.com");

		String uri = "http://localhost:9191/employee/update/2";
		Employee e = new Employee();
		e.setName("kiran");
		e.setAge(26);
		e.setDepartment("QA");
		e.setIsActive(true);
		e.setContact(al);

		String inputJson = mapToJson(e);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		// String content=mvcResult.getResponse().getContentAsString();
		// assertEquals(content,);
	}

	@Test
	public void shouldVerifyInvalidUpdateEmployeeId() throws Exception {
		String uri = "http://localhost:9191/employee/update/abc";
		this.mockMvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON)
				// .content("{\"id\": 999, \"name\": \"C. S. Lewis\", \"email\":
				// \"cslewis@books.com\"}")
				.content(
						"{\"id\":\"0\",\"name\": \"xyz\",\"age\":\"15\",\"isActive\":\"false\",\"department\":\"Support\",\"contact\": [\"991256789\",\"abc@gmail.com\"]}")
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	public void saveEmployee() throws Exception {
		List<String> s = new ArrayList<String>();
		s.add("9949383199");
		s.add("jhon@piprasolutions.com");
		String uri = "http://localhost:9191/employee/saveEmployee";

		Employee e = new Employee();
		e.setId(4);
		e.setName("Jhon");
		e.setAge(32);
		e.setDepartment("Devops");
		e.setIsActive(true);
		e.setContact(s);

		String inputJson = mapToJson(e);
		/*
		 * RestTemplate template = getTemplate(); HttpHeaders headers = new
		 * HttpHeaders(); headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		 * headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE); HttpEntity
		 * requestpacket = new HttpEntity<>(e,headers);
		 * 
		 * ResponseEntity<String>response=template.exchange(new
		 * URI(uri),HttpMethod.POST,requestpacket,String.class);
		 * 
		 * int status=response.getStatusCodeValue();
		 */

		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void shouldVerifyInvalidSaveEmployee() throws Exception {
		String uri = "http://localhost:9191/employee/saveEmployee";
		this.mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(
				"{\"id\":\"0\",\"name\": \"\",\"age\":\"15\",\"isActive\":\"false\",\"department\":\"Support\",\"contact\": [\"991256789\",\"abc@gmail.com\"]}")
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isBadRequest()).andReturn();

	}

	@Test
	public void deleteEmployee() throws Exception {
		String uri = "http://localhost:9191/employee/delete/3";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void shouldVerifyInvalidEmployeeRemove() throws Exception {
		String uri = "http://localhost:9191/employee/delete/"+"Jhon";
		this.mockMvc.perform(MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isBadRequest());
		// .andReturn();
	}

	@Test
	public void getAllEmployees() throws Exception {

		String uri = "http://localhost:9191/employee/allEmployees";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		// String content=mvcResult.getResponse().getContentAsString();
		// Employee[] employeesList=mapFromJson(content, Employee[].class);
		// assertTrue(employeesList.length>0);

	}

}
