package cn.lnd.reactordemo.demo01;

import java.util.function.Consumer;

/**
 * @Author lnd
 * @Description 购物车服务
 *  接口
 *  只有一个 calculate 方法接收一个参数，处理完之后返回结果
 * @Date 2022/8/28 22:43
 */
public interface ShoppingCardService {
    /*
    * 计算购物车中所有商品的总价值
    * */
    void calculate(Input value, Consumer<Output> consumer);
}
