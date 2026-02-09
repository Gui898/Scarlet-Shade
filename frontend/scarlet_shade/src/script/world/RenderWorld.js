import { CHUNK_SIZE, TILE_SIZE } from "../utils/constants.js";

const TILE_COLORS = {
    1: "green",
    2: "blue",
    3: "red"
};

export class RenderWorld {

    render(ctx, worldManager, camera) {

        const district = worldManager.currentDistrict;
        if (!district) {
            return;
        }

        ctx.clearRect(0, 0, camera.width, camera.height);

        const visibleChunks = this.getVisibleChunks(district, camera);

        for (const chunk of visibleChunks) {
            
            this.drawChunk(ctx, chunk, camera);
        }
    }
    
    getVisibleChunks(district, camera) {
        
        const chunksToRender = [];
        const chunkSizePx = CHUNK_SIZE * TILE_SIZE;

        const startChunkX = Math.floor(camera.position.x / chunkSizePx);
        const endChunkX = Math.floor((camera.position.x + camera.width) / chunkSizePx);
        const startChunkY = Math.floor(camera.position.y / chunkSizePx);
        const endChunkY = Math.floor((camera.position.y + camera.height) / chunkSizePx);

        for (let chunkX = startChunkX; chunkX <= endChunkX; chunkX++) {
            
            for (let chunkY = startChunkY; chunkY <= endChunkY; chunkY++) {

                chunksToRender.push(district.getChunk(chunkX, chunkY));
            }
        }

        return chunksToRender;
    }

    drawChunk(ctx, chunk, camera) {

        for (let y = 0; y < CHUNK_SIZE; y++) {

            for (let x = 0; x < CHUNK_SIZE; x++) {

                const tileId = chunk.getTile(x, y);

                if (tileId === 0) {
                    continue;
                } 

                const worldX = (chunk.position.x * CHUNK_SIZE + x) * TILE_SIZE;
                const worldY = (chunk.position.y * CHUNK_SIZE + y) * TILE_SIZE;

                const screenX = worldX - (camera.position.x || 0);
                const screenY = worldY - (camera.position.y || 0);

                ctx.fillStyle = TILE_COLORS[tileId] || "#000000";
                ctx.fillRect(screenX, screenY, TILE_SIZE, TILE_SIZE);
            }
        }
    }
}