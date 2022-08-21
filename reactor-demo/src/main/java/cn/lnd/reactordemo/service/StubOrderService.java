package cn.lnd.reactordemo.service;


import cn.lnd.reactordemo.model.Order;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author lnd
 * @Description
 * @Date 2022/8/21 14:55
 */
@Service
public class StubOrderService {

    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    public Flux<Order> getOrders() {
        return Flux.fromIterable(this.orders.values());
    }

    public Flux<Order> getOrdersByIds(final Flux<String> ids) {
        Flux<Order> orderFlux = ids.flatMap(id -> Mono.justOrEmpty(this.orders.get(id)));
        return orderFlux;
    }

    public Mono<Order> getOrderById(final String id) {
        return Mono.justOrEmpty(this.orders.get(id));
    }

    public Mono<Void> createOrUpdateOrder(final Mono<Order> productMono) {
        return productMono
                .doOnNext(product -> {
                    orders.put(product.getId(), product);
                })
                .thenEmpty(Mono.empty());
    }

    public Mono<Order> deleteOrder(final String id){
        return Mono.justOrEmpty(this.orders.remove(id));
    }
}
