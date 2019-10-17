package com.bitcoin.juwan.baselibrary;

import android.content.res.Resources;

/**
 * FileName：PluginItem
 * Create By：liumengqiang
 * Description：TODO
 */
public class PluginItem {
    private String dexPath;

    private ClassLoader classLoader;

    private Resources resources;

    public PluginItem(String dexPath, ClassLoader classLoader, Resources resources) {
        this.dexPath = dexPath;
        this.classLoader = classLoader;
        this.resources = resources;
    }

    public String getDexPath() {
        return dexPath;
    }

    public void setDexPath(String dexPath) {
        this.dexPath = dexPath;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
