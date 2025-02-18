package net.pupskuchen.timecontrol.util;

import org.bukkit.World;

public final class TimeUtil {
    // https://minecraft.fandom.com/wiki/Daylight_cycle
    public static final TimeRange DAYTIME = new TimeRange(0, 11999);
    public static final TimeRange SUNSET = new TimeRange(12000, 12999);
    public static final TimeRange NIGHTTIME = new TimeRange(13000, 22999);
    public static final TimeRange SUNRISE = new TimeRange(23000, 23999);

    // https://minecraft.fandom.com/wiki/Bed#Sleeping
    public static final TimeRange SLEEP_ALLOWED_CLEAR = new TimeRange(12542, 23459);
    public static final TimeRange SLEEP_ALLOWED_RAIN = new TimeRange(12010, 23991);

    private TimeUtil() {}

    public static boolean sleepAllowed(final World world) {
        if (world.isThundering()) {
            return true;
        }

        final TimeRange range = world.hasStorm() ? SLEEP_ALLOWED_RAIN : SLEEP_ALLOWED_CLEAR;

        return range.isInRange(world.getTime());
    }

    public static int getWakeTime(final World world) {
        final int nightEnd = world.hasStorm() ? SLEEP_ALLOWED_RAIN.end : SLEEP_ALLOWED_CLEAR.end;

        return nightEnd + 1;
    }
}
