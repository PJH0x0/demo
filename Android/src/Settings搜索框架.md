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

