package chapter1;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.Arrays;

public class OneOrZeroObservables {
    public static void main(String[] args) {
        single();
        maybe();
    }

    private static void maybe() {
        Maybe<Integer> maybe = Maybe.just(100);
        maybe.subscribe(element -> System.out.println(element));

        Maybe<Object> empty = Maybe.empty();
        empty.subscribe(element -> System.out.println(element));
    }

    private static void single() {
        Single.just("Hello")
                .map(String::length)
                .subscribe(element -> System.out.println(element), Throwable::printStackTrace);


        Observable.fromIterable(Arrays.asList("Alpha", "Beta", "Gamma"))
                .first("Nil")
                .subscribe(iterable -> System.out.println(iterable));
    }
}
