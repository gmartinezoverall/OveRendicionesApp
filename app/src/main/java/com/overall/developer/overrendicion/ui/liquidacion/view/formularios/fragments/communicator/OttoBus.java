package com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.communicator;

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
