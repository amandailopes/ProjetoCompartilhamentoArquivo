/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocompartilhamentodearquivo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amanda
 */
class ThreadRecebedor extends Thread {

    ObjectInputStream entrada;
    ObjectOutputStream saida;

    public ThreadRecebedor(Socket accept) throws IOException {
        entrada = new ObjectInputStream(accept.getInputStream());
        saida = new ObjectOutputStream(accept.getOutputStream());
    }

    private Object lerObjeto() {
        try {
            return entrada.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ThreadRecebedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void run() {
        Servidor s = new Servidor();
        while (true) {
            Instrucao lido = (Instrucao) lerObjeto();
            Instrucao.tipoInstrucao codigo = lido.getCodigo();
            Usuario u = null;
            switch (codigo) {
                case LOGIN:
                    u = (Usuario) lido.getDados();
                    s.conectarUsuario(u);
                    break;
                case NOVOUSUARIO:
                    u = (Usuario) lido.getDados();
                    s.cadastrarUsuario(u);
                    break;
            }
        }
    }
    //http://pt.stackoverflow.com/questions/25520/enviar-objetos-via-socket
}
