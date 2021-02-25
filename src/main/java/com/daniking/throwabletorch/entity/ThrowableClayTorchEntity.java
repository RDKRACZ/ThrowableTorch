package com.daniking.throwabletorch.entity;

import com.daniking.throwabletorch.registry.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.world.World;

/**
 * Copyright 2021 DaniK1ngRD
 */

public class ThrowableClayTorchEntity extends ThrowableTorchEntity
{

    public ThrowableClayTorchEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(ModEntities.THROWABLE_CLAY_TORCH_ENTITY, world);
    }
}
