import { GameObject } from "../GameObject";

export class Player extends GameObject{
    
    speed;

    constructor(width, height, posX, posY, speed){
        super(width, height, posX, posY);
        this.speed = speed;
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