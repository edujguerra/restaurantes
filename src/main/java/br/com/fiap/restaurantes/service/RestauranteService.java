package br.com.fiap.restaurantes.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.restaurantes.ControllerNotFoundException;
import br.com.fiap.restaurantes.dto.RestauranteDTO;
import br.com.fiap.restaurantes.entities.Endereco;
import br.com.fiap.restaurantes.entities.Restaurante;
import br.com.fiap.restaurantes.entities.TipoCozinha;
import br.com.fiap.restaurantes.repository.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository repo;

    public Collection<RestauranteDTO> findAll() {
        var restaurantes = repo.findAll();
        return restaurantes
                .stream()
                .map(this::toRestauranteDTO)        
                .collect(Collectors.toList()); 
    }

    public RestauranteDTO findById(Long id) {
        var carro =
                repo.findById(id).orElseThrow(
                        () -> new ControllerNotFoundException("Restaurante não Encontrada !!!!")                );
        return toRestauranteDTO(carro);
    }

    public RestauranteDTO save(RestauranteDTO restauranteDTO) {
    	Restaurante restaurante = toRestaurante(restauranteDTO);
    	restaurante = repo.save(restaurante);
        return toRestauranteDTO(restaurante);
    }

    public RestauranteDTO update(Long id, RestauranteDTO restauranteDTO) {
        try {
        	Restaurante buscaRestaurante = repo.getReferenceById(id);
            buscaRestaurante.setEndereço(restauranteDTO.endereço());
            buscaRestaurante.setHoraFinal(restauranteDTO.horaFinal());
            buscaRestaurante.setHoraInicio(restauranteDTO.horaInicio());
            buscaRestaurante.setNome(restauranteDTO.nome());
            buscaRestaurante.setTipoCozinha(restauranteDTO.tipoCozinha());
            buscaRestaurante = repo.save(buscaRestaurante);

            return toRestauranteDTO(buscaRestaurante);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Restaurante não Encontrado !!!!!!!!");
        }
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private RestauranteDTO toRestauranteDTO(Restaurante restaurante) {
        return new RestauranteDTO(
        		restaurante.getId(),
        		restaurante.getNome(),
        		restaurante.getEndereço(),
        		restaurante.getTipoCozinha(),
        		restaurante.getHoraInicio(),
        		restaurante.getHoraFinal()

        );
    }

    private Restaurante toRestaurante(RestauranteDTO restauranteDTO) {
        return new Restaurante(
        		restauranteDTO.id(),
        		restauranteDTO.nome(),
        		restauranteDTO.endereço(),
        		restauranteDTO.tipoCozinha(),
        		restauranteDTO.horaInicio(),
        		restauranteDTO.horaFinal()        		
        );
    }
}
