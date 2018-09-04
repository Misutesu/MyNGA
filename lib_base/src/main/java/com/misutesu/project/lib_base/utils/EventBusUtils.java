package com.misutesu.project.lib_base.utils;

import org.greenrobot.eventbus.EventBus;

public class EventBusUtils {
    public static void bind(Object o) {
        if (!EventBus.getDefault().isRegistered(o)) {
            EventBus.getDefault().register(o);
        }
    }

    public static void unBind(Object o) {
        if (EventBus.getDefault().isRegistered(o)) {
            EventBus.getDefault().unregister(o);
        }
    }
}
