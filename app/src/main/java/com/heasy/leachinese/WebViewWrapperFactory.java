package com.heasy.leachinese;

import com.heasy.leachinese.core.HeasyApplication;
import com.heasy.leachinese.core.HeasyContext;
import com.heasy.leachinese.core.configuration.AbstractComponentScanner;
import com.heasy.leachinese.core.webview.DefaultActionDispatcher;
import com.heasy.leachinese.core.webview.DefaultWebChromeClient;
import com.heasy.leachinese.core.webview.DefaultWebViewClient;
import com.heasy.leachinese.core.webview.JSInterfaceImpl;
import com.heasy.leachinese.core.webview.WebViewWrapper;
import com.heasy.leachinese.action.ActionScanner;

/**
 * Created by Administrator on 2018/1/23.
 */
class WebViewWrapperFactory {
    private static DefaultActionDispatcher actionDispatcher = null;
    private static WebViewWrapper webViewWrapper = null;

    /**
     * 初始化DefaultJSActionRouter
     * @param heasyContext
     * @param actionBasePackages 多个包路径用 分号 分隔
     */
    public static void initActionDispatcher(HeasyContext heasyContext, String actionBasePackages){
        AbstractComponentScanner actionScanner = new ActionScanner();
        actionScanner.setContext(heasyContext.getServiceEngine().getAndroidContext());
        actionScanner.setBasePackages(actionBasePackages);

        //DefaultActionDispatcher
        actionDispatcher = new DefaultActionDispatcher();
        actionDispatcher.setActionScanner(actionScanner);
        actionDispatcher.init();
    }

    /**
     * @param heasyContext
     */
    public static void build(HeasyContext heasyContext){
        //JSInterfaceImpl
        JSInterfaceImpl jsInterface = new JSInterfaceImpl();
        jsInterface.setActionDispatcher(actionDispatcher);
        jsInterface.setHeasyContext(heasyContext);

        //DefaultWebViewClient
        DefaultWebViewClient webViewClient = new DefaultWebViewClient();

        //保证select控件能弹出下拉框
        HeasyApplication app = (HeasyApplication)heasyContext.getServiceEngine().getAndroidContext();
        webViewWrapper = new WebViewWrapper.Builder(app.getMainActivity())
                .setWebViewClient(webViewClient)
                .setWebChromeClient(new DefaultWebChromeClient())
                .setJSInterface(jsInterface)
                .build();

        heasyContext.setJsInterface(jsInterface);
    }

    public static WebViewWrapper getWebViewWrapper() {
        return webViewWrapper;
    }

}
