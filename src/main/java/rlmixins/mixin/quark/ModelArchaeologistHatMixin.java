package rlmixins.mixin.quark;

import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.arl.item.ModelModArmor;
import vazkii.quark.world.client.model.ModelArchaeologistHat;

@Mixin(ModelArchaeologistHat.class)
public abstract class ModelArchaeologistHatMixin extends ModelModArmor {

    /*
     *   Moving setters to constructor seems to solve most rendering issues
     */

    @Shadow(remap = false)
    @Final
    public ModelRenderer hat;

    @Inject(
            method = "<init>",
            at = @At("RETURN"),
            remap = true
    )
    private void rlmixins_quarkModelArchaeologistHat_init(CallbackInfo ci){
        this.bipedHead.showModel = false;
        this.bipedHeadwear = this.hat;
        this.bipedHead = this.hat;
    }

    @Inject(
            method = "setModelParts",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void rlmixins_quarkModelArchaeologistHat_setModelParts(CallbackInfo ci){
        ci.cancel();
    }
}
