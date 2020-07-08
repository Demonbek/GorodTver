/*
 * *
 *  * Created by DemonApps on 14.03.20 20:01
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 14.03.20 19:53
 *
 */

package ru.gorod_dubna.gorod_dubna;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.my.target.ads.MyTargetView;

@SuppressLint("Registered")
public class Video extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String GOOGLE_API_KEY = "AIzaSyDU9sAsjm1bIHpSC0FGEmiUz76hx_7m7r8";
    public static final String YOUTUBE_PLAYLIST_ID = "PLw5XBQ4p2VPt5mrnq_naW8H3XgxuT7QT-";
    final String TAG = getClass().getSimpleName();
    private MyTargetView adViewVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        final RelativeLayout layout = findViewById(R.id.activityLayoutVideo);


        // Включение режима отладки
        //MyTargetView.setDebugMode(true);


        // Создаем экземпляр MyTargetView, формат 320х50
        adViewVideo = new MyTargetView(this);

        // Создаем экземпляр MyTargetView, формат 300х250
        // adView = new MyTargetView(this, AdSize.BANNER_300x250);

        // Инициализируем экземпляр
        adViewVideo.init(426852);


        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtubePlayerView);
        youTubePlayerView.initialize(GOOGLE_API_KEY, this);

        // Устанавливаем LayoutParams
        RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        adViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adViewVideo.setLayoutParams(adViewLayoutParams);

        // Устанавливаем слушатель событий
        adViewVideo.setListener(new MyTargetView.MyTargetViewListener()
        {
            @Override
            public void onLoad(@NonNull MyTargetView myTargetView)
            {
                // Данные успешно загружены, запускаем показ объявлений
                layout.addView(adViewVideo);
            }

            @Override
            public void onNoAd(@NonNull String reason, @NonNull MyTargetView myTargetView)
            {
            }

            @Override
            public void onClick(@NonNull MyTargetView myTargetView)
            {
            }
        });

        // Запускаем загрузку данных
        adViewVideo.load();
    }


    @Override
    public void onInitializationSuccess
            (YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

        Log.e(TAG, "Initialized youtube player");

        youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoaded(String s) {

            }

            @Override
            public void onAdStarted() {

            }

            @Override
            public void onVideoStarted() {

            }

            @Override
            public void onVideoEnded() {

            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {

            }
        });

        youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {

            }

            @Override
            public void onPaused() {

            }

            @Override
            public void onStopped() {

            }

            @Override
            public void onBuffering(boolean b) {

            }

            @Override
            public void onSeekTo(int i) {

            }
        });

        if (!wasRestored) {
            youTubePlayer.cuePlaylist(YOUTUBE_PLAYLIST_ID);
        }

    }

    @Override
    public void onInitializationFailure
            (YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        Log.e(TAG, "Failed to initialize youtube player");
    }
}
