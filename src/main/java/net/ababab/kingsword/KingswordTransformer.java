package net.ababab.kingsword;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.*;

public class KingswordTransformer implements IClassTransformer {
  public byte[] transform(String name, String transformedName, byte[] basicClass) {
    final String notchName = name;
    if ((name.equals("blk")) || (name.equals("net.minecraft.client.gui.GuiScreen"))) {
      ClassReader classReader = new ClassReader(basicClass);
      ClassWriter classWriter = new ClassWriter(classReader, 2);
      ClassVisitor classVisitor = new ClassVisitor(262144, classWriter) {
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
          if (((name.equals("drawScreen")) || (name.equals("func_73863_a")) || (name.equals("a"))) && (desc.equals("(IIF)V"))) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            mv.visitCode();
            mv.visitVarInsn(25, 0);
            mv.visitVarInsn(21, 1);
            mv.visitVarInsn(21, 2);
            mv.visitVarInsn(23, 3);
            mv.visitMethodInsn(184, "net/ababab/kingsword/EventUtil", "drawScreen", "(Lnet/minecraft/client/gui/GuiScreen;IIF)V", false);
            mv.visitInsn(177);
            mv.visitMaxs(4, 4);
            mv.visitEnd();
            return null;
          }
          return super.visitMethod(access, name, desc, signature, exceptions);
        }

        public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
          if ((name.equals("buttonList")) || (name.equals("field_146292_n")) || (name.equals("n")) || (name.equals("labelList")) || (name.equals("field_146293_o")) || (name.equals("o"))) {
            access = 1;
          }
          return super.visitField(access, name, desc, signature, value);
        }
      };
      classReader.accept(classVisitor, 262144);
      return classWriter.toByteArray();
    }
    if ((name.equals("aec")) || (name.equals("net.minecraft.entity.player.InventoryPlayer")))
    {
      ClassReader classReader = new ClassReader(basicClass);
      ClassWriter classWriter = new ClassWriter(classReader, 2);
      ClassVisitor classVisitor = new ClassVisitor(262144, classWriter)
      {
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
        {
          if (((name.equals("dropAllItems")) || (name.equals("o"))) && (desc.equals("()V")))
          {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            mv.visitCode();
            mv.visitVarInsn(25, 0);
            mv.visitMethodInsn(184, "net/ababab/kingsword/EventUtil", "dropAllItems", "(Lnet/minecraft/entity/player/InventoryPlayer;)V", false);
            mv.visitInsn(177);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
            return null;
          }
          if (((name.equals("removeStackFromSlot")) || (name.equals("c_"))) && (desc.equals("(I)Lnet/minecraft/item/ItemStack;")))
          {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            mv.visitCode();
            mv.visitVarInsn(25, 0);
            mv.visitVarInsn(21, 1);
            mv.visitMethodInsn(184, "net/ababab/kingsword/EventUtil", "removeStackFromSlot", "(Lnet/minecraft/entity/player/InventoryPlayer;I)Lnet/minecraft/item/ItemStack;", false);
            mv.visitInsn(176);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
            return null;
          }
          if (((name.equals("clearMatchingItems")) || (name.equals("a"))) && (desc.equals("(Lnet/minecraft/item/Item;IILnet/minecraft/nbt/NBTTagCompound;)I")))
          {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            mv.visitCode();
            mv.visitVarInsn(25, 0);
            mv.visitVarInsn(25, 1);
            mv.visitVarInsn(21, 2);
            mv.visitVarInsn(21, 3);
            mv.visitVarInsn(25, 4);
            mv.visitMethodInsn(184, "net/ababab/kingsword/EventUtil", "clearMatchingItems", "(Lnet/minecraft/entity/player/InventoryPlayer;Lnet/minecraft/item/Item;IILnet/minecraft/nbt/NBTTagCompound;)I", false);
            mv.visitInsn(172);
            mv.visitMaxs(5, 5);
            mv.visitEnd();
            return null;
          }
          return super.visitMethod(access, name, desc, signature, exceptions);
        }

        public FieldVisitor visitField(int access, String name, String desc, String signature, Object value)
        {
          if ((name.equals("allInventories")) || (name.equals("f"))) {
            access = 17;
          }
          if ((name.equals("itemStack")) || (name.equals("g"))) {
            access = 1;
          }
          return super.visitField(access, name, desc, signature, value);
        }
      };
      classReader.accept(classVisitor, 262144);
      return classWriter.toByteArray();
    }
    if (name.equals("net.minecraftforge.fml.common.eventhandler.EventBus")) {
      ClassReader classReader = new ClassReader(basicClass);
      ClassWriter classWriter = new ClassWriter(classReader, 2);
      ClassVisitor classVisitor = new ClassVisitor(262144, classWriter) {
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
          if (name.equals("post")) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            mv.visitCode();
            mv.visitVarInsn(25, 0);
            mv.visitVarInsn(25, 1);
            mv.visitMethodInsn(184, "net/ababab/kingsword/EventUtil", "post", "(Lnet/minecraftforge/fml/common/eventhandler/EventBus;Lnet/minecraftforge/fml/common/eventhandler/Event;)Z", false);
            mv.visitInsn(172);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
            return null;
          }
          return super.visitMethod(access, name, desc, signature, exceptions);
        }

        public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
          if (access == 2) {
            access = 1;
          }
          if (access == 18) {
            access = 17;
          }
          return super.visitField(access, name, desc, signature, value);
        }
      };
    }
    if ("net.minecraft.entity.player.EntityPlayer".equals(transformedName)) {
      ClassReader reader = new ClassReader(basicClass);
      ClassWriter writer = new ClassWriter(reader, 1);
      ClassVisitor visitor = new ClassVisitor(327680, writer) {
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
          String srgName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(notchName, name, desc);
          if (("closeScreen".equals(srgName)) || (("func_71053_j".equals(srgName)) && ("()V".equals(desc)))) {
            MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
            mv.visitCode();
            Label start = new Label();
            mv.visitLabel(start);
            mv.visitVarInsn(25, 0);
            mv.visitMethodInsn(184, "net/ababab/kingsword/EventUtil", "closeScreen", "(Lnet/minecraft/entity/player/EntityPlayer;)V", false);
            mv.visitInsn(177);
            Label end = new Label();
            mv.visitLabel(end);
            mv.visitLocalVariable("this", "Lnet/minecraft/entity/player/EntityPlayer;", null, start, end, 0);
            mv.visitEnd();
          }
          return null;
        }
      };
    }
    if ((name.equals("bib")) || (name.equals("net.minecraft.client.Minecraft"))) {
      ClassReader classReader = new ClassReader(basicClass);
      ClassWriter classWriter = new ClassWriter(classReader, 2);
      ClassVisitor classVisitor = new ClassVisitor(262144, classWriter) {
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
          if (((name.equals("createDisplay")) || (name.equals("func_175609_am")) || (name.equals("at"))) && (desc.equals("()V"))) {
            new MethodVisitor(262144, super.visitMethod(access, name, desc, signature, exceptions)) {
              public void visitLdcInsn(Object minecraft) {
                //if (o.equals("Minecraft 1.12.2")) {
                minecraft = "Minecraft 1.12.2 King\u306eSword 1.7.6";
                //}
                super.visitLdcInsn(minecraft);
              }
            };
          }
          return super.visitMethod(access, name, desc, signature, exceptions);
        }
      };
    }
      /*if (name.equals("net.minecraftforge.common.ForgeHooks"))
      {
        ClassReader classReader = new ClassReader(basicClass);
        ClassWriter classWriter = new ClassWriter(classReader, 2);
        ClassVisitor classVisitor = new ClassVisitor(262144, classWriter)
        {
          public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
          {
            if (name.equals("onLivingUpdate"))
            {
              MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
              mv.visitCode();
              mv.visitVarInsn(25, 0);
              mv.visitMethodInsn(184, "net/ababab/kingsword/EventUtil", "onLivingUpdate", "(Lnet/minecraft/entity/EntityLivingBase;)Z", false);
              mv.visitInsn(172);
              mv.visitMaxs(1, 1);
              mv.visitEnd();
              return null;
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
          }
        };
        classReader.accept(classVisitor, 262144);
        return classWriter.toByteArray();
      }*/
    if ("net.minecraft.client.Minecraft".equals(transformedName))
    {
      ClassReader reader = new ClassReader(basicClass);
      ClassWriter writer = new ClassWriter(reader, 1);
      ClassVisitor visitor = new ClassVisitor(327680, writer)
      {
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
        {
          String srgName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(notchName, name, desc);
          if (("runGameLoop".equals(srgName)) || ("func_71411_J".equals(srgName))) {
            return new MethodVisitor(327680, this.cv.visitMethod(access, name, desc, signature, exceptions)) {
              public void visitCode() {
                super.visitCode();
                this.mv.visitVarInsn(25, 0);
                this.mv.visitMethodInsn(184, "net/ababab/kingsword/EventUtil", "onRunGameLoopStart", "(Lnet/minecraft/client/Minecraft;)V", false);
              }
            };
          }
          /*if (((name.equals("runTick")) || (name.equals("func_71407_l")) || (name.equals("t"))) && (desc.equals("()V"))) {
            new MethodVisitor(262144, super.visitMethod(access, name, desc, signature, exceptions))
            {
              public void visitCode()
              {
                super.visitCode();
                this.mv.visitVarInsn(25, 0);
                this.mv.visitMethodInsn(184, "net/ababab/kingsword/EventUtil", "runTick", "(Lnet/minecraft/client/Minecraft;)V", false);
              }
            };
          }*/
          return super.visitMethod(access, name, desc, signature, exceptions);
        }

        @Override
        public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
          if (access == Opcodes.ACC_PRIVATE) access = Opcodes.ACC_PUBLIC;
          if (access == Opcodes.ACC_PRIVATE + Opcodes.ACC_FINAL) access = Opcodes.ACC_PUBLIC;
          return super.visitField(access, name, desc, signature, value);
        }
      };
      reader.accept(visitor, 327680);
      return writer.toByteArray();
    }
      if ("net.minecraft.entity.EntityLivingBase".equals(transformedName)) {
        ClassReader reader = new ClassReader(basicClass);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
        ClassVisitor visitor = new ClassVisitor(Opcodes.ASM5, writer) {
          @Override
          public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            String srgName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(notchName, name, desc);
            if ("getHealth".equals(srgName) || "func_110143_aJ".equals(srgName) && "()F".equals(desc)) {
              MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
              mv.visitCode();
              Label start = new Label();
              mv.visitLabel(start);
              mv.visitVarInsn(Opcodes.ALOAD, 0);
              mv.visitMethodInsn(Opcodes.INVOKESTATIC, "net/ababab/kingsword/EventUtil", "getHealth", "(Lnet/minecraft/entity/EntityLivingBase;)F", false);
              mv.visitInsn(Opcodes.FRETURN);
              Label end = new Label();
              mv.visitLabel(end);
              mv.visitLocalVariable("this", "Lnet/minecraft/entity/EntityLivingBase;", null, start, end, 0);
              mv.visitEnd();
              return null;
            } else if ("isEntityAlive".equals(srgName) || "func_70089_S".equals(srgName) && "()Z".equals(desc)) {
              MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
              mv.visitCode();
              Label start = new Label();
              mv.visitLabel(start);
              mv.visitVarInsn(Opcodes.ALOAD, 0);
              mv.visitMethodInsn(Opcodes.INVOKESTATIC, "net/ababab/kingsword/EventUtil", "isEntityAlive", "(Lnet/minecraft/entity/EntityLivingBase;)Z", false);
              mv.visitInsn(Opcodes.IRETURN);
              Label end = new Label();
              mv.visitLabel(end);
              mv.visitLocalVariable("this", "Lnet/minecraft/entity/EntityLivingBase;", null, start, end, 0);
              mv.visitEnd();
              return null;
            }
            return cv.visitMethod(access, name, desc, signature, exceptions);
          }
        };
        reader.accept(visitor, Opcodes.ASM5);
        return writer.toByteArray();
      }
    return basicClass;
  }
}