import { GameObject } from "../GameObject";

export class Camera extends GameObject{

    canvas;
    smoothness = 0.1;

    constructor(canvas, posX, posY){
        super(canvas.width, canvas.height, posX, posY);
        this.canvas = canvas;
    }

    // a = currentPosition, b = targetPosition, t = smoothness
    lerp(a, b, t){
        return a + (b-a) * t
    } 

    updateCamera(player) {
        const targetX =
            player.position.x + player.width / 2 - this.width / 2;

        const targetY =
            player.position.y + player.height / 2 - this.height / 2;

        this.position.x = this.lerp(this.position.x, targetX, this.smoothness);
        this.position.y = this.lerp(this.position.y, targetY, this.smoothness);
    }

    draw(ctx){
        ctx.translate(-this.position.x, -this.position.y);
    }
}