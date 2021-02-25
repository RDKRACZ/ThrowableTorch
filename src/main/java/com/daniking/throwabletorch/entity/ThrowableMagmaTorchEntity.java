package com.daniking.throwabletorch.entity;

import com.daniking.throwabletorch.registry.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

/**
 * Copyright 2021 DaniK1ngRD
 */

public class ThrowableMagmaTorchEntity extends ThrowableTorchEntity{

    public ThrowableMagmaTorchEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(ModEntities.THROWABLE_MAGMA_TORCH_ENTITY, world);
    }

    public ThrowableMagmaTorchEntity(World world, PlayerEntity user) {
        super(world, user);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult)
    {
        super.onEntityHit(entityHitResult);
        entityHitResult.getEntity().damage(DamageSource.thrownProjectile(this, this.getOwner()), 2F);
        entityHitResult.getEntity().setOnFireFor(5);
        this.remove();

    }
}
