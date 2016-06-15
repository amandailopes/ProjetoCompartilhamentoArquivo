/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocompartilhamentodearquivo;

import java.io.Serializable;

/**
 *
 * @author kenreurison
 */
public class initTransmission implements Serializable {

    public static final int LOGIN = 1;
    public static final int NOVOUSUARIO = 2;
    public static final int LISTARARQUIVOS = 3;
    public static final int LISTARUSUARIOS = 4;

    private final int valor;

    initTransmission(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
