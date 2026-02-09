export class EntityManager{

    static entities;

    static init(player, camera){

        this.entities = {
            player: player,
            camera: camera,
            enemies: []
        }
    }

    static addEntity(entity) {

        this.entities.enemies.push(entity);
    }

    static updateEntity() {

    }

    static removeEntity() {
        
    }

    static drawEntities(ctx, renderWorld, worldManager) {

        let camera = this.entities.camera;

        camera.draw(ctx);
        
        renderWorld.renderLayer(ctx, worldManager, camera, "ground");

        renderWorld.renderLayer(ctx, worldManager, camera, "collision");

        this.entities.player.draw(ctx);

        this.entities.enemies.forEach((e) => e.draw(ctx));

        renderWorld.renderLayer(ctx, worldManager, camera, "overhead");
    }
}