package com.itaycohen.services;

/**
 * Delivering the network layer queries into to server-side services.
 * @param <RM> The expected result model
 * @param <PM> The supplied parameter model.
 */
public interface IController<RM, PM> {

    RM onGet(PM params);
    void onUpdate(PM params);
    void onDelete(PM params);
}
