import { CherryBlossomDistrict } from "../world/districts/CherryBlossomDistrict.js";
import { CollisionManager } from "./CollisionManager.js";

export class WorldManager {
     
    districts;
    currentDistrict;
    connections;

    constructor() {

        cherryBlossomDistrict = new CherryBlossomDistrict();
        
        this.districts = {
            cherryBlossomDistrict: cherryBlossomDistrict
        };

        this.currentDistrict = cherryBlossomDistrict;
        this.connections = new Map();
    }


    // Method to add the connections on the World; 
    // Probably replaced on the future;
    addConnection(fromDistrict, toDistrict, triggerArea, targetSpawn) {
        if (!this.connections.has(fromDistrict)) {
            this.connections.set(fromDistrict, []);
        }
        
        this.connections.get(fromDistrict).push({
            targetDistrict: toDistrict,
            area: triggerArea, 
            spawn: targetSpawn 
        });
    }

    // Method to check the player position and then, change the district;
    checkConnection(player) {

        const activeConnections = this.connections.get(this.currentDistrict.name);
        
        if (!activeConnections) return;

        for (const connection of activeConnections) {
            
            rectangleOne = {
                x: player.position.x,
                y: player.position.y,
                width: player.width,
                height: player.height
            };

            rectangleTwo = {
                x: connection.area.x,
                y: connection.area.y,
                width: connection.area.width,
                height: connection.area.height
            };

            if (CollisionManager.rectangleIntersectionCollision(rectangleOne, rectangleTwo)) {
                
                this.changeDistrict(connection.targetDistrict, connection.spawn, player);
                break;
            }
        }
    }

    changeDistrict() {
        const nextDistrict = this.districts[targetDistrictName];
    
        if (nextDistrict) {
            this.currentDistrict = nextDistrict;
            
            player.position.x = spawnPoint.x;
            player.position.y = spawnPoint.y;
        }
    }
}