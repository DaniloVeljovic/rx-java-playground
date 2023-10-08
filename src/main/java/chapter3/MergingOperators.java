package chapter3;

import io.reactivex.Observable;

public class MergingOperators {
    public static void main(String[] args) {
        merge();
        mergeWith();
        mergeArray();
        flatMapOneToMany();
        flatMapFilter();
    }

    private static void flatMapFilter() {
        Observable.just("521934/2342/FOXTROT", "21962/12112/78886 /TANGO",
                        "283242/4542/WHISKEY/2348562")
                .flatMap(value -> Observable.fromArray(value.split("/")))
                .filter(value ->  value.matches("[0-9]+"))
                .map(Integer::valueOf)
                .subscribe(System.out::println);
    }

    private static void flatMapOneToMany() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma");
        source1.flatMap(value -> Observable.fromArray(value.split("")))
                .subscribe(value -> System.out.println("Received: " + value));
    }

    private static void mergeArray() {
        Observable<String> source1 =
                Observable.just("Alpha", "Beta");
        Observable<String> source2 =
                Observable.just("Gamma", "Delta");
        Observable<String> source3 =
                Observable.just("Epsilon", "Zeta");
        Observable<String> source4 =
                Observable.just("Eta", "Theta");
        Observable<String> source5 =
                Observable.just("Iota", "Kappa");

        Observable.mergeArray(source1, source2, source3, source4, source5)
                .subscribe(value -> System.out.println("Received: " + value));
    }

    private static void mergeWith() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma");
        Observable<String> source2 = Observable.just("Delta", "Eta", "Zeta");
        source1.mergeWith(source2)
                .subscribe(value -> System.out.println("Received: " + value));
    }

    private static void merge() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma");
        Observable<String> source2 = Observable.just("Delta", "Eta", "Zeta");

        Observable.merge(source1, source2).subscribe(value -> System.out.println("Received: " + value));
    }


}
