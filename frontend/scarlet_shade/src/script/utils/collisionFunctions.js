
export function rectangleIntersectionCollision(rectangleOne, rectangleTwo) {

    if (rectangleOne.x < rectangleTwo.x + rectangleTwo.width &&
        rectangleOne.x + rectangleOne.width > rectangleTwo.x &&
        rectangleOne.y < rectangleTwo.y + rectangleTwo.height &&
        rectangleOne.y + rectangleOne.height > rectangleTwo.y) {
            
        return true;
    }

    return false;
}

export function tileIndexCollision(worldX, worldY, tileSize, chunkSize) {

    const tileX = Math.floor(worldX / tileSize);
    const tileY = Math.floor(worldY / tileSize);

    const chunkX = Math.floor(tileX / chunkSize);
    const chunkY = Math.floor(tileY / chunkSize);

    let localX = tileX % chunkSize;
    let localY = tileY % chunkSize;

    if (localX < 0) {
        localX += chunkSize;
    }

    if (localY < 0) {
        localY += chunkSize;
    }

    return { chunkX, chunkY, localX, localY };
}