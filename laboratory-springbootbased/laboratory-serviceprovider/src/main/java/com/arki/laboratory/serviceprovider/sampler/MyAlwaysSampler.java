package com.arki.laboratory.serviceprovider.sampler;

import brave.sampler.Sampler;

public class MyAlwaysSampler extends Sampler {

    private boolean on;

    public MyAlwaysSampler(boolean on) {
        this.on = on;
    }

    @Override
    public boolean isSampled(long traceId) {
        return on;
    }
}
