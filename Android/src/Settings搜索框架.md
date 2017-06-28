# 前言

本文基于Android N源码进行分析，其实Settings的数据库不仅能够搜索Settings下的项，它还可以搜索其它应用的一些信息，例如短信和通讯录，理论上都是可以实现的。。

# 涉及的类



# 搜索数据库

Settings里面的搜索框架是基于数据库来实现的，数据库的名称叫**search_index.db** ,Android M是在/data/data/com.android.settings/databases/下，Android N是在/data/user_de/0/com.android.settings/databases/下，如果不清楚的可按照下面的步骤进行查询

> 1. adb shell
> 2. find . "search_index.db"

# 插入数据

## 1. 声明数据

声明数据的意思就是表明哪些界面的数据是可以被搜索的，例如在Settings中，SearchIndexableResources就声明了许多可以搜索的界面，Wifi、显示、应用管理等。。

``` java
//SearchIndexableResources.java
//声明显示是可以搜索的
sResMap.put(DisplaySettings.class.getName(),
            new SearchIndexableResource(
              Ranking.getRankForClassName(DisplaySettings.class.getName()),
              NO_DATA_RES_ID,
              DisplaySettings.class.getName(),
              R.drawable.ic_settings_display));
```

## 2. 创建Provider

Settings的数据库的来源是ContentProvider，所以创建一个应用内部的ContentProvider，继承自SearchIndexablesProvider，来进行提供数据，例如下面是Settings提供数据的

``` java

//SettingsSearchIndexablesProvider.java
public class SettingsSearchIndexablesProvider extends SearchIndexablesProvider {
    private static final String TAG = "SettingsSearchIndexablesProvider";

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor queryXmlResources(String[] projection) {
      	//这里是创建了一个虚拟表，是用来提供数据的，因为我们的数据并不是存储在数据库中
        MatrixCursor cursor = new MatrixCursor(INDEXABLES_XML_RES_COLUMNS);
      	//从SearchIndexableResources中获取类等数据
        Collection<SearchIndexableResource> values = SearchIndexableResources.values();
        for (SearchIndexableResource val : values) {
            Object[] ref = new Object[7];
            ref[COLUMN_INDEX_XML_RES_RANK] = val.rank;
            ref[COLUMN_INDEX_XML_RES_RESID] = val.xmlResId;
            ref[COLUMN_INDEX_XML_RES_CLASS_NAME] = val.className;
            ref[COLUMN_INDEX_XML_RES_ICON_RESID] = val.iconResId;
            ref[COLUMN_INDEX_XML_RES_INTENT_ACTION] = null; // intent action
            ref[COLUMN_INDEX_XML_RES_INTENT_TARGET_PACKAGE] = null; // intent target package
            ref[COLUMN_INDEX_XML_RES_INTENT_TARGET_CLASS] = null; // intent target class
            cursor.addRow(ref);
        }
        return cursor;
    }

    @Override
    public Cursor queryRawData(String[] projection) {
        MatrixCursor result = new MatrixCursor(INDEXABLES_RAW_COLUMNS);
        return result;
    }

    @Override
    public Cursor queryNonIndexableKeys(String[] projection) {
        MatrixCursor cursor = new MatrixCursor(NON_INDEXABLES_KEYS_COLUMNS);
        return cursor;
    }
}
```

## 获取真实的数据

之前我们获取的并不是真实的数据，真实的数据则是动态获取的，首先，提供数据的是各个具体的页面

``` java
//书写的时候SEARCH_INDEX_DATA_PROVIDER是固定的，不能进行修改，否则不生效
public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider() {
  				//从xml中获取数据，到时会解析xml
                @Override
                public List<SearchIndexableResource> getXmlResourcesToIndex(Context context,
                        boolean enabled) {
                    ArrayList<SearchIndexableResource> result =
                            new ArrayList<SearchIndexableResource>();

                    SearchIndexableResource sir = new SearchIndexableResource(context);
                    sir.xmlResId = R.xml.display_settings;
                    result.add(sir);

                    return result;
                }
  				//获取单个Preference的数据
  				@Override
                public List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean 						enabled) {
                    return null;
                }

				//获取不加入搜索的数据
                @Override
                public List<String> getNonIndexableKeys(Context context) {
                    ArrayList<String> result = new ArrayList<String>();
                    if (!context.getResources().getBoolean(
                            com.android.internal.R.bool.config_dreamsSupported)) {
                        result.add(KEY_SCREEN_SAVER);
                    }
                    if (!isAutomaticBrightnessAvailable(context.getResources())) {
                        result.add(KEY_AUTO_BRIGHTNESS);
                    }
                    if (!NightDisplayController.isAvailable(context)) {
                        result.add(KEY_NIGHT_DISPLAY);
                    }
                    if (!isLiftToWakeAvailable(context)) {
                        result.add(KEY_LIFT_TO_WAKE);
                    }
                    if (!isDozeAvailable(context)) {
                        result.add(KEY_DOZE);
                    }
                    if (!RotationPolicy.isRotationLockToggleVisible(context)) {
                        result.add(KEY_AUTO_ROTATE);
                    }
                    if (!isTapToWakeAvailable(context.getResources())) {
                        result.add(KEY_TAP_TO_WAKE);
                    }
                    if (!isCameraGestureAvailable(context.getResources())) {
                        result.add(KEY_CAMERA_GESTURE);
                    }
                    if (!isVrDisplayModeAvailable(context)) {
                        result.add(KEY_VR_DISPLAY_PREF);
                    }
                    return result;
                }
            };
```



