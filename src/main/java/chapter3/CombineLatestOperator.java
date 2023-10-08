package chapter3;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class CombineLatestOperator {
    public static void main(String[] args) throws InterruptedException {
        combineLatest();
        withLatestFrom();
    }

    private static void withLatestFrom() throws InterruptedException {
        Observable<Long> source1 =
                Observable.interval(300, TimeUnit.MILLISECONDS);
        Observable<Long> source2 =
                Observable.interval(1, TimeUnit.SECONDS);
        source2.withLatestFrom(source1,
                (l1,l2) -> "SOURCE 2: " + l1 + " SOURCE 1: " + l2
        ) .subscribe(System.out::println);

        sleep(3000);
    }

    private static void combineLatest() throws InterruptedException {
        Observable<Long> source1 = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> source2 = Observable.interval(300, TimeUnit.MILLISECONDS);
        Observable.combineLatest(source1,source2, (l1, l2) -> "SOURCE 1: " + l1 + " SOURCE 2: " + l2)
                .subscribe(element -> System.out.println(element));

        sleep(3000);
    }
}
