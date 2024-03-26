package br.com.fiap.restaurantes.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.restaurantes.dto.TipoCozinhaDTO;
import br.com.fiap.restaurantes.service.TipoCozinhaService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
public class TipoRestauranteControllerTest {
    
@Autowired
    private MockMvc mockMvc;
    
    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private TipoCozinhaService tipoCozinhaService;

     @Test
    public void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tipocozinha"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void save() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/tipocozinha")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(criaTipoCozinhaDTO())))
            .andExpect(MockMvcResultMatchers.status().isCreated()); 
    }

    @Test
    public void findById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tipocozinha/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/tipocozinha/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(criaTipoCozinhaDTO())))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete() throws Exception {
        doNothing().when(tipoCozinhaService).delete(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/tipocozinha/1"))
                .andExpect(MockMvcResultMatchers.status().is(204));

                verify(tipoCozinhaService, times(1)).delete(1L);
    }

    public TipoCozinhaDTO criaTipoCozinhaDTO(){
        return new TipoCozinhaDTO(1L,"LANCHES");
    }
    
}
