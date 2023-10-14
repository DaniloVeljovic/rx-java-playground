package chapter4;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class Multicasting {
    public static void main(String[] args) {
        coldObservable();
        hotObservableAkaMulticasting();
    }

    private static void hotObservableAkaMulticasting() {
        ConnectableObservable<Integer> source = Observable.just(1, 2, 3).publish();
        source.subscribe(value -> System.out.println("Observer 1: " + value));
        source.subscribe(value -> System.out.println("Observer 2: " + value));
        source.connect();
    }

    private static void coldObservable() {
        Observable<Integer> source = Observable.just(1, 2, 3);
        source.subscribe(value -> System.out.println("Observer 1: " + value));
        source.subscribe(value -> System.out.println("Observer 2: " + value));
    }
}
