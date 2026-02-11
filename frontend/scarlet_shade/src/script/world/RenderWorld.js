import { CHUNK_SIZE, TILE_SIZE } from "../utils/constants.js";

const TILE_COLORS = {
    ground: {
        1: "#96FF37",
        2: "#F4C4A4",
        3: "#9E7166"
    },
    collision: {
        3: "#E74BA1",
        4: "#FF3838",
        5: "#39ACDE",
        6: "#626669",
        7: "#9A4835",
        8: "#A22633"
    },
    overhead: {
        3: "#A22633"
    }
};

export class RenderWorld {

    renderLayer(ctx, worldManager, camera, layerName) {

        const district = worldManager.currentDistrict;

        const visibleChunks = this.getVisibleChunks(district, camera);

        for (const chunk of visibleChunks) {

            this.drawChunkLayer(ctx, chunk, layerName);
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
                if (chunk) { // Só adiciona se o chunk existir/estiver carregado
                    chunksToRender.push(chunk);
                }
            }
        }
        return chunksToRender;
    }

    drawChunkLayer(ctx, chunk, layerName) {
        for (let y = 0; y < CHUNK_SIZE; y++) {
            for (let x = 0; x < CHUNK_SIZE; x++) {

                const tileId = chunk.getTile(layerName, x, y);

                if (tileId === 0) continue;

                const worldX = (chunk.position.x * CHUNK_SIZE + x) * TILE_SIZE;
                const worldY = (chunk.position.y * CHUNK_SIZE + y) * TILE_SIZE;

                // Busca a cor dentro da camada específica
                const layerColors = TILE_COLORS[layerName];
                ctx.fillStyle = (layerColors && layerColors[tileId]) ? layerColors[tileId] : "#000000";

                ctx.fillRect(worldX, worldY, TILE_SIZE + 1, TILE_SIZE + 1);
            }
        }
    }
}