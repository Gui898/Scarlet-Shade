export class EntityManager{
    static entities;

    static init(player, camera){
        this.entities = {
            player: player,
            camera: camera,
            enemies: []
        }
    }

    static addEntity(entity){
        this.entities.enemies.push(entity);
    }

    static updateEntity(){

    }

    static removeEntity(){
        
    }

    static drawEntities(ctx){
        this.entities.camera.draw(ctx);
        this.entities.player.draw(ctx);
        this.entities.enemies.forEach((e) => e.draw(ctx));
    }
}