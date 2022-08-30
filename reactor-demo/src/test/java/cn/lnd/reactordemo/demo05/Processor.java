package cn.lnd.reactordemo.demo05;

/**
 * @Author lnd
 * @Description
 * @Date 2022/8/30 23:44
 */
public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {

    /*
        如果存在链式调用，即
            Web端先请求Controller，Controller继续请求Service，Service继续请求Dao，Dao请求数据库
            那么对于 Controller 而言，它既是Web端请求的Publisher，又是 Service 层的 Subscriber，那么它就是一个 Processor
            同理，这里的 Service、Dao 也是一个 Processor
    */
}
