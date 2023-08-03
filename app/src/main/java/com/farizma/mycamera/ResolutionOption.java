package com.farizma.mycamera;

public class ResolutionOption {
    private String name;
    private int width;
    private int height;

    public ResolutionOption(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}