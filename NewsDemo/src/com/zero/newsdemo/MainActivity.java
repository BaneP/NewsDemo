package com.zero.newsdemo;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.zero.newsdemo.beans.News;

public class MainActivity extends ActionBarActivity implements
		OnItemClickListener, OnClickListener {
	private static final int SPLASH_DELAY = 3000;
	private static final String GOOGLE_MARKET_URI = "market://search?q=pub:";
	private static final String WEB_MARKET_URI = "http://play.google.com/store/apps/developer?id=";
	/**
	 * If you want to change developer on google market, change this to anything
	 * you want
	 */
	private static final String GOOGLE_MARKET_DEVELOPER = "Facebook";

	private static ArrayList<News> mNewsList = new ArrayList<News>();
	/**
	 * List of news sites is populated here. You populate it by adding new News
	 * object. To create new News object you need [NAME, NEWS_URL,
	 * NEWS_ICON_URL]
	 */
	static {
		mNewsList
				.add(new News("BBC", "http://www.bbc.com/news",
						R.drawable.f));
		mNewsList
				.add(new News(
						"CNN",
						"http://edition.cnn.com/",
						R.drawable.g));
		mNewsList.add(new News("Yahoo", "http://news.yahoo.com/",
				R.drawable.h));
		mNewsList
				.add(new News("FOX", "http://www.foxnews.com/",
						R.drawable.i));
		mNewsList.add(new News("abc", "http://abcnews.go.com/",
				"http://www.userlogos.org/files/logos/MShadows/abc2.png"));
	}

	private ListView mListViewNews;
	private ImageView mSplashImage;
	private InterstitialAd mInterstitialAd;

	private Runnable mCloseSplashRunnable = new Runnable() {

		@Override
		public void run() {
			if (mSplashImage.isShown()) {
				mSplashImage.setVisibility(View.GONE);
				showInterstitialAd = false;
				displayInterstitial();
			}
		}
	};

	private boolean showInterstitialAd = true;
	/**
	 * Change this to false if you don't want to start market
	 */
	private final boolean splashImageLoadMarket = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Init splash image
		mSplashImage = (ImageView) findViewById(R.id.imageViewSplash);
		/**
		 * Un-comment this if you do not want splash image to show
		 */
		// mSplashImage.setVisibility(View.GONE);
		mSplashImage.setOnClickListener(this);
		// Init list view with news sites
		mListViewNews = (ListView) findViewById(R.id.listView);
		mListViewNews.setAdapter(new NewsAdapter(mNewsList));
		mListViewNews.setOnItemClickListener(this);

		// Init ads
		AdView mAdView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);

		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
		requestNewInterstitial();
		mInterstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				requestNewInterstitial();
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {
				Log.d("MainActivity", "onAdFailedToLoad " + errorCode);
			}

			@Override
			public void onAdLoaded() {
				if (showInterstitialAd) {
					showInterstitialAd = false;
					if (!mSplashImage.isShown()) {
						displayInterstitial();
					}
				}
			}
		});

	}

	// Request new add
	private void requestNewInterstitial() {
		AdRequest adRequest = new AdRequest.Builder().build();// This is test device id
		mInterstitialAd.loadAd(adRequest);
	}

	public void displayInterstitial() {
		if (mInterstitialAd.isLoaded()) {
			mInterstitialAd.show();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSplashImage.postDelayed(mCloseSplashRunnable, SPLASH_DELAY);
		displayInterstitial();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		News news = (News) parent.getItemAtPosition(position);
		Intent intent = new Intent(this, BrowserActivity.class);
		intent.putExtra(BrowserActivity.URL_TO_LOAD, news.getActionUrl());
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		if (splashImageLoadMarket) {
			try {
				/**
				 * This starts google market application if it exist on device
				 */
				startActivity(new Intent(Intent.ACTION_VIEW,
						Uri.parse(GOOGLE_MARKET_URI + GOOGLE_MARKET_DEVELOPER)));
			} catch (android.content.ActivityNotFoundException anfe) {
				/**
				 * This starts browser application if there is not google market
				 * application
				 */
				Intent intent = new Intent(this, BrowserActivity.class);
				intent.putExtra(BrowserActivity.URL_TO_LOAD, WEB_MARKET_URI
						+ GOOGLE_MARKET_DEVELOPER);
				startActivity(intent);
			}
		} else {
			/**
			 * This starts browser application if there is not google market
			 * application
			 */
			Intent intent = new Intent(this, BrowserActivity.class);
			intent.putExtra(BrowserActivity.URL_TO_LOAD, WEB_MARKET_URI);
			startActivity(intent);
		}
	}

}
