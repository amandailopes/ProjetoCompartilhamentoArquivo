/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

/**
 *
 * @author kenreurison
 */
public enum InicioDaTransmissao {

    LOGIN(1), NOVOUSUARIO(2), LISTARARQUIVOS(3);

    private final int valor;

    InicioDaTransmissao(int valor) {
        this.valor = 0;
    }

    public int getValor() {
        return valor;
    }
}
