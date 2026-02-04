import { GameObject } from "../GameObject";
import { lerp } from "../../utils/mathFunctions.js";

export class Camera extends GameObject{

    canvas;
    smoothness = 0.1;

    constructor(canvas, posX, posY){
        super(canvas.width, canvas.height, posX, posY);
        this.canvas = canvas;
    }

    updateCamera(player) {
        const targetX =
            player.position.x + player.width / 2 - this.width / 2;

        const targetY =
            player.position.y + player.height / 2 - this.height / 2;

        this.position.x = lerp(this.position.x, targetX, this.smoothness);
        this.position.y = lerp(this.position.y, targetY, this.smoothness);
    }

    draw(ctx){
        ctx.translate(-this.position.x, -this.position.y);
    }
}