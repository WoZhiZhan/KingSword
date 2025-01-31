package org.lwjgl.opengl;

import org.lwjgl.BufferChecks;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.MemoryUtil;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Objects;

public class DraftGL {

    public static void glDrawArrays(int mode, int first, int count) {
        long function_pointer = DefaultGL.glDrawArrays;
        GL11.nglDrawArrays(mode, first, count, function_pointer);
    }



    public static void glEnd() {
        ContextCapabilities caps = DefaultGL.caps;
        long function_pointer = DefaultGL.glEnd;
        if ( ContextCapabilities.DEBUG ) StateTracker.setBeginEnd(caps, false);
        GL11.nglEnd(function_pointer);
    }

    public static void glTexCoord2f(float s, float t) {
        ContextCapabilities caps = DefaultGL.caps;
        long function_pointer = DefaultGL.glTexCoord2f;
        GL11.nglTexCoord2f(s, t, function_pointer);
    }

    public static void glBegin(int mode) {
        ContextCapabilities caps = DefaultGL.caps;
        long function_pointer = DefaultGL.glBegin;
        if ( ContextCapabilities.DEBUG ) StateTracker.setBeginEnd(caps, true);
        GL11.nglBegin(mode, function_pointer);
    }

    public static void glVertex3f(float x, float y, float z) {
        long function_pointer = DefaultGL.glVertex3f;
        GL11.nglVertex3f(x, y, z, function_pointer);
    }


    public static void glVertexPointer(int size, int type, int stride, ByteBuffer pointer) {
        ContextCapabilities caps = DefaultGL.caps;
        long function_pointer = DefaultGL.glVertexPointer;
        if ( LWJGLUtil.CHECKS ) StateTracker.getReferences(caps).GL11_glVertexPointer_pointer = pointer;
        GL11.nglVertexPointer(size, type, stride, MemoryUtil.getAddress(pointer), function_pointer);
    }

    public static int glGenTextures() {
        long function_pointer = DefaultGL.glGenTextures;
        IntBuffer textures = APIUtil.getBufferInt(DefaultGL.caps);
        GL11.nglGenTextures(1, MemoryUtil.getAddress(textures), function_pointer);
        return textures.get(0);
    }

    public static void nglBindFramebufferEXT(int target, int framebuffer) {
        long function_pointer = DefaultGL.glBindFramebufferEXT;
        EXTFramebufferObject.nglBindFramebufferEXT(target, framebuffer, function_pointer);
    }

    public static int glGenFramebuffers() {
        long function_pointer = DefaultGL.glGenFramebuffers;
        IntBuffer framebuffers = APIUtil.getBufferInt(DefaultGL.caps);
        GL30.nglGenFramebuffers(1, MemoryUtil.getAddress(framebuffers), function_pointer);
        return framebuffers.get(0);
    }

    public static int glGenFramebuffersEXT() {
        long function_pointer = DefaultGL.glGenFramebuffersEXT;
        IntBuffer framebuffers = APIUtil.getBufferInt(DefaultGL.caps);
        EXTFramebufferObject.nglGenFramebuffersEXT(1, MemoryUtil.getAddress(framebuffers), function_pointer);
        return framebuffers.get(0);
    }

    public static void glClientActiveTextureARB(int texture) {
        long function_pointer = DefaultGL.glClientActiveTextureARB;
        ARBMultitexture.nglClientActiveTextureARB(texture, function_pointer);
    }


    public static void nglEnable(int cap) {
        long function_pointer = DefaultGL.glEnable;
        GL11.nglEnable(cap, function_pointer);
    }

    public static void nglDisable(int cap) {
        long function_pointer = DefaultGL.glDisable;
        GL11.nglDisable(cap, function_pointer);
    }

    public static void glShadeModel(int mode) {
        long function_pointer = DefaultGL.glShadeModel;
        GL11.nglShadeModel(mode, function_pointer);
    }

    public static void ortho(double left, double right, double bottom, double top, double zNear, double zFar) {
        long function_pointer = DefaultGL.glOrtho;
        GL11.nglOrtho(left, right, bottom, top, zNear, zFar, function_pointer);
    }

    public static void nglClear(int mask) {
        long function_pointer = DefaultGL.glClear;
        GL11.nglClear(mask, function_pointer);
    }

    public static void glBindFramebuffer(int target, int framebufferIn) {
        nglBindFramebuffer(target, framebufferIn);
    }

    public static void nglPopMatrix() {
        long function_pointer = DefaultGL.glPopMatrix;
        GL11.nglPopMatrix(function_pointer);
    }

    public static void nglPushMatrix() {
        long function_pointer = DefaultGL.glPushMatrix;
        GL11.nglPushMatrix(function_pointer);
    }

    public static void nglBindFramebuffer(int target, int framebufferIn) {
        long function_pointer = DefaultGL.glBindFramebuffer;
        GL30.nglBindFramebuffer(target, framebufferIn, function_pointer);
    }

    public static void setState(int cap) {
        long function_pointer = DefaultGL.glDisable;
        GL11.nglDisable(cap, function_pointer);
    }

    public static void translate(float x, float y, float z) {
        long function_pointer = DefaultGL.glTranslatef;
        GL11.nglTranslatef(x, y, z, function_pointer);
    }

    public static void color(float red, float green, float blue, float alpha) {
        long function_pointer = DefaultGL.glColor4f;
        GL11.nglColor4f(red, green, blue, alpha, function_pointer);
    }

