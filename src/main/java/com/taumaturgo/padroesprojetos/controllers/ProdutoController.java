package com.taumaturgo.padroesprojetos.controllers;

import com.taumaturgo.padroesprojetos.facades.ProdutoFacade;
import com.taumaturgo.padroesprojetos.models.Produto;
import com.taumaturgo.padroesprojetos.strategies.DescontoNatalStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoFacade produtoFacade;

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoFacade.criarProduto(produto.getNome(), produto.getPreco());
    }

    @GetMapping("/{id}")
    public Produto buscarProduto(@PathVariable Long id) {
        return produtoFacade.buscarProdutoPorId(id);
    }

    @GetMapping("/{id}/preco-com-desconto")
    public double obterPrecoComDesconto(@PathVariable Long id) {
        return produtoFacade.obterPrecoComDesconto(id, new DescontoNatalStrategy());
    }
}
