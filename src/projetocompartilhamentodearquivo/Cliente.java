/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocompartilhamentodearquivo;

import java.io.Serializable;

/**
 *
 * @author Amanda
 */
class Cliente implements Serializable{

    public Cliente() {
    }

    void enviarCadastro(Usuario u) {
        String serializar = u.serializar();
        enviarArquivo();
    }

    private void enviarArquivo() {
        Instrucao i = new Instrucao("Novo usuario", 1, this);
        i.enviar();
    }
}
