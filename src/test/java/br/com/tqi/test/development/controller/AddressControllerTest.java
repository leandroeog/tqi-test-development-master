package br.com.tqi.test.development.controller;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = "classpath:/script/V0__test_script_inicial.sql")
public class AddressControllerTest extends AbstractBaseControllerTest {

    @Test
    @Transactional
    public void mustUpdateByClient() throws Exception {

        mockMvc.perform(put("/address/1/client/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("script/json/update_client_address.json"))))
                .andExpect(status().isOk());

    }

    @Test
    @Transactional
    public void mustNotUpdateByOtherClient() throws Exception {

        mockMvc.perform(put("/address/1/client/2")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("script/json/update_client_address.json"))))
                .andExpect(status().isBadRequest());

    }

    @Test
    @Transactional
    public void mustNotFindAddress() throws Exception {

        mockMvc.perform(put("/address/3/client/2")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("script/json/update_client_address.json"))))
                .andExpect(status().isBadRequest());

    }

}
