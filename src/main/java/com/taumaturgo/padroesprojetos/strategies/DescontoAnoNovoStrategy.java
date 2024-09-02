package com.taumaturgo.padroesprojetos.strategies;

import org.springframework.stereotype.Component;

@Component
public class DescontoAnoNovoStrategy implements DescontoStrategy {

    @Override
    public double aplicarDesconto(double valor) {
        return valor * 0.85; // 15% de desconto
    }
}
