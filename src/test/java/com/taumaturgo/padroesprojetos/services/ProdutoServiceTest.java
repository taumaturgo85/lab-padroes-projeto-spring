package com.taumaturgo.padroesprojetos.services;



import com.taumaturgo.padroesprojetos.models.Produto;
import com.taumaturgo.padroesprojetos.repositories.ProdutoRepository;
import com.taumaturgo.padroesprojetos.strategies.DescontoStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private DescontoStrategy descontoStrategy;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCalcularPrecoComDesconto() {
        Produto produto = new Produto(1L, "Produto Teste", 100.0);
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(descontoStrategy.aplicarDesconto(100.0)).thenReturn(90.0);

        produtoService.setDescontoStrategy(descontoStrategy);
        double precoComDesconto = produtoService.calcularPrecoComDesconto(1L);

        assertEquals(90.0, precoComDesconto);
    }
}
