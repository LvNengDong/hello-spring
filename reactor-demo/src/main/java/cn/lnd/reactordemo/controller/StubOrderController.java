package cn.lnd.reactordemo.controller;

import cn.lnd.reactordemo.model.Order;
import cn.lnd.reactordemo.service.StubOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author lnd
 * @Description
 * @Date 2022/8/21 15:13
 */
@RestController
@RequestMapping("/orders")
public class StubOrderController {
    @Autowired
    private StubOrderService orderService;

    @GetMapping
    public Flux<Order> getOrders(){
        return this.orderService.getOrders();
    }

    @GetMapping("/{id}")
    public Mono<Order> getOrderById(@PathVariable("id") final String id) {
        return this.orderService.getOrderById(id);
    }

    @PostMapping("")
    public Mono<Void> createOrder(@RequestBody final Mono<Order> order) {
        return this.orderService.createOrUpdateOrder(order);
    }

    @DeleteMapping("/{id}")
    public Mono<Order> delete(@PathVariable("id") final String id) {
        return this.orderService.deleteOrder(id);
    }

}
