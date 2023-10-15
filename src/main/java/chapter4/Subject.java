package chapter4;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Subject {
    public static void main(String[] args) throws InterruptedException {
        createSubject();
        exampleUseCase();
    }

    private static void exampleUseCase() throws InterruptedException {
        Observable<String> source1 =
                Observable.interval(1, TimeUnit.SECONDS)
                        .map(l -> (l + 1) + " seconds");
        Observable<String> source2 =
                Observable.interval(300, TimeUnit.MILLISECONDS)
                        .map(l -> ((l + 1) * 300) + " milliseconds");

        PublishSubject<String> subscriber
                = PublishSubject.<String>create();

        source1.subscribe(subscriber);
        source2.subscribe(subscriber);

        sleep(3000);
    }

    private static void createSubject() {
        PublishSubject<String> subject = PublishSubject.create();

        subject.map(String::length)
                .subscribe(System.out::println);

        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        subject.onComplete();
    }
}
