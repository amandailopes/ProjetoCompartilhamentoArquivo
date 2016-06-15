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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Amanda
 */
class ThreadRecebedor extends Thread {

    private ObjectInputStream entrada;
    private ObjectOutputStream saida;
    private Servidor s = new Servidor();

    public ThreadRecebedor(Socket cliente) {
        try {
            entrada = new ObjectInputStream(cliente.getInputStream());
            saida = new ObjectOutputStream(cliente.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadRecebedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    /**
     * 
     * @return retorno da classe
     */
    private Object lerObjeto() {
        try {
            return entrada.readObject();
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
        while (true) {
            Object lido = lerObjeto();
            if (lido instanceof FimDeTransmissao) {
                break;
            }
            if (lido instanceof initTransmission) {
                initTransmission codigo = (initTransmission) lido;
                rotina(codigo);
            }
        }
    }
    //http://pt.stackoverflow.com/questions/25520/enviar-objetos-via-socket

    private void rotina(initTransmission codigo) {
        try {
            Usuario u = null;
            Boolean b = false;
            switch (codigo.getValor()) {
                case initTransmission.LOGIN:
                    u = (Usuario) entrada.readObject();
                    b = s.conectarUsuario(u);
                    saida.writeObject(b);
                    saida.flush();
                    saida.reset();
                    break;
                case initTransmission.LISTARARQUIVOS:
                    break;
                case initTransmission.NOVOUSUARIO:
                    u = (Usuario) entrada.readObject();
                    b = s.cadastrarUsuario(u);
                    saida.writeObject(b);
                    saida.flush();
                    saida.reset();
                    break;
                case initTransmission.LISTARUSUARIOS:
                    System.out.println(Servidor.getUsuariosCadastrados().toString());
                    System.out.println(Servidor.getUsuariosConectados().toString());
                    break; 
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadRecebedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadRecebedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Socket accept = null;
        while (true) {
            try {
                accept = serverSocket.accept();
            } catch (IOException ex) {
                Logger.getLogger(ThreadRecebedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            new ThreadRecebedor(accept).start();
        }
    }
}
