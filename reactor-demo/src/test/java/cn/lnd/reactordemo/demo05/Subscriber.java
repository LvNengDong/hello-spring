package cn.lnd.reactordemo.demo05;


/**
 * @Author lnd
 * @Description
 * @Date 2022/8/30 23:02
 */
public interface Subscriber<T> {
    /*
        （回调：发布者调用监听者）
        发布者在开始处理之前调用，并向订阅者传递一个订阅票据对象
    */
    public void onSubscribe(Subscription s);
    public void onNext(T t);    // （回调：发布者调用订阅者）
    public void onError(Throwable t); // （回调：发布者调用订阅者）
    public void onComplete();// （回调：发布者调用订阅者）
}
