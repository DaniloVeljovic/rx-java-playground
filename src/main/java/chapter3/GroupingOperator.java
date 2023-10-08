package chapter3;

import io.reactivex.Observable;

public class GroupingOperator {
    public static void main(String[] args) {
        grouping();

    }

    private static void grouping() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .groupBy(s -> s.length())
                .flatMapSingle(group -> group.toList())
                .subscribe(element -> System.out.println(element));
    }
}
