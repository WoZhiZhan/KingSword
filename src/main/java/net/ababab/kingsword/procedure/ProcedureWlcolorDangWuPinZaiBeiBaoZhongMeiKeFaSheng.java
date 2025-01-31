package net.ababab.kingsword.procedure;

import net.minecraft.item.ItemStack;

import net.ababab.kingsword.ElementsKingswordMod;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureWlcolorDangWuPinZaiBeiBaoZhongMeiKeFaSheng extends ElementsKingswordMod.ModElement {
	public ProcedureWlcolorDangWuPinZaiBeiBaoZhongMeiKeFaSheng(ElementsKingswordMod instance) {
		super(instance, 591);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure WlcolorDangWuPinZaiBeiBaoZhongMeiKeFaSheng!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
			((itemstack)).setStackDisplayName("\u7269\u7406\u5F69\u5B57");
			Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
				((itemstack)).setStackDisplayName("king\u7269\u7406\u5F69\u5B57");
				Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
					((itemstack)).setStackDisplayName("\u7269king\u7406\u5F69\u5B57");
					Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
						((itemstack)).setStackDisplayName("\u7269\u7406king\u5F69\u5B57");
						Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
							((itemstack)).setStackDisplayName("\u7269\u7406\u5F69king\u5B57");
							Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
								((itemstack)).setStackDisplayName("\u7269\u7406\u5F69\u5B57king");
								Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
									((itemstack)).setStackDisplayName("\u00A71\u7269\u7406\u5F69\u5B57");
									Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
										((itemstack)).setStackDisplayName("\u00A72\u7269\u7406\u5F69\u5B57");
										Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
											((itemstack)).setStackDisplayName("\u00A73\u7269\u7406\u5F69\u5B57");
											Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
												((itemstack)).setStackDisplayName("\u00A74\u7269\u7406\u5F69\u5B57");
												Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()).schedule(() -> {
													((itemstack)).setStackDisplayName("\u00A75\u7269\u7406\u5F69\u5B57");
												}, 300, TimeUnit.MILLISECONDS);
											}, 300, TimeUnit.MILLISECONDS);
										}, 300, TimeUnit.MILLISECONDS);
									}, 300, TimeUnit.MILLISECONDS);
								}, 300, TimeUnit.MILLISECONDS);
							}, 300, TimeUnit.MILLISECONDS);
						}, 300, TimeUnit.MILLISECONDS);
					}, 300, TimeUnit.MILLISECONDS);
				}, 300, TimeUnit.MILLISECONDS);
			}, 300, TimeUnit.MILLISECONDS);
		}, 300, TimeUnit.MILLISECONDS);
	}
}
