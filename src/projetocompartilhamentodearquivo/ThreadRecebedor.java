/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocompartilhamentodearquivo;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amanda
 */
class ThreadRecebedor extends Thread {

    ObjectInputStream entrada;
    ObjectOutputStream saida;

    public ThreadRecebedor(Socket cliente) {
        try {
            entrada = new ObjectInputStream(cliente.getInputStream());
            saida = new ObjectOutputStream(cliente.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadRecebedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Usuario lerObjeto() {
        try {
            return (Usuario) entrada.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ThreadRecebedor.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void enviarLista(Servidor s) {
        try {
            ArrayList<File> listarArquivos = s.listarArquivos();
            Instrucao instrucaoSaida
                    = new Instrucao(Instrucao.tipoInstrucao.LISTARARQUIVOS,
                            "Enviando para o Cliente a Lista", listarArquivos);
            saida.writeObject(instrucaoSaida);
        } catch (IOException ex) {
            Logger.getLogger(ThreadRecebedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        Servidor s = new Servidor();
        Usuario lido =  lerObjeto();
    }
    //http://pt.stackoverflow.com/questions/25520/enviar-objetos-via-socket
}
