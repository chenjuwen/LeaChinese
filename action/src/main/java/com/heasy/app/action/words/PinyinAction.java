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
@JSActionAnnotation(name = ActionNames.Pinyin)
public class PinyinAction implements Action {
    private static final Logger logger = LoggerFactory.getLogger(PinyinAction.class);

    @Override
    public String execute(HeasyContext heasyContext, String jsonData, String extend) {
        JSONObject jsonObject = FastjsonUtil.string2JSONObject(jsonData);
        String name = FastjsonUtil.getString(jsonObject, "name");
        String mp3FilePath = heasyContext.getServiceEngine().getConfigurationService().getConfigBean().getDBFilePath();
        MP3Play.play(mp3FilePath);
        return SUCCESS;
    }

}
