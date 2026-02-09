export class CollisionManager {

    static checkMapCollision(x, y, width, height, worldManager) {

        const points = [
            { x: x + 2, y: y + 2 },
            { x: x + width - 2, y: y + 2 },
            { x: x + 2, y: y + height - 2 },
            { x: x + width - 2, y: y + height - 2 }
        ];

        for (let point of points) {

            const tileId = worldManager.getTileAt(point.x, point.y, "collision");
            
            if (tileId > 0) {
                return true;
            } 
        }
        return false;
    }
}