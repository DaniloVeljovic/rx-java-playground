package chapter1;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class ObservableSources {
    public static void main(String[] args) throws InterruptedException {
        range();
        interval();
        empty();
        //error();
        defer();
        callable();
    }

    private static void callable() {
        Observable.fromCallable(() -> new RuntimeException("Error!"))
                .subscribe(element -> System.out.println(element), error -> System.out.println(error));
    }

    static int start = 1;
    static int count = 5;
    private static void defer() {

        Observable<Integer> source = Observable.defer(() -> Observable.range(start, count));

        source.subscribe(number -> System.out.println("Observer 1 received " + number));

        count = 10;
        source.subscribe(number -> System.out.println("Observer 2 received " + number));
    }

    private static void error() {
        Observable.error(new Exception("Error!"))
                .subscribe(t -> System.out.println(t), Throwable::printStackTrace, () -> System.out.println("Done!"));
    }

    private static void empty() {
        Observable.empty().subscribe(s -> System.out.println(s));
    }

    private static void interval() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(i -> System.out.println("Received " + i));
        sleep(5000);
    }

    private static void range() {
        Observable.range(1, 10)
                .subscribe(i -> System.out.println(i));
    }





}
