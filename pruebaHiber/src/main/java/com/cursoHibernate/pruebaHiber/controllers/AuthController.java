package com.cursoHibernate.pruebaHiber.controllers;

import com.cursoHibernate.pruebaHiber.dao.UsuarioDao;
import com.cursoHibernate.pruebaHiber.models.Usuario;
import com.cursoHibernate.pruebaHiber.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;


    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){

        Usuario u = usuarioDao.obtenerUsuarioPorCredenciales(usuario);

        if(u != null){
            String tokenJwt = jwtUtil.create(String.valueOf(usuario.getId()), u.getEmail());
            return tokenJwt;
        }

        return "fail";
    }
}
