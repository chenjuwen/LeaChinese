package com.heasy.leachinese.action.common;

import com.heasy.leachinese.core.HeasyContext;
import com.heasy.leachinese.core.annotation.JSActionAnnotation;
import com.heasy.leachinese.core.event.ExitAppEvent;
import com.heasy.leachinese.core.webview.Action;
import com.heasy.leachinese.action.ActionNames;

/**
 * 退出应用
 */
@JSActionAnnotation(name = ActionNames.ExitApp)
public class ExitAppAction implements Action {
    @Override
    public String execute(final HeasyContext heasyContext, String jsonData, String extend) {
        heasyContext.getServiceEngine().getEventService().postEvent(new ExitAppEvent(this));
        return SUCCESS;
    }

}
