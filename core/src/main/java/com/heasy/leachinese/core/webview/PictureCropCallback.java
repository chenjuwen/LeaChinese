package com.heasy.leachinese.core.webview;

import com.heasy.leachinese.core.HeasyContext;

/**
 * 图片裁剪后的回调接口类
 */
public interface PictureCropCallback {
    void execute(HeasyContext heasyContext, String jsonData, String imagePath);
}
