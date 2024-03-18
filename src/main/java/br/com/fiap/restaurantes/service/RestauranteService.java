package br.com.fiap.restaurantes.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import br.com.fiap.restaurantes.dto.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.restaurantes.ControllerNotFoundException;
import br.com.fiap.restaurantes.dto.RestauranteDTO;
import br.com.fiap.restaurantes.entities.Restaurante;
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
        var restaurante =
                repo.findById(id).orElseThrow(
                        () -> new ControllerNotFoundException("Restaurante não Encontrada !!!!")                );
        return toRestauranteDTO(restaurante);
    }

    public RestauranteDTO save(RestauranteDTO restauranteDTO) {
    	Restaurante restaurante = toRestaurante(restauranteDTO);
        restaurante.setMesasDisponiveis(restauranteDTO.numMesas()); //Setando valor inicial de mesas disponiveis
    	restaurante = repo.save(restaurante);
        return toRestauranteDTO(restaurante);
    }

    public RestauranteDTO update(Long id, RestauranteDTO restauranteDTO) {
        try {
        	Restaurante buscaRestaurante = repo.getReferenceById(id);
            buscaRestaurante.setEndereco(restauranteDTO.endereco());
            buscaRestaurante.setNome(restauranteDTO.nome());
            buscaRestaurante.setNumMesas(restauranteDTO.numMesas());
            buscaRestaurante.setMesasDisponiveis(restauranteDTO.numMesas()); //Atualizando valor inicial de mesas disponiveis
            buscaRestaurante.setHoraInicio(restauranteDTO.horaInicio());
            buscaRestaurante.setHoraFinal(restauranteDTO.horaFinal());
            buscaRestaurante = repo.save(buscaRestaurante);

            return toRestauranteDTO(buscaRestaurante);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Restaurante não Encontrado !!!!!!!!");
        }
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void atualizaMesasDisponiveis(RestauranteDTO restauranteDTO, ReservaDTO reservaDTO) {
        Restaurante restaurante = repo.getReferenceById(restauranteDTO.id());
        if (isMesaDisponivelNoHorario(restaurante.getId(), reservaDTO.dataReserva(), reservaDTO.horaInicio(), restaurante.getNumMesas())){
            restaurante.setMesasDisponiveis(restaurante.getMesasDisponiveis() - (reservaDTO.numeroPessoas() / 4));
        } else {
            throw new ControllerNotFoundException("Nao ha mesas disponiveis para este restaurante na data: " +
                    reservaDTO.dataReserva() + " horario: " + reservaDTO.horaInicio());
        }
        repo.save(restaurante);
    }

    private boolean isMesaDisponivelNoHorario(Long idRestaurante, LocalDate dataReserva, String horaReserva, int totalDeMesas) {
        int mesasOcupadas = repo.mesasOcupadasNoHorario(idRestaurante, dataReserva, horaReserva);
       if (mesasOcupadas < totalDeMesas) {
            return true;
        } else {
            return false;
        }
    }

    private RestauranteDTO toRestauranteDTO(Restaurante restaurante) {
        return new RestauranteDTO(
        		restaurante.getId(),
        		restaurante.getNome(),
        		restaurante.getEndereco(),
        		restaurante.getTipoCozinha(),
                restaurante.getHoraInicio(),
                restaurante.getHoraFinal(),
        		restaurante.getNumMesas(),
                restaurante.getMesasDisponiveis()
        );
    }

    private Restaurante toRestaurante(RestauranteDTO restauranteDTO) {
        return new Restaurante(
        		restauranteDTO.id(),
        		restauranteDTO.nome(),
        		restauranteDTO.endereco(),
        		restauranteDTO.tipoCozinha(),
                restauranteDTO.horaInicio(),
                restauranteDTO.horaFinal(),
        		restauranteDTO.numMesas(),
                restauranteDTO.mesasDisponiveis()
        );
    }
}
