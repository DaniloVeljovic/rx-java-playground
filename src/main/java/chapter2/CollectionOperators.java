package chapter2;

import io.reactivex.Observable;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionOperators {
    public static void main(String[] args) {
        toListRx();
        toSortedList();
        toMap();
        toMapWithValue();
        toMultiMap();
        collect();
    }

    private static void collect() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta")
                .collect(HashSet::new, HashSet::add)
                .subscribe(element -> System.out.println(element));
    }

    private static void toMultiMap() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta")
                .toMultimap(String::length)
                .subscribe(value -> System.out.println(value));
    }

    private static void toMapWithValue() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta")
                .toMap(key -> key.charAt(0), String::length, ConcurrentHashMap::new)
                .subscribe(v -> System.out.println(v));
    }

    private static void toMap() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta")
                .toMap(value -> value.charAt(0))
                .subscribe(value -> System.out.println(value));
    }

    private static void toSortedList() {
        Observable.just(5, 3, 1, 2, 8, 10)
                .toSortedList()
                .subscribe(value -> System.out.println(value));
    }

    private static void toListRx() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta")
                .toList(CopyOnWriteArrayList::new)
                .subscribe(value -> System.out.println(value));
    }
}
