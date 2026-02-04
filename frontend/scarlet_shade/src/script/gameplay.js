import { Enemy } from "./models/enemies/Enemy.js";
import { Camera } from "./models/player/Camera.js";
import { Player } from "./models/player/Player.js";
import { GameObject } from "./models/GameObject.js";
import { InputManager } from "./engine/InputManager.js";
import { onMount } from "svelte";
import { EntityManager } from "./engine/EntityManager.js";

let inputManager;

export function startGame(canvas) {

    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;

    addEventListener('resize', () => {
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;
    });

    const ctx = canvas.getContext("2d");

    inputManager = new InputManager(canvas);

    EntityManager.init(new Player(100, 100, 100, 100, 15), new Camera(canvas, 0, 0));

    EntityManager.addEntity(new Enemy(100, 100, 600, 300, 15));
    EntityManager.addEntity(new Enemy(100, 100, 6000, 100, 15));

    gameloop(canvas, ctx);
}

const gameloop = (canvas, ctx) => {
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    
    EntityManager.entities.camera.updateCamera(EntityManager.entities.player);
    ctx.save();
    
    EntityManager.drawEntities(ctx);

    ctx.restore();
    inputManager.applyInputs(EntityManager.entities.player);

    requestAnimationFrame(() => gameloop(canvas, ctx));
}