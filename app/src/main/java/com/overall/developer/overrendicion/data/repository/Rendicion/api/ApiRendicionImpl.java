package com.overall.developer.overrendicion.data.repository.Rendicion.api;

import com.overall.developer.overrendicion.data.repository.Rendicion.RendicionRepository;

public class ApiRendicionImpl implements ApiRendicion
{
    RendicionRepository mRepository;

    public ApiRendicionImpl(RendicionRepository repository)
    {
        this.mRepository = repository;
    }
}
