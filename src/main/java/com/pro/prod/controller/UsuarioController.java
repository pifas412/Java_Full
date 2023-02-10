package com.pro.prod.controller;

import com.pro.prod.dao.UsuarioDao;
import com.pro.prod.models.Usuario;
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

    @RequestMapping(value = "api/usuarios/{id}")
    public Usuario getusuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Mario");
        usuario.setApellido("Mendoza");
        usuario.setEmail("@jfkaj.com");
        usuario.setPassword("ifdjalkhgd");
        usuario.setTelefono("4204983290");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios(){
        return usuarioDao.getUsuarios();

    }

    @DeleteMapping(value = "api/usuarios/{id}")
    public void eliminar (@PathVariable Long id){
        usuarioDao.eliminar(id);
    }


    @PostMapping(value = "api/usuarios")
    public void registrarUsuarios(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);

    }
}
