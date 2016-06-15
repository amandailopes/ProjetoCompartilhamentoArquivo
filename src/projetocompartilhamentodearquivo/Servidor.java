/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocompartilhamentodearquivo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author kenreurison
 */
public class Servidor {

    private static HashMap<Usuario, String> usuariosConectados;
    private static HashMap<Usuario, String> usuariosCadastrados;

    Servidor() {
        if (usuariosCadastrados == null) {
            usuariosCadastrados = new HashMap<>();

        }
        if (usuariosConectados == null) {
            usuariosConectados = new HashMap<>();
        }
    }

    public boolean usuarioExiste(Usuario u) {
        return (usuariosCadastrados.containsValue(u.getLogin()));
    }

    public boolean usuarioConectado(Usuario u) {
        return usuariosConectados.containsValue(u.getLogin());
    }

    public Boolean cadastrarUsuario(Usuario u) {
        if (!usuarioExiste(u)) {
            usuariosCadastrados.put(u, u.getLogin());
            return true;
        }
        return false;
    }

    public Boolean conectarUsuario(Usuario u) {
        if (!usuarioConectado(u) && usuarioExiste(u)) {
            usuariosConectados.put(u, u.getLogin());
            return true;
        }
        return false;
    }

    public void desconectarUsuario(Usuario u) {
        usuariosConectados.remove(u.getLogin());
    }

    private void criarDiretorio() {
        File diretorio = new File("arquivos");
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
    }

    public ArrayList<File> listarArquivos() {
        File diretorio = new File("arquivos");
        ArrayList<File> asList = new ArrayList<>(Arrays.asList(diretorio.listFiles()));
        return asList;
    }

    public static HashMap<Usuario, String> getUsuariosConectados() {
        return usuariosConectados;
    }

    public static HashMap<Usuario, String> getUsuariosCadastrados() {
        return usuariosCadastrados;
    }

}
