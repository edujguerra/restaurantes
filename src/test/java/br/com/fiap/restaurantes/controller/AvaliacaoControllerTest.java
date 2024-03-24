package br.com.fiap.restaurantes.controller;

import br.com.fiap.restaurantes.dto.AvaliacaoRequest;
import br.com.fiap.restaurantes.dto.ClienteDTO;
import br.com.fiap.restaurantes.dto.RestauranteDTO;
import br.com.fiap.restaurantes.entities.TipoCozinha;
import br.com.fiap.restaurantes.service.AvaliacaoService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;

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
            .content(objectMapper.writeValueAsString(criaAvaliacao())))
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
            .content(objectMapper.writeValueAsString(criaAvaliacao())))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete() throws Exception {
        doNothing().when(avaliacaoService).apagarAvaliacao(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/avaliacao/1"))
                .andExpect(MockMvcResultMatchers.status().is(204));

                verify(avaliacaoService, times(1)).apagarAvaliacao(1l);
    }

    public AvaliacaoRequest criaAvaliacao(){
        TipoCozinha tipoCozinha = new TipoCozinha(1L, "Teste");
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Teste", "email",1234L );
        RestauranteDTO restauranteDTO = new RestauranteDTO(1L, "nome teste",
                "endereço",  tipoCozinha, "01:00", "06:00",
                10,10);
        var timestamp = LocalDateTime.now();

        return AvaliacaoRequest.builder()
                .cliente(clienteDTO)
                .restaurante(restauranteDTO)
                .dataAvaliacao(LocalDate.from(timestamp))
                .nota(7)
                .descricao("Avaliação Boa")
                .build();
    }
    
}
