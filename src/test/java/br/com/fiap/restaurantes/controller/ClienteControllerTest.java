package br.com.fiap.restaurantes.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.restaurantes.dto.ClienteDTO;
import br.com.fiap.restaurantes.entities.Cliente;
import br.com.fiap.restaurantes.service.ClienteService;
import lombok.NoArgsConstructor;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@NoArgsConstructor
public class ClienteControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ClienteService clienteService;

    public String criaClienteDTO() throws JsonProcessingException {
        return objectMapper.writeValueAsString(new ClienteDTO(1L,"CLIENTE UM","EMAIL UM",213213l));
    }    

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void save() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(criaClienteDTO()))
            .andExpect(MockMvcResultMatchers.status().isCreated()); 
    }

    @Test
    public void findById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/clientes/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(criaClienteDTO()))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete() throws Exception {
        doNothing().when(clienteService).delete(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/1"))
                .andExpect(MockMvcResultMatchers.status().is(204));

                 verify(clienteService, times(1)).delete(1l);
    }

public Cliente criaCliente(){
        return new Cliente(1L,"CLIENTE UM","EMAIL UM",213213l);
    }
}
    
