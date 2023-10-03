package introduction;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Launcher {
    public static void main(String[] args) throws InterruptedException {
        basicFunctionalities();
        learningPushModel();
    }

    private static void learningPushModel() throws InterruptedException {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);

        interval.subscribe(s -> System.out.println(s));

        sleep(5000);
    }

    private static void basicFunctionalities() {
        Observable<String> myStrings = Observable.just("A", "B", "C", "D", "E");

        myStrings
                .map(String::length)
                .subscribe(System.out::println);
    }


}
