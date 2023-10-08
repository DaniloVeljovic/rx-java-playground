package chapter3;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class AmbiguousOperator {
    public static void main(String[] args) throws InterruptedException {
        ambiguous();
    }

    private static void ambiguous() throws InterruptedException {
        Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
                .take(2)
                .map(val -> val + 1)
                .map(element -> "Source1: " + element + " seconds");

        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(element -> (element + 1) * 300)
                .map(element -> "Source2: " + element + " milliseconds");

        Observable.amb(Arrays.asList(source1, source2))
                .subscribe(element -> System.out.println(element));

        sleep(5000);
    }

}
