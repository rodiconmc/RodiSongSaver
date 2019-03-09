package com.rodiconmc.rodisongsaver;

class Note {
    private int instrument;
    private int key;

    Note(int instrument, int key) {
        this.instrument = instrument;
        this.key = key;
    }

    public int getInstrument() {
        return instrument;
    }

    public int getKey() {
        return key;
    }
}
