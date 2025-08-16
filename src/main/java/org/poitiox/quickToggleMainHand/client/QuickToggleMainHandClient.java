package org.poitiox.quickToggleMainHand.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Arm;
import org.lwjgl.glfw.GLFW;

public class QuickToggleMainHandClient implements ClientModInitializer {

    private static  KeyBinding keyX;

    @Override
    public void onInitializeClient() {

        keyX = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.quick.mainhand", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_X, // The keycode of the key
                "category.quick.mainhand" // The translation key of the keybinding's category.
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyX.wasPressed()) {
                toggleMainHand(client);
            }
        });

    }

    private void toggleMainHand(MinecraftClient client) {
        assert client.player != null;
        Arm currentArm = client.options.getMainArm().getValue();
        client.options.getMainArm().setValue(currentArm == Arm.LEFT ? Arm.RIGHT : Arm.LEFT);
        client.options.write();
    }


}
