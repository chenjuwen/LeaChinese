package com.heasy.leachinese.core.service;

import com.heasy.leachinese.core.HeasyContext;

/**
 * Created by Administrator on 2017/12/28.
 */
public interface Service {
    void init();
    boolean isInit();
    void unInit();
    HeasyContext getHeasyContext();
    void setHeasyContext(HeasyContext heasyContext);
}
