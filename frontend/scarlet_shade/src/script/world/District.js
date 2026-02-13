import { Chunk } from "./Chunk.js";

export class District {

    name;
    chunks;
    mapJson;
    atlasJson;
    isLoaded = false;

    constructor(name, mapJson, atlasJson) {

        this.name = name;
        this.chunks = new Map();
        this.loadMap(mapJson, atlasJson);
    }

    async loadMap(mapPath, atlasPath) {

        try {

            const responseMap = await fetch(mapPath);
            const responseAtlas = await fetch(atlasPath);

            this.mapJson = await responseMap.json();
            this.atlasJson = await responseAtlas.json();
            this.isLoaded = true;
        } 
        catch (error) {

            console.error("Error Loading Map or Atlas:", error);
        }
    }

    getKey(chunkX, chunkY) {
        return `${chunkX},${chunkY}`;
    }

    hasChunk(chunkX, chunkY) {
        return this.chunks.has(this.getKey(chunkX, chunkY));
    }

    getChunk(chunkX, chunkY) {

        if (!this.isLoaded) {
            return null;
        }

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