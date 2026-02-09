import { District } from "../District.js";
import { CHUNK_SIZE } from "../../utils/constants.js";

export class CherryBlossomDistrict extends District {

    constructor() {
        super("Cherry Blossom District");
    }

    generateChunk(chunk) {
        
        const chunkX = chunk.position.x;
        const chunkY = chunk.position.y;

        for (let y = 0; y < CHUNK_SIZE; y++) {
            for (let x = 0; x < CHUNK_SIZE; x++) {

                chunk.setTile("ground", x, y, 1);

                // 2. APENAS no chunk (0,0) colocamos um "telhado" rosa
                if (chunkX === 0 && chunkY === 0) {
                    // Vamos colocar overhead apenas em uma parte para você ver a diferença
                    if (x > 5 && y > 5) {
                        chunk.setTile("overhead", x, y, 3); 
                    }
                }

                // 3. Colocamos uma parede azul para testar a camada de colisão
                if (x === 8 && chunkX === 0) {
                    chunk.setTile("collision", x, y, 2);
                }
            }
        }
    }
}