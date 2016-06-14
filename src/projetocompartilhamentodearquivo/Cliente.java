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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amanda
 */
class Cliente implements Serializable {

    private static final String IP = "127.0.0.1";
    private static final int porta = 1234;

    ObjectOutputStream oos;
    ObjectInputStream ois;

    public Cliente() {

    }

    public void enviarCadastro(Usuario u) {
        try {
            Socket socketCliente = new Socket("127.0.0.1", 1234);
            oos = new ObjectOutputStream(socketCliente.getOutputStream());
            ois = new ObjectInputStream(socketCliente.getInputStream());
            oos.writeObject(u);
            oos.flush();
            oos.reset();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {
        Cliente c = new Cliente();
        Usuario u = new Usuario("usuario", "senha");
        c.enviarCadastro(u);
    }
}
