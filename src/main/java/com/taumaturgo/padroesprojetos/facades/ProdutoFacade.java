package com.taumaturgo.padroesprojetos.facades;

import com.taumaturgo.padroesprojetos.models.Produto;
import com.taumaturgo.padroesprojetos.services.ProdutoService;
import com.taumaturgo.padroesprojetos.strategies.DescontoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutoFacade {

    private final ProdutoService produtoService;

    public double obterPrecoComDesconto(Long produtoId, DescontoStrategy descontoStrategy) {
        produtoService.setDescontoStrategy(descontoStrategy);
        return produtoService.calcularPrecoComDesconto(produtoId);
    }

    public Produto criarProduto(String nome, double preco) {
        Produto produto = new Produto(null, nome, preco);
        return produtoService.salvarProduto(produto);
    }

    public Produto buscarProdutoPorId(Long id) {
        return produtoService.buscarProdutoPorId(id);
    }
}
