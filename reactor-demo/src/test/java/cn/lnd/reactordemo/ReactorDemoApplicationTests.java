package cn.lnd.reactordemo;

import com.sun.org.apache.bcel.internal.generic.FSUB;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;
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
        //flux1.subscribe(System.out::println);
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

        Flux.generate(() -> 3, (i, sink) -> {
            sink.next(i);
            if (i == 8){
                sink.complete();
            }
            return ++i;
        }).subscribe(System.out::println);
        Flux.generate(synchronousSink -> {
            synchronousSink.next("AAA");
            synchronousSink.complete();
        }).subscribe(System.out::println);


    }

    /**
     * 如何操作响应式流？（Flux流）
     */
    @Test
    public void demo02() throws InterruptedException {

        /* 订阅（Publisher -> Subscriber）*/
        //Flux<String> flux1 = Flux.just("A", "B", "C");
        //flux1.log()
        //    .subscribe();

        //Flux<Long> flux7 = Flux.interval(Duration.ofSeconds(2), Duration.ofMillis(200));
        //flux7.subscribe(System.out::println);

        Flux.interval(Duration.ofSeconds(2), Duration.ofMillis(200)).subscribe(System.out::println);

        Thread.sleep(10);
        System.out.println("jahh");
    }

    /**
     *  通过Mono对象创建响应式流
     *      1、just
     *      2、empty
     *      3、error
     *      4、never
     *      5、justOrEmpty
     */
    @Test
    public void monoStreamDemo() {
        //Mono.just("AElement").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.empty()).subscribe(System.out::println);
    }



    /**
     *  订阅响应式流
     */
    @Test
    public void demo() {
        Mono.just("AAA")
                .concatWith(Mono.error(new IllegalStateException()))
                .onErrorReturn("default")
                .subscribe(System.out::println, System.err::println);
    }

    /**
     *
     */
    @Test
    public void demo1() {
        Flux.just("A", "B", "C")
                .subscribe(
                        data -> System.out.println("onNext：" + data),
                        err -> System.out.println("onError" + err),
                        () -> System.out.println("onComplete")
                );

    }


    /**
     *
     */
    @Test
    public void bufferDemo() {
        Flux.range(1, 25).buffer(10).subscribe(System.out::println);
        /* 执行以上代码，输出结果为：
        --------------------------------------
        [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        [11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
        [21, 22, 23, 24, 25]
        ----------------------------------- */
    }

    /**
     *
     */
    @Test
    public void windowDemo() {
        Flux<Flux<Integer>> fluxFlux = Flux.range(1, 5).window(2);
        for (Flux<Integer> integerFlux : fluxFlux.toIterable()) {
            integerFlux.subscribe(System.out::println);
            System.out.println("---------");
        }
    }

    /**
     *
     */
    @Test
    public void FilterDemo() {
        Flux.range(1,10)
                .filter(e -> e % 2 == 0)
                .subscribe(System.out::println);
    }

    /**
     *
     */
    @Test
    public void firstOrLastDemo() {
        Flux.range(1,10).last().subscribe(System.out::println);
    }

    /**
     *
     */
    @Test
    public void skipDemo() {
        Flux.range(1,10).skip(5).subscribe(System.out::println);
        System.out.println("========");
        Flux.range(1,10).skipLast(5).subscribe(System.out::println);
    }

    /**
     *
     */
    @Test
    public void takeDemo() {
        //取前10个
        Flux.range(1,100).take(10).subscribe(System.out::println);
        //取后10个
        Flux.range(1,100).takeLast(10).subscribe(System.out::println);
    }

    /**
     *
     */
    @Test
    public void thenDemo() {
        Flux.just(1,2,3)
                .then()
                .subscribe(System.out::println);
    }
}
