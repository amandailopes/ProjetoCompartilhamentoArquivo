/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocompartilhamentodearquivo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amanda
 */
class Cliente implements Serializable {

    private Socket socketCliente;

    ObjectOutputStream saida;
    ObjectInputStream entrada;

    public Cliente() {
        try {
            this.socketCliente = new Socket("127.0.0.1", 1234);
            saida = new ObjectOutputStream(socketCliente.getOutputStream());
            entrada = new ObjectInputStream(socketCliente.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Boolean enviarCadastro(Usuario u) throws IOException, ClassNotFoundException {
        initTransmission ini = new initTransmission(initTransmission.NOVOUSUARIO);
        saida.writeObject(ini);
        saida.flush();
        saida.reset();
        saida.writeObject(u);
        saida.flush();
        saida.reset();
        Boolean readObject = (Boolean) entrada.readObject();
        saida.writeObject(new FimDeTransmissao());
        saida.flush();
        saida.reset();
//        saida.close();
        return readObject;
    }

    private Boolean realizarLogin(Usuario u) throws IOException, ClassNotFoundException {
        initTransmission ini = new initTransmission(initTransmission.LOGIN);
        saida.writeObject(ini);
        saida.flush();
        saida.reset();
        saida.writeObject(u);
        saida.flush();
        saida.reset();
        Boolean confirmacao = (Boolean) entrada.readObject();
        saida.writeObject(new FimDeTransmissao());
        saida.flush();
        saida.reset();
//        saida.close();
        return confirmacao;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cliente c = new Cliente();
        Usuario u = new Usuario("usuario", "senha");
        c.enviarCadastro(u);
        Cliente c2 = new Cliente();
        c2.realizarLogin(u);
    }

}
