/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocompartilhamentodearquivo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Amanda
 */
public class Instrucao implements Serializable {

    private String instrucao;
    private Integer codigo;
    private Object dados;

    public Instrucao(String instrucao, Integer codigo, Object dados) {
        this.instrucao = instrucao;
        this.codigo = codigo;
        this.dados = dados;
    }

    private Instrucao() {

    }

    private String serializar() {
        String s = "test.ser";
        try {
            FileOutputStream fo = new FileOutputStream(s);
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(this); // serializo objeto cat
            oo.close();
            System.out.println(" serializado com sucesso");
        } catch (Exception e) {
        }
        return s;
    }

    private Object desSerializar(String s) {
        Object o = null;
        try {
            FileInputStream fi = new FileInputStream(s);
            ObjectInputStream oi = new ObjectInputStream(fi);
            o = oi.readObject();
            oi.close();
            System.out.println("agora ele foi des-serializado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public String toString() {
        return "Instrucao{" + "instrucao=" + instrucao + ", codigo=" + codigo + ", dados=" + dados + '}';
    }

    public static void main(String[] args) {
        Instrucao u1 = new Instrucao("Cadastrar Usuario", 0, new Usuario("Kennedy", "123"));
        String objeto = u1.serializar();

        Instrucao u2 = new Instrucao();
        u2 = (Instrucao) u2.desSerializar(objeto);
        System.out.println(u2.toString());
    }

    

    
    
    void enviar() {
        String serializar = this.serializar();

    }
}
