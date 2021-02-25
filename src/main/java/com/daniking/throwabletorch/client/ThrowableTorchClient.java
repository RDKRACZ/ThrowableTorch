package com.daniking.throwabletorch.client;

import com.daniking.throwabletorch.ThrowableTorch;

import com.daniking.throwabletorch.registry.ModEntityPacket;
import com.daniking.throwabletorch.registry.ModEntityRenderer;
import net.fabricmc.api.ClientModInitializer;

import net.minecraft.util.Identifier;



public class ThrowableTorchClient implements ClientModInitializer
{

    public static final Identifier PacketID = new Identifier(ThrowableTorch.MODID, "throwable_torch");

    @Override
    public void onInitializeClient() {
        ModEntityPacket.receiveEntityPacket();
        ModEntityRenderer.EntityRenderer();
    }

}