    public static void colorMask(boolean red, boolean green, boolean blue, boolean alpha) {
        long function_pointer = DefaultGL.glColorMask;
        GL11.nglColorMask(red, green, blue, alpha, function_pointer);
    }

    public static void depthMask(boolean flagIn) {
        long function_pointer = DefaultGL.glDepthMask;
        GL11.nglDepthMask(flagIn, function_pointer);
    }

    public static void popMatrix() {
        long function_pointer = DefaultGL.glPopMatrix;
        GL11.nglPopMatrix(function_pointer);
    }

    public static void shadeModel(int mode) {
        long function_pointer = DefaultGL.glShadeModel;
        GL11.nglShadeModel(mode, function_pointer);
    }

    public static void loadIdentity() {
        long function_pointer = DefaultGL.glLoadIdentity;
        GL11.nglLoadIdentity(function_pointer);
    }

    public static void clear(int mask) {
        long function_pointer = DefaultGL.glClear;
        GL11.nglClear(mask, function_pointer);
    }

    public static void bindTexture(int texture) {
        long function_pointer = DefaultGL.glBindTexture;
        GL11.nglBindTexture(3553, texture, function_pointer);
    }

    public static void glTexParameteri(int target, int parameterName, int parameter) {
        long function_pointer = DefaultGL.glTexParameteri;
        GL11.nglTexParameteri(target, parameterName, parameter, function_pointer);
    }

    public static void glPushMatrix() {
        long function_pointer = DefaultGL.glPushMatrix;
        GL11.nglPushMatrix(function_pointer);
    }

    public static void glScalef(float x, float y, float z) {
        long function_pointer = DefaultGL.glScalef;
        GL11.nglScalef(x, y, z, function_pointer);
    }

    public static void matrixMode(int mode) {
        long function_pointer = DefaultGL.glMatrixMode;
        GL11.nglMatrixMode(mode, function_pointer);
    }

    public static void glPopMatrix() {
        long function_pointer = DefaultGL.glPopMatrix;
        GL11.nglPopMatrix(function_pointer);
    }

    public static void pushMatrix() {
        long function_pointer = DefaultGL.glPushMatrix;
        GL11.nglPushMatrix(function_pointer);
    }

    public static void viewport(int x, int y, int width, int height) {
        long function_pointer = DefaultGL.glViewport;
        GL11.nglViewport(x, y, width, height, function_pointer);
    }

    public static void glDisableVertexAttribArray(int index) {
        long function_pointer = DefaultGL.glDisableVertexAttribArray;
        GL20.nglDisableVertexAttribArray(index, function_pointer);
    }

    public static void glEnableVertexAttribArray(int index) {
        long function_pointer = DefaultGL.glEnableVertexAttribArray;
        GL20.nglEnableVertexAttribArray(index, function_pointer);
    }

    public static void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, ByteBuffer buffer) {
        long function_pointer = DefaultGL.glVertexAttribPointer;
        if (LWJGLUtil.CHECKS)
            (StateTracker.getReferences(DefaultGL.caps)).glVertexAttribPointer_buffer[index] = buffer;
        GL20.nglVertexAttribPointer(index, size, type, normalized, stride, MemoryUtil.getAddress(buffer), function_pointer);
    }

    public static void glEnableClientState(int cap) {
        long function_pointer = DefaultGL.glEnableClientState;
        GL11.nglEnableClientState(cap, function_pointer);
    }

    public static void glTexCoordPointer(int size, int type, int stride, ByteBuffer pointer) {
        long function_pointer = DefaultGL.glTexCoordPointer;
        if (LWJGLUtil.CHECKS)
            (StateTracker.getReferences(DefaultGL.caps)).glTexCoordPointer_buffer[(StateTracker.getReferences(DefaultGL.caps)).glClientActiveTexture] = pointer;
        GL11.nglTexCoordPointer(size, type, stride, MemoryUtil.getAddress(pointer), function_pointer);
    }

    public static void glDisableClientState(int cap) {
        long function_pointer = DefaultGL.glDisableClientState;
        GL11.nglDisableClientState(cap, function_pointer);
    }

    public static void glClientActiveTexture(int texture) {
        long function_pointer = DefaultGL.glClientActiveTexture;
        (StateTracker.getReferences(DefaultGL.caps)).glClientActiveTexture = texture - 33984;
        GL13.nglClientActiveTexture(texture, function_pointer);
    }

    public static void glColorPointer(int size, int type, int stride, ByteBuffer pointer) {
        long function_pointer = DefaultGL.glColorPointer;
        if (LWJGLUtil.CHECKS)
            (StateTracker.getReferences(DefaultGL.caps)).GL11_glColorPointer_pointer = pointer;
        GL11.nglColorPointer(size, type, stride, MemoryUtil.getAddress(pointer), function_pointer);
    }

    public static void glNormalPointer(int type, int stride, ByteBuffer pointer) {
        long function_pointer = DefaultGL.glNormalPointer;
        if (LWJGLUtil.CHECKS)
            (StateTracker.getReferences(DefaultGL.caps)).GL11_glNormalPointer_pointer = pointer;
        GL11.nglNormalPointer(type, stride, MemoryUtil.getAddress(pointer), function_pointer);
    }
    {
            System.loadLibrary("glfw");
            System.loadLibrary("jemalloc");
            System.loadLibrary("lwjgl");
            System.loadLibrary("lwjgl_opengl");
            System.loadLibrary("lwjgl_stb");
            System.loadLibrary("lwjgl_tinyfd");
            System.loadLibrary("OpenAL");
            System.loadLibrary("SAPIWrapper_x64");
    }
}
