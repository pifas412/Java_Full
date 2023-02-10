package com.pro.prod.controller;

import com.pro.prod.dao.UsuarioDao;
import com.pro.prod.models.Usuario;
import com.pro.prod.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(value = "api/login")
    public String login(@RequestBody Usuario usuario) {

        Usuario usuariLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);

        if (usuariLogueado != null) {

            String tokenJwt = jwtUtil.create(String.valueOf(usuariLogueado.getId()), usuariLogueado.getEmail());
            return tokenJwt;
        }
        return"FAIL";
    }


}
