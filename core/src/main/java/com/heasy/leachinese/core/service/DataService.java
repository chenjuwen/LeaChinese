package com.heasy.leachinese.core.service;

import com.heasy.leachinese.core.datastorage.DataCache;
import com.heasy.leachinese.core.datastorage.SQLCipherManager;

/**
 * Created by Administrator on 2017/12/29.
 */
public interface DataService extends Service {
    SQLCipherManager getSQLCipherManager();
    DataCache getGlobalMemoryDataCache();
}
