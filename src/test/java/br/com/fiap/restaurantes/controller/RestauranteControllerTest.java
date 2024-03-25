package br.com.fiap.restaurantes.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.fiap.restaurantes.service.RestauranteServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.restaurantes.dto.RestauranteDTO;
import br.com.fiap.restaurantes.entities.Restaurante;
import br.com.fiap.restaurantes.entities.TipoCozinha;
import br.com.fiap.restaurantes.service.RestauranteService;

@SpringBootTest
@AutoConfigureMockMvc
public class RestauranteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private RestauranteServiceImpl restauranteService;

    public String criaRestauranteDTO() throws JsonProcessingException {
        return objectMapper.writeValueAsString(new RestauranteDTO(1L,"RESTAURANTE UM","AVENIDA UM",new TipoCozinha(1L,"LANCHES"),"22:00","06:00",30,20));
    }    

    @Test
    public void findAll() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/restaurante"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void save() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/restaurante")
            .contentType(MediaType.APPLICATION_JSON)
            .content(criaRestauranteDTO()))
            .andExpect(MockMvcResultMatchers.status().isCreated()); 
    }

    @Test
    public void findById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/restaurante/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/restaurante/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(criaRestauranteDTO()))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete() throws Exception {
        doNothing().when(restauranteService).delete(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/restaurante/1"))
                .andExpect(MockMvcResultMatchers.status().is(204));

                 verify(restauranteService, times(1)).delete(1l);
    }

    public Restaurante criaRestaurante(){
        Restaurante restaurante = new Restaurante(1L,"RESTAURANTE UM","AVENIDA UM",new TipoCozinha(1L,"LANCHES"),"22:00","06:00",30,20);

        return restaurante;      
    }
}
