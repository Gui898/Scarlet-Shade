import { GameObject } from "../GameObject";

export class Camera extends GameObject{

    canvas;

    constructor(canvas, posX, posY){
        super(canvas.width, canvas.height, posX, posY);
        this.canvas = canvas;
    }

    updateCamera(player){
        this.position.x = player.position.x + player.width/2 - this.width/2; 
        this.position.y = player.position.y + player.height/2 - this.height/2; 
    }

    draw(ctx){
        ctx.translate(-this.position.x, -this.position.y);
    }
}