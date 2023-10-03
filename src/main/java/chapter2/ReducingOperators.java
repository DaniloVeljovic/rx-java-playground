package chapter2;

import io.reactivex.Observable;

public class ReducingOperators {
    public static void main(String[] args) {
        count();
        reduce();
        all();
        any();
        contains();
    }

    private static void contains() {
        Observable.range(1, 1000)
                .contains(950)
                .subscribe(value -> System.out.println(value));
    }

    private static void any() {
        Observable.just(1, 3, 4, 5)
                .any(value -> value == 5)
                .subscribe(value -> System.out.println(value));
    }

    private static void all() {
        Observable.just(1, 5, 10, 15)
                .all(value -> value < 10)
                .subscribe(value -> System.out.println(value));
    }

    private static void reduce() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8)
                .reduce(10, (total, next) -> total + next)
                .subscribe(value -> System.out.println(value));
    }

    private static void count() {
        Observable.just("Alpha", "Beta", "Gamma")
                .count()
                .subscribe(value -> System.out.println(value));
    }


}
