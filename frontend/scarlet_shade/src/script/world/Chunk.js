import { CHUNK_SIZE } from "../utils/constants.js";

export class Chunk {
    constructor(x, y) {
        this.position = { x, y };
        this.layers = {
            ground: this.createEmptyGrid(),
            collision: this.createEmptyGrid(),
            overhead: this.createEmptyGrid()
        };
    }

    createEmptyGrid() {
        // Cria uma matriz CHUNK_SIZE x CHUNK_SIZE preenchida com 0
        return Array.from({ length: CHUNK_SIZE }, () => 
            new Array(CHUNK_SIZE).fill(0)
        );
    }

    setTile(layerName, x, y, tileId) {
        if (this.layers[layerName]) {
            this.layers[layerName][y][x] = tileId;
        } else {
            console.warn(`Camada ${layerName} não existe no Chunk.`);
        }
    }

    getTile(layerName, x, y) {
        if (this.layers[layerName]) {
            return this.layers[layerName][y][x];
        }
        return 0;
    }
}