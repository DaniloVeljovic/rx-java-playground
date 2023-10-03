package chapter1;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.PublishSubject;

public class ColdVsHotObservables {
    public static void main(String[] args) {
        coldObservablesWith2Observers();
        hotObservable();
        connectableObservable();
    }

    private static void connectableObservable() {
        ConnectableObservable<String> observable = Observable
                .just("Alpha", "Beta", "Gamma", "Delta")
                .publish();

        observable.subscribe(s -> System.out.println("Observer 1 received " + s));

        observable
                .map(String::length)
                .subscribe(i -> System.out.println("Observer 2 received " + i));

        observable.connect();
    }

    private static void hotObservable() {
        PublishSubject<Integer> hotObservable = PublishSubject.create();

        hotObservable.onNext(1);
        hotObservable.onNext(2);
        hotObservable.onNext(3);

        hotObservable.subscribe(item -> System.out.println("Observer 1 received " + item));

        hotObservable.onNext(4);
        hotObservable.onNext(5);

        hotObservable.subscribe(item -> System.out.println("Observer 2 received " + item));

        hotObservable.onNext(6);
        hotObservable.onNext(7);

        hotObservable.subscribe(item -> System.out.println("Observer 3 received " + item));

        hotObservable.onNext(8);
        hotObservable.onNext(9);
    }

    private static void coldObservablesWith2Observers() {
        Observable<String> observable = Observable.just("Alpha", "Beta", "Gamma", "Delta");

        observable.subscribe(s -> System.out.println("Observer 1 received " + s));
        observable.subscribe(s -> System.out.println("Observer 2 received " + s));
    }
}
