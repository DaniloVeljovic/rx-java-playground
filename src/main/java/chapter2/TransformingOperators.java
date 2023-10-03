package chapter2;

import io.reactivex.Observable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class TransformingOperators {
    public static void main(String[] args) throws InterruptedException {
        map();
        cast();
        startWith();
        startWithArray();
        defaultIfEmpty();
        switchIfEmpty();
        sorted();
        delay();
        repeat();
        scan();
    }

    private static void scan() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .scan((accumulator, next) -> accumulator + next)
                .subscribe(value -> System.out.println(value));
    }

    private static void repeat() {
        Observable.just("Alpha","Beta","Gamma","Delta","Epsilon")
                .repeat(2)
                .subscribe(value -> System.out.println(value));
    }

    private static void delay() throws InterruptedException {
        Observable.just("Alpha","Beta","Gamma","Delta","Epsilon")
                .delay(3, TimeUnit.SECONDS)
                .subscribe(value -> System.out.println(value));
        sleep(5000);
    }

    private static void sorted() {
        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                .sorted()
                .subscribe(value -> System.out.println(value));
    }

    private static void switchIfEmpty() {
        Observable.just("Alpha","Beta","Gamma","Delta","Epsilon")
                .filter(string -> string.length() > 10)
                .switchIfEmpty(Observable.just("Backup", "Target"))
                .subscribe(value -> System.out.println(value));
    }

    private static void defaultIfEmpty() {
        Observable.just("Alpha","Beta","Gamma","Delta","Epsilon")
                .filter(string -> string.length() > 10)
                .defaultIfEmpty("None")
                .subscribe(result -> System.out.println(result));
    }

    private static void startWithArray() {
        Observable.just("Coffee", "Tea", "Espresso")
                .startWithArray("Coffee shop menu", "-------------------")
                .subscribe(value -> System.out.println(value));
    }

    private static void startWith() {
        Observable.just("Coffee", "Tea", "Espresso")
                .startWith("Coffee shop menu")
                .subscribe(value -> System.out.println(value));
    }

    private static void cast() {
        Observable<Object> cast = Observable.just("Alpha", "Beta", "Gamma")
                .cast(Object.class);
    }

    private static void map() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        Observable.just("1/3/2016", "5/9/2016", "10/12/2016")
                .map(date -> LocalDate.parse(date,dateTimeFormatter))
                .subscribe(localDate -> System.out.println(localDate));
    }
}
