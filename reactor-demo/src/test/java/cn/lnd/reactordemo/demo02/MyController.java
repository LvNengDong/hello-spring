package cn.lnd.reactordemo.demo02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.CompletionStage;

/**
 * @Author lnd
 * @Description
 * @Date 2022/8/29 20:10
 */
@RequestMapping
public class MyController {

    private AsyncAdapters asyncAdapters = new AsyncAdapters();

    @RequestMapping
    public ListenableFuture<?> requestData(){
        AsyncRestTemplate httpClient = new AsyncRestTemplate(); //异步http客户端
        AsyncDatabaseClient databaseClient = null; //异步数据库客户端

        httpClient.execute(null, null, null, null)
        CompletionStage<String> completionStage = asyncAdapters.toCompletion(
                httpClient.execute(null, null, null, null) // execute的执行结果是一个 ListenableFuture
        );

        return asyncAdapters.toListenable(
                databaseClient.store(completionStage) // store的执行结果是一个 CompletionStage
        );
    }
}
