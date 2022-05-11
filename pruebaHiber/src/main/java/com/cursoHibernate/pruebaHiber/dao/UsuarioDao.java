package com.cursoHibernate.pruebaHiber.dao;

import com.cursoHibernate.pruebaHiber.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuario();

    void eliminar(long id);

    void registrar(Usuario u);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);

}
