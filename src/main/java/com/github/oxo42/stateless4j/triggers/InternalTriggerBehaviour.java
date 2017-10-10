package com.github.oxo42.stateless4j.triggers;

import com.github.oxo42.stateless4j.delegates.Action1;
import com.github.oxo42.stateless4j.delegates.FuncBoolean;

public class InternalTriggerBehaviour<S, T> extends TriggerBehaviour<S, T> {

    private final Action1<Object[]> action;

    public InternalTriggerBehaviour(T trigger, FuncBoolean guard, Action1<Object[]> action) {
        super(trigger, guard);
        this.action = action;
    }
    
    @Override
    public void performAction(Object[] args) {
        action.doIt(args);
    }
    
    @Override
    public boolean isInternal() {
        return true;
    }    

    @Override
    public S transitionsTo(S source, Object[] args) {
        return source;
    }
}
