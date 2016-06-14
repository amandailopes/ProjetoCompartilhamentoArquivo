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
class ThreadRecebedor extends Thread {

    private ObjectOutputStream output;
    private ObjectInputStream input;

    public ThreadRecebedor(Socket accept) {
        try {
            input = new ObjectInputStream(accept.getInputStream());
            output = new ObjectOutputStream(accept.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadRecebedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object lido = input.readObject();
                if (lido instanceof FimDeTransmissao) {
                    break;
                }
                if (lido instanceof Usuario) {
                    Usuario u = (Usuario) lido;
                    output.writeUTF("Você me mandou:");
                    output.flush();
                    output.reset();
                    u.setNome(u.getNome() + " e Larissa");
                    output.writeObject(u);
                    output.flush();
                    output.reset();
                }
            } catch (IOException ex) {
                Logger.getLogger(ThreadRecebedor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadRecebedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
