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
    private tipoInstrucao codigo;
    private Object dados;

    public Instrucao(tipoInstrucao codigo, String instrucao, Object dados) {
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
        Instrucao u1 = new Instrucao(tipoInstrucao.LOGIN, "Cadastrar Usuario", new Usuario("Kennedy", "123"));
        String objeto = u1.serializar();

        Instrucao u2 = new Instrucao();
        u2 = (Instrucao) u2.desSerializar(objeto);
        System.out.println(u2.toString());
    }

    public String getInstrucao() {
        return instrucao;
    }

    public void setInstrucao(String instrucao) {
        this.instrucao = instrucao;
    }

    public tipoInstrucao getCodigo() {
        return codigo;
    }

    public void setCodigo(tipoInstrucao codigo) {
        this.codigo = codigo;
    }

    public Object getDados() {
        return dados;
    }

    public void setDados(Object dados) {
        this.dados = dados;
    }

    public enum tipoInstrucao {

        LOGIN(1), NOVOUSUARIO(2), LISTARARQUIVOS(3);

        private final int valor;

        tipoInstrucao(int valor) {
            this.valor = 0;
        }

        public int getValor() {
            return valor;
        }
    }
}
