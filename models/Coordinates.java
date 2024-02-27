package models;

public class Coordinates {
    private Integer x;
    private double y;

    public Coordinates(Integer x, double y){
        this.setX(x);
        this.setY(y);
    }
    public void setX(Integer x){
        if (validateX(x)){
            this.x = x;
        }
    }

    public void setY(double y){
        if (validateY(y)){
            this.y = y;
        }
    }

    private boolean validateX(Integer x){
        return x > -393;
    }

    private boolean validateY(double y){
        return y <= 518;
    }
}
