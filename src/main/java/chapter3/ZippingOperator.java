package chapter3;

import io.reactivex.Observable;

public class ZippingOperator {
    public static void main(String[] args) {
        zip();
    }

    private static void zip() {
        Observable<String> source1 =
                Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<Integer> source2 = Observable.range(1,6);

        Observable.zip(source1, source2, (s, i) -> s + "-" + i)
                .subscribe(element -> System.out.println(element));

    }


}
