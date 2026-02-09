import { CHUNK_SIZE } from "../utils/constants.js";

export class Chunk {
    
    position;
    layers;
    
    constructor(chunkX, chunky) {
        
        this.position = {
            x: chunkX,
            y: chunky
        }

        this.layers = {
            ground: Array.from({ length: CHUNK_SIZE }, () => new Array(CHUNK_SIZE).fill(0)),
            collision: Array.from({ length: CHUNK_SIZE }, () => new Array(CHUNK_SIZE).fill(0)),
            overhead: Array.from({ length: CHUNK_SIZE }, () => new Array(CHUNK_SIZE).fill(0))
        };
    }

    getTile(layerName, x, y) {
        return this.layers[layerName][y][x];
    }

    setTile(layerName, x, y, id) {
        this.layers[layerName][y][x] = id;
    }
}
