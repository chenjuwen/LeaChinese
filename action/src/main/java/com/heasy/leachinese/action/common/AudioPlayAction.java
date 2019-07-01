package com.heasy.leachinese.action.common;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.alibaba.fastjson.JSONObject;
import com.heasy.leachinese.action.ActionNames;
import com.heasy.leachinese.core.HeasyContext;
import com.heasy.leachinese.core.annotation.JSActionAnnotation;
import com.heasy.leachinese.core.utils.FastjsonUtil;
import com.heasy.leachinese.core.webview.Action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 音频播放
 */
@JSActionAnnotation(name = ActionNames.AudioPlay)
public class AudioPlayAction implements Action {
    private static final Logger logger = LoggerFactory.getLogger(AudioPlayAction.class);
    private ReentrantLock lock = new ReentrantLock(true);

    //输入参数
    public static final String p_audioFile = "audioFile";

    private MediaPlayer mediaPlayer;
    String audioFile = "";

    @Override
    public String execute(HeasyContext heasyContext, String jsonData, String extend) {
        JSONObject jsonObject = FastjsonUtil.string2JSONObject(jsonData);
        audioFile = FastjsonUtil.getString(jsonObject, p_audioFile);
        startPlay(heasyContext);
        return SUCCESS;
    }

    private void startPlay(HeasyContext heasyContext){
        try {
            boolean b = lock.tryLock(500, TimeUnit.MILLISECONDS);
            if(b){
                try {
                    mediaPlayer = new MediaPlayer();

                    AssetFileDescriptor assetFileDescriptor = heasyContext.getServiceEngine().getAndroidContext().getAssets().openFd(audioFile);
                    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());

                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    logger.debug("media start play!!");

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mMediaPlayer) {
                            stopPlay();
                            lock.unlock();
                        }
                    });
                } catch (Exception ex) {
                    logger.error("play audio error: " + ex.toString());
                    stopPlay();
                    lock.unlock();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            lock.unlock();
        }
    }

    private void stopPlay(){
        if(mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
                logger.debug("media stop play!!");
            } catch (Exception ex) {
                logger.error("stop play audio error: " + ex.toString());
            }
        }
    }

}
