export class InputManager{
    keys;
    canvas;
    keyboardIndex;
    gamepadIndex;

    constructor(canvas){
        this.keys = {
            up: false,
            down: false,
            left: false,
            right: false,
            jump: false,
            dash: false,
            crouch: false,
            attack: false,
            spinAttack: false,
            especialOne: false,
            especialTwo: false,
            menuAccess: false,
            selectItem: false,
            useItem: false,
        }

        this.canvas = canvas;

        addEventListener("keydown", (e) => this.onKeyDown(e));
        addEventListener("keyup", (e) => this.onKeyUp(e));

        this.gamepadIndex = null;
    }

    onKeyDown(event){
        const key = event.key.toLowerCase();
        switch(key){
            case "w": this.keys.up = true; break;
            case "a": this.keys.left = true; break;
            case "s": this.keys.down = true; break;
            case "d": this.keys.right = true; break;
        }
    }

    onKeyUp(event){
        const key = event.key.toLowerCase();
        switch(key){
            case "w": this.keys.up = false; break;
            case "a": this.keys.left = false; break;
            case "s": this.keys.down = false; break;
            case "d": this.keys.right = false; break;
        }
    }

    applyInputs(player) {

        if (this.keys.left) {
            player.moveLeft();
        }
        if (this.keys.right) {
            player.moveRight();
        } 
        if (this.keys.up) {
            player.moveUp();
        } 
        if (this.keys.down) {
            player.moveDown();
        }
    }

}