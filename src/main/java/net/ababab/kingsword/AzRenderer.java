package net.ababab.kingsword;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AzRenderer
  extends Render<Entity>
{
  public AzRenderer()
  {
    super(Minecraft.getMinecraft().getRenderManager());
  }
  
  public boolean shouldRender(Entity entity, ICamera camera, double camX, double camY, double camZ)
  {
    return false;
  }
  
  public void doRender(Entity entity, double d1, double d2, double d3, float f1, float f2) {}
  
  protected ResourceLocation getEntityTexture(Entity entity)
  {
    return new ResourceLocation("");
  }
  
  public void doRenderShadowAndFire(Entity entity, double d1, double d2, double d3, float f1, float f2) {}
}
