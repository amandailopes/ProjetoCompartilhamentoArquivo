/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import projetocompartilhamentodearquivo.Usuario;

/**
 *
 * @author kenreurison
 */
public class Cliente {

    Socket socketClient = new Socket("127.0.0.1", 1234);

    public Cliente() throws IOException, ClassNotFoundException {

        ObjectOutputStream output = new ObjectOutputStream(socketClient.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socketClient.getInputStream());

        Usuario u = new Usuario("Kennedy", "kenreurison", "Senha");

        output.writeObject(u);
        output.flush();
        output.reset();

        String readUTF = input.readUTF();
        Usuario u2 = (Usuario) input.readObject();
        Boolean readObject = false;
        readObject = (Boolean) input.readObject();

        output.writeObject(new FimDeTransmissao());
        output.flush();
        output.reset();
        output.close();
        System.out.println(readUTF);
        System.out.println(u2.toString());

    }

    public static void main(String[] args) {
        try {
            Cliente c = new Cliente();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
