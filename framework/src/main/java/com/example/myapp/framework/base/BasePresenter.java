package com.example.myapp.framework.base;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.OutsideLifecycleException;
import com.trello.rxlifecycle2.RxLifecycle;

import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;

public class BasePresenter<V extends BaseView> {
    protected V view;
    private final BehaviorSubject<Integer> lifecycleSubject = BehaviorSubject.create();

    protected void onBind() {
        lifecycleSubject.onNext(PresenterEvent.ATTACH);
    }

    protected void onUnbind() {
        lifecycleSubject.onNext(PresenterEvent.DETACH);
    }

    public final void bind(V viewToBind) {
        view = viewToBind;
        onBind();
    }

    public final void unbind() {
        onUnbind();
        view = null;
    }

    protected <T> LifecycleTransformer<T> bindToLifeCycle(){
        return RxLifecycle.bind(lifecycleSubject, PRESENTER_LIFECYCLE);
    }

    private static final Function<Integer, Integer> PRESENTER_LIFECYCLE = lastEvent -> {
        if (lastEvent == PresenterEvent.ATTACH) {
            return PresenterEvent.DETACH;
        } else if (lastEvent == PresenterEvent.DETACH) {
            throw new OutsideLifecycleException("Cannot bind to Presenter lifecycle when outside of it.");
        } else {
            throw new UnsupportedOperationException("Binding to " + lastEvent + " not yet implemented");
        }
    };

    public interface PresenterEvent {
        int ATTACH = 0;
        int DETACH = 1;
    }
}
