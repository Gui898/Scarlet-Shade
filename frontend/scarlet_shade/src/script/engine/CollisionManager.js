export class CollisionManager {

    static rectangleIntersectionCollision(rectangleOne, rectangleTwo) {

        if (rectangleOne.x < rectangleTwo.x + rectangleTwo.width &&
            rectangleOne.x + rectangleOne.width > rectangleTwo.x &&
            rectangleOne.y < rectangleTwo.y + rectangleTwo.height &&
            rectangleOne.y + rectangleOne.height > rectangleTwo.y) {
            
            return true;
        }
        return false;
    }
}