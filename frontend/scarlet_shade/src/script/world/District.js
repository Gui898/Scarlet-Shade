import { Chunk } from "./Chunk.js";

export class District {

    name;
    chunks;
    mapJson;
    isLoaded = false;

    constructor(name, mapJson) {
        this.name = name;
        this.chunks = new Map();
        this.loadMap(mapJson);
    }

    async loadMap(mapPath) {
        try {
            const response = await fetch(mapPath);
            this.mapJson = await response.json();
            this.isLoaded = true;
        } catch (error) {
            console.error("Erro ao carregar o mapa:", error);
        }
    }

    getKey(chunkX, chunkY) {
        return `${chunkX},${chunkY}`;
    }

    hasChunk(chunkX, chunkY) {
        return this.chunks.has(this.getKey(chunkX, chunkY));
    }

    getChunk(chunkX, chunkY) {

        if (!this.isLoaded || chunkX < 0 || chunkY < 0) return null;

        if (!this.hasChunk(chunkX, chunkY)) {
            const chunk = new Chunk(chunkX, chunkY);

            this.generateChunk(chunk);

            this.chunks.set(this.getKey(chunkX, chunkY), chunk);
        }

        return this.chunks.get(this.getKey(chunkX, chunkY));
    }

    generateChunk(chunk) {
        throw new Error("generateChunk must be implemented");
    }
}