package com.heasy.leachinese.core.service;

import com.heasy.leachinese.core.HeasyContext;

/**
 * Created by Administrator on 2018/1/27.
 */
public abstract class AbstractService implements Service {
    private HeasyContext heasyContext;
    protected boolean successInit = false;

    @Override
    public HeasyContext getHeasyContext() {
        return heasyContext;
    }

    @Override
    public void setHeasyContext(HeasyContext heasyContext) {
        this.heasyContext = heasyContext;
    }
}
