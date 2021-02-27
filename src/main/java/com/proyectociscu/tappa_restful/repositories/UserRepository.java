package com.proyectociscu.tappa_restful.repositories;

import com.proyectociscu.tappa_restful.model.Order;
import com.proyectociscu.tappa_restful.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>{
    @Query(value="SELECT * FROM usuario WHERE usuario.nombre LIKE %?1%", nativeQuery = true)
    public List<User> getUsersByName(String name);
    @Query(value="SELECT p.* FROM usuario AS u INNER JOIN usuario_pedido AS up ON u.id=up.id_usuario INNER JOIN pedido AS p ON p.id=up.id_pedido WHERE u.id=?1",
            nativeQuery = true)
    public List<Object> getOrdersByUserId(Long id);
}
