package com.overall.developer.overrendicion2.ui.communicator;

import com.squareup.otto.Bus;

public class OttoBus
{
    private static Bus bus;

    public static Bus getBus()
    {
        if (bus == null) bus = new Bus();
        return bus;
    }
}
