/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocompartilhamentodearquivo;
import java.io.*;
import java.util.Date;
/**
 *
 * @author Amanda
 */
class Arquivo implements Serializable {

    private String nome;
    private float tamanho;
    private Date dataDeCriacao;
    private Usuario UsuariosAdd;
    private String palavraChave;
    private String extensao;

    public Arquivo(String nome, float tamanho, Date dataDeCriacao, Usuario UsuariosAdd, String palavraChave, String extensao) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.dataDeCriacao = dataDeCriacao;
        this.UsuariosAdd = UsuariosAdd;
        this.palavraChave = palavraChave;
        this.extensao = extensao;
    }
            

    
}
