package com.heasy.leachinese.action.common;

import com.heasy.leachinese.core.HeasyContext;
import com.heasy.leachinese.core.annotation.JSActionAnnotation;
import com.heasy.leachinese.action.ActionNames;
import com.heasy.leachinese.core.webview.Action;

/**
 * 返回上一页
 */
@JSActionAnnotation(name = ActionNames.GoBack)
public class GoBackAction implements Action {
    @Override
    public String execute(HeasyContext heasyContext, String jsonData, String extend) {
        heasyContext.getJsInterface().goBack();
        return SUCCESS;
    }
}
