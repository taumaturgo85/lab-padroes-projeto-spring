package com.taumaturgo.padroesprojetos.strategies;

import org.springframework.stereotype.Component;

@Component
public class DescontoNatalStrategy implements DescontoStrategy {

    @Override
    public double aplicarDesconto(double valor) {
        return valor * 0.9; // 10% de desconto
    }
}
