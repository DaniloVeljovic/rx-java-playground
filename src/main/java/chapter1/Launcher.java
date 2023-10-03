package chapter1;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.Arrays;
import java.util.List;

public class Launcher {
    public static void main(String[] args) {
        onNextCall();
        onErrorAndMapFilter();
        usingJust();
        usingFromIterables();
        createAnObserver();
        ObserverWithLambdas();
    }

    private static void ObserverWithLambdas() {
        List<String> strings = Arrays.asList("Alpha", "Beta", "Gamma", "Delta");
        Observable
                .fromIterable(strings)
                .map(String::length)
                .filter(i -> i >= 5)
                .subscribe(
                        i -> System.out.println("Received " + i),
                        Throwable::printStackTrace,
                        () -> System.out.println("Done!")
                );

    }

    private static void createAnObserver() {
        Observer<Integer> observer = new Observer<>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Received " + integer);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        };

        List<String> strings = Arrays.asList("Alpha", "Beta", "Gamma", "Delta");
        Observable
                .fromIterable(strings)
                .map(String::length)
                .filter(i -> i >= 5)
                .subscribe(observer);
    }

    private static void usingFromIterables() {
        List<String> strings = Arrays.asList("Alpha", "Beta", "Gamma", "Delta");
        Observable
                .fromIterable(strings)
                .map(String::toUpperCase)
                .filter(s -> !s.contains("a"))
                .subscribe(s -> System.out.println(s));

    }

    private static void usingJust() {
        Observable
                .just("Danilo", "Veljko", "Radisa", "Miljana")
                .map(String::toUpperCase)
                .filter(s -> s.length() >= 5)
                .subscribe(s -> System.out.println(s));
    }

    private static void onErrorAndMapFilter() {
        Observable.<String>create(observableEmitter -> {
            try {
                observableEmitter.onNext("A");
                observableEmitter.onNext("AB");
                observableEmitter.onNext("ABC");
                observableEmitter.onNext("ABCD");
                observableEmitter.onNext("ABCDE");
                observableEmitter.onComplete();
            } catch (Exception e) {
                observableEmitter.onError(e);
            }
        })
                .map(String::length)
                .filter(i -> i > 2)
                .subscribe(s -> System.out.println("RECEIVED " + s), Throwable::printStackTrace);
    }

    private static void onNextCall() {
        Observable<String> observable = Observable.create(observableEmitter -> {
            observableEmitter.onNext("A");
            observableEmitter.onNext("A");
            observableEmitter.onNext("A");
            observableEmitter.onNext("A");
            observableEmitter.onNext("A");
            observableEmitter.onComplete();
        });

        observable.subscribe(s -> System.out.println("RECEIVED " + s));
    }
}
