package br.com.fiap.restaurantes.service;

import java.util.Collection;
import java.util.stream.Collectors;

import br.com.fiap.restaurantes.dto.ReservaDTO;
import br.com.fiap.restaurantes.exception.AvaliacaoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.restaurantes.dto.RestauranteDTO;
import br.com.fiap.restaurantes.entities.Restaurante;
import br.com.fiap.restaurantes.repository.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RestauranteServiceImpl implements RestauranteService{
    @Autowired
    private RestauranteRepository repo;

    public RestauranteServiceImpl(RestauranteRepository restauranteRepository) {
        this.repo = restauranteRepository;
    }

    @Override
    public Collection<RestauranteDTO> findAll() {
        var restaurantes = repo.findAll();
        return restaurantes
                .stream()
                .map(this::toRestauranteDTO)        
                .collect(Collectors.toList()); 
    }

    @Override
    public RestauranteDTO findById(Long id) {
        var restaurante =
                repo.findById(id).orElseThrow(
                        () -> new AvaliacaoNotFoundException.ControllerNotFoundException("Restaurante não Encontrada !!!!")                );
        return toRestauranteDTO(restaurante);
    }

    @Override
    public RestauranteDTO save(RestauranteDTO restauranteDTO) {
    	Restaurante restaurante = toRestaurante(restauranteDTO);
        restaurante.setMesasDisponiveis(restauranteDTO.numMesas()); //Setando valor inicial de mesas disponiveis
    	restaurante = repo.save(restaurante);
        return toRestauranteDTO(restaurante);
    }

    @Override
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
            throw new AvaliacaoNotFoundException.ControllerNotFoundException("Restaurante não Encontrado !!!!!!!!");
        }
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void atualizaMesasDisponiveis(RestauranteDTO restauranteDTO, ReservaDTO reservaDTO) {Restaurante restaurante = repo.getReferenceById(restauranteDTO.id());
        if (isMesaDisponivelNoHorario(restaurante.getId(), reservaDTO.dataReserva(), reservaDTO.horaInicio(), restaurante.getNumMesas())){
            restaurante.setMesasDisponiveis((int) ((double) restaurante.getMesasDisponiveis() - ((double) reservaDTO.numeroPessoas() / 4)));
        } else {
            throw new AvaliacaoNotFoundException.ControllerNotFoundException("Nao ha mesas disponiveis para este restaurante na data: " +
                    reservaDTO.dataReserva() + " horario: " + reservaDTO.horaInicio());
        }
        repo.save(restaurante);
    }

    @Override
    public boolean isMesaDisponivelNoHorario(Long idRestaurante, String dataReserva, String horaReserva, int totalDeMesas) {
        int mesasOcupadas = repo.mesasOcupadasNoHorario(idRestaurante, dataReserva, horaReserva);
       return (mesasOcupadas < totalDeMesas);
    }

    @Override
    public RestauranteDTO toRestauranteDTO(Restaurante restaurante) {
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

    @Override
    public Restaurante toRestaurante(RestauranteDTO restauranteDTO) {
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
