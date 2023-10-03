package chapter1;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;

public class CreateDisposables {
    public static void main(String[] args) {
        classic();
        resourceObserver();
        create();
    }

    private static void create() {

    }

    private static void resourceObserver() {
        ResourceObserver<Integer> resourceObserver = new ResourceObserver<>() {
            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        };

        Observable.range(1, 10)
                .subscribeWith(resourceObserver);

    }

    private static void classic() {
        new Observer<Integer>() {
            private Disposable disposable;
            @Override
            public void onSubscribe(Disposable disposable) {
                this.disposable = disposable;
            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
