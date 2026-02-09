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

                if (chunkX === 0 && chunkY === 0) {
                    chunk.setTile(x, y, (x + y) % 2 === 0 ? 1 : 3);
                } 
                else {
                    chunk.setTile(x, y, 1);
                }

                if (x === 8 && chunkX === 0) {
                    chunk.setTile(x, y, 2);
                }
            }
        }
    }
}