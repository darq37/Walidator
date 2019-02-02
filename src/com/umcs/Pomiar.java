package com.umcs;

public class Pomiar {
    private Float z0, z1, z2, z3;
    private Float x, y;

    public Pomiar(Float x, Float y, Float z0, Float z1, Float z2, Float z3) {
        this.x = x;
        this.y = y;
        this.z0 = z0;
        this.z1 = z1;
        this.z2 = z2;
        this.z3 = z3;
    }

    public Float getZ1() {
        return z1;
    }

    public void setZ1(Float z1) {
        this.z1 = z1;
    }

    public Float getZ2() {
        return z2;
    }

    public void setZ2(Float z2) {
        this.z2 = z2;
    }

    public Float getZ3() {
        return z3;
    }

    public void setZ3(Float z3) {
        this.z3 = z3;
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

    public Float getZ0() {
        return z0;
    }

    public void setZ0(Float z0) {
        this.z0 = z0;
    }

    @Override
    public String toString() {
        return "[" + x + "]\t\t[" + y + "]\t\t[" + z0 + "]\t\t[" + z1 + "]\t\t[" + z2 + "]\t\t[" + z3 + "]";
    }
}
