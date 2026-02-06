import { CHUNK_SIZE, TILE_SIZE } from "../utils/constants.js";

const TILE_COLORS = {
    1: "green",
    2: "blue",
    3: "red"
};

export class RenderWorld {

    render(ctx, worldManager, camera) {
        const district = worldManager.currentDistrict;
        if (!district) return;

        // 1. Limpar a tela antes de desenhar o novo frame
        ctx.clearRect(0, 0, camera.width, camera.height);

        // 2. Pegar apenas os chunks que a câmera consegue ver
        const visibleChunks = this.getVisibleChunks(district, camera);

        // 3. Desenhar os tiles de cada chunk
        for (const chunk of visibleChunks) {
            this.drawChunk(ctx, chunk, camera);
        }
    }
    getVisibleChunks(district, camera) {
        const chunksToRender = [];
        const chunkSizePx = CHUNK_SIZE * TILE_SIZE;

        // Matemática: Determinar o intervalo de índices de chunks visíveis
        const startX = Math.floor(camera.position.x / chunkSizePx);
        const endX = Math.floor((camera.position.x + camera.width) / chunkSizePx);
        const startY = Math.floor(camera.position.y / chunkSizePx);
        const endY = Math.floor((camera.position.y + camera.height) / chunkSizePx);

        // Percorrer esse intervalo e buscar no Map do Distrito
        for (let cx = startX; cx <= endX; cx++) {
            for (let cy = startY; cy <= endY; cy++) {
                if (district.hasChunk(cx, cy)) {
                    chunksToRender.push(district.getChunk(cx, cy));
                }
            }
        }
        return chunksToRender;
    }

    drawChunk(ctx, chunk, camera) {
        for (let y = 0; y < CHUNK_SIZE; y++) {
            for (let x = 0; x < CHUNK_SIZE; x++) {
                const tileId = chunk.getTile(x, y);

                // Pular se for vazio (ID 0)
                if (tileId === 0) continue;

                const color = TILE_COLORS[tileId] || "#000000"; // Preto se ID não existir

                // Cálculo da posição no mundo (absoluta)
                const worldX = (chunk.position.x * CHUNK_SIZE + x) * TILE_SIZE;
                const worldY = (chunk.position.y * CHUNK_SIZE + y) * TILE_SIZE;

                // Cálculo da posição na tela (relativa à câmera)
                const screenX = worldX - camera.position.x;
                const screenY = worldY - camera.position.y;

                // Desenhar o quadrado
                ctx.fillStyle = color;
                ctx.fillRect(
                    screenX,
                    screenY,
                    TILE_SIZE,
                    TILE_SIZE
                );
            }
        }
    }
}