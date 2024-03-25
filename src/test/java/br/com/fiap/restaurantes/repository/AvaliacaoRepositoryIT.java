package br.com.fiap.restaurantes.repository;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.fiap.restaurantes.repository.AvaliacaoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

@Transactional
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("tests")
public class AvaliacaoRepositoryIT {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Test
    void devePermitirCriarTabela() {
        long totalTabelasCriada = avaliacaoRepository.count();
        assertThat(totalTabelasCriada).isNotNegative();
    }
}
