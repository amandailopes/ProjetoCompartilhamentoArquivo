/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocompartilhamentodearquivo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kenreurison
 */
public class Servidor {

    private HashMap<Usuario, String> usuariosConectados = new HashMap<>();
    private HashMap<Usuario, String> usuariosCadastrados = new HashMap<>();

    Servidor() {

    }

    public boolean usuarioExiste(Usuario u) {
        return (usuariosCadastrados.containsValue(u.getLogin()));
    }

    public boolean usuarioConectado(Usuario u) {
        return usuariosConectados.containsValue(u.getLogin());
    }

    public void cadastrarUsuario(Usuario u) {
        if (!usuarioExiste(u)) {
            usuariosCadastrados.put(u, u.getLogin());
            System.out.println(usuariosCadastrados.toString());
        }
    }

    public void conectarUsuario(Usuario u) {
        if (!usuarioConectado(u) && usuarioExiste(u)) {
            usuariosConectados.put(u, u.getLogin());
        }
    }

    private void criarDiretorio() {
        File diretorio = new File("arquivos");
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
    }

    public ArrayList<File> listarArquivos() {
        File diretorio = new File("arquivos");
        ArrayList<File> asList = new ArrayList<File>(Arrays.asList(diretorio.listFiles()));
        return asList;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println(serverSocket.getLocalPort());
        while (true) {
            Socket accept = serverSocket.accept();
            new ThreadRecebedor(accept).start();
        }
    }

}
