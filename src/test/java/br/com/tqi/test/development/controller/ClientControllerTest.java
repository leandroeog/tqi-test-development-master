package br.com.tqi.test.development.controller;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = "classpath:/script/V0__test_script_inicial.sql")
public class ClientControllerTest extends AbstractBaseControllerTest {

    @Test
    @Transactional(readOnly = true)
    public void mustFindAll() throws Exception {
        mockMvc.perform(get("/client")).andExpect(status().isOk());
    }

    @Test
    @Transactional(readOnly = true)
    public void mustFindById() throws Exception {

        String clientData = mockMvc.perform(get("/client/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedClientData = IOUtils.toString(this.getClass()
                .getClassLoader()
                .getResourceAsStream("script/json/existing_client.json"));

        assertEquals(clientData, expectedClientData);

    }

    @Test
    @Transactional(readOnly = true)
    public void mustNotFindById() throws Exception {
        mockMvc.perform(get("/client/2")).andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void mustSave() throws Exception {

        mockMvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("script/json/new_client.json"))))
                .andExpect(status().isOk());

    }

}

