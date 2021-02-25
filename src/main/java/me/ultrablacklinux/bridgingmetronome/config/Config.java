package me.ultrablacklinux.bridgingmetronome.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@me.shedaniel.autoconfig.annotation.Config(name = "assets/bridgingmetronome")
@me.shedaniel.autoconfig.annotation.Config.Gui.Background("minecraft:textures/block/iron_block.png")
public class Config extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("BridgingMetronome")
    @ConfigEntry.Gui.TransitiveObject
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public BridgingMetronome bridgingMetronome = new BridgingMetronome();


    public static void init() {
        AutoConfig.register(Config.class, PartitioningSerializer.wrap(GsonConfigSerializer::new));
    }

    public static Config get() {
        return AutoConfig.getConfigHolder(Config.class).getConfig();
    }


    @me.shedaniel.autoconfig.annotation.Config(name = "assets/bridgingmetronome")
    public static class BridgingMetronome implements ConfigData {

        @Comment("Click delay in ms")
        public int sleepTime = 500;

        @ConfigEntry.BoundedDiscrete(min = 0, max = 10)
        public int pitch = 0;

        @ConfigEntry.BoundedDiscrete(min = 0, max = 10)
        public int volume = 10;

        @ConfigEntry.BoundedDiscrete(min = 1, max = 50)
        public int stepSize = 10;

    }


}
