import { CHUNK_SIZE } from "../utils/constants.js";

export class Chunk {
    
    position;
    tiles;
    
    constructor(chunkX, chunky) {
        
        this.position = {
            x: chunkX,
            y: chunky
        }

        this.tiles = Array.from({ length: CHUNK_SIZE }, () =>
            new Array(CHUNK_SIZE).fill(0)
        );
    }

    getTile(x, y) {
        return this.tiles[y][x];
    }

    setTile(x, y, id) {
        this.tiles[y][x] = id;
    }
}
