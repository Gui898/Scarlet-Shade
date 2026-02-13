import { District } from "../District.js";
import { CHUNK_SIZE } from "../../utils/constants.js";

export class CherryBlossomDistrict extends District {

    constructor() {
        super("Cherry Blossom District", "/maps/cherryBlossomDistrict.json", "/atlas/cherryBlossomDistrictAtlas.json");
    }

    generateChunk(chunk) {

        console.log(`Gerando chunk em: ${chunk.position.x}, ${chunk.position.y}`);
        
        const chunkX = chunk.position.x;
        const chunkY = chunk.position.y;

        const layers = ["ground", "collision", "overhead"];

        for (let y = 0; y < CHUNK_SIZE; y++) {
            for (let x = 0; x < CHUNK_SIZE; x++) {

                const globalX = (chunkX * CHUNK_SIZE) + x;
                const globalY = (chunkY * CHUNK_SIZE) + y;

                const isOutOfBounds = 
                    globalX < 0 || 
                    globalY < 0 || 
                    globalX >= this.mapJson.worldGridWidth || 
                    globalY >= this.mapJson.worldGridHeight;
                    
                if (isOutOfBounds) {

                    chunk.setTile("collision", x, y, 3);

                    continue;
                }

                const tileIndex = (globalY * this.mapJson.worldGridWidth) + globalX;

                layers.forEach(layerName => {

                    const layerData = this.mapJson.data[layerName];

                    if (layerData && layerData[tileIndex] !== undefined) {
                        
                        const tileId = layerData[tileIndex];

                        if (tileId !== 0) {
                            
                            chunk.setTile(layerName, x, y, tileId);
                        }
                    }
                });
            }
        }
    }
}