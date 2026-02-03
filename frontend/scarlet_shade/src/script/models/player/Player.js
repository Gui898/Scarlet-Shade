import { GameObject } from "../GameObject";

export class Player extends GameObject{
    
    speed;

    //Put speed HERE BABUINO
    constructor(width, height, posX, posY){
        super(width, height, posX, posY);
        this.speed = 15;
    }

    moveUp(){
        this.position.y -= this.speed;
    }

    moveDown(){
        this.position.y += this.speed;
    }

    moveLeft(){
        this.position.x -= this.speed;
    }

    moveRight(){
        this.position.x += this.speed;
    }
}