package cn.lnd.reactordemo.demo02;

import cn.lnd.reactordemo.demo03.Item;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

/**
 * @Author lnd
 * @Description
 * @Date 2022/8/29 19:52
 */
// async 数据库客户端的接口声明，它是支持异步数据库访问的客户端接口的代表性示例
public interface AsyncDatabaseClient {
    <T> CompletionStage<T> store(CompletionStage<T> stage);

    CompletionStage<Item> getNextAfterId(String elementId);

    CompletionStage<List<Item>> getNextBatchAfterId(String elementId, int count);

    Stream getStreamOfItems();
}

