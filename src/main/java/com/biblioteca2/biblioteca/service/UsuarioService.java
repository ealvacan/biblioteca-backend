package com.biblioteca2.biblioteca.service;

import com.biblioteca2.biblioteca.model.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario crearUsuario(Usuario usuario);

    Usuario obtenerPorId(Long id);

    List<Usuario> listarUsuarios();

    Usuario actualizarUsuario(Long id, Usuario usuario);

    void eliminarUsuario(Long id);
}
