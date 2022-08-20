package cn.lnd.reactordemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.stream.Stream;

@SpringBootTest
class ReactorDemoApplicationTests {

    /**
     * 如何创建响应式流（Flux流）
     * Flux 流可以包含零个、一个或多个元素
     */
    @Test
    public void demo01() {
        Flux<String> flux = Flux.just("AElement");
        Flux<String> flux1 = Flux.just("A", "B", "C");
        Flux<String> flux2 = Flux.fromIterable(Arrays.asList("A", "B", "C"));
        Flux<String> flux3 = Flux.fromArray(new String[]{"A", "B", "C"});
        /*
            对于由Stream生成的flux而言，它只能被使用一次。
            对于 JDK8 的 Stream 而言，它只会被打开一次，如果多次订阅这个操作的话，会有异常产生
        */
        Flux<String> flux4 = Flux.fromStream(Stream.of("A", "B", "C"));
        flux4.subscribe();
        //flux4.subscribe(); //can only subscribe once！otherwise will throw error
        // IllegalStateException: stream has already been operated upon or closed

        /*
            返回一个空的序列
        */
        Flux<String> flux5 = Flux.empty();

        /*
            返回一个范围序列。
            arg1：start
            arg2：offset
        */
        Flux<Integer> flux6 = Flux.range(5, 3);

    }

    /**
     * 如何操作响应式流？（Flux流）
     */
    @Test
    public void demo02() {

        /* 订阅（Publisher -> Subscriber）*/
        Flux<String> flux1 = Flux.just("A", "B", "C");
        flux1.log()
            .subscribe();
    }

}
