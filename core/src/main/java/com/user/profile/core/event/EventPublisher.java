package com.user.profile.core.event;

/**
 * @author jayensingh
 * <p>
 * <p>
 * 18/04/22
 */
public interface EventPublisher<O> {

    void publish(O msg);
}
