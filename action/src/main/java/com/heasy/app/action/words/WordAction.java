package com.heasy.app.action.words;

import com.alibaba.fastjson.JSONObject;
import com.heasy.app.action.ActionNames;
import com.heasy.app.core.HeasyContext;
import com.heasy.app.core.annotation.JSActionAnnotation;
import com.heasy.app.core.datastorage.PageInfo;
import com.heasy.app.core.datastorage.SQLCipherManager;
import com.heasy.app.core.utils.FastjsonUtil;
import com.heasy.app.core.utils.StringUtil;
import com.heasy.app.core.webview.Action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 拼音
 */
@JSActionAnnotation(name = ActionNames.Word)
public class WordAction implements Action {
    private static final Logger logger = LoggerFactory.getLogger(WordAction.class);

    @Override
    public String execute(HeasyContext heasyContext, String jsonData, String extend) {
        JSONObject jsonObject = FastjsonUtil.string2JSONObject(jsonData);

        if("query".equalsIgnoreCase(extend)) { //查询
            String keyword = FastjsonUtil.getString(jsonObject, "keyword");

            String orderBy = FastjsonUtil.getString(jsonObject, "orderBy");
            if("笔画数".equalsIgnoreCase(orderBy)){
                orderBy = "count";
            }else if("拼音".equalsIgnoreCase(orderBy)){
                orderBy = "pinyin";
            }else if("汉字".equalsIgnoreCase(orderBy)){
                orderBy = "word";
            }else{
                orderBy = "count";
            }

            int pageSize = Integer.parseInt(FastjsonUtil.getString(jsonObject, "pageSize"));
            int pageNum = Integer.parseInt(FastjsonUtil.getString(jsonObject, "pageNum"));

            SQLCipherManager sqlCipherManager = heasyContext.getServiceEngine().getDataService().getSQLCipherManager();
            PageInfo<Map<String, String>> pageInfo = new PageInfo<>();

            String[] columns = {"id","word","pinyin","count","bushou","jiegou","cizu","image_name"};

            if (StringUtil.isNotEmpty(keyword)) {
                pageInfo = sqlCipherManager
                        .queryForPage("hanzi", columns, "word=? or pinyin like ?", new String[]{keyword, "%" + keyword + "%"}, orderBy + " asc", pageNum, pageSize);
            } else {
                pageInfo = sqlCipherManager
                        .queryForPage("hanzi", columns, null, null, orderBy + " asc", pageNum, pageSize);
            }

            logger.debug("pageNum=" + pageInfo.getPageNum() + ", pageSize=" + pageInfo.getPageSize() +  ", pages=" + pageInfo.getPages() +  ", size=" + pageInfo.getSize() +  ", total=" + pageInfo.getTotal());
            String dataResult = FastjsonUtil.object2String(pageInfo);
            //logger.debug(dataResult);
            return dataResult;
        }

        return SUCCESS;
    }

}
