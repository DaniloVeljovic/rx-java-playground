package chapter4;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Replay {
    public static void main(String[] args) throws InterruptedException {
        replay();
        replayAfterSeconds();
    }

    private static void replayAfterSeconds() throws InterruptedException {
        Observable<Long> source = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(interval -> (interval + 1) * 300)
                .replay(1, TimeUnit.SECONDS)
                .autoConnect();

        source.subscribe(value -> System.out.println("Observer 1:" + value));

        sleep(2000);

        source.subscribe(value -> System.out.println("Observer 2:" + value));

        sleep(1000);
    }

    private static void replay() throws InterruptedException {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS)
                .replay()
                .autoConnect();


        source.subscribe(value -> System.out.println("Observer 1: " + value));

        sleep(3000);

        source.subscribe(value -> System.out.println("Observer 2: " + value));

        sleep(3000);
    }
}
