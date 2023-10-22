package chapter5;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

public class IntroMultithreading {
    public static void main(String[] args) throws InterruptedException {
        //sequentialProcessing();
        //multithreadingProcessing();
        zipOnDifferentThreads();
    }

    private static void zipOnDifferentThreads() throws InterruptedException {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(IntroMultithreading::intenseCalculation);

        Observable<Integer> source2 = Observable.range(1, 6)
                .subscribeOn(Schedulers.computation())
                .map(IntroMultithreading::intenseCalculation);

        Observable.zip(source1, source2, (s, i) -> s + "-" + i)
                        .subscribe(System.out::println);

        sleep(20_000);
    }

    private static void multithreadingProcessing() throws InterruptedException {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(IntroMultithreading::intenseCalculation)
                .subscribe(System.out::println);

        Observable.range(1, 6)
                .subscribeOn(Schedulers.computation())
                .map(IntroMultithreading::intenseCalculation)
                .subscribe(System.out::println);

        sleep(20_000);
    }

    private static void sequentialProcessing() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .map(IntroMultithreading::intenseCalculation)
                .subscribe(System.out::println);

        Observable.range(1, 6)
                .map(IntroMultithreading::intenseCalculation)
                .subscribe(System.out::println);
    }

    public static <T> T intenseCalculation(T value) throws InterruptedException {
        sleep(ThreadLocalRandom.current().nextInt(3000));
        return value;
    }
}
