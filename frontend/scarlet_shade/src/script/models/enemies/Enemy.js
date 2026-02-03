import { GameObject } from "../GameObject";

export class Enemy extends GameObject{

    speed;

    //Put speed HERE BABUINO
    constructor(width, height, posX, posY){
        super(width, height, posX, posY);
        this.speed = 15;
    }

    draw(ctx) {
        // ctx.save();

        ctx.fillStyle = "red";
        ctx.fillRect(this.position.x, this.position.y, this.width, this.height);

        // ctx.restore();
    }

}