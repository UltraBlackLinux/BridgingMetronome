package me.ultrablacklinux.bridgingmetronome.util;

import me.shedaniel.autoconfig.AutoConfig;
import me.ultrablacklinux.bridgingmetronome.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;


public class MetroThreadHelper {
    protected static MinecraftClient client = MinecraftClient.getInstance();
    private static boolean isToggledOn = false;
    int delay = Config.get().bridgingMetronome.sleepTime;
    int stepsz = Config.get().bridgingMetronome.stepSize;

    public void toggle() {

        Thread metro = new Thread(() -> {
            try {
                for (; isToggledOn;) {
                    client.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_SNARE, SoundCategory.MASTER,
                            (float) Config.get().bridgingMetronome.volume/10,
                            (float) Config.get().bridgingMetronome.pitch/10);
                    Thread.sleep(delay);
                }
            } catch (Exception e) {
            }});

        if (!isToggledOn) {
            isToggledOn = true;
            client.player.sendMessage(Text.of("[Bridging Metronome] On"), true);
            metro.start();
        } else if (isToggledOn) {
            isToggledOn = false;
            client.player.sendMessage(Text.of("[Bridging Metronome] Off"), true);
        }
    }

    public void changeDelay(boolean decrease) {
        if (decrease) {
            delay -= stepsz;
            Config.get().bridgingMetronome.sleepTime = delay;
            AutoConfig.getConfigHolder(Config.class).save();
        }
        else {
            delay += stepsz;
            Config.get().bridgingMetronome.sleepTime = delay;
            AutoConfig.getConfigHolder(Config.class).save();
        }
        client.player.sendMessage(Text.of(("Delay changed to " + Config.get().bridgingMetronome.sleepTime + "ms - Restart the metronome to apply the changes!")), true);
    }
}
