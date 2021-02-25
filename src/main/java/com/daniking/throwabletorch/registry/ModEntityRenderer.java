package com.daniking.throwabletorch.registry;

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class ModEntityRenderer {

    public static void EntityRenderer(){

        EntityRendererRegistry.INSTANCE.register(ModEntities.THROWABLE_TORCH_ENTITY, (dispatcher, context) ->
            new FlyingItemEntityRenderer<>(dispatcher, context.getItemRenderer()));
    ModEntityPacket.receiveEntityPacket();

        EntityRendererRegistry.INSTANCE.register(ModEntities.THROWABLE_SLIME_TORCH_ENTITY, (dispatcher, context) ->
                new FlyingItemEntityRenderer<>(dispatcher, context.getItemRenderer()));
        ModEntityPacket.receiveEntityPacket();


        EntityRendererRegistry.INSTANCE.register(ModEntities.THROWABLE_CLAY_TORCH_ENTITY, (dispatcher, context) ->
            new FlyingItemEntityRenderer<>(dispatcher, context.getItemRenderer()));
    ModEntityPacket.receiveEntityPacket();

        EntityRendererRegistry.INSTANCE.register(ModEntities.THROWABLE_MAGMA_TORCH_ENTITY, (dispatcher, context) ->
                new FlyingItemEntityRenderer<>(dispatcher, context.getItemRenderer()));
        ModEntityPacket.receiveEntityPacket();
    }
}

