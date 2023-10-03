package chapter2;

import io.reactivex.Observable;

public class ErrorRecoveryOperators {
    public static void main(String[] args) {
        //doError();
        onErrorReturnItem();
        onErrorReturn();
        //retry();

    }

    private static void retry() {
        Observable.just(2, 5, 4, 0, 4, 5)
                .map(v -> 10 / v)
                .retry(2)
                .subscribe(value -> System.out.println(value));
    }

    private static void onErrorReturn() {
        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(element -> 10 / element)
                .onErrorReturn(element -> -1)
                .subscribe(element -> System.out.println(element), element -> System.out.println("Error: " + element));
    }

    private static void onErrorReturnItem() {
        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(element -> 10 / element)
                .onErrorReturnItem(-1)
                .subscribe(element -> System.out.println(element), element -> System.out.println("Error: " + element));
    }

    private static void doError() {
        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(element -> 10 / element)
                .subscribe(element -> System.out.println(element), element -> System.out.println("Error: " + element));
    }


}
