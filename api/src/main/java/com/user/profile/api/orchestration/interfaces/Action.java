package com.user.profile.api.orchestration.interfaces;

/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */
public interface Action<I, O> {

    O execute(I input);

    default <O1> Action<I, O1> then(Action<O, O1> afterAction) {
        return i -> afterAction.execute(this.execute(i));
    }
}