然后是动态的

``` java
public class Index {
  //这个String就是类
  private static final String FIELD_NAME_SEARCH_INDEX_DATA_PROVIDER =
            "SEARCH_INDEX_DATA_PROVIDER";
  
  
  public void update() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final Intent intent = new Intent(SearchIndexablesContract.PROVIDER_INTERFACE);
                List<ResolveInfo> list =
                        mContext.getPackageManager().queryIntentContentProviders(intent, 0);

                final int size = list.size();
                for (int n = 0; n < size; n++) {
                    final ResolveInfo info = list.get(n);
                    if (!isWellKnownProvider(info)) {
                        continue;
                    }
                    final String authority = info.providerInfo.authority;
                    final String packageName = info.providerInfo.packageName;

                    addIndexablesFromRemoteProvider(packageName, authority);
                    addNonIndexablesKeysFromRemoteProvider(packageName, authority);
                }

                mDataToProcess.fullIndex = true;
              	//更新Settings
                updateInternal();
            }
        });
    }
  
  private void indexOneResource(SQLiteDatabase database, String localeStr,
            SearchIndexableResource sir, Map<String, List<String>> nonIndexableKeysFromResource) {
		
        if (sir == null) {
            Log.e(LOG_TAG, "Cannot index a null resource!");
            return;
        }

        final List<String> nonIndexableKeys = new ArrayList<String>();

        if (sir.xmlResId > SearchIndexableResources.NO_DATA_RES_ID) {
            List<String> resNonIndxableKeys = nonIndexableKeysFromResource.get(sir.packageName);
            if (resNonIndxableKeys != null && resNonIndxableKeys.size() > 0) {
                nonIndexableKeys.addAll(resNonIndxableKeys);
            }

            indexFromResource(sir.context, database, localeStr,
                    sir.xmlResId, sir.className, sir.iconResId, sir.rank,
                    sir.intentAction, sir.intentTargetPackage, sir.intentTargetClass,
                    nonIndexableKeys);
        } else {
            if (TextUtils.isEmpty(sir.className)) {
                Log.w(LOG_TAG, "Cannot index an empty Search Provider name!");
                return;
            }

            final Class<?> clazz = getIndexableClass(sir.className);
            if (clazz == null) {
                Log.d(LOG_TAG, "SearchIndexableResource '" + sir.className +
                        "' should implement the " + Indexable.class.getName() + " interface!");
                return;
            }

            // Will be non null only for a Local provider implementing a
            // SEARCH_INDEX_DATA_PROVIDER field
            final Indexable.SearchIndexProvider provider = getSearchIndexProvider(clazz);
            if (provider != null) {
                List<String> providerNonIndexableKeys = provider.getNonIndexableKeys(sir.context);
                if (providerNonIndexableKeys != null && providerNonIndexableKeys.size() > 0) {
                    nonIndexableKeys.addAll(providerNonIndexableKeys);
                }

                indexFromProvider(mContext, database, localeStr, provider, sir.className,
                        sir.iconResId, sir.rank, sir.enabled, nonIndexableKeys);
            }
        }
    }
  
  private Indexable.SearchIndexProvider getSearchIndexProvider(final Class<?> clazz) {
        try {
            final Field f = clazz.getField(FIELD_NAME_SEARCH_INDEX_DATA_PROVIDER);
            return (Indexable.SearchIndexProvider) f.get(null);
        } catch (NoSuchFieldException e) {
            Log.d(LOG_TAG, "Cannot find field '" + FIELD_NAME_SEARCH_INDEX_DATA_PROVIDER + "'");
        } catch (SecurityException se) {
            Log.d(LOG_TAG,
                    "Security exception for field '" + FIELD_NAME_SEARCH_INDEX_DATA_PROVIDER + "'");
        } catch (IllegalAccessException e) {
            Log.d(LOG_TAG,
                    "Illegal access to field '" + FIELD_NAME_SEARCH_INDEX_DATA_PROVIDER + "'");
        } catch (IllegalArgumentException e) {
            Log.d(LOG_TAG,
                    "Illegal argument when accessing field '" +
                            FIELD_NAME_SEARCH_INDEX_DATA_PROVIDER + "'");
        }
        return null;
    }
}
```

