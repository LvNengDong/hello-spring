package cn.lnd.reactordemo.demo01;

import java.util.function.Consumer;

/**
 * @Author lnd
 * @Description
 * @Date 2022/8/28 23:03
 */
public class AsyncShoppingCardService implements ShoppingCardService{
    @Override
    public void calculate(Input value, Consumer<Output> consumer) {
        new Thread(() -> {
            consumer.accept(new Output());
        });
    }
}
