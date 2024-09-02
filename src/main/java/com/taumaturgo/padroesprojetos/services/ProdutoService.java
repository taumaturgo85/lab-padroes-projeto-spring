package com.taumaturgo.padroesprojetos.services;

import com.taumaturgo.padroesprojetos.models.Produto;
import com.taumaturgo.padroesprojetos.repositories.ProdutoRepository;
import com.taumaturgo.padroesprojetos.strategies.DescontoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private DescontoStrategy descontoStrategy;

    public void setDescontoStrategy(DescontoStrategy descontoStrategy) {
        this.descontoStrategy = descontoStrategy;
    }

    public double calcularPrecoComDesconto(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return descontoStrategy.aplicarDesconto(produto.getPreco());
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}
