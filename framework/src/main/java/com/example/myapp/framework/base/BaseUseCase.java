package com.example.myapp.framework.base;


import io.reactivex.Observable;

public abstract class BaseUseCase<T> {

    private final UseCaseComposer mUseCaseComposer;

    public BaseUseCase(UseCaseComposer useCaseComposer) {
        this.mUseCaseComposer = useCaseComposer;
    }

    public Observable<T> executeUseCase() {
        Observable<T> observable = createObservable();
        observable = observable.compose(mUseCaseComposer.apply());
        return observable;
    }

    protected abstract Observable<T> createObservable();
}
