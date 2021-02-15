package com.proyectociscu.tappa_restful.repositories;

import com.proyectociscu.tappa_restful.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
    @Query(value="SELECT p.* FROM pedido AS p INNER JOIN usuario_pedido AS up ON up.id_pedido=p.id WHERE up.id_usuario=?1", nativeQuery=true)
    public List<Order> getOrderByUserId(Long id);
    
    @Query(value="SELECT SUM(c.precio) FROM pedido AS p INNER JOIN pedido_comida AS pc ON pc.id_pedido=p.id INNER JOIN comida AS c ON pc.id_comida=c.id WHERE p.id=?1", nativeQuery=true)
    public double getAllPriceForOrder(Long id);
}
