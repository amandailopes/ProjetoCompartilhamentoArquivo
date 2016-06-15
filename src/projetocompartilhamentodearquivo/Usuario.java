/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocompartilhamentodearquivo;

import java.io.*;

/**
 *
 * @author Amanda
 */
public class Usuario implements Serializable {

    private String nome;
    private String email;
    private String cpf;
    private String login;
    private String senha;

    public Usuario(String nome, String email, String cpf, String login, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.login = login;
    }

    private Usuario() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String serializar() {
        String s = "test.ser";
        try {
            FileOutputStream fo = new FileOutputStream("test.ser");
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(this); // serializo objeto cat
            oo.close();
            System.out.println(" serializado com sucesso");
        } catch (Exception e) {
        }
        return s;
    }

    public Usuario desSerializar(String s) {
        Usuario u = null;
        try {
            FileInputStream fi = new FileInputStream(s);
            ObjectInputStream oi = new ObjectInputStream(fi);
            u = (Usuario) oi.readObject();
            oi.close();
            System.out.println("agora ele foi des-serializado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", senha=" + senha + '}';
    }

    public static void main(String[] args) {
        Usuario u1 = new Usuario("Kennedy","kenreurison", "123");
        String objeto = u1.serializar();

        Usuario u2 = new Usuario();
        u2 = u2.desSerializar(objeto);
        System.out.println(u2.toString());
    }

}
