package com.heasy.leachinese.core.webview;

import com.heasy.leachinese.core.HeasyContext;

/**
 * Created by Administrator on 2017/12/20.
 */
public interface ActionDispatcher {
    void init();
    String dispatch(HeasyContext heasyContext, String actionName, String jsonData, String extend);
}
