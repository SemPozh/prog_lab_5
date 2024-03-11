package models;

import exceptions.InvalidObjectFieldException;

public class Coordinates {
    private Integer x;
    private double y = 0.0;

    public Coordinates(Integer x, double y) throws InvalidObjectFieldException {
        this.setX(x);
        this.setY(y);
    }

    public Coordinates(Integer x) throws InvalidObjectFieldException {
        this.setX(x);
    }
    public void setX(Integer x) throws InvalidObjectFieldException {
        if (validateX(x)){
            this.x = x;
        } else {
            throw new InvalidObjectFieldException("X-coordinate can't be <= -393");
        }
    }

    public void setY(double y) throws InvalidObjectFieldException {
        if (validateY(y)){
            this.y = y;
        } else {
            throw new InvalidObjectFieldException("X-coordinate can't be > 518");
        }
    }

    private boolean validateX(Integer x){
        return x > -393;
    }

    private boolean validateY(double y){
        return y <= 518;
    }

    public Integer getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
