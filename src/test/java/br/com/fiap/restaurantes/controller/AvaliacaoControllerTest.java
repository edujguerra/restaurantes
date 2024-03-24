package br.com.fiap.restaurantes.controller;

import br.com.fiap.restaurantes.dto.TipoCozinhaDTO;
import br.com.fiap.restaurantes.service.AvaliacaoService;
import br.com.fiap.restaurantes.service.TipoCozinhaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AvaliacaoControllerTest {

@Autowired
    private MockMvc mockMvc;
    
    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private AvaliacaoService avaliacaoService;

 @Test
    public void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/avaliacoes"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void save() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/avaliacoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(criaAvaliacaoDTO())))
            .andExpect(MockMvcResultMatchers.status().isCreated()); 
    }

    @Test
    public void findById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/avaliacao/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/tipocozinha/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(criaAvaliacaoDTO())))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete() throws Exception {
        doNothing().when(avaliacaoService).delete(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/avaliacao/1"))
                .andExpect(MockMvcResultMatchers.status().is(204));

                verify(avaliacaoService, times(1)).delete(1l);
    }

    public AvaliacaooDTO criaTipoCozinhaDTO(){
     return new AvaliacaoDTO(1L,"LANCHES");
    }
    
}
