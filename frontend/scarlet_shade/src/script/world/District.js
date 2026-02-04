export class District {
    
    name;
    chunks;

    constructor(name) {

        this.name = name;
        this.chunks = new Map();
    }

    getKey(chunkX, chunkY) {
        return `${chunkX},${chunkY}`;
    }

    hasChunk(chunkX, chunkY) {
        return this.chunks.has(this.getKey(chunkX, chunkY));
    }

    getChunk(chunkX, chunkY) {
        
        if (!this.hasChunk(chunkX, chunkY)) {
            
            const chunk = new Chunk(chunkX, chunkY);

            this.generateChunk(chunk);

            this.chunks.set(this.getKey(chunkX, chunkY), chunk);
        }

        return this.chunks.get(this.getKey(chunkX, chunkY));
    }

    generateChunk(chunk) {}
}