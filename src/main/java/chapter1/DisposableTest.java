package chapter1;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class DisposableTest {

    public static void main(String[] args) throws InterruptedException {
        disposable();
    }

    private static void disposable() throws InterruptedException {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);

        Disposable disposable = source.subscribe(value -> System.out.println(value));

        sleep(5000);

        disposable.dispose();

        sleep(5000);
    }
}
