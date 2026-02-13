import { GameObject } from "../GameObject";
import { CollisionManager } from "../../engine/CollisionManager.js";
import { euclideanDistance } from "../../utils/mathFunctions.js";

export class Player extends GameObject{
    
    speed;

    constructor(width, height, posX, posY, speed){

        super(width, height, posX, posY);
        
        this.speed = speed;
    }

    attemptMove(deltaX, deltaY, worldManager) {

        const steps = Math.ceil(euclideanDistance(deltaX, deltaY));
        
        const stepX = deltaX / steps;
        const stepY = deltaY / steps;

        for (let i = 0; i < steps; i++) {

            const nextX = this.position.x + stepX;
            const nextY = this.position.y + stepY;

            if (!CollisionManager.checkMapCollision(nextX, nextY, this.width, this.height, worldManager)) {
                
                this.position.x = nextX;
                this.position.y = nextY;
            } 
            else {
                
                break;
            }
        }
    }

    moveUp(worldManager) {

        this.attemptMove(0, -this.speed, worldManager);
    }

    moveDown(worldManager) {

        this.attemptMove(0, this.speed, worldManager);
    }

    moveLeft(worldManager) {

        this.attemptMove(-this.speed, 0, worldManager);
    }

    moveRight(worldManager) {

        this.attemptMove(this.speed, 0, worldManager);
    }
}