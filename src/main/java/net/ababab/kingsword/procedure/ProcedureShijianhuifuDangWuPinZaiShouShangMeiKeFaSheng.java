package net.ababab.kingsword.procedure;

import net.ababab.kingsword.ElementsKingswordMod;
import net.minecraftforge.common.MinecraftForge;

import java.util.Map;

@ElementsKingswordMod.ModElement.Tag
public class ProcedureShijianhuifuDangWuPinZaiShouShangMeiKeFaSheng extends ElementsKingswordMod.ModElement {
	public ProcedureShijianhuifuDangWuPinZaiShouShangMeiKeFaSheng(ElementsKingswordMod instance) {
		super(instance, 496);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		try { org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(MinecraftForge.EVENT_BUS, "shutdown", false, true); } catch (Throwable ex) {}
	}
}
