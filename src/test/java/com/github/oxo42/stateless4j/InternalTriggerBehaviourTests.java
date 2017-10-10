package com.github.oxo42.stateless4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.oxo42.stateless4j.delegates.Action;
import com.github.oxo42.stateless4j.delegates.Action1;
import com.github.oxo42.stateless4j.delegates.FuncBoolean;
import com.github.oxo42.stateless4j.triggers.InternalTriggerBehaviour;

public class InternalTriggerBehaviourTests {

    public static FuncBoolean returnTrue = new FuncBoolean() {

        @Override
        public boolean call() {
            return true;
        }
    };

    public static FuncBoolean returnFalse = new FuncBoolean() {

        @Override
        public boolean call() {
            return false;
        }
    };

    public static Action nopAction = new Action() {

        @Override
        public void doIt() {
        }
    };
    
    public static final Action1<Object[]> nopActionN = new Action1<Object[]>() {
        
        @Override
        public void doIt(Object[] args) {
        }
    };

    @Test
    public void StateRemainsUnchanged() {
        InternalTriggerBehaviour<State, Trigger> ignored = new InternalTriggerBehaviour<>(Trigger.X, returnTrue, nopActionN);
        State target = ignored.transitionsTo(State.B, new Object[0]);
        assertEquals(State.B, target);
    }

    @Test
    public void ExposesCorrectUnderlyingTrigger() {
    	InternalTriggerBehaviour<State, Trigger> ignored = new InternalTriggerBehaviour<>(Trigger.X, returnTrue, nopActionN);
        assertEquals(Trigger.X, ignored.getTrigger());
    }

    @Test
    public void WhenGuardConditionFalse_IsGuardConditionMetIsFalse() {
    	InternalTriggerBehaviour<State, Trigger> ignored = new InternalTriggerBehaviour<>(Trigger.X, returnFalse, nopActionN);
        assertFalse(ignored.isGuardConditionMet());
    }

    @Test
    public void WhenGuardConditionTrue_IsGuardConditionMetIsTrue() {
    	InternalTriggerBehaviour<State, Trigger> ignored = new InternalTriggerBehaviour<>(Trigger.X, returnTrue, nopActionN);
        assertTrue(ignored.isGuardConditionMet());
    }
    
    @Test
    public void TransitionIsInternal() {
    	InternalTriggerBehaviour<State, Trigger> ignored = new InternalTriggerBehaviour<>(Trigger.X, returnTrue, nopActionN);
    	assertTrue(ignored.isInternal());
    }
}
