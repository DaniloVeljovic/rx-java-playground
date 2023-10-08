package chapter3;

import io.reactivex.Observable;

public class ConcatenationOperators {
    public static void main(String[] args) {
        concat();
        concatWith();
        concatMap();
    }

    private static void concatMap() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma");
        source1.concatMap(value -> Observable.fromArray(value.split("")))
                .subscribe(value -> System.out.println("Received: " + value));
    }

    private static void concatWith() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma");
        Observable<String> source2 = Observable.just("Delta", "Eta", "Zeta");

        source1.concatWith(source2).subscribe(value -> System.out.println("Received: " + value));
    }

    private static void concat() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma");
        Observable<String> source2 = Observable.just("Delta", "Eta", "Zeta");

        Observable.concat(source1, source2).subscribe(value -> System.out.println("Received: " + value));

    }


}
