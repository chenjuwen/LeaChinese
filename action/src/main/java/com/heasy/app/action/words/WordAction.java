package com.heasy.app.action.words;

import com.alibaba.fastjson.JSONObject;
import com.heasy.app.action.ActionNames;
import com.heasy.app.core.HeasyContext;
import com.heasy.app.core.annotation.JSActionAnnotation;
import com.heasy.app.core.utils.FastjsonUtil;
import com.heasy.app.core.utils.MP3Play;
import com.heasy.app.core.webview.Action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 拼音
 */
@JSActionAnnotation(name = ActionNames.Word)
public class WordAction implements Action {
    private static final Logger logger = LoggerFactory.getLogger(WordAction.class);

    @Override
    public String execute(HeasyContext heasyContext, String jsonData, String extend) {
        JSONObject jsonObject = FastjsonUtil.string2JSONObject(jsonData);
        String keyword = FastjsonUtil.getString(jsonObject, "keyword");

        StringBuilder sb = new StringBuilder();

        return SUCCESS;
    }

}
