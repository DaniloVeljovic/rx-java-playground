package chapter5;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

public class BlockingSubscribe {
    public static void main(String[] args) {
        blockingSubscribe();
    }

    private static void blockingSubscribe() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(BlockingSubscribe::intenseCalculation)
                .blockingSubscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Done!"));
    }

    public static <T> T intenseCalculation(T value) throws InterruptedException {
        sleep(ThreadLocalRandom.current().nextInt(3000));
        return value;
    }
}
