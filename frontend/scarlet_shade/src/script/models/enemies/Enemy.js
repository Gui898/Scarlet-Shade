import { GameObject } from "../GameObject";

export class Enemy extends GameObject{

    speed;

    constructor(width, height, posX, posY, speed){
        super(width, height, posX, posY);
        this.speed = speed;
    }

    draw(ctx) {
        // ctx.save();

        ctx.fillStyle = "red";
        ctx.fillRect(this.position.x, this.position.y, this.width, this.height);

        // ctx.restore();
    }
}