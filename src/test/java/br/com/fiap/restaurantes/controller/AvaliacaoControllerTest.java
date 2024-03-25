package br.com.fiap.restaurantes.controller;

import br.com.fiap.restaurantes.dto.AvaliacaoDTO;
import br.com.fiap.restaurantes.dto.ClienteDTO;
import br.com.fiap.restaurantes.dto.RestauranteDTO;
import br.com.fiap.restaurantes.entities.TipoCozinha;
import br.com.fiap.restaurantes.service.AvaliacaoServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
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

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@NoArgsConstructor
public class AvaliacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private AvaliacaoServiceImpl avaliacaoService;

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
        mockMvc.perform(MockMvcRequestBuilders.get("/avaliacoes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/avaliacoes/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(criaAvaliacao())))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete() throws Exception {
        doNothing().when(avaliacaoService).delete(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/avaliacoes/1"))
                .andExpect(MockMvcResultMatchers.status().is(204));

            verify(avaliacaoService, times(1)).delete(1l);
    }

    public AvaliacaoDTO criaAvaliacao() throws JsonProcessingException {
        TipoCozinha tipoCozinha = new TipoCozinha(1L, "Teste");
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Teste", "email",1234L );
        RestauranteDTO restauranteDTO = new RestauranteDTO(1L, "nome teste",
                "endereço",  tipoCozinha, "01:00", "06:00",
                10,10);

        LocalDate dateFormated = LocalDate.parse("2019-12-31");

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(1L, clienteDTO,
                restauranteDTO, "Avaliação Boa", 7, dateFormated);

        return avaliacaoDTO;
    }
    
}
