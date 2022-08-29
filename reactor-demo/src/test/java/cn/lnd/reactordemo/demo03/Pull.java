package cn.lnd.reactordemo.demo03;

import cn.lnd.reactordemo.demo02.AsyncDatabaseClient;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @Author lnd
 * @Description
 * @Date 2022/8/29 22:18
 */
public class Pull {

    // AsyncDatabaseClient 字段声明。在这里，我们使用该客户端将异步、非阻塞通信与外部数据库连接起来
    final AsyncDatabaseClient dbClient = null;


    /*
       这是 list 方法声明。这里我们通过返回 CompletionStage 声明一个异步契约，并将其作为调用
        list 方法的结果。
        同时，为了聚合拉取结果并将其异步发送给调用者，我们声明Queue 和 CompletableFuture 来存储
        接收的值，并在稍后手动发送所收集的 Queue。
    */
    public CompletionStage<Queue<Item>> list(int count) {
        BlockingQueue<Item> storage = new ArrayBlockingQueue<>(count);
        CompletableFuture<Queue<Item>> result = new CompletableFuture<>();
        pull("1", storage, result, count);
        return result;
    }

    /*
        这是 pull 方法声明。
        在该方法中，我们调用 AsyncDatabaseClient#getNextAfterId来执行查询并异步接收结果。
        然后，当收到结果时，我们在点(3.1)处对其进行过滤。如果是有效项，我们就将其
        聚合到队列中。
        另外，在点(3.2)，我们检查是否已经收集了足够的元素，将它们发送给调用者，然后退
        出拉操作。
        如果任何一个所涉及的 if 分支被绕过，就再次递归调用 pull方法(3.3)。
    */
    //private void pull(String elementId, Queue<Item> queue, CompletableFuture resultFuture, int count) {
    //    dbClient.getNextAfterId(elementId)
    //            .thenAccept(item -> { //thenAccept会接收『上一个任务的结果』作为参数，使用一个新的线程去执行
    //                if (isValid(item)) {
    //                    queue.offer(item);
    //                    if (queue.size() == count) {
    //                        resultFuture.complete(queue); //将处理结果加入queue
    //                        return;
    //                    }
    //                }
    //                pull(item.getId(), queue, resultFuture, count);
    //            });
    //}

    /*
        这是 getNextBatchAfterId 执行过程。可以注意到，AsyncDatabaseClient 方法可用于查
        询特定数量的元素，这些元素作为 List <Item>返回。反过来，当数据可用时，除了要创建额外的
        for 循环以分别处理该批的每个元素，我们对它们的处理方式几乎相同。
    */
    private void pull(String elementId, Queue<Item> queue, CompletableFuture resultFuture, int count) {
        dbClient.getNextBatchAfterId(elementId, count)
                .thenAccept(items -> { //thenAccept会接收『上一个任务的结果』作为参数，使用一个新的线程去执行
                    for (Item item : items) {
                        if (isValid(item)) {
                            queue.offer(item);
                            if (queue.size() == count) {
                                resultFuture.complete(queue); //将处理结果加入queue
                                return;
                            }
                        }
                    }
                    // 这是递归 pull 方法的执行过程，在缺少来自上次拉取的数据项的情况下，这个设计会被用于获取另外一批数据项。
                    pull(items.get(items.size() - 1).getId(), queue, resultFuture, count);
                });
    }



    private boolean isValid(Item item) {
        // TODO
        return false;
    }
}
