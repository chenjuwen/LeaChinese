package com.heasy.leachinese.core.webview;

import com.heasy.leachinese.core.HeasyContext;

/**
 * Created by Administrator on 2017/12/21.
 */
public interface Action {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    String execute(HeasyContext heasyContext, String jsonData, String extend);
}
