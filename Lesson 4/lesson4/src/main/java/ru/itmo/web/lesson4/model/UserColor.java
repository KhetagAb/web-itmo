package ru.itmo.web.lesson4.model;

public enum UserColor {
    RED("red"),
    GREEN("green"),
    BLUE("blue");

    private final String colorId;

    UserColor(String colorId) {
        this.colorId = colorId;
    }

    public String getColorId() {
        return colorId;
    }
}
