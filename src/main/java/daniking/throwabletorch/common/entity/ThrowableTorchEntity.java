package daniking.throwabletorch.common.entity;

import daniking.throwabletorch.common.network.packet.EntitySpawnPacket;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public abstract class ThrowableTorchEntity extends ThrownItemEntity {

    public ThrowableTorchEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrowableTorchEntity(EntityType<? extends ThrownItemEntity> entityType, World world, LivingEntity owner) {
        super(entityType, owner, world);
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return EntitySpawnPacket.create(this);
    }

    @Override
    protected abstract Item getDefaultItem();

    boolean hasHit = false;

    protected void onBlockHit(BlockHitResult hit) {

        if (!this.world.isClient) {

            Direction direction = hit.getSide();
            BlockPos placeAt = hit.getBlockPos().offset(direction);
            Block placeTorch = Blocks.TORCH;
            Block wallTorch = Blocks.WALL_TORCH;
            BlockState wallTorchState = wallTorch.getDefaultState();

            if (placeAt == null) {
                this.discard();
                return;
            }

            boolean isPlaceableUp = Blocks.TORCH.canPlaceAt(placeTorch.getDefaultState(), this.world, placeAt);
            boolean isPlaceableNorth = Blocks.WALL_TORCH.canPlaceAt(wallTorchState.with(WallTorchBlock.FACING, Direction.NORTH), this.world, placeAt);
            boolean isPlaceableSouth = Blocks.WALL_TORCH.canPlaceAt(wallTorchState.with(WallTorchBlock.FACING, Direction.SOUTH), this.world, placeAt);
            boolean isPlaceableEast = Blocks.WALL_TORCH.canPlaceAt(wallTorchState.with(WallTorchBlock.FACING, Direction.EAST), this.world, placeAt);
            boolean isPlaceableWest = Blocks.WALL_TORCH.canPlaceAt(wallTorchState.with(WallTorchBlock.FACING, Direction.WEST), this.world, placeAt);

            //At least it works..

            if (!hasHit) {
                if (world.getBlockState(placeAt).isAir()) {
                    if (direction == Direction.UP && isPlaceableUp) {
                        world.setBlockState(placeAt, placeTorch.getDefaultState());
                        hasHit = true;
                    } else if (direction == Direction.DOWN) {
                        spawnTorch();
                        hasHit = true;
                    } else if (direction == Direction.NORTH && isPlaceableNorth) {
                        world.setBlockState(placeAt, wallTorchState.with(WallTorchBlock.FACING, Direction.NORTH));
                        hasHit = true;
                    } else if (direction == Direction.SOUTH && isPlaceableSouth) {
                        world.setBlockState(placeAt, wallTorchState.with(WallTorchBlock.FACING, Direction.SOUTH));
                        hasHit = true;
                    } else if (direction == Direction.EAST && isPlaceableEast) {
                        world.setBlockState(placeAt, wallTorchState.with(WallTorchBlock.FACING, Direction.EAST));
                        hasHit = true;
                    } else if (direction == Direction.WEST && isPlaceableWest) {
                        world.setBlockState(placeAt, wallTorchState.with(WallTorchBlock.FACING, Direction.WEST));
                        hasHit = true;
                    }
                } else {
                    spawnTorch();
                    hasHit = true;
                }
            }
            this.discard();
        }
    }


    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        spawnTorch();
        entityHitResult.getEntity().damage(DamageSource.thrownProjectile(this, this.getOwner()), 1F);
        this.discard();
    }

    private void spawnTorch() {
        world.spawnEntity(new ItemEntity(this.world, this.getX(), this.getY(), this.getZ(), new ItemStack(Blocks.TORCH)));
    }
}