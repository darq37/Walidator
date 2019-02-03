package com.umcs;

public class Pomiar {
    private Float SURFACE_1, SURFACE_2, SURFACE_3, SURFACE_4;
    private Float x, y;

    public Pomiar(Float x, Float y, Float SURFACE_1, Float SURFACE_2, Float SURFACE_3, Float SURFACE_4) {
        this.x = x;
        this.y = y;
        this.SURFACE_1 = SURFACE_1;
        this.SURFACE_2 = SURFACE_2;
        this.SURFACE_3 = SURFACE_3;
        this.SURFACE_4 = SURFACE_4;
    }

    public Float getSURFACE_2() {
        return SURFACE_2;
    }

    public void setSURFACE_2(Float SURFACE_2) {
        this.SURFACE_2 = SURFACE_2;
    }

    public Float getSURFACE_3() {
        return SURFACE_3;
    }

    public void setSURFACE_3(Float SURFACE_3) {
        this.SURFACE_3 = SURFACE_3;
    }

    public Float getSURFACE_4() {
        return SURFACE_4;
    }

    public void setSURFACE_4(Float SURFACE_4) {
        this.SURFACE_4 = SURFACE_4;
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

    public Float getSURFACE_1() {
        return SURFACE_1;
    }

    public void setSURFACE_1(Float SURFACE_1) {
        this.SURFACE_1 = SURFACE_1;
    }

    @Override
    public String toString() {
        return "[" + x + "]\t\t[" + y + "]\t\t[" + SURFACE_1 + "]\t\t[" + SURFACE_2 + "]\t\t[" + SURFACE_3 + "]\t\t[" + SURFACE_4 + "]";
    }
}
