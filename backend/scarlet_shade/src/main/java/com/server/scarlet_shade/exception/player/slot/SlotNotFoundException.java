package com.server.scarlet_shade.exception.player.slot;

public class SlotNotFoundException extends RuntimeException{

    public SlotNotFoundException(){
        super("Slot not found");
    }

    public SlotNotFoundException(String message){
        super(message);
    }

}
