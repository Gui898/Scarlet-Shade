import { GameObject } from "../GameObject";
import { CollisionManager } from "../../engine/CollisionManager.js";

export class Player extends GameObject{
    
    speed;

    constructor(width, height, posX, posY, speed){

        super(width, height, posX, posY);
        
        this.speed = speed;
    }

    moveUp(worldManager){
        
        const nextY = this.position.y - this.speed;

        if (!CollisionManager.checkMapCollision(this.position.x, nextY, this.width, this.height, worldManager)) {
            
            this.position.y = nextY;
        }
    }

    moveDown(worldManager){
        
        const nextY = this.position.y + this.speed;

        if (!CollisionManager.checkMapCollision(this.position.x, nextY, this.width, this.height, worldManager)) {
            
            this.position.y = nextY;
        }
    }

    moveLeft(worldManager){
        
        const nextX = this.position.x - this.speed;

        if (!CollisionManager.checkMapCollision(nextX, this.position.y, this.width, this.height, worldManager)) {
            
            this.position.x = nextX;
        }
    }

    moveRight(worldManager){
        
        const nextX = this.position.x + this.speed;
        
        if (!CollisionManager.checkMapCollision(nextX, this.position.y, this.width, this.height, worldManager)) {
            this.position.x = nextX;
        }
    }
}