package chapter4;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.ThreadLocalRandom;

public class MulticastingWithOperators {
    public static void main(String[] args) {
        coldObservablesMap();
        hotObservableMap();
    }

    private static void hotObservableMap() {
        ConnectableObservable<Integer> source = Observable
                .range(1, 3)
                .map(i -> ThreadLocalRandom.current().nextInt(100_000))
                .publish();

        source.subscribe(value -> System.out.println("Observer 1: " + value));
        source.subscribe(value -> System.out.println("Observer 2: " + value));

        source.connect();
    }

    private static void coldObservablesMap() {
        Observable<Integer> source = Observable.range(1, 3)
                .map(i -> ThreadLocalRandom.current().nextInt(100_000));

        source.subscribe(value -> System.out.println("Observer 1: " + value));
        source.subscribe(value -> System.out.println("Observer 2: " + value));
    }
}
