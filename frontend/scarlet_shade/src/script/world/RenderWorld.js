import { CHUNK_SIZE, TILE_SIZE } from "../utils/constants.js";

export class RenderWorld {

    renderLayer(ctx, worldManager, camera, layerName) {

        const district = worldManager.currentDistrict;

        const visibleChunks = this.getVisibleChunks(district, camera);

        for (const chunk of visibleChunks) {

            this.drawChunkLayer(ctx, chunk, layerName, district.atlasJson);
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
                
                const chunk = district.getChunk(chunkX, chunkY);
                
                if (chunk) {
                    chunksToRender.push(chunk);
                }
            }
        }

        return chunksToRender;
    }

    drawChunkLayer(ctx, chunk, layerName, atlas) {

        for (let y = 0; y < CHUNK_SIZE; y++) {
            for (let x = 0; x < CHUNK_SIZE; x++) {

                const tileId = chunk.getTile(layerName, x, y);

                if (tileId === 0) {
                    continue;
                }

                const worldX = (chunk.position.x * CHUNK_SIZE + x) * TILE_SIZE;
                const worldY = (chunk.position.y * CHUNK_SIZE + y) * TILE_SIZE;

                const layerColors = atlas[layerName];
                ctx.fillStyle = (layerColors && layerColors[tileId]) ? layerColors[tileId] : "#000000";

                ctx.fillRect(worldX, worldY, TILE_SIZE + 1, TILE_SIZE + 1);
            }
        }
    }
}