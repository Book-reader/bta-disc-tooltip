package bookreader.disctooltip.mixin;

import net.minecraft.client.gui.GuiTooltip;
import net.minecraft.core.item.ItemRecord;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;


@Mixin(value = GuiTooltip.class, remap = false)
public class DiscTooltipMixin {

	@Inject(method = "getTooltipText(Lnet/minecraft/core/item/ItemStack;ZLnet/minecraft/core/player/inventory/slot/Slot;)Ljava/lang/String;", at = @At(value = "TAIL", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
	public void injectTooltip(ItemStack itemStack, boolean showDescription, Slot slot, CallbackInfoReturnable<String> cir, I18n trans, StringBuilder text) {
		if (itemStack.getItem() instanceof ItemRecord) {
			ItemRecord record = (ItemRecord) itemStack.getItem();
			text.append("\n").append(record.recordAuthor).append(" - ").append(record.recordName);
		}
	}
}
