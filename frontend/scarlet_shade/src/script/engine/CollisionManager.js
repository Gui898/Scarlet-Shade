export class CollisionManager {
    static checkMapCollision(x, y, width, height, worldManager) {
        // Proteção: se o mapa não carregou, não permite mover (ou ignora colisão)
        if (!worldManager.currentDistrict || !worldManager.currentDistrict.isLoaded) return false;

        const points = [
            { x: x + 1, y: y + 1 },
            { x: x + width - 1, y: y + 1 },
            { x: x + 1, y: y + height - 1 },
            { x: x + width - 1, y: y + height - 1 }
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