/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocompartilhamentodearquivo;

import java.util.Date;

/**
 *
 * @author Amanda
 */
public class Imagem extends Arquivo{

    public Imagem(String nome, float tamanho, Date dataDeCriacao, Usuario UsuariosAdd, String palavraChave, String extensao) {
        super(nome, tamanho, dataDeCriacao, UsuariosAdd, palavraChave, extensao);
    }

}
