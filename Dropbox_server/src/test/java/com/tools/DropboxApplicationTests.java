package com.tools ;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Entry.class)
@SpringBootTest
public class DropboxApplicationTests {
	
	private MockMvc mockMvc;
	
	private String attribute = "{\"email\":\"palash\",\"foldername\":\"palash\" , \"directory\" : \"palashdir\" }";
	private String attribute2 = "{\"email\":\"palash\"}";
	private String attribute3 = "{\"email\":\"palash\",\"groupname\":\"palash\" }";
	private String attribute4 = "{\"email\":\"palash\",\"directory\":\"palash\" }";
	private String attribute5 = "{\"email\":\"palash\",\"directory\":\"palash\"  , \"foldername\" : \"palash\" }";
	
	
	@Autowired
    private WebApplicationContext wac;
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void createFolder() throws Exception {
		mockMvc.perform(post("/createFolder")
				.content(attribute)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) ; 
	}
	
	@Test
	public void getFilesHistory() throws Exception {
		mockMvc.perform(post("/getFilesHistory")
				.content(attribute2)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) ; 
	}
	
	@Test
	public void createGroup() throws Exception {
		mockMvc.perform(post("/createGroup")
				.content(attribute3)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) ; 
	}
	
	@Test
	public void getAllGroups() throws Exception {
		mockMvc.perform(post("/getAllGroups")
				.content(attribute2)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) ; 
	}
	
	
	@Test
	public void getAllUsers() throws Exception {
		mockMvc.perform(post("/getAllUsers")
				.content(attribute2)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) ; 
	}
	
	
	@Test
	public void readIndividualFolder() throws Exception {
		mockMvc.perform(post("/readIndividualFolder")
				.content(attribute4)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) ; 
	}
	
	
	@Test
	public void getAllSharedFile() throws Exception {
		mockMvc.perform(post("/getAllSharedFile")
				.content(attribute2)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) ; 
	}
	
	
	@Test
	public void readallStarredfiles() throws Exception {
		mockMvc.perform(post("/readallStarredfiles")
				.content(attribute4)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) ; 
	}
	
	
	@Test
	public void getProfile() throws Exception {
		mockMvc.perform(post("/getProfile")
				.content(attribute2)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) ; 
	}
	
	@Test
	public void checkProfileExist() throws Exception {
		mockMvc.perform(post("/checkProfileExist")
				.content(attribute2)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) ; 
	}
	
	
	@Test
	public void getAllFiles() throws Exception {
		mockMvc.perform(post("/readallfiles")
				.content(attribute5)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) ; 
	}
}