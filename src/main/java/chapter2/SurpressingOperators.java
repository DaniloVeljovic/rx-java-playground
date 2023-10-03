package chapter2;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class SurpressingOperators {
    public static void main(String[] args) throws InterruptedException {
        filter();
        take();
        takeWithInterval();
        skip();
        takeWhile();
        skipWhile();
        distinct();
        distinctUntilChanged();
        elementAt();
    }

    private static void elementAt() {
        Observable.just("Alpha", "Beta", "Zeta", "Eta", "Gamma")
                .elementAt(3)
                .subscribe(value -> System.out.println(value));
    }

    private static void distinctUntilChanged() {
        Observable.just(1, 1, 1, 2, 2, 3, 3, 2, 1, 1)
                .distinctUntilChanged()
                .subscribe(value -> System.out.println(value));
    }

    private static void distinct() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .map(String::length)
                .distinct()
                .subscribe(value -> System.out.println(value));
    }

    private static void skipWhile() {
        Observable.range(1, 100)
                .skipWhile(value -> value < 95)
                .subscribe(value -> System.out.println(value));
    }

    private static void takeWhile() {
        Observable.range(1, 100)
                .takeWhile(value -> value <= 5)
                .subscribe(value -> System.out.println(value));
    }

    private static void skip() {
        Observable.range(1, 100)
                .skip(90)
                .subscribe(value -> System.out.println(value));
    }

    private static void takeWithInterval() throws InterruptedException {
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(2, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println("Received " + s));
        sleep(5000);
    }

    private static void take() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .take(3)
                .subscribe(s -> System.out.println("Received " + s));
    }

    private static void filter() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .filter(string -> string.length() >= 5)
                .subscribe(s -> System.out.println("Received " + s));
    }


}
