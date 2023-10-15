package chapter4;

import io.reactivex.Observable;

public class Caching {
    public static void main(String[] args) {
        Observable<Integer> source = Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                .scan(0, Integer::sum)
                .cache();
                //.cacheWithInitialCapacity(2);

        source.subscribe(System.out::println);
    }
}
