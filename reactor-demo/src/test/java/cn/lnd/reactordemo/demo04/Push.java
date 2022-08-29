package cn.lnd.reactordemo.demo04;

import cn.lnd.reactordemo.demo02.AsyncDatabaseClient;
import cn.lnd.reactordemo.demo03.Item;


/**
 * @Author lnd
 * @Description
 * @Date 2022/8/29 23:48
 */
public class Push {
    // 只会请求一次数据，当数据变为可用时，该数据源异步推送数据

    final AsyncDatabaseClient dbClient = null;
    
    // 这是 list 方法声明。这里的 Observable<Item> 会返回一个类型，该类型标识正在被推送的元素。
    public Observable<Item> list(int count){

        /*
            这是流查询阶段。通过调用 AsyncDatabaseClient#getStreamOfItems 方法，我们会对数据
            库完成一次订阅。在点(2.1)，我们会过滤元素，并且通过使用.take()操作符根据调用者的请求获取特
            定数量的数据(2.2)。
        */
        return dbClient.getStreamOfItems()
                .filter(item -> isValid(item))
                .take(count);
    }

    private boolean isValid(Object item) {
        // TODO
        return false;
    }
}
