package cn.lnd.reactordemo.demo01;

/**
 * @Author lnd
 * @Description 订单服务
 *  什么是回调：https://baijiahao.baidu.com/s?id=1715456849939222336&wfr=spider&for=pc
 * @Date 2022/8/28 22:43
 */
public class OrderService {

    ShoppingCardService shoppingCardService;

    void process(){
        Input input = new Input();
        // 真正执行consumer中的方法的是shoppingCardService
        shoppingCardService.calculate(input, (output) -> {
            // 需要执行的回调逻辑
            System.out.println(output + "===");
        });
    }
}
