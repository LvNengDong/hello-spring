package cn.lnd.reactordemo.demo01;

import java.util.function.Consumer;

/**
 * @Author lnd
 * @Description
 * @Date 2022/8/28 23:00
 */
public class SyncShoppingCardService implements ShoppingCardService{
    @Override
    public void calculate(Input value, Consumer<Output> consumer) {
        Output output = new Output();
        consumer.accept(output);
    }
}
