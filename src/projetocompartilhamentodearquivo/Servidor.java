/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocompartilhamentodearquivo;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

/**
 *
 * @author kenreurison
 */
public class Servidor {

    private HashMap<Usuario, String> usuariosConectados = new HashMap<>();
    private HashMap<Usuario, String> usuariosCadastrados = new HashMap<>();

    Servidor(){
        
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
        }
    }

    public void conectarUsuario(Usuario u) {
        if (!usuarioConectado(u) && usuarioExiste(u)) {
            usuariosConectados.put(u, u.getLogin());
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        while (true) {
            new ThreadRecebedor(serverSocket.accept()).start();
        }
    }
}
