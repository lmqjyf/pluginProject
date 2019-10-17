package com.bitcoin.juwan.hostappproject;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.bitcoin.juwan.baselibrary.PluginManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName：BaseApplication
 * Create By：liumengqiang
 * Description：TODO
 */
public class BaseApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        addPlugin();

    }

    private void addPlugin() {
        String path = "/sdcard/app-debug.apk";
        //获取私有目录缓存
        File cacheDir = getCacheDir();
        try {
            //获取PathListField
            Field pathListField = RefInvoke.getField(getClassLoader(), "pathList");
            //获取PathList对象
            Object pathList = pathListField.get(getClassLoader());
            //获取PathList对象中的Elements数组
            Field dexElementField = RefInvoke.getField(pathList, "dexElements");
            Object[] dexElements = (Object[]) dexElementField.get(pathList);
            //新建Plugin的Element数组
            ArrayList<File> files = new ArrayList<>();
            files.add(new File(path));
            Method makePathElementsMethod = RefInvoke.getMethod(pathList, "makePathElements", List.class, File.class, List.class);;
            Object[] pluginDexElements = (Object[]) makePathElementsMethod.invoke(null, files, cacheDir, new ArrayList<IOException>());
            //创建新数组
            Object[] replaceDexElements = (Object[]) Array.newInstance(dexElements.getClass().getComponentType(), dexElements.length + pluginDexElements.length);
            //拷贝新数组和老数组到replaceDexElements中
            System.arraycopy(pluginDexElements, 0, replaceDexElements, 0, pluginDexElements.length);
            System.arraycopy(dexElements, 0, replaceDexElements, pluginDexElements.length, dexElements.length);
            //替换PathList中的Element数组对象
            RefInvoke.setFieldObject(pathList, "dexElements", replaceDexElements);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
