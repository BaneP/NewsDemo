1. To change google market provider, just change value of GOOGLE_MARKET_DEVELOPER in MainActivity.java (for demo purposes it is Facebook).
2. To change link of splash image to load some custom URL (not market one) change the value of splashImageLoadMarket in MainActivity.java to false (currently is true).
	Then you must change WEB_MARKET_URI to desired one.
3. To hide splash image you must change "// mSplashImage.setVisibility(View.GONE);" to "mSplashImage.setVisibility(View.GONE);" in MainActivity.java (erase "//").
4. To change ad IDs, change the value of "banner_ad_unit_id", "banner_ad_unit_id_browser" and "interstitial_ad_unit_id" in /res/values/strings.xml (currently it is some test value).
5. To populate list with your own logo, links you will have to change the code under "static{}" tag in MainActivity.java.
	Here is example of one news: mNewsList.add(new News("BBC", "http://www.bbc.com/news","http://www.iconarchive.com/download/i75805/martz90/circle/bbc-news.ico"));
	You can change existing ones and copy/paste as many entries as you want in this list. To create new News() you will have to provide its NAME, URL, IMAGE_URL.
7. To change application name, just change "app_name" in in /res/values/strings.xml.
8. To change application icon copy it in /res/drawable-xhdpi/ folder and change android:icon="@drawable/<icon_name>" in AndroidManifest.xml.
9. To change icon of splash image, copy new icon to /res/drawable-xxhdpi/ folder and overwrite "splash.png".

NOTE: custom icon name must contain only small letters and underscore symbol!!!