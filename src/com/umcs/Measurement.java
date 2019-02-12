package com.umcs;

public class Measurement {
    private Float firstSurface, secondSurface, thirdSurface, fourthSurface;
    private Float x, y;
    private Boolean active;
    private Boolean disconnected;

    public Measurement(Float x, Float y, Float firstSurface, Float secondSurface, Float thirdSurface, Float fourthSurface, Boolean disconnected) {
        this.x = x;
        this.y = y;
        this.firstSurface = firstSurface;
        this.secondSurface = secondSurface;
        this.thirdSurface = thirdSurface;
        this.fourthSurface = fourthSurface;
        this.disconnected = disconnected;
    }

    public Boolean isDisconnected() {
        return disconnected;
    }

    public void setDisconnected(Boolean disconnected) {
        this.disconnected = disconnected;
    }

    public Float getSecondSurface() {
        return secondSurface;
    }

    public void setSecondSurface(Float secondSurface) {
        this.secondSurface = secondSurface;
    }

    public Float getThirdSurface() {
        return thirdSurface;
    }

    public void setThirdSurface(Float thirdSurface) {
        this.thirdSurface = thirdSurface;
    }

    public Float getFourthSurface() {
        return fourthSurface;
    }

    public void setFourthSurface(Float fourthSurface) {
        this.fourthSurface = fourthSurface;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getFirstSurface() {
        return firstSurface;
    }

    public void setFirstSurface(Float firstSurface) {
        this.firstSurface = firstSurface;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "[" + x + "]\t[" + y + "]\t";
    }
}
