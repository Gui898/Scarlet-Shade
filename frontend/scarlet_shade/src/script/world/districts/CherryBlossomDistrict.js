import { District } from "../District.js";
import { CHUNK_SIZE } from "../utils/constants.js";

export class CherryBlossomDistrict extends District {

    constructor() {
        super("Cherry Blossom District");
    }

    generateChunk(chunk) {
        const cx = chunk.position.x;
        const cy = chunk.position.y;

        for (let y = 0; y < CHUNK_SIZE; y++) {
            for (let x = 0; x < CHUNK_SIZE; x++) {

                // Exemplo de Lógica:
                // Se for o chunk (0,0), vamos fazer um padrão de tabuleiro
                if (cx === 0 && cy === 0) {
                    chunk.setTile(x, y, (x + y) % 2 === 0 ? 1 : 4);
                } else {
                    // Outros chunks são apenas grama
                    chunk.setTile(x, y, 1);
                }

                // Criar uma "parede" de teste no meio do caminho
                if (x === 8 && cx === 0) {
                    chunk.setTile(x, y, 2);
                }
            }
        }
    }
}