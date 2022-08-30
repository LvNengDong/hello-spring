package cn.lnd.reactordemo.demo05;


import org.reactivestreams.Subscriber;

/**
 * @Author lnd
 * @Description 表示数据流的生产者或数据源，包含一个方法让订阅者注册到发布者，
 * Publisher 代表了发布者和订阅者直接连接的标准化入口点。
 * @Date 2022/8/30 19:57
 */
public interface Publisher<T> {
    /*
    注意：这里是一种监听器模式的实现，所以这里的执行逻辑是产生数据的发布者向一个订阅者“推送”数据。
    同时，发布者内部会持有订阅者实例，当订阅者订阅的事件有了结果时，Subscriber 实例会执行一系列回调方法来处理事件的结果。
    */
    public void subscribe(Subscriber<? extends T> s);
}
