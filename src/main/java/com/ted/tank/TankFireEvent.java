package com.ted.tank;

public class TankFireEvent {
    public Tank source;

    public TankFireEvent(Tank source) {
        this.source = source;
    }

    public Tank getSource() {
        return source;
    }
}
