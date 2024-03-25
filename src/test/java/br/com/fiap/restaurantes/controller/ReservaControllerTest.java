package br.com.fiap.restaurantes.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;

import br.com.fiap.restaurantes.service.ReservaServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.restaurantes.dto.ReservaDTO;
import br.com.fiap.restaurantes.service.ReservaService;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
public class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    
    ClienteControllerTest clienteControllerTest = new ClienteControllerTest();

    RestauranteControllerTest restauranteControllerTest = new RestauranteControllerTest();

    @MockBean
    ReservaServiceImpl reservaService;

    public ReservaDTO criaReservaDTO() throws JsonProcessingException {
        return new ReservaDTO(1L,clienteControllerTest.criaCliente(),restauranteControllerTest.criaRestaurante(),new Date().toString(),4,"11","12");
    }    

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reservas"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void save() throws Exception {
        ReservaDTO reservaDTO = criaReservaDTO();
        Mockito.when(reservaService.save(reservaDTO)).thenReturn((reservaDTO));
        mockMvc.perform(MockMvcRequestBuilders.post("/reservas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(reservaDTO)))
            .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(reservaService, Mockito.times(1)).save(reservaDTO);
      
    }

    @Test
    public void findById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reservas/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/reservas/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(criaReservaDTO())))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete() throws Exception {
         doNothing().when(reservaService).delete(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/reservas/1"))
                .andExpect(MockMvcResultMatchers.status().is(204));

                 verify(reservaService, times(1)).delete(1l);
    }

}
