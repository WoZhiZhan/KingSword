package org.lwjgl.opengl;

import java.nio.IntBuffer;

public class GLHelper {
    public static void nglPushMatrix(long function_pointer) {}
    public static void nglClear(int mask, long function_pointer) {}
    public static void nglBindFramebuffer(int target, int framebuffer, long function_pointer) {}
    public static void nglViewport(int x, int y, int width, int height, long function_pointer) {}
    public static void nglPopMatrix(long function_pointer) {}
    public static void nglColorMask(boolean red, boolean green, boolean blue, boolean alpha, long function_pointer) {}
    public static void nglEnable(int caps, long function_pointer) {}
    public static void nglDisable(int caps, long function_pointer) {}
    public static void nglDepthMask(boolean flag, long function_pointer) {}
    public static void nglMatrixMode(int mode, long function_pointer) {}
    public static void nglLoadIdentity(long function_pointer) {}
    public static void nglOrtho(double left, double right, double bottom, double top, double zNear, double zFar, long function_pointer) {}
    public static void nglTranslatef(float x, float y, float z, long function_pointer) {}
    public static void nglColor4f(float colorRed, float colorGreen, float colorBlue, float colorAlpha, long function_pointer) {}
    public static void nglGenFramebuffers(int framebuffers_n, long framebuffers, long function_pointer) {}
    public static void nglGenTextures(int textures_n, long textures, long function_pointer) {}
    public static void nglGenRenderbuffers(int renderbuffers_n, long renderbuffers, long function_pointer) {}
    public static void nglBindTexture(int target, int texture, long function_pointer) {}
    public static void nglTexParameteri(int target, int pname, int param, long function_pointer) {}
    public static void nglTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, long pixels, long function_pointer) {}
    public static void nglFramebufferTexture2D(int target, int attachment, int textarget, int texture, int level, long function_pointer) {}
    public static void nglBindRenderbuffer(int target, int renderbuffer, long function_pointer) {}
    public static void nglRenderbufferStorage(int target, int internalformat, int width, int height, long function_pointer) {}
    public static void nglFramebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer, long function_pointer) {}
    public static void nglClearColor(float red, float green, float blue, float alpha, long function_pointer) {}
    public static void nglClearDepth(double depth, long function_pointer) {}
    public static void nglVertexPointer(int size, int type, int stride, long pointer, long function_pointer) {}
    public static void nglEnableClientState(int cap, long function_pointer) {}
    public static void nglNormalPointer(int type, int stride, long pointer, long function_pointer) {}
    public static void nglColorPointer(int size, int type, int stride, long pointer, long function_pointer) {}
    public static void nglClientActiveTexture(int texture, long function_pointer) {}
    public static void nglTexCoordPointer(int size, int type, int stride, long pointer, long function_pointer) {}
    public static void nglEnableVertexAttribArray(int index, long function_pointer) {}
    public static void nglVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long buffer, long function_pointer) {}
    public static void nglDisableClientState(int cap, long function_pointer) {}
    public static void nglDisableVertexAttribArray(int index, long function_pointer) {}
    public static void nglDrawArrays(int mode, int first, int count, long function_pointer) {}
    public static void nglBlendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha, long function_pointer) {}
    public static void nglShadeModel(int mode, long function_pointer) {}
    public static void nglScalef(float x, float y, float z, long function_pointer) {}
    public static void nglTexParameterf(int target, int pname, float param, long function_pointer) {}
    public static void nglTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, long pixels, long function_pointer) {}
    public static void nglBegin(int mode, long function_pointer) {}
    public static void nglTexCoord2f(float s, float t, long function_pointer) {}
    public static void nglVertex3f(float x, float y, float z, long function_pointer) {}
    public static void nglEnd(long function_pointer) {}
    public static void nglBlendFunc(int sfactor, int dfactor, long function_pointer) {}
    public static IntBuffer getBufferInt(ContextCapabilities ctxcap) {return null;}
    public static void uploadRender() throws Exception {}
}
