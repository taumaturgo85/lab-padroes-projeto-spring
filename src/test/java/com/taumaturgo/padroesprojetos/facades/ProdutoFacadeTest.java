package com.taumaturgo.padroesprojetos.facades;

import com.taumaturgo.padroesprojetos.models.Produto;
import com.taumaturgo.padroesprojetos.services.ProdutoService;
import com.taumaturgo.padroesprojetos.strategies.DescontoStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProdutoFacadeTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoFacade produtoFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveObterPrecoComDesconto() {
        DescontoStrategy descontoStrategy = mock(DescontoStrategy.class);
        when(produtoService.calcularPrecoComDesconto(1L)).thenReturn(90.0);

        double precoComDesconto = produtoFacade.obterPrecoComDesconto(1L, descontoStrategy);

        assertEquals(90.0, precoComDesconto);
        verify(produtoService).setDescontoStrategy(descontoStrategy);
    }

    @Test
    void deveCriarProduto() {
        Produto produto = new Produto(null, "Produto Teste", 100.0);
        Produto produtoSalvo = new Produto(1L, "Produto Teste", 100.0);
        when(produtoService.salvarProduto(produto)).thenReturn(produtoSalvo);

        Produto resultado = produtoFacade.criarProduto("Produto Teste", 100.0);

        assertEquals(produtoSalvo, resultado);
    }
}
