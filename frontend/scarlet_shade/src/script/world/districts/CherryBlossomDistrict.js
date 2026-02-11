import { District } from "../District.js";
import { CHUNK_SIZE } from "../../utils/constants.js";

export class CherryBlossomDistrict extends District {

    constructor() {
        super("Cherry Blossom District", "/maps/CherryBlossomDistrict.json");
    }

    generateChunk(chunk) {
        const chunkX = chunk.position.x;
        const chunkY = chunk.position.y;

        const layers = ["ground", "collision", "overhead"];

        for (let y = 0; y < CHUNK_SIZE; y++) {
            for (let x = 0; x < CHUNK_SIZE; x++) {
                const globalX = (chunkX * CHUNK_SIZE) + x;
                const globalY = (chunkY * CHUNK_SIZE) + y;

                if (globalX >= this.mapJson.worldGridWidth || globalY >= this.mapJson.worldGridHeight) {
                    continue;
                }

                const tileIndex = (globalY * this.mapJson.worldGridWidth) + globalX;

                layers.forEach(layerName => {
                    // --- AQUI ENTRA A PROTEÇÃO ---
                    const layerData = this.mapJson.data[layerName];

                    // Verificamos: 
                    // 1. Se a camada existe no JSON (layerData)
                    // 2. Se o ID do tile não é zero (vazio)
                    if (layerData && layerData[tileIndex] !== undefined) {
                        const tileId = layerData[tileIndex];

                        if (tileId !== 0) {
                            chunk.setTile(layerName, x, y, tileId);
                        }
                    }
                    // -----------------------------
                });
            }
        }
    }
}