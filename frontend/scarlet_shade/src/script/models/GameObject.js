export class GameObject {

    width;
    height;
    posX;
    posY;

    constructor(width, height, posX, posY){
        this.width = width;
        this.height = height;
        this.position = {
            x: posX,
            y: posY
        }
    }

    draw(ctx) {
        // ctx.save();

        ctx.fillStyle = "yellow";
        ctx.fillRect(this.position.x, this.position.y, this.width, this.height);

        // ctx.restore();
    }
}

