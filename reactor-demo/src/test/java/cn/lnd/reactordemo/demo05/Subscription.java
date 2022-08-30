package cn.lnd.reactordemo.demo05;

/**
 * @Author lnd
 * @Description
 * @Date 2022/8/30 23:05
 */
public interface Subscription {
    /*
        订阅者向发布者订阅需要得到的元素的数量。
        如果订阅者请求的个数达到了 long.MAXVALUE，发布者就会认为订阅者请求的元素个数是无边界的
    */
    public void request(long n);
    public void cancel();
}
