package chapter2;

import io.reactivex.Observable;

public class ActionOperators {
    public static void main(String[] args) {
        doOnNext();
        doOnComplete();
        doOnSubscrineAndDoOnDispose();
    }

    private static void doOnSubscrineAndDoOnDispose() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta")
                .doOnSubscribe(disposable -> System.out.println("Subscribed!"))
                .doOnDispose(() -> System.out.println("Disposed!"))
                .subscribe(value -> System.out.println(value));
    }

    private static void doOnComplete() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta")
                .doOnComplete(() -> System.out.println("Source is done emitting"))
                .map(String::length)
                .subscribe(value -> System.out.println(value));
    }

    private static void doOnNext() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta")
                .doOnNext(value -> System.out.println(value))
                .map(String::length)
                .subscribe(value -> System.out.println(value));
    }


}
