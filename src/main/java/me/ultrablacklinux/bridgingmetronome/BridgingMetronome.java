package me.ultrablacklinux.bridgingmetronome;

import me.ultrablacklinux.bridgingmetronome.config.Config;
import me.ultrablacklinux.bridgingmetronome.util.MetroThreadHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class BridgingMetronome implements ModInitializer {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
        Config.init();
        KeyBinding toggleMetronome = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.BridgingMetronome.toggle", GLFW.GLFW_KEY_M, "key.category.BridgingMetronome"));
        KeyBinding incrSpd = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.BridgingMetronome.incrspeed", GLFW.GLFW_KEY_K, "key.category.BridgingMetronome"));
        KeyBinding decrSpd = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.BridgingMetronome.decrspeed", GLFW.GLFW_KEY_L, "key.category.BridgingMetronome"));
        LOGGER.info("[BridgingMetronome] Started");

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            MetroThreadHelper mth = new MetroThreadHelper();
            if (toggleMetronome.wasPressed()) {
                mth.toggle();
            }
            else if (decrSpd.wasPressed()) {
                mth.changeDelay(true);
            }
            else if (incrSpd.wasPressed()) {
                mth.changeDelay(false);
            }
        });
    }
}
