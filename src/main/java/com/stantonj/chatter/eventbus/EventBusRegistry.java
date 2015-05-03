package com.stantonj.chatter.eventbus;

import java.util.concurrent.ConcurrentHashMap;

public class EventBusRegistry {

    private static ConcurrentHashMap<String, Class<? extends EventBus>> busTypes = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, EventBus> buses = new ConcurrentHashMap<>();
    private static String DefaultBusType = null;


    public static void RegisterEventBusType(Class<? extends EventBus> type) {
        busTypes.putIfAbsent(type.getName(), type);
        if (DefaultBusType == null)
            DefaultBusType = type.getTypeName();
    }

    public static EventBus GetOrCreateBus(String id) {
        return GetOrCreateBus(id, DefaultBusType);
    }

    public static EventBus GetOrCreateBus(String id, String type) {
        return GetOrCreateBus(id, busTypes.get(type));
    }

    public static EventBus GetOrCreateBus(String id, Class<? extends EventBus> type) {
        assert id != null && !id.isEmpty() && type != null;

        if (!buses.containsKey(id))
            try {
                buses.putIfAbsent(id, type.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }

        return buses.get(id);
    }
}
