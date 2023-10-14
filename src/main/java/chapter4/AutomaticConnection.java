package chapter4;

import io.reactivex.Observable;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class AutomaticConnection {
    public static void main(String[] args) throws InterruptedException {
        autoConnect();
        refCount();
    }

    private static void refCount() throws InterruptedException {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS)
                .publish()
                .refCount();

        source.take(5)
                .subscribe(value -> System.out.println("Observer 1: " + value));

        sleep(3000);

        source.take(2)
                .subscribe(value -> System.out.println("Observer 1: " + value));

        sleep(3000);

        source
                .subscribe(value -> System.out.println("Observer 1: " + value));

        sleep(3000);
    }

    private static void autoConnect() {
        Observable<Integer> source = Observable.range(1, 3)
                .map(map -> ThreadLocalRandom.current().nextInt(100_000))
                .publish()
                .autoConnect(2);

        source.subscribe(value -> System.out.println("Observer 1: " + value));
        source.subscribe(value -> System.out.println("Observer 2: " + value));


    }
}
