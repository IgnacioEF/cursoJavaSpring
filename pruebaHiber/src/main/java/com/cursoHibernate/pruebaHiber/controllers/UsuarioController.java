package com.cursoHibernate.pruebaHiber.controllers;

import com.cursoHibernate.pruebaHiber.dao.UsuarioDao;
import com.cursoHibernate.pruebaHiber.models.Usuario;
import com.cursoHibernate.pruebaHiber.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios/{id}")
    public Usuario getUsuario(@PathVariable long id){
        Usuario usuario = new Usuario("Liliana", "Rivas","lili97@hotmail.com","654338971");
        usuario.setId(id);
        return usuario;
    }

    /*@RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuario(){
        return usuarioDao.getUsuario();
    }*/

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuario(@RequestHeader(value="Authorization") String token){

        if(!validarToken(token)){
            return null;
        }

        return usuarioDao.getUsuario();
    }

    private boolean validarToken(String token){
        String usuarioID = jwtUtil.getKey(token);

        return usuarioID != null;
    }


    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario u){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, u.getPassword());
        u.setPassword(hash);

        usuarioDao.registrar(u);
    }

    @RequestMapping(value = "api/Usuario1")
    public Usuario editar(){
        Usuario u = new Usuario();
        return u;
    }



    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable long id, @RequestHeader(value="Authorization") String token){
        if(!validarToken(token)){
            return;
        }
        usuarioDao.eliminar(id);
    }
}
